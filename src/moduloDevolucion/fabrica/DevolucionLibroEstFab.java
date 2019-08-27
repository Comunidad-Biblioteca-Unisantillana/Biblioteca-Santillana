package moduloDevolucion.fabrica;

import controller.exceptions.NonexistentEntityException;
import java.sql.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.ServicioFecha;
import moduloDevolucion.DAO.DevolucionLibroDAOEst;
import moduloDevolucion.IDevolucion;
import moduloDevolucion.entitys.DevolucionLibroEst;
import moduloPrestamo.DAO.PrestamoLibroDAOEst;
import moduloPrestamo.entitys.PrestamoLibroEst;
import moduloReserva.DAO.ReservaColgenDAOEst;
import moduloReserva.entitys.ReservaColgenEstudiante;
import recursos1.controllers.LibroJpaController;
import recursos1.entitys.Libro;
import vista.AlertBox;
import vista.IAlertBox;

/**
 * @author Camilo Jaramillo
 * @version 1.0
 * @created 04-ago.-2019 10:37:38 a. m.
 */
public class DevolucionLibroEstFab implements IDevolucion {

    public DevolucionLibroEstFab() {

    }

    @Override
    public boolean ejecutarDevolucion(String codBarras, String idBibliotecario, String estadoRecurso) {
        IAlertBox alert = new AlertBox();
        try {
            LibroJpaController control = new LibroJpaController();
            Libro libro = control.findLibro(codBarras);
            if (libro != null) {
                int codPrestamo = consultarPrestamoLibro(codBarras);
                if (codPrestamo > 0) {

                    PrestamoLibroDAOEst prestDAOEst = new PrestamoLibroDAOEst();
                    PrestamoLibroEst prestEst = prestDAOEst.readDAO(codPrestamo);
                    if (prestEst.getDevuelto().equalsIgnoreCase("no")) {
                        java.util.Date fechaDevolucion =  new java.util.Date();
                        
                        DevolucionLibroEst devEst = new DevolucionLibroEst(prestEst.getCodPrestamoLibroEst(),
                                idBibliotecario, new Date(fechaDevolucion.getTime()), estadoRecurso);
                        DevolucionLibroDAOEst devDAOEst = new DevolucionLibroDAOEst();
                        devDAOEst.createDAO(devEst);
                        //se verifica si esta reservado
                        ReservaColgenDAOEst reservaDAO = new ReservaColgenDAOEst();
                        ReservaColgenEstudiante reserva = reservaDAO.readDAO(codBarras);
                        if((libro.getCodcategoriacoleccion().getNombrecol().equalsIgnoreCase("general")) && (reserva != null)){
                            reserva.setFechaRetencion(new Date(fechaDevolucion.getTime()));
                            java.util.Date fechaLimiteReserva = ServicioFecha.sumarDiasAFecha(fechaDevolucion, 5);
                            reserva.setFechaLimiteReserva(new Date(fechaLimiteReserva.getTime()));
                            libro.setDisponibilidad("reservado");
                        }else{
                            libro.setDisponibilidad("disponible");
                        }
                        
                        control.edit(libro);
                        //se actualiza el prestamo
                        prestEst.setDevuelto("si");
                        prestDAOEst.updateDAO(prestEst);
                        alert.showAlert("Anuncio", "Devolución libro", "La devolución del usuario con codigo "
                                + prestEst.getCodEstudiante() + " se realizo con exito");
                    } else {
                        alert.showAlert("Anuncio", "Devolución libro", "El libro se había devuelto anteriormente");
                    }
                    return true;
                }
            }
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(DevolucionDiccionarioEstFab.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(DevolucionDiccionarioEstFab.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public int consultarPrestamoLibro(String codBarras) {
        PrestamoLibroDAOEst prestDAOEst = new PrestamoLibroDAOEst();
        List<PrestamoLibroEst> prestamos = prestDAOEst.readAllDAO();
        int codPrestamo = 0;
        for (int i = 0; i < prestamos.size(); i++) {
            if (prestamos.get(i).getCodBarraLibro().equalsIgnoreCase(codBarras)) {
                codPrestamo = prestamos.get(i).getCodPrestamoLibroEst();
                break;
            }
        }
        return codPrestamo;
    }

    public boolean verificarReserva(String codBarras) {
        return false;
    }

    public void notificarDevolucion(String codEstudiante, String tituloRecurso, java.util.Date fechaDevolucion) {

    }
}

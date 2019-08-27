package moduloDevolucion.fabrica;

import controller.exceptions.NonexistentEntityException;
import java.sql.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.ServicioFecha;
import moduloDevolucion.DAO.DevolucionLibroDAOProf;
import moduloDevolucion.IDevolucion;
import moduloDevolucion.entitys.DevolucionLibroProf;
import moduloPrestamo.DAO.PrestamoLibroDAOProf;
import moduloPrestamo.entitys.PrestamoLibroProf;
import moduloReserva.DAO.ReservaColgenDAOProf;
import moduloReserva.entitys.ReservaColgenProfesor;
import recursos1.controllers.LibroJpaController;
import recursos1.entitys.Libro;
import vista.AlertBox;
import vista.IAlertBox;

/**
 * @author Camilo Jaramillo
 * @version 1.0
 * @created 04-ago.-2019 10:37:41 a. m.
 */
public class DevolucionLibroProfFab implements IDevolucion {

    public DevolucionLibroProfFab() {

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
                    PrestamoLibroDAOProf prestDAOProf = new PrestamoLibroDAOProf();
                    PrestamoLibroProf prestProf = prestDAOProf.readDAO(codPrestamo);
                    if (prestProf.getDevuelto().equalsIgnoreCase("no")) {
                        java.util.Date fechaDevolucion =  new java.util.Date();
                        
                        DevolucionLibroProf devProf = new DevolucionLibroProf(prestProf.getCodPrestamoLibroProf(),
                                idBibliotecario, new Date(fechaDevolucion.getTime()), estadoRecurso);
                        DevolucionLibroDAOProf devDAOProf = new DevolucionLibroDAOProf();
                        devDAOProf.createDAO(devProf);
                        ReservaColgenDAOProf reservaDAO = new ReservaColgenDAOProf();
                        ReservaColgenProfesor reserva = reservaDAO.readDAO(codBarras);
                        if((libro.getCodcategoriacoleccion().getNombrecol().equalsIgnoreCase("general")) && (reserva != null)){
                            reserva.setFechaRetencion(new Date(fechaDevolucion.getTime()));
                            java.util.Date fechaLimiteReserva = ServicioFecha.sumarDiasAFecha(fechaDevolucion, 5);
                            reserva.setFechaLimiteReserva(new Date(fechaLimiteReserva.getTime()));
                            libro.setDisponibilidad("reservado");
                        }else{
                            libro.setDisponibilidad("disponible");
                        }
                        control.edit(libro);
                        //Se actualiza el prestamo
                        prestProf.setDevuelto("si");
                        prestDAOProf.updateDAO(prestProf);
                        alert.showAlert("Anuncio", "Devolución libro", "La devolución del usuario con codigo"
                                + prestProf.getIdProfesor() + "se realizo con exito");
                    } else {
                        alert.showAlert("Anuncio", "Devolución libro", "El libro se había devuelto anteriormente");
                    }
                    return true;
                } else {
                    alert.showAlert("Anuncio", "Devolución libro", "El prestamo del libro no existe");
                }
            } else {
                alert.showAlert("Anuncio", "Devolución libro", "El libro no existe");
            }
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(DevolucionDiccionarioEstFab.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(DevolucionDiccionarioEstFab.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public int consultarPrestamoLibro(String codBarras) {
        PrestamoLibroDAOProf prestDAOProf = new PrestamoLibroDAOProf();
        List<PrestamoLibroProf> prestamos = prestDAOProf.readAllDAO();
        int codPrestamo = 0;
        for (int i = 0; i < prestamos.size(); i++) {
            if (prestamos.get(i).getCodBarraLibro().equalsIgnoreCase(codBarras)) {
                codPrestamo = prestamos.get(i).getCodPrestamoLibroProf();
                break;
            }
        }
        return codPrestamo;
    }

    public boolean verificarReserva(String codBarras) {
        return false;
    }

    public void notificarDevolucion(String idProfesor, String tituloRecurso, java.util.Date fechaDevolucion) {

    }
}

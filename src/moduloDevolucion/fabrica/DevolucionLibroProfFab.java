package moduloDevolucion.fabrica;

import controller.exceptions.NonexistentEntityException;
import java.sql.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import general.modelo.ServicioFecha;
import moduloDevolucion.DAO.DevolucionLibroDAOProf;
import moduloDevolucion.modelo.IDevolucion;
import moduloDevolucion.entitys.DevolucionLibroProf;
import moduloPrestamo.DAO.PrestamoLibroDAOProf;
import moduloPrestamo.entitys.PrestamoLibroProf;
import moduloReserva.DAO.ReservaColgenDAOProf;
import moduloReserva.entitys.ReservaColgenProfesor;
import recursos.controllers.LibroJpaController;
import recursos.entitys.Libro;
import general.vista.AlertBox;
import general.vista.IAlertBox;
import moduloReserva.DAO.ReservaColgenDAOEst;
import moduloReserva.DAO.ReservaRecursoDAOAbs;
import moduloReserva.entitys.ReservaColgenEstudiante;
import moduloReserva.modelo.VerificaReserva;

/**
 * @author Julian
 * @creado: 04/08/2019
 * @author Mifuel Fernández
 * @modificado: 07/09/2019
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
                        java.util.Date fechaDevolucion = new java.util.Date();

                        DevolucionLibroProf devProf = new DevolucionLibroProf(prestProf.getCodPrestamoLibroProf(),
                                idBibliotecario, new Date(fechaDevolucion.getTime()), estadoRecurso);
                        DevolucionLibroDAOProf devDAOProf = new DevolucionLibroDAOProf();
                        devDAOProf.createDAO(devProf);
                        //se verifica si esta reservado
                        Object reserva = verificarReserva(codBarras);
                        
                        if ((libro.getCodcategoriacoleccion().getNombrecol().equalsIgnoreCase("general")) && (reserva != null)) {
                            if (reserva instanceof ReservaColgenEstudiante) {
                                ReservaColgenDAOEst reservaDAO = new ReservaColgenDAOEst();
                                ReservaColgenEstudiante reservaEst = (ReservaColgenEstudiante) reserva;

                                if (reservaDAO.updateDAO(reservaEst)) {
                                    new VerificaReserva().notificarRetencionEmailEst(libro);
                                }
                            } else {
                                ReservaColgenDAOProf reservaDAO = new ReservaColgenDAOProf();
                                ReservaColgenProfesor reservaProf = (ReservaColgenProfesor) reserva;
                                
                                if (reservaDAO.updateDAO(reservaProf)) {
                                    new VerificaReserva().notificarRetencionEmailProf(libro);
                                }
                            }
                            
                            libro.setDisponibilidad("reservado");
                        } else {
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
            if (prestamos.get(i).getCodBarraLibro().equalsIgnoreCase(codBarras)
                    && prestamos.get(i).getDevuelto().equalsIgnoreCase("no")) {
                codPrestamo = prestamos.get(i).getCodPrestamoLibroProf();
                break;
            }
        }
        return codPrestamo;
    }

    /**
     * el método retorna la reserva asociada al libro del estudiante o profesor.
     *
     * @param codBarras
     * @return reserva
     */
    public Object verificarReserva(String codBarras) {
        ReservaRecursoDAOAbs reservaDAO = new ReservaColgenDAOEst();
        Object reserva = reservaDAO.readDAO(codBarras);

        if (reserva == null) {
            reservaDAO = new ReservaColgenDAOProf();
            reserva = reservaDAO.readDAO(codBarras);
        }

        return reserva;
    }
    
    public void notificarDevolucion(String idProfesor, String tituloRecurso, java.util.Date fechaDevolucion) {

    }
}

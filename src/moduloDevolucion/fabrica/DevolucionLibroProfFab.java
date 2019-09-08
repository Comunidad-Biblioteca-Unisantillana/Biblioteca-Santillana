package moduloDevolucion.fabrica;

import controller.exceptions.NonexistentEntityException;
import general.modelo.NotificacionEmail;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import moduloReserva.DAO.ReservaColgenDAOEst;
import moduloReserva.DAO.ReservaRecursoDAOAbs;
import moduloReserva.entitys.ReservaColgenEstudiante;
import moduloReserva.modelo.VerificaReserva;
import usuarios.control.ProfesorJpaController;
import usuarios.entitys.Profesor;

/**
 * @author Julian
 * @creado: 04/08/2019
 * @author Miguel Fernández
 * @modificado: 08/09/2019
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
                        DevolucionLibroProf devProf = new DevolucionLibroProf(prestProf.getCodPrestamoLibroProf(),
                                idBibliotecario, estadoRecurso);
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
                        
                        notificarDevolucion(prestProf.getIdProfesor(), libro, codPrestamo);
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
    
    /**
     * el método realiza concatenación de los datos necesarios para la
     * construcción del e-mail al profesor, notificandole de la devoluciòn del
     * libro.
     *
     * @param idProfesor
     * @param libro
     * @param codPrestamo
     */
    public void notificarDevolucion(String idProfesor, Libro libro, int codPrestamo) {
        ProfesorJpaController profesorJpaController = new ProfesorJpaController();
        Profesor profesor = profesorJpaController.findProfesor(idProfesor);

        DevolucionLibroDAOProf devDAOProf = new DevolucionLibroDAOProf();
        DevolucionLibroProf devProf = devDAOProf.readDAO(devDAOProf.readCodigoDAO(codPrestamo));

        DateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");

        String datos = profesor.getApellidos().toUpperCase() + ";"
                + profesor.getNombres().toUpperCase() + ";"
                + "Identificaciòn: " + idProfesor + ";"
                + libro.getTitulo() + ";"
                + libro.getCodbarralibro() + ";"
                + formatoFecha.format((Date) devProf.getFechaDevolucion()) + ";"
                + "null;"
                + "null;"
                + profesor.getCorreoelectronico();

        NotificacionEmail em = new NotificacionEmail();
        em.gestionarNotificacion(datos, "mensajeDevolucion");
    }
    
}

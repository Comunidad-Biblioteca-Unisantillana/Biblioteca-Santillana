package moduloPrestamo.fabrica;

import general.modelo.NotificacionEmail;
import moduloPrestamo.modelo.IPrestamo;
import moduloPrestamo.entitys.PrestamoLibroProf;
import recursos.controllers.LibroJpaController;
import recursos.entitys.Libro;
import java.sql.Date;
import moduloPrestamo.DAO.PrestamoLibroDAOProf;
import general.vista.AlertBox;
import general.vista.IAlertBox;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import moduloReserva.DAO.ReservaColgenDAOProf;
import moduloReserva.entitys.ReservaColgenProfesor;
import moduloReserva.modelo.VerificaReserva;
import usuarios.control.ProfesorJpaController;
import usuarios.entitys.Profesor;

/**
 * la clase se encarga gestionar el préstamo del libro al profesor.
 *
 * @author Julian
 * @creado:
 * @author Miguel Fernández
 * @modificado: 07/09/2019
 */
public class PrestamoLibroProfFab implements IPrestamo {

    /**
     * constuctor de la clase sin parámetros.
     */
    public PrestamoLibroProfFab() {

    }

    /**
     * el método realiza el préstamo del libro al profesor.
     *
     * @param codBarras
     * @param codUsuario
     * @param idBibliotecario
     * @return estadoPrestamo
     */
    @Override
    public boolean ejecutarPrestamo(String codBarras, String codUsuario, String idBibliotecario) {
        IAlertBox alert = new AlertBox();
        boolean estadoPrestamo = false;

        try {
            LibroJpaController control = new LibroJpaController();
            Libro libro = control.findLibro(codBarras);

            if (libro != null) {
                if (libro.getDisponibilidad().equalsIgnoreCase("disponible")) {  
                    int diasPrestamo = libro.getCodcategoriacoleccion().getNombrecol().equalsIgnoreCase("general") ? 15 : 2;// falta organizarlo

                    PrestamoLibroProf prestLibProf = new PrestamoLibroProf();
                    prestLibProf.setCodBarraLibro(codBarras);
                    prestLibProf.setIdProfesor(codUsuario);
                    prestLibProf.setIdBibliotecario(idBibliotecario);
                    prestLibProf.setNumRenovaciones(0);

                    PrestamoLibroDAOProf prestLibDAOProf = new PrestamoLibroDAOProf(diasPrestamo);
        
                    if (prestLibDAOProf.createDAO(prestLibProf)) {
                        libro.setDisponibilidad("prestado");
                        control.edit(libro);
                        notificarPrestamoEmail(codUsuario, libro);
                        estadoPrestamo = true;
                    }
                } else if (libro.getDisponibilidad().equalsIgnoreCase("reservado")) {
                    ReservaColgenDAOProf reservaColgenDAOProf = new ReservaColgenDAOProf();
                    ReservaColgenProfesor reserva = reservaColgenDAOProf.readDAO(codBarras);

                    if (reserva.getIdProfesor().equals(codUsuario)) {
                        PrestamoLibroProf prestLibProf = new PrestamoLibroProf();
                        prestLibProf.setCodBarraLibro(codBarras);
                        prestLibProf.setIdProfesor(codUsuario);
                        prestLibProf.setIdBibliotecario(idBibliotecario);
                        prestLibProf.setNumRenovaciones(0);

                        PrestamoLibroDAOProf prestLibDAOProf = new PrestamoLibroDAOProf(15);

                        if (prestLibDAOProf.createDAO(prestLibProf)) {
                            libro.setDisponibilidad("prestado");
                            control.edit(libro);
                            VerificaReserva verificaReserva = new VerificaReserva();

                            if (!verificaReserva.liberarReservaAPrestamoProf(codUsuario, codBarras)) {
                                System.out.println("Error al eliminar la reserva del libro del profesor, después de generar el préstamo.");
                            }

                            notificarPrestamoEmail(codUsuario, libro);
                            estadoPrestamo = true;
                        }
                    } else {
                        alert.showAlert("Anuncio", "Libro reservado", "El libro con el código: " + codBarras
                                + " , se encuentra reservado por otro usuario.");
                    }
                } else if (libro.getDisponibilidad().equalsIgnoreCase("prestado")) {
                    alert.showAlert("Anuncio", "Préstamo libro", "El libro: " + codBarras
                            + ", se encuentra préstado a otro usuario.");
                } else if (libro.getDisponibilidad().equalsIgnoreCase("vencido")) {
                    alert.showAlert("Anuncio", "Préstamo libro", "El libro: " + codBarras
                            + ", no ha sido devuelto por el usuario al que se le presto.");
                }
            } else {
                alert.showAlert("Anuncio", "Préstamo libro", "No se encuentró un libro "
                        + "asociado al código: " + codBarras + ".");
            }
        } catch (Exception e) {
            System.out.println("Error al generar el préstamo del libro a un profesor");
        }

        return estadoPrestamo;
    }

    /**
     * el método realiza concatenación de los datos necesarios para la
     * construcción del e-mail al profesor, notificandole del préstamo del
     * libro.
     *
     * @param idProfesor
     * @param libro
     */
    private void notificarPrestamoEmail(String idProfesor, Libro libro) {
        ProfesorJpaController profesorJpaController = new ProfesorJpaController();
        Profesor profesor = profesorJpaController.findProfesor(idProfesor);

        PrestamoLibroDAOProf prestLibroDAOProf = new PrestamoLibroDAOProf();
        PrestamoLibroProf prestLibroProf = prestLibroDAOProf.readDAO(prestLibroDAOProf.readCodigoDAO(libro.getCodbarralibro()));

        DateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");

        String datos = profesor.getApellidos().toUpperCase() + ";"
                + profesor.getNombres().toUpperCase() + ";"
                + "Identificación: " + idProfesor + ";"
                + libro.getTitulo() + ";"
                + libro.getCodbarralibro() + ";"
                + formatoFecha.format((Date) prestLibroProf.getFechaPrestamo()) + ";"
                + formatoFecha.format((Date) prestLibroProf.getFechaDevolucion()) + ";"
                + libro.getCodcategoriacoleccion().getNombrecol() + ";"
                + profesor.getCorreoelectronico();

        NotificacionEmail notificacionEmail = new NotificacionEmail(datos, "mensajePrestamo");
        notificacionEmail.start();
    }

}

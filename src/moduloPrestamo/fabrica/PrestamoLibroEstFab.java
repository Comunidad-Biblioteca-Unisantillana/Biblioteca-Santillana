package moduloPrestamo.fabrica;

import general.modelo.NotificacionEmail;
import moduloPrestamo.modelo.IPrestamo;
import moduloPrestamo.entitys.PrestamoLibroEst;
import recursos.controllers.LibroJpaController;
import recursos.entitys.Libro;
import moduloPrestamo.DAO.PrestamoLibroDAOEst;
import general.vista.AlertBox;
import general.vista.IAlertBox;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import moduloReserva.DAO.ReservaColgenDAOEst;
import moduloReserva.entitys.ReservaColgenEstudiante;
import moduloReserva.modelo.VerificaReserva;
import usuarios.control.EstudianteJpaController;
import usuarios.entitys.Estudiante;

/**
 * la clase se encarga gestionar el préstamo del libro al estudiante.
 *
 * @author Julian
 * @creado
 * @author Miguel Fernández
 * @modificado 07/09/2019
 */
public class PrestamoLibroEstFab implements IPrestamo {

    /**
     * constructor de la clase sin parámetros.
     */
    public PrestamoLibroEstFab() {

    }

    /**
     * el método realiza el préstamo del libro al estudiante.
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

                    PrestamoLibroEst prestLibEst = new PrestamoLibroEst();
                    prestLibEst.setCodBarraLibro(codBarras);
                    prestLibEst.setCodEstudiante(codUsuario);
                    prestLibEst.setIdBibliotecario(idBibliotecario);
                    prestLibEst.setNumRenovaciones(0);

                    PrestamoLibroDAOEst prestLibDAOEst = new PrestamoLibroDAOEst(diasPrestamo);

                    if (prestLibDAOEst.createDAO(prestLibEst)) {
                        libro.setDisponibilidad("prestado");
                        control.edit(libro);
                        notificarPrestamoEmail(codUsuario, libro);
                        estadoPrestamo = true;
                    }
                } else if (libro.getDisponibilidad().equalsIgnoreCase("reservado")) {
                    ReservaColgenDAOEst reservaColgenDAOEst = new ReservaColgenDAOEst();
                    ReservaColgenEstudiante reserva = reservaColgenDAOEst.readDAO(codBarras);

                    if (reserva.getCodEstudiante().equalsIgnoreCase(codUsuario)) {
                        PrestamoLibroEst prestLibEst = new PrestamoLibroEst();
                        prestLibEst.setCodBarraLibro(codBarras);
                        prestLibEst.setCodEstudiante(codUsuario);
                        prestLibEst.setIdBibliotecario(idBibliotecario);
                        prestLibEst.setNumRenovaciones(0);

                        PrestamoLibroDAOEst prestLibDAOEst = new PrestamoLibroDAOEst(15);

                        if (prestLibDAOEst.createDAO(prestLibEst)) {
                            libro.setDisponibilidad("prestado");
                            control.edit(libro);
                            VerificaReserva verificaReserva = new VerificaReserva();

                            if (!verificaReserva.liberarReservaAPrestamoEst(codUsuario, codBarras)) {
                                System.out.println("Error al eliminar la reserva del libro del estudiante, después de generar el préstamo.");
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
            System.out.println("Error al generar el préstamo del libro a un estudiante");
        }

        return estadoPrestamo;
    }

    /**
     * el método realiza concatenación de los datos necesarios para la
     * construcción del e-mail al estudiante, notificandole del préstamo de la
     * libro.
     *
     * @param codEstudiante
     * @param libro
     */
    private void notificarPrestamoEmail(String codEstudiante, Libro libro) {
        EstudianteJpaController estudianteJpaController = new EstudianteJpaController();
        Estudiante estudiante = estudianteJpaController.findEstudiante(codEstudiante);

        PrestamoLibroDAOEst prestLibroDAOEst = new PrestamoLibroDAOEst();
        PrestamoLibroEst prestLibroEst = prestLibroDAOEst.readDAO(prestLibroDAOEst.readCodigoDAO(libro.getCodbarralibro()));

        DateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");

        String datos = estudiante.getApellido().toUpperCase() + ";"
                + estudiante.getNombre().toUpperCase() + ";"
                + "Código: " + codEstudiante + ";"
                + libro.getTitulo() + ";"
                + libro.getCodbarralibro() + ";"
                + formatoFecha.format((Date) prestLibroEst.getFechaPrestamo()) + ";"
                + formatoFecha.format((Date) prestLibroEst.getFechaDevolucion()) + ";"
                + libro.getCodcategoriacoleccion().getNombrecol() + ";"
                + estudiante.getCorreoelectronico();

        NotificacionEmail em = new NotificacionEmail();
        em.gestionarNotificacion(datos, "mensajePrestamo");
    }

}

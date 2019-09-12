package moduloRenovacion.fabrica;

import general.modelo.NotificacionEmail;
import moduloPrestamo.DAO.PrestamoLibroDAOEst;
import moduloPrestamo.entitys.PrestamoLibroEst;
import moduloRenovacion.modelo.IRenovacion;
import recursos.controllers.LibroJpaController;
import recursos.entitys.Libro;
import general.vista.AlertBox;
import general.vista.IAlertBox;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import moduloReserva.modelo.VerificaReserva;
import usuarios.control.EstudianteJpaController;
import usuarios.entitys.Estudiante;

/**
 * @author Miguel Fernández
 * @creado 24/08/2019
 * @author Miguel Fernández
 * @modificado 08/09/2019
 */
public class RenovacionColgenEstFab implements IRenovacion {

    private IAlertBox alert;

    /**
     * constructor de la clase sin parámetros.
     */
    public RenovacionColgenEstFab() {
        alert = new AlertBox();
    }

    /**
     * el método se encarga de verificar si existe una reserva del recurso a
     * renovar.
     *
     * @param codBarras
     * @return boolean
     */
    private boolean consultarReservas(String codBarras) {
        VerificaReserva verificaReserva = new VerificaReserva();

        if (!verificaReserva.verificarReservaEst(codBarras)) {
            if (!verificaReserva.verificarReservaProf(codBarras)) {
                return false;
            }
        }

        return true;
    }

    /**
     * el método se encarga de realizar la renovaión del recurso solicitado por
     * el estudiante.
     *
     * @param codBarras
     * @param idUsuario
     * @return boolean
     */
    @Override
    public boolean ejecutarRenovacion(String codBarras, String idUsuario) {
        LibroJpaController libroJpaController = new LibroJpaController();
        Libro libro = libroJpaController.findLibro(codBarras);

        if (verificarCondicionesRecurso(libro, codBarras)) {
            if (!consultarReservas(codBarras)) {
                PrestamoLibroDAOEst prestamoLibroDAOEst = new PrestamoLibroDAOEst(15);
                PrestamoLibroEst prestamoLibroEst = prestamoLibroDAOEst.readDAO(prestamoLibroDAOEst.readCodigoDAO(codBarras));

                if (prestamoLibroEst != null) {
                    if (prestamoLibroEst.getCodEstudiante().equals(idUsuario)) {
                        if (prestamoLibroEst.getNumRenovaciones() < libro.getCodcategoriacoleccion().getCantmaxrenovacionesest()) {
                            prestamoLibroEst.setNumRenovaciones(prestamoLibroEst.getNumRenovaciones() + 1);

                            if (prestamoLibroDAOEst.updateDAO(prestamoLibroEst)) {
                                notificarPrestamoEmail(idUsuario, libro);

                                alert.showAlert("Anuncio", "Renovación exitosa", "La renovación del libro: " + codBarras
                                        + ", se realizó con exito");

                                return true;
                            } else {
                                alert.showAlert("Anuncio", "Renovación fallida", "La renovación del libro: " + codBarras
                                        + ", no se pudo realizar");
                            }
                        } else {
                            alert.showAlert("Anuncio", "Limite máximo de renovación", "El estudiante: " + idUsuario
                                    + ", ya llegó al limite de máximo(tres) de renovaciones del libro: " + codBarras
                                    + ".\n\nRecuerde devolver el recurso en la fecha establecida para evitar sanciones.");
                        }
                    } else {
                        alert.showAlert("Anuncio", "El usuario no coincide", "El estudiante: " + idUsuario
                                + " que esta intentando renovar el libro, no corresponde al usuario: "
                                + prestamoLibroEst.getCodEstudiante()+ " que solicito el préstamo. Por favor verifique "
                                + "el código del libro a renovar o el código del estudiante.");
                    }
                } else {
                    alert.showAlert("Anuncio", "Préstamo no encontrado", "No se encontró un préstamo actual "
                            + "asociado al libro con el código: " + codBarras + ".");
                }
            } else {
                alert.showAlert("Anuncio", "Libro reservado", "El libro con el código: " + codBarras
                        + " , que desea renovar, se encuentra reservado por otro usuario."
                        + "\n\nRecuerde devolver el recurso en la fecha establecida para evitar sanciones.");
            }
        }

        return false;
    }

    /**
     * el método realiza la concatenación de los datos necesarios para la
     * construcción del e-mail al estudiante, notificandole de la renovación del
     * libro.
     *
     * @param codEstudiante
     * @param libro
     */
    private void notificarPrestamoEmail(String codEstudiante, Libro libro) {
        EstudianteJpaController estudianteJpaController = new EstudianteJpaController();
        Estudiante estudiante = estudianteJpaController.findEstudiante(codEstudiante);

        PrestamoLibroDAOEst prestamoLibroDAOEst = new PrestamoLibroDAOEst();
        PrestamoLibroEst prestamoLibroEst = prestamoLibroDAOEst.readDAO(prestamoLibroDAOEst.readCodigoDAO(libro.getCodbarralibro()));

        DateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");

        String datos = estudiante.getApellido().toUpperCase() + ";"
                + estudiante.getNombre().toUpperCase() + ";"
                + "Código: " + codEstudiante + ";"
                + libro.getTitulo() + ";"
                + libro.getCodbarralibro() + ";"
                + formatoFecha.format(new Date().getTime()) + ";"
                + formatoFecha.format((Date) prestamoLibroEst.getFechaDevolucion()) + ";"
                + libro.getCodcategoriacoleccion().getNombrecol() + ";"
                + estudiante.getCorreoelectronico();

        NotificacionEmail em = new NotificacionEmail();
        em.gestionarNotificacion(datos, "mensajeRenovacion");
    }

    /**
     * el método verifica ciertas rrestricciones sobre el libro a renovar.
     *
     * @param libro
     * @param codBarras
     * @return boolean
     */
    private boolean verificarCondicionesRecurso(Libro libro, String codBarras) {
        if (libro != null) {
            if (libro.getCodcategoriacoleccion().getNombrecol().equalsIgnoreCase("general")) {
                return true;
            } else {
                alert.showAlert("Anuncio", "Renovación", "El libro debe pertencer a la colección "
                        + "general para realizar la renovación.");
            }
        } else {
            alert.showAlert("Anuncio", "Renovación", "No se encontró un libro asociado al código: " + codBarras
                    + ".\n\nRecurde que solo se puden renovar libros de la colección general.");
        }

        return false;
    }

}

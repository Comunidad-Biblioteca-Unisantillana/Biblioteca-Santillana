package moduloRenovacion.fabrica;

import general.modelo.NotificacionEmail;
import moduloPrestamo.DAO.PrestamoLibroDAOProf;
import moduloPrestamo.entitys.PrestamoLibroProf;
import moduloRenovacion.modelo.IRenovacion;
import recursos.controllers.LibroJpaController;
import recursos.entitys.Libro;
import general.vista.AlertBox;
import general.vista.IAlertBox;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import moduloReserva.modelo.VerificaReserva;
import usuarios.control.ProfesorJpaController;
import usuarios.entitys.Profesor;

/**
 * @author Miguel Fernández
 * @creado 24/08/2019
 * @author Miguel Fernández
 * @modificado 08/09/2019
 */
public class RenovacionColgenProfFab implements IRenovacion {

    private IAlertBox alert;

    /**
     * constructor de la clase sin parámetros.
     */
    public RenovacionColgenProfFab() {
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
     * el profesor.
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
                PrestamoLibroDAOProf prestamoLibroDAOProf = new PrestamoLibroDAOProf(15);
                PrestamoLibroProf prestamoLibroProf = prestamoLibroDAOProf.readDAO(prestamoLibroDAOProf.readCodigoDAO(codBarras));

                if (prestamoLibroProf != null) {
                    if (prestamoLibroProf.getIdProfesor().equals(idUsuario)) {
                        if (prestamoLibroProf.getNumRenovaciones() < libro.getCodcategoriacoleccion().getCantmaxrenovacionesest()) {
                            prestamoLibroProf.setNumRenovaciones(prestamoLibroProf.getNumRenovaciones() + 1);

                            if (prestamoLibroDAOProf.updateDAO(prestamoLibroProf)) {
                                notificarPrestamoEmail(idUsuario, libro);

                                alert.showAlert("Anuncio", "Renovación exitosa", "La renovación del libro: " + codBarras
                                        + ", se realizó con éxito");

                                return true;
                            } else {
                                alert.showAlert("Anuncio", "Renovación fallida", "La renovación del libro: " + codBarras
                                        + ", no se pudo realizar");
                            }
                        } else {
                            alert.showAlert("Anuncio", "Limite máximo de renovación", "El profesor: " + idUsuario
                                    + ", ya llegó al limite de máximo(tres) de renovaciones del libro: " + codBarras
                                    + ".\n\nRecuerde devolver el recurso en la fecha establecida para evitar sanciones.");
                        }
                    } else {
                        alert.showAlert("Anuncio", "El usuario no coincide", "El profesor: " + idUsuario
                                + " que esta intentando renovar el libro, no corresponde al usuario: "
                                + prestamoLibroProf.getIdProfesor() + " que solicito el préstamo. Por favor verifique "
                                + "el código del libro a renovar o la identificación del profesor.");
                    }
                } else {
                    alert.showAlert("Anuncio", "Préstamo no encontrado", "No se encontró un préstamo actual "
                            + "asociado al libro, con el código: " + codBarras + ".");
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
     * construcción del e-mail al profesor, notificandole de la renovación del
     * libro.
     *
     * @param idProfesor
     * @param libro
     */
    private void notificarPrestamoEmail(String idProfesor, Libro libro) {
        ProfesorJpaController profesorJpaController = new ProfesorJpaController();
        Profesor profesor = profesorJpaController.findProfesor(idProfesor);

        PrestamoLibroDAOProf prestamoLibroDAOProf = new PrestamoLibroDAOProf();
        PrestamoLibroProf prestamoLibroProf = prestamoLibroDAOProf.readDAO(prestamoLibroDAOProf.readCodigoDAO(libro.getCodbarralibro()));

        DateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");

        String datos = profesor.getApellidos().toUpperCase() + ";"
                + profesor.getNombres().toUpperCase() + ";"
                + "Identificación: " + idProfesor + ";"
                + libro.getTitulo() + ";"
                + libro.getCodbarralibro() + ";"
                + formatoFecha.format(new Date().getTime()) + ";"
                + formatoFecha.format((Date) prestamoLibroProf.getFechaDevolucion()) + ";"
                + libro.getCodcategoriacoleccion().getNombrecol() + ";"
                + profesor.getCorreoelectronico();

        NotificacionEmail notificacionEmail = new NotificacionEmail(datos, "mensajeRenovacion");
        notificacionEmail.start();
    }

    /**
     * el método vrifica ciertas restricciones sobre el libro a renovar.
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

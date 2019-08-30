package moduloRenovacion.fabrica;

import java.sql.Date;
import moduloPrestamo.DAO.PrestamoLibroDAOProf;
import moduloPrestamo.entitys.PrestamoLibroProf;
import moduloRenovacion.modelo.IRenovacion;
import recursos.controllers.LibroJpaController;
import recursos.entitys.Libro;
import general.vista.AlertBox;
import general.vista.IAlertBox;
import moduloReserva.modelo.VerificaReserva;

/**
 * @author Miguel Fernández
 * @creado 24/08/2019
 * @author Miguel Fernández
 * @modificado 25/08/2019
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
     * el metódo se encarga de verificar si existe una reserva del recurso a
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
     * el metódo se encarga de realizar la renovaión del recurso solicitado por
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
                    if (prestamoLibroProf.getNumRenovaciones() < libro.getCodcategoriacoleccion().getCantmaxrenovacionesest()) {
                        prestamoLibroProf.setNumRenovaciones(prestamoLibroProf.getNumRenovaciones() + 1);

                        if (prestamoLibroDAOProf.updateDAO(prestamoLibroProf)) {
                            //espacio para el envio del correo

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
     * el metódo realiza la construcción del correo, para notificar al profesor
     * de que resucro se renovó.
     *
     * @param codEstudiante
     * @param codBarras
     * @param tituloRecurso
     * @param fechaRenovacion
     * @param fechaDevolucion
     */
    private void notificarRenovacion(String idProfesor, String codBarras, String tituloRecurso, Date fechaRenovacion, Date fechaDevolucion) {

    }

    /**
     * el metódo vrifica ciertas restricciones sobre el libro a renovar.
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

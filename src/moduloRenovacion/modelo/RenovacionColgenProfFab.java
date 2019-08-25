package moduloRenovacion.modelo;

import java.sql.Date;
import moduloPrestamo.DAO.PrestamoLibroDAOProf;
import moduloPrestamo.entitys.PrestamoLibroProf;
import recursos1.controllers.LibroJpaController;
import recursos1.entitys.Libro;
import vista.AlertBox;
import vista.IAlertBox;

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
        return false;
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
        //espacio para verificar reserva

        LibroJpaController libroJpaController = new LibroJpaController();
        Libro libro = libroJpaController.findLibro(codBarras);

        if (verificarCondicionesRecurso(libro, codBarras)) {
            PrestamoLibroDAOProf prestamoLibroDAOProf = new PrestamoLibroDAOProf(15);
            PrestamoLibroProf prestamoLibroProf = prestamoLibroDAOProf.readDAO(prestamoLibroDAOProf.readCodigoDAO(codBarras));

            if (prestamoLibroProf != null) {
                if (prestamoLibroProf.getNumRenovaciones() < libro.getCodcategoriacoleccion().getCantmaxrenovacionesest()) {
                    prestamoLibroProf.setNumRenovaciones(prestamoLibroProf.getNumRenovaciones() + 1);
                    prestamoLibroDAOProf.updateDAO(prestamoLibroProf);

                    //espacio para el envio del correo
                    return true;
                } else {
                    alert.showAlert("Anuncio", "Renovación", "El profesor: " + idUsuario
                            + ", ya llegó al limite de máximo de tres renovaciones del libro: " + codBarras);
                }
            } else {
                alert.showAlert("Anuncio", "Renovación", "No se encontró un un préstamo actual "
                        + "del libro asociado al código: " + codBarras);
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
     * el matódo vrifica ciertas restricciones sobre el libro a renovar.
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

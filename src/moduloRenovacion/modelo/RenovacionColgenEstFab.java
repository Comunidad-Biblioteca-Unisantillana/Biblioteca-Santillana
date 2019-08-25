package moduloRenovacion.modelo;

import java.sql.Date;
import moduloPrestamo.DAO.PrestamoLibroDAOEst;
import moduloPrestamo.entitys.PrestamoLibroEst;
import recursos1.controllers.LibroJpaController;
import recursos1.entitys.Libro;
import vista.AlertBox;
import vista.IAlertBox;

/**
 * @author Miguel Fernández
 * @creado 24/08/2019
 * @author Miguel Fernández
 * @modificado 24/08/2019
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
     * el estudiante.
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
            PrestamoLibroDAOEst prestamoLibroDAOEst = new PrestamoLibroDAOEst(15);
            PrestamoLibroEst prestamoLibroEst = prestamoLibroDAOEst.readDAO(prestamoLibroDAOEst.readCodigoDAO(codBarras));

            if (prestamoLibroEst != null) {
                if (prestamoLibroEst.getNumRenovaciones() < libro.getCodcategoriacoleccion().getCantmaxrenovacionesest()) {
                    prestamoLibroEst.setNumRenovaciones(prestamoLibroEst.getNumRenovaciones() + 1);

                    if (prestamoLibroDAOEst.updateDAO(prestamoLibroEst)) {
                        //espacio para el envio del correo
                        
                        return true;
                    }
                } else {
                    alert.showAlert("Anuncio", "Renovación", "El estudiante: " + idUsuario
                            + ", ya llegó al limite de máximo(tres) de renovaciones del libro: " + codBarras
                            + "\n\nRecuerde devolver el recurso en la fecha establecida para evitar sanciones");
                }
            } else {
                alert.showAlert("Anuncio", "Renovación", "No se encontró un préstamo actual "
                        + "asociado al libro con el código: " + codBarras);
            }
        }

        return false;
    }

    /**
     * el metódo realiza la construcción del correo, para notificar al
     * estudiante de que resucro se renovó.
     *
     * @param codEstudiante
     * @param codBarras
     * @param tituloRecurso
     * @param fechaRenovacion
     * @param fechaDevolucion
     */
    private void notificarRenovacion(String codEstudiante, String codBarras, String tituloRecurso, Date fechaRenovacion, Date fechaDevolucion) {

    }

    /**
     * el metódo verifica ciertas rrestricciones sobre el libro a renovar.
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

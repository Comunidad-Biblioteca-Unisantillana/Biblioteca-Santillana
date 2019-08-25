package ModuloRenovacion;

import java.sql.Date;
import recursos1.entitys.Libro;

/**
 * @author Miguel Fernández
 * @creado 24/08/2019
 * @author Miguel Fernández
 * @modificado 24/08/2019
 */
public class RenovacionColgenEstFab implements IRenovacion {

    /**
     * constructor de la clase sin parámetros.
     */
    public RenovacionColgenEstFab() {

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
     * el matódo vrifica ciertas rrestricciones sobre el libro a renovar.
     *
     * @param libro
     * @return boolean
     */
    private boolean verificarCondicionesRecurso(Libro libro) {
        return false;
    }

}

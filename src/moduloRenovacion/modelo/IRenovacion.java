package moduloRenovacion.modelo;

/**
 * @author Miguel Fernández
 * @creado 24/08/2019
 * @author Miguel Fernández
 * @modificado 24/08/2019
 */
public interface IRenovacion {

    /**
     * se encarga de realizar la renovaión del recurso solicitado por el usuario.
     *
     * @param codBarras
     * @param idUsuario
     * @return boolean
     */
    public boolean ejecutarRenovacion(String codBarras, String idUsuario);

}

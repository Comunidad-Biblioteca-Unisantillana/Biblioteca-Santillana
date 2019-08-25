package moduloPrestamo.fabrica;

/**
 *  
 * @author Julian
 * @creado
 * @author
 * @modificado
 */
public interface IPrestamo {

    /**
     * el metódo se encarga de realizar el préstamo del recurso solicitado por
     * el usuario.
     *
     * @param codBarras
     * @param codUsuario
     * @param idBibliotecario
     * @return prestamo
     */
    public boolean ejecutarPrestamo(String codBarras, String codUsuario, String idBibliotecario);

}

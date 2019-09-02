package moduloMulta.modelo;

/**
 * la interface tiene el prototipo de los métodos, para verificar las multas de
 * un usuario en un recurso especifico.
 *
 * @author Miguel Fernández
 * @creado: 30/08/2019
 * @author Miguel Fernández
 * @modificado: 31/08/2019
 */
public interface IVerificaMulta {

    /**
     * prototipo del método que verifica, si un usuario tiene una multa asociada
     * al recurso libro.
     *
     * @param idUsuario
     * @return boolean
     *
     */
    public boolean buscarMultaLibro(String idUsuario);

    /**
     * prototipo del método que verifica, si un usuario tiene una multa asociada
     * al recurso enciclopedia.
     *
     * @param idUsuario
     * @return boolean
     */
    public boolean buscarMultaEnciclopedia(String idUsuario);

    /**
     * prototipo del método que verifica, si un usuario tiene una multa asociada
     * al recurso diccionario.
     *
     * @param idUsuario
     * @return boolean
     */
    public boolean buscarMultaDiccionario(String idUsuario);

    /**
     * prototipo del método que verifica, si un usuario tiene una multa asociada
     * al recurso revista.
     *
     * @param idUsuario
     * @return boolean
     */
    public boolean buscarMultaRevista(String idUsuario);

    /**
     * prototipo del método que verifica, si un usuario tiene una multa asociada
     * al recurso periódico.
     *
     * @param idUsuario
     * @return boolean
     */
    public boolean buscarMultaPeriodico(String idUsuario);

    /**
     * prototipo del método que verifica, si un usuario tiene una multa asociada
     * al recurso mapa.
     *
     * @param idUsuario
     * @return boolean
     */
    public boolean buscarMultaMapa(String idUsuario);

    /**
     * prototipo del método que verifica, si el usuario tiene una multa en algún
     * recurso.
     *
     * @param idUsuario
     * @return boolean
     */
    public boolean verificarMultaUsuario(String idUsuario);

}

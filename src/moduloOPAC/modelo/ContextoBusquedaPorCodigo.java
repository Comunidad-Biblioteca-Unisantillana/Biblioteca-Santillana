package moduloOPAC.modelo;

import recursos1.entitys.Diccionario;
import recursos1.entitys.Enciclopedia;
import recursos1.entitys.Libro;
import recursos1.entitys.Mapa;
import recursos1.entitys.Periodico;
import recursos1.entitys.Revista;

/**
 *
 * @author Miguel Fernández
 * @creado 17/08/2019
 * @author Miguel Fernández
 * @modificado 18/08/2019
 */
public class ContextoBusquedaPorCodigo {

    private BusquedaCodigoAbs tipoBusquedaCodigo;

    /**
     * constructor vacio.
     */
    public ContextoBusquedaPorCodigo() {

    }

    /**
     * el metódo delega la busqueda al metódo de la clase que se encarga de
     * realizarlo, retornando el libro asociado al codigo de barras o isbn
     * ingresado.
     *
     * @param codigo
     * @return libro
     */
    public Libro buscarLibroPorCodigo(String codigo) {
        return tipoBusquedaCodigo.buscarLibroPorCodigo(codigo); // -> libro
    }

    /**
     * el metódo delega la busqueda al metódo de la clase que se encarga de
     * realizarlo, retornando el libro asociado al codigo de barras o isbn
     * ingresado.
     *
     * @param codigo
     * @return enciclopedia
     */
    public Enciclopedia buscarEnciclopediaPorCodigo(String codigo) {
        return tipoBusquedaCodigo.buscarEnciclopediaPorCodigo(codigo); // -> enciclopedia
    }

    /**
     * el metódo delega la busqueda al metódo de la clase que se encarga de
     * realizarlo, retornando el libro asociado al codigo de barras o isbn
     * ingresado.
     *
     * @param codigo
     * @return diccionario
     */
    public Diccionario buscarDiccionarioPorCodigo(String codigo) {
        return tipoBusquedaCodigo.buscarDiccionarioPorCodigo(codigo); // -> diccionario 
    }

    /**
     * el metódo delega la busqueda al metódo de la clase que se encarga de
     * realizarlo, retornando el libro asociado al codigo de barras o issn
     * ingresado.
     *
     * @param codigo
     * @return revista
     */
    public Revista buscarRevistaPorCodigo(String codigo) {
        return tipoBusquedaCodigo.buscarRevistaPorCodigo(codigo); // -> revista
    }

    /**
     * el metódo delega la busqueda al metódo de la clase que se encarga de
     * realizarlo, retornando el libro asociado al codigo de barras o issn
     * ingresado.
     *
     * @param codigo
     * @return periodico
     */
    public Periodico buscarPeriodicoPorCodigo(String codigo) {
        return tipoBusquedaCodigo.buscarPeriodicoPorCodigo(codigo); // -> periodico
    }

    /**
     * el metódo delega la busqueda al metódo de la clase que se encarga de
     * realizarlo, retornando el libro asociado al codigo de barras o isbn
     * ingresado.
     *
     * @param codigo
     * @return mapa
     */
    public Mapa buscarMapaPorCodigo(String codigo) {
        return tipoBusquedaCodigo.buscarMapaPorCodigo(codigo); // -> mapa 
    }

    /**
     * el metódo recibe la clase que se encargará de la busqueda del recurso.
     *
     * @param tipoBusquedaCodigo
     */
    public void setBusquedaPorCodigo(BusquedaCodigoAbs tipoBusquedaCodigo) {
        this.tipoBusquedaCodigo = tipoBusquedaCodigo;
    }

}

package moduloOPAC.modelo;

import recursos1.entitys.Diccionario;
import recursos1.entitys.Enciclopedia;
import recursos1.entitys.Libro;
import recursos1.entitys.Mapa;
import recursos1.entitys.Periodico;
import recursos1.entitys.Revista;
import modelo.ConnectionBD;

/**
 *
 * @author Miguel Fernández
 * @creado 17/08/2019
 * @author Miguel Fernández
 * @modificado 18/08/2019
 *//**
 * @author Miguel Fernández
 */
public abstract class BusquedaCodigoAbs {

    protected ConnectionBD connection;
    protected String[] entidades = {"libro", "enciclopedia", "diccionario", "mapa", "revista", "periodico"};

    /**
     * el metódo retorna el nombre de la entidad asociada a un codigo de barras,
     * isbn o issn y se desarrolla en las clases hijas.
     *
     * @param codigo
     * @return entidad
     */
    public abstract String buscarEntidad(String codigo);

    /**
     * el metódo retorna un libro asociado a un codigo de barras o isbn
     * ingresado y se desarrolla en las clases hijas.
     *
     * @param codigo
     * @return libro
     */
    public abstract Libro buscarLibroPorCodigo(String codigo);

    /**
     * el metódo retorna una enciclopedia asociado a un codigo de barras o isbn
     * ingresado y se desarrolla en las clases hijas.
     *
     * @param codigo
     * @return enciclopedia
     */
    public abstract Enciclopedia buscarEnciclopediaPorCodigo(String codigo);

    /**
     * el metódo retorna un diccionario asociado a un codigo de barras o isbn
     * ingresado y se desarrolla en las clases hijas.
     *
     * @param codigo
     * @return diccionario
     */
    public abstract Diccionario buscarDiccionarioPorCodigo(String codigo);

    /**
     * el metódo retorna una revista asociado a un codigo de barras o issn
     * ingresado y se desarrolla en las clases hijas.
     *
     * @param codigo
     * @return revista
     */
    public abstract Revista buscarRevistaPorCodigo(String codigo);

    /**
     * el metódo retorna un periodico asociado a un codigo de barras o issn
     * ingresado y se desarrolla en las clases hijas.
     *
     * @param codigo
     * @return periodico
     */
    public abstract Periodico buscarPeriodicoPorCodigo(String codigo);

    /**
     * el metódo retorna un mapa asociado a un codigo de barras o isbn
     * ingresado y se desarrolla en las clases hijas.
     *
     * @param codigo
     * @return mapa
     */
    public abstract Mapa buscarMapaPorCodigo(String codigo);

}

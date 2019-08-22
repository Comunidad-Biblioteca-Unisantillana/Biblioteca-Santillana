package moduloOPAC.modelo;

/**
 * @author Miguel Fernández
 * @creado 18/08/2019
 * @author Miguel Fernández
 * @modificado 19/08/2019
 */
public interface IBusquedaAutorRecurso {

    /**
     * el metódo busca los autores de un recurso, por medio del codigo de
     * barras y se desarrolla en las clases hijas.
     *
     * @param codBarras
     */
    public void buscarAutorRecurso(String codBarras);

    /**
     *
     * el metódo retorna una cadena con el nombre y apellido de los autores de
     * una entidad especifica y se desarrolla en las clases hijas.
     *
     * @return cadenaAutores
     */
    public String getCadenaAutores();

}

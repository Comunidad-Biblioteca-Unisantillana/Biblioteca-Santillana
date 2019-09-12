package moduloOPAC.modelo;

/**
 * @author Miguel Fernández
 * @creado 18/08/2019
 * @author Miguel Fernández
 * @modificado 19/08/2019
 */
public interface IBusquedaMateriaRecurso {

    /**
     * el metódo busca las materias de un recurso, por medio del codigo de
     * barras y se desarrolla en las clases hijas.
     *
     * @param codBarras
     */
    public void buscarMateriaRecurso(String codBarras);

    /**
     *
     * el metódo retorna una cadena con el nombrede las materias de
     * una entidad especifica y se desarrolla en las clases hijas.
     *
     * @return cadenaMaterias
     */
    public String getCadenaMaterias();

}

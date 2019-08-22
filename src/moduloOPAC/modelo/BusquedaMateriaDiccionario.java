package moduloOPAC.modelo;

import java.util.List;
import recursos1.controllers.MateriaPorDiccionarioJpaController;
import recursos1.entitys.Materia;

/**
 * @author Miguel Fernández
 * @creado 18/08/2019
 * @author Miguel Fernández
 * @modificado 19/08/2019
 */
public class BusquedaMateriaDiccionario implements IBusquedaMateriaRecurso {

    private String cadenaMaterias;

    /**
     * constructor sin parámetros.
     */
    public BusquedaMateriaDiccionario() {
        cadenaMaterias = "";
    }

    /**
     * el metódo busca las materias de un diccionario, por medio del codigo de
     * barras.
     *
     * @param codBarras
     */
    @Override
    public void buscarMateriaRecurso(String codBarras) {
        MateriaPorDiccionarioJpaController materiaPorDicJC = new MateriaPorDiccionarioJpaController();
        List<Materia> listMat = materiaPorDicJC.findMateriaPorDiccionarioCodBarras(codBarras);

        if (!listMat.isEmpty()) {
            listMat.forEach((mat) -> {
                cadenaMaterias += "• " + mat.getNombremateria() + "\n";
            });
        }
    }

    /**
     * el metódo retorna una cadena con el nombre de las materias de un
     * diccionario.
     *
     * @return cadenaMaterias
     */
    @Override
    public String getCadenaMaterias() {
        return cadenaMaterias.replaceAll("null", "");
    }

}

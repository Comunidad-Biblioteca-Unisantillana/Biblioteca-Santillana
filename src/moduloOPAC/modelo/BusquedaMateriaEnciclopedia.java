package moduloOPAC.modelo;

import java.util.List;
import recursos.controllers.MateriaPorEnciclopediaJpaController;
import recursos.entitys.Materia;

/**
 * @author Miguel Fernández
 * @creado 18/08/2019
 * @author Miguel Fernández
 * @modificado 19/08/2019
 */
public class BusquedaMateriaEnciclopedia implements IBusquedaMateriaRecurso {

    private String cadenaMaterias;

    /**
     * constructor sin parámetros.
     */
    public BusquedaMateriaEnciclopedia() {
        cadenaMaterias = "";
    }

    /**
     * el metódo busca las materias de una enciclopedia, por medio del codigo de
     * barras.
     *
     * @param codBarras
     */
    @Override
    public void buscarMateriaRecurso(String codBarras) {
        MateriaPorEnciclopediaJpaController materiaPorEncJC = new MateriaPorEnciclopediaJpaController();
        List<Materia> listMat = materiaPorEncJC.findMateriaPorEnciclopediaCodBarras(codBarras);
        
        if (!listMat.isEmpty()) {
            listMat.forEach((mat) -> {
                cadenaMaterias += "• " + mat.getNombremateria() + "\n";
            });
        }
    }

    /**
     * el metódo retorna una cadena con el nombre de las materias de una
     * enciclopedia.
     *
     * @return cadenaMaterias
     */
    @Override
    public String getCadenaMaterias() {
        return cadenaMaterias.replaceAll("null", "");
    }

}

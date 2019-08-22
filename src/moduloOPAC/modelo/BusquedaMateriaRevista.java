package moduloOPAC.modelo;

import java.util.List;
import recursos1.controllers.MateriaPorRevistaJpaController;
import recursos1.entitys.Materia;

/**
 * @author Miguel Fernández
 * @creado 18/08/2019
 * @author Miguel Fernández
 * @modificado 19/08/2019
 */
public class BusquedaMateriaRevista implements IBusquedaMateriaRecurso {

    private String cadenaMaterias;

    /**
     * constructor sin parámetros.
     */
    public BusquedaMateriaRevista() {

    }

    /**
     * el metódo busca las materias de una revista, por medio del codigo de
     * barras.
     *
     * @param codBarras
     */
    @Override
    public void buscarMateriaRecurso(String codBarras) {
        MateriaPorRevistaJpaController materiaPorRevJC = new MateriaPorRevistaJpaController();
        List<Materia> listMat = materiaPorRevJC.findMateriaPorRevistaCodBarras(codBarras);

        if (!listMat.isEmpty()) {
            listMat.forEach((mat) -> {
                cadenaMaterias += "• " + mat.getNombremateria() + "\n";
            });
        }
    }

    /**
     * el metódo retorna una cadena con el nombre de las materias de una
     * revista.
     *
     * @return cadenaMaterias
     */
    @Override
    public String getCadenaMaterias() {
        return cadenaMaterias.replaceAll("null", "");
    }

}

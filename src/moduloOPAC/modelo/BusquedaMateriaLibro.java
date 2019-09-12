package moduloOPAC.modelo;

import java.util.List;
import recursos.controllers.MateriaPorLibroJpaController;
import recursos.entitys.Materia;

/**
 * @author Miguel Fernández
 * @creado 18/08/2019
 * @author Miguel Fernández
 * @modificado 19/08/2019
 */
public class BusquedaMateriaLibro implements IBusquedaMateriaRecurso {

    private String cadenaMaterias;

    /**
     * constructor sin parámetros.
     */
    public BusquedaMateriaLibro() {
        cadenaMaterias = "";
    }

    /**
     * el metódo busca las materias de un libro, por medio del codigo de barras.
     *
     * @param codBarras
     */
    @Override
    public void buscarMateriaRecurso(String codBarras) {
        MateriaPorLibroJpaController materiaPorLibJC = new MateriaPorLibroJpaController();
        List<Materia> listMat = materiaPorLibJC.findMateriaPorLibroCodBarras(codBarras);
        
        if (!listMat.isEmpty()) {
            listMat.forEach((mat) -> {
                cadenaMaterias += "• " + mat.getNombremateria() + "\n";
            });
        }
    }

    /**
     * el metódo retorna una cadena con el nombre de las materias de un libro.
     *
     * @return cadenaMaterias
     */
    @Override
    public String getCadenaMaterias() {
        return cadenaMaterias.replaceAll("null", "");
    }

}

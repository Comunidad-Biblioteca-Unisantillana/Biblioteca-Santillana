package moduloMulta.fabrica;

import java.sql.Date;
import java.util.List;
import moduloDevolucion.entitys.DevolucionRevistaProf;
import moduloMulta.entitys.MultaRevistaProf;
import moduloMulta.modelo.IMulta;
import moduloPrestamo.entitys.PrestamoRevistaProf;

/**
 *
 * @author Miguel Fernández
 * @creado: 30/08/2019
 * @author
 * @modificado:
 */
public class MultaRevistaProfFab implements IMulta {

    public MultaRevistaProfFab() {

    }

    @Override
    public void actualizarDiasAtrasadosMulta() {

    }

    @Override
    public void generarMulta() {

    }

    private Date getFechaDevolucionPrestamo(int codigoPrestamo) {
        return null;
    }

    private void notificarMulta() {

    }

    private List<DevolucionRevistaProf> readDevolucionRevistasProfDAO() {
        return null;
    }

    private List<MultaRevistaProf> readMultasRevistasProfDAO() {
        return null;
    }

    /**
     * Método que obtiene todas las tuplas de la tabla Prestamo_Revista de la
     * BD.
     *
     * @return List
     */
    private List<PrestamoRevistaProf> readPrestamosRevistasProfDAO() {
        return null;
    }

}

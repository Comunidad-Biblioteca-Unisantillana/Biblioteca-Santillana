package moduloMulta.fabrica;

import moduloMulta.entitys.MultaRevistaEst;
import java.sql.Date;
import java.util.List;
import moduloDevolucion.entitys.DevolucionRevistaEst;
import moduloMulta.modelo.IMulta;
import moduloPrestamo.entitys.PrestamoRevistaEst;

/**
 *
 * @author Miguel Fernández
 * @creado: 30/08/2019
 * @author
 * @modificado:
 */
public class MultaRevistaEstFab implements IMulta {

    public MultaRevistaEstFab() {

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

    private void notificarMulta(PrestamoRevistaEst prestamo, int valorMulta) {

    }

    private List<DevolucionRevistaEst> readDevolucionRevistasEstDAO() {
        return null;
    }

    private List<MultaRevistaEst> readMultasRevistasEstDAO() {
        return null;
    }

    private List<PrestamoRevistaEst> readPrestamosRevistasEstDAO() {
        return null;
    }

}

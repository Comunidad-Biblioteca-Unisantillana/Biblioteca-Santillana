package moduloMulta.fabrica;

import java.sql.Date;
import java.util.List;
import moduloDevolucion.entitys.DevolucionEnciclopediaEst;
import moduloMulta.entitys.MultaEnciclopediaEst;
import moduloMulta.modelo.IMulta;
import moduloPrestamo.entitys.PrestamoEnciclopediaEst;

/**
 *
 * @author Miguel Fernández
 * @creado: 30/08/2019
 * @author
 * @modificado:
 */
public class MultaEnciclopediaEstFab implements IMulta {

    public MultaEnciclopediaEstFab() {

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

    private void notificarMulta(PrestamoEnciclopediaEst prestamo, int valorMulta) {

    }

    private List<DevolucionEnciclopediaEst> readDevolucionEnciclopediasEstDAO() {
        return null;
    }

    private List<MultaEnciclopediaEst> readMultasEnciclopediasEstDAO() {
        return null;
    }

    private List<PrestamoEnciclopediaEst> readPrestamosEnciclopediasEstDAO() {
        return null;
    }

}

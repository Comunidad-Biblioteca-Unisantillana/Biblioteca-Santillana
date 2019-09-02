package moduloMulta.fabrica;

import java.sql.Date;
import java.util.List;
import moduloDevolucion.entitys.DevolucionMapaEst;
import moduloMulta.entitys.MultaMapaEst;
import moduloMulta.modelo.IMulta;
import moduloPrestamo.entitys.PrestamoMapaEst;

/**
 *
 * @author Miguel Fernández
 * @creado: 30/08/2019
 * @author
 * @modificado:
 */
public class MultaMapaEstFab implements IMulta {

    public MultaMapaEstFab() {

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

    private void notificarMulta(PrestamoMapaEst prestamo, int valorMulta) {

    }

    private List<DevolucionMapaEst> readDevolucionMapasEstDAO() {
        return null;
    }

    private List<MultaMapaEst> readMultasMapasEstDAO() {
        return null;
    }

    private List<PrestamoMapaEst> readPrestamosMapasEstDAO() {
        return null;
    }

}

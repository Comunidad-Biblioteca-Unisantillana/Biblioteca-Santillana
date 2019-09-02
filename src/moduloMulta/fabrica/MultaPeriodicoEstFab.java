package moduloMulta.fabrica;

import java.sql.Date;
import java.util.List;
import moduloDevolucion.entitys.DevolucionPeriodicoEst;
import moduloMulta.entitys.MultaPeriodicoEst;
import moduloMulta.modelo.IMulta;
import moduloPrestamo.entitys.PrestamoPeriodicoEst;

/**
 *
 * @author Miguel Fernández
 * @creado: 30/08/2019
 * @author
 * @modificado:
 */
public class MultaPeriodicoEstFab implements IMulta {

    public MultaPeriodicoEstFab() {

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

    private void notificarMulta(PrestamoPeriodicoEst prestamo, int valorMulta) {

    }

    private List<DevolucionPeriodicoEst> readDevolucionRevistasEstDAO() {
        return null;
    }

    private List<MultaPeriodicoEst> readMultasRevistasEstDAO() {
        return null;
    }

    private List<PrestamoPeriodicoEst> readPrestamosPeriodicosEstDAO() {
        return null;
    }

}

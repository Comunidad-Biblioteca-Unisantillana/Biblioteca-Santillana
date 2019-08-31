package moduloMulta.fabrica;

import java.sql.Date;
import java.util.List;
import moduloDevolucion.entitys.DevolucionDiccionarioEst;
import moduloMulta.entitys.MultaDiccionarioEst;
import moduloMulta.modelo.IMulta;
import moduloPrestamo.entitys.PrestamoDiccionarioEst;

/**
 *
 * @author Miguel Fernández
 * @creado: 30/08/2019
 * @author
 * @modificado:
 */
public class MultaDiccionarioEstFab implements IMulta {

    public MultaDiccionarioEstFab() {

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

    private void notificarMulta(PrestamoDiccionarioEst prestamo, int valorMulta) {

    }

    private List<DevolucionDiccionarioEst> readDevolucionDiccionariosEstDAO() {
        return null;
    }

    private List<MultaDiccionarioEst> readMultasDiccionariosEstDAO() {
        return null;
    }

    private List<PrestamoDiccionarioEst> readPrestamosDiccionariosEstDAO() {
        return null;
    }

}

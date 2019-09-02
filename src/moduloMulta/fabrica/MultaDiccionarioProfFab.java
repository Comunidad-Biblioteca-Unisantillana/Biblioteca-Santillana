package moduloMulta.fabrica;

import java.sql.Date;
import java.util.List;
import moduloDevolucion.entitys.DevolucionDiccionarioProf;
import moduloMulta.entitys.MultaDiccionarioProf;
import moduloMulta.modelo.IMulta;
import moduloPrestamo.entitys.PrestamoDiccionarioProf;

/**
 *
 * @author Miguel Fernández
 * @creado: 30/08/2019
 * @author
 * @modificado:
 */
public class MultaDiccionarioProfFab implements IMulta {

    public MultaDiccionarioProfFab() {

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

    private void notificarMulta(PrestamoDiccionarioProf prestamo, int valorMulta) {

    }

    private List<DevolucionDiccionarioProf> readDevolucionDiccionariosProfDAO() {
        return null;
    }

    private List<MultaDiccionarioProf> readMultasDiccionariosProfDAO() {
        return null;
    }

    private List<PrestamoDiccionarioProf> readPrestamosDiccionariosProfDAO() {
        return null;
    }

}

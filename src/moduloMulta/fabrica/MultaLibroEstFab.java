package moduloMulta.fabrica;

import java.sql.Date;
import java.util.List;
import moduloDevolucion.entitys.DevolucionLibroEst;
import moduloMulta.entitys.MultaLibroEst;
import moduloMulta.modelo.IMulta;
import moduloPrestamo.entitys.PrestamoLibroEst;

/**
 *
 * @author Miguel Fernández
 * @creado: 30/08/2019
 * @author
 * @modificado:
 */
public class MultaLibroEstFab implements IMulta {

    public MultaLibroEstFab() {

    }

    @Override
    public void actualizarDiasAtrasadosMulta() {

    }

    @Override
    public void generarMulta() {

    }

    private Date getFechaDevolucionPrestamoEst(int codigoPrestamo) {
        return null;
    }

    private void notificarMulta(PrestamoLibroEst prestamo, int valorMulta) {

    }

    private List<DevolucionLibroEst> readDevolucionLibrosEstDAO() {
        return null;
    }

    private List<MultaLibroEst> readMultasLibrosEstDAO() {
        return null;
    }

    private List<PrestamoLibroEst> readPrestamosLibrosEstDAO() {
        return null;
    }

}

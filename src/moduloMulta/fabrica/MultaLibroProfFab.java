package moduloMulta.fabrica;

import java.sql.Date;
import java.util.List;
import moduloDevolucion.entitys.DevolucionLibroProf;
import moduloMulta.entitys.MultaLibroProf;
import moduloMulta.modelo.IMulta;
import moduloPrestamo.entitys.PrestamoLibroProf;

/**
 *
 * @author Miguel Fernández
 * @creado: 30/08/2019
 * @author
 * @modificado:
 */
public class MultaLibroProfFab implements IMulta {

    public MultaLibroProfFab() {

    }

    @Override
    public void actualizarDiasAtrasadosMulta() {

    }

    @Override
    public void generarMulta() {

    }

    private Date getFechaDevolucionPrestamoProf(int codigoPrestamo) {
        return null;
    }

    private void notificarMulta(PrestamoLibroProf prestamo, int valorMulta) {

    }

    private List<DevolucionLibroProf> readDevolucionLibrosProfDAO() {
        return null;
    }

    private List<MultaLibroProf> readMultasLibrosProfDAO() {
        return null;
    }

    private List<PrestamoLibroProf> readPrestamosLibrosProfDAO() {
        return null;
    }

}

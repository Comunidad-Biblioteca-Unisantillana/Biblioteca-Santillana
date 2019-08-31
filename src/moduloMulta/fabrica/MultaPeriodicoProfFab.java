package moduloMulta.fabrica;

import java.sql.Date;
import java.util.List;
import moduloDevolucion.entitys.DevolucionPeriodicoProf;
import moduloMulta.entitys.MultaPeriodicoProf;
import moduloMulta.modelo.IMulta;
import moduloPrestamo.entitys.PrestamoPeriodicoProf;

/**
 *
 * @author Miguel Fernández
 * @creado: 30/08/2019
 * @author
 * @modificado:
 */
public class MultaPeriodicoProfFab implements IMulta {

    public MultaPeriodicoProfFab() {

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

    private void notificarMulta(PrestamoPeriodicoProf prestamo, int valorMulta) {

    }

    private List<DevolucionPeriodicoProf> readDevolucionRevistasProfDAO() {
        return null;
    }

    private List<MultaPeriodicoProf> readMultasRevistasProfDAO() {
        return null;
    }

    private List<PrestamoPeriodicoProf> readPrestamosPeriodicosProfDAO() {
        return null;
    }

}

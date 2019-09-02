package moduloMulta.fabrica;

import java.sql.Date;
import java.util.List;
import moduloDevolucion.entitys.DevolucionMapaProf;
import moduloMulta.entitys.MultaMapaProf;
import moduloMulta.modelo.IMulta;
import moduloPrestamo.entitys.PrestamoMapaProf;

/**
 *
 * @author Miguel Fernández
 * @creado: 30/08/2019
 * @author
 * @modificado:
 */
public class MultaMapaProfFab implements IMulta {

    public MultaMapaProfFab() {

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

    public void notificarMulta(PrestamoMapaProf prestamo, int valorMulta) {

    }

    private List<DevolucionMapaProf> readDevolucionMapasProfDAO() {
        return null;
    }

    private List<MultaMapaProf> readMultasMapasProfDAO() {
        return null;
    }

    private List<PrestamoMapaProf> readPrestamosMapasProfDAO() {
        return null;
    }

}

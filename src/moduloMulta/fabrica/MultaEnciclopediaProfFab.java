package moduloMulta.fabrica;

import java.sql.Date;
import java.util.List;
import moduloDevolucion.entitys.DevolucionEnciclopediaProf;
import moduloMulta.entitys.MultaEnciclopediaProf;
import moduloMulta.modelo.IMulta;
import moduloPrestamo.entitys.PrestamoEnciclopediaProf;

/**
 *
 * @author Miguel Fernández
 * @creado: 30/08/2019
 * @author
 * @modificado:
 */
public class MultaEnciclopediaProfFab implements IMulta {

    public MultaEnciclopediaProfFab() {

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

    private void notificarMulta(PrestamoEnciclopediaProf prestamo, int valorMulta) {

    }

    private List<DevolucionEnciclopediaProf> readDevolucionEnciclopediasProfDAO() {
        return null;
    }

    private List<MultaEnciclopediaProf> readMultasEnciclopediaProfDAO() {
        return null;
    }

    private List<PrestamoEnciclopediaProf> readPrestamosEnciclopediasProfDAO() {
        return null;
    }

}

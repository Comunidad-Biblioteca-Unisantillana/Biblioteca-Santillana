package moduloDevolucion.fabrica;

import controller.exceptions.NonexistentEntityException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import moduloDevolucion.DAO.DevolucionPeriodicoDAOEst;
import moduloDevolucion.IDevolucion;
import moduloDevolucion.entitys.DevolucionPeriodicoEst;
import moduloPrestamo.DAO.PrestamoPeriodicoDAOEst;
import moduloPrestamo.entitys.PrestamoPeriodicoEst;
import recursos1.controllers.PeriodicoJpaController;
import recursos1.entitys.Periodico;
import vista.AlertBox;
import vista.IAlertBox;

/**
 * @author Camilo Jaramillo
 * @version 1.0
 * @created 04-ago.-2019 10:37:52 a. m.
 */
public class DevolucionPeriodicoEstFab implements IDevolucion {

    public DevolucionPeriodicoEstFab() {

    }

    @Override
    public boolean ejecutarDevolucion(String codBarras, String idBibliotecario, String estadoRecurso) {
        IAlertBox alert = new AlertBox();
        try {
            PeriodicoJpaController control = new PeriodicoJpaController();
            Periodico periodico = control.findPeriodico(codBarras);
            if (periodico != null) {
                int codPrestamo = consultarPrestamoPeriodico(codBarras);
                if (codPrestamo > 0) {

                    PrestamoPeriodicoDAOEst prestDAOEst = new PrestamoPeriodicoDAOEst();
                    PrestamoPeriodicoEst prestEst = prestDAOEst.readDAO(codPrestamo);
                    if (prestEst.getDevuelto().equalsIgnoreCase("no")) {
                        DevolucionPeriodicoEst devEst = new DevolucionPeriodicoEst(prestEst.getCodPrestamoPeriodicoEst(), idBibliotecario, null, estadoRecurso);
                        DevolucionPeriodicoDAOEst devDAOEst = new DevolucionPeriodicoDAOEst();
                        devDAOEst.createDAO(devEst);

                        periodico.setDisponibilidad("disponible");
                        control.edit(periodico);

                        prestEst.setDevuelto("si");
                        prestDAOEst.updateDAO(prestEst);
                        alert.showAlert("Anuncio", "Devolución periodico", "La devolución del usuario con codigo"
                                + prestEst.getCodEstudiante()
                                + "se realizo con exito");
                    } else {
                        alert.showAlert("Anuncio", "Devolución periodico", "El periodico se había devuelto anteriormente");
                    }
                    return true;
                }
            }
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(DevolucionDiccionarioEstFab.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(DevolucionDiccionarioEstFab.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public int consultarPrestamoPeriodico(String codBarras) {
        PrestamoPeriodicoDAOEst prestDAOEst = new PrestamoPeriodicoDAOEst();
        List<PrestamoPeriodicoEst> prestamos = prestDAOEst.readAllDAO();
        int codPrestamo = 0;
        for (int i = 0; i < prestamos.size(); i++) {
            if (prestamos.get(i).getCodBarraPeriodico().equalsIgnoreCase(codBarras)) {
                codPrestamo = prestamos.get(i).getCodPrestamoPeriodicoEst();
                break;
            }
        }
        return codPrestamo;
    }

    public void notificarDevolucion(String codEstudiante, String tituloRecurso, java.util.Date fechaDevolucion) {

    }
}

package moduloDevolucion.fabrica;

import controller.exceptions.NonexistentEntityException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import moduloDevolucion.DAO.DevolucionMapaDAOEst;
import moduloDevolucion.IDevolucion;
import moduloDevolucion.entitys.DevolucionMapaEst;
import moduloPrestamo.DAO.PrestamoMapaDAOEst;
import moduloPrestamo.entitys.PrestamoMapaEst;
import recursos1.controllers.MapaJpaController;
import recursos1.entitys.Mapa;
import vista.AlertBox;
import vista.IAlertBox;

/**
 * @author Camilo Jaramillo
 * @version 1.0
 * @created 04-ago.-2019 10:37:45 a. m.
 */
public class DevolucionMapaEstFab implements IDevolucion {

    public DevolucionMapaEstFab() {

    }

    @Override
    public boolean ejecutarDevolucion(String codBarras, String idBibliotecario, String estadoRecurso) {
        IAlertBox alert = new AlertBox();
        try {
            MapaJpaController control = new MapaJpaController();
            Mapa mapa = control.findMapa(codBarras);
            if (mapa != null) {
                int codPrestamo = consultarPrestamoMapa(codBarras);
                if (codPrestamo > 0) {

                    PrestamoMapaDAOEst prestDAOEst = new PrestamoMapaDAOEst();
                    PrestamoMapaEst prestEst = prestDAOEst.readDAO(codPrestamo);
                    if (prestEst.getDevuelto().equalsIgnoreCase("no")) {
                        DevolucionMapaEst devEst = new DevolucionMapaEst(prestEst.getCodPrestamoMapaEst(), idBibliotecario, null, estadoRecurso);
                        DevolucionMapaDAOEst devDAOEst = new DevolucionMapaDAOEst();
                        devDAOEst.createDAO(devEst);

                        mapa.setDisponibilidad("disponible");
                        control.edit(mapa);

                        prestEst.setDevuelto("si");
                        prestDAOEst.updateDAO(prestEst);
                        alert.showAlert("Anuncio", "Devolución mapa", "La devolución del usuario con codigo"
                                + prestEst.getCodEstudiante()
                                + "se realizo con exito");
                    } else {
                        alert.showAlert("Anuncio", "Devolución mapa", "El mapa se había devuelto anteriormente");
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

    public int consultarPrestamoMapa(String codBarras) {
        PrestamoMapaDAOEst prestDAOEst = new PrestamoMapaDAOEst();
        List<PrestamoMapaEst> prestamos = prestDAOEst.readAllDAO();
        int codPrestamo = 0;
        for (int i = 0; i < prestamos.size(); i++) {
            if (prestamos.get(i).getCodBarraMapa().equalsIgnoreCase(codBarras)) {
                codPrestamo = prestamos.get(i).getCodPrestamoMapaEst();
                break;
            }
        }
        return codPrestamo;
    }

    public void notificarDevolucion(String codEstudiante, String tituloRecurso, java.util.Date fechaDevolucion) {

    }
}

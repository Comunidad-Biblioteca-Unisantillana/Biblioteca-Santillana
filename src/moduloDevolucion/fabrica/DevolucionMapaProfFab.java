package moduloDevolucion.fabrica;

import controller.exceptions.NonexistentEntityException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import moduloDevolucion.DAO.DevolucionMapaDAOProf;
import moduloDevolucion.IDevolucion;
import moduloDevolucion.entitys.DevolucionMapaProf;
import moduloPrestamo.DAO.PrestamoMapaDAOProf;
import moduloPrestamo.entitys.PrestamoMapaProf;
import recursos1.controllers.MapaJpaController;
import recursos1.entitys.Mapa;
import vista.AlertBox;
import vista.IAlertBox;

/**
 * @author Camilo Jaramillo
 * @version 1.0
 * @created 04-ago.-2019 10:37:48 a. m.
 */
public class DevolucionMapaProfFab implements IDevolucion {

    public DevolucionMapaProfFab() {

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
                    PrestamoMapaDAOProf prestDAOProf = new PrestamoMapaDAOProf();
                    PrestamoMapaProf prestProf = prestDAOProf.readDAO(codPrestamo);
                    if (prestProf.getDevuelto() == 'n') {
                        DevolucionMapaProf devProf = new DevolucionMapaProf(prestProf.getCodPrestamoMapaProf(), idBibliotecario, null, estadoRecurso);
                        DevolucionMapaDAOProf devDAOProf = new DevolucionMapaDAOProf();
                        devDAOProf.createDAO(devProf);

                        mapa.setDisponibilidad("disponible");
                        control.edit(mapa);

                        prestProf.setDevuelto('s');
                        prestDAOProf.updateDAO(prestProf);
                        alert.showAlert("Anuncio", "Devolución mapa", "La devolución del usuario con codigo"
                                + prestProf.getIdProfesor() + "se realizo con exito");
                    } else {
                        alert.showAlert("Anuncio", "Devolución mapa", "El mapa se había devuelto anteriormente");
                    }
                    return true;
                } else {
                    alert.showAlert("Anuncio", "Devolución mapa", "El prestamo del mapa no existe");
                }
            } else {
                alert.showAlert("Anuncio", "Devolución mapa", "El mapa no existe");
            }
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(DevolucionDiccionarioEstFab.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(DevolucionDiccionarioEstFab.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public int consultarPrestamoMapa(String codBarras) {
        PrestamoMapaDAOProf prestDAOProf = new PrestamoMapaDAOProf();
        List<PrestamoMapaProf> prestamos = prestDAOProf.readAllDAO();
        int codPrestamo = 0;
        for (int i = 0; i < prestamos.size(); i++) {
            if (prestamos.get(i).getCodBarraMapa().equalsIgnoreCase(codBarras)) {
                codPrestamo = prestamos.get(i).getCodPrestamoMapaProf();
                break;
            }
        }
        return codPrestamo;
    }

    public void notificarDevolucion(String idProfesor, String tituloRecurso, java.util.Date fechaDevolucion) {

    }
}

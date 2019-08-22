package moduloDevolucion.fabrica;

import controller.exceptions.NonexistentEntityException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import moduloDevolucion.DAO.DevolucionEnciclopediaDAOEst;
import moduloDevolucion.IDevolucion;
import moduloDevolucion.entitys.DevolucionEnciclopediaEst;
import moduloPrestamo.DAO.PrestamoEnciclopediaDAOEst;
import moduloPrestamo.entitys.PrestamoEnciclopediaEst;
import recursos1.controllers.EnciclopediaJpaController;
import recursos1.entitys.Enciclopedia;
import vista.AlertBox;
import vista.IAlertBox;

/**
 * @author Camilo Jaramillo
 * @version 1.0
 * @created 04-ago.-2019 10:37:29 a. m.
 */
public class DevolucionEnciclopediaEstFab implements IDevolucion {

    public DevolucionEnciclopediaEstFab() {

    }

    @Override
    public boolean ejecutarDevolucion(String codBarras, String idBibliotecario, String estadoRecurso) {
        IAlertBox alert = new AlertBox();
        try {
            EnciclopediaJpaController control = new EnciclopediaJpaController();
            Enciclopedia enciclopedia = control.findEnciclopedia(codBarras);
            if (enciclopedia != null) {
                int codPrestamo = consultarPrestamoEnciclopedia(codBarras);
                if (codPrestamo > 0) {
                    PrestamoEnciclopediaDAOEst prestDAOEst = new PrestamoEnciclopediaDAOEst();
                    PrestamoEnciclopediaEst prestEst = prestDAOEst.readDAO(codPrestamo);
                    if (prestEst.getDevuelto() == 'n') {
                        DevolucionEnciclopediaEst devEst = new DevolucionEnciclopediaEst(prestEst.getCodPrestamoEnciclopediaEst(), idBibliotecario, null, estadoRecurso);
                        DevolucionEnciclopediaDAOEst devDAOEst = new DevolucionEnciclopediaDAOEst();
                        devDAOEst.createDAO(devEst);

                        enciclopedia.setDisponibilidad("disponible");
                        control.edit(enciclopedia);

                        prestEst.setDevuelto('s');
                        prestDAOEst.updateDAO(prestEst);
                        alert.showAlert("Anuncio", "Devolución enciclopedia", "La devolución del usuario con codigo"
                                + prestEst.getCodEstudiante() + "se realizo con exito");
                    } else {
                        alert.showAlert("Anuncio", "Devolución enciclopedia", "La enciclopedia se había devuelto anteriormente");
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

    public int consultarPrestamoEnciclopedia(String codBarras) {
        PrestamoEnciclopediaDAOEst prestDAOEst = new PrestamoEnciclopediaDAOEst();
        List<PrestamoEnciclopediaEst> prestamos = prestDAOEst.readAllDAO();
        int codPrestamo = 0;
        for (int i = 0; i < prestamos.size(); i++) {
            if (prestamos.get(i).getCodBarraEnciclopedia().equalsIgnoreCase(codBarras)) {
                codPrestamo = prestamos.get(i).getCodPrestamoEnciclopediaEst();
                break;
            }
        }
        return codPrestamo;
    }

    public void notificarDevolucion(String codEstudiante, String tituloRecurso, java.util.Date fechaDevolucion) {

    }
}

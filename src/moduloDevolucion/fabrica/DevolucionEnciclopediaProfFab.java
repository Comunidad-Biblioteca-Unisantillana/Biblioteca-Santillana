package moduloDevolucion.fabrica;

import controller.exceptions.NonexistentEntityException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import moduloDevolucion.DAO.DevolucionEnciclopediaDAOProf;
import moduloDevolucion.IDevolucion;
import moduloDevolucion.entitys.DevolucionEnciclopediaProf;
import moduloPrestamo.DAO.PrestamoEnciclopediaDAOProf;
import moduloPrestamo.entitys.PrestamoEnciclopediaProf;
import recursos1.controllers.EnciclopediaJpaController;
import recursos1.entitys.Enciclopedia;
import vista.AlertBox;
import vista.IAlertBox;

/**
 * @author Camilo Jaramillo
 * @version 1.0
 * @created 04-ago.-2019 10:37:33 a. m.
 */
public class DevolucionEnciclopediaProfFab implements IDevolucion {

    public DevolucionEnciclopediaProfFab() {

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

                    PrestamoEnciclopediaDAOProf prestDAOProf = new PrestamoEnciclopediaDAOProf();
                    PrestamoEnciclopediaProf prestProf = prestDAOProf.readDAO(codPrestamo);
                    if (prestProf.getDevuelto().equalsIgnoreCase("no")) {
                        DevolucionEnciclopediaProf devProf = new DevolucionEnciclopediaProf(prestProf.getCodPrestamoEnciclopediaProf(), idBibliotecario, null, estadoRecurso);
                        DevolucionEnciclopediaDAOProf devDAOProf = new DevolucionEnciclopediaDAOProf();
                        devDAOProf.createDAO(devProf);

                        enciclopedia.setDisponibilidad("disponible");
                        control.edit(enciclopedia);

                        prestProf.setDevuelto("si");
                        prestDAOProf.updateDAO(prestProf);
                        alert.showAlert("Anuncio", "Devolución enciclopedia", "La devolución del usuario con codigo"
                                + prestProf.getIdProfesor() + "se realizo con exito");
                    } else {
                        alert.showAlert("Anuncio", "Devolución enciclopedia", "La enciclopedia se había devuelto anteriormente");
                    }
                    return true;
                } else {
                    alert.showAlert("Anuncio", "Devolución enciclopedia", "El prestamo de la enciclopedia no existe");
                }
            } else {
                alert.showAlert("Anuncio", "Devolución enciclopedia", "La enciclopedia no existe");
            }
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(DevolucionDiccionarioEstFab.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(DevolucionDiccionarioEstFab.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public int consultarPrestamoEnciclopedia(String codBarras) {
        PrestamoEnciclopediaDAOProf prestDAOProf = new PrestamoEnciclopediaDAOProf();
        List<PrestamoEnciclopediaProf> prestamos = prestDAOProf.readAllDAO();
        int codPrestamo = 0;
        for (int i = 0; i < prestamos.size(); i++) {
            if (prestamos.get(i).getCodBarraEnciclopedia().equalsIgnoreCase(codBarras)) {
                codPrestamo = prestamos.get(i).getCodPrestamoEnciclopediaProf();
                break;
            }
        }
        return codPrestamo;
    }

    public void notificarDevolucion(String idProfesor, String tituloRecurso, java.util.Date fechaDevolucion) {

    }
}

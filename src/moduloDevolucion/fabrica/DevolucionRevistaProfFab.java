package moduloDevolucion.fabrica;

import controller.exceptions.NonexistentEntityException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import moduloDevolucion.DAO.DevolucionRevistaDAOProf;
import moduloDevolucion.IDevolucion;
import moduloDevolucion.entitys.DevolucionRevistaProf;
import moduloPrestamo.DAO.PrestamoRevistaDAOProf;
import moduloPrestamo.entitys.PrestamoRevistaProf;
import recursos1.controllers.RevistaJpaController;
import recursos1.entitys.Revista;
import vista.AlertBox;
import vista.IAlertBox;

/**
 * @author Camilo Jaramillo
 * @version 1.0
 * @created 04-ago.-2019 10:38:03 a. m.
 */
public class DevolucionRevistaProfFab implements IDevolucion {

    public DevolucionRevistaProfFab() {

    }

    @Override
    public boolean ejecutarDevolucion(String codBarras, String idBibliotecario, String estadoRecurso) {
        IAlertBox alert = new AlertBox();
        try {
            RevistaJpaController control = new RevistaJpaController();
            Revista revista = control.findRevista(codBarras);
            if (revista != null) {
                int codPrestamo = consultarPrestamoRevista(codBarras);
                if (codPrestamo > 0) {
                    PrestamoRevistaDAOProf prestDAOProf = new PrestamoRevistaDAOProf();
                    PrestamoRevistaProf prestProf = prestDAOProf.readDAO(codPrestamo);
                    if (prestProf.getDevuelto().equalsIgnoreCase("no")) {
                        DevolucionRevistaProf devProf = new DevolucionRevistaProf(prestProf.getCodPrestamoRevistaProf(), idBibliotecario, null, estadoRecurso);
                        DevolucionRevistaDAOProf devDAOProf = new DevolucionRevistaDAOProf();
                        devDAOProf.createDAO(devProf);

                        revista.setDisponibilidad("disponible");
                        control.edit(revista);

                        prestProf.setDevuelto("si");
                        prestDAOProf.updateDAO(prestProf);
                        alert.showAlert("Anuncio", "Devolución revista", "La devolución del usuario con codigo"
                                + prestProf.getIdProfesor() + "se realizo con exito");
                    } else {
                        alert.showAlert("Anuncio", "Devolución revista", "La revista se había devuelto anteriormente");
                    }
                    return true;
                } else {
                    alert.showAlert("Anuncio", "Devolución revista", "El prestamo de la revista no existe");
                }
            } else {
                alert.showAlert("Anuncio", "Devolución revista", "La revista no existe");
            }
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(DevolucionDiccionarioEstFab.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(DevolucionDiccionarioEstFab.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public int consultarPrestamoRevista(String codBarras) {
        PrestamoRevistaDAOProf prestDAOProf = new PrestamoRevistaDAOProf();
        List<PrestamoRevistaProf> prestamos = prestDAOProf.readAllDAO();
        int codPrestamo = 0;
        for (int i = 0; i < prestamos.size(); i++) {
            if (prestamos.get(i).getCodBarraRevista().equalsIgnoreCase(codBarras)) {
                codPrestamo = prestamos.get(i).getCodPrestamoRevistaProf();
                break;
            }
        }
        return codPrestamo;
    }

    public void notificarDevolucion(String idProfesor, String tituloRecurso, java.util.Date fechaDevolucion) {

    }
}

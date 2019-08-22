package moduloDevolucion.fabrica;

import controller.exceptions.NonexistentEntityException;
import recursos1.controllers.DiccionarioJpaController;
import recursos1.entitys.Diccionario;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import moduloDevolucion.DAO.DevolucionDiccionarioDAOProf;
import moduloDevolucion.IDevolucion;
import moduloDevolucion.entitys.DevolucionDiccionarioProf;
import moduloPrestamo.DAO.PrestamoDiccionarioDAOProf;
import moduloPrestamo.entitys.PrestamoDiccionarioProf;
import vista.AlertBox;
import vista.IAlertBox;

/**
 * @author Camilo Jaramillo
 * @version 1.0
 * @created 04-ago.-2019 10:37:25 a. m.
 */
public class DevolucionDiccionarioProfFab implements IDevolucion {

    public DevolucionDiccionarioProfFab() {

    }

    @Override
    public boolean ejecutarDevolucion(String codBarras, String idBibliotecario, String estadoRecurso) {
        IAlertBox alert = new AlertBox();
        try {

            DiccionarioJpaController control = new DiccionarioJpaController();
            Diccionario diccionario = control.findDiccionario(codBarras);
            if (diccionario != null) {
                int codPrestamo = consultarPrestamoDiccionario(codBarras);
                if (codPrestamo > 0) {
                    PrestamoDiccionarioDAOProf prestDAOProf = new PrestamoDiccionarioDAOProf();
                    PrestamoDiccionarioProf prestProf = prestDAOProf.readDAO(codPrestamo);
                    if (prestProf.getDevuelto() == 'n') {
                        DevolucionDiccionarioProf devProf = new DevolucionDiccionarioProf(prestProf.getCodPrestamoDiccionarioProf(), idBibliotecario, null, estadoRecurso);
                        DevolucionDiccionarioDAOProf devDAOProf = new DevolucionDiccionarioDAOProf();
                        devDAOProf.createDAO(devProf);

                        diccionario.setDisponibilidad("disponible");
                        control.edit(diccionario);

                        prestProf.setDevuelto('s');
                        prestDAOProf.updateDAO(prestProf);
                        alert.showAlert("Anuncio", "Devolucion", "La devolucion del usuario con codigo"
                                + prestProf.getIdProfesor() + "se realizo con exito");
                    } else {
                        alert.showAlert("Anuncio", "Devolución diccionario", "El diccionario se había devuelto anteriormente");
                    }
                    return true;
                } else {
                    alert.showAlert("Anuncio", "Devolución diccionario", "El prestamo del diccionario no existe");
                }
            } else {
                alert.showAlert("Anuncio", "Devolución diccionario", "El diccionario no existe");
            }
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(DevolucionDiccionarioEstFab.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(DevolucionDiccionarioEstFab.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public int consultarPrestamoDiccionario(String codBarras) {
        PrestamoDiccionarioDAOProf prestDAOProf = new PrestamoDiccionarioDAOProf();
        List<PrestamoDiccionarioProf> prestamos = prestDAOProf.readAllDAO();
        int codPrestamo = 0;
        for (int i = 0; i < prestamos.size(); i++) {
            if (prestamos.get(i).getCodBarraDiccionario().equalsIgnoreCase(codBarras)) {
                codPrestamo = prestamos.get(i).getCodPrestamoDiccionarioProf();
                break;
            }
        }
        return codPrestamo;
    }

    public void notificarDevolucion(String idProfesor, String tituloRecurso, java.util.Date fechaDevolucion) {

    }
}

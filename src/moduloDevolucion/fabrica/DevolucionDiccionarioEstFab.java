package moduloDevolucion.fabrica;

import controller.exceptions.NonexistentEntityException;
import controllers.DiccionarioJpaController;
import controllers.LibroJpaController;
import entitys.Diccionario;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import moduloDevolucion.DAO.DevolucionDiccionarioDAOEst;
import moduloDevolucion.IDevolucion;
import moduloDevolucion.entitys.DevolucionDiccionarioEst;
import moduloPrestamo.DAO.PrestamoDiccionarioDAOEst;
import moduloPrestamo.entitys.PrestamoDiccionarioEst;
import vista.AlertBox;
import vista.IAlertBox;

/**
 * @author Camilo Jaramillo
 * @version 1.0
 * @created 04-ago.-2019 10:37:21 a. m.
 */
public class DevolucionDiccionarioEstFab implements IDevolucion {

    public DevolucionDiccionarioEstFab() {
    }

    @Override
    public boolean ejecutarDevolucion(String codBarras, String idBibliotecario, String estadoRecurso) {
        IAlertBox alert = new AlertBox();
        try {
            int codPrestamo = consultarPrestamoDiccionario(codBarras);
            if (codPrestamo > 0) {
                DiccionarioJpaController control = new DiccionarioJpaController();
                Diccionario diccionario = control.findDiccionario(codBarras);
                if (diccionario != null) {
                    if (diccionario.getDisponibilidad().equalsIgnoreCase("prestado")) {
                        PrestamoDiccionarioDAOEst prestDAOEst = new PrestamoDiccionarioDAOEst();
                        PrestamoDiccionarioEst prestEst = prestDAOEst.readDAO(codPrestamo);

                        DevolucionDiccionarioEst devDicEst = new DevolucionDiccionarioEst(prestEst.getCodPrestamoDiccionarioEst(), idBibliotecario, null, estadoRecurso);
                        DevolucionDiccionarioDAOEst devDicDAOEst = new DevolucionDiccionarioDAOEst();
                        devDicDAOEst.createDAO(devDicEst);

                        diccionario.setDisponibilidad("disponible");
                        control.edit(diccionario);

                        prestEst.setDevuelto('s');
                        prestDAOEst.updateDAO(prestEst);
                        alert.showAlert("Anuncio", "Devolucion", "La devolucion del usuario con codigo"
                                + prestEst.getCodEstudiante() + "se realizo con exito");
                        return true;
                    }
                }
            }
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(DevolucionDiccionarioEstFab.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(DevolucionDiccionarioEstFab.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public int consultarPrestamoDiccionario(String codBarras) {
        PrestamoDiccionarioDAOEst prestDAOEst = new PrestamoDiccionarioDAOEst();
        List<PrestamoDiccionarioEst> prestamos = prestDAOEst.readAllDAO();
        int codPrestamo = -1;
        for (int i = 0; i < prestamos.size(); i++) {
            if (prestamos.get(i).getCodBarraDiccionario().equalsIgnoreCase(codBarras)) {
                codPrestamo = prestamos.get(i).getCodPrestamoDiccionarioEst();
                break;
            }
        }
        return codPrestamo;
    }

    public void notificarDevolucion(String codEstudiante, String tituloRecurso, java.util.Date fechaDevolucion) {

    }
}//end DevolucionDiccionarioEstFab

package moduloDevolucion.fabrica;

import controller.exceptions.NonexistentEntityException;
import java.sql.Date;
import recursos.controllers.DiccionarioJpaController;
import recursos.entitys.Diccionario;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import moduloDevolucion.DAO.DevolucionDiccionarioDAOEst;
import moduloDevolucion.modelo.IDevolucion;
import moduloDevolucion.entitys.DevolucionDiccionarioEst;
import moduloPrestamo.DAO.PrestamoDiccionarioDAOEst;
import moduloPrestamo.entitys.PrestamoDiccionarioEst;
import general.vista.AlertBox;
import general.vista.IAlertBox;

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
            DiccionarioJpaController control = new DiccionarioJpaController();
            Diccionario diccionario = control.findDiccionario(codBarras);
            if (diccionario != null) {
                int codPrestamo = consultarPrestamoDiccionario(codBarras);
                if (codPrestamo > 0) {
                    PrestamoDiccionarioDAOEst prestDAOEst = new PrestamoDiccionarioDAOEst();
                    PrestamoDiccionarioEst prestEst = prestDAOEst.readDAO(codPrestamo);
                    if (prestEst.getDevuelto().equalsIgnoreCase("no")) {
                        java.util.Date fechaDevolucion =  new java.util.Date();
                        
                        DevolucionDiccionarioEst devEst = new DevolucionDiccionarioEst(prestEst.getCodPrestamoDiccionarioEst(),
                                idBibliotecario, new Date(fechaDevolucion.getTime()), estadoRecurso);
                        DevolucionDiccionarioDAOEst devDAOEst = new DevolucionDiccionarioDAOEst();
                        devDAOEst.createDAO(devEst);
                        diccionario.setDisponibilidad("disponible");
                        control.edit(diccionario);

                        prestEst.setDevuelto("si");
                        prestDAOEst.updateDAO(prestEst);
                        alert.showAlert("Anuncio", "Devolucion diccionario", "La devolucion del usuario con codigo"
                                + prestEst.getCodEstudiante() + "se realizo con exito");
                    } else {
                        alert.showAlert("Anuncio", "Devolución diccionario", "El diccionario se había devuelto anteriormente");
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

    public int consultarPrestamoDiccionario(String codBarras) {
        PrestamoDiccionarioDAOEst prestDAOEst = new PrestamoDiccionarioDAOEst();
        List<PrestamoDiccionarioEst> prestamos = prestDAOEst.readAllDAO();
        int codPrestamo = 0;
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

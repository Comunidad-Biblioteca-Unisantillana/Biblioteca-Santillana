package moduloPrestamo.fabrica;

import java.util.Date;
import moduloPrestamo.entitys.PrestamoDiccionarioEst;
import recursos1.controllers.DiccionarioJpaController;
import recursos1.entitys.Diccionario;
import moduloPrestamo.DAO.PrestamoDiccionarioDAOEst;
import vista.AlertBox;
import vista.IAlertBox;

/**
 * La clase se encarga gestionar el préstamo del diccionario al estudiante.
 *
 * @author Julian
 * @creado
 * @author Miguel Fernández
 * @modificado 24/08/2019
 */
public class PrestamoDiccionarioEstFab implements IPrestamo {

    /**
     * constructor de la clase sin parámetros.
     */
    public PrestamoDiccionarioEstFab() {

    }

    /**
     * el metódo realiza el préstamo del dicionario al estudiante.
     *
     * @param codBarras
     * @param codUsuario
     * @param idBibliotecario
     * @return boolean
     */
    @Override
    public boolean ejecutarPrestamo(String codBarras, String codUsuario, String idBibliotecario) {
        IAlertBox alert = new AlertBox();
        
        try {
            DiccionarioJpaController controlDic = new DiccionarioJpaController();
            Diccionario dicccionario = controlDic.findDiccionario(codBarras);
            
            if (dicccionario != null) {
                if (dicccionario.getDisponibilidad().equalsIgnoreCase("disponible")) {
                    PrestamoDiccionarioEst prestDicEst = new PrestamoDiccionarioEst();
                    prestDicEst.setCodBarraDiccionario(codBarras);
                    prestDicEst.setCodEstudiante(codUsuario);
                    prestDicEst.setIdBibliotecario(idBibliotecario);
                    
                    PrestamoDiccionarioDAOEst prestDicDAOEst = new PrestamoDiccionarioDAOEst();
                    
                    if (prestDicDAOEst.createDAO(prestDicEst)) {
                        dicccionario.setDisponibilidad("prestado");
                        controlDic.edit(dicccionario);
                        
                        //espacio para el envio del correo
                        
                        return true; 
                    }

                } else if (dicccionario.getDisponibilidad().equalsIgnoreCase("prestado")) {
                    alert.showAlert("Anuncio", "Préstamo diccionario", "El diccionario: " + codBarras
                            + ", se encuentra préstado a otro usuario.");
                } else if (dicccionario.getDisponibilidad().equalsIgnoreCase("vencido")) {
                    alert.showAlert("Anuncio", "Préstamo diccionario", "El dicionario: " + codBarras
                            + ", no ha sido devuelto por el usuario al que se le presto.");
                }
            } else {
                alert.showAlert("Anuncio", "Préstamo diccionario", "No se encuentró un diccionario "
                        + "asociado al código: " + codBarras + ".");
            }
        } catch (Exception e) {
            System.out.println("Error al generar el préstamo del diccionario a un estudiante");
        }
        
        return false; 
    }

    /**
     * el metódo realiza la construccción del e-mail al estudiante, notificandole
     * el préstamo del diccionario.
     * 
     * @param idProfesor
     * @param tituloRecurso
     * @param fechaPrestamo
     * @param fechaDevolucion 
     */
    public void notificarPrestamoEmail(String idProfesor, String tituloRecurso, Date fechaPrestamo, Date fechaDevolucion) {

    }
    
}

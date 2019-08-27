package moduloPrestamo.fabrica;

import moduloPrestamo.modelo.IPrestamo;
import moduloPrestamo.entitys.PrestamoDiccionarioProf;
import recursos.controllers.DiccionarioJpaController;
import recursos.entitys.Diccionario;
import moduloPrestamo.DAO.PrestamoDiccionarioDAOProf;
import general.vista.AlertBox;
import general.vista.IAlertBox;

/**
 * La clase se encarga gestionar el préstamo del diccionario al profesor.
 *
 * @author Julian
 * @creado
 * @author Miguel Fernández
 * @modificado 24/08/2019
 */
public class PrestamoDiccionarioProfFab implements IPrestamo {

    /**
     * constructor de la clase sin parámetros.
     */
    public PrestamoDiccionarioProfFab() {

    }

    /**
     * el metódo realiza el préstamo del dicionario al profesor.
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
                    PrestamoDiccionarioProf prestDicProf = new PrestamoDiccionarioProf();
                    prestDicProf.setCodBarraDiccionario(codBarras);
                    prestDicProf.setIdProfesor(codUsuario);
                    prestDicProf.setIdBibliotecario(idBibliotecario);
                    
                    PrestamoDiccionarioDAOProf prestDicDAOProf = new PrestamoDiccionarioDAOProf();

                    if (prestDicDAOProf.createDAO(prestDicProf)) {
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
                        + "asociado al código: " + codBarras  + ".");
            }
        } catch (Exception e) {
            System.out.println("Error al generar el préstamo del diccionario a un profesor");
        }

        return false; 
    }

    /**
     * el metódo realiza la construccción del e-mail al profesor, notificandole
     * el préstamo del diccioanrio.
     *
     * @param idProfesor
     * @param tituloRecurso
     * @param fechaPrestamo
     * @param fechaDevolucion
     */
    public void notificarPrestamoEmail(String idProfesor, String tituloRecurso, java.util.Date fechaPrestamo, java.util.Date fechaDevolucion) {

    }
    
}

package moduloPrestamo.fabrica;

import moduloPrestamo.entitys.PrestamoEnciclopediaProf;
import recursos1.controllers.EnciclopediaJpaController;
import recursos1.entitys.Enciclopedia;
import java.sql.Date;
import moduloPrestamo.DAO.PrestamoEnciclopediaDAOProf;
import vista.AlertBox;
import vista.IAlertBox;

/**
 * clase que se encarga gestionar el préstamo de la enciclopedia al estudiante.
 *
 * @author Julian
 * @creado
 * @author Miguel Fernández
 * @modificado 24/08/2019
 */
public class PrestamoEnciclopediaProfFab implements IPrestamo {

    /**
     * constructor de la clase sin parámetros.
     */
    public PrestamoEnciclopediaProfFab() {

    }

    /**
     * el metódo realiza el préstamo de la encilopedia al profesor.
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
            EnciclopediaJpaController control = new EnciclopediaJpaController();
            Enciclopedia enciclopedia = control.findEnciclopedia(codBarras);
            
            if (enciclopedia != null) {
                if (enciclopedia.getDisponibilidad().equalsIgnoreCase("disponible")) {
                    PrestamoEnciclopediaProf presEncProf = new PrestamoEnciclopediaProf();
                    presEncProf.setCodBarraEnciclopedia(codBarras);
                    presEncProf.setIdProfesor(codUsuario);
                    presEncProf.setIdBibliotecario(idBibliotecario);
                    
                    PrestamoEnciclopediaDAOProf presEncDAOProf = new PrestamoEnciclopediaDAOProf();

                    if (presEncDAOProf.createDAO(presEncProf)) {
                        enciclopedia.setDisponibilidad("prestado");
                        control.edit(enciclopedia);
                        
                        //espacio para el envio del correo
                        
                        return true; 
                    }
                } else if (enciclopedia.getDisponibilidad().equalsIgnoreCase("prestado")) {
                    alert.showAlert("Anuncio", "Préstamo enciclopedia", "La encilopedia: " + codBarras
                            + ", se encuentra préstado a otro usuario.");
                } else if (enciclopedia.getDisponibilidad().equalsIgnoreCase("vencido")) {
                    alert.showAlert("Anuncio", "Préstamo enciclopedia", "La enciclopedia: " + codBarras
                            + ", no ha sido devuelta por el usuario al que se le presto.");
                }
            } else {
                alert.showAlert("Anuncio", "Préstamo enciclopedia", "No se encuentró una encilopedia "
                        + "asociada al código: " + codBarras + ".");
            }
        } catch (Exception e) {
            System.out.println("Error al generar el préstamo de la enciclopedia a un profesor");
        }
        
        return false; 
    }

    /**
     * el metódo realiza la construccción del e-mail al profesor,
     * notificandole el préstamo de la enciclopedia.
     * 
     * @param idProfesor
     * @param tituloRecurso
     * @param fechaPrestamo
     * @param fechaDevolucion 
     */
    public void notificarPrestamoEmail(String idProfesor, String tituloRecurso, Date fechaPrestamo, Date fechaDevolucion) {

    }
    
}

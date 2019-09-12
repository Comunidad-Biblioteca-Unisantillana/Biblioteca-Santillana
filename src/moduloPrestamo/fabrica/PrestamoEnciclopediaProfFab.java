package moduloPrestamo.fabrica;

import general.modelo.NotificacionEmail;
import moduloPrestamo.modelo.IPrestamo;
import moduloPrestamo.entitys.PrestamoEnciclopediaProf;
import recursos.controllers.EnciclopediaJpaController;
import recursos.entitys.Enciclopedia;
import java.sql.Date;
import moduloPrestamo.DAO.PrestamoEnciclopediaDAOProf;
import general.vista.AlertBox;
import general.vista.IAlertBox;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import usuarios.control.ProfesorJpaController;
import usuarios.entitys.Profesor;

/**
 * clase que se encarga gestionar el préstamo de la enciclopedia al estudiante.
 *
 * @author Julian
 * @creado:
 * @author Miguel Fernández
 * @modificado: 07/09/2019
 */
public class PrestamoEnciclopediaProfFab implements IPrestamo {

    /**
     * constructor de la clase sin parámetros.
     */
    public PrestamoEnciclopediaProfFab() {

    }

    /**
     * el método realiza el préstamo de la encilopedia al profesor.
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
                        notificarPrestamoEmail(codUsuario, enciclopedia);
                        
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
     * el método realiza concatenación de los datos necesarios para la
     * construcción del e-mail al profesor, notificandole del préstamo de la
     * enciclopedia.
     *
     * @param idProfesor
     * @param enciclopedia
     */
    private void notificarPrestamoEmail(String idProfesor, Enciclopedia enciclopedia) {
        ProfesorJpaController profesorJpaController = new ProfesorJpaController();
        Profesor profesor = profesorJpaController.findProfesor(idProfesor);

        PrestamoEnciclopediaDAOProf prestEncDAOProf = new PrestamoEnciclopediaDAOProf();
        PrestamoEnciclopediaProf prestEncProf = prestEncDAOProf.readDAO(prestEncDAOProf.readCodigoDAO(enciclopedia.getCodbarraenciclopedia()));

        DateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");

        String datos = profesor.getApellidos().toUpperCase() + ";"
                + profesor.getNombres().toUpperCase() + ";"
                + "Identificación: " + idProfesor + ";"
                + enciclopedia.getTitulo() + ";"
                + enciclopedia.getCodbarraenciclopedia() + ";"
                + formatoFecha.format((Date) prestEncProf.getFechaPrestamo()) + ";"
                + formatoFecha.format((Date) prestEncProf.getFechaDevolucion()) + ";"
                + "referencia;"
                + profesor.getCorreoelectronico();

        NotificacionEmail notificacionEmail = new NotificacionEmail(datos, "mensajePrestamo");
        notificacionEmail.start();
    }
    
}

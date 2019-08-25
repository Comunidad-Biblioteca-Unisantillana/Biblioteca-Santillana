package moduloPrestamo.fabrica;

import moduloPrestamo.entitys.PrestamoEnciclopediaEst;
import recursos1.controllers.EnciclopediaJpaController;
import recursos1.entitys.Enciclopedia;
import java.sql.Date;
import moduloPrestamo.DAO.PrestamoEnciclopediaDAOEst;
import vista.AlertBox;
import vista.IAlertBox;

/**
 * La clase se encarga gestionar el préstamo de la enciclopedia al estudiante.
 *
 * @author Julian
 * @creado
 * @author Miguel Fernández
 * @modificado 24/08/2019
 */
public class PrestamoEnciclopediaEstFab implements IPrestamo {

    /**
     * constructor de la clase sin parámetros.
     */
    public PrestamoEnciclopediaEstFab() {

    }

    /**
     * el metódo realiza el préstamo de la encilopedia al estudiante.
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
                    PrestamoEnciclopediaEst presEncEst = new PrestamoEnciclopediaEst();
                    presEncEst.setCodBarraEnciclopedia(codBarras);
                    presEncEst.setCodEstudiante(codUsuario);
                    presEncEst.setIdBibliotecario(idBibliotecario);

                    PrestamoEnciclopediaDAOEst presEncDAOEst = new PrestamoEnciclopediaDAOEst();
                    if (presEncDAOEst.createDAO(presEncEst)) {
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
                        + "sociada al código: " + codBarras + ".");
            }
        } catch (Exception e) {
            System.out.println("Error al generar el préstamo de la enciclopedia a un estudiante");
        }

        return false; 
    }

    /**
     * el metódo realiza la construccción del e-mail al estudiante,
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

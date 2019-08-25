package moduloPrestamo.fabrica;

import moduloPrestamo.entitys.PrestamoRevistaEst;
import recursos1.controllers.RevistaJpaController;
import recursos1.entitys.Revista;
import java.sql.Date;
import moduloPrestamo.DAO.PrestamoRevistaDAOEst;
import vista.AlertBox;
import vista.IAlertBox;

/**
 * la clase se encarga gestionar el préstamo de la revista al estudiante.
 *
 * @author Julian
 * @creado
 * @author Miguel Fernández
 * @modificado 23/08/2019
 */
public class PrestamoRevistaEstFab implements IPrestamo {

    /**
     * constructor de la clase sin parámetros.
     */
    public PrestamoRevistaEstFab() {
    }

    /**
     * el metódo realiza el préstamo de la revista al estudiante.
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
            RevistaJpaController control = new RevistaJpaController();
            Revista revista = control.findRevista(codBarras);

            if (revista != null) {
                if (revista.getDisponibilidad().equalsIgnoreCase("disponible")) {
                    PrestamoRevistaEst presRevEst = new PrestamoRevistaEst();
                    presRevEst.setCodBarraRevista(codBarras);
                    presRevEst.setCodEstudiante(codUsuario);
                    presRevEst.setIdBibliotecario(idBibliotecario);

                    PrestamoRevistaDAOEst presRevDAOEst = new PrestamoRevistaDAOEst();

                    if (presRevDAOEst.createDAO(presRevEst)) {
                        revista.setDisponibilidad("prestado");
                        control.edit(revista);

                        //espacio para el envio del correo
                        
                        return true; 
                    }
                } else if (revista.getDisponibilidad().equalsIgnoreCase("prestado")) {
                    alert.showAlert("Anuncio", "Préstamo revista", "La revista: " + codBarras
                            + ", se encuentra préstada a otro usuario.");
                } else if (revista.getDisponibilidad().equalsIgnoreCase("vencido")) {
                    alert.showAlert("Anuncio", "Préstamo revista", "La revista: " + codBarras
                            + ", no ha sido devuelta por el usuario al que se le presto.");
                }
            } else {
                alert.showAlert("Anuncio", "Préstamo revista", "No se encuentró una revista "
                        + "asociada al código: " + codBarras + ".");
            }
        } catch (Exception e) {
            System.out.println("Error al generar el préstamo de la revista a un estudiante");
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

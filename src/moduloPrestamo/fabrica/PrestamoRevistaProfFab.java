package moduloPrestamo.fabrica;

import general.modelo.NotificacionEmail;
import moduloPrestamo.modelo.IPrestamo;
import moduloPrestamo.entitys.PrestamoRevistaProf;
import recursos.controllers.RevistaJpaController;
import recursos.entitys.Revista;
import moduloPrestamo.DAO.PrestamoRevistaDAOProf;
import general.vista.AlertBox;
import general.vista.IAlertBox;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import usuarios.control.ProfesorJpaController;
import usuarios.entitys.Profesor;

/**
 * La clase se encarga gestionar el préstamo de la revista al estudiante.
 *
 * @author Julian
 * @creado
 * @author Miguel Fernández
 * @modificado 23/08/2019
 */
public class PrestamoRevistaProfFab implements IPrestamo {

    /**
     * constructor de la clase sin parámetros.
     */
    public PrestamoRevistaProfFab() {

    }

    /**
     * el método realiza el préstamo de la al profesor.
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
                    PrestamoRevistaProf presRevProf = new PrestamoRevistaProf();
                    presRevProf.setCodBarraRevista(codBarras);
                    presRevProf.setIdProfesor(codUsuario);
                    presRevProf.setIdBibliotecario(idBibliotecario);

                    PrestamoRevistaDAOProf presRevDAOProf = new PrestamoRevistaDAOProf();

                    if (presRevDAOProf.createDAO(presRevProf)) {
                        revista.setDisponibilidad("prestado");
                        control.edit(revista);
                        notificarPrestamoEmail(codUsuario, revista);
                        
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
            System.out.println("Error al generar el préstamo de la revista a un profesor");
        }

        return false;
    }

    /**
     * el método realiza concatenación de los datos necesarios para la
     * construcción del e-mail al profesor, notificandole del préstamo del
     * revista.
     *
     * @param idProfesor
     * @param revista
     */
    private void notificarPrestamoEmail(String idProfesor, Revista revista) {
        ProfesorJpaController profesorJpaController = new ProfesorJpaController();
        Profesor profesor = profesorJpaController.findProfesor(idProfesor);

        PrestamoRevistaDAOProf prestRevDAOProf = new PrestamoRevistaDAOProf();
        PrestamoRevistaProf prestRevProf = prestRevDAOProf.readDAO(prestRevDAOProf.readCodigoDAO(revista.getCodbarrarevista()));

        DateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");

        String datos = profesor.getApellidos().toUpperCase() + ";"
                + profesor.getNombres().toUpperCase() + ";"
                + "Identificación: " + idProfesor + ";"
                + revista.getTitulo()+ ";"
                + revista.getCodbarrarevista() + ";"
                + formatoFecha.format((Date) prestRevProf.getFechaPrestamo()) + ";"
                + formatoFecha.format((Date) prestRevProf.getFechaDevolucion()) + ";"
                + "hemeroteca;"
                + profesor.getCorreoelectronico();

        NotificacionEmail em = new NotificacionEmail();
        em.gestionarNotificacion(datos, "mensajePrestamo");
    }

}

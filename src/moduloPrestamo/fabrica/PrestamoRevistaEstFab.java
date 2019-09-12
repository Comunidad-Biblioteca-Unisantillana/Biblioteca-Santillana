package moduloPrestamo.fabrica;

import general.modelo.NotificacionEmail;
import moduloPrestamo.modelo.IPrestamo;
import moduloPrestamo.entitys.PrestamoRevistaEst;
import recursos.controllers.RevistaJpaController;
import recursos.entitys.Revista;
import moduloPrestamo.DAO.PrestamoRevistaDAOEst;
import general.vista.AlertBox;
import general.vista.IAlertBox;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import usuarios.control.EstudianteJpaController;
import usuarios.entitys.Estudiante;

/**
 * la clase se encarga gestionar el préstamo de la revista al estudiante.
 *
 * @author Julian
 * @creado:
 * @author Miguel Fernández
 * @modificado: 07/09/2019
 */
public class PrestamoRevistaEstFab implements IPrestamo {

    /**
     * constructor de la clase sin parámetros.
     */
    public PrestamoRevistaEstFab() {
    }

    /**
     * el método realiza el préstamo de la revista al estudiante.
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
            System.out.println("Error al generar el préstamo de la revista a un estudiante");
        }

        return false;
    }

    /**
     * el método realiza concatenación de los datos necesarios para la
     * construcción del e-mail al estudiante, notificandole del préstamo de la
     * revista.
     *
     * @param codEstudiante
     * @param revista
     */
    private void notificarPrestamoEmail(String codEstudiante, Revista revista) {
        EstudianteJpaController estudianteJpaController = new EstudianteJpaController();
        Estudiante estudiante = estudianteJpaController.findEstudiante(codEstudiante);

        PrestamoRevistaDAOEst prestRevDAOEst = new PrestamoRevistaDAOEst();
        PrestamoRevistaEst prestRevEst = prestRevDAOEst.readDAO(prestRevDAOEst.readCodigoDAO(revista.getCodbarrarevista()));

        DateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");

        String datos = estudiante.getApellido().toUpperCase() + ";"
                + estudiante.getNombre().toUpperCase() + ";"
                + "Código: " + codEstudiante + ";"
                + revista.getTitulo() + ";"
                + revista.getCodbarrarevista() + ";"
                + formatoFecha.format((Date) prestRevEst.getFechaPrestamo()) + ";"
                + formatoFecha.format((Date) prestRevEst.getFechaDevolucion()) + ";"
                + "hemeroteca;"
                + estudiante.getCorreoelectronico();

        NotificacionEmail notificacionEmail = new NotificacionEmail(datos, "mensajePrestamo");
        notificacionEmail.start();
    }

}

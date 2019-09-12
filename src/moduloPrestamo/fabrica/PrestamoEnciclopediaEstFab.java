package moduloPrestamo.fabrica;

import general.modelo.NotificacionEmail;
import moduloPrestamo.modelo.IPrestamo;
import moduloPrestamo.entitys.PrestamoEnciclopediaEst;
import recursos.controllers.EnciclopediaJpaController;
import recursos.entitys.Enciclopedia;
import moduloPrestamo.DAO.PrestamoEnciclopediaDAOEst;
import general.vista.AlertBox;
import general.vista.IAlertBox;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import usuarios.control.EstudianteJpaController;
import usuarios.entitys.Estudiante;

/**
 * La clase se encarga gestionar el préstamo de la enciclopedia al estudiante.
 *
 * @author Julian
 * @creado:
 * @author Miguel Fernández
 * @modificado: 07/09/2019
 */
public class PrestamoEnciclopediaEstFab implements IPrestamo {

    /**
     * constructor de la clase sin parámetros.
     */
    public PrestamoEnciclopediaEstFab() {

    }

    /**
     * el método realiza el préstamo de la encilopedia al estudiante.
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
                        + "sociada al código: " + codBarras + ".");
            }
        } catch (Exception e) {
            System.out.println("Error al generar el préstamo de la enciclopedia a un estudiante");
        }

        return false;
    }

    /**
     * el método realiza concatenación de los datos necesarios para la
     * construcción del e-mail al estudiante, notificandole del préstamo de la
     * enciclopedia.
     *
     * @param codEstudiante
     * @param enciclopedia
     */
    private void notificarPrestamoEmail(String codEstudiante, Enciclopedia enciclopedia) {
        EstudianteJpaController estudianteJpaController = new EstudianteJpaController();
        Estudiante estudiante = estudianteJpaController.findEstudiante(codEstudiante);

        PrestamoEnciclopediaDAOEst prestEncDAOEst = new PrestamoEnciclopediaDAOEst();
        PrestamoEnciclopediaEst prestEncEst = prestEncDAOEst.readDAO(prestEncDAOEst.readCodigoDAO(enciclopedia.getCodbarraenciclopedia()));

        DateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");

        String datos = estudiante.getApellido().toUpperCase() + ";"
                + estudiante.getNombre().toUpperCase() + ";"
                + "Código: " + codEstudiante + ";"
                + enciclopedia.getTitulo() + ";"
                + enciclopedia.getCodbarraenciclopedia() + ";"
                + formatoFecha.format((Date) prestEncEst.getFechaPrestamo()) + ";"
                + formatoFecha.format((Date) prestEncEst.getFechaDevolucion()) + ";"
                + "referencia;"
                + estudiante.getCorreoelectronico();

        NotificacionEmail notificacionEmail = new NotificacionEmail(datos, "mensajePrestamo");
        notificacionEmail.start();
    }

}

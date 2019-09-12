package moduloPrestamo.fabrica;

import general.modelo.NotificacionEmail;
import moduloPrestamo.modelo.IPrestamo;
import moduloPrestamo.entitys.PrestamoPeriodicoProf;
import recursos.controllers.PeriodicoJpaController;
import recursos.entitys.Periodico;
import moduloPrestamo.DAO.PrestamoPeriodicoDAOProf;
import general.vista.AlertBox;
import general.vista.IAlertBox;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import usuarios.control.ProfesorJpaController;
import usuarios.entitys.Profesor;

/**
 * La clase se encarga gestionar el préstamo de un periódico al profesor.
 *
 * @author Julian
 * @creado:
 * @author Miguel Fernández
 * @modificado: 07/09/2019
 */
public class PrestamoPeriodicoProfFab implements IPrestamo {

    /**
     * constructor de la clase sin parámetros.
     */
    public PrestamoPeriodicoProfFab() {

    }

    /**
     * el método realiza el préstamo de un periódico al profesor.
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
            PeriodicoJpaController control = new PeriodicoJpaController();
            Periodico periodico = control.findPeriodico(codBarras);

            if (periodico != null) {
                if (periodico.getDisponibilidad().equalsIgnoreCase("disponible")) {
                    PrestamoPeriodicoProf presPerProf = new PrestamoPeriodicoProf();
                    presPerProf.setCodBarraPeriodico(codBarras);
                    presPerProf.setIdProfesor(codUsuario);
                    presPerProf.setIdBibliotecario(idBibliotecario);

                    PrestamoPeriodicoDAOProf presPerDAOProf = new PrestamoPeriodicoDAOProf();

                    if (presPerDAOProf.createDAO(presPerProf)) {
                        periodico.setDisponibilidad("prestado");
                        control.edit(periodico);
                        notificarPrestamoEmail(codUsuario, periodico);
                        
                        return true;
                    }
                } else if (periodico.getDisponibilidad().equalsIgnoreCase("prestado")) {
                    alert.showAlert("Anuncio", "Préstamo periódico", "El periódico: " + codBarras
                            + ", se encuentra préstado a otro usuario.");
                } else if (periodico.getDisponibilidad().equalsIgnoreCase("vencido")) {
                    alert.showAlert("Anuncio", "Préstamo préstamo", "El periódico: " + codBarras
                            + ", no ha sido devuelto por el usuario al que se le presto.");
                }
            } else {
                alert.showAlert("Anuncio", "Préstamo periódico", "No se encuentró un periódico "
                        + "asociado al código: " + codBarras + ".");
            }
        } catch (Exception e) {
            System.out.println("Error al generar el préstamo del periódico a un profesor");
        }
        return false;
    }

    /**
     * el método realiza concatenación de los datos necesarios para la
     * construcción del e-mail al profesor, notificandole del préstamo del
     * periódico.
     *
     * @param idProfesor
     * @param periodico
     */
    private void notificarPrestamoEmail(String idProfesor, Periodico periodico) {
        ProfesorJpaController profesorJpaController = new ProfesorJpaController();
        Profesor profesor = profesorJpaController.findProfesor(idProfesor);

        PrestamoPeriodicoDAOProf prestPerDAOProf = new PrestamoPeriodicoDAOProf();
        PrestamoPeriodicoProf prestPerProf = prestPerDAOProf.readDAO(prestPerDAOProf.readCodigoDAO(periodico.getCodbarraperiodico()));

        DateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");

        String datos = profesor.getApellidos().toUpperCase() + ";"
                + profesor.getNombres().toUpperCase() + ";"
                + "Identificación: " + idProfesor + ";"
                + periodico.getNombreperiodico() + ";"
                + periodico.getCodbarraperiodico() + ";"
                + formatoFecha.format((Date) prestPerProf.getFechaPrestamo()) + ";"
                + formatoFecha.format((Date) prestPerProf.getFechaDevolucion()) + ";"
                + "hemeroteca;"
                + profesor.getCorreoelectronico();

        NotificacionEmail notificacionEmail = new NotificacionEmail(datos, "mensajePrestamo");
        notificacionEmail.start();
    }

}

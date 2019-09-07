package moduloPrestamo.fabrica;

import general.modelo.NotificacionEmail;
import moduloPrestamo.modelo.IPrestamo;
import moduloPrestamo.entitys.PrestamoPeriodicoEst;
import recursos.controllers.PeriodicoJpaController;
import recursos.entitys.Periodico;
import moduloPrestamo.DAO.PrestamoPeriodicoDAOEst;
import general.vista.AlertBox;
import general.vista.IAlertBox;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import usuarios.control.EstudianteJpaController;
import usuarios.entitys.Estudiante;

/**
 * La clase se encarga gestionar el préstamo de un periódico al estudiante.
 *
 * @author Julian
 * @creado
 * @author Miguel Fernández
 * @modificado 07/09/2019
 */
public class PrestamoPeriodicoEstFab implements IPrestamo {

    /**
     * constructor de la clase sin parámetros.
     */
    public PrestamoPeriodicoEstFab() {
    }

    /**
     * el método realiza el préstamo de un periódico al estudiante.
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
                    PrestamoPeriodicoEst presPerEst = new PrestamoPeriodicoEst();
                    presPerEst.setCodBarraPeriodico(codBarras);
                    presPerEst.setCodEstudiante(codUsuario);
                    presPerEst.setIdBibliotecario(idBibliotecario);
                    
                    PrestamoPeriodicoDAOEst presPerDAOEst = new PrestamoPeriodicoDAOEst();

                    if (presPerDAOEst.createDAO(presPerEst)) {
                        periodico.setDisponibilidad("prestado");
                        control.edit(periodico);
                        notificarPrestamoEmail(codUsuario, periodico);
                        
                        return true; 
                    }
                } else if (periodico.getDisponibilidad().equalsIgnoreCase("prestado")) {
                    alert.showAlert("Anuncio", "Préstamo periódico", "El periódico: " + codBarras
                            + ", se encuentra préstado a otro usuario.");
                } else if (periodico.getDisponibilidad().equalsIgnoreCase("vencido")) {
                    alert.showAlert("Anuncio", "Préstamo periódico", "El periódico: " + codBarras
                            + ", no ha sido devuelto por el usuario al que se le presto.");
                }
            } else {
                alert.showAlert("Anuncio", "Préstamo periódico", "No se encuentró un periódico "
                        + "asociado al código: " + codBarras + ".");
            }
        } catch (Exception e) {
            System.out.println("Error al generar el préstamo del periódico a un estudiante");
        }

        return false; 
    }

    /**
     * el método realiza concatenación de los datos necesarios para la
     * construcción del e-mail al estudiante, notificandole del préstamo de la
     * periódico.
     *
     * @param codEstudiante
     * @param periodico
     */
    private void notificarPrestamoEmail(String codEstudiante, Periodico periodico) {
        EstudianteJpaController estudianteJpaController = new EstudianteJpaController();
        Estudiante estudiante = estudianteJpaController.findEstudiante(codEstudiante);

        PrestamoPeriodicoDAOEst prestPerDAOEst = new PrestamoPeriodicoDAOEst();
        PrestamoPeriodicoEst prestPerEst = prestPerDAOEst.readDAO(prestPerDAOEst.readCodigoDAO(periodico.getCodbarraperiodico()));

        DateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");

        String datos = estudiante.getApellido().toUpperCase() + ";"
                + estudiante.getNombre().toUpperCase() + ";"
                + "Código: " + codEstudiante + ";"
                + periodico.getNombreperiodico()+ ";"
                + periodico.getCodbarraperiodico() + ";"
                + formatoFecha.format((Date) prestPerEst.getFechaPrestamo()) + ";"
                + formatoFecha.format((Date) prestPerEst.getFechaDevolucion()) + ";"
                + "hemeroteca;"
                + estudiante.getCorreoelectronico();

        NotificacionEmail em = new NotificacionEmail();
        em.gestionarNotificacion(datos, "mensajePrestamo");
    }

}

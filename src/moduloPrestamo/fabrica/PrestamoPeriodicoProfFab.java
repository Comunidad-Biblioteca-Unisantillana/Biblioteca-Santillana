package moduloPrestamo.fabrica;

import moduloPrestamo.modelo.IPrestamo;
import moduloPrestamo.entitys.PrestamoPeriodicoProf;
import recursos.controllers.PeriodicoJpaController;
import recursos.entitys.Periodico;
import java.sql.Date;
import moduloPrestamo.DAO.PrestamoPeriodicoDAOProf;
import general.vista.AlertBox;
import general.vista.IAlertBox;

/**
 * La clase se encarga gestionar el préstamo de un periódico al profesor.
 *
 * @author Julian
 * @creado
 * @author Miguel Fernández
 * @modificado 23/08/2019
 */
public class PrestamoPeriodicoProfFab implements IPrestamo {

    /**
     * constructor de la clase sin parámetros.
     */
    public PrestamoPeriodicoProfFab() {

    }

    /**
     * el metódo realiza el préstamo de un periódico al profesor.
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

                        //espacio para el envio del correo
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
     * el metódo realiza la construccción del e-mail al profesor, notificandole
     * el préstamo de un periódico.
     *
     * @param idProfesor
     * @param tituloRecurso
     * @param fechaPrestamo
     * @param fechaDevolucion
     */
    public void notificarPrestamoEmail(String idProfesor, String tituloRecurso, Date fechaPrestamo, Date fechaDevolucion) {

    }

}

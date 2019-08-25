package moduloPrestamo.fabrica;

import moduloPrestamo.entitys.PrestamoPeriodicoEst;
import recursos1.controllers.PeriodicoJpaController;
import recursos1.entitys.Periodico;
import java.sql.Date;
import moduloPrestamo.DAO.PrestamoPeriodicoDAOEst;
import vista.AlertBox;
import vista.IAlertBox;

/**
 * La clase se encarga gestionar el préstamo de un periódico al estudiante.
 *
 * @author Julian
 * @creado
 * @author Miguel Fernández
 * @modificado 24/08/2019
 */
public class PrestamoPeriodicoEstFab implements IPrestamo {

    /**
     * constructor de la clase sin parámetros.
     */
    public PrestamoPeriodicoEstFab() {
    }

    /**
     * el metódo realiza el préstamo de un periódico al estudiante.
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

                        //espacio para enviar correo
                        
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
                alert.showAlert("Anuncio", "Préstamo periódico", "No se encuentró un periódico asociado al código: " + codBarras);
            }
        } catch (Exception e) {
            System.out.println("Error al generar el préstamo del periódico a un estudiante");
        }

        return false; 
    }

    /**
     * el metódo realiza la construccción del e-mail al estudiante,
     * notificandole el préstamo de un periódico.
     *
     * @param idProfesor
     * @param tituloRecurso
     * @param fechaPrestamo
     * @param fechaDevolucion
     */
    public void notificarPrestamoEmail(String idProfesor, String tituloRecurso, Date fechaPrestamo, Date fechaDevolucion) {

    }

}

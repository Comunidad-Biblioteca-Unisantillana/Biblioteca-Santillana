package moduloPrestamo.fabrica;

import moduloPrestamo.entitys.PrestamoPeriodicoProf;
import recursos1.controllers.PeriodicoJpaController;
import recursos1.entitys.Periodico;
import java.sql.Date;
import moduloPrestamo.DAO.PrestamoPeriodicoDAOProf;
import vista.AlertBox;
import vista.IAlertBox;

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
                } else {
                    alert.showAlert("Anuncio", "Prestamo periodico", "El periodico no se encuentra disponible");
                }
            } else {
                alert.showAlert("Anuncio", "Prestamo periodico", "Ningun periodico tiene este codigo de barra");
            }
        } catch (Exception e) {
            System.out.println("error al generar el prestamo del periodico de un profesor");
        }
        return false; 
    }

    /**
     * el metódo realiza la construccción del e-mail al profesor,
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

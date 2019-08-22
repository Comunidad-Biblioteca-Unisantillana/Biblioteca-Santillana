package moduloPrestamo.fabrica;

import moduloPrestamo.entitys.PrestamoPeriodicoProf;
import recursos1.controllers.PeriodicoJpaController;
import recursos1.entitys.Periodico;
import java.sql.Date;
import modelo.ServicioFecha;
import moduloPrestamo.DAO.PrestamoPeriodicoDAOProf;
import moduloPrestamo.IPrestamo;
import vista.AlertBox;
import vista.IAlertBox;

/**
 *
 * @author Julian
 */
public class PrestamoPeriodicoProfFab implements IPrestamo {

    public PrestamoPeriodicoProfFab() {

    }

    @Override
    public boolean ejecutarPrestamo(String codBarras, String codUsuario, String idBibliotecario) {
        IAlertBox alert = new AlertBox();
        try {
            PeriodicoJpaController control = new PeriodicoJpaController();
            Periodico periodico = control.findPeriodico(codBarras);
            if (periodico != null) {
                if (periodico.getDisponibilidad().equalsIgnoreCase("disponible")) {

                    java.util.Date fechaActual = new java.util.Date();
                    java.util.Date fechaDevolucion = ServicioFecha.sumarDiasAFecha(fechaActual, 0);

                    PrestamoPeriodicoProf presPerProf = new PrestamoPeriodicoProf(codBarras, codUsuario, idBibliotecario,
                            new Date(fechaActual.getTime()), new Date(fechaDevolucion.getTime()));
                    PrestamoPeriodicoDAOProf presPerDAOProf = new PrestamoPeriodicoDAOProf();
                    
                    if (presPerDAOProf.createDAO(presPerProf)) {
                        System.out.println("Cambiando disponibilidad del periodico...");
                        
                        periodico.setDisponibilidad("prestado");
                        control.edit(periodico);
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

    public void notificarPrestamoEmail(String idProfesor, String tituloRecurso, java.util.Date fechaPrestamo, java.util.Date fechaDevolucion) {

    }

}

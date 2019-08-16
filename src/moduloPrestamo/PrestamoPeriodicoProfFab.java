package moduloPrestamo;

import controllers.PeriodicoJpaController;
import entitys.Periodico;
import java.sql.Date;
import modelo.QueryRecurso;
import modelo.ServicioFecha;
import moduloPrestamoDAO.PrestamoPeriodicoDAOProf;

/**
 *
 * @author Julian
 */
public class PrestamoPeriodicoProfFab implements IPrestamo {

    public PrestamoPeriodicoProfFab() {

    }

    @Override
    public boolean ejecutarPrestamo(String codBarras, String codUsuario, String idBibliotecario) {
        try {
            Periodico periodico = QueryRecurso.consultarPeriodico(codBarras);
            if (periodico != null) {
                if (periodico.getDisponibilidad().equalsIgnoreCase("disponible")) {

                    java.util.Date fechaActual = new java.util.Date();
                    java.util.Date fechaDevolucion = ServicioFecha.sumarDiasAFecha(fechaActual, 0);

                    PrestamoPeriodicoProf presPerProf = new PrestamoPeriodicoProf(codBarras, codUsuario, idBibliotecario,
                            new Date(fechaActual.getTime()), new Date(fechaDevolucion.getTime()));
                    PrestamoPeriodicoDAOProf presPerDAOProf = new PrestamoPeriodicoDAOProf();
                    presPerDAOProf.createDAO(presPerProf);

                    System.out.println("Cambiando disponibilidad del periodico...");
                    PeriodicoJpaController control = new PeriodicoJpaController();
                    periodico.setDisponibilidad("prestado");
                    control.edit(periodico);
                    return true;
                } else {
                    System.out.println("el periodico no se encuentra disponible");
                }
            } else {
                System.out.println("Ningun periodico tiene este codigo de barra");
            }
        } catch (Exception e) {
            System.out.println("error al generar el prestamo del periodico de un profesor");
        }
        return false;
    }

    public void notificarPrestamoEmail(String idProfesor, String tituloRecurso, java.util.Date fechaPrestamo, java.util.Date fechaDevolucion) {

    }

}

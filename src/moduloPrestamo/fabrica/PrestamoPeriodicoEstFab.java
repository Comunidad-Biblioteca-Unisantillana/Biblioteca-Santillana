package moduloPrestamo.fabrica;

import moduloPrestamo.entitys.PrestamoPeriodicoEst;
import controllers.PeriodicoJpaController;
import entitys.Periodico;
import java.sql.Date;
import modelo.QueryRecurso;
import modelo.ServicioFecha;
import moduloPrestamo.DAO.PrestamoPeriodicoDAOEst;
import moduloPrestamo.IPrestamo;
import vista.AlertBox;
import vista.IAlertBox;

/**
 *
 * @author Julian
 */
public class PrestamoPeriodicoEstFab implements IPrestamo {

    public PrestamoPeriodicoEstFab() {
    }

    @Override
    public boolean ejecutarPrestamo(String codBarras, String codUsuario, String idBibliotecario) {
        IAlertBox alert = new AlertBox();
        try {
            Periodico periodico = QueryRecurso.consultarPeriodico(codBarras);
            if (periodico != null) {
                if (periodico.getDisponibilidad().equalsIgnoreCase("disponible")) {

                    java.util.Date fechaActual = new java.util.Date();
                    java.util.Date fechaDevolucion = ServicioFecha.sumarDiasAFecha(fechaActual, 0);

                    PrestamoPeriodicoEst presPerEst = new PrestamoPeriodicoEst(codBarras, codUsuario, idBibliotecario,
                            new Date(fechaActual.getTime()), new Date(fechaDevolucion.getTime()));
                    PrestamoPeriodicoDAOEst presPerDAOEst = new PrestamoPeriodicoDAOEst();
                    if (presPerDAOEst.createDAO(presPerEst)) {
                        System.out.println("Cambiando disponibilidad del periodico...");
                        PeriodicoJpaController control = new PeriodicoJpaController();
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
            System.out.println("error al generar el prestamo del periodico de un estudiante");
        }
        return false;
    }

    public void notificarPrestamoEmail(String idProfesor, String tituloRecurso, java.util.Date fechaPrestamo, java.util.Date fechaDevolucion) {

    }

}

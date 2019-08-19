package moduloPrestamo.fabrica;

import moduloPrestamo.entitys.PrestamoRevistaProf;
import controllers.RevistaJpaController;
import entitys.Revista;
import java.sql.Date;
import modelo.ServicioFecha;
import moduloPrestamo.DAO.PrestamoRevistaDAOProf;
import moduloPrestamo.IPrestamo;
import vista.AlertBox;
import vista.IAlertBox;

/**
 *
 * @author Julian
 */
public class PrestamoRevistaProfFab implements IPrestamo {

    public PrestamoRevistaProfFab() {

    }

    @Override
    public boolean ejecutarPrestamo(String codBarras, String codUsuario, String idBibliotecario) {
        IAlertBox alert = new AlertBox();
        try {
            RevistaJpaController control = new RevistaJpaController();
            Revista revista = control.findRevista(codBarras);
            if (revista != null) {
                if (revista.getDisponibilidad().equalsIgnoreCase("disponible")) {
                    java.util.Date fechaActual = new java.util.Date();
                    java.util.Date fechaDevolucion = ServicioFecha.sumarDiasAFecha(fechaActual, 0);

                    PrestamoRevistaProf presRevProf = new PrestamoRevistaProf(codBarras, codUsuario, idBibliotecario,
                            new Date(fechaActual.getTime()), new Date(fechaDevolucion.getTime()));
                    PrestamoRevistaDAOProf presRevDAOProf = new PrestamoRevistaDAOProf();
                    
                    if (presRevDAOProf.createDAO(presRevProf)) {
                        System.out.println("Cambiando disponibilidad de la revista...");
                        
                        revista.setDisponibilidad("prestado");
                        control.edit(revista);
                        return true;
                    }
                } else {
                    alert.showAlert("Anuncio", "Prestamo revista", "La revista no se encuentra disponible");
                }
            } else {
                alert.showAlert("Anuncio", "Prestamo revista", "Ninguna revista tiene este codigo de barra");
            }
        } catch (Exception e) {
            System.out.println("error al generar el prestamo de la revista de un profesor");
        }
        return false;
    }

    public void notificarPrestamoEmail(String idProfesor, String tituloRecurso, java.util.Date fechaPrestamo, java.util.Date fechaDevolucion) {

    }

}

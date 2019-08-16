package moduloPrestamo;

import controllers.RevistaJpaController;
import entitys.Revista;
import java.sql.Date;
import modelo.QueryRecurso;
import modelo.ServicioFecha;
import moduloPrestamoDAO.PrestamoRevistaDAOEst;

/**
 *
 * @author Julian
 */
public class PrestamoRevistaEstFab implements IPrestamo {

    public PrestamoRevistaEstFab() {
    }

    @Override
    public boolean ejecutarPrestamo(String codBarras, String codUsuario, String idBibliotecario) {
        System.out.println("1");
        try {
            Revista revista = QueryRecurso.consultarRevista(codBarras);
            System.out.println("1");
            if (revista != null) {
                System.out.println("1");
                if (revista.getDisponibilidad().equalsIgnoreCase("disponible")) {
                    System.out.println("1");
                    java.util.Date fechaActual = new java.util.Date();
                    java.util.Date fechaDevolucion = ServicioFecha.sumarDiasAFecha(fechaActual, 0);
                    System.out.println("1");
                    PrestamoRevistaEst presRevEst = new PrestamoRevistaEst(codBarras, codUsuario, idBibliotecario,
                            new Date(fechaActual.getTime()), new Date(fechaDevolucion.getTime()));

                    PrestamoRevistaDAOEst presRevDAOEst = new PrestamoRevistaDAOEst();
                    presRevDAOEst.createDAO(presRevEst);

                    System.out.println("Cambiando disponibilidad de la revista...");
                    RevistaJpaController control = new RevistaJpaController();
                    revista.setDisponibilidad("prestado");
                    control.edit(revista);
                    return true;
                } else {
                    System.out.println("la revista no se encuentra disponible");
                }
            } else {
                System.out.println("Ninguna revista tiene este codigo de barra");
            }
        } catch (Exception e) {
            System.out.println("error al generar el prestamo de la revista de un estudiante");
        }
        return false;
    }

    public void notificarPrestamoEmail(String idProfesor, String tituloRecurso, java.util.Date fechaPrestamo, java.util.Date fechaDevolucion) {

    }
}

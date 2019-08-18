package moduloPrestamo.fabrica;

import moduloPrestamo.entitys.PrestamoEnciclopediaEst;
import controllers.EnciclopediaJpaController;
import entitys.Enciclopedia;
import java.sql.Date;
import modelo.QueryRecurso;
import modelo.ServicioFecha;
import moduloPrestamo.DAO.PrestamoEnciclopediaDAOEst;
import moduloPrestamo.IPrestamo;

/**
 *
 * @author Julian
 */
public class PrestamoEnciclopediaEstFab implements IPrestamo {

    public PrestamoEnciclopediaEstFab() {

    }

    @Override
    public boolean ejecutarPrestamo(String codBarras, String codUsuario, String idBibliotecario) {
        try {
            Enciclopedia enciclopedia = QueryRecurso.consultarEnciclopedia(codBarras);
            if (enciclopedia != null) {
                if (enciclopedia.getDisponibilidad().equalsIgnoreCase("disponible")) {

                    java.util.Date fechaActual = new java.util.Date();
                    java.util.Date fechaDevolucion = ServicioFecha.sumarDiasAFecha(fechaActual, 1);

                    PrestamoEnciclopediaEst presEncEst = new PrestamoEnciclopediaEst(codBarras, codUsuario, idBibliotecario,
                            new Date(fechaActual.getTime()), new Date(fechaDevolucion.getTime()));

                    PrestamoEnciclopediaDAOEst presEncDAOEst = new PrestamoEnciclopediaDAOEst();
                    if (presEncDAOEst.createDAO(presEncEst)) {
                        System.out.println("Cambiando disponibilidad de la enciclopedia...");
                        EnciclopediaJpaController control = new EnciclopediaJpaController();
                        enciclopedia.setDisponibilidad("prestado");
                        control.edit(enciclopedia);
                    }
                    return true;
                } else {
                    System.out.println("la enciclopedia no se encuentra disponible");
                }
            } else {
                System.out.println("Ninguna enciclopedia tiene este codigo de barra");
            }
        } catch (Exception e) {
            System.out.println("error al generar el prestamo de enciclopedio de un estudiante");
        }
        return false;
    }

    public void notificarPrestamoEmail(String idProfesor, String tituloRecurso, java.util.Date fechaPrestamo, java.util.Date fechaDevolucion) {

    }
}

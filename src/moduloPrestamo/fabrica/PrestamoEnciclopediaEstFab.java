package moduloPrestamo.fabrica;

import moduloPrestamo.entitys.PrestamoEnciclopediaEst;
import controllers.EnciclopediaJpaController;
import entitys.Enciclopedia;
import java.sql.Date;
import modelo.ServicioFecha;
import moduloPrestamo.DAO.PrestamoEnciclopediaDAOEst;
import moduloPrestamo.IPrestamo;
import vista.AlertBox;
import vista.IAlertBox;

/**
 *
 * @author Julian
 */
public class PrestamoEnciclopediaEstFab implements IPrestamo {

    public PrestamoEnciclopediaEstFab() {

    }

    @Override
    public boolean ejecutarPrestamo(String codBarras, String codUsuario, String idBibliotecario) {
        IAlertBox alert = new AlertBox();
        try {
            EnciclopediaJpaController control = new EnciclopediaJpaController();
            Enciclopedia enciclopedia = control.findEnciclopedia(codBarras);
            if (enciclopedia != null) {
                if (enciclopedia.getDisponibilidad().equalsIgnoreCase("disponible")) {

                    java.util.Date fechaActual = new java.util.Date();
                    java.util.Date fechaDevolucion = ServicioFecha.sumarDiasAFecha(fechaActual, 0);

                    PrestamoEnciclopediaEst presEncEst = new PrestamoEnciclopediaEst(codBarras, codUsuario, idBibliotecario,
                            new Date(fechaActual.getTime()), new Date(fechaDevolucion.getTime()));

                    PrestamoEnciclopediaDAOEst presEncDAOEst = new PrestamoEnciclopediaDAOEst();
                    if (presEncDAOEst.createDAO(presEncEst)) {
                        System.out.println("Cambiando disponibilidad de la enciclopedia...");
                        
                        enciclopedia.setDisponibilidad("prestado");
                        control.edit(enciclopedia);
                    }
                    return true;
                } else {
                    alert.showAlert("Anuncio", "Prestamo enciclopedia", "la enciclopedia no se encuentra disponible");
                }
            } else {
                alert.showAlert("Anuncio", "Prestamo enciclopedia", "Ninguna enciclopedia tiene este codigo de barra");
            }
        } catch (Exception e) {
            System.out.println("error al generar el prestamo de enciclopedio de un estudiante");
        }
        return false;
    }

    public void notificarPrestamoEmail(String idProfesor, String tituloRecurso, java.util.Date fechaPrestamo, java.util.Date fechaDevolucion) {

    }
}

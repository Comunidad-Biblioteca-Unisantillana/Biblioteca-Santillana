package moduloPrestamo.fabrica;

import moduloPrestamo.entitys.PrestamoMapaEst;
import controllers.MapaJpaController;
import entitys.Mapa;
import java.sql.Date;
import modelo.QueryRecurso;
import modelo.ServicioFecha;
import moduloPrestamo.DAO.PrestamoMapaDAOEst;
import moduloPrestamo.IPrestamo;
import vista.AlertBox;
import vista.IAlertBox;

/**
 *
 * @author Julian
 */
public class PrestamoMapaEstFab implements IPrestamo {

    public PrestamoMapaEstFab() {
    }

    @Override
    public boolean ejecutarPrestamo(String codBarras, String codUsuario, String idBibliotecario) {
        IAlertBox alert = new AlertBox();
        try {
            Mapa mapa = QueryRecurso.consultarMapa(codBarras);
            if (mapa != null) {
                if (mapa.getDisponibilidad().equalsIgnoreCase("disponible")) {
                    java.util.Date fechaActual = new java.util.Date();
                    java.util.Date fechaDevolucion = ServicioFecha.sumarDiasAFecha(fechaActual, 0);

                    PrestamoMapaEst presMapEst = new PrestamoMapaEst(codBarras, codUsuario, idBibliotecario,
                            new Date(fechaActual.getTime()), new Date(fechaDevolucion.getTime()));
                    PrestamoMapaDAOEst presMapDAOEst = new PrestamoMapaDAOEst();
                    
                    if (presMapDAOEst.createDAO(presMapEst)) {
                        System.out.println("Cambiando disponibilidad del mapa...");
                        MapaJpaController control = new MapaJpaController();
                        mapa.setDisponibilidad("prestado");
                        control.edit(mapa);
                        return true;
                    }
                } else {
                    alert.showAlert("Anuncio", "Prestamo mapa", "el mapa no se encuentra disponible");
                }
            } else {
                alert.showAlert("Anuncio", "Prestamo mapa", "Ningun mapa tiene este codigo de barra");
            }
        } catch (Exception e) {
            System.out.println("error al generar el prestamo del mapa de un estudiante: " + e.getMessage());
        }
        return false;
    }

    public void notificarPrestamoEmail(String idProfesor, String tituloRecurso, java.util.Date fechaPrestamo, java.util.Date fechaDevolucion) {

    }
}

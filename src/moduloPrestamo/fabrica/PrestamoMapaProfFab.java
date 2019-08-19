package moduloPrestamo.fabrica;

import moduloPrestamo.entitys.PrestamoMapaProf;
import controllers.MapaJpaController;
import entitys.Mapa;
import java.sql.Date;
import modelo.ServicioFecha;
import moduloPrestamo.DAO.PrestamoMapaDAOProf;
import moduloPrestamo.IPrestamo;
import vista.AlertBox;
import vista.IAlertBox;

/**
 *
 * @author Julian
 */
public class PrestamoMapaProfFab implements IPrestamo {

    public PrestamoMapaProfFab() {

    }

    @Override
    public boolean ejecutarPrestamo(String codBarras, String codUsuario, String idBibliotecario) {
        IAlertBox alert = new AlertBox();
        try {
            MapaJpaController control = new MapaJpaController();
            Mapa mapa = control.findMapa(codBarras);
            if (mapa != null) {
                if (mapa.getDisponibilidad().equalsIgnoreCase("disponible")) {

                    java.util.Date fechaActual = new java.util.Date();
                    java.util.Date fechaDevolucion = ServicioFecha.sumarDiasAFecha(fechaActual, 0);

                    PrestamoMapaProf presMapProf = new PrestamoMapaProf(codBarras, codUsuario, idBibliotecario,
                            new Date(fechaActual.getTime()), new Date(fechaDevolucion.getTime()));

                    PrestamoMapaDAOProf presMapDAOProf = new PrestamoMapaDAOProf();
                    if (presMapDAOProf.createDAO(presMapProf)) {
                        System.out.println("Cambiando disponibilidad del mapa...");
                        
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
            System.out.println("error al generar el prestamo del mapa de un profesor");
        }
        return false;
    }

    public void notificarPrestamoEmail(String idProfesor, String tituloRecurso, java.util.Date fechaPrestamo, java.util.Date fechaDevolucion) {

    }
}

package moduloPrestamo.fabrica;

import moduloPrestamo.entitys.PrestamoMapaProf;
import controllers.MapaJpaController;
import entitys.Mapa;
import java.sql.Date;
import modelo.QueryRecurso;
import modelo.ServicioFecha;
import moduloPrestamo.DAO.PrestamoMapaDAOProf;
import moduloPrestamo.IPrestamo;

/**
 *
 * @author Julian
 */
public class PrestamoMapaProfFab implements IPrestamo {

    public PrestamoMapaProfFab() {

    }

    @Override
    public boolean ejecutarPrestamo(String codBarras, String codUsuario, String idBibliotecario) {
        try {
            Mapa mapa = QueryRecurso.consultarMapa(codBarras);
            if (mapa != null) {
                if (mapa.getDisponibilidad().equalsIgnoreCase("disponible")) {

                    java.util.Date fechaActual = new java.util.Date();
                    java.util.Date fechaDevolucion = ServicioFecha.sumarDiasAFecha(fechaActual, 1);

                    PrestamoMapaProf presMapProf = new PrestamoMapaProf(codBarras, codUsuario, idBibliotecario,
                            new Date(fechaActual.getTime()), new Date(fechaDevolucion.getTime()));

                    PrestamoMapaDAOProf presMapDAOProf = new PrestamoMapaDAOProf();
                    if (presMapDAOProf.createDAO(presMapProf)) {
                        System.out.println("Cambiando disponibilidad del mapa...");
                        MapaJpaController control = new MapaJpaController();
                        mapa.setDisponibilidad("prestado");
                        control.edit(mapa);
                        return true;
                    }
                } else {
                    System.out.println("el mapa no se encuentra disponible");
                }
            } else {
                System.out.println("Ningun mapa tiene este codigo de barra");
            }
        } catch (Exception e) {
            System.out.println("error al generar el prestamo del mapa de un profesor");
        }
        return false;
    }

    public void notificarPrestamoEmail(String idProfesor, String tituloRecurso, java.util.Date fechaPrestamo, java.util.Date fechaDevolucion) {

    }
}

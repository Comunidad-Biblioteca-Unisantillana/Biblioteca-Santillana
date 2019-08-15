package moduloPrestamo;

import controllers.MapaJpaController;
import entitys.Mapa;
import java.sql.Date;
import modelo.QueryRecurso;
import modelo.ServicioFecha;
import moduloPrestamoDAO.PrestamoMapaDAOEst;

/**
 *
 * @author Julian
 */
public class PrestamoMapaEstFab implements IPrestamo {

    public PrestamoMapaEstFab() {
    }

    @Override
    public boolean ejecutarPrestamo(String codBarras, String codUsuario, String idBibliotecario) {
        try {
            Mapa mapa = QueryRecurso.consultarMapa(codBarras);
            if (mapa != null) {
                if (mapa.getDisponibilidad().equalsIgnoreCase("disponible")) {
                    
                    java.util.Date fechaActual = new java.util.Date();
                    java.util.Date fechaDevolucion = ServicioFecha.sumarDiasAFecha(fechaActual, 0);
                    
                    PrestamoMapaEst presMapEst = new PrestamoMapaEst(codBarras, codUsuario, idBibliotecario,
                            new Date(fechaActual.getTime()), new Date(fechaDevolucion.getTime()));

                    PrestamoMapaDAOEst presMapDAOEst = new PrestamoMapaDAOEst();
                    presMapDAOEst.createDAO(presMapEst);

                    System.out.println("Cambiando disponibilidad del mapa...");
                    MapaJpaController control = new MapaJpaController(); 
                    mapa.setDisponibilidad("prestado");
                    control.edit(mapa);
                    return true;
                } else {
                    System.out.println("el mapa no se encuentra disponible");
                }
            } else {
                System.out.println("Ningun mapa tiene este codigo de barra");
            }
        } catch (Exception e) {
            System.out.println("error al generar el prestamo del mapa de un estudiante");
        }
        return false;
    }

    public void notificarPrestamoEmail(String idProfesor, String tituloRecurso, java.util.Date fechaPrestamo, java.util.Date fechaDevolucion) {

    }
}
package moduloPrestamo.fabrica;

import moduloPrestamo.entitys.PrestamoMapaProf;
import recursos1.controllers.MapaJpaController;
import recursos1.entitys.Mapa;
import java.sql.Date;
import moduloPrestamo.DAO.PrestamoMapaDAOProf;
import vista.AlertBox;
import vista.IAlertBox;

/**
 * La clase se encarga gestionar el préstamo del mapa al profesor.
 *
 * @author Julian
 * @creado
 * @author Miguel Fernández
 * @modificado 24/08/2019
 */
public class PrestamoMapaProfFab implements IPrestamo {

    /**
     * constructor de la clase sin parámetros.
     */
    public PrestamoMapaProfFab() {

    }

    /**
     * el metódo realiza el préstamo del mapa al profesor.
     *
     * @param codBarras
     * @param codUsuario
     * @param idBibliotecario
     * @return boolean
     */
    @Override
    public boolean ejecutarPrestamo(String codBarras, String codUsuario, String idBibliotecario) {
        IAlertBox alert = new AlertBox();

        try {
            MapaJpaController control = new MapaJpaController();
            Mapa mapa = control.findMapa(codBarras);

            if (mapa != null) {
                if (mapa.getDisponibilidad().equalsIgnoreCase("disponible")) {
                    PrestamoMapaProf presMapProf = new PrestamoMapaProf();
                    presMapProf.setCodBarraMapa(codBarras);
                    presMapProf.setIdProfesor(codUsuario);
                    presMapProf.setIdBibliotecario(idBibliotecario);

                    PrestamoMapaDAOProf presMapDAOProf = new PrestamoMapaDAOProf();

                    if (presMapDAOProf.createDAO(presMapProf)) {
                        mapa.setDisponibilidad("prestado");
                        control.edit(mapa);

                        //espacio para enviar el correo
                        
                        return true;
                    }
                } else if (mapa.getDisponibilidad().equalsIgnoreCase("prestado")) {
                    alert.showAlert("Anuncio", "Préstamo mapa", "El mapa: " + codBarras
                            + ", se encuentra préstado a otro usuario.");
                } else if (mapa.getDisponibilidad().equalsIgnoreCase("vencido")) {
                    alert.showAlert("Anuncio", "Préstamo mapa", "El mapa: " + codBarras
                            + ", no ha sido devuelto por el usuario al que se le presto.");
                }
            } else {
                alert.showAlert("Anuncio", "Préstamo mapa", "No se encuentró un mapa asociado al código: " + codBarras);
            }
        } catch (Exception e) {
            System.out.println("Error al generar el préstamo del mapa a un profesor");
        }

        return false; 
    }

    /**
     * el metódo realiza la construccción del e-mail al profesor,
     * notificandole el préstamo del mapa.
     *
     * @param idProfesor
     * @param tituloRecurso
     * @param fechaPrestamo
     * @param fechaDevolucion
     */
    public void notificarPrestamoEmail(String idProfesor, String tituloRecurso, Date fechaPrestamo, Date fechaDevolucion) {

    }
    
}

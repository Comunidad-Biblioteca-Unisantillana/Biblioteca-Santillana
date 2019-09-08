package moduloPrestamo.fabrica;

import general.modelo.NotificacionEmail;
import moduloPrestamo.modelo.IPrestamo;
import moduloPrestamo.entitys.PrestamoMapaProf;
import recursos.controllers.MapaJpaController;
import recursos.entitys.Mapa;
import moduloPrestamo.DAO.PrestamoMapaDAOProf;
import general.vista.AlertBox;
import general.vista.IAlertBox;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import usuarios.control.ProfesorJpaController;
import usuarios.entitys.Profesor;

/**
 * La clase se encarga gestionar el préstamo del mapa al profesor.
 *
 * @author Julian
 * @creado:
 * @author Miguel Fernández
 * @modificado: 07/09/2019
 */
public class PrestamoMapaProfFab implements IPrestamo {

    /**
     * constructor de la clase sin parámetros.
     */
    public PrestamoMapaProfFab() {

    }

    /**
     * el método realiza el préstamo del mapa al profesor.
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
                        notificarPrestamoEmail(codUsuario, mapa);
                        
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
                alert.showAlert("Anuncio", "Préstamo mapa", "No se encuentró un mapa "
                        + "asociado al código: " + codBarras + ".");
            }
        } catch (Exception e) {
            System.out.println("Error al generar el préstamo del mapa a un profesor");
        }

        return false; 
    }

    /**
     * el método realiza concatenación de los datos necesarios para la
     * construcción del e-mail al profesor, notificandole del préstamo del
     * mapa.
     *
     * @param idProfesor
     * @param mapa
     */
    private void notificarPrestamoEmail(String idProfesor, Mapa mapa) {
        ProfesorJpaController profesorJpaController = new ProfesorJpaController();
        Profesor profesor = profesorJpaController.findProfesor(idProfesor);

        PrestamoMapaDAOProf prestMapaDAOProf = new PrestamoMapaDAOProf();
        PrestamoMapaProf prestMapaProf = prestMapaDAOProf.readDAO(prestMapaDAOProf.readCodigoDAO(mapa.getCodbarramapa()));

        DateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");

        String datos = profesor.getApellidos().toUpperCase() + ";"
                + profesor.getNombres().toUpperCase() + ";"
                + "Identificación: " + idProfesor + ";"
                + mapa.getTitulo() + ";"
                + mapa.getCodbarramapa() + ";"
                + formatoFecha.format((Date) prestMapaProf.getFechaPrestamo()) + ";"
                + formatoFecha.format((Date) prestMapaProf.getFechaDevolucion()) + ";"
                + "mapoteca;"
                + profesor.getCorreoelectronico();

        NotificacionEmail em = new NotificacionEmail();
        em.gestionarNotificacion(datos, "mensajePrestamo");
    }
    
}

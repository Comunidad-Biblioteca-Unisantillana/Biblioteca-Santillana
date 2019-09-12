package moduloPrestamo.fabrica;

import general.modelo.NotificacionEmail;
import moduloPrestamo.modelo.IPrestamo;
import moduloPrestamo.entitys.PrestamoMapaEst;
import recursos.controllers.MapaJpaController;
import recursos.entitys.Mapa;
import moduloPrestamo.DAO.PrestamoMapaDAOEst;
import general.vista.AlertBox;
import general.vista.IAlertBox;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import usuarios.control.EstudianteJpaController;
import usuarios.entitys.Estudiante;

/**
 * La clase se encarga gestionar el préstamo del mapa al estudiante.
 *
 * @author Julian
 * @creado:
 * @author Miguel Fernández
 * @modificado: 07/09/2019
 */
public class PrestamoMapaEstFab implements IPrestamo {

    /**
     * constructor de la clase sin parámetros.
     */
    public PrestamoMapaEstFab() {
    }

    /**
     * el método realiza el préstamo del mapa al estudiante.
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
                    PrestamoMapaEst prestMapaEst = new PrestamoMapaEst();
                    prestMapaEst.setCodBarraMapa(codBarras);
                    prestMapaEst.setCodEstudiante(codUsuario);
                    prestMapaEst.setIdBibliotecario(idBibliotecario);
                    
                    PrestamoMapaDAOEst prestMapaDAOEst = new PrestamoMapaDAOEst();

                    if (prestMapaDAOEst.createDAO(prestMapaEst)) {
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
            System.out.println("Error al generar el préstamo del mapa a un estudiante");
        }

        return false; 
    }

    /**
     * el método realiza concatenación de los datos necesarios para la
     * construcción del e-mail al estudiante, notificandole del préstamo del
     * mapa.
     *
     * @param codEstudiante
     * @param mapa
     */
    private void notificarPrestamoEmail(String codEstudiante, Mapa mapa) {
        EstudianteJpaController estudianteJpaController = new EstudianteJpaController();
        Estudiante estudiante = estudianteJpaController.findEstudiante(codEstudiante);

        PrestamoMapaDAOEst prestMapaDAOEst = new PrestamoMapaDAOEst();
        PrestamoMapaEst prestMapaEst = prestMapaDAOEst.readDAO(prestMapaDAOEst.readCodigoDAO(mapa.getCodbarramapa()));

        DateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");

        String datos = estudiante.getApellido().toUpperCase() + ";"
                + estudiante.getNombre().toUpperCase() + ";"
                + "Código: " + codEstudiante + ";"
                + mapa.getTitulo() + ";"
                + mapa.getCodbarramapa() + ";"
                + formatoFecha.format((Date) prestMapaEst.getFechaPrestamo()) + ";"
                + formatoFecha.format((Date) prestMapaEst.getFechaDevolucion()) + ";"
                + "mapoteca;"
                + estudiante.getCorreoelectronico();

        NotificacionEmail notificacionEmail = new NotificacionEmail(datos, "mensajePrestamo");
        notificacionEmail.start();
    }

}

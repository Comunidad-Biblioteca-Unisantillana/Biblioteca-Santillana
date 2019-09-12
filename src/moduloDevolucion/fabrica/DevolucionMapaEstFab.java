package moduloDevolucion.fabrica;

import controller.exceptions.NonexistentEntityException;
import general.modelo.NotificacionEmail;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import moduloDevolucion.DAO.DevolucionMapaDAOEst;
import moduloDevolucion.modelo.IDevolucion;
import moduloDevolucion.entitys.DevolucionMapaEst;
import moduloPrestamo.DAO.PrestamoMapaDAOEst;
import moduloPrestamo.entitys.PrestamoMapaEst;
import recursos.controllers.MapaJpaController;
import recursos.entitys.Mapa;
import general.vista.AlertBox;
import general.vista.IAlertBox;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import usuarios.control.EstudianteJpaController;
import usuarios.entitys.Estudiante;

/**
 * @author Camilo Jaramillo
 * @creado: 04/08/2019
 * @author Miguel Fernández
 * @modificado: 08/09/2019
 */
public class DevolucionMapaEstFab implements IDevolucion {

    public DevolucionMapaEstFab() {

    }

    @Override
    public boolean ejecutarDevolucion(String codBarras, String idBibliotecario, String estadoRecurso) {
        IAlertBox alert = new AlertBox();
        try {
            MapaJpaController control = new MapaJpaController();
            Mapa mapa = control.findMapa(codBarras);
            if (mapa != null) {
                int codPrestamo = consultarPrestamoMapa(codBarras);
                if (codPrestamo > 0) {

                    PrestamoMapaDAOEst prestDAOEst = new PrestamoMapaDAOEst();
                    PrestamoMapaEst prestEst = prestDAOEst.readDAO(codPrestamo);
                    if (prestEst.getDevuelto().equalsIgnoreCase("no")) {
                        DevolucionMapaEst devEst = new DevolucionMapaEst(prestEst.getCodPrestamoMapaEst(), idBibliotecario, estadoRecurso);
                        DevolucionMapaDAOEst devDAOEst = new DevolucionMapaDAOEst();
                        devDAOEst.createDAO(devEst);

                        mapa.setDisponibilidad("disponible");
                        control.edit(mapa);

                        prestEst.setDevuelto("si");
                        prestDAOEst.updateDAO(prestEst);

                        notificarDevolucion(prestEst.getCodEstudiante(), mapa, codPrestamo);
                        alert.showAlert("Anuncio", "Devolución mapa", "La devolución del usuario con codigo"
                                + prestEst.getCodEstudiante()
                                + "se realizo con exito");
                    } else {
                        alert.showAlert("Anuncio", "Devolución mapa", "El mapa se había devuelto anteriormente");
                    }
                    return true;
                }
            }
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(DevolucionDiccionarioEstFab.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(DevolucionDiccionarioEstFab.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public int consultarPrestamoMapa(String codBarras) {
        PrestamoMapaDAOEst prestDAOEst = new PrestamoMapaDAOEst();
        List<PrestamoMapaEst> prestamos = prestDAOEst.readAllDAO();
        int codPrestamo = 0;
        for (int i = 0; i < prestamos.size(); i++) {
            if (prestamos.get(i).getCodBarraMapa().equalsIgnoreCase(codBarras)
                    && prestamos.get(i).getDevuelto().equalsIgnoreCase("no")) {
                codPrestamo = prestamos.get(i).getCodPrestamoMapaEst();
                break;
            }
        }
        return codPrestamo;
    }

    /**
     * el método realiza concatenación de los datos necesarios para la
     * construcción del e-mail al estudiante, notificandole de la devoluciòn del
     * mapa.
     *
     * @param codEstudiante
     * @param mapa
     * @param codPrestamo
     */
    public void notificarDevolucion(String codEstudiante, Mapa mapa, int codPrestamo) {
        EstudianteJpaController estudianteJpaController = new EstudianteJpaController();
        Estudiante estudiante = estudianteJpaController.findEstudiante(codEstudiante);

        DevolucionMapaDAOEst devDAOEst = new DevolucionMapaDAOEst();
        DevolucionMapaEst devEst = devDAOEst.readDAO(devDAOEst.readCodigoDAO(codPrestamo));

        DateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");

        String datos = estudiante.getApellido().toUpperCase() + ";"
                + estudiante.getNombre().toUpperCase() + ";"
                + "Código: " + codEstudiante + ";"
                + mapa.getTitulo() + ";"
                + mapa.getCodbarramapa() + ";"
                + formatoFecha.format((Date) devEst.getFechaDevolucion()) + ";"
                + "null;"
                + "null;"
                + estudiante.getCorreoelectronico();

        NotificacionEmail notificacionEmail = new NotificacionEmail(datos, "mensajeDevolucion");
        notificacionEmail.start();
    }

}

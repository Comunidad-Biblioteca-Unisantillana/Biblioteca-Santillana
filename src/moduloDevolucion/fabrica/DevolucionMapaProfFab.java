package moduloDevolucion.fabrica;

import controller.exceptions.NonexistentEntityException;
import general.modelo.NotificacionEmail;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import moduloDevolucion.DAO.DevolucionMapaDAOProf;
import moduloDevolucion.modelo.IDevolucion;
import moduloDevolucion.entitys.DevolucionMapaProf;
import moduloPrestamo.DAO.PrestamoMapaDAOProf;
import moduloPrestamo.entitys.PrestamoMapaProf;
import recursos.controllers.MapaJpaController;
import recursos.entitys.Mapa;
import general.vista.AlertBox;
import general.vista.IAlertBox;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import usuarios.control.ProfesorJpaController;
import usuarios.entitys.Profesor;

/**
 * @author Camilo Jaramillo
 * @creado: 04/08/2019
 * @author Miguel Fernández
 * @modificado: 08/09/2019
 */
public class DevolucionMapaProfFab implements IDevolucion {

    public DevolucionMapaProfFab() {

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
                    PrestamoMapaDAOProf prestDAOProf = new PrestamoMapaDAOProf();
                    PrestamoMapaProf prestProf = prestDAOProf.readDAO(codPrestamo);
                    if (prestProf.getDevuelto().equalsIgnoreCase("no")) {
                        DevolucionMapaProf devProf = new DevolucionMapaProf(prestProf.getCodPrestamoMapaProf(),
                                idBibliotecario, estadoRecurso);
                        DevolucionMapaDAOProf devDAOProf = new DevolucionMapaDAOProf();
                        devDAOProf.createDAO(devProf);

                        mapa.setDisponibilidad("disponible");
                        control.edit(mapa);

                        prestProf.setDevuelto("si");
                        prestDAOProf.updateDAO(prestProf);

                        notificarDevolucion(prestProf.getIdProfesor(), mapa, codPrestamo);
                        alert.showAlert("Anuncio", "Devolución mapa", "La devolución del usuario con codigo"
                                + prestProf.getIdProfesor() + "se realizo con exito");
                    } else {
                        alert.showAlert("Anuncio", "Devolución mapa", "El mapa se había devuelto anteriormente");
                    }
                    return true;
                } else {
                    alert.showAlert("Anuncio", "Devolución mapa", "El prestamo del mapa no existe");
                }
            } else {
                alert.showAlert("Anuncio", "Devolución mapa", "El mapa no existe");
            }
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(DevolucionDiccionarioEstFab.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(DevolucionDiccionarioEstFab.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public int consultarPrestamoMapa(String codBarras) {
        PrestamoMapaDAOProf prestDAOProf = new PrestamoMapaDAOProf();
        List<PrestamoMapaProf> prestamos = prestDAOProf.readAllDAO();
        int codPrestamo = 0;
        for (int i = 0; i < prestamos.size(); i++) {
            if (prestamos.get(i).getCodBarraMapa().equalsIgnoreCase(codBarras)
                    && prestamos.get(i).getDevuelto().equalsIgnoreCase("no")) {
                codPrestamo = prestamos.get(i).getCodPrestamoMapaProf();
                break;
            }
        }
        return codPrestamo;
    }

    /**
     * el método realiza concatenación de los datos necesarios para la
     * construcción del e-mail al profesor, notificandole de la devoluciòn del
     * mapa.
     *
     * @param idProfesor
     * @param mapa
     * @param codPrestamo
     */
    public void notificarDevolucion(String idProfesor, Mapa mapa, int codPrestamo) {
        ProfesorJpaController profesorJpaController = new ProfesorJpaController();
        Profesor profesor = profesorJpaController.findProfesor(idProfesor);

        DevolucionMapaDAOProf devDAOProf = new DevolucionMapaDAOProf();
        DevolucionMapaProf devProf = devDAOProf.readDAO(devDAOProf.readCodigoDAO(codPrestamo));

        DateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");

        String datos = profesor.getApellidos().toUpperCase() + ";"
                + profesor.getNombres().toUpperCase() + ";"
                + "Identificaciòn: " + idProfesor + ";"
                + mapa.getTitulo() + ";"
                + mapa.getCodbarramapa() + ";"
                + formatoFecha.format((Date) devProf.getFechaDevolucion()) + ";"
                + "null;"
                + "null;"
                + profesor.getCorreoelectronico();

        NotificacionEmail notificacionEmail = new NotificacionEmail(datos, "mensajeDevolucion");
        notificacionEmail.start();
    }
    
}

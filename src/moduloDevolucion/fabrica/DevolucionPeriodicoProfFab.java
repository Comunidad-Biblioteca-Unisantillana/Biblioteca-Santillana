package moduloDevolucion.fabrica;

import controller.exceptions.NonexistentEntityException;
import general.modelo.NotificacionEmail;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import moduloDevolucion.DAO.DevolucionPeriodicoDAOProf;
import moduloDevolucion.modelo.IDevolucion;
import moduloDevolucion.entitys.DevolucionPeriodicoProf;
import moduloPrestamo.DAO.PrestamoPeriodicoDAOProf;
import moduloPrestamo.entitys.PrestamoPeriodicoProf;
import recursos.controllers.PeriodicoJpaController;
import recursos.entitys.Periodico;
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
public class DevolucionPeriodicoProfFab implements IDevolucion {

    public DevolucionPeriodicoProfFab() {

    }

    @Override
    public boolean ejecutarDevolucion(String codBarras, String idBibliotecario, String estadoRecurso) {
        IAlertBox alert = new AlertBox();
        try {
            PeriodicoJpaController control = new PeriodicoJpaController();
            Periodico periodico = control.findPeriodico(codBarras);
            if (periodico != null) {
                int codPrestamo = consultarPrestamoPeriodico(codBarras);
                if (codPrestamo > 0) {
                    PrestamoPeriodicoDAOProf prestDAOProf = new PrestamoPeriodicoDAOProf();
                    PrestamoPeriodicoProf prestProf = prestDAOProf.readDAO(codPrestamo);
                    if (prestProf.getDevuelto().equalsIgnoreCase("no")) {
                        DevolucionPeriodicoProf devProf = new DevolucionPeriodicoProf(prestProf.getCodPrestamoPeriodicoProf(),
                                idBibliotecario, estadoRecurso);
                        DevolucionPeriodicoDAOProf devDAOProf = new DevolucionPeriodicoDAOProf();
                        devDAOProf.createDAO(devProf);

                        periodico.setDisponibilidad("disponible");
                        control.edit(periodico);

                        prestProf.setDevuelto("si");
                        prestDAOProf.updateDAO(prestProf);

                        notificarDevolucion(prestProf.getIdProfesor(), periodico, codPrestamo);
                        alert.showAlert("Anuncio", "Devolución periodico", "La devolución del usuario con codigo"
                                + prestProf.getIdProfesor() + "se realizo con exito");
                    } else {
                        alert.showAlert("Anuncio", "Devolución periodico", "El periodico se había devuelto anteriormente");
                    }
                    return true;
                } else {
                    alert.showAlert("Anuncio", "Devolución periodico", "El prestamo del periodico no existe");
                }
            } else {
                alert.showAlert("Anuncio", "Devolución periodico", "El periodico no existe");
            }
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(DevolucionDiccionarioEstFab.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(DevolucionDiccionarioEstFab.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public int consultarPrestamoPeriodico(String codBarras) {
        PrestamoPeriodicoDAOProf prestDAOProf = new PrestamoPeriodicoDAOProf();
        List<PrestamoPeriodicoProf> prestamos = prestDAOProf.readAllDAO();
        int codPrestamo = 0;
        for (int i = 0; i < prestamos.size(); i++) {
            if (prestamos.get(i).getCodBarraPeriodico().equalsIgnoreCase(codBarras)
                    && prestamos.get(i).getDevuelto().equalsIgnoreCase("no")) {
                codPrestamo = prestamos.get(i).getCodPrestamoPeriodicoProf();
                break;
            }
        }
        return codPrestamo;
    }

    /**
     * el método realiza concatenación de los datos necesarios para la
     * construcción del e-mail al profesor, notificandole de la devoluciòn de la
     * periòdico.
     *
     * @param idProfesor
     * @param periodico
     * @param codPrestamo
     */
    public void notificarDevolucion(String idProfesor, Periodico periodico, int codPrestamo) {
        ProfesorJpaController profesorJpaController = new ProfesorJpaController();
        Profesor profesor = profesorJpaController.findProfesor(idProfesor);

        DevolucionPeriodicoDAOProf devDAOProf = new DevolucionPeriodicoDAOProf();
        DevolucionPeriodicoProf devProf = devDAOProf.readDAO(devDAOProf.readCodigoDAO(codPrestamo));

        DateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");

        String datos = profesor.getApellidos().toUpperCase() + ";"
                + profesor.getNombres().toUpperCase() + ";"
                + "Identificaciòn: " + idProfesor + ";"
                + periodico.getNombreperiodico()+ ";"
                + periodico.getCodbarraperiodico() + ";"
                + formatoFecha.format((Date) devProf.getFechaDevolucion()) + ";"
                + "null;"
                + "null;"
                + profesor.getCorreoelectronico();

        NotificacionEmail em = new NotificacionEmail();
        em.gestionarNotificacion(datos, "mensajeDevolucion");
    }
    
}

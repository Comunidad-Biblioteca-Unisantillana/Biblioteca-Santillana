package moduloDevolucion.fabrica;

import controller.exceptions.NonexistentEntityException;
import general.modelo.NotificacionEmail;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import moduloDevolucion.DAO.DevolucionPeriodicoDAOEst;
import moduloDevolucion.modelo.IDevolucion;
import moduloDevolucion.entitys.DevolucionPeriodicoEst;
import moduloPrestamo.DAO.PrestamoPeriodicoDAOEst;
import moduloPrestamo.entitys.PrestamoPeriodicoEst;
import recursos.controllers.PeriodicoJpaController;
import recursos.entitys.Periodico;
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
public class DevolucionPeriodicoEstFab implements IDevolucion {

    public DevolucionPeriodicoEstFab() {

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

                    PrestamoPeriodicoDAOEst prestDAOEst = new PrestamoPeriodicoDAOEst();
                    PrestamoPeriodicoEst prestEst = prestDAOEst.readDAO(codPrestamo);
                    if (prestEst.getDevuelto().equalsIgnoreCase("no")) {
                        DevolucionPeriodicoEst devEst = new DevolucionPeriodicoEst(prestEst.getCodPrestamoPeriodicoEst(),
                                idBibliotecario, estadoRecurso);
                        DevolucionPeriodicoDAOEst devDAOEst = new DevolucionPeriodicoDAOEst();
                        devDAOEst.createDAO(devEst);

                        periodico.setDisponibilidad("disponible");
                        control.edit(periodico);

                        prestEst.setDevuelto("si");
                        prestDAOEst.updateDAO(prestEst);

                        notificarDevolucion(prestEst.getCodEstudiante(), periodico, codPrestamo);
                        alert.showAlert("Anuncio", "Devolución periodico", "La devolución del usuario con codigo"
                                + prestEst.getCodEstudiante()
                                + "se realizo con exito");
                    } else {
                        alert.showAlert("Anuncio", "Devolución periodico", "El periodico se había devuelto anteriormente");
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

    public int consultarPrestamoPeriodico(String codBarras) {
        PrestamoPeriodicoDAOEst prestDAOEst = new PrestamoPeriodicoDAOEst();
        List<PrestamoPeriodicoEst> prestamos = prestDAOEst.readAllDAO();
        int codPrestamo = 0;
        for (int i = 0; i < prestamos.size(); i++) {
            if (prestamos.get(i).getCodBarraPeriodico().equalsIgnoreCase(codBarras)
                    && prestamos.get(i).getDevuelto().equalsIgnoreCase("no")) {
                codPrestamo = prestamos.get(i).getCodPrestamoPeriodicoEst();
                break;
            }
        }
        return codPrestamo;
    }

    /**
     * el método realiza concatenación de los datos necesarios para la
     * construcción del e-mail al estudiante, notificandole de la devoluciòn del
     * periòdico.
     *
     * @param codEstudiante
     * @param periodico
     * @param codPrestamo
     */
    public void notificarDevolucion(String codEstudiante, Periodico periodico, int codPrestamo) {
        EstudianteJpaController estudianteJpaController = new EstudianteJpaController();
        Estudiante estudiante = estudianteJpaController.findEstudiante(codEstudiante);

        DevolucionPeriodicoDAOEst devDAOEst = new DevolucionPeriodicoDAOEst();
        DevolucionPeriodicoEst devEst = devDAOEst.readDAO(devDAOEst.readCodigoDAO(codPrestamo));

        DateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");

        String datos = estudiante.getApellido().toUpperCase() + ";"
                + estudiante.getNombre().toUpperCase() + ";"
                + "Código: " + codEstudiante + ";"
                + periodico.getNombreperiodico()+ ";"
                + periodico.getCodbarraperiodico() + ";"
                + formatoFecha.format((Date) devEst.getFechaDevolucion()) + ";"
                + "null;"
                + "null;"
                + estudiante.getCorreoelectronico();

        NotificacionEmail notificacionEmail = new NotificacionEmail(datos, "mensajeDevolucion");
        notificacionEmail.start();
    }
    
}

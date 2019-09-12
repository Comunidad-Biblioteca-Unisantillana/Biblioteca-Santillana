package moduloDevolucion.fabrica;

import controller.exceptions.NonexistentEntityException;
import general.modelo.NotificacionEmail;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import moduloDevolucion.DAO.DevolucionEnciclopediaDAOEst;
import moduloDevolucion.modelo.IDevolucion;
import moduloDevolucion.entitys.DevolucionEnciclopediaEst;
import moduloPrestamo.DAO.PrestamoEnciclopediaDAOEst;
import moduloPrestamo.entitys.PrestamoEnciclopediaEst;
import recursos.controllers.EnciclopediaJpaController;
import recursos.entitys.Enciclopedia;
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
public class DevolucionEnciclopediaEstFab implements IDevolucion {

    public DevolucionEnciclopediaEstFab() {

    }

    @Override
    public boolean ejecutarDevolucion(String codBarras, String idBibliotecario, String estadoRecurso) {
        IAlertBox alert = new AlertBox();
        try {
            EnciclopediaJpaController control = new EnciclopediaJpaController();
            Enciclopedia enciclopedia = control.findEnciclopedia(codBarras);
            if (enciclopedia != null) {
                int codPrestamo = consultarPrestamoEnciclopedia(codBarras);
                if (codPrestamo > 0) {
                    PrestamoEnciclopediaDAOEst prestDAOEst = new PrestamoEnciclopediaDAOEst();
                    PrestamoEnciclopediaEst prestEst = prestDAOEst.readDAO(codPrestamo);
                    if (prestEst.getDevuelto().equalsIgnoreCase("no")) {
                        DevolucionEnciclopediaEst devEst = new DevolucionEnciclopediaEst(prestEst.getCodPrestamoEnciclopediaEst(),
                                idBibliotecario, estadoRecurso);
                        DevolucionEnciclopediaDAOEst devDAOEst = new DevolucionEnciclopediaDAOEst();
                        devDAOEst.createDAO(devEst);

                        enciclopedia.setDisponibilidad("disponible");
                        control.edit(enciclopedia);

                        prestEst.setDevuelto("si");
                        prestDAOEst.updateDAO(prestEst);

                        notificarDevolucion(prestEst.getCodEstudiante(), enciclopedia, codPrestamo);
                        alert.showAlert("Anuncio", "Devolución enciclopedia", "La devolución del usuario con codigo"
                                + prestEst.getCodEstudiante() + "se realizo con exito");
                    } else {
                        alert.showAlert("Anuncio", "Devolución enciclopedia", "La enciclopedia se había devuelto anteriormente");
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

    public int consultarPrestamoEnciclopedia(String codBarras) {
        PrestamoEnciclopediaDAOEst prestDAOEst = new PrestamoEnciclopediaDAOEst();
        List<PrestamoEnciclopediaEst> prestamos = prestDAOEst.readAllDAO();
        int codPrestamo = 0;
        for (int i = 0; i < prestamos.size(); i++) {
            if (prestamos.get(i).getCodBarraEnciclopedia().equalsIgnoreCase(codBarras)
                    && prestamos.get(i).getDevuelto().equalsIgnoreCase("no")) {
                codPrestamo = prestamos.get(i).getCodPrestamoEnciclopediaEst();
                break;
            }
        }
        return codPrestamo;
    }

    /**
     * el método realiza concatenación de los datos necesarios para la
     * construcción del e-mail al estudiante, notificandole de la devoluciòn de
     * la enciclopedia.
     *
     * @param codEstudiante
     * @param enciclopedia
     * @param codPrestamo
     */
    public void notificarDevolucion(String codEstudiante, Enciclopedia enciclopedia, int codPrestamo) {
        EstudianteJpaController estudianteJpaController = new EstudianteJpaController();
        Estudiante estudiante = estudianteJpaController.findEstudiante(codEstudiante);

        DevolucionEnciclopediaDAOEst devDAOEst = new DevolucionEnciclopediaDAOEst();
        DevolucionEnciclopediaEst devEst = devDAOEst.readDAO(devDAOEst.readCodigoDAO(codPrestamo));

        DateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");

        String datos = estudiante.getApellido().toUpperCase() + ";"
                + estudiante.getNombre().toUpperCase() + ";"
                + "Código: " + codEstudiante + ";"
                + enciclopedia.getTitulo() + ";"
                + enciclopedia.getCodbarraenciclopedia() + ";"
                + formatoFecha.format((Date) devEst.getFechaDevolucion()) + ";"
                + "null;"
                + "null;"
                + estudiante.getCorreoelectronico();

        NotificacionEmail notificacionEmail = new NotificacionEmail(datos, "mensajeDevolucion");
        notificacionEmail.start();
    }

}

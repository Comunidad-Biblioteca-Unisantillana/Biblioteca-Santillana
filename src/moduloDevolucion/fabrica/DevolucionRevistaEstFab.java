package moduloDevolucion.fabrica;

import controller.exceptions.NonexistentEntityException;
import general.modelo.NotificacionEmail;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import moduloDevolucion.DAO.DevolucionRevistaDAOEst;
import moduloDevolucion.modelo.IDevolucion;
import moduloDevolucion.entitys.DevolucionRevistaEst;
import moduloPrestamo.DAO.PrestamoRevistaDAOEst;
import moduloPrestamo.entitys.PrestamoRevistaEst;
import recursos.controllers.RevistaJpaController;
import recursos.entitys.Revista;
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
public class DevolucionRevistaEstFab implements IDevolucion {

    public DevolucionRevistaEstFab() {

    }

    @Override
    public boolean ejecutarDevolucion(String codBarras, String idBibliotecario, String estadoRecurso) {
        IAlertBox alert = new AlertBox();
        try {
            RevistaJpaController control = new RevistaJpaController();
            Revista revista = control.findRevista(codBarras);
            if (revista != null) {
                int codPrestamo = consultarPrestamoRevista(codBarras);
                if (codPrestamo > 0) {

                    PrestamoRevistaDAOEst prestDAOEst = new PrestamoRevistaDAOEst();
                    PrestamoRevistaEst prestEst = prestDAOEst.readDAO(codPrestamo);
                    if (prestEst.getDevuelto().equalsIgnoreCase("no")) {
                        DevolucionRevistaEst devEst = new DevolucionRevistaEst(prestEst.getCodPrestamoRevistaEst(),
                                idBibliotecario, estadoRecurso);
                        DevolucionRevistaDAOEst devDAOEst = new DevolucionRevistaDAOEst();
                        devDAOEst.createDAO(devEst);

                        revista.setDisponibilidad("disponible");
                        control.edit(revista);

                        prestEst.setDevuelto("si");
                        prestDAOEst.updateDAO(prestEst);

                        notificarDevolucion(prestEst.getCodEstudiante(), revista, codPrestamo);
                        alert.showAlert("Anuncio", "Devolución revista", "La devolución del usuario con codigo"
                                + prestEst.getCodEstudiante()
                                + "se realizo con exito");
                    } else {
                        alert.showAlert("Anuncio", "Devolución revista", "La revista se había devuelto anteriormente");
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

    public int consultarPrestamoRevista(String codBarras) {
        PrestamoRevistaDAOEst prestDAOEst = new PrestamoRevistaDAOEst();
        List<PrestamoRevistaEst> prestamos = prestDAOEst.readAllDAO();
        int codPrestamo = 0;
        for (int i = 0; i < prestamos.size(); i++) {
            if (prestamos.get(i).getCodBarraRevista().equalsIgnoreCase(codBarras)
                    && prestamos.get(i).getDevuelto().equalsIgnoreCase("no")) {
                codPrestamo = prestamos.get(i).getCodPrestamoRevistaEst();
                break;
            }
        }
        return codPrestamo;
    }

    /**
     * el método realiza concatenación de los datos necesarios para la
     * construcción del e-mail al estudiante, notificandole de la devoluciòn de
     * la revista.
     *
     * @param codEstudiante
     * @param revista
     * @param codPrestamo
     */
    public void notificarDevolucion(String codEstudiante, Revista revista, int codPrestamo) {
        EstudianteJpaController estudianteJpaController = new EstudianteJpaController();
        Estudiante estudiante = estudianteJpaController.findEstudiante(codEstudiante);

        DevolucionRevistaDAOEst devDAOEst = new DevolucionRevistaDAOEst();
        DevolucionRevistaEst devEst = devDAOEst.readDAO(devDAOEst.readCodigoDAO(codPrestamo));

        DateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");

        String datos = estudiante.getApellido().toUpperCase() + ";"
                + estudiante.getNombre().toUpperCase() + ";"
                + "Código: " + codEstudiante + ";"
                + revista.getTitulo() + ";"
                + revista.getCodbarrarevista() + ";"
                + formatoFecha.format((Date) devEst.getFechaDevolucion()) + ";"
                + "null;"
                + "null;"
                + estudiante.getCorreoelectronico();

        NotificacionEmail em = new NotificacionEmail();
        em.gestionarNotificacion(datos, "mensajeDevolucion");
    }
    
}

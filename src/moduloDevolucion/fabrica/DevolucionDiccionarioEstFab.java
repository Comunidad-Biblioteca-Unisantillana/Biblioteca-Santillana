package moduloDevolucion.fabrica;

import controller.exceptions.NonexistentEntityException;
import general.modelo.NotificacionEmail;
import recursos.controllers.DiccionarioJpaController;
import recursos.entitys.Diccionario;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import moduloDevolucion.DAO.DevolucionDiccionarioDAOEst;
import moduloDevolucion.modelo.IDevolucion;
import moduloDevolucion.entitys.DevolucionDiccionarioEst;
import moduloPrestamo.DAO.PrestamoDiccionarioDAOEst;
import moduloPrestamo.entitys.PrestamoDiccionarioEst;
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
public class DevolucionDiccionarioEstFab implements IDevolucion {

    public DevolucionDiccionarioEstFab() {
    }

    @Override
    public boolean ejecutarDevolucion(String codBarras, String idBibliotecario, String estadoRecurso) {
        IAlertBox alert = new AlertBox();
        try {
            DiccionarioJpaController control = new DiccionarioJpaController();
            Diccionario diccionario = control.findDiccionario(codBarras);
            if (diccionario != null) {
                int codPrestamo = consultarPrestamoDiccionario(codBarras);
                if (codPrestamo > 0) {
                    PrestamoDiccionarioDAOEst prestDAOEst = new PrestamoDiccionarioDAOEst();
                    PrestamoDiccionarioEst prestEst = prestDAOEst.readDAO(codPrestamo);
                    if (prestEst.getDevuelto().equalsIgnoreCase("no")) {
                        DevolucionDiccionarioEst devEst = new DevolucionDiccionarioEst(prestEst.getCodPrestamoDiccionarioEst(),
                                idBibliotecario, estadoRecurso);
                        DevolucionDiccionarioDAOEst devDAOEst = new DevolucionDiccionarioDAOEst();
                        devDAOEst.createDAO(devEst);
                        diccionario.setDisponibilidad("disponible");
                        control.edit(diccionario);

                        prestEst.setDevuelto("si");
                        prestDAOEst.updateDAO(prestEst);

                        notificarDevolucion(prestEst.getCodEstudiante(), diccionario, codPrestamo);
                        alert.showAlert("Anuncio", "Devolucion diccionario", "La devolucion del usuario con codigo"
                                + prestEst.getCodEstudiante() + "se realizo con exito");
                    } else {
                        alert.showAlert("Anuncio", "Devolución diccionario", "El diccionario se había devuelto anteriormente");
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

    public int consultarPrestamoDiccionario(String codBarras) {
        PrestamoDiccionarioDAOEst prestDAOEst = new PrestamoDiccionarioDAOEst();
        List<PrestamoDiccionarioEst> prestamos = prestDAOEst.readAllDAO();
        int codPrestamo = 0;
        for (int i = 0; i < prestamos.size(); i++) {
            if (prestamos.get(i).getCodBarraDiccionario().equalsIgnoreCase(codBarras)
                    && prestamos.get(i).getDevuelto().equalsIgnoreCase("no")) {
                codPrestamo = prestamos.get(i).getCodPrestamoDiccionarioEst();
                break;
            }
        }
        return codPrestamo;
    }

    /**
     * el método realiza concatenación de los datos necesarios para la
     * construcción del e-mail al estudiante, notificandole de la devoluciòn del
     * dicionario.
     *
     * @param codEstudiante
     * @param diccionario
     * @param codPrestamo
     */
    public void notificarDevolucion(String codEstudiante, Diccionario diccionario, int codPrestamo) {
        EstudianteJpaController estudianteJpaController = new EstudianteJpaController();
        Estudiante estudiante = estudianteJpaController.findEstudiante(codEstudiante);

        DevolucionDiccionarioDAOEst devDAOEst = new DevolucionDiccionarioDAOEst();
        DevolucionDiccionarioEst devEst = devDAOEst.readDAO(devDAOEst.readCodigoDAO(codPrestamo));

        DateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");

        String datos = estudiante.getApellido().toUpperCase() + ";"
                + estudiante.getNombre().toUpperCase() + ";"
                + "Código: " + codEstudiante + ";"
                + diccionario.getTitulo() + ";"
                + diccionario.getCodbarradiccionario() + ";"
                + formatoFecha.format((Date) devEst.getFechaDevolucion()) + ";"
                + "null;"
                + "null;"
                + estudiante.getCorreoelectronico();

        NotificacionEmail em = new NotificacionEmail();
        em.gestionarNotificacion(datos, "mensajeDevolucion");
    }

}

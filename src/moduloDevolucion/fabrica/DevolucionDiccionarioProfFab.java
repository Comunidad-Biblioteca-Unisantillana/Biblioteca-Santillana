package moduloDevolucion.fabrica;

import controller.exceptions.NonexistentEntityException;
import general.modelo.NotificacionEmail;
import recursos.controllers.DiccionarioJpaController;
import recursos.entitys.Diccionario;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import moduloDevolucion.DAO.DevolucionDiccionarioDAOProf;
import moduloDevolucion.modelo.IDevolucion;
import moduloDevolucion.entitys.DevolucionDiccionarioProf;
import moduloPrestamo.DAO.PrestamoDiccionarioDAOProf;
import moduloPrestamo.entitys.PrestamoDiccionarioProf;
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
public class DevolucionDiccionarioProfFab implements IDevolucion {

    public DevolucionDiccionarioProfFab() {

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
                    PrestamoDiccionarioDAOProf prestDAOProf = new PrestamoDiccionarioDAOProf();
                    PrestamoDiccionarioProf prestProf = prestDAOProf.readDAO(codPrestamo);
                    if (prestProf.getDevuelto().equalsIgnoreCase("no")) {
                        DevolucionDiccionarioProf devProf = new DevolucionDiccionarioProf(prestProf.getCodPrestamoDiccionarioProf(),
                                idBibliotecario, estadoRecurso);
                        DevolucionDiccionarioDAOProf devDAOProf = new DevolucionDiccionarioDAOProf();
                        devDAOProf.createDAO(devProf);

                        diccionario.setDisponibilidad("disponible");
                        control.edit(diccionario);

                        prestProf.setDevuelto("si");
                        prestDAOProf.updateDAO(prestProf);

                        notificarDevolucion(prestProf.getIdProfesor(), diccionario, codPrestamo);
                        alert.showAlert("Anuncio", "Devolucion", "La devolucion del usuario con codigo"
                                + prestProf.getIdProfesor() + "se realizo con exito");
                    } else {
                        alert.showAlert("Anuncio", "Devolución diccionario", "El diccionario se había devuelto anteriormente");
                    }
                    return true;
                } else {
                    alert.showAlert("Anuncio", "Devolución diccionario", "El prestamo del diccionario no existe");
                }
            } else {
                alert.showAlert("Anuncio", "Devolución diccionario", "El diccionario no existe");
            }
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(DevolucionDiccionarioEstFab.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(DevolucionDiccionarioEstFab.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public int consultarPrestamoDiccionario(String codBarras) {
        PrestamoDiccionarioDAOProf prestDAOProf = new PrestamoDiccionarioDAOProf();
        List<PrestamoDiccionarioProf> prestamos = prestDAOProf.readAllDAO();
        int codPrestamo = 0;
        for (int i = 0; i < prestamos.size(); i++) {
            if (prestamos.get(i).getCodBarraDiccionario().equalsIgnoreCase(codBarras)
                    && prestamos.get(i).getDevuelto().equalsIgnoreCase("no")) {
                codPrestamo = prestamos.get(i).getCodPrestamoDiccionarioProf();
                break;
            }
        }
        return codPrestamo;
    }

    /**
     * el método realiza concatenación de los datos necesarios para la
     * construcción del e-mail al profesor, notificandole de la devoluciòn del
     * dicionario.
     *
     * @param idProfesor
     * @param diccionario
     * @param codPrestamo
     */
    public void notificarDevolucion(String idProfesor, Diccionario diccionario, int codPrestamo) {
        ProfesorJpaController profesorJpaController = new ProfesorJpaController();
        Profesor profesor = profesorJpaController.findProfesor(idProfesor);

        DevolucionDiccionarioDAOProf devDAOProf = new DevolucionDiccionarioDAOProf();
        DevolucionDiccionarioProf devProf = devDAOProf.readDAO(devDAOProf.readCodigoDAO(codPrestamo));

        DateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");

        String datos = profesor.getApellidos().toUpperCase() + ";"
                + profesor.getNombres().toUpperCase() + ";"
                + "Identificaciòn: " + idProfesor + ";"
                + diccionario.getTitulo() + ";"
                + diccionario.getCodbarradiccionario() + ";"
                + formatoFecha.format((Date) devProf.getFechaDevolucion()) + ";"
                + "null;"
                + "null;"
                + profesor.getCorreoelectronico();

        NotificacionEmail em = new NotificacionEmail();
        em.gestionarNotificacion(datos, "mensajeDevolucion");
    }

}

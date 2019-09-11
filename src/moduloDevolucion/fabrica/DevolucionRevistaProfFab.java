package moduloDevolucion.fabrica;

import controller.exceptions.NonexistentEntityException;
import general.modelo.NotificacionEmail;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import moduloDevolucion.DAO.DevolucionRevistaDAOProf;
import moduloDevolucion.modelo.IDevolucion;
import moduloDevolucion.entitys.DevolucionRevistaProf;
import moduloPrestamo.DAO.PrestamoRevistaDAOProf;
import moduloPrestamo.entitys.PrestamoRevistaProf;
import recursos.controllers.RevistaJpaController;
import recursos.entitys.Revista;
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
public class DevolucionRevistaProfFab implements IDevolucion {

    public DevolucionRevistaProfFab() {

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
                    PrestamoRevistaDAOProf prestDAOProf = new PrestamoRevistaDAOProf();
                    PrestamoRevistaProf prestProf = prestDAOProf.readDAO(codPrestamo);
                    if (prestProf.getDevuelto().equalsIgnoreCase("no")) {
                        DevolucionRevistaProf devProf = new DevolucionRevistaProf(prestProf.getCodPrestamoRevistaProf(),
                                idBibliotecario, estadoRecurso);
                        DevolucionRevistaDAOProf devDAOProf = new DevolucionRevistaDAOProf();
                        devDAOProf.createDAO(devProf);

                        revista.setDisponibilidad("disponible");
                        control.edit(revista);

                        prestProf.setDevuelto("si");
                        prestDAOProf.updateDAO(prestProf);

                        notificarDevolucion(prestProf.getIdProfesor(), revista, codPrestamo);
                        alert.showAlert("Anuncio", "Devolución revista", "La devolución del usuario con codigo"
                                + prestProf.getIdProfesor() + "se realizo con exito");
                    } else {
                        alert.showAlert("Anuncio", "Devolución revista", "La revista se había devuelto anteriormente");
                    }
                    return true;
                } else {
                    alert.showAlert("Anuncio", "Devolución revista", "El prestamo de la revista no existe");
                }
            } else {
                alert.showAlert("Anuncio", "Devolución revista", "La revista no existe");
            }
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(DevolucionDiccionarioEstFab.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(DevolucionDiccionarioEstFab.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public int consultarPrestamoRevista(String codBarras) {
        PrestamoRevistaDAOProf prestDAOProf = new PrestamoRevistaDAOProf();
        List<PrestamoRevistaProf> prestamos = prestDAOProf.readAllDAO();
        int codPrestamo = 0;
        for (int i = 0; i < prestamos.size(); i++) {
            if (prestamos.get(i).getCodBarraRevista().equalsIgnoreCase(codBarras)
                    && prestamos.get(i).getDevuelto().equalsIgnoreCase("no")) {
                codPrestamo = prestamos.get(i).getCodPrestamoRevistaProf();
                break;
            }
        }
        return codPrestamo;
    }

    /**
     * el método realiza concatenación de los datos necesarios para la
     * construcción del e-mail al profesor, notificandole de la devoluciòn de la
     * revista.
     *
     * @param idProfesor
     * @param revista
     * @param codPrestamo
     */
    public void notificarDevolucion(String idProfesor, Revista revista, int codPrestamo) {
        ProfesorJpaController profesorJpaController = new ProfesorJpaController();
        Profesor profesor = profesorJpaController.findProfesor(idProfesor);

        DevolucionRevistaDAOProf devDAOProf = new DevolucionRevistaDAOProf();
        DevolucionRevistaProf devProf = devDAOProf.readDAO(devDAOProf.readCodigoDAO(codPrestamo));

        DateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");

        String datos = profesor.getApellidos().toUpperCase() + ";"
                + profesor.getNombres().toUpperCase() + ";"
                + "Identificaciòn: " + idProfesor + ";"
                + revista.getTitulo() + ";"
                + revista.getCodbarrarevista() + ";"
                + formatoFecha.format((Date) devProf.getFechaDevolucion()) + ";"
                + "null;"
                + "null;"
                + profesor.getCorreoelectronico();

        NotificacionEmail em = new NotificacionEmail();
        em.gestionarNotificacion(datos, "mensajeDevolucion");
    }
    
}

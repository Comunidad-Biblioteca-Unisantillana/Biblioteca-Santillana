package moduloDevolucion.fabrica;

import controller.exceptions.NonexistentEntityException;
import general.modelo.NotificacionEmail;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import moduloDevolucion.DAO.DevolucionEnciclopediaDAOProf;
import moduloDevolucion.modelo.IDevolucion;
import moduloDevolucion.entitys.DevolucionEnciclopediaProf;
import moduloPrestamo.DAO.PrestamoEnciclopediaDAOProf;
import moduloPrestamo.entitys.PrestamoEnciclopediaProf;
import recursos.controllers.EnciclopediaJpaController;
import recursos.entitys.Enciclopedia;
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
public class DevolucionEnciclopediaProfFab implements IDevolucion {

    public DevolucionEnciclopediaProfFab() {

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

                    PrestamoEnciclopediaDAOProf prestDAOProf = new PrestamoEnciclopediaDAOProf();
                    PrestamoEnciclopediaProf prestProf = prestDAOProf.readDAO(codPrestamo);
                    if (prestProf.getDevuelto().equalsIgnoreCase("no")) {
                        DevolucionEnciclopediaProf devProf = new DevolucionEnciclopediaProf(prestProf.getCodPrestamoEnciclopediaProf(),
                                idBibliotecario, estadoRecurso);
                        DevolucionEnciclopediaDAOProf devDAOProf = new DevolucionEnciclopediaDAOProf();
                        devDAOProf.createDAO(devProf);

                        enciclopedia.setDisponibilidad("disponible");
                        control.edit(enciclopedia);

                        prestProf.setDevuelto("si");
                        prestDAOProf.updateDAO(prestProf);

                        notificarDevolucion(prestProf.getIdProfesor(), enciclopedia, codPrestamo);
                        alert.showAlert("Anuncio", "Devolución enciclopedia", "La devolución del usuario con codigo"
                                + prestProf.getIdProfesor() + "se realizo con exito");
                    } else {
                        alert.showAlert("Anuncio", "Devolución enciclopedia", "La enciclopedia se había devuelto anteriormente");
                    }
                    return true;
                } else {
                    alert.showAlert("Anuncio", "Devolución enciclopedia", "El prestamo de la enciclopedia no existe");
                }
            } else {
                alert.showAlert("Anuncio", "Devolución enciclopedia", "La enciclopedia no existe");
            }
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(DevolucionDiccionarioEstFab.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(DevolucionDiccionarioEstFab.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public int consultarPrestamoEnciclopedia(String codBarras) {
        PrestamoEnciclopediaDAOProf prestDAOProf = new PrestamoEnciclopediaDAOProf();
        List<PrestamoEnciclopediaProf> prestamos = prestDAOProf.readAllDAO();
        int codPrestamo = 0;
        for (int i = 0; i < prestamos.size(); i++) {
            if (prestamos.get(i).getCodBarraEnciclopedia().equalsIgnoreCase(codBarras)
                    && prestamos.get(i).getDevuelto().equalsIgnoreCase("no")) {
                codPrestamo = prestamos.get(i).getCodPrestamoEnciclopediaProf();
                break;
            }
        }
        return codPrestamo;
    }

    /**
     * el método realiza concatenación de los datos necesarios para la
     * construcción del e-mail al profesor, notificandole de la devoluciòn de la
     * enciclopedia.
     *
     * @param idProfesor
     * @param enciclopedia
     * @param codPrestamo
     */
    public void notificarDevolucion(String idProfesor, Enciclopedia enciclopedia, int codPrestamo) {
        ProfesorJpaController profesorJpaController = new ProfesorJpaController();
        Profesor profesor = profesorJpaController.findProfesor(idProfesor);

        DevolucionEnciclopediaDAOProf devDAOProf = new DevolucionEnciclopediaDAOProf();
        DevolucionEnciclopediaProf devProf = devDAOProf.readDAO(devDAOProf.readCodigoDAO(codPrestamo));

        DateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");

        String datos = profesor.getApellidos().toUpperCase() + ";"
                + profesor.getNombres().toUpperCase() + ";"
                + "Identificaciòn: " + idProfesor + ";"
                + enciclopedia.getTitulo() + ";"
                + enciclopedia.getCodbarraenciclopedia() + ";"
                + formatoFecha.format((Date) devProf.getFechaDevolucion()) + ";"
                + "null;"
                + "null;"
                + profesor.getCorreoelectronico();

        NotificacionEmail notificacionEmail = new NotificacionEmail(datos, "mensajeDevolucion");
        notificacionEmail.start();
    }

}

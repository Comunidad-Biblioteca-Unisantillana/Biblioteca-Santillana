package moduloDevolucion.fabrica;

import controller.exceptions.NonexistentEntityException;
import java.sql.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import moduloDevolucion.DAO.DevolucionPeriodicoDAOProf;
import moduloDevolucion.IDevolucion;
import moduloDevolucion.entitys.DevolucionPeriodicoProf;
import moduloPrestamo.DAO.PrestamoPeriodicoDAOProf;
import moduloPrestamo.entitys.PrestamoPeriodicoProf;
import recursos1.controllers.PeriodicoJpaController;
import recursos1.entitys.Periodico;
import vista.AlertBox;
import vista.IAlertBox;

/**
 * @author Camilo Jaramillo
 * @version 1.0
 * @created 04-ago.-2019 10:37:55 a. m.
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
                        java.util.Date fechaDevolucion =  new java.util.Date();
                        
                        DevolucionPeriodicoProf devProf = new DevolucionPeriodicoProf(prestProf.getCodPrestamoPeriodicoProf(),
                                idBibliotecario, new Date(fechaDevolucion.getTime()), estadoRecurso);
                        DevolucionPeriodicoDAOProf devDAOProf = new DevolucionPeriodicoDAOProf();
                        devDAOProf.createDAO(devProf);

                        periodico.setDisponibilidad("disponible");
                        control.edit(periodico);

                        prestProf.setDevuelto("si");
                        prestDAOProf.updateDAO(prestProf);
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
            if (prestamos.get(i).getCodBarraPeriodico().equalsIgnoreCase(codBarras)) {
                codPrestamo = prestamos.get(i).getCodPrestamoPeriodicoProf();
                break;
            }
        }
        return codPrestamo;
    }

    public void notificarDevolucion(String idProfesor, String tituloRecurso, java.util.Date fechaDevolucion) {

    }
}

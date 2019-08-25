package moduloDevolucion.fabrica;

import controller.exceptions.NonexistentEntityException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import moduloDevolucion.DAO.DevolucionLibroDAOEst;
import moduloDevolucion.IDevolucion;
import moduloDevolucion.entitys.DevolucionLibroEst;
import moduloPrestamo.DAO.PrestamoLibroDAOEst;
import moduloPrestamo.entitys.PrestamoLibroEst;
import recursos1.controllers.LibroJpaController;
import recursos1.entitys.Libro;
import vista.AlertBox;
import vista.IAlertBox;

/**
 * @author Camilo Jaramillo
 * @version 1.0
 * @created 04-ago.-2019 10:37:38 a. m.
 */
public class DevolucionLibroEstFab implements IDevolucion {

    public DevolucionLibroEstFab() {

    }

    @Override
    public boolean ejecutarDevolucion(String codBarras, String idBibliotecario, String estadoRecurso) {
        IAlertBox alert = new AlertBox();
        try {
            LibroJpaController control = new LibroJpaController();
            Libro libro = control.findLibro(codBarras);
            if (libro != null) {
                int codPrestamo = consultarPrestamoLibro(codBarras);
                if (codPrestamo > 0) {

                    PrestamoLibroDAOEst prestDAOEst = new PrestamoLibroDAOEst();
                    PrestamoLibroEst prestEst = prestDAOEst.readDAO(codPrestamo);
                    if (prestEst.getDevuelto().equalsIgnoreCase("no")) {
                        DevolucionLibroEst devEst = new DevolucionLibroEst(prestEst.getCodPrestamoLibroEst(), idBibliotecario, null, estadoRecurso);
                        DevolucionLibroDAOEst devDAOEst = new DevolucionLibroDAOEst();
                        devDAOEst.createDAO(devEst);
                        
                        libro.setDisponibilidad("disponible");
                        control.edit(libro);
                        
                        prestEst.setDevuelto("si");
                        System.out.println("hole");
                        prestDAOEst.updateDAO(prestEst);
                        alert.showAlert("Anuncio", "Devolución libro", "La devolución del usuario con codigo"
                                + prestEst.getCodEstudiante() + "se realizo con exito");
                    } else {
                        alert.showAlert("Anuncio", "Devolución libro", "El libro se había devuelto anteriormente");
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

    public int consultarPrestamoLibro(String codBarras) {
        PrestamoLibroDAOEst prestDAOEst = new PrestamoLibroDAOEst();
        List<PrestamoLibroEst> prestamos = prestDAOEst.readAllDAO();
        int codPrestamo = 0;
        for (int i = 0; i < prestamos.size(); i++) {
            if (prestamos.get(i).getCodBarraLibro().equalsIgnoreCase(codBarras)) {
                codPrestamo = prestamos.get(i).getCodPrestamoLibroEst();
                break;
            }
        }
        return codPrestamo;
    }

    public boolean verificarReserva(String codBarras) {
        return false;
    }

    public void notificarDevolucion(String codEstudiante, String tituloRecurso, java.util.Date fechaDevolucion) {

    }
}

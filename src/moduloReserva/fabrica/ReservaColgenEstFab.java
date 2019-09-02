package moduloReserva.fabrica;

import java.sql.Date;
import general.modelo.ServicioFecha;
import moduloPrestamo.DAO.PrestamoLibroDAOEst;
import moduloPrestamo.entitys.PrestamoLibroEst;
import moduloReserva.DAO.ReservaColgenDAOEst;
import moduloReserva.entitys.ReservaColgenEstudiante;
import moduloReserva.modelo.IReserva;
import recursos.controllers.LibroJpaController;
import recursos.entitys.Libro;
import general.vista.AlertBox;
import general.vista.IAlertBox;

/**
 * Clase que se encarga de realizar una reserva de estudiante
 * @author Julian
 * @creado: 24/08/2019
 * @modificado:26/08/2019
 */
public class ReservaColgenEstFab implements IReserva {

    /**
     * Método que se encarga de realizar una reserva de estudiante
     * @param codBarras
     * @param codUsuario
     * @param idBibliotecario
     * @return 
     */
    @Override
    public boolean ejecutarReserva(String codBarras, String codUsuario, String idBibliotecario) {
        IAlertBox alert = new AlertBox();
        try {
            LibroJpaController control = new LibroJpaController();
            Libro libro = control.findLibro(codBarras);
            if (libro != null) {
                if (libro.getDisponibilidad().equalsIgnoreCase("prestado")) {
                    if (libro.getCodcategoriacoleccion().getCodcategoriacoleccion().equalsIgnoreCase("colgen")) {

                        PrestamoLibroDAOEst presDAO = new PrestamoLibroDAOEst();
                        PrestamoLibroEst prestamo = presDAO.readDAO(presDAO.readCodigoDAO(codBarras));
                        
                        if (prestamo != null) {
                            
                            java.util.Date fechaActual = new java.util.Date();

                            ReservaColgenEstudiante reserva = new ReservaColgenEstudiante(codBarras, codUsuario, idBibliotecario,
                                    new Date(fechaActual.getTime()));

                            ReservaColgenDAOEst resDAO = new ReservaColgenDAOEst();
                            resDAO.createDAO(reserva);
                            return true;
                        } else {
                            System.out.println("El prestamo es null");
                        }
                    } else {
                        alert.showAlert("Anuncio", "Reserva estudiante", "El libro no se puede reservar, no es de colección general");
                    }
                } else {
                    alert.showAlert("Anuncio", "Reserva estudiante", "El libro no se puede reservar, no se encuentra prestado");
                }
            } else {
                alert.showAlert("Anuncio", "Libro", "No existe un libro con ese código en la base de datos");
            }
        } catch (Exception e) {
        }
        return false;
    }

}

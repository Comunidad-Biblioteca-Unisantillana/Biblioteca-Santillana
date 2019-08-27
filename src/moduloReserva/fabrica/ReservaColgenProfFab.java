
package moduloReserva.fabrica;

import java.sql.Date;
import modelo.ServicioFecha;
import moduloPrestamo.DAO.PrestamoLibroDAOProf;
import moduloPrestamo.entitys.PrestamoLibroProf;
import moduloReserva.DAO.ReservaColgenDAOProf;
import moduloReserva.entitys.ReservaColgenProfesor;
import moduloReserva.modelo.IReserva;
import recursos1.controllers.LibroJpaController;
import recursos1.entitys.Libro;
import vista.AlertBox;
import vista.IAlertBox;

/**
 *
 * @author Julian
 */
public class ReservaColgenProfFab implements IReserva {

    @Override
    public boolean ejecutarReserva(String codBarras, String codUsuario, String idBibliotecario) {
        IAlertBox alert = new AlertBox();
        try {
            LibroJpaController control = new LibroJpaController();
            Libro libro = control.findLibro(codBarras);
            if (libro != null) {
                if (libro.getDisponibilidad().equalsIgnoreCase("prestado")) {
                    if (libro.getCodcategoriacoleccion().getCodcategoriacoleccion().equalsIgnoreCase("colgen")) {

                        PrestamoLibroDAOProf presDAO = new PrestamoLibroDAOProf();
                        PrestamoLibroProf prestamo = presDAO.readDAO(presDAO.readCodigoDAO(codBarras));
                        
                        if (prestamo != null) {
                            
                            java.util.Date fechaActual = new java.util.Date();
                            java.util.Date fechaLimiteReserva = ServicioFecha.sumarDiasAFecha(prestamo.getFechaDevolucion(), 5);

                            ReservaColgenProfesor reserva = new ReservaColgenProfesor(codBarras, codUsuario, idBibliotecario,
                                    new Date(fechaActual.getTime()));
                            reserva.setFechaLimiteReserva(new Date( fechaLimiteReserva.getTime()));

                            ReservaColgenDAOProf resDAO = new ReservaColgenDAOProf();
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

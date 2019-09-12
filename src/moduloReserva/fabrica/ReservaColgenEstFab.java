package moduloReserva.fabrica;

import moduloPrestamo.DAO.PrestamoLibroDAOEst;
import moduloPrestamo.entitys.PrestamoLibroEst;
import moduloReserva.DAO.ReservaColgenDAOEst;
import moduloReserva.entitys.ReservaColgenEstudiante;
import moduloReserva.modelo.IReserva;
import recursos.controllers.LibroJpaController;
import recursos.entitys.Libro;
import general.vista.AlertBox;
import general.vista.IAlertBox;
import moduloPrestamo.DAO.PrestamoLibroDAOProf;
import moduloPrestamo.DAO.PrestamoRecursoDAOAbs;
import moduloPrestamo.entitys.PrestamoLibroProf;
import moduloReserva.modelo.VerificaReserva;

/**
 * Clase que se encarga de realizar una reserva de estudiante
 *
 * @author Julian
 * @creado: 24/08/2019
 * @author Miguel Fernández
 * @modificado: 07/09/2019
 */
public class ReservaColgenEstFab implements IReserva {

    /**
     * el metódo se encarga de verificar si existe una reserva del recurso a
     * reservar.
     *
     * @param codBarras
     * @return boolean
     */
    public boolean consultarReservas(String codBarras) {
        VerificaReserva verificaReserva = new VerificaReserva();

        if (!verificaReserva.verificarReservaEst(codBarras)) {
            if (!verificaReserva.verificarReservaProf(codBarras)) {
                return false;
            }
        }

        return true;
    }

    /**
     * Método que se encarga de realizar una reserva de estudiante
     *
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
                        if (!consultarReservas(codBarras)) {
                            PrestamoRecursoDAOAbs presDAO = new PrestamoLibroDAOEst();
                            PrestamoLibroEst prestamo = (PrestamoLibroEst) presDAO.readDAO(presDAO.readCodigoDAO(codBarras));
                            presDAO = new PrestamoLibroDAOProf();
                            PrestamoLibroProf prestamo1 = (PrestamoLibroProf) presDAO.readDAO(presDAO.readCodigoDAO(codBarras));

                            if (prestamo != null || prestamo1 != null) {
                                ReservaColgenEstudiante reserva = new ReservaColgenEstudiante(codBarras, codUsuario, idBibliotecario);

                                ReservaColgenDAOEst resDAO = new ReservaColgenDAOEst();
                                resDAO.createDAO(reserva);
                                return true;
                            } else {
                                System.out.println("El prestamo es null");
                            }
                        } else {
                            alert.showAlert("Anuncio", "Libro reservado", "El libro con el código: " + codBarras
                                    + " , se encuentra reservado por otro usuario.");
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

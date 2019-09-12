package moduloReserva.fabrica;

import moduloPrestamo.DAO.PrestamoLibroDAOProf;
import moduloPrestamo.entitys.PrestamoLibroProf;
import moduloReserva.DAO.ReservaColgenDAOProf;
import moduloReserva.entitys.ReservaColgenProfesor;
import moduloReserva.modelo.IReserva;
import recursos.controllers.LibroJpaController;
import recursos.entitys.Libro;
import general.vista.AlertBox;
import general.vista.IAlertBox;
import moduloPrestamo.DAO.PrestamoLibroDAOEst;
import moduloPrestamo.DAO.PrestamoRecursoDAOAbs;
import moduloPrestamo.entitys.PrestamoLibroEst;
import moduloReserva.modelo.VerificaReserva;

/**
 *
 * @author Julian
 * @creado:
 * @author Miguel Fernández
 * @modificado: 07/09/2019
 */
public class ReservaColgenProfFab implements IReserva {

    /**
     * el metódo se encarga de verificar si existe una reserva del recurso a
     * reservar.
     *
     * @param codBarras
     * @return boolean
     */
    private boolean consultarReservas(String codBarras) {
        VerificaReserva verificaReserva = new VerificaReserva();

        if (!verificaReserva.verificarReservaEst(codBarras)) {
            if (!verificaReserva.verificarReservaProf(codBarras)) {
                return false;
            }
        }

        return true;
    }

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
                            PrestamoRecursoDAOAbs presDAO = new PrestamoLibroDAOProf();
                            PrestamoLibroProf prestamo = (PrestamoLibroProf) presDAO.readDAO(presDAO.readCodigoDAO(codBarras));
                            presDAO = new PrestamoLibroDAOEst();
                            PrestamoLibroEst prestamo1 = (PrestamoLibroEst) presDAO.readDAO(presDAO.readCodigoDAO(codBarras));

                            if (prestamo != null || prestamo1 != null) {
                                String codUser = (prestamo == null) ? prestamo1.getCodEstudiante() : prestamo.getIdProfesor();

                                if (!codUser.equals(codUsuario)) {
                                    ReservaColgenProfesor reserva = new ReservaColgenProfesor(codBarras, idBibliotecario, codUsuario);

                                    ReservaColgenDAOProf resDAO = new ReservaColgenDAOProf();
                                    resDAO.createDAO(reserva);
                                    System.out.println("Actualizando dispinibilidad.....");
                                    libro.setDisponibilidad("reservado");
                                    control.edit(libro);
                                    return true;
                                } else {
                                    alert.showAlert("Anuncio", "Profesor con préstamo", "El profesor: " + codUsuario
                                            + ", no puede reservar el libro que se le ha prestado.");
                                }
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

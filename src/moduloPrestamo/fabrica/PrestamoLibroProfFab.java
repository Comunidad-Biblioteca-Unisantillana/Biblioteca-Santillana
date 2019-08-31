package moduloPrestamo.fabrica;

import moduloPrestamo.modelo.IPrestamo;
import moduloPrestamo.entitys.PrestamoLibroProf;
import recursos.controllers.LibroJpaController;
import recursos.entitys.Libro;
import java.sql.Date;
import moduloPrestamo.DAO.PrestamoLibroDAOProf;
import general.vista.AlertBox;
import general.vista.IAlertBox;
import moduloReserva.DAO.ReservaColgenDAOProf;
import moduloReserva.entitys.ReservaColgenProfesor;
import moduloReserva.modelo.VerificaReserva;

/**
 * la clase se encarga gestionar el préstamo del libro al profesor.
 *
 * @author Julian
 * @creado
 * @author Miguel Fernández
 * @modificado 24/08/2019
 */
public class PrestamoLibroProfFab implements IPrestamo {

    /**
     * constuctor de la clase sin parámetros.
     */
    public PrestamoLibroProfFab() {

    }

    /**
     * el metódo realiza el préstamo del libro al profesor.
     *
     * @param codBarras
     * @param codUsuario
     * @param idBibliotecario
     * @return estadoPrestamo
     */
    @Override
    public boolean ejecutarPrestamo(String codBarras, String codUsuario, String idBibliotecario) {
        IAlertBox alert = new AlertBox();
        boolean estadoPrestamo = false;

        try {
            LibroJpaController control = new LibroJpaController();
            Libro libro = control.findLibro(codBarras);

            if (libro != null) {
                if (libro.getDisponibilidad().equalsIgnoreCase("disponible")) {
                    int diasPrestamo = libro.getCodcategoriacoleccion().getNombrecol().equalsIgnoreCase("general") ? 15 : 2;// falta organizarlo

                    PrestamoLibroProf prestLibProf = new PrestamoLibroProf();
                    prestLibProf.setCodBarraLibro(codBarras);
                    prestLibProf.setIdProfesor(codUsuario);
                    prestLibProf.setIdBibliotecario(idBibliotecario);
                    prestLibProf.setNumRenovaciones(0);

                    PrestamoLibroDAOProf prestLibDAOProf = new PrestamoLibroDAOProf(diasPrestamo);

                    if (prestLibDAOProf.createDAO(prestLibProf)) {
                        libro.setDisponibilidad("prestado");
                        control.edit(libro);

                        //espacio para enviar el correo electronico                        
                        estadoPrestamo = true;
                    }
                } else if (libro.getDisponibilidad().equalsIgnoreCase("reservado")) {
                    ReservaColgenDAOProf reservaColgenDAOProf = new ReservaColgenDAOProf();
                    ReservaColgenProfesor reserva = reservaColgenDAOProf.readDAO(codBarras);

                    if (reserva.getIdProfesor().equalsIgnoreCase(codUsuario)) {
                        PrestamoLibroProf prestLibProf = new PrestamoLibroProf();
                        prestLibProf.setCodBarraLibro(codBarras);
                        prestLibProf.setIdProfesor(codUsuario);
                        prestLibProf.setIdBibliotecario(idBibliotecario);
                        prestLibProf.setNumRenovaciones(0);

                        PrestamoLibroDAOProf prestLibDAOProf = new PrestamoLibroDAOProf(15);

                        if (prestLibDAOProf.createDAO(prestLibProf)) {
                            libro.setDisponibilidad("prestado");
                            control.edit(libro);
                            VerificaReserva verificaReserva = new VerificaReserva();

                            if (!verificaReserva.liberarReservaAPrestamoEst(codUsuario, codBarras)) {
                                System.out.println("Error al eliminar la reserva del libro del profesor, después de generar el préstamo.");
                            }

                            //espacio para enviar el correo electronico                        
                            estadoPrestamo = true;
                        }
                    } else {
                        alert.showAlert("Anuncio", "Libro reservado", "El libro con el código: " + codBarras
                                + " , se encuentra reservado por otro usuario.");
                    }
                } else if (libro.getDisponibilidad().equalsIgnoreCase("prestado")) {
                    alert.showAlert("Anuncio", "Préstamo libro", "El libro: " + codBarras
                            + ", se encuentra préstado a otro usuario.");
                } else if (libro.getDisponibilidad().equalsIgnoreCase("vencido")) {
                    alert.showAlert("Anuncio", "Préstamo libro", "El libro: " + codBarras
                            + ", no ha sido devuelto por el usuario al que se le presto.");
                }
            } else {
                alert.showAlert("Anuncio", "Préstamo libro", "No se encuentró un libro "
                        + "asociado al código: " + codBarras + ".");
            }
        } catch (Exception e) {
            System.out.println("Error al generar el préstamo del libro a un profesor");
        }

        return estadoPrestamo;
    }

    /**
     * el metódo realiza la construccción del e-mail al profesor, notificandole
     * el préstamo del libro.
     *
     * @param idProfesor
     * @param tituloRecurso
     * @param fechaPrestamo
     * @param fechaDevolucion
     */
    public void notificarPrestamoEmail(String idProfesor, String tituloRecurso, Date fechaPrestamo, Date fechaDevolucion) {

    }

}

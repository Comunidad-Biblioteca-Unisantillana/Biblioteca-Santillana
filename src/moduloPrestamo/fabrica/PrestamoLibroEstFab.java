package moduloPrestamo.fabrica;

import moduloPrestamo.modelo.IPrestamo;
import moduloPrestamo.entitys.PrestamoLibroEst;
import recursos.controllers.LibroJpaController;
import recursos.entitys.Libro;
import java.sql.Date;
import moduloPrestamo.DAO.PrestamoLibroDAOEst;
import general.vista.AlertBox;
import general.vista.IAlertBox;
import moduloReserva.DAO.ReservaColgenDAOEst;
import moduloReserva.entitys.ReservaColgenEstudiante;
import moduloReserva.modelo.VerificaReserva;

/**
 * la clase se encarga gestionar el préstamo del libro al estudiante.
 *
 * @author Julian
 * @creado
 * @author Miguel Fernández
 * @modificado 24/08/2019
 */
public class PrestamoLibroEstFab implements IPrestamo {

    /**
     * constructor de la clase sin parámetros.
     */
    public PrestamoLibroEstFab() {

    }

    /**
     * el metódo realiza el préstamo del libro al estudiante.
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

                    PrestamoLibroEst prestLibEst = new PrestamoLibroEst();
                    prestLibEst.setCodBarraLibro(codBarras);
                    prestLibEst.setCodEstudiante(codUsuario);
                    prestLibEst.setIdBibliotecario(idBibliotecario);
                    prestLibEst.setNumRenovaciones(0);

                    PrestamoLibroDAOEst prestLibDAOEst = new PrestamoLibroDAOEst(diasPrestamo);

                    if (prestLibDAOEst.createDAO(prestLibEst)) {
                        libro.setDisponibilidad("prestado");
                        control.edit(libro);

                        //espacio para enviar el correo
                        estadoPrestamo = true;
                    }
                } else if (libro.getDisponibilidad().equalsIgnoreCase("reservado")) {
                    ReservaColgenDAOEst reservaColgenDAOEst = new ReservaColgenDAOEst();
                    ReservaColgenEstudiante reserva = reservaColgenDAOEst.readDAO(codBarras);
                    
                    if (reserva.getCodEstudiante().equalsIgnoreCase(codUsuario)) {
                        PrestamoLibroEst prestLibEst = new PrestamoLibroEst();
                        prestLibEst.setCodBarraLibro(codBarras);
                        prestLibEst.setCodEstudiante(codUsuario);
                        prestLibEst.setIdBibliotecario(idBibliotecario);
                        prestLibEst.setNumRenovaciones(0);

                        PrestamoLibroDAOEst prestLibDAOEst = new PrestamoLibroDAOEst(15);

                        if (prestLibDAOEst.createDAO(prestLibEst)) {
                            libro.setDisponibilidad("prestado");
                            control.edit(libro);
                            VerificaReserva verificaReserva = new VerificaReserva();

                            if (!verificaReserva.liberarReservaAPrestamoEst(codUsuario, codBarras)) {
                                System.out.println("Error al eliminar la reserva del libro del estudiante, después de generar el préstamo.");
                            }

                            //espacio para enviar el correo
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
            System.out.println("Error al generar el préstamo del libro a un estudiante");
        }

        return estadoPrestamo;
    }

    /**
     * el metódo realiza la construccción del e-mail al estudiante,
     * notificandole el préstamo del libro.
     *
     * @param idProfesor
     * @param tituloRecurso
     * @param fechaPrestamo
     * @param fechaDevolucion
     */
    public void notificarPrestamoEmail(String idProfesor, String tituloRecurso, Date fechaPrestamo, Date fechaDevolucion) {

    }

}

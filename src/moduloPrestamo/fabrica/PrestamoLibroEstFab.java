package moduloPrestamo.fabrica;

import moduloPrestamo.entitys.PrestamoLibroEst;
import recursos1.controllers.LibroJpaController;
import recursos1.entitys.Libro;
import java.sql.Date;
import moduloPrestamo.DAO.PrestamoLibroDAOEst;
import moduloPrestamo.IPrestamo;
import vista.AlertBox;
import vista.IAlertBox;

/**
 *
 * @author Julian
 */
public class PrestamoLibroEstFab implements IPrestamo {

    public PrestamoLibroEstFab() {

    }

    @Override
    public boolean ejecutarPrestamo(String codBarras, String codUsuario, String idBibliotecario) {
        IAlertBox alert = new AlertBox();
        try {
            LibroJpaController control = new LibroJpaController();
            Libro libro = control.findLibro(codBarras);
            if (libro != null) {
                if (libro.getDisponibilidad().equalsIgnoreCase("disponible")) {

                    java.util.Date fechaActual = new java.util.Date();
                    int diasPrestamo = libro.getCodcategoriacoleccion().getCodcategoriacoleccion().equalsIgnoreCase("colgen") ? 15 : 2;

                    PrestamoLibroEst prestLibEst = new PrestamoLibroEst(codBarras, codUsuario, idBibliotecario,
                            new Date(fechaActual.getTime()), null);

                    PrestamoLibroDAOEst prestLibDAOEst = new PrestamoLibroDAOEst(diasPrestamo);
                    if (prestLibDAOEst.createDAO(prestLibEst)) {
                        System.out.println("Cambiando disponibilidad del libro...");
                        
                        libro.setDisponibilidad("prestado");
                        control.edit(libro);
                        return true;
                    }
                } else {
                    alert.showAlert("Anuncio", "Prestamo libro", "el libro no se encuentra disponible");
                }
            } else {
                alert.showAlert("Anuncio", "Prestamo libro", "Ningun libro tiene este codigo de barra");
            }
        } catch (Exception e) {
            System.out.println("error al generar el prestamo de libro de un estudiante");
        }
        return false;
    }

    public void notificarPrestamoEmail(String idProfesor, String tituloRecurso, java.util.Date fechaPrestamo, java.util.Date fechaDevolucion) {

    }
}

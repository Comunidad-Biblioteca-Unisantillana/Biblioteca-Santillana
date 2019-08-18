package moduloPrestamo.fabrica;

import moduloPrestamo.entitys.PrestamoLibroProf;
import controllers.LibroJpaController;
import entitys.Libro;
import java.sql.Date;
import modelo.QueryRecurso;
import moduloPrestamo.DAO.PrestamoLibroDAOProf;
import moduloPrestamo.IPrestamo;

/**
 *
 * @author Julian
 */
public class PrestamoLibroProfFab implements IPrestamo {

    public PrestamoLibroProfFab() {

    }

    @Override
    public boolean ejecutarPrestamo(String codBarras, String codUsuario, String idBibliotecario) {
        try {
            Libro libro = QueryRecurso.consultarLibro(codBarras);
            if (libro != null) {
                if (libro.getDisponibilidad().equalsIgnoreCase("disponible")) {

                    java.util.Date fechaActual = new java.util.Date();
                    java.util.Date fechaDevolucion = new java.util.Date();
                    int diasPrestamo = libro.getCodcategoriacoleccion().getCodcategoriacoleccion().equalsIgnoreCase("colgen") ? 15 : 2;

                    PrestamoLibroProf prestLibProf = new PrestamoLibroProf(codBarras, codUsuario, idBibliotecario,
                            new Date(fechaActual.getTime()), new Date(fechaDevolucion.getTime()));
                    PrestamoLibroDAOProf prestLibDAOProf = new PrestamoLibroDAOProf(diasPrestamo);

                    if (prestLibDAOProf.createDAO(prestLibProf)) {
                        System.out.println("Cambiando disponibilidad del libro...");
                        LibroJpaController control = new LibroJpaController();
                        libro.setDisponibilidad("prestado");
                        control.edit(libro);
                        return true;
                    }
                } else {
                    System.out.println("el libro no se encuentra disponible");
                }
            } else {
                System.out.println("Ningun libro tiene este codigo de barra");
            }
        } catch (Exception e) {
            System.out.println("error al generar el prestamo de libro de un profesor");
        }
        return false;
    }

    public void notificarPrestamoEmail(String idProfesor, String tituloRecurso, java.util.Date fechaPrestamo, java.util.Date fechaDevolucion) {

    }
}

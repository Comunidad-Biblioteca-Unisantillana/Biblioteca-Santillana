package moduloPrestamo;

import javafx.scene.control.TextField;
import modeloDAO.EstudianteDAO;
import modeloDAO.ProfesorDAO;

/**
 * Clase que se encarga de generar un prestamo de un recurso
 *
 * @author Julian
 */
public class GeneradorPrestamo {

    public GeneradorPrestamo() {

    }

    public void createPrestamo(String codBarra, String codUsuario, String idBibliotecario, String tipoPrestamo, String tipoUsuario) throws Exception {
        if (tipoUsuario.toLowerCase().equals("estudiante")) {
            EstudianteDAO estDAO = new EstudianteDAO();
            if (estDAO.readDAO(codUsuario) != null) {
                if (generarPrestamoEstudiante(codBarra, codUsuario, idBibliotecario, tipoPrestamo)) {
                    System.out.println("El prestamo se realizo con exito");
                }
            }
        } else if (tipoUsuario.toLowerCase().equals("profesor")) {
            ProfesorDAO profDAO = new ProfesorDAO();
            if (profDAO.readDAO(codUsuario) != null) {
                if (generarPrestamoProfesor(codBarra, codUsuario, idBibliotecario, tipoPrestamo)) {
                    System.out.println("El prestamo se realizo con exito");
                }
            }
        } else {
            System.out.println("El codigo no concuerda con ningun estudiante o profesor");
        }
    }

    private boolean generarPrestamoEstudiante(String codBarras, String codEstudiante, String idBibliotecario, String tipoPrestamo) {
        FabricaPrestamo fabPrestamo = new FabricaPrestamo();
        IPrestamo prestamoEstudiante = fabPrestamo.getPrestamo(tipoPrestamo, "estudiante");
        if (prestamoEstudiante.ejecutarPrestamo(codBarras, codEstudiante, idBibliotecario)) {
            return true;
        }
        return false;
    }

    private boolean generarPrestamoProfesor(String codBarras, String idProfesor, String idBibliotecario, String nombreRecurso)  {
        FabricaPrestamo fabPrestamo = new FabricaPrestamo();
        IPrestamo prestamoEstudiante = fabPrestamo.getPrestamo(nombreRecurso, "profesor");
        if (prestamoEstudiante.ejecutarPrestamo(codBarras, idProfesor, idBibliotecario)) {
            return true;
        }
        return false;
    }

}

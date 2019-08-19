package moduloPrestamo;

import modeloDAO.EstudianteDAO;
import modeloDAO.ProfesorDAO;
import vista.AlertBox;
import vista.IAlertBox;

/**
 * Clase que se encarga de generar un prestamo de un recurso
 *
 * @author Julian <br>
 * Fecha de Creación: 12/08/2018 <br>
 * Fecha de ultima Modificación:
 * 13/08/2019
 */
public class GeneradorPrestamo {

    /**
     * Constructor de la clase
     */
    public GeneradorPrestamo() {

    }

    /**
     * Metodo que se encarga de crear un prestamo
     *
     * @param codBarra
     * @param codUsuario
     * @param idBibliotecario
     * @param tipoPrestamo
     * @param tipoUsuario
     * @throws Exception
     */
    public void createPrestamo(String codBarra, String codUsuario, String idBibliotecario, String tipoPrestamo, String tipoUsuario) throws Exception {
        IAlertBox alert = new AlertBox();
        if (tipoUsuario.toLowerCase().equals("estudiante")) {
            EstudianteDAO estDAO = new EstudianteDAO();
            if (estDAO.readDAO(codUsuario) != null) {
                if (generarPrestamoEstudiante(codBarra, codUsuario, idBibliotecario, tipoPrestamo)) {
                    alert.showAlert("Anuncio", "Prestamo", "El prestamo del estudiante se realizo con exito");
                }
            } else {
                alert.showAlert("Anuncio", "Error usuario", "no hay ningun estudiante con ese codigo");
            }
        } else if (tipoUsuario.toLowerCase().equals("profesor")) {
            ProfesorDAO profDAO = new ProfesorDAO();
            if (profDAO.readDAO(codUsuario) != null) {
                if (generarPrestamoProfesor(codBarra, codUsuario, idBibliotecario, tipoPrestamo)) {
                    alert.showAlert("Anuncio", "Prestamo", "El prestamo del profesor se realizo con exito");
                }
            } else {
                alert.showAlert("Anuncio", "Error usuario", "no hay ningun profesor con esa identificación");
            }
        }
    }

    /**
     * Metodo que genera un prestamo de un estudiante
     *
     * @param codBarras
     * @param codEstudiante
     * @param idBibliotecario
     * @param tipoPrestamo
     * @return
     */
    private boolean generarPrestamoEstudiante(String codBarras, String codEstudiante, String idBibliotecario, String tipoPrestamo) {
        FabricaPrestamo fabPrestamo = new FabricaPrestamo();
        IPrestamo prestamoEstudiante = fabPrestamo.getPrestamo(tipoPrestamo, "estudiante");
        if (prestamoEstudiante.ejecutarPrestamo(codBarras, codEstudiante, idBibliotecario)) {
            return true;
        }
        return false;
    }

    /**
     * Metodo que genera un prestamo de un profesor
     *
     * @param codBarras
     * @param idProfesor
     * @param idBibliotecario
     * @param nombreRecurso
     * @return
     */
    private boolean generarPrestamoProfesor(String codBarras, String idProfesor, String idBibliotecario, String nombreRecurso) {
        FabricaPrestamo fabPrestamo = new FabricaPrestamo();
        IPrestamo prestamoEstudiante = fabPrestamo.getPrestamo(nombreRecurso, "profesor");
        if (prestamoEstudiante.ejecutarPrestamo(codBarras, idProfesor, idBibliotecario)) {
            return true;
        }
        return false;
    }

    /**
     * Metodo que consulta las multas del estudiante por medio de su codigo
     *
     * @param idProfesor
     * @return
     */
    public boolean consultarMultaProfesor(String idProfesor) {
        return false;
    }

    /**
     * Metodo que consulta las multas del profesor por medio de su
     * identificación
     *
     * @param codEstudiante
     * @return
     */
    public boolean consultarMultaEstudiante(String codEstudiante) {
        return false;
    }

}

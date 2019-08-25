package moduloPrestamo.modelo;

import moduloPrestamo.fabrica.IPrestamo;
import moduloPrestamo.fabrica.FabricaPrestamo;
import modeloDAO.EstudianteDAO;
import modeloDAO.ProfesorDAO;
import vista.AlertBox;
import vista.IAlertBox;

/**
 * Clase que se encarga de generar un préstamo de un recurso.
 *
 * @author Julian
 * @creado 12/08/2018
 * @author Miguel Fernández
 * @modificado 23/08/2019
 */
public class GeneradorPrestamo {

    /**
     * Constructor de la clase sin parámetros.
     */
    public GeneradorPrestamo() {

    }

    /**
     * el metódo se encarga de crear un préstamoñ
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
        
        if (tipoUsuario.equalsIgnoreCase("estudiante")) {
            EstudianteDAO estDAO = new EstudianteDAO();
            
            if (estDAO.readDAO(codUsuario) != null) {
                //aqui quedaria el metódo que consulta las multas del estudiante.
                
                if (generarPrestamoEstudiante(codBarra, codUsuario, idBibliotecario, tipoPrestamo)) {
                    alert.showAlert("Anuncio", "Préstamo", "El préstamo del estudiante se realizó con éxito.");
                }
            } else {
                alert.showAlert("Anuncio", "Error usuario", "No hay ningún estudiante asociado al código: " + codUsuario);
            }
        } else if (tipoUsuario.equalsIgnoreCase("profesor")) {
            ProfesorDAO profDAO = new ProfesorDAO();
             //aqui quedaria el metódo que consulta las multas del profesor.
             
            if (profDAO.readDAO(codUsuario) != null) {
                if (generarPrestamoProfesor(codBarra, codUsuario, idBibliotecario, tipoPrestamo)) {
                    alert.showAlert("Anuncio", "Préstamo", "El préstamo del profesor se realizó con éxito.");
                }
            } else {
                alert.showAlert("Anuncio", "Error usuario", "No hay ningún profesor asociado a la identificación: " + codUsuario);
            }
        }
    }

    /**
     * el metódo genera un préstamo de un estudiante.
     *
     * @param codBarras
     * @param codEstudiante
     * @param idBibliotecario
     * @param tipoPrestamo
     * @return
     */
    private boolean generarPrestamoEstudiante(String codBarras, String codEstudiante, String idBibliotecario, String tipoPrestamo) {
        FabricaPrestamo fabPrestamo = new FabricaPrestamo();
        IPrestamo prestamoEstudiante = fabPrestamo.getPrestamo(tipoPrestamo, "estudiante");//no se tienen encuenta el null

        if (prestamoEstudiante.ejecutarPrestamo(codBarras, codEstudiante, idBibliotecario)) {
            return true;
        }

        return false;
    }

    /**
     * el metdo genera un préstamo de un profesor.
     *
     * @param codBarras
     * @param idProfesor
     * @param idBibliotecario
     * @param nombreRecurso
     * @return
     */
    private boolean generarPrestamoProfesor(String codBarras, String idProfesor, String idBibliotecario, String tipoPrestamo) {
        FabricaPrestamo fabPrestamo = new FabricaPrestamo();
        IPrestamo prestamoEstudiante = fabPrestamo.getPrestamo(tipoPrestamo, "profesor");//no se tienen encuenta el null
        
        if (prestamoEstudiante.ejecutarPrestamo(codBarras, idProfesor, idBibliotecario)) {
            return true;
        }
        
        return false;
    }

    /**
     * el metódo consulta las multas del estudiante por medio de su
     * identificación.
     *
     * @param idProfesor
     * @return
     */
    public boolean consultarMultaProfesor(String idProfesor) {
        return false;
    }

    /**
     * el metódo consulta las multas del profesor por medio de su código.
     *
     * @param codEstudiante
     * @return
     */
    public boolean consultarMultaEstudiante(String codEstudiante) {
        return false;
    }

}

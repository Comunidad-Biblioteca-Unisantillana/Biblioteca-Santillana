package ModuloRenovacion;

/**
 * @author Miguel Fernández
 * @creado 24/08/2019
 * @author Miguel Fernández
 * @modificado 25/08/2019
 */
public class GeneradorRenovacion {

    /**
     * constructor de la clase sin parámetros.
     */
    public GeneradorRenovacion() {

    }

    /**
     * el metódo verifica si el codigo del estudiante ingresado existe en la BD.
     *
     * @param codEstudiante
     * @return boolean
     */
    public boolean consultarExistenciaEstudiante(String codEstudiante) {
        
        return false;
    }

    /**
     * el metódo verifica si el codigo del profesor ingresado existe en la BD.
     *
     * @param idProfesor
     * @return boolean
     */
    public boolean consultarExistenciaProfesor(String idProfesor) {
        return false;
    }

    /**
     * el metódo verifica si el estudiante, cuenta con alguna multa.
     *
     * @param codEstudiante
     * @return boolean
     */
    public boolean consultarMultaEstudiante(String codEstudiante) {
        return false;
    }

    /**
     * el metódo verifica si el profesor, cuenta con alguna multa.
     *
     * @param idProfesor
     * @return boolean
     */
    public boolean consultarMultaProfesor(String idProfesor) {
        return false;
    }

    /**
     * el metódo realiza la renovación de un recurso, solicitado por el usuario.
     *
     * @param codBarras
     * @param codUsuario
     * @param tipoUsuario
     */
    public void createRenovacion(String codBarras, String codUsuario, String tipoUsuario) {
        if (tipoUsuario.equalsIgnoreCase("estudiante")) {
            if (consultarExistenciaEstudiante(codUsuario)) {

            }
        } else {

        }
    }

}

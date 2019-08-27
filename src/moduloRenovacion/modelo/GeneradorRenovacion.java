package moduloRenovacion.modelo;

import usuario.controllers.EstudianteJpaController;
import usuario.controllers.ProfesorJpaController;
import vista.AlertBox;
import vista.IAlertBox;

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
    private boolean consultarExistenciaEstudiante(String codEstudiante) {
        EstudianteJpaController estJPA = new EstudianteJpaController();
        return estJPA.findEstudiante(codEstudiante) != null;
    }

    /**
     * el metódo verifica si el codigo del profesor ingresado existe en la BD.
     *
     * @param idProfesor
     * @return boolean
     */
    private boolean consultarExistenciaProfesor(String idProfesor) {
        ProfesorJpaController profJPA = new ProfesorJpaController();
        return profJPA.findProfesor(idProfesor) != null;
    }

    /**
     * el metódo verifica si el estudiante, cuenta con alguna multa.
     *
     * @param codEstudiante
     * @return boolean
     */
    private boolean consultarMultaEstudiante(String codEstudiante) {
        return false;
    }

    /**
     * el metódo verifica si el profesor, cuenta con alguna multa.
     *
     * @param idProfesor
     * @return boolean
     */
    private boolean consultarMultaProfesor(String idProfesor) {
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
        IAlertBox alert = new AlertBox();
        FabricaRenovacion fabricaRenovacion = new FabricaRenovacion();
        IRenovacion renovacion = null;

        switch (tipoUsuario) {
            case "estudiante":
                if (consultarExistenciaEstudiante(codUsuario)) {
                    //espacio para validar multas del usuario

                    renovacion = fabricaRenovacion.getRenovacion(tipoUsuario);
                } else {
                    alert.showAlert("Anuncio", "Renovación", "No hay ningún estudiante "
                            + "asociado al código: " + codUsuario + ".");
                }
                break;
            case "profesor":
                if (consultarExistenciaProfesor(codUsuario)) {
                    //espacio para validar multas del usuario

                    renovacion = fabricaRenovacion.getRenovacion(tipoUsuario);
                } else {
                    alert.showAlert("Anuncio", "Renovación", "No hay ningún profesor "
                            + "asociado al código: " + codUsuario + ".");
                }
                break;
            default:
                System.out.println("Error al crear la renovación del usuario: " 
                        + tipoUsuario + ", que no existe.");
                break;
        }

        if (renovacion != null) {
            renovacion.ejecutarRenovacion(codBarras, codUsuario);
        }

    }

}

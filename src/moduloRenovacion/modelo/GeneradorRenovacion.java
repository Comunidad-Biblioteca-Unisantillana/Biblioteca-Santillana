package moduloRenovacion.modelo;

import usuarios.control.EstudianteJpaController;
import usuarios.control.ProfesorJpaController;
import general.vista.AlertBox;
import general.vista.IAlertBox;
import moduloMulta.modelo.IVerificaMulta;
import moduloMulta.modelo.VerificaMultaEstudiante;
import moduloMulta.modelo.VerificaMultaProfesor;

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
     * el método consulta las multas de un usuario, por medio del código.
     *
     * @param codUsuario
     * @param tipoUsuario
     * @return boolean
     */
    public boolean consultarMulta(String codUsuario, String tipoUsuario) {
        IVerificaMulta iVerificaMulta;

        if (tipoUsuario.equalsIgnoreCase("estudiante")) {
            iVerificaMulta = new VerificaMultaEstudiante();
        } else {
            iVerificaMulta = new VerificaMultaProfesor();
        }

        return iVerificaMulta.verificarMultaUsuario(codUsuario);
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
                    if (!consultarMulta(codUsuario, tipoUsuario)) {
                        renovacion = fabricaRenovacion.getRenovacion(tipoUsuario);
                    } else {
                        alert.showAlert("Anuncio", "Estudiante multado", "El estudiante: " + codUsuario
                                + ", tiene cargado en su cuenta una multa. Por lo tanto no se le puede prestar "
                                + "el libro : " + codBarras + ", hasta que cancele la multa.");
                    }
                } else {
                    alert.showAlert("Anuncio", "Renovación", "No hay ningún estudiante "
                            + "asociado al código: " + codUsuario + ".");
                }
                break;

            case "profesor":
                if (consultarExistenciaProfesor(codUsuario)) {
                    if (!consultarMulta(codUsuario, tipoUsuario)) {

                        renovacion = fabricaRenovacion.getRenovacion(tipoUsuario);
                    } else {
                        alert.showAlert("Anuncio", "Profesor multado", "El profesor: " + codUsuario
                                + ", tiene cargado en su cuenta una multa. Por lo tanto no se le puede prestar "
                                + "el libro : " + codBarras + ", hasta que cancele la multa.");
                    }
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

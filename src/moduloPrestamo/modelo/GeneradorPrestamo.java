package moduloPrestamo.modelo;

import usuarios.control.EstudianteJpaController;
import usuarios.control.ProfesorJpaController;
import general.vista.AlertBox;
import general.vista.IAlertBox;
import moduloMulta.modelo.IVerificaMulta;
import moduloMulta.modelo.VerificaMultaEstudiante;
import moduloMulta.modelo.VerificaMultaProfesor;

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
     * el método se encarga de crear un préstamoñ
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
            EstudianteJpaController estJPA = new EstudianteJpaController();

            if (estJPA.findEstudiante(codUsuario) != null) {
                if (!consultarMulta(codUsuario, tipoUsuario)) {
                    if (generarPrestamoEstudiante(codBarra, codUsuario, idBibliotecario, tipoPrestamo)) {
                        alert.showAlert("Anuncio", "Préstamo", "El préstamo del/de(la) " + tipoPrestamo
                                + ": " + codBarra + " al estudiante: " + codUsuario + ", se realizó con éxito.");
                    } else {
                        alert.showAlert("Anuncio", "Error préstamo", "No se pudo realizar el préstamo del/de(la) "
                                + tipoPrestamo + ": " + codBarra + " al estudiante: " + codUsuario + ".");
                    }
                } else {
                    alert.showAlert("Anuncio", "Estudiante multado", "El estudiante: " + codUsuario
                            + ", tiene cargado en su cuenta una multa. Por lo tanto no se le puede prestar el/la "
                            + tipoPrestamo + ": " + codBarra + ", hasta que cancele la multa.");
                }
            } else {
                alert.showAlert("Anuncio", "Error usuario", "No hay ningún estudiante asociado al código: " + codUsuario + ".");
            }
        } else if (tipoUsuario.equalsIgnoreCase("profesor")) {
            ProfesorJpaController profJPA = new ProfesorJpaController();

            if (profJPA.findProfesor(codUsuario) != null) {
                if (!consultarMulta(codUsuario, tipoUsuario)) {
                    if (generarPrestamoProfesor(codBarra, codUsuario, idBibliotecario, tipoPrestamo)) {
                        alert.showAlert("Anuncio", "Préstamo", "El préstamo del/de(la) " + tipoPrestamo
                                + ": " + codBarra + " al estudiante: " + codUsuario + ", se realizó con éxito.");
                    } else {
                        alert.showAlert("Anuncio", "Error préstamo", "No se pudo realizar el préstamo del/de(la) "
                                + tipoPrestamo + ": " + codBarra + " al profesor: " + codUsuario + ".");
                    }
                } else {
                    alert.showAlert("Anuncio", "Profesor multado", "El profesor: " + codUsuario
                            + ", tiene cargado en su cuenta una multa. Por lo tanto no se le puede prestar el/la "
                            + tipoPrestamo + ": " + codBarra + ", hasta que cancele la multa.");
                }
            } else {
                alert.showAlert("Anuncio", "Error usuario", "No hay ningún profesor asociado a la identificación: " + codUsuario + ".");
            }
        }
    }

    /**
     * el método genera un préstamo de un estudiante.
     *
     * @param codBarras
     * @param codEstudiante
     * @param idBibliotecario
     * @param tipoPrestamo
     * @return boolean
     */
    private boolean generarPrestamoEstudiante(String codBarras, String codEstudiante, String idBibliotecario, String tipoPrestamo) {
        FabricaPrestamo fabPrestamo = new FabricaPrestamo();
        IPrestamo prestamoEstudiante = fabPrestamo.getPrestamo(tipoPrestamo, "estudiante");//no se tienen encuenta el null

        return prestamoEstudiante.ejecutarPrestamo(codBarras, codEstudiante, idBibliotecario);
    }

    /**
     * el método genera un préstamo de un profesor.
     *
     * @param codBarras
     * @param idProfesor
     * @param idBibliotecario
     * @param nombreRecurso
     * @return boolean
     */
    private boolean generarPrestamoProfesor(String codBarras, String idProfesor, String idBibliotecario, String tipoPrestamo) {
        FabricaPrestamo fabPrestamo = new FabricaPrestamo();
        IPrestamo prestamoEstudiante = fabPrestamo.getPrestamo(tipoPrestamo, "profesor");//no se tienen encuenta el null

        return prestamoEstudiante.ejecutarPrestamo(codBarras, idProfesor, idBibliotecario);
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

}

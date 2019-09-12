package moduloReserva.modelo;

import usuarios.control.EstudianteJpaController;
import usuarios.control.ProfesorJpaController;
import general.vista.AlertBox;
import general.vista.IAlertBox;
import moduloMulta.modelo.IVerificaMulta;
import moduloMulta.modelo.VerificaMultaEstudiante;
import moduloMulta.modelo.VerificaMultaProfesor;

/**
 * Clase que se encarga de generar una reserva
 *
 * @author Julian
 */
public class GeneradorReserva {

    public GeneradorReserva() {
    }

    public boolean createReserva(String codBarras, String codUsuario, String tipoUsuario, String idBibliotecario) {
        IAlertBox alert = new AlertBox();
        if (tipoUsuario.equalsIgnoreCase("estudiante")) {
            EstudianteJpaController estudiante = new EstudianteJpaController();
            if (estudiante.findEstudiante(codUsuario) != null) {
                if (!consultarMulta(codUsuario, tipoUsuario)) {
                    if (generarReserva(codBarras, codUsuario, tipoUsuario, idBibliotecario)) {
                        alert.showAlert("Anuncio", "Reserva estudiante", "La reserva se realizo con exito");
                        return true;
                    }
                } else {
                    alert.showAlert("Anuncio", "Estudiante multado", "El estudiante: " + codUsuario
                            + ", tiene cargado en su cuenta una multa. Por lo tanto no se le puede prestar "
                            + "el libro : " + codBarras + ", hasta que cancele la multa.");
                }
            } else {
                alert.showAlert("Anuncio", "Reserva estudiante", "No existe un estudiante con ese código");
            }
        } else {
            ProfesorJpaController profesor = new ProfesorJpaController();
            if (profesor.findProfesor(codUsuario) != null) {
                if (!consultarMulta(codUsuario, tipoUsuario)) {
                    if (generarReserva(codBarras, codUsuario, tipoUsuario, idBibliotecario)) {
                        alert.showAlert("Anuncio", "Reserva profesor", "La reserva se realizo con exito");
                        return true;
                    }
                } else {
                    alert.showAlert("Anuncio", "Profesor multado", "El profesor: " + codUsuario
                            + ", tiene cargado en su cuenta una multa. Por lo tanto no se le puede prestar "
                            + "el libro : " + codBarras + ", hasta que cancele la multa.");
                }
            } else {
                alert.showAlert("Anuncio", "Reserva profesor", "No existe un profesor con esa identificación");
            }
        }
        return false;
    }

    private boolean generarReserva(String codBarras, String codUsuario, String tipoUsuario, String idBibliotecario) {
        FabricaReserva fabrica = new FabricaReserva();
        IReserva reserva = fabrica.getReserva(tipoUsuario);
        if (reserva.ejecutarReserva(codBarras, codUsuario, idBibliotecario)) {
            return true;
        }
        return false;
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

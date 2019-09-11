package usuarios.control;

import javafx.event.ActionEvent;
import usuarios.entitys.Bibliotecario;
import usuarios.entitys.Estudiante;
import usuarios.entitys.Profesor;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import moduloLogin.vista.LoginUnisantillanaStage;

/**
 * Clase que controla la vista DatosBasicosUsuario.fxml
 *
 * @author Julian Fecha de Creación: 26/07/2019 Fecha de ultima Modificación:
 * 09/09/2019
 */
public class DatosBasicosUsuarioController {

    @FXML
    private Label lblCodId;
    @FXML
    private Label lblNombre;

    @FXML
    private void btnCerarSesion(ActionEvent event) {
        ((Stage) lblNombre.getScene().getWindow()).close();
        LoginUnisantillanaStage.getInstance().show();
    }

    /**
     * Método que carga el nombre y código del usuario
     *
     * @param nombreEntidad
     * @param codigo
     */
    public void cargarComponentes(String nombreEntidad, String codigo) {
        if (nombreEntidad.equalsIgnoreCase("estudiante")) {
            EstudianteJpaController estJPA = new EstudianteJpaController();
            Estudiante est = estJPA.findEstudiante(codigo);
            cargarTextoLabels(est.getNombre() + " " + est.getApellido(), "Código: " + codigo);
        } else if (nombreEntidad.equalsIgnoreCase("bibliotecario")) {
            BibliotecarioJpaController bibJPA = new BibliotecarioJpaController();
            Bibliotecario bib = bibJPA.findBibliotecario(codigo);
            cargarTextoLabels(bib.getNombres() + " " + bib.getApellidos(), "Identificación: " + codigo);
        } else if (nombreEntidad.equalsIgnoreCase("profesor")) {
            ProfesorJpaController profJPA = new ProfesorJpaController();
            Profesor prof = profJPA.findProfesor(codigo);
            cargarTextoLabels(prof.getNombres() + " " + prof.getApellidos(), "Identificación: " + codigo);
        }
    }

    /**
     * Método que muestra el nombre y el código del usuario en pantalla
     *
     * @param nombreCompleto
     * @param codigo
     */
    private void cargarTextoLabels(String nombreCompleto, String codigo) {
        lblNombre.setText("Nombre: " + nombreCompleto);
        lblCodId.setText(codigo);
    }
}

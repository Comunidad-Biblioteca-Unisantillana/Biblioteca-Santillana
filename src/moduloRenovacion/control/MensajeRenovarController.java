package moduloRenovacion.control;

import com.jfoenix.controls.JFXTextArea;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import moduloPrestamo.control.PrestamoEstudianteProfesorController;
import moduloPrestamo.modelo.Prestamo;
import moduloRenovacion.modelo.GeneradorRenovacion;

/**
 * Clase que controla la vista MensajeRenovar.fxml.
 *
 * @author Miguel Fernández
 * @creado 25/08/2019
 * @author Miguel Fernández
 * @modificado 25/08/2019
 */
public class MensajeRenovarController implements Initializable {

    @FXML
    private JFXTextArea tfMensaje;
    private Stage stage;
    private Prestamo prestamo;
    private String codUsuario;
    private String tipoUsuario;
    private PrestamoEstudianteProfesorController prestEstProfController;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    /**
     * el metódo realiza la renovación del recurso.
     *
     * @param event
     */
    @FXML
    private void btnRevonar(ActionEvent event) {
        prestEstProfController.setEstadoRenovarRecurso(false);
        GeneradorRenovacion generadorRenovacion = new GeneradorRenovacion();
        generadorRenovacion.createRenovacion(prestamo.getCodBarrasRecurso(), codUsuario, tipoUsuario);
        prestEstProfController.cargarDatosUsuario(codUsuario, tipoUsuario);
        stage = (Stage) tfMensaje.getScene().getWindow();
        stage.close();
    }

    /**
     * el metódo cancela la renovación del recurso.
     *
     * @param event
     */
    @FXML
    private void btnCancelar(ActionEvent event) {
        prestEstProfController.setEstadoRenovarRecurso(false);
        stage = (Stage) tfMensaje.getScene().getWindow();
        stage.close();
    }

    /**
     * el metódo carga een el controlador los datos del prestamo seleccionado,
     * el tipo de usuario y el controlador PrestamoEstudianteProfesorController.
     *
     * @param prestamo
     * @param codUsuario
     * @param tipoUsuario
     * @param prestEstProfController
     */
    public void cargarDatos(Prestamo prestamo, String codUsuario, String tipoUsuario,
            PrestamoEstudianteProfesorController prestEstProfController) {
        this.prestamo = prestamo;
        this.codUsuario = codUsuario;
        this.tipoUsuario = tipoUsuario;
        this.prestEstProfController = prestEstProfController;

        tfMensaje.setText("Desea renovar el recurso:"
                + "\n\nCódigo de barras: " + prestamo.getCodBarrasRecurso()
                + "\nTitulo: " + prestamo.getTituloRecurso());
    }

}

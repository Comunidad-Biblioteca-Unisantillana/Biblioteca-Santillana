package moduloLogin.control;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import general.control.KeyEventJFXTextFieldController;
import general.vista.AlertBox;
import general.vista.IAlertBox;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import usuarios.control.EstudianteJpaController;
import usuarios.control.ProfesorJpaController;

/**
 * la clase controla la vista ConsultarUsuario.fxml.
 *
 * @author Miguel Fernández
 * @creado: 08/09/2019
 * @author Miguel Fernández
 * @modificado: 08/09/2019
 */
public class ConsultarUsuarioController implements Initializable {

    @FXML
    private GridPane gridPaneFicha;
    @FXML
    private JFXTextField txfCodUsuario;
    @FXML
    private JFXComboBox<String> cboTipoUsuario;
    private IAlertBox alert;

    /**
     * el método se ejecuta automáticamente al enlazar este controlador con su
     * respectiva vista.
     *
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        KeyEventJFXTextFieldController eventoTecla = new KeyEventJFXTextFieldController();
        eventoTecla.soloNumeros(txfCodUsuario);
        ObservableList<String> listaTipoRecurso = FXCollections.observableArrayList("Estudiante", "Profesor");
        cboTipoUsuario.setItems(listaTipoRecurso);
        cboTipoUsuario.setValue("Estudiante");
        alert = new AlertBox();
    }

    /**
     * el método se encarga de buscar la información del estudiante o profesor y
     * cargala en el panel para visualizarla.
     *
     * @param event
     */
    @FXML
    private void btnBuscarUsuario(ActionEvent event) {
        String tipoUsuario = cboTipoUsuario.getValue().toLowerCase();
        String codUsuario = txfCodUsuario.getText().trim();
        Object usuario;

        if (!codUsuario.isEmpty()) {
            if (tipoUsuario.equalsIgnoreCase("estudiante")) {
                EstudianteJpaController estudianteJpaController = new EstudianteJpaController();
                usuario = estudianteJpaController.findEstudiante(codUsuario);
            } else {
                ProfesorJpaController profesorJpaController = new ProfesorJpaController();
                usuario = profesorJpaController.findProfesor(codUsuario);
            }

            if (usuario != null) {
                limpiarGridPane();
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/moduloLogin/vista/FichaUsuario.fxml"));
                    gridPaneFicha.add(loader.load(), 0, 1);
                    FichaUsuarioController control = loader.getController();
                    control.usuario(usuario);
                } catch (IOException ex) {
                    System.out.println("Error al cargar el panel de la ficha del usuario.");
                }
            } else {
                alert.showAlert("Anuncio", "Error usuario", "No se encontró un" + tipoUsuario
                        + ", asociado al código: " + codUsuario + ".");
                limpiarGridPane();
            }
        } else {
            alert.showAlert("Anuncio", "Campo vacio", "Por favor ingrese el campo código/identificación.");
        }
    }

    private void limpiarGridPane() {
        if (!gridPaneFicha.getChildren().isEmpty()) {
            gridPaneFicha.getChildren().clear();
        }
    }

}

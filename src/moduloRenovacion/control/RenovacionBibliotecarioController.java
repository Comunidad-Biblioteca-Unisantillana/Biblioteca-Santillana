package moduloRenovacion.control;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import moduloRenovacion.modelo.GeneradorRenovacion;
import vista.AlertBox;
import vista.IAlertBox;

/**
 * @author Miguel Fernández
 * @creado 24/08/2019
 * @author Miguel Fernández
 * @modificado 25/08/2019
 */
public class RenovacionBibliotecarioController implements Initializable {

    @FXML
    private JFXTextField txtCodBarras;
    @FXML
    private JFXTextField txtcodUsuario;
    @FXML
    private JFXComboBox<String> cboTipoUsuario;

    /**
     * Initializes the controller class.
     *
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<String> listaTipoUsuario = FXCollections.observableArrayList("Estudiante", "Profesor");
        cboTipoUsuario.setItems(listaTipoUsuario);
        cboTipoUsuario.setValue("Estudiante");
    }

    /**
     * el metódo lleva a cabo la renovacón del recurso por parte del
     * bibliotecario.
     *
     * @param event
     */
    @FXML
    private void btnRenovarRecursoPress(ActionEvent event) {
        String codBarras = txtCodBarras.getText().trim();
        String codUsuario = txtcodUsuario.getText().trim();
        String tipoUsuario = cboTipoUsuario.getValue().toLowerCase();
        IAlertBox alert = new AlertBox();

        if (!codBarras.isEmpty() && !codUsuario.isEmpty()) {
            GeneradorRenovacion generadorRenovacion = new GeneradorRenovacion();
            generadorRenovacion.createRenovacion(codBarras, codUsuario, tipoUsuario);
            limpiarCampos();
        } else {
            alert.showAlert("Anuncio", "Campo vacio", "Por favor, no deje ningún campo vacio "
                    + "para poder realizar la renovación del libro.");
        }
    }

    /**
     * el metódo limpia el campo de txtCodBarras, txtcodUsuario y pone el
     * comobox por defecto en "Estudiante".
     */
    private void limpiarCampos() {
        txtCodBarras.setText("");
        txtcodUsuario.setText("");
        cboTipoUsuario.setValue("Estudiante");
    }

}

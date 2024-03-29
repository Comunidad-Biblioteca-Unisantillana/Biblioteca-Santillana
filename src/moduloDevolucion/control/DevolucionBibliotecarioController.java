package moduloDevolucion.control;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import general.control.KeyEventJFXTextFieldController;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import moduloDevolucion.modelo.GeneradorDevolucion;
import general.vista.AlertBox;
import general.vista.IAlertBox;

/**
 * Clase que controla la vista DevolucionBibliotecario.fxml
 *
 * @author Julian Fecha de Creación: 18/07/2019 Fecha de ultima Modificación:
 * 04/08/2019
 */
public class DevolucionBibliotecarioController implements Initializable {

    @FXML
    private JFXTextField codBarrasDevTxt;
    @FXML
    private JFXComboBox<String> cboTipoRecurso;
    @FXML
    private JFXTextArea textAEstadoRecurso;

    private String idBibliotecario;

    /**
     * Método que se ejecuta automáticamente al enlazar<br>
     * este controlador con su respectiva vista
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        KeyEventJFXTextFieldController eventgoTecla = new KeyEventJFXTextFieldController();
        eventgoTecla.soloNumeros(codBarrasDevTxt);

        ObservableList<String> listaTipoRecurso = FXCollections.observableArrayList("Libro", "Enciclopedia", "Diccionario",
                "Revista", "Periodico", "Mapa");
        cboTipoRecurso.setItems(listaTipoRecurso);
        cboTipoRecurso.setValue("Libro");
    }

    /**
     * Método que se encarga de devolver un recurso<br>
     * que se encuentra prestado
     *
     * @param event
     */
    @FXML
    private void btnDevolverPressed(ActionEvent event) {
        IAlertBox alert = new AlertBox();
        if (!codBarrasDevTxt.getText().isEmpty() && !textAEstadoRecurso.getText().isEmpty()) {
            if (textAEstadoRecurso.getText().length() <= 200) {
                try {
                    GeneradorDevolucion generador = new GeneradorDevolucion();
                    generador.createDevolucion(codBarrasDevTxt.getText(), idBibliotecario, cboTipoRecurso.getValue(), textAEstadoRecurso.getText());
                } catch (Exception ex) {
                    System.out.println("Error al generar  la devolución");
                }
            } else {
                alert.showAlert("Anuncio", "Cantidad de caracteres", "El campo del estado recurso no debe superar los 200 caracteres.");
            }
        } else {
            alert.showAlert("Anuncio", "Devolución", "Por favor ingrese todos los campos!");
        }
    }

    /**
     * Metodo que carga la identificación del bibliotecario
     *
     * @param idBibliotecario
     */
    public void setIdBibliotecario(String idBibliotecario) {
        this.idBibliotecario = idBibliotecario;
    }
}

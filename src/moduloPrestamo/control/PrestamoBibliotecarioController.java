package moduloPrestamo.control;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import general.control.KeyEventJFXTextFieldController;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import moduloPrestamo.modelo.GeneradorPrestamo;
import general.vista.AlertBox;
import general.vista.IAlertBox;

/**
 * Clase que controla la vista PrestamoBibliotecario.fxml
 *
 * @author Julian
 * @creado 18/07/2019
 * @author Julian
 * @modificado 04/08/2019
 */
public class PrestamoBibliotecarioController implements Initializable {

    @FXML
    private JFXTextField codBarrasPresTxt;
    @FXML
    private JFXTextField codUserPresTxt;
    @FXML
    private JFXComboBox<String> cboTipoPrestamo;
    @FXML
    private JFXComboBox<String> cboTipoUsuario;
    private String idBibliotecario;

    /**
     * el método se ejecuta automáticamente al enlazar este controlador con su
     * respectiva vista.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Restriccion solo numeros
        KeyEventJFXTextFieldController eventoTecla = new KeyEventJFXTextFieldController();
        eventoTecla.soloNumeros(codBarrasPresTxt);
        eventoTecla.soloNumeros(codUserPresTxt);

        //iniciar comboBox tipo recursos
        ObservableList<String> listaTipoRecurso = FXCollections.observableArrayList("Libro", "Enciclopedia", "Diccionario",
                "Revista", "Periodico", "Mapa");
        cboTipoPrestamo.setItems(listaTipoRecurso);
        cboTipoPrestamo.setValue("Libro");

        //iniciar comboBox tipo usuario
        ObservableList<String> listaTipoUsuario = FXCollections.observableArrayList("Estudiante", "Profesor");
        cboTipoUsuario.setItems(listaTipoUsuario);
        cboTipoUsuario.setValue("Estudiante");
    }

    /**
     * el método que se encarga de prestar un recurso, por medio de su código de
     * barras y el código del usuario.
     *
     * @param event
     */
    @FXML
    private void btnPrestarPressed(ActionEvent event) {
        IAlertBox alert = new AlertBox();

        if (!codBarrasPresTxt.getText().isEmpty() && !codUserPresTxt.getText().isEmpty()) {
            try {
                GeneradorPrestamo generador = new GeneradorPrestamo();
                generador.createPrestamo(codBarrasPresTxt.getText().toLowerCase(), codUserPresTxt.getText().toLowerCase(),
                        idBibliotecario, cboTipoPrestamo.getValue().toLowerCase(), cboTipoUsuario.getValue().toLowerCase());
            } catch (Exception ex) {
                System.out.println("Error al generar el préstamo del recurso");
            }
        } else {
            alert.showAlert("Anuncio", "Campos vacios", "Por favor, "
                    + "ingrese el código de barras y el código del usuario");
        }
    }

    /**
     * el metódo que carga la identificación del bibliotecario.
     *
     * @param idBibliotecario
     */
    public void setIdBibliotecario(String idBibliotecario) {
        this.idBibliotecario = idBibliotecario;
    }

    /**
     * el metódo limpia el campo de codBarrasPresTxt, codUserPresTxt y pone el
     * comobox cboTipoUsuario por defecto en "Estudiante" y cboTipoPrestamo por
     * defecto en "Todos".
     */
    private void limpiarCampos() {
        codBarrasPresTxt.setText("");
        codUserPresTxt.setText("");
        cboTipoPrestamo.setValue("Libro");
        cboTipoUsuario.setValue("Estudiante");
    }

}

package control;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import moduloPrestamo.GeneradorPrestamo;
import vista.AlertBox;
import vista.IAlertBox;

/**
 * Clase que controla la vista PrestamoBibliotecario.fxml
 *
 * @author Julian Fecha de Creación: 18/07/2019 Fecha de ultima Modificación:
 * 04/08/2019
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
     * Método que se ejecuta automáticamente al enlazar<br>
     * este controlador con su respectiva vista
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
        ObservableList<String> listaTipoUsuario = FXCollections.observableArrayList("Estudiante","Profesor");
        cboTipoUsuario.setItems(listaTipoUsuario);
        cboTipoUsuario.setValue("Estudiante");
    }

    /**
     * Método que se encarga de prestar un recurso<br>
     * por medio de su código de barras y el código<br>
     * del usuario
     *
     * @param event
     */
    @FXML
    private void btnPrestarPressed(ActionEvent event) {
        IAlertBox alert = new AlertBox();
        if (!codBarrasPresTxt.getText().isEmpty() && !codUserPresTxt.getText().isEmpty()) {
            try {
                GeneradorPrestamo generador = new GeneradorPrestamo();
                generador.createPrestamo(codBarrasPresTxt.getText(), codUserPresTxt.getText(), 
                        idBibliotecario, cboTipoPrestamo.getValue(), cboTipoUsuario.getValue()); 
            } catch (Exception ex) {
                System.out.println("Error al generar el préstamo");
            }
        } else {
            alert.showAlert("Anuncio", "Préstamo", "Por favor ingrese todos los campos!");
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

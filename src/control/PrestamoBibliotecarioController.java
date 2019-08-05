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
import moduloPrestamo.GeneradorPrestamoRecurso;
import vista.AlertBox;
import vista.IAlertBox;

/**
 * Clase que controla la vista PrestamoBibliotecario.fxml
 * @author Julian
 * Fecha de Creación: 18/07/2019
 * Fecha de ultima Modificación: 04/08/2019
 */
public class PrestamoBibliotecarioController implements Initializable{
    
    @FXML
    private JFXTextField codBarrasPresTxt;
    @FXML
    private JFXTextField codUserPresTxt;
    @FXML
    private JFXTextField fechaPrestamoPresTxt;
    @FXML
    private JFXTextField fechaDevolucionPresTxt;
    @FXML
    private JFXComboBox<String> cboTipoPrestamo;
    
    private String idBibliotecario;
    
    /**
     * Método que se ejecuta automáticamente al enlazar<br>
     * este controlador con su respectiva vista
     * @param url
     * @param rb 
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        KeyEventJFXTextFieldController eventoTecla = new KeyEventJFXTextFieldController();
        eventoTecla.soloNumeros(codBarrasPresTxt);
        eventoTecla.soloNumeros(codUserPresTxt);
        ObservableList<String> listaTipoRecurso = FXCollections.observableArrayList( "Libro", "Enciclopedia", "Diccionario",
                "Revista", "Periodico", "Mapa");
        cboTipoPrestamo.setItems(listaTipoRecurso);
        cboTipoPrestamo.setValue("Libro");
    }
    
    /**
     * Método que se encarga de prestar un recurso<br>
     * por medio de su código de barras y el código<br>
     * del usuario
     * @param event 
     */
    @FXML
    private void btnPrestarPressed(ActionEvent event) {
        IAlertBox alert = new AlertBox();
        if (!codBarrasPresTxt.getText().isEmpty() && !codUserPresTxt.getText().isEmpty()) {
            try {
                GeneradorPrestamoRecurso generador = new GeneradorPrestamoRecurso();

                if (generador.createPrestamo(codBarrasPresTxt.getText(), codUserPresTxt.getText(), idBibliotecario,
                        cboTipoPrestamo.getValue(), fechaPrestamoPresTxt, fechaDevolucionPresTxt)) {
                    alert.showAlert("Anuncio", "Préstamo", "El préstamo ha sido realizado con éxito!");
                }
            } catch (Exception ex) {
                System.out.println("Error al generar el préstamo");
            }
        } else {
            alert.showAlert("Anuncio", "Préstamo", "Por favor ingrese todos los campos!");
        }
    }
    
    /**
     * Metodo que carga la identificación del bibliotecario
     * @param idBibliotecario 
     */
    public void setIdBibliotecario(String idBibliotecario){
        this.idBibliotecario = idBibliotecario;
    }
}

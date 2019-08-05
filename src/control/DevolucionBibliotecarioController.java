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
import moduloDevolucion.GeneradorDevolucion;
import vista.AlertBox;
import vista.IAlertBox;

/**
 * Clase que controla la vista DevolucionBibliotecario.fxml
 * @author Julian
 * Fecha de Creación: 18/07/2019
 * Fecha de ultima Modificación: 04/08/2019
 */
public class DevolucionBibliotecarioController implements Initializable{

    @FXML
    private JFXTextField codBarrasDevTxt;
    @FXML
    private JFXComboBox<String> cboTipoRecurso;
    @FXML
    private JFXComboBox<String> cboEstadoRecurso;
    
    private String idBibliotecario;
    
    /**
     * Método que se ejecuta automáticamente al enlazar<br>
     * este controlador con su respectiva vista
     * @param url
     * @param rb 
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        KeyEventJFXTextFieldController eventgoTecla = new KeyEventJFXTextFieldController();
        eventgoTecla.soloNumeros(codBarrasDevTxt);
        
        ObservableList<String> listaTipoRecurso = FXCollections.observableArrayList( "Libro", "Enciclopedia", "Diccionario",
                "Revista", "Periodico", "Mapa");
        ObservableList<String> listaEstadosFisicos = FXCollections.observableArrayList( "Bueno", "Regular", "Malo");
        cboTipoRecurso.setItems(listaTipoRecurso);
        cboEstadoRecurso.setItems(listaEstadosFisicos);
        cboTipoRecurso.setValue("Libro");
        cboEstadoRecurso.setValue("Bueno");
    }
    
    /**
     * Método que se encarga de devolver un recurso<br>
     * que se encuentra prestado
     * @param event 
     */
    @FXML
    private void btnDevolverPressed(ActionEvent event) {
         IAlertBox alert = new AlertBox();
        if(!codBarrasDevTxt.getText().isEmpty() && ! idBibliotecario.isEmpty()){
            try {
                GeneradorDevolucion generador = new GeneradorDevolucion();

                if(generador.createDevolucion(codBarrasDevTxt.getText(), idBibliotecario, cboTipoRecurso.getValue(),
                        cboEstadoRecurso.getValue())){
                    alert.showAlert("Anuncio", "Devolución", "La devolución ha sido realizado con éxito!");
                }        
            } catch (Exception ex) {
                System.out.println("Error al generar  la devolución");
            }
        }
        else{
            alert.showAlert("Anuncio", "Devolución", "Por favor ingrese todos los campos!");
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

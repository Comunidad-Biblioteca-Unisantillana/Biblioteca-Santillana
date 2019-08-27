package moduloReserva.control;

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
import moduloReserva.modelo.GeneradorReserva;
import general.vista.AlertBox;
import general.vista.IAlertBox;

/**
 * Clase que controla la vista ReservaBibliotecario.fxml
 * @author Julian
 * Fecha de Creación: 18/07/2019
 * Fecha de ultima Modificación: 04/08/2019
 */
public class ReservaBibliotecarioController implements Initializable{

    @FXML
    private JFXTextField codBarrasResTxt;
    @FXML
    private JFXTextField txtCodUserReserva;
    @FXML
    private JFXComboBox<String> cboTipoUsuario;
    
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
        eventoTecla.soloNumeros(codBarrasResTxt);
        eventoTecla.soloNumeros(txtCodUserReserva);
        ObservableList<String> listaTipoRecurso = FXCollections.observableArrayList( "Estudiante", "Profesor");
        cboTipoUsuario.setItems(listaTipoRecurso);
        cboTipoUsuario.setValue("Estudiante");
    }
    
    /**
     * Método que se encarga de reservar un recurso<br>
     * por medio de su código de barras y el código<br>
     * del usuario
     * @param event 
     */
    @FXML
    private void btnReservarPressed(ActionEvent event) {
        IAlertBox alert = new AlertBox();
        if(!codBarrasResTxt.getText().isEmpty() && !txtCodUserReserva.getText().isEmpty()){
            try{
                GeneradorReserva generador = new GeneradorReserva();
                generador.createReserva(codBarrasResTxt.getText(), txtCodUserReserva.getText(), cboTipoUsuario.getValue(), idBibliotecario);
            }
            catch(Exception e){
                System.out.println("Error al realizar la reseva");
            }
        }
        else{
            alert.showAlert("Anuncio", "Reserva", "Por favor ingrese todos los campos!");
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

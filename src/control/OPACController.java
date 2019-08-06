package control;

import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import modelo.ConsultaOPAC;
import vista.AlertBox;
import vista.CargarFichaTecnica;
import vista.FichaTecnicaPanel;
import vista.IAlertBox;
import vista.StageTableOPAC;

/** 
 * Clase que controla la vista OPAC.fxml
 * @author Julian
 * Fecha de Creación: 18/07/2019
 * Fecha de ultima Modificación: 06/08/2019
 */
public class OPACController implements Initializable{
    
    private Stage stage;
    @FXML
    private GridPane panelOpac;
    @FXML
    private JFXTextField codBarrasOpacTxt;
    @FXML
    private JFXTextField tituloOpacTxt;
    
    private CargarFichaTecnica cft;
    
    /**
     * Método que se ejecuta automáticamente al enlazar<br>
     * este controlador con su respectiva vista
     * @param url
     * @param rb 
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        KeyEventJFXTextFieldController eventoTecla = new KeyEventJFXTextFieldController();
        eventoTecla.soloNumeros(codBarrasOpacTxt);
    }
    
    /**
     * Método que busca por medio de un código la<br>
     * información del recurso
     * @param event 
     */
    @FXML
    private void handledBtnBuscarCodBarras(ActionEvent event) {
        IAlertBox alert = new AlertBox();
        if(!codBarrasOpacTxt.getText().isEmpty()){
            ConsultaOPAC consulta = new ConsultaOPAC();
            String nombreEntidad = consulta.consultarTabla(codBarrasOpacTxt.getText().trim());
            
            if(!nombreEntidad.isEmpty()){
                FichaTecnicaPanel crs = new FichaTecnicaPanel("libro");
                panelOpac.add(crs.cargarFichaTecnica(), 0, 0);
            }
            else
                alert.showAlert("Anuncio", "Busqueda", "No se encontro ningún recurso con ese código");
        }
        else{
            alert.showAlert("Anuncio", "Busqueda", "Ingrese el código de barras del recurso");
        }
    }
    
    /**
     * Método que por medio de un titulo de recurso crea<br>
     * un stage donde se muestra la información básica<br>
     * de dicho recurso 
     * @param event 
     */
    @FXML
    private void handledBtnBuscarTitulo(ActionEvent event){
        if(!tituloOpacTxt.getText().isEmpty()){
            StageTableOPAC stageTable = new StageTableOPAC(tituloOpacTxt.getText().trim(), stage);
        }
        else{
            IAlertBox alert = new AlertBox();
            alert.showAlert("Anuncio", "Busqueda", "Ingrese el titulo del recurso");
        }
    }
    
    /**
     * Metodo que asignaa un stage
     * @param stage 
     */
    public void setStage(Stage stage) {
        this.stage = stage;
    }
}

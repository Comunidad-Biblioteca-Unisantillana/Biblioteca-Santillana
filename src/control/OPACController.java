/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import modelo.ConsultaOPAC;
import vista.AlertBox;
import vista.CargarFichaTecnica;
import vista.IAlertBox;
import vista.StageTableOPAC;

/**
 *
 * @author Julian
 */
public class OPACController {
    
    private Stage stage;
    @FXML
    private GridPane panelOpac;
    @FXML
    private JFXTextField codBarrasOpacTxt;
    @FXML
    private JFXTextField tituloOpacTxt;
    
    private CargarFichaTecnica cft;
    
    @FXML
    private void handledBtnBuscarCodBarras(ActionEvent event) {
        IAlertBox alert = new AlertBox();
        if(!codBarrasOpacTxt.getText().isEmpty()){
            ConsultaOPAC consulta = new ConsultaOPAC();
            String nombreEntidad = consulta.consultarTabla(codBarrasOpacTxt.getText().trim());
            
            if(!nombreEntidad.isEmpty()){
                cft = new CargarFichaTecnica(nombreEntidad);
                cft.crearFichaTecnica(panelOpac, codBarrasOpacTxt.getText());
            }
            else
                alert.showAlert("Anuncio", "Busqueda", "No se encontro ningún recurso con ese código");
        }
        else{
            alert.showAlert("Anuncio", "Busqueda", "Ingrese el código de barras del recurso");
        }
    }
    
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
    
    @FXML
    private void btnOpacLimpiarPressed(ActionEvent event) {
        if(cft != null){
            cft.limpiarCamposTextos();
        }
    }
    
    public void setStage(Stage stage) {
        this.stage = stage;
    }
}

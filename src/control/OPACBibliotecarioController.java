/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import modelo.ConsultaOPAC;
import vista.AlertBox;
import vista.CargarFichaTecnica;
import vista.CuentaBibliotecarioStage;
import vista.IAlertBox;
import vista.StageTableOPAC;

/**
 *
 * @author Julian
 */
public class OPACBibliotecarioController {
    
    private CuentaBibliotecarioStage stage;
    @FXML
    private GridPane panelOpac;
    @FXML
    private TextField codBarrasOpacTxt;
    @FXML
    private TextField tituloOpacTxt;
    
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
        cft.limpiarCamposTextos();
    }
    
    public void setStage(CuentaBibliotecarioStage stage) {
        this.stage = stage;
    }
}

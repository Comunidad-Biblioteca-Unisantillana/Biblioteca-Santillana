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
import vista.CargarFichaTecnica;
import modelo.ConsultaOPAC;
import vista.AlertBox;
import vista.CuentaEstudianteStage;
import vista.IAlertBox;
import vista.StageTableOPAC;

/**
 * clase que controla el panel OPAC del estudiante
 * @author Julian
 */
public class OPACEstudianteController{
    
    @FXML
    private GridPane panelOpac;
    @FXML
    private TextField codBarrasOpacTxt;
    @FXML
    private TextField tituloOpacTxt;
    
    private CuentaEstudianteStage stageEst;
    
    @FXML
    private void handledBtnBuscarTitulo(ActionEvent event) {
        if(!tituloOpacTxt.getText().isEmpty()){
            StageTableOPAC stage = new StageTableOPAC(tituloOpacTxt.getText().trim(), stageEst);
        }
        else{
            IAlertBox alert = new AlertBox();
            alert.showAlert("Anuncio", "Busqueda", "Ingrese el titulo del recurso");
        }
    }
    
    @FXML
    private void handledBtnBuscarCodBarras(ActionEvent event) {
        IAlertBox alert = new AlertBox();
        if(!codBarrasOpacTxt.getText().isEmpty()){
            ConsultaOPAC consulta = new ConsultaOPAC();
            String nombreEntidad = consulta.consultarTabla(codBarrasOpacTxt.getText().trim());
            
            if(!nombreEntidad.isEmpty()){
                
                CargarFichaTecnica cft = new CargarFichaTecnica(nombreEntidad);
                cft.crearFichaTecnica(panelOpac, codBarrasOpacTxt.getText());
            }
            else
                alert.showAlert("Anuncio", "Busqueda", "No se encontro ningún recurso con ese código");
        }
        else{
            alert.showAlert("Anuncio", "Busqueda", "Ingrese el código de barras del recurso");
        }
    }
    
    /**
     * Metodo que asigna un stage de Estudiante
     * @param stageEst 
     */
    public void setStageEst(CuentaEstudianteStage stageEst) {
        this.stageEst = stageEst;
    }
}

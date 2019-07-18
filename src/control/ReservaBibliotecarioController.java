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
import modelo.GeneradorReserva;
import vista.AlertBox;
import vista.CargarFichaTecnica;
import vista.IAlertBox;

/**
 *
 * @author Julian
 */
public class ReservaBibliotecarioController {
    
    @FXML
    private GridPane panelReservas;
    @FXML
    private TextField codBarrasResTxt;
    @FXML
    private TextField txtCodEstReserva;
    @FXML
    private TextField txtFechaReserva;
    @FXML
    private TextField txtFechaLimite;
    @FXML
    private TextField bibliotecarioResTxt;
    
    private CargarFichaTecnica cft;
    
    @FXML
    private void btnReservarPressed(ActionEvent event) {
        IAlertBox alert = new AlertBox();
        if(!codBarrasResTxt.getText().isEmpty() && !txtCodEstReserva.getText().isEmpty()){
            try{
                GeneradorReserva generador = new GeneradorReserva();

                if(generador.createReserva(codBarrasResTxt.getText(), txtCodEstReserva.getText(), bibliotecarioResTxt.getText(), 
                        txtFechaReserva, txtFechaLimite)){
                    
                    cft = new CargarFichaTecnica("libro");
                    cft.crearFichaTecnica(panelReservas, codBarrasResTxt.getText());
                    alert.showAlert("Anuncio", "Reserva", "La reserva ha sido realizado con éxito!");
                }
            }
            catch(Exception e){
                System.out.println("Error al realizar la reseva");
            }
        }
        else{
            alert.showAlert("Anuncio", "Reserva", "Por favor ingrese todos los campos!");
        }
    }
    
    @FXML
    private void btnReservasLimpiarPressed(ActionEvent event) {
        cft.limpiarCamposTextos();
    }
}

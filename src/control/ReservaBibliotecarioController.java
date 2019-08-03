/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import moduloReserva.GeneradorReserva;
import vista.AlertBox;
import vista.IAlertBox;

/**
 *
 * @author Julian
 */
public class ReservaBibliotecarioController {

    @FXML
    private JFXTextField codBarrasResTxt;
    @FXML
    private JFXTextField txtCodEstReserva;
    @FXML
    private JFXTextField txtFechaReserva;
    @FXML
    private JFXTextField txtFechaLimite;
    
    private String idBibliotecario;
    
    @FXML
    private void btnReservarPressed(ActionEvent event) {
        IAlertBox alert = new AlertBox();
        if(!codBarrasResTxt.getText().isEmpty() && !txtCodEstReserva.getText().isEmpty()){
            try{
                GeneradorReserva generador = new GeneradorReserva();

                if(generador.createReserva(codBarrasResTxt.getText(), txtCodEstReserva.getText(), idBibliotecario, 
                        txtFechaReserva, txtFechaLimite)){
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
    
    /**
     * Metodo que carga la identificación del bibliotecario
     * @param idBibliotecario 
     */
    public void setIdBibliotecario(String idBibliotecario){
        this.idBibliotecario = idBibliotecario;
    }
}

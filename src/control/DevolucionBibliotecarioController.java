/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import modelo.GeneradorDevolucion;
import vista.AlertBox;
import vista.IAlertBox;

/**
 *
 * @author Julian
 */
public class DevolucionBibliotecarioController implements Initializable{

    @FXML
    private JFXTextField codBarrasDevTxt;
    @FXML
    private JFXComboBox<String> cboTipoRecurso;
    @FXML
    private JFXComboBox<String> cboEstadoRecurso;
    
    private String idBibliotecario;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> listaTipoRecurso = FXCollections.observableArrayList( "Libro", "Enciclopedia", "Diccionario",
                "Revista", "Periodico", "Mapa");
        ObservableList<String> listaEstadosFisicos = FXCollections.observableArrayList( "Bueno", "Regular", "Malo");
        cboTipoRecurso.setItems(listaTipoRecurso);
        cboEstadoRecurso.setItems(listaEstadosFisicos);
        cboTipoRecurso.setValue("Libro");
        cboEstadoRecurso.setValue("Bueno");
    }
    
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

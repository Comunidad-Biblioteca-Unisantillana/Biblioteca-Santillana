/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import modelo.GeneradorDevolucion;
import vista.AlertBox;
import vista.CargarFichaTecnica;
import vista.IAlertBox;

/**
 *
 * @author Julian
 */
public class DevolucionBibliotecarioController implements Initializable{
    
    @FXML
    private GridPane panelDevoluciones;
    @FXML
    private TextField codBarrasDevTxt;
    @FXML
    private TextField bibliotecarioDevTxt;
    @FXML
    private ComboBox<String> cboTipoRecurso;
    @FXML
    private ComboBox<String> cboEstadoRecurso;
    
    private CargarFichaTecnica cft;
    
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
        if(!codBarrasDevTxt.getText().isEmpty() && ! bibliotecarioDevTxt.getText().isEmpty()){
            try {
                GeneradorDevolucion generador = new GeneradorDevolucion();

                if(generador.createDevolucion(codBarrasDevTxt.getText(), bibliotecarioDevTxt.getText(), cboTipoRecurso.getValue(),
                        cboEstadoRecurso.getValue())){
                    
                    cft = new CargarFichaTecnica(cboTipoRecurso.getValue());
                    cft.crearFichaTecnica(panelDevoluciones, codBarrasDevTxt.getText());
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
    
    @FXML
    private void btnDevolucionesLimpiarPressed(ActionEvent event) {
        if(cft != null){
            cft.limpiarCamposTextos();
        }
    }
    
    /**
     * Metodo que carga la identificación del bibliotecario
     * @param idBibliotecario 
     */
    public void cargarIdBibliotecario(String idBibliotecario){
        bibliotecarioDevTxt.setText(idBibliotecario);
    }
}

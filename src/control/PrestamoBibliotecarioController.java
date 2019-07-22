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
import javafx.scene.control.ComboBox;
import javafx.scene.layout.GridPane;
import vista.CargarFichaTecnica;
import modelo.GeneradorPrestamoRecurso;
import vista.AlertBox;
import vista.IAlertBox;

/**
 *
 * @author Storkolm
 */
public class PrestamoBibliotecarioController implements Initializable{
    
    @FXML
    private JFXTextField codBarrasPresTxt;
    @FXML
    private JFXTextField codEstudiantePresTxt;
    @FXML
    private JFXTextField fechaPrestamoPresTxt;
    @FXML    
    private JFXTextField bibliotecarioPresTxt;
    @FXML
    private JFXTextField fechaDevolucionPresTxt;
    @FXML
    private JFXComboBox<String> cboTipoPrestamo;
    @FXML
    private GridPane panelPrestamos;
    
    private CargarFichaTecnica cft;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> listaTipoRecurso = FXCollections.observableArrayList( "Libro", "Enciclopedia", "Diccionario",
                "Revista", "Periodico", "Mapa");
        cboTipoPrestamo.setItems(listaTipoRecurso);
        cboTipoPrestamo.setValue("Libro");
    }
    
    @FXML
    private void btnPrestarPressed(ActionEvent event) {
        IAlertBox alert = new AlertBox();
        if (!codBarrasPresTxt.getText().isEmpty() && !codEstudiantePresTxt.getText().isEmpty()) {
            try {
                GeneradorPrestamoRecurso generador = new GeneradorPrestamoRecurso();

                if (generador.createPrestamo(codBarrasPresTxt.getText(), codEstudiantePresTxt.getText(), bibliotecarioPresTxt.getText(),
                        cboTipoPrestamo.getValue(), fechaPrestamoPresTxt, fechaDevolucionPresTxt)) {

                    cft = new CargarFichaTecnica(cboTipoPrestamo.getValue());
                    cft.crearFichaTecnica(panelPrestamos, codBarrasPresTxt.getText());

                    alert.showAlert("Anuncio", "Préstamo", "El préstamo ha sido realizado con éxito!");
                }
            } catch (Exception ex) {
                System.out.println("Error al generar el préstamo");
            }
        } else {
            alert.showAlert("Anuncio", "Préstamo", "Por favor ingrese todos los campos!");
        }
    }
    
    @FXML
    private void btnPrestamosLimpiarPressed(ActionEvent event) {
        if(cft != null){
            cft.limpiarCamposTextos();
        }
    }
    
    /**
     * Metodo que carga la identificación del bibliotecario
     * @param idBibliotecario 
     */
    public void cargarIdBibliotecario(String idBibliotecario){
        bibliotecarioPresTxt.setText(idBibliotecario);
    }
}

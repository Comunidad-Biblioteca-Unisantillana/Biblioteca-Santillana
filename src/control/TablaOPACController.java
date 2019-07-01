/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import entitys.Recurso;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import modelo.ConsultaOPAC;
import vista.AlertBox;
import vista.IAlertBox;

/**
 *
 * @author Camilo
 */
public class TablaOPACController {

    @FXML
    private GridPane gridPaneMultas;
    private TableView<?> tableMulta;
    @FXML
    private TableColumn<Recurso, String> colCodBarras;
    @FXML
    private TableColumn<Recurso, String> colIsbn;
    @FXML
    private TableColumn<Recurso, String> colTitulo;
    @FXML
    private TableColumn<Recurso, String> colIdioma;
    @FXML
    private TableColumn<Recurso, String> colArea;
    @FXML
    private TableColumn<Recurso, String> colDisponibilidad;
    @FXML
    private TableView<Recurso> tableOPAC;
    
    public void initialize(){
    }
    
    public void cargarDatosTabla(String cadena){
        try {
            ConsultaOPAC consulta = new ConsultaOPAC();
            
            colCodBarras.setCellValueFactory(new PropertyValueFactory<>("codBarras"));
            colIsbn.setCellValueFactory(new PropertyValueFactory<>("isbn_issn"));
            colTitulo.setCellValueFactory(new PropertyValueFactory<>("titulo"));
            colIdioma.setCellValueFactory(new PropertyValueFactory<>("idioma"));
            colArea.setCellValueFactory(new PropertyValueFactory<>("disponibilidad"));
            colDisponibilidad.setCellValueFactory(new PropertyValueFactory<>("area"));
            
            tableOPAC.setItems(consulta.consultarRecursos(cadena));
        } catch (Exception ex) {
            IAlertBox alert = new AlertBox();
            alert.showAlert("Aviso", "Error al consultar", "Error al realizar la busqueda");
        }
    }
}

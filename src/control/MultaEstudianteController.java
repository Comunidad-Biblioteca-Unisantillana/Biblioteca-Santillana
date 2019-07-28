/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import entitys.Multa;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import modelo.ConsultaMulta;

/**
 * clase que controla el panel multa del estudiante
 *
 * @author Julian
 */
public class MultaEstudianteController {

    @FXML
    private TableView<Multa> tableMulta;
    @FXML
    private TableColumn<Multa, Integer> colCodMulta;
    @FXML
    private TableColumn<Multa, Integer> colCodPrestamo;
    @FXML
    private TableColumn<Multa, Integer> colDiasAtrasados;
    @FXML
    private TableColumn<Multa, Integer> colValorTot;
    @FXML
    private TableColumn<Multa, String> colCancelado;
    @FXML
    private TableColumn<Multa, String> colTipo;

    /**
     * Metodo que carga los datos de las multas de un estudiante
     *
     * @param codEstudiante
     */
    public void cargarDatosTableMultas(String codEstudiante)  {
        try {
            ConsultaMulta consulta = new ConsultaMulta();

            colCodMulta.setCellValueFactory(new PropertyValueFactory<>("codMulta"));
            colCodPrestamo.setCellValueFactory(new PropertyValueFactory<>("codPrestamo"));
            colDiasAtrasados.setCellValueFactory(new PropertyValueFactory<>("diasAtrasados"));
            colValorTot.setCellValueFactory(new PropertyValueFactory<>("valorTotal"));
            colCancelado.setCellValueFactory(new PropertyValueFactory<>("cancelado"));
            colTipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));
            tableMulta.setItems(consulta.getMultas(codEstudiante));
        } catch (Exception ex) {
            Logger.getLogger(CuentaEstudianteProfesorController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import com.jfoenix.controls.JFXTextField;
import entitys.Multa;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import modelo.ConsultaMulta;
import vista.AlertBox;
import vista.IAlertBox;

/**
 *
 * @author Storkolm
 */
public class MultaBibliotecarioController implements Initializable {

    @FXML
    private JFXTextField codEstudianteHisMulTxt;
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

    private int codMulta = 0;
    private String tipo = "";
    private String codEstudiante = "";
    private boolean consultoMultas = false;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tableMulta.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends Multa> arg0, Multa oldValue, Multa newValue) -> {
            tipo = newValue.getTipo();
            codMulta = newValue.getCodMulta();
        });
    }

    @FXML
    private void btnconsultarHisMulPressed(ActionEvent event) {

        if (!codEstudianteHisMulTxt.getText().isEmpty()) {
            try {
                cargarDatosTableMultas(codEstudianteHisMulTxt.getText());
                consultoMultas = true;
            } catch (Exception ex) {
                System.err.println("Error al cargar los datos del historial de multas");
            }
        } else {
            IAlertBox alert = new AlertBox();
            alert.showAlert("Anuncio", "Historial de multas de un estudiante", "Ingrese el código del estudiante");
        }
    }

    @FXML
    private void btnCancelarMultaPressed(ActionEvent event) throws Exception {
        IAlertBox alert = new AlertBox();
        if (consultoMultas) {
            ConsultaMulta consultaMulta = new ConsultaMulta();
            if (consultaMulta.eliminarMulta(codMulta, tipo)) {
                alert.showAlert("Anuncio", "Multa", "La multa ha sido eliminada");
                cargarDatosTableMultas(codEstudiante);

            } else {
                alert.showAlert("Anuncio", "Login Estudiante", "La multa no ha sido eliminada");
            }
        } else {
            alert.showAlert("Anuncio", "Login Estudiante", "No hay multas seleccionadas");
        }
    }

    /**
     * Método que se encarga de cargar las multas que se han realizado a un
     * estudiante por medio de código.
     *
     * @param codEstudiante
     * @throws Exception
     */
    public void cargarDatosTableMultas(String codEstudiante) throws Exception {
        ConsultaMulta consulta = new ConsultaMulta();

        colCodMulta.setCellValueFactory(new PropertyValueFactory<>("codMulta"));
        colCodPrestamo.setCellValueFactory(new PropertyValueFactory<>("codPrestamo"));
        colDiasAtrasados.setCellValueFactory(new PropertyValueFactory<>("diasAtrasados"));
        colValorTot.setCellValueFactory(new PropertyValueFactory<>("valorTotal"));
        colCancelado.setCellValueFactory(new PropertyValueFactory<>("cancelado"));
        colTipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        tableMulta.setItems(consulta.getMultas(codEstudiante));

        this.codEstudiante = codEstudiante;
    }

}

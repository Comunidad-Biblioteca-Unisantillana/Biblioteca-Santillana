/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moduloMulta.control;

import com.jfoenix.controls.JFXTextArea;
import general.vista.AlertBox;
import general.vista.IAlertBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import moduloMulta.entitys.Multa;
import moduloMulta.modelo.ConsultaMultaAbs;
import moduloMulta.modelo.ConsultaMultaEst;
import moduloMulta.modelo.ConsultaMultaProf;
import moduloMulta.vista.CancelarMultaStage;

/**
 *
 * @author Storkolm
 */
public class CancelarMultaController {

    private String tipoUsuario;

    private CancelarMultaStage stage;
    private TableView<Multa> tableMulta;

    @FXML
    private JFXTextArea areaJustificación;

    @FXML
    private void btnConfirmarPresed(ActionEvent e) {
        IAlertBox alert = new AlertBox();
        if (!areaJustificación.getText().isEmpty()) {
            ConsultaMultaAbs anular;

            if (tipoUsuario.equalsIgnoreCase("estudiante")) {
                anular = new ConsultaMultaEst();
            } else {
                anular = new ConsultaMultaProf();
            }
            Multa multa = tableMulta.getItems().get(tableMulta.getSelectionModel().getSelectedIndex());
            System.out.println(multa.getCodBarrasRecurso());
            if (areaJustificación.getText().length() < 500) {
                if (anular.eliminarMulta(multa.getCodMulta(), multa.getTipoRecurso(), areaJustificación.getText())) {
                    alert.showAlert("Anuncio", "Multa " + tipoUsuario, "La multa ha sido anulada");
                    tableMulta.getItems().remove(tableMulta.getSelectionModel().getSelectedIndex());
                    stage.close();
                }
            } else {
                alert.showAlert("Anuncio", "Cantidad de caracteres", "El campo motivo de cancelación no debe superar los 500 caracteres.");
            }
        } else {
            alert.showAlert("Anuncio", "Multa " + tipoUsuario, "Escriba el motivo de la cancelación");
        }

    }

    private Multa getMulta() {
        Multa multa = tableMulta.getItems().get(tableMulta.getSelectionModel().getSelectedIndex());
        return multa;
    }

    @FXML
    private void btnCancelarPresed(ActionEvent e) {
        stage.close();
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public void setStage(CancelarMultaStage stage) {
        this.stage = stage;
    }

    public void setTableMulta(TableView<Multa> tableMulta) {
        this.tableMulta = tableMulta;
    }
}

package moduloMulta.control;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import general.control.KeyEventJFXTextFieldController;
import moduloMulta.entitys.Multa;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import general.vista.AlertBox;
import general.vista.IAlertBox;
import java.sql.Date;
import javafx.scene.control.Label;
import moduloMulta.modelo.ConsultaMultaAbs;
import moduloMulta.modelo.ConsultaMultaEst;
import moduloMulta.modelo.ConsultaMultaProf;
import moduloMulta.vista.CancelarMultaStage;

/**
 * Clase que controla la vista MultaBibliotecario.fxml
 *
 * @author Julian Fecha de Creación: 18/07/2019 Fecha de ultima Modificación:
 * 04/08/2019
 */
public class MultaBibliotecarioController implements Initializable {

    @FXML
    private JFXTextField codUserHisMulTxt;
    @FXML
    private JFXButton btnMultaEstudiante;
    @FXML
    private JFXButton btnMultaProfesor;
    @FXML
    private TableView<Multa> tableMulta;
    @FXML
    private TableColumn<Multa, Integer> colCodMulta;
    @FXML
    private TableColumn<Multa, String> colCodUsuario;
    @FXML
    private TableColumn<Multa, String> colNombreUsuario;
    @FXML
    private TableColumn<Multa, Date> colFechaMulta;
    @FXML
    private TableColumn<Multa, String> colCodBarraRecurso;
    @FXML
    private TableColumn<Multa, String> colTituloRecurso;
    @FXML
    private TableColumn<Multa, Integer> colDiasAtrasados;
    @FXML
    private TableColumn<Multa, Integer> colValorTot;
    @FXML
    private TableColumn<Multa, String> colCancelado;
    @FXML
    private Label lblCodUsuario;

    private String tipoUsuario = "estudiante";

    /**
     * Método que se ejecuta automáticamente al enlazar<br>
     * este controlador con su respectiva vista
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        KeyEventJFXTextFieldController eventoTecla = new KeyEventJFXTextFieldController();
        eventoTecla.soloNumeros(codUserHisMulTxt);
        setDisableButton();
        //cargamos las columnas
        colCodMulta.setCellValueFactory(new PropertyValueFactory<>("codMulta"));
        colCodUsuario.setCellValueFactory(new PropertyValueFactory<>("idUsuario"));
        colNombreUsuario.setCellValueFactory(new PropertyValueFactory<>("nombreUsuario"));
        colFechaMulta.setCellValueFactory(new PropertyValueFactory<>("fechaMulta"));
        colCodBarraRecurso.setCellValueFactory(new PropertyValueFactory<>("codBarrasRecurso"));
        colTituloRecurso.setCellValueFactory(new PropertyValueFactory<>("tituloRecurso"));
        colDiasAtrasados.setCellValueFactory(new PropertyValueFactory<>("diasAtrasados"));
        colValorTot.setCellValueFactory(new PropertyValueFactory<>("valorTotal"));
        colCancelado.setCellValueFactory(new PropertyValueFactory<>("candelado"));
        //cargamos los datos
        cargarAllMultas();
    }

    /**
     * Método que por medio de un código busca el<br>
     * historial de multas de un usuario
     *
     * @param event
     */
    @FXML
    private void btnConsultarPressed(ActionEvent event) {
        IAlertBox alert = new AlertBox();
        if (!codUserHisMulTxt.getText().isEmpty()) {
            cargarAllMultas();
            for (int i = 0; i < tableMulta.getItems().size(); i++) {
                System.out.println(i);
                if (!tableMulta.getItems().get(i).getIdUsuario().equals(codUserHisMulTxt.getText().trim())) {
                    tableMulta.getItems().remove(i);
                    i--;
                }
            }
            if (tableMulta.getItems().isEmpty()) {
                alert.showAlert("Anuncio", "Multa " + tipoUsuario, "No se encontraron multas");
                cargarAllMultas();
            } else {
                codUserHisMulTxt.setText("");
            }
        } else {
            alert.showAlert("Anuncio", "Multa " + tipoUsuario, "El campo esta vacio");
        }
    }

    /**
     * Metodo que se encarga de eliminar la multa de<br>
     * un usuario
     *
     * @param event
     * @throws Exception
     */
    @FXML
    private void btnCancelarMultaPressed(ActionEvent event) throws Exception {
        IAlertBox alert = new AlertBox();
        if (tableMulta.getSelectionModel().getSelectedIndex() > -1) {
            CancelarMultaStage stage = new CancelarMultaStage();
            stage.cargarComponentes(tipoUsuario,tableMulta);
        }else{
            alert.showAlert("Anuncio", "Multa " + tipoUsuario, "Seleccione una fila");
        }
        System.out.println(tableMulta.getSelectionModel().getSelectedIndex());
    }

    /**
     * Metodo que carga todas las multas de los estudiantes
     *
     * @param e
     */
    @FXML
    private void btnMultaEstudiantePressed(ActionEvent e) {
        tipoUsuario = "estudiante";
        setDisableButton();
        cargarAllMultas();
    }

    /**
     * Metodo que carga todas las multas de los profesores
     *
     * @param e
     */
    @FXML
    private void btnMultaProfesorPressed(ActionEvent e) {
        tipoUsuario = "profesor";
        setDisableButton();
        cargarAllMultas();
    }

    /**
     * Metodo que carga todas las multas de los usuarios(estudiante/profesor)
     */
    private void cargarAllMultas() {
        ConsultaMultaAbs consulta;
        if (tipoUsuario.equalsIgnoreCase("estudiante")) {
            consulta = new ConsultaMultaEst();
            colCodUsuario.setText("Código estudiante");
        } else {
            consulta = new ConsultaMultaProf();
            colCodUsuario.setText("Identificacíon profesor");
        }
        tableMulta.setItems(consulta.getMultasAll());
    }

    /**
     * el metódo inhabilita el botón que se haya seleccionado.
     *
     * @param busqueda
     */
    private void setDisableButton() {
        if (tipoUsuario.equalsIgnoreCase("estudiante")) {
            lblCodUsuario.setText("Código estudiante");
            btnMultaEstudiante.setDisable(true);
            btnMultaProfesor.setDisable(false);
        } else {
            lblCodUsuario.setText("Identificación profesor");
            btnMultaEstudiante.setDisable(false);
            btnMultaProfesor.setDisable(true);
        }
    }

}

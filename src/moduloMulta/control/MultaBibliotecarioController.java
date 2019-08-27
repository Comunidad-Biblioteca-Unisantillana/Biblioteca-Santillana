package moduloMulta.control;

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
import moduloMulta.modelo.ConsultaMulta;
import general.vista.AlertBox;
import general.vista.IAlertBox;

/**
 * Clase que controla la vista MultaBibliotecario.fxml
 * @author Julian
 * Fecha de Creación: 18/07/2019
 * Fecha de ultima Modificación: 04/08/2019
 */
public class MultaBibliotecarioController implements Initializable {

    @FXML
    private JFXTextField codUserHisMulTxt;
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

    /**
     * Método que se ejecuta automáticamente al enlazar<br>
     * este controlador con su respectiva vista
     * @param url
     * @param rb 
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        KeyEventJFXTextFieldController eventoTecla = new KeyEventJFXTextFieldController();
        eventoTecla.soloNumeros(codUserHisMulTxt);
        tableMulta.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends Multa> arg0, Multa oldValue, Multa newValue) -> {
            tipo = newValue.getTipo();
            codMulta = newValue.getCodMulta();
        });
    }

    /**
     * Método que por medio de un código busca el<br>
     * historial de multas de un usuario
     * @param event 
     */
    @FXML
    private void btnconsultarHisMulPressed(ActionEvent event) {
        if (!codUserHisMulTxt.getText().isEmpty()) {
            try {
                cargarDatosTableMultas(codUserHisMulTxt.getText());
                consultoMultas = true;
            } catch (Exception ex) {
                System.err.println("Error al cargar los datos del historial de multas");
            }
        } else {
            IAlertBox alert = new AlertBox();
            alert.showAlert("Anuncio", "Historial de multas de un estudiante", "Ingrese el código del estudiante");
        }
    }

    /**
     * Metodo que se encarga de eliminar la multa de<br>
     * un usuario
     * @param event
     * @throws Exception 
     */
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

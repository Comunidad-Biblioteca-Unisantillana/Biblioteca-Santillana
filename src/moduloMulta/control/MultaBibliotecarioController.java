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
import moduloMulta.modelo.ConsultaMulta;
import general.vista.AlertBox;
import general.vista.IAlertBox;
import java.sql.Date;
import moduloMulta.modelo.ConsultaMultaAbs;
import moduloMulta.modelo.ConsultaMultaEst;
import moduloMulta.modelo.ConsultaMultaProf;

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
    

    private int codMulta = 0;
    private String tipo = "";
    private String idUsuario = "";
    private boolean consultoMultas = false;
    private String tipoUsuario = "estudiante";

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
            tipo = newValue.getTipoRecurso();
            codMulta = newValue.getCodMulta();
        });
        setDisableButton();
        cargarAllMultas();
    }

    /**
     * Método que por medio de un código busca el<br>
     * historial de multas de un usuario
     * @param event 
     */
    @FXML
    private void btnconsultarHisMulPressed(ActionEvent event) {
        consultoMultas = false;
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
                cargarDatosTableMultas(idUsuario);

            } else {
                alert.showAlert("Anuncio", "Login Estudiante", "La multa no ha sido eliminada");
            }
        } else {
            alert.showAlert("Anuncio", "Login Estudiante", "No hay multas seleccionadas");
        }
    }
    
    @FXML
    private void btnMultaEstudiantePressed(ActionEvent e){
        tipoUsuario = "estudiante";
        setDisableButton();
        cargarAllMultas();
    }
    
    @FXML
    private void btnMultaProfesorPressed(ActionEvent e){
        tipoUsuario = "profesor";
        setDisableButton();
        cargarAllMultas();
    }

    /**
     * Método que se encarga de cargar las multas que se han realizado a un
     * estudiante por medio de código.
     * @param idUsuario
     * @throws Exception
     */
    public void cargarDatosTableMultas(String idUsuario) throws Exception {
        ConsultaMultaAbs consulta;
        if(tipoUsuario.equalsIgnoreCase("estudiante")){
            consulta = new ConsultaMultaEst();
        }else{
            consulta = new ConsultaMultaProf();
        }
        colCodMulta.setCellValueFactory(new PropertyValueFactory<>("codMulta"));
        colCodUsuario.setCellValueFactory(new PropertyValueFactory<>("codPrestamo"));
        colDiasAtrasados.setCellValueFactory(new PropertyValueFactory<>("diasAtrasados"));
        colValorTot.setCellValueFactory(new PropertyValueFactory<>("valorTotal"));
        colCancelado.setCellValueFactory(new PropertyValueFactory<>("cancelado"));
        colTituloRecurso.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        tableMulta.setItems(consulta.getMultasUsuario(idUsuario));

        this.idUsuario = idUsuario;
    }
    
    public void cargarAllMultas(){
        ConsultaMultaAbs consulta;
        if(tipoUsuario.equalsIgnoreCase("estudiante")){
            consulta = new ConsultaMultaEst();
            colCodUsuario.setText("Código estudiante");
        }else{
            consulta = new ConsultaMultaProf();
            colCodUsuario.setText("Identificacíon profesor");
        }
        colCodMulta.setCellValueFactory(new PropertyValueFactory<>("codMulta"));
        colCodUsuario.setCellValueFactory(new PropertyValueFactory<>("idUsuario"));
        colNombreUsuario.setCellValueFactory(new PropertyValueFactory<>("nombreUsuario"));
        colFechaMulta.setCellValueFactory(new PropertyValueFactory<>("fechaMulta"));
        colCodBarraRecurso.setCellValueFactory(new PropertyValueFactory<>("codBarrasRecurso"));
        colTituloRecurso.setCellValueFactory(new PropertyValueFactory<>("tituloRecurso"));
        colDiasAtrasados.setCellValueFactory(new PropertyValueFactory<>("diasAtrasados"));
        colValorTot.setCellValueFactory(new PropertyValueFactory<>("valorTotal"));
        colCancelado.setCellValueFactory(new PropertyValueFactory<>("candelado"));
        tableMulta.setItems(consulta.getMultasAll());
    }
    
    /**
     * el metódo inhabilita el botón que se haya seleccionado.
     *
     * @param busqueda
     */
    private void setDisableButton() {
        if(tipoUsuario.equalsIgnoreCase("estudiante")){
            btnMultaEstudiante.setDisable(true);
            btnMultaProfesor.setDisable(false);
        }else{
            btnMultaEstudiante.setDisable(false);
            btnMultaProfesor.setDisable(true);
        }
    }

}

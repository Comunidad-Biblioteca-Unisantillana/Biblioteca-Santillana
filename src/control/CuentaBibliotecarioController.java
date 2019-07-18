package control;

import entitys.Multa;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import modelo.ConsultaMulta;
import vista.AlertBox;
import vista.CuentaBibliotecarioStage;
import vista.IAlertBox;
import vista.LoginUnisantillanaStage;

/**
 * FXML Controller class
 *
 * @author stiven valencia
 */
public class CuentaBibliotecarioController {

    private CuentaBibliotecarioStage stage;

    @FXML
    private TextField  bibliotecarioPresTxt, bibliotecarioResTxt , bibliotecarioDevTxt, codEstudianteHisMulTxt;

    private FichaTecnicaLibroController ftlc;
    private FichaTecnicaEnciclopediaController ftec;
    private FichaTecnicaMapaController ftmc;
    private FichaTecnicaPeriodicoController ftpc;
    private FichaTecnicaRevistaController ftrc;
    private FichaTecnicaDiccionarioController ftdc;

    private Parent rootFTLibro, rootFTEnciclopedia, rootFTMapa, rootFTPeriodico, rootFTRevista, rootFTDiccionario;

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
    private String codEstudiante, tipo = "";

    /**
     * Initializes the controller class.
     */
    public void initialize() {
        seleccionarFila();
    }
   

    @FXML
    private void btnconsultarHisMulPressed(ActionEvent event) {

        if (!codEstudianteHisMulTxt.getText().isEmpty()) {
            try {
                cargarDatosTableMultas(codEstudianteHisMulTxt.getText());
            } catch (Exception ex) {
                System.err.println("Error al cargar los datos del historial de multas");
            }
        } else {
            IAlertBox alert = new AlertBox();
            alert.showAlert("Anuncio", "Historial de multas de un estudiante", "Ingrese el código del estudiante");
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


    /**
     * Método que asigna la identificación del bibliotecario.
     *
     * @param idBibliotecario
     */
    public void cargarIdBibliotecario(String idBibliotecario) {
        bibliotecarioPresTxt.setText(idBibliotecario);
        bibliotecarioResTxt.setText(idBibliotecario);
        bibliotecarioDevTxt.setText(idBibliotecario);
    }

    /**
     * Método que retorna al login de la universidad.
     */
    private void retornarLoginUnisantillana() {
        CuentaBibliotecarioStage.getInstance().close();
        LoginUnisantillanaStage.getInstance().show();
    }

    private void seleccionarFila()  {

            tableMulta.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends Multa> arg0, Multa oldValue, Multa newValue) -> {                
                tipo = newValue.getTipo();
                codMulta = newValue.getCodMulta();
            });
   
    }

    @FXML
    private void itemSalirPressed(ActionEvent event) {
        retornarLoginUnisantillana();
    }

    @FXML
    private void itemAcercaDe(ActionEvent event) {
    }

    @FXML
    private void btnCancelarMultaPressed(ActionEvent event) throws Exception {
        IAlertBox alert = new AlertBox();

        ConsultaMulta consultaMulta = new ConsultaMulta();
        if (!(consultaMulta.eliminarMulta(codMulta, tipo))) {
            alert.showAlert("Anuncio", "Multa", "La multa ha sido eliminada");
            cargarDatosTableMultas(codEstudiante);

        } else {
            alert.showAlert("Anuncio", "Login Estudiante", "La multa no ha sido eliminada");
        }
    }

    public void setStage(CuentaBibliotecarioStage stage) {
        this.stage = stage;
    }
}

package control;

import entitys.Multa;
import entitys.Prestamo;
import java.io.IOException;
import java.sql.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import modelo.ConsultaMulta;
import modelo.ConsultaOPAC;
import modelo.ConsultaPrestamo;
import vista.AlertBox;
import vista.CuentaEstudianteStage;
import vista.IAlertBox;
import vista.LoginUnisantillanaStage;
import vista.StageTableOPAC;

/**
 * FXML Controller class
 *
 * @author stive
 */
public class CuentaEstudianteController {

    private CuentaEstudianteStage stageEst;
    private FichaTecnicaLibroController ftlc;
    private FichaTecnicaEnciclopediaController ftec;
    private FichaTecnicaMapaController ftmc;
    private FichaTecnicaPeriodicoController ftpc;
    private FichaTecnicaRevistaController ftrc;
    private FichaTecnicaDiccionarioController ftdc;
    private Parent rootFTLibro, rootFTEnciclopedia, rootFTMapa, rootFTPeriodico, rootFTRevista, rootFTDiccionario;
    private String nameRoot;

    private boolean estadoPanelLibro, estadoPanelEnciclopedia, estadoPanelDiccionario, estadoPanelPeriodico, estadoPanelMapa, estadoPanelRevista;
    
    @FXML
    private TableColumn<Prestamo, Integer> codPrestamosTable;
    @FXML
    private TableColumn<Prestamo, Date> fechaPrestamoTable;
    @FXML
    private TableColumn<Prestamo, Date> fechaDevolucionTable;
    @FXML
    private TableColumn<Prestamo, String> tituloTable;
    @FXML
    private TextField codBarrasOpacTxt;
    @FXML
    private TextField tituloOpacTxt;

    private String codEstudiante;
    @FXML
    private TableView<Prestamo> tablePrestamo;
    @FXML
    private GridPane gridPaneMultas;
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
    @FXML
    private Button btnConsultarMultasPressed;
    @FXML
    private GridPane panelOpac;
  
    public void initialize() {
        cargarFXML();
        validacionPaneles();
    }


    /**
     * Método que asigna el código del estudiante.
     *
     * @param codEstudiante
     */
    public void setCodEstudiante(String codEstudiante) {
        this.codEstudiante = codEstudiante;
    }

    @FXML
    private void itemSalirPressed(ActionEvent event) {
        LoginUnisantillanaStage.getInstance().show();
        CuentaEstudianteStage.getInstance().close();
    }

    public void cargarDatosTablePrestamos(String codEstudiante) throws Exception {
        ConsultaPrestamo consulta = new ConsultaPrestamo();

        codPrestamosTable.setCellValueFactory(new PropertyValueFactory<>("codPrestamo"));
        tituloTable.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        fechaPrestamoTable.setCellValueFactory(new PropertyValueFactory<>("fechaPrestamo"));
        fechaDevolucionTable.setCellValueFactory(new PropertyValueFactory<>("fechaDevolucion"));
        tablePrestamo.setItems(consulta.getPrestamos(codEstudiante));
    }

    @FXML
    private void itemAcercaDePressed(ActionEvent event) {
    }

    @FXML
    private void btnConsultarPrestamosPressed(ActionEvent event) {
         try {
            cargarDatosTablePrestamos(codEstudiante);
        } catch (Exception ex) {
            Logger.getLogger(CuentaEstudianteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void btnconsultarHisMulPressed(ActionEvent event) {
        try {
            cargarDatosTableMultas(codEstudiante);
        } catch (Exception ex) {
            Logger.getLogger(CuentaEstudianteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     public void cargarDatosTableMultas(String codEstudiante) throws Exception {
        ConsultaMulta consulta = new ConsultaMulta();

        colCodMulta.setCellValueFactory(new PropertyValueFactory<>("codMulta"));
        colCodPrestamo.setCellValueFactory(new PropertyValueFactory<>("codPrestamo"));
        colDiasAtrasados.setCellValueFactory(new PropertyValueFactory<>("diasAtrasados"));
        colValorTot.setCellValueFactory(new PropertyValueFactory<>("valorTotal"));
        colCancelado.setCellValueFactory(new PropertyValueFactory<>("cancelado"));
        colTipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        tableMulta.setItems(consulta.getMultas(codEstudiante));

    }

    @FXML
    private void handledBtnBuscarTitulo(ActionEvent event) {
        if(!tituloOpacTxt.getText().isEmpty()){
            StageTableOPAC stage = new StageTableOPAC(tituloOpacTxt.getText().trim(), stageEst);
        }
        else{
            IAlertBox alert = new AlertBox();
            alert.showAlert("Anuncio", "Busqueda", "Ingrese el titulo del recurso");
        }
    }

    @FXML
    private void handledBtnBuscarCodBarras(ActionEvent event) {
        IAlertBox alert = new AlertBox();
        if(!codBarrasOpacTxt.getText().isEmpty()){
            ConsultaOPAC consulta = new ConsultaOPAC();
            String nombreEntidad = consulta.consultarTabla(codBarrasOpacTxt.getText().trim());
            
            if(!nombreEntidad.isEmpty())
                crearFichaTecnica(panelOpac,  nombreEntidad, codBarrasOpacTxt.getText());
            else
                alert.showAlert("Anuncio", "Busqueda", "No se encontro ningún recurso con ese código");
        }
        else{
            alert.showAlert("Anuncio", "Busqueda", "Ingrese el código de barras del recurso");
        }
    }

    
    private void crearFichaTecnica(GridPane panel, String tipoRecurso, String codBarras) {
        try {
            visibilidad();
            if (tipoRecurso.equalsIgnoreCase("libro")) {     
                QueryControl.getInstance().consultarAutorRecurso(codBarras, tipoRecurso);
                QueryControl.getInstance().consultarMaterias(codBarras, tipoRecurso);
                anadirPanel(estadoPanelLibro, rootFTLibro, panel, "libro");
                ftlc.cargarDatos(codBarras);
            } else if (tipoRecurso.equalsIgnoreCase("enciclopedia")) {
                QueryControl.getInstance().consultarAutorRecurso(codBarras, tipoRecurso);
                QueryControl.getInstance().consultarMaterias(codBarras, tipoRecurso);
                anadirPanel(estadoPanelEnciclopedia, rootFTEnciclopedia, panel, "enciclopedia");
                ftec.cargarDatos(codBarras);
            } else if (tipoRecurso.equalsIgnoreCase("periodico")) {
                anadirPanel(estadoPanelPeriodico, rootFTPeriodico, panel, "periodico");
                ftpc.cargarDatos(codBarras);
            } else if (tipoRecurso.equalsIgnoreCase("mapa")) {
                anadirPanel(estadoPanelMapa, rootFTMapa, panel, "mapa");
                ftmc.cargarDatos(codBarras);
            } else if (tipoRecurso.equalsIgnoreCase("revista")) {
                QueryControl.getInstance().consultarMaterias(codBarras, tipoRecurso);
                anadirPanel(estadoPanelRevista, rootFTRevista, panel, "revista");
                ftrc.cargarDatos(codBarras);
            } else if (tipoRecurso.equalsIgnoreCase("diccionario")) {
                QueryControl.getInstance().consultarAutorRecurso(codBarras, tipoRecurso);
                QueryControl.getInstance().consultarMaterias(codBarras, tipoRecurso);
                anadirPanel(estadoPanelDiccionario, rootFTDiccionario, panel, "diccionario");
                ftdc.cargarDatos(codBarras);
            }
        } catch (Exception e) {
            System.out.println("Error al momento de cargar la ficha tecnica");
        }
    }
    
    private void visibilidad() {
        rootFTLibro.setVisible(false);
        rootFTEnciclopedia.setVisible(false);
        rootFTMapa.setVisible(false);
        rootFTPeriodico.setVisible(false);
        rootFTRevista.setVisible(false);
        rootFTDiccionario.setVisible(false);
    }
    
    /**
     * Método que se encargar de asignar un valor true a los estados de paneles.
     */
    private void validacionPaneles() {
        estadoPanelLibro = true;
        estadoPanelEnciclopedia = true;
        estadoPanelDiccionario = true;
        estadoPanelMapa = true;
        estadoPanelRevista = true;
        estadoPanelPeriodico = true;
    }
    private void validacionPanelesFalso(String nombrePanel){
        switch (nombrePanel){
            case "libro":
                estadoPanelLibro = false;
                break;
            case "enciclopedia":
                estadoPanelEnciclopedia = false;
                    break;
            case "diccionario":
                estadoPanelDiccionario = false;
                break;
            case "periodico":
                estadoPanelDiccionario = false;
                break;
            case "mapa":
                estadoPanelMapa = false;
                break;
            case "revista":
                estadoPanelRevista = false;
                break;
                    
        }
        
    }

    /**
     * Método que se encarga de cargar cada FXML.
     */
    private void cargarFXML() {
        try {
            FXMLLoader loaderLibro = new FXMLLoader(getClass().getResource("/vista/FichaTecnicaLibro.fxml"));
            FXMLLoader loaderEnciclopedia = new FXMLLoader(getClass().getResource("/vista/FichaTecnicaEnciclopedia.fxml"));
            FXMLLoader loaderMapa = new FXMLLoader(getClass().getResource("/vista/FichaTecnicaMapa.fxml"));
            FXMLLoader loaderPeriodico = new FXMLLoader(getClass().getResource("/vista/FichaTecnicaPeriodico.fxml"));
            FXMLLoader loaderRevista = new FXMLLoader(getClass().getResource("/vista/FichaTecnicaRevista.fxml"));
            FXMLLoader loaderDiccionario = new FXMLLoader(getClass().getResource("/vista/FichaTecnicaDiccionario.fxml"));

            rootFTLibro = loaderLibro.load();
            ftlc = loaderLibro.getController();

            rootFTEnciclopedia = loaderEnciclopedia.load();
            ftec = loaderEnciclopedia.getController();

            rootFTMapa = loaderMapa.load();
            ftmc = loaderMapa.getController();

            rootFTPeriodico = loaderPeriodico.load();
            ftpc = loaderPeriodico.getController();

            rootFTRevista = loaderRevista.load();
            ftrc = loaderRevista.getController();

            rootFTDiccionario = loaderDiccionario.load();
            ftdc = loaderDiccionario.getController();

        } catch (IOException ex) {
            Logger.getLogger(CuentaBibliotecarioController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Método que se encargar de anadir paneles a la escena.
     *
     * @param estadoPanel
     * @param root
     * @param panel
     */
    private void anadirPanel(boolean estadoPanel, Parent root, GridPane panel, String nombrePanel) {
        if (estadoPanel) { 
            panel.add(root, 0, 0);
            validacionPanelesFalso(nombrePanel);
        }
        root.setVisible(true);
    }

    public void setStageEst(CuentaEstudianteStage stageEst) {
        this.stageEst = stageEst;
    }
   
}

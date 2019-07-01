package control;

import entitys.Multa;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import modelo.ConsultaMulta;
import modelo.ConsultaOPAC;
import modelo.GeneradorDevolucion;
import modelo.GeneradorPrestamoRecurso;
import modelo.GeneradorReserva;
import vista.AlertBox;
import vista.CuentaBibliotecarioStage;
import vista.IAlertBox;
import vista.LoginUnisantillanaStage;
import vista.StageTableOPAC;

/**
 * FXML Controller class
 *
 * @author stiven valencia
 */
public class CuentaBibliotecarioController {

    private CuentaBibliotecarioStage stage = null;
    @FXML
    private GridPane gridPanePrestamos, gridPaneReservas, gridPaneOpac, gridPaneDevoluciones, gridPaneMultas, panelOpac;

    @FXML
    private TextField codBarrasPresTxt, codEstudiantePresTxt, fechaPrestamoPresTxt, fechaDevolucionPresTxt, bibliotecarioPresTxt, codBarrasResTxt, bibliotecarioResTxt,
            codBarrasDevTxt, bibliotecarioDevTxt, codEstudianteHisMulTxt, codBarrasOpacTxt, tituloOpacTxt;

    private FichaTecnicaLibroController ftlc;
    private FichaTecnicaEnciclopediaController ftec;
    private FichaTecnicaMapaController ftmc;
    private FichaTecnicaPeriodicoController ftpc;
    private FichaTecnicaRevistaController ftrc;
    private FichaTecnicaDiccionarioController ftdc;

    private Parent rootFTLibro, rootFTEnciclopedia, rootFTMapa, rootFTPeriodico, rootFTRevista, rootFTDiccionario;
    @FXML
    private GridPane panelPrestamos;
    @FXML
    private GridPane panelReservas;
    @FXML
    private GridPane panelDevoluciones;

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
    private ComboBox<String> cboTipoPrestamo;
    @FXML
    private TextField txtFechaReserva;
    @FXML
    private TextField txtFechaLimite;
    @FXML
    private TextField txtCodEstReserva;
    private int codMulta = 0;
    private String codEstudiante, tipo = "";
    
    @FXML
    private ComboBox<String> cboTipoRecurso;
    @FXML
    private ComboBox<String> cboEstadoRecurso;

    /**
     * Initializes the controller class.
     */
    public void initialize() {

        //----------------------------------------------
        ObservableList<String> listaTipoRecurso = FXCollections.observableArrayList( "Libro", "Enciclopedia", "Diccionario",
                "Revista", "Periodico", "Mapa");
        ObservableList<String> listaEstadosFisicos = FXCollections.observableArrayList( "Bueno", "Regular", "Malo");
        cboTipoPrestamo.setItems(listaTipoRecurso);
        cboTipoRecurso.setItems(listaTipoRecurso);
        cboEstadoRecurso.setItems(listaEstadosFisicos);
        cboTipoPrestamo.setValue("Libro");
        cboTipoRecurso.setValue("Libro");
        cboEstadoRecurso.setValue("Bueno");
        //----------------------------------------------
        cargarFXML();
        validacionPaneles();
        seleccionarFila();

    }

    @FXML
    private void btnPrestarPressed(ActionEvent event) {
        IAlertBox alert = new AlertBox();
        if (!codBarrasPresTxt.getText().isEmpty() && !codEstudiantePresTxt.getText().isEmpty()) {
            try {
                GeneradorPrestamoRecurso generador = new GeneradorPrestamoRecurso();

                if (generador.createPrestamo(codBarrasPresTxt.getText(), codEstudiantePresTxt.getText(), bibliotecarioPresTxt.getText(),
                        cboTipoPrestamo.getValue(), fechaPrestamoPresTxt, fechaDevolucionPresTxt)) {

                    crearFichaTecnica(panelPrestamos, cboTipoPrestamo.getValue(), codBarrasPresTxt.getText());

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
    private void btnReservarPressed(ActionEvent event) {
        IAlertBox alert = new AlertBox();
        if(!codBarrasResTxt.getText().isEmpty() && !txtCodEstReserva.getText().isEmpty()){
            try{
                GeneradorReserva generador = new GeneradorReserva();

                if(generador.createReserva(codBarrasResTxt.getText(), txtCodEstReserva.getText(), bibliotecarioPresTxt.getText(), 
                        txtFechaReserva, txtFechaLimite)){
                    
                    
                    crearFichaTecnica(panelReservas, "libro", codBarrasResTxt.getText());
                    alert.showAlert("Anuncio", "Reserva", "La reserva ha sido realizado con éxito!");
                }
            }
            catch(Exception e){
                System.out.println("Error al realizar la reseva");
            }
        }
        else{
            alert.showAlert("Anuncio", "Reserva", "Por favor ingrese todos los campos!");
        }
    }

    @FXML
    private void btnDevolverPressed(ActionEvent event) {
         IAlertBox alert = new AlertBox();
        if(!codBarrasDevTxt.getText().isEmpty() && ! bibliotecarioDevTxt.getText().isEmpty()){
            try {
                GeneradorDevolucion generador = new GeneradorDevolucion();

                if(generador.createDevolucion(codBarrasDevTxt.getText(), bibliotecarioDevTxt.getText(), cboTipoRecurso.getValue(),
                        cboEstadoRecurso.getValue())){
                    crearFichaTecnica(panelDevoluciones, cboTipoRecurso.getValue(), codBarrasDevTxt.getText());
                    alert.showAlert("Anuncio", "Devolución", "La devolución ha sido realizado con éxito!");
                }        
            } catch (Exception ex) {
                System.out.println("Error al generar  la devolución");
            }
        }
        else{
            alert.showAlert("Anuncio", "Devolución", "Por favor ingrese todos los campos!");
        }
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
    
    @FXML
    private void handledBtnBuscarTitulo(ActionEvent event){
        if(!tituloOpacTxt.getText().isEmpty()){
            visibilidad();
            StageTableOPAC stageTable = new StageTableOPAC(tituloOpacTxt.getText().trim(), stage);
        }
        else{
            IAlertBox alert = new AlertBox();
            alert.showAlert("Anuncio", "Busqueda", "Ingrese el titulo del recurso");
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

    private void visibilidad() {
        rootFTLibro.setVisible(false);
        rootFTEnciclopedia.setVisible(false);
        rootFTMapa.setVisible(false);
        rootFTPeriodico.setVisible(false);
        rootFTRevista.setVisible(false);
        rootFTDiccionario.setVisible(false);
    }

    @FXML
    private void prestamosChanged(Event event) {
        validacionPaneles();
    }

    @FXML
    private void reservasChanged(Event event) {
        validacionPaneles();
    }

    @FXML
    private void devolucionesChanged(Event event) {
        validacionPaneles();
    }

    @FXML
    private void multasChanged(Event event) {
        validacionPaneles();
    }

    @FXML
    private void opacChanged(Event event) {
        validacionPaneles();
    }

    private String nameRoot;

    private boolean estadoPanelLibro, estadoPanelEnciclopedia, estadoPanelDiccionario, estadoPanelPeriodico, estadoPanelMapa, estadoPanelRevista;

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
     * Método que asigna la identificación del bibliotecario.
     *
     * @param idBibliotecario
     */
    public void cargarIdBibliotecario(String idBibliotecario) {
        bibliotecarioPresTxt.setText(idBibliotecario);
        bibliotecarioResTxt.setText(idBibliotecario);
        bibliotecarioDevTxt.setText(idBibliotecario);
    }
    
    private void crearFichaTecnica(GridPane panel, String tipoRecurso, String codBarras) {
        visibilidad();
        try {
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


    private void anadirPanel(boolean estadoPanel, Parent root, GridPane panel, String nombrePanel) {
        if (estadoPanel) {
            panel.add(root, 0, 0);

             validacionPanelesFalso(nombrePanel);
        }
        this.nameRoot = nombrePanel;
        root.setVisible(true);
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
    @FXML
    private void btnPrestamosLimpiarPressed(ActionEvent event) {
        limpiarCamposTextos(nameRoot);
    }

    private void btnReservasLimpiar(ActionEvent event) {
        limpiarCamposTextos(nameRoot);
    }

    private void btnDevolucionesLimpiarCamposTextosPressed(ActionEvent event) {
        limpiarCamposTextos(nameRoot);
    }

    @FXML
    private void btnOpacLimpiarPressed(ActionEvent event) {
        limpiarCamposTextos(nameRoot);
    }

    /**
     * Método que retorna al login de la universidad.
     */
    private void retornarLoginUnisantillana() {
        CuentaBibliotecarioStage.getInstance().close();
        LoginUnisantillanaStage.getInstance().show();
    }

    @FXML
    private void btnReservasLimpiarPressed(ActionEvent event) {

    }

    @FXML
    private void btnDevolucionesLimpiarPressed(ActionEvent event) {
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
    
     private void limpiarCamposTextos(String nameRoot) {
        switch (nameRoot) {
            case "libro":
                ftlc.limpiarCampos();
                break;
            case "enciclopedia":
                ftec.limpiarCampos();
                break;
            case "periodico":
                ftpc.limpiarCampos();
                break;
            case "mapa":
                ftpc.limpiarCampos();
                break;
            case "diccionario":
                ftdc.limpiarCampos();
                break;
            case "revista":
                ftrc.limpiarCampos();
                break;
        }
    }
}

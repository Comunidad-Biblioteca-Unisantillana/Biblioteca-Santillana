package moduloOPAC.control;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import moduloOPAC.modelo.Recurso;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import moduloOPAC.modelo.BusquedaAutorDiccionario;
import moduloOPAC.modelo.BusquedaAutorEnciclopedia;
import moduloOPAC.modelo.BusquedaAutorLibro;
import moduloOPAC.modelo.BusquedaCodigoAbs;
import moduloOPAC.modelo.BusquedaCodigoBarras;
import moduloOPAC.modelo.BusquedaMateriaDiccionario;
import moduloOPAC.modelo.BusquedaMateriaEnciclopedia;
import moduloOPAC.modelo.BusquedaMateriaLibro;
import moduloOPAC.modelo.BusquedaMateriaRevista;
import moduloOPAC.modelo.ContextoBusquedaPorCodigo;
import moduloOPAC.modelo.IBusquedaAutorRecurso;
import moduloOPAC.modelo.IBusquedaMateriaRecurso;
import moduloOPAC.vista.FichaTecnicaPanel;
import moduloOPAC.vista.FichaTecnicaStage;
import vista.AlertBox;
import vista.IAlertBox;

/**
 * clase que controla la vista TablaOPAC.fxml
 *
 * @author Camilo
 * @creado: 05/09/2018
 * @author Miguel Fernández
 * @modificado 20/08/2019
 */
public class TablaOPACController implements Initializable {

    @FXML
    private TableView<Recurso> tableOPAC;
    @FXML
    private TableColumn<Recurso, String> colCodBarras;
    @FXML
    private TableColumn<Recurso, String> colISBN_ISSN;
    @FXML
    private TableColumn<Recurso, String> colTitulo;
    @FXML
    private TableColumn<Recurso, String> colIdioma;
    @FXML
    private TableColumn<Recurso, String> colDisponibilidad;
    @FXML
    private TableColumn<Recurso, String> colTipoRecurso;
    @FXML
    private Label labelMensaje;
    private IAlertBox alert;
    private GridPane gridPaneFicha;
    private int ancho;
    private int alto;
    private boolean estadoVentanaFicha;

    /**
     * el método que se ejecuta automáticamente al enlazar este controlador con
     * su respectiva vista.
     *
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colCodBarras.setCellValueFactory(new PropertyValueFactory<>("codBarras"));
        colISBN_ISSN.setCellValueFactory(new PropertyValueFactory<>("isbn_issn"));
        colTitulo.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        colIdioma.setCellValueFactory(new PropertyValueFactory<>("idioma"));
        colDisponibilidad.setCellValueFactory(new PropertyValueFactory<>("disponibilidad"));
        colTipoRecurso.setCellValueFactory(new PropertyValueFactory<>("tipoRecurso"));
        tableOPAC.getSelectionModel().clearSelection();
        alert = new AlertBox();
        estadoVentanaFicha = false;
    }

    /**
     * el método que carga los resultados de la busqueda en la tabla.
     *
     * @param cadena
     */
    public void cargarDatosTabla(String cadena, ObservableList<Recurso> recursos) {
        tableOPAC.setItems(recursos);
        tableOPAC.getSelectionModel().clearSelection();

        if (recursos.size() == 1) {
            labelMensaje.setText("Hay " + recursos.size()
                    + " resultado obtenido, con el criterio de búsqueda ingresado: " + cadena + ".");
        } else {
            labelMensaje.setText("Hay " + recursos.size()
                + " resultados obtenidos, con el criterio de búsqueda ingresado: " + cadena + ".");
        }
    }

    /**
     * el metódo busca y muestra la ficha técnica del recurso seleccionado.
     *
     * @param event
     */
    @FXML
    private void btnVerFichaTecnica(ActionEvent event) {
        int filaSeleccionada = tableOPAC.getSelectionModel().getFocusedIndex();

        if (filaSeleccionada > -1) {
            if (estadoVentanaFicha == false) {
                Recurso recurso = tableOPAC.getSelectionModel().getSelectedItem();
                buscarRecurso(recurso.getTipoRecurso(), recurso.getCodBarras());
                estadoVentanaFicha = true;
            } else {
                alert.showAlert("Anuncio", "Ver Ficha Técnica", "Por favor cerar la ventana actual "
                        + "de la ficha técnica del recurso abierta, "
                        + "para poder visulizar la ficha de otro recurso.");
            }

            tableOPAC.getSelectionModel().clearSelection();
        } else {
            alert.showAlert("Anuncio", "Ver Ficha Técnica", "Por favor  seleccionar un recurso "
                    + "del resultado de la búsqueda, para poder ver su ficha técnica.");
        }
    }

    /**
     * el metódo busca por medio de un código la información de un recurso y la
     * visulaiza en una ventana.
     *
     * @param busquedaCodigoAbs
     * @param entidad
     * @param codigo
     */
    private void buscarRecurso(String entidad, String codigo) {
        ContextoBusquedaPorCodigo contextoBusquedaPorCodigo = new ContextoBusquedaPorCodigo();
        BusquedaCodigoAbs busquedaCodigoAbs = new BusquedaCodigoBarras();

        contextoBusquedaPorCodigo.setBusquedaPorCodigo(busquedaCodigoAbs);

        Object recurso = null;
        IBusquedaAutorRecurso iBusquedaAutorRecurso = null;
        IBusquedaMateriaRecurso iBusquedaMateriaRecurso = null;

        switch (entidad) {
            case "libro":
                recurso = contextoBusquedaPorCodigo.buscarLibroPorCodigo(codigo);

                iBusquedaAutorRecurso = new BusquedaAutorLibro();
                iBusquedaAutorRecurso.buscarAutorRecurso(codigo);

                iBusquedaMateriaRecurso = new BusquedaMateriaLibro();
                iBusquedaMateriaRecurso.buscarMateriaRecurso(codigo);

                ancho = 1120;
                alto = 540;
                break;
            case "enciclopedia":
                recurso = contextoBusquedaPorCodigo.buscarEnciclopediaPorCodigo(codigo);

                iBusquedaAutorRecurso = new BusquedaAutorEnciclopedia();
                iBusquedaAutorRecurso.buscarAutorRecurso(codigo);

                iBusquedaMateriaRecurso = new BusquedaMateriaEnciclopedia();
                iBusquedaMateriaRecurso.buscarMateriaRecurso(codigo);

                ancho = 1180;
                alto = 540;
                break;
            case "diccionario":
                recurso = contextoBusquedaPorCodigo.buscarDiccionarioPorCodigo(codigo);

                iBusquedaAutorRecurso = new BusquedaAutorDiccionario();
                iBusquedaAutorRecurso.buscarAutorRecurso(codigo);

                iBusquedaMateriaRecurso = new BusquedaMateriaDiccionario();
                iBusquedaMateriaRecurso.buscarMateriaRecurso(codigo);

                ancho = 1140;
                alto = 540;
                break;
            case "revista":
                recurso = contextoBusquedaPorCodigo.buscarRevistaPorCodigo(codigo);

                iBusquedaMateriaRecurso = new BusquedaMateriaRevista();
                iBusquedaMateriaRecurso.buscarMateriaRecurso(codigo);

                ancho = 700;
                alto = 400;
                break;
            case "periodico":
                recurso = contextoBusquedaPorCodigo.buscarPeriodicoPorCodigo(codigo);

                ancho = 710;
                alto = 360;
                break;
            default:
                recurso = contextoBusquedaPorCodigo.buscarMapaPorCodigo(codigo);

                ancho = 710;
                alto = 360;
                break;
        }

        FichaTecnicaPanel fichaTecticaPanel = new FichaTecnicaPanel();

        if (iBusquedaAutorRecurso == null) {
            if (iBusquedaMateriaRecurso == null) {
                gridPaneFicha = fichaTecticaPanel.cargarFichaTecnica(recurso, "", "");
            } else {
                gridPaneFicha = fichaTecticaPanel.cargarFichaTecnica(recurso, "", iBusquedaMateriaRecurso.getCadenaMaterias());
            }
        } else {
            gridPaneFicha = fichaTecticaPanel.cargarFichaTecnica(recurso, iBusquedaAutorRecurso.getCadenaAutores(), 
                    iBusquedaMateriaRecurso.getCadenaMaterias());
        }

        FichaTecnicaStage fichaTecnicaStage = new FichaTecnicaStage(alto, ancho);
        fichaTecnicaStage.setGridPaneFicha(gridPaneFicha);
        fichaTecnicaStage.setTablaOPACController(this);
    }

    /**
     * el metódo modifica el estado en que se encuentra la ventana que visuliza
     * la ficha tecnica del recurso, para poder visualizar otro.
     *
     * @param estadoVentanaFicha
     */
    public void setEstadoVentanaFicha(boolean estadoVentanaFicha) {
        this.estadoVentanaFicha = estadoVentanaFicha;
    }

}

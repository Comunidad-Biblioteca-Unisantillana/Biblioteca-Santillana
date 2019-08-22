package moduloOPAC.control;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import control.KeyEventJFXTextFieldController;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.layout.BorderPane;
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
import moduloOPAC.modelo.Busqueda_ISBN_ISSN;
import moduloOPAC.modelo.ContextoBusquedaPorCodigo;
import moduloOPAC.modelo.IBusquedaAutorRecurso;
import moduloOPAC.modelo.IBusquedaMateriaRecurso;
import moduloOPAC.vista.FichaTecnicaPanel;
import recursos1.entitys.Diccionario;
import recursos1.entitys.Enciclopedia;
import recursos1.entitys.Libro;
import recursos1.entitys.Periodico;
import recursos1.entitys.Revista;
import vista.AlertBox;
import vista.IAlertBox;

/**
 * Clase que controla la vista CodigoOPAC.fxml
 *
 * @author Julian
 * @creado 18/07/2019
 * @author Miguel Fernández
 * @modificado 18/08/2019
 */
public class CodigoOPACController implements Initializable {

    @FXML
    private JFXTextField txtCodigo;
    @FXML
    private JFXComboBox<String> cboTipoBusqueda;
    @FXML
    private GridPane gridPaneFicha;
    private IAlertBox alert;
    @FXML
    private GridPane gridPaneMenu;

    /**
     * el método que se ejecuta automáticamente al enlazar este controlador con
     * su respectiva vista.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        KeyEventJFXTextFieldController eventoTecla = new KeyEventJFXTextFieldController();
        eventoTecla.soloNumeros(txtCodigo);

        ObservableList<String> listaTipoBusqueda = FXCollections.observableArrayList("Todos", "Libro", "Enciclopedia", "Diccionario",
                "Revista", "Periodico", "Mapa");
        cboTipoBusqueda.setItems(listaTipoBusqueda);
        cboTipoBusqueda.setValue("Todos");

        alert = new AlertBox();
    }

    /**
     * el metódo valida los datos ingresados por el usuario y luego llama al
     * metódo que se encarga de la busqueda del recusro..
     *
     * @param event
     */
    @FXML
    private void BtnBuscarCodigo(ActionEvent event) {
        String codigo = txtCodigo.getText().trim();
        String entidad = cboTipoBusqueda.getSelectionModel().getSelectedItem().toLowerCase();
        BusquedaCodigoAbs busquedaCodigoAbs = null;

        if (!codigo.isEmpty()) {
            if ((codigo.length() > 0) && (codigo.length() <= 6)) {//codbarras
                busquedaCodigoAbs = new BusquedaCodigoBarras();
            } else if ((codigo.length() > 6) && (codigo.length() <= 17)) {//issn-isbn
                busquedaCodigoAbs = new Busqueda_ISBN_ISSN();
            } else {
                alert.showAlert("Anuncio", "Tamaño del código", "Por favor ingrese "
                        + "un tamaño de código válido: " + codigo
                        + ".\n\n Recurde que para: "
                        + "\n• Código de barras mayor a 0 y menor 6."
                        + "\n• Isbn o issn : mayor a 6 y menor a 17.");
            }

            if (busquedaCodigoAbs != null) {
                if (entidad.equals("todos")) {
                    entidad = busquedaCodigoAbs.buscarEntidad(codigo);
                }

                buscarRecurso(busquedaCodigoAbs, entidad, codigo);
            }
        } else {
            alert.showAlert("Anuncio", "Campo vacío", "Por favor ingrese "
                    + "el código de barras, el isbn o el issn del recurso a buscar.");
        }

    }

    /**
     * el metódo busca por medio de un código la información de un recurso.
     *
     * @param busquedaCodigoAbs
     * @param entidad
     * @param codigo
     */
    private void buscarRecurso(BusquedaCodigoAbs busquedaCodigoAbs, String entidad, String codigo) {
        ContextoBusquedaPorCodigo contextoBusquedaPorCodigo = new ContextoBusquedaPorCodigo();
        Object recurso = null;
        IBusquedaAutorRecurso iBusquedaAutorRecurso = null;
        IBusquedaMateriaRecurso iBusquedaMateriaRecurso = null;

        contextoBusquedaPorCodigo.setBusquedaPorCodigo(busquedaCodigoAbs);

        switch (entidad) {
            case "libro":
                recurso = contextoBusquedaPorCodigo.buscarLibroPorCodigo(codigo);

                if (recurso instanceof Libro) {
                    codigo = ((Libro) recurso).getCodbarralibro();

                    iBusquedaAutorRecurso = new BusquedaAutorLibro();
                    iBusquedaAutorRecurso.buscarAutorRecurso(codigo);

                    iBusquedaMateriaRecurso = new BusquedaMateriaLibro();
                    iBusquedaMateriaRecurso.buscarMateriaRecurso(codigo);

                    gridPaneFicha.setPadding(new Insets(0, 120, 0, 120));
                } else {
                    recurso = null;
                }
                break;
            case "enciclopedia":
                recurso = contextoBusquedaPorCodigo.buscarEnciclopediaPorCodigo(codigo);

                if (recurso instanceof Enciclopedia) {
                    codigo = ((Enciclopedia) recurso).getCodbarraenciclopedia();

                    iBusquedaAutorRecurso = new BusquedaAutorEnciclopedia();
                    iBusquedaAutorRecurso.buscarAutorRecurso(codigo);

                    iBusquedaMateriaRecurso = new BusquedaMateriaEnciclopedia();
                    iBusquedaMateriaRecurso.buscarMateriaRecurso(codigo);

                    gridPaneFicha.setPadding(new Insets(0, 100, 0, 100));
                } else {
                    recurso = null;
                }
                break;
            case "diccionario":
                recurso = contextoBusquedaPorCodigo.buscarDiccionarioPorCodigo(codigo);

                if (recurso instanceof Diccionario) {
                    codigo = ((Diccionario) recurso).getCodbarradiccionario();

                    iBusquedaAutorRecurso = new BusquedaAutorDiccionario();
                    iBusquedaAutorRecurso.buscarAutorRecurso(codigo);

                    iBusquedaMateriaRecurso = new BusquedaMateriaDiccionario();
                    iBusquedaMateriaRecurso.buscarMateriaRecurso(codigo);

                    gridPaneFicha.setPadding(new Insets(0, 110, 0, 110));
                } else {
                    recurso = null;
                }
                break;
            case "revista":
                recurso = contextoBusquedaPorCodigo.buscarRevistaPorCodigo(codigo);

                if (recurso instanceof Revista) {
                    codigo = ((Revista) recurso).getCodbarrarevista();

                    iBusquedaMateriaRecurso = new BusquedaMateriaRevista();
                    iBusquedaMateriaRecurso.buscarMateriaRecurso(codigo);

                    gridPaneFicha.setPadding(new Insets(60, 300, 60, 300));
                } else {
                    recurso = null;
                }
                break;
            case "periodico":
                recurso = contextoBusquedaPorCodigo.buscarPeriodicoPorCodigo(codigo);

                if (recurso instanceof Periodico) {
                    gridPaneFicha.setPadding(new Insets(100, 300, 100, 300));
                } else {
                    recurso = null;
                }
                break;
            case "mapa":
                recurso = contextoBusquedaPorCodigo.buscarMapaPorCodigo(codigo);

                if (recurso instanceof Periodico) {
                    gridPaneFicha.setPadding(new Insets(100, 300, 100, 300));
                } else {
                    recurso = null;
                }
                break;
            default:
                //No hace nada, pero es cuando un codigo no pertenece a una entidady el metodo retorna "";
                break;
        }

        if (recurso != null) {
            FichaTecnicaPanel fichaTecticaPanel = new FichaTecnicaPanel();

            limpiarCampos();

            if (iBusquedaAutorRecurso == null) {
                if (iBusquedaMateriaRecurso == null) {
                    gridPaneFicha.add(fichaTecticaPanel.cargarFichaTecnica(recurso, "", ""), 0, 1);
                } else {
                    gridPaneFicha.add(fichaTecticaPanel.cargarFichaTecnica(recurso, "", iBusquedaMateriaRecurso.getCadenaMaterias()), 0, 1);
                }
            } else {
                gridPaneFicha.add(fichaTecticaPanel.cargarFichaTecnica(recurso, iBusquedaAutorRecurso.getCadenaAutores(), iBusquedaMateriaRecurso.getCadenaMaterias()), 0, 1);
            }
        } else {
            if (!gridPaneFicha.getChildren().isEmpty()) {
                gridPaneFicha.getChildren().clear();
            }

            alert.showAlert("Anuncio", "Busqueda recurso", "No se encontró un recurso asociado al código: " + codigo);
        }
    }

    /**
     * el metódo limpia el campo txtCodigo y pone el comobox por defecto en
     * "todos".
     */
    private void limpiarCampos() {
        txtCodigo.setText("");
        cboTipoBusqueda.setValue("Todos");
        gridPaneFicha.getChildren().clear();
    }

    /**
     * el metódo carga el menu de la diferentes consultas con las que cuenta el
     * módulo de OPAC.
     */
    public void cargarMenuOPAC(BorderPane rootModulo) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/moduloOPAC/vista/MenuOPAC.fxml"));
            gridPaneMenu.add(loader.load(), 0, 0);
            
            MenuOPACController menuOPACController = loader.getController();
            menuOPACController.setBorderPane(rootModulo);
            menuOPACController.setDisableButton("codigo");
            
        } catch (IOException ex) {

        }
    }

}

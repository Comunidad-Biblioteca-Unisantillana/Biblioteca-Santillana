package moduloOPAC.control;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
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
import moduloOPAC.modelo.BusquedaAutor;
import moduloOPAC.modelo.BusquedaAvanzadaAbs;
import moduloOPAC.modelo.ContextoBusquedaAvanzada;
import moduloOPAC.modelo.Recurso;
import general.vista.AlertBox;
import general.vista.IAlertBox;

/**
 * Clase que controla la vista AutorOPAC.fxml
 *
 * @author Miguel Fernández
 * @creado 20/08/2019
 * @author Miguel Fernández
 * @modificado 21/08/2019
 */
public class AutorOPACController implements Initializable {

    @FXML
    private JFXTextField txtNombreAutor;
    @FXML
    private JFXComboBox<String> cboTipoBusqueda;
    @FXML
    private GridPane gridPaneTabla;
    private IAlertBox alert;
    private TablaOPACController tablaOPACController;
    @FXML
    private GridPane gridPaneMenu;

    /**
     * el método que se ejecuta automáticamente al enlazar este controlador con
     * su respectiva vista.
     *
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<String> listaTipoBusqueda = FXCollections.observableArrayList("Todos", "Libro", "Enciclopedia", "Diccionario");
        cboTipoBusqueda.setItems(listaTipoBusqueda);
        cboTipoBusqueda.setValue("Todos");

        alert = new AlertBox();

        cargarTabla();
    }

    /**
     * el metódo busca en los diferentes recursos o en uno especificoel autor
     * ingresado.
     *
     * @param event
     */
    @FXML
    private void btnBuscarAutor(ActionEvent event) {
        String nombreAutor = txtNombreAutor.getText().trim();
        String entidad = cboTipoBusqueda.getSelectionModel().getSelectedItem().toLowerCase();

        if (!nombreAutor.isEmpty()) {
            ContextoBusquedaAvanzada contextoBusquedaAvanzada = new ContextoBusquedaAvanzada();
            BusquedaAvanzadaAbs busquedaAvanzadaAbs = new BusquedaAutor(nombreAutor);

            contextoBusquedaAvanzada.setBusquedaAvanzada(busquedaAvanzadaAbs);
            ObservableList<Recurso> recursos = contextoBusquedaAvanzada.buscarRecursos(entidad);
            tablaOPACController.cargarDatosTabla(nombreAutor, recursos);

            limpiarCampos();
        } else {
            alert.showAlert("Anuncio", "Campo vacio", "Por favor ingrese el nombre del autor buscar.");
        }
    }

    /**
     * el metódo carga la tabla donde se visualizaran los resultados de la
     * busqueda.
     */
    private void cargarTabla() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/moduloOPAC/vista/TablaOPAC.fxml"));
            gridPaneTabla.add(loader.load(), 0, 1);
            gridPaneTabla.setPadding(new Insets(0, 0, 0, 160));
            tablaOPACController = loader.getController();
        } catch (IOException ex) {

        }
    }

    /**
     * el metódo limpia el campo de txtNombreAutor y pone el comobox por defecto
     * en "todos".
     */
    private void limpiarCampos() {
        txtNombreAutor.setText("");
        cboTipoBusqueda.setValue("Todos");
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
            menuOPACController.setDisableButton("autor");

        } catch (IOException ex) {
            System.out.println("Error al cargar la interfaz MenuOPAC.fxml");
        }
    }

}

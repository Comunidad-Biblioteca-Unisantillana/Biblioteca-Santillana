package moduloPrestamo.control;

import com.jfoenix.controls.JFXRadioButton;
import java.net.URL;
import moduloPrestamo.modelo.Prestamo;
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import moduloPrestamo.modelo.ConsultaPrestamoEst;
import moduloPrestamo.modelo.ConsultaPrestamoProf;
import moduloPrestamo.modelo.IConsultarPrestamo;
import moduloRenovacion.vista.MensajeRenovarStage;
import vista.AlertBox;
import vista.IAlertBox;

/**
 * Clase que controla el panel préstamo del estudiante o profesor.
 *
 * @author Julian
 * @creado
 * @author Miguel Fernández
 * @modificado 24/08/2019
 */
public class PrestamoEstudianteProfesorController implements Initializable {

    @FXML
    private TableView<Prestamo> tablePrestamo;
    @FXML
    private TableColumn<Prestamo, String> codBrrasTable;
    @FXML
    private TableColumn<Prestamo, String> tituloTable;
    @FXML
    private TableColumn<Prestamo, Date> fechaPrestamoTable;
    @FXML
    private TableColumn<Prestamo, Date> fechaDevolucionTable;
    @FXML
    private JFXRadioButton radioPrestActual;
    @FXML
    private JFXRadioButton radioHistorialPrest;
    @FXML
    private Label txtMensaje;
    @FXML
    private Button btnRenovarRecurso;
    private ToggleGroup togleRadio;
    private String codUsuario;
    private String tipoUsuario;
    private IConsultarPrestamo consulta;
    private IAlertBox alert;
    private boolean estadoRenovarRecurso;

    /**
     * el método que se ejecuta automáticamente al enlazar este controlador con
     * su respectiva vista.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        togleRadio = new ToggleGroup();

        radioHistorialPrest.setToggleGroup(togleRadio);
        radioHistorialPrest.setSelectedColor(Color.CORNFLOWERBLUE);

        radioPrestActual.setToggleGroup(togleRadio);
        radioPrestActual.setSelected(true);
        radioPrestActual.setSelectedColor(Color.CORNFLOWERBLUE);

        codBrrasTable.setCellValueFactory(new PropertyValueFactory<>("codBarrasRecurso"));
        tituloTable.setCellValueFactory(new PropertyValueFactory<>("tituloRecurso"));
        fechaPrestamoTable.setCellValueFactory(new PropertyValueFactory<>("fechaPrestamo"));
        fechaDevolucionTable.setCellValueFactory(new PropertyValueFactory<>("fechaDevolucion"));
        tablePrestamo.getSelectionModel().clearSelection();

        alert = new AlertBox();
        estadoRenovarRecurso = false;
    }

    /**
     * el metódo muestra los préstamos actuales e histrial de préstamos de un
     * usuario.
     *
     * @param event
     */
    @FXML
    private void btnHistorialPrestamos(ActionEvent event) {
        ObservableList<Prestamo> prestamos = null;

        if (event.getSource().equals(radioHistorialPrest)) {
            btnRenovarRecurso.setDisable(true);
            prestamos = consulta.getHistorialPrestamos(codUsuario);
        } else if (event.getSource().equals(radioPrestActual)) {
            prestamos = consulta.getPrestamosActuales(codUsuario);
            btnRenovarRecurso.setDisable(false);
        }

        tablePrestamo.setItems(prestamos);
        txtMensaje.setText("Se encontrarón: " + prestamos.size() + " préstamos.");
    }

    /**
     * el metódo realiza la renovación de un recurso seleccionado por el
     * usuario.
     *
     * @param event
     */
    @FXML
    void btnRevovarRecursoPress(ActionEvent event) {
        int filaSeleccionada = tablePrestamo.getSelectionModel().getFocusedIndex();

        if (filaSeleccionada > -1) {
            if (estadoRenovarRecurso == false) {
                Prestamo prestamo = tablePrestamo.getSelectionModel().getSelectedItem();
                estadoRenovarRecurso = true;

                MensajeRenovarStage mensajeRenovarStage = new MensajeRenovarStage();
                mensajeRenovarStage.cargarDatosMensajeRenovarController(prestamo, codUsuario, tipoUsuario, this);
            } else {
                alert.showAlert("Anuncio", "Renovar recurso", "Por favor cerar la ventana actual "
                        + "de renovación abierta, para poder renovar otro recurso.");
            }

            tablePrestamo.getSelectionModel().clearSelection();

        } else {
            alert.showAlert("Anuncio", "Renovar recurso", "Por favor, seleccionar un recurso "
                    + "de la lista de préstamos actuales para poder realizar la renovación.");
        }
    }

    /**
     * el metódo carga el código del usuario y la clase de las consultas según
     * el usuario.
     *
     * @param codUsuario
     * @param tipoUsuario
     */
    public void cargarDatosUsuario(String codUsuario, String tipoUsuario) {
        this.codUsuario = codUsuario;
        this.tipoUsuario = tipoUsuario;

        if (tipoUsuario.equalsIgnoreCase("profesor")) {
            consulta = new ConsultaPrestamoProf();
        } else {
            consulta = new ConsultaPrestamoEst();
        }

        ObservableList<Prestamo> prestamos = consulta.getPrestamosActuales(codUsuario);
        tablePrestamo.setItems(prestamos);
        txtMensaje.setText("Se encontrarón: " + prestamos.size() + " préstamos.");
    }

    /**
     * el metódo modifica el estado en que se encuentra la ventana para renovar
     * el recurso, para poder renovar otro.
     *
     * @param estadoVentanaFicha
     */
    public void setEstadoRenovarRecurso(boolean estadoRenovarRecurso) {
        this.estadoRenovarRecurso = estadoRenovarRecurso;
    }

}

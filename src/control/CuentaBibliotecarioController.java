package control;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import moduloOPAC.control.CodigoOPACController;
import vista.CuentaBibliotecarioStage;
import vista.IniciarMenuDesplegable;
import vista.LoginUnisantillanaStage;

/**
 * Clase que controla la vista CuentaBibliotecario.fxml
 *
 * @author stiven valencia
 * @creado: 05/09/2018
 * @Miguel Fernández
 * @modificado: 21/08/2019
 */
public class CuentaBibliotecarioController implements Initializable {

    private CuentaBibliotecarioStage stage;
    @FXML
    private BorderPane rootModulo;
    @FXML
    private JFXDrawer drawer;
    @FXML
    private JFXHamburger hamburger;
    @FXML
    private AnchorPane anchorDrawer;
    @FXML
    private ImageView imgIconPrestamo;
    @FXML
    private ImageView imgIconReserva;
    @FXML
    private ImageView imgIconDevolucion;
    @FXML
    private ImageView imgIconMulta;
    @FXML
    private JFXButton btnOPAC;
    @FXML
    private JFXButton btnPrestamo;
    @FXML
    private JFXButton btnReserva;
    @FXML
    private JFXButton btnDevolucion;
    @FXML
    private JFXButton btnMulta;
    @FXML
    private ImageView imgIconOPAC;
    private String idBibliotecario;
    private IniciarMenuDesplegable imd;

    /**
     * Método que se ejecuta automáticamente al enlazar<br>
     * este controlador con su respectiva vista
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        imd = new IniciarMenuDesplegable(drawer, anchorDrawer, hamburger);
        imgIconPrestamo.setImage(new Image("/recursos/iconPrestamo.png"));
        imgIconReserva.setImage(new Image("/recursos/iconReserva.png"));
        imgIconDevolucion.setImage(new Image("/recursos/iconDevolucion.png"));
        imgIconMulta.setImage(new Image("/recursos/iconMulta.png"));
        imgIconOPAC.setImage(new Image("/recursos/iconOPAC.png"));
        loadOPAC();
        valoresPorDefecto("OPAC");
    }

    /**
     * Método que cambia un módulo por el de préstamo
     *
     * @param event
     */
    @FXML
    private void btnPrestamoPressed(ActionEvent event) {
        loadPrestamo();
        valoresPorDefecto("prestamo");
    }

    /**
     * Método que cambia un módulo por el de reserva
     *
     * @param event
     */
    @FXML
    private void btnReservaPressed(ActionEvent event) {
        loadReserva();
        valoresPorDefecto("reserva");
    }

    /**
     * Método que cambia un módulo por el de devolución
     *
     * @param event
     */
    @FXML
    private void btnDevolucionPressed(ActionEvent event) {
        loadDevolucion();
        valoresPorDefecto("devolucion");
    }

    /**
     * Método que cambia un módulo por el de multa
     *
     * @param event
     */
    @FXML
    private void btnMultaPressed(ActionEvent event) {
        loadMulta();
        valoresPorDefecto("multa");
    }

    /**
     * el metódo cambia el módulo por el de OPAC.
     *
     * @param event
     */
    @FXML
    private void btnOPACPressed(ActionEvent event) {
        loadOPAC();
        valoresPorDefecto("OPAC");
    }

    /**
     * el metódo repliega la barra del menu e inhabilta el botón del módulo
     * selecionado.
     */
    private void valoresPorDefecto(String modulo) {
        if (modulo.equals("OPAC")) {
            btnOPAC.setDisable(true);
            btnPrestamo.setDisable(false);
            btnReserva.setDisable(false);
            btnDevolucion.setDisable(false);
            btnMulta.setDisable(false);
        } else if (modulo.equals("prestamo")) {
            btnOPAC.setDisable(false);
            btnPrestamo.setDisable(true);
            btnReserva.setDisable(false);
            btnDevolucion.setDisable(false);
            btnMulta.setDisable(false);
        } else if (modulo.equals("reserva")) {
            btnOPAC.setDisable(false);
            btnPrestamo.setDisable(false);
            btnReserva.setDisable(true);
            btnDevolucion.setDisable(false);
            btnMulta.setDisable(false);
        } else if (modulo.equals("devolucion")) {
            btnOPAC.setDisable(false);
            btnPrestamo.setDisable(false);
            btnReserva.setDisable(false);
            btnDevolucion.setDisable(true);
            btnMulta.setDisable(false);
        } else {
            btnOPAC.setDisable(false);
            btnPrestamo.setDisable(false);
            btnReserva.setDisable(false);
            btnDevolucion.setDisable(false);
            btnMulta.setDisable(true);
        }

        imd.valorPorDefecto();
    }

    /**
     * Método que retorna a la ventana login
     *
     * @param event
     */
    @FXML
    private void itemSalirPressed(ActionEvent event) {
        CuentaBibliotecarioStage.deleteInstance();
        LoginUnisantillanaStage.getInstance().show();
    }

    /**
     * Método que muestra la información del programa
     *
     * @param event
     */
    @FXML
    private void itemAcercaDe(ActionEvent event) {

    }

    /**
     * Metodo que carga el modulo prestamo del bibliotecario
     */
    private void loadPrestamo() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/PrestamoBibliotecario.fxml"));
            rootModulo.setCenter(loader.load());
            PrestamoBibliotecarioController control = loader.getController();
            control.setIdBibliotecario(idBibliotecario);
        } catch (IOException ex) {

        }
    }

    /**
     * Metodo que carga el modulo reserva del bibliotecario
     */
    private void loadReserva() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/ReservaBibliotecario.fxml"));
            rootModulo.setCenter(loader.load());
            ReservaBibliotecarioController control = loader.getController();
            control.setIdBibliotecario(idBibliotecario);
        } catch (IOException ex) {

        }
    }

    /**
     * Metodo que carga el modulo devolución del bibliotecario
     */
    private void loadDevolucion() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/DevolucionBibliotecario.fxml"));
            rootModulo.setCenter(loader.load());
            DevolucionBibliotecarioController control = loader.getController();
            control.setIdBibliotecario(idBibliotecario);
        } catch (IOException ex) {

        }
    }

    /**
     * Metodo que carga el modulo multa del bibliotecario
     */
    private void loadMulta() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/MultaBibliotecario.fxml"));
            rootModulo.setCenter(loader.load());
            MultaBibliotecarioController control = loader.getController();
        } catch (IOException ex) {

        }
    }

    /**
     * el metódo que carga el modulo OPAC del Estudiante o Profesor, por defecto
     * en el tipo de busqueda a: busqueda por codigo.
     */
    private void loadOPAC() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/moduloOPAC/vista/CodigoOPAC.fxml"));
            rootModulo.setCenter(loader.load());

            CodigoOPACController codigoOPACController = loader.getController();
            codigoOPACController.cargarMenuOPAC(rootModulo);
        } catch (IOException ex) {

        }
    }

    /**
     * Metodo que muestra el nombre y la identificación del bibliotecario en la
     * ventana
     */
    public void loadDatosBasicosBibliotecario() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/DatosBasicosUsuario.fxml"));
            Parent parent = loader.load();
            rootModulo.setTop(parent);
            DatosBasicosUsuarioController control = loader.getController();
            control.cargarComponentes("bibliotecario", idBibliotecario);
        } catch (IOException ex) {

        }
    }

    /**
     * Método que asigna la identificación del bibliotecario.
     *
     * @param idBibliotecario
     */
    public void setIdBibliotecario(String idBibliotecario) {
        this.idBibliotecario = idBibliotecario;
    }

    /**
     * Metodo que asigna stage de bibliotecario
     *
     * @param stage
     */
    public void setStage(CuentaBibliotecarioStage stage) {
        this.stage = stage;
    }
}

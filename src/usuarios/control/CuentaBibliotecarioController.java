package usuarios.control;

import moduloMulta.control.MultaBibliotecarioController;
import moduloDevolucion.control.DevolucionBibliotecarioController;
import moduloReserva.control.ReservaBibliotecarioController;
import moduloPrestamo.control.PrestamoBibliotecarioController;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import general.vista.InformacionEmpresaStage;
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
import usuarios.vista.CuentaBibliotecarioStage;
import general.vista.IniciarMenuDesplegable;
import moduloLogin.vista.LoginUnisantillanaStage;

/**
 * Clase que controla la vista CuentaBibliotecario.fxml
 *
 * @author stiven valencia
 * @creado: 05/09/2018
 * @author Miguel Fernández
 * @modificado: 09/09/2019
 */
public class CuentaBibliotecarioController implements Initializable {

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
    private ImageView imgIconRenovacion;
    @FXML
    private ImageView imgIconConsultarUsuario;
    @FXML
    private JFXButton btnConsultarUsuario;
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
    private JFXButton btnRenovacion;
    @FXML
    private ImageView imgIconOPAC;
    private String idBibliotecario;
    private IniciarMenuDesplegable imd;

    /**
     * el método ejecuta automáticamente al enlazar<br>
     * este controlador con su respectiva vista
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        imd = new IniciarMenuDesplegable(drawer, anchorDrawer, hamburger);
        imgIconConsultarUsuario.setImage(new Image("/general/recursos/img/iconBuscarUsuario.png"));
        imgIconPrestamo.setImage(new Image("/general/recursos/img/iconPrestamoRecurso.png"));
        imgIconReserva.setImage(new Image("/general/recursos/img/iconReserva.png"));
        imgIconDevolucion.setImage(new Image("/general/recursos/img/iconDevolucion.png"));
        imgIconMulta.setImage(new Image("/general/recursos/img/iconMulta.png"));
        imgIconOPAC.setImage(new Image("/general/recursos/img/iconOPAC.png"));
        imgIconRenovacion.setImage(new Image("/general/recursos/img/iconRenovacion.png"));
        loadConsultaUusuario();
        valoresPorDefecto("consultaUsuario");
    }

    /**
     * el método cambia un módulo por el de consulta usuario.
     *
     * @param event
     */
    @FXML
    private void btnConsultarUsuarioPressed(ActionEvent event) {
        loadConsultaUusuario();
        valoresPorDefecto("consultaUsuario");
    }

    /**
     * el método cambia un módulo por el de préstamo.
     *
     * @param event
     */
    @FXML
    private void btnPrestamoPressed(ActionEvent event) {
        loadPrestamo();
        valoresPorDefecto("prestamo");
    }

    /**
     * el método cambia un módulo por el de reserva.
     *
     * @param event
     */
    @FXML
    private void btnReservaPressed(ActionEvent event) {
        loadReserva();
        valoresPorDefecto("reserva");
    }

    /**
     * el método cambia un módulo por el de devolución.
     *
     * @param event
     */
    @FXML
    private void btnDevolucionPressed(ActionEvent event) {
        loadDevolucion();
        valoresPorDefecto("devolucion");
    }

    /**
     * el método cambia un módulo por el de multa.
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
     * el metódo cambia el módulo por el de Renovación.
     *
     * @param event
     */
    @FXML
    void btnRenovacionPressed(ActionEvent event) {
        loadRenovacion();
        valoresPorDefecto("renovacion");
    }

    /**
     * el metódo repliega la barra del menu e inhabilta el botón del módulo
     * selecionado.
     */
    private void valoresPorDefecto(String modulo) {
        if (modulo.equals("consultaUsuario")) {
            btnConsultarUsuario.setDisable(true);
            btnOPAC.setDisable(false);
            btnPrestamo.setDisable(false);
            btnReserva.setDisable(false);
            btnDevolucion.setDisable(false);
            btnMulta.setDisable(false);
            btnRenovacion.setDisable(false);
        } else if (modulo.equals("OPAC")) {
            btnConsultarUsuario.setDisable(false);
            btnOPAC.setDisable(true);
            btnPrestamo.setDisable(false);
            btnReserva.setDisable(false);
            btnDevolucion.setDisable(false);
            btnMulta.setDisable(false);
            btnRenovacion.setDisable(false);
        } else if (modulo.equals("prestamo")) {
            btnConsultarUsuario.setDisable(false);
            btnOPAC.setDisable(false);
            btnPrestamo.setDisable(true);
            btnReserva.setDisable(false);
            btnDevolucion.setDisable(false);
            btnMulta.setDisable(false);
            btnRenovacion.setDisable(false);
        } else if (modulo.equals("reserva")) {
            btnConsultarUsuario.setDisable(false);
            btnOPAC.setDisable(false);
            btnPrestamo.setDisable(false);
            btnReserva.setDisable(true);
            btnDevolucion.setDisable(false);
            btnMulta.setDisable(false);
            btnRenovacion.setDisable(false);
        } else if (modulo.equals("devolucion")) {
            btnConsultarUsuario.setDisable(false);
            btnOPAC.setDisable(false);
            btnPrestamo.setDisable(false);
            btnReserva.setDisable(false);
            btnDevolucion.setDisable(true);
            btnMulta.setDisable(false);
            btnRenovacion.setDisable(false);
        } else if (modulo.equals("multa")) {
            btnConsultarUsuario.setDisable(false);
            btnOPAC.setDisable(false);
            btnPrestamo.setDisable(false);
            btnReserva.setDisable(false);
            btnDevolucion.setDisable(false);
            btnMulta.setDisable(true);
            btnRenovacion.setDisable(false);
        } else {
            btnConsultarUsuario.setDisable(false);
            btnOPAC.setDisable(false);
            btnPrestamo.setDisable(false);
            btnReserva.setDisable(false);
            btnDevolucion.setDisable(false);
            btnMulta.setDisable(false);
            btnRenovacion.setDisable(true);
        }

        imd.valorPorDefecto();
    }

    /**
     * el método retorna a la ventana login.
     *
     * @param event
     */
    @FXML
    private void itemSalirPressed(ActionEvent event) {
        CuentaBibliotecarioStage.deleteInstance();
        LoginUnisantillanaStage.getInstance().show();
    }

    /**
     * el método muestra la información del programa
     *
     * @param event
     */
    @FXML
    private void itemAcercaDe(ActionEvent event) {
        new InformacionEmpresaStage();
    }

    /**
     * el metódo carga el módulo de consultar datos usuario del bibliotecario.
     */
    private void loadConsultaUusuario() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/moduloLogin/vista/ConsultarUsuario.fxml"));
            rootModulo.setCenter(loader.load());
        } catch (IOException ex) {
            System.out.println("Error al cargar la vista PrestamoBibliotecario.fxml");
        }
    }
    
    /**
     * el metódo carga el módulo préstamo del bibliotecario.
     */
    private void loadPrestamo() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/moduloPrestamo/vista/PrestamoBibliotecario.fxml"));
            rootModulo.setCenter(loader.load());
            PrestamoBibliotecarioController control = loader.getController();
            control.setIdBibliotecario(idBibliotecario);
        } catch (IOException ex) {
            System.out.println("Error al cargar la vista PrestamoBibliotecario.fxml");
        }
    }

    /**
     * el metódo carga el módulo reserva del bibliotecario.
     */
    private void loadReserva() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/moduloReserva/vista/ReservaBibliotecario.fxml"));
            rootModulo.setCenter(loader.load());
            ReservaBibliotecarioController control = loader.getController();
            control.setIdBibliotecario(idBibliotecario);
        } catch (IOException ex) {
            System.out.println("Error al cargar la vista ReservaBibliotecario.fxml");
        }
    }

    /**
     * el metódo carga el módulo devolución del bibliotecario.
     */
    private void loadDevolucion() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/moduloDevolucion/vista/DevolucionBibliotecario.fxml"));
            rootModulo.setCenter(loader.load());
            DevolucionBibliotecarioController control = loader.getController();
            control.setIdBibliotecario(idBibliotecario);
        } catch (IOException ex) {
            System.out.println("Error al cargar la vista DevolucionBibliotecario.fxml");
        }
    }

    /**
     * el metódo carga el módulo multa del bibliotecario.
     */
    private void loadMulta() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/moduloMulta/vista/MultaBibliotecario.fxml"));
            rootModulo.setCenter(loader.load());
            MultaBibliotecarioController control = loader.getController();
        } catch (IOException ex) {
            System.out.println("Error al cargar la vista MultaBibliotecario.fxml");
        }
    }

    /**
     * el metódo carga el módulo OPAC del Estudiante o Profesor, por defecto en
     * el tipo de busqueda a: busqueda por codigo.
     */
    private void loadOPAC() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/moduloOPAC/vista/CodigoOPAC.fxml"));
            rootModulo.setCenter(loader.load());

            CodigoOPACController codigoOPACController = loader.getController();
            codigoOPACController.cargarMenuOPAC(rootModulo);
        } catch (IOException ex) {
            System.out.println("Error al cargar la vista CodigoOPAC.fxml");
        }
    }

    /**
     * el metódo carga el módulo de renovación del bibliotecario.
     */
    private void loadRenovacion() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/moduloRenovacion/vista/RenovacionBibliotecario.fxml"));
            rootModulo.setCenter(loader.load());
        } catch (IOException ex) {
            System.out.println("Error al cargar la vista RenovacionBibliotecario.fxml");
        }
    }

    /**
     * el metódo muestra el nombre y la identificación del bibliotecario en la
     * ventana.
     */
    public void loadDatosBasicosBibliotecario() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/usuarios/vista/DatosBasicosUsuario.fxml"));
            Parent parent = loader.load();
            rootModulo.setTop(parent);
            DatosBasicosUsuarioController control = loader.getController();
            control.cargarComponentes("bibliotecario", idBibliotecario);
        } catch (IOException ex) {
            System.out.println("Error al cargar la vista DatosBasicosUsuario.fxml");
        }
    }

    /**
     * el método asigna la identificación del bibliotecario.
     *
     * @param idBibliotecario
     */
    public void setIdBibliotecario(String idBibliotecario) {
        this.idBibliotecario = idBibliotecario;
    }

}

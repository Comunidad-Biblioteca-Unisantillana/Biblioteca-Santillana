package control;

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
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import vista.CuentaEstudianteStage;
import vista.CuentaProfesorStage;
import vista.IniciarMenuDesplegable;
import vista.LoginUnisantillanaStage;

/**
 * Clase que controla la vista CuentaEstudianteProfesor.fxml
 *
 * @author stive Fecha de Creación: 05/09/2018 Fecha de ultima Modificación:
 * 04/08/2019
 */
public class CuentaEstudianteProfesorController implements Initializable {

    @FXML
    private BorderPane rootModulo;
    @FXML
    private JFXHamburger hamburger;
    @FXML
    private JFXDrawer drawer;
    @FXML
    private AnchorPane anchorDrawer;
    @FXML
    private ImageView imgIconPrestamo;
    @FXML
    private ImageView imgIconMulta;
    @FXML
    private ImageView imgIconCodigoOPAC;
    @FXML
    private ImageView imgIconAutorOPAC;
    @FXML
    private ImageView imgIconPalabraClaveOPAC;
    @FXML
    private ImageView imgIconTituloYAutorOPAC;
    @FXML
    private JFXDrawer subDrawer;
    @FXML
    private AnchorPane subAnchorDrawer;
    @FXML
    private JFXHamburger hamburger1;
    private String idBibliotecario;
    private IniciarMenuDesplegable imd;
    private IniciarMenuDesplegable imd1;
    private Stage stage;
    private String codigo;
    private String tipoUsuario;

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
        imd1 = new IniciarMenuDesplegable(subDrawer, subAnchorDrawer, hamburger1);
        imgIconPrestamo.setImage(new Image("/recursos/iconPrestamo.png"));
        imgIconMulta.setImage(new Image("/recursos/iconMulta.png"));
        imgIconCodigoOPAC.setImage(new Image("/recursos/iconOPAC.png"));
        imgIconAutorOPAC.setImage(new Image("/recursos/iconOPAC.png"));
        imgIconPalabraClaveOPAC.setImage(new Image("/recursos/iconOPAC.png"));
        imgIconTituloYAutorOPAC.setImage(new Image("/recursos/iconOPAC.png"));
        loadOPAC("Codigo");
    }

    /**
     * Método que retona a la ventana del login
     *
     * @param event
     */
    @FXML
    private void itemSalirPressed(ActionEvent event) {
        LoginUnisantillanaStage.getInstance().show();
        if (tipoUsuario.equalsIgnoreCase("estudiante")) {
            CuentaEstudianteStage.deleteInstance();
        } else if (tipoUsuario.equalsIgnoreCase("profesor")) {
            CuentaProfesorStage.deleteInstance();
        }
    }

    @FXML
    private void itemAcercaDePressed(ActionEvent event) {

    }

    /**
     * Método que cambia un módulo por el de préstamo
     *
     * @param event
     */
    @FXML
    private void btnPrestamoPressed(ActionEvent event) {
        loadPrestamo();
        valoresPorDefecto();
    }

    /**
     * Método que cambia un módulo por el de multa
     *
     * @param event
     */
    @FXML
    private void btnMultaPressed(ActionEvent event) {
        loadMulta();
        valoresPorDefecto();
    }

    /**
     * el metódo cambia el tipo de busqueda a: busqueda por codigo.
     *
     * @param event
     */
    @FXML
    private void btnCodigoOPACPressed(ActionEvent event) {
        loadOPAC("Codigo");
        valoresPorDefecto();
    }

    /**
     * el metódo cambia el tipo de busqueda a: busqueda por autor.
     *
     * @param event
     */
    @FXML
    private void btnAutorOPACPressed(ActionEvent event) {
        loadOPAC("Autor");
        valoresPorDefecto();
    }

    /**
     * el metódo cambia el tipo de busqueda a: busqueda por palabra clave.
     *
     * @param event
     */
    @FXML
    private void btnPalabraClaveOPACPressed(ActionEvent event) {
        loadOPAC("PalabraClave");
        valoresPorDefecto();
    }

    /**
     * el metódo cambia el tipo de busqueda a: busqueda por titulo y autor.
     *
     * @param event
     */
    @FXML
    private void btnTituloYAutorOPACPressed(ActionEvent event) {
        loadOPAC("TituloYAutor");
        valoresPorDefecto();
    }

    /**
     * el metódo repliega la barra del menu.
     */
    private void valoresPorDefecto() {
        imd.valorPorDefecto();
        imd1.valorPorDefecto();
    }

    /**
     * Método que carga el modulo préstamo del<br>
     * usuario(Estudiante,Profesor).
     */
    private void loadPrestamo() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/PrestamoEstudianteProfesor.fxml"));
            GridPane parent = loader.load();
            rootModulo.setCenter(parent);
            PrestamoEstudianteProfesorController control = loader.getController();
            control.cargarDatosUsuario(codigo, tipoUsuario);
        } catch (IOException ex) {

        }
    }

    /**
     * Método que carga el modulo multa del<br>
     * usuario(Estudiante,Profesor).
     */
    private void loadMulta() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/MultaEstudianteProfesor.fxml"));
            GridPane parent = loader.load();
            rootModulo.setCenter(parent);
            MultaEstudianteProfesorController control = loader.getController();
            control.cargarDatosTableMultas(codigo, tipoUsuario);
        } catch (IOException ex) {

        }
    }

    /**
     * el metódo que carga el modulo OPAC del Estudiante o Profesor.
     */
    private void loadOPAC(String busqueda) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/moduloOPAC/vista/" + busqueda + "OPAC.fxml"));
            rootModulo.setCenter(loader.load());
        } catch (IOException ex) {

        }
    }

    /**
     * Metodo que muestra el nombre y el codigo del usuario en la ventana
     */
    public void loadDatosBasicos() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/DatosBasicosUsuario.fxml"));
            Parent parent = loader.load();
            rootModulo.setTop(parent);
            DatosBasicosUsuarioController control = loader.getController();
            control.cargarComponentes(tipoUsuario, codigo);
        } catch (IOException ex) {

        }
    }

    /**
     * Metodo que asigna un stage.
     *
     * @param stage
     */
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    /**
     * Método que asigna el código del usuario.
     *
     * @param codigo
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    /**
     * Metodo que asigna el tipo de usuario(Estudiante o Profesor)
     *
     * @param tipoUsuario
     */
    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }
}

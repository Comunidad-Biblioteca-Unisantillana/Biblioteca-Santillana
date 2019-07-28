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
 * FXML Controller class
 *
 * @author stive
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
    private ImageView imgIconOPAC;

    private Stage stage;

    private String codigo;
    
    private String tipoUsuario;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        IniciarMenuDesplegable imd = new IniciarMenuDesplegable(drawer, anchorDrawer, hamburger);
        loadOPAC();
        imgIconPrestamo.setImage(new Image("/recursos/iconPrestamo.png"));
        imgIconMulta.setImage(new Image("/recursos/iconMulta.png"));
        imgIconOPAC.setImage(new Image("/recursos/iconOPAC.png"));
    }

    @FXML
    private void itemSalirPressed(ActionEvent event) {
        LoginUnisantillanaStage.getInstance().show();
        if(tipoUsuario.equalsIgnoreCase("estudiante")){
            CuentaEstudianteStage.deleteInstance();
        }else if(tipoUsuario.equalsIgnoreCase("profesor")){
            CuentaProfesorStage.deleteInstance();
        }
    }

    @FXML
    private void itemAcercaDePressed(ActionEvent event) {

    }

    @FXML
    private void btnPrestamoPressed(ActionEvent event) {
        loadPrestamo();
    }

    @FXML
    private void btnMultaPressed(ActionEvent event) {
        loadMulta();
    }

    @FXML
    private void btnOPACPressed(ActionEvent event) {
        loadOPAC();
    }
    
    /**
     * Metodo que carga el modulo prestamo.
     */
    private void loadPrestamo(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/PrestamoEstudiante.fxml"));
            GridPane parent = loader.load();
            rootModulo.setCenter(parent);
            PrestamoEstudianteController control = loader.getController();
            control.cargarDatosTablePrestamos(codigo);
        } catch (IOException ex) {
            
        }
    }
    
    /**
     * Metodo que carga el modulo multa.
     */
    private void loadMulta(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/MultaEstudiante.fxml"));
            GridPane parent = loader.load();
            rootModulo.setCenter(parent);
            MultaEstudianteController control = loader.getController();
            control.cargarDatosTableMultas(codigo);
        } catch (IOException ex) {
            
        }
    }
    
    /**
     * Metodo que carga el modulo OPAC.
     */
    private void loadOPAC(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/OPAC.fxml"));
            GridPane parent = loader.load();
            rootModulo.setCenter(parent);
            OPACController control = loader.getController();
            control.setStage(stage);
        } catch (IOException ex) {
            
        }
    }
    
    /**
     * Metodo que muestra el nombre y el codigo del usuario en la ventana
     */
    public void loadDatosBasicos(){
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
     * @param stage
     */
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    /**
     * Método que asigna el código del usuario.
     * @param codigo
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    /**
     * Metodo que asigna  el tipo de usuario(Estudiante o Profesor)
     * @param tipoUsuario 
     */
    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }
}

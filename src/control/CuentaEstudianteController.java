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
import vista.CuentaEstudianteStage;
import vista.IniciarMenuDesplegable;
import vista.LoginUnisantillanaStage;

/**
 * FXML Controller class
 *
 * @author stive
 */
public class CuentaEstudianteController implements Initializable {
    
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

    private CuentaEstudianteStage stageEst;

    private String codEstudiante;

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
        CuentaEstudianteStage.getInstance().close();
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
     * Metodo que carga el modulo prestamo del estudiante
     */
    private void loadPrestamo(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/PrestamoEstudiante.fxml"));
            GridPane parent = loader.load();
            rootModulo.setCenter(parent);
            PrestamoEstudianteController control = loader.getController();
            control.cargarDatosTablePrestamos(codEstudiante);
        } catch (IOException ex) {
            
        }
    }
    
    /**
     * Metodo que carga el modulo multa del estudiante
     */
    private void loadMulta(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/MultaEstudiante.fxml"));
            GridPane parent = loader.load();
            rootModulo.setCenter(parent);
            MultaEstudianteController control = loader.getController();
            control.cargarDatosTableMultas(codEstudiante);
        } catch (IOException ex) {
            
        }
    }
    
    /**
     * Metodo que carga el modulo OPAC del estudiante
     */
    private void loadOPAC(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/OPAC.fxml"));
            GridPane parent = loader.load();
            rootModulo.setCenter(parent);
            OPACController control = loader.getController();
            control.setStage(stageEst);
        } catch (IOException ex) {
            
        }
    }
    
    /**
     * Metodo que muestra el nombre y el codigo del estudiante en la ventana
     */
    public void loadDatosBasicosEstudiante(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/DatosBasicosUsuario.fxml"));
            Parent parent = loader.load();
            rootModulo.setTop(parent);
            DatosBasicosUsuarioController control = loader.getController();
            control.cargarComponentes("estudiante", codEstudiante);
        } catch (IOException ex) {
            
        }
    }

    /**
     * Metodo que asigna un stage de Estudiante
     *
     * @param stageEst
     */
    public void setStageEst(CuentaEstudianteStage stageEst) {
        this.stageEst = stageEst;
    }

    /**
     * Método que asigna el código del estudiante.
     *
     * @param codEstudiante
     */
    public void setCodEstudiante(String codEstudiante) {
        this.codEstudiante = codEstudiante;
    }
}

package control;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import vista.CuentaEstudianteStage;
import vista.LoginUnisantillanaStage;

/**
 * FXML Controller class
 *
 * @author stive
 */
public class CuentaEstudianteController implements Initializable {

    @FXML
    private BorderPane root;
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
        //cargar prestamo
        loadPrestamo();
        //cargar imagenes botones
        imgIconPrestamo.setImage(new Image("/recursos/iconPrestamo.png"));
        imgIconMulta.setImage(new Image("/recursos/iconPrestamo.png"));
        imgIconOPAC.setImage(new Image("/recursos/iconPrestamo.png"));
        //iniciar hamburger
        drawer.setSidePane(anchorDrawer);
        HamburgerBackArrowBasicTransition burgerTask2 = new HamburgerBackArrowBasicTransition(hamburger);
        burgerTask2.setRate(-1);
        hamburger.addEventHandler(MouseEvent.MOUSE_PRESSED, (e) -> {
            burgerTask2.setRate(burgerTask2.getRate() * -1);
            burgerTask2.play();
            if (drawer.isShown()) {
                drawer.close();
            } else {
                drawer.open();
            }
        });
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
            root.setCenter(loader.load());
            PrestamoEstudianteController control = loader.getController();
            control.setCodEstudiante(codEstudiante);
        } catch (IOException ex) {
            
        }
    }
    
    /**
     * Metodo que carga el modulo multa
     */
    private void loadMulta(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/MultaEstudiante.fxml"));
            root.setCenter(loader.load());
            MultaEstudianteController control = loader.getController();
            control.setCodEstudiante(codEstudiante);
        } catch (IOException ex) {
            
        }
    }
    
    /**
     * Metodo que carga el modulo OPAC
     */
    private void loadOPAC(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/OPACEstudiante.fxml"));
            root.setCenter(loader.load());
            OPACEstudianteController control = loader.getController();
            control.setStageEst(stageEst);
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

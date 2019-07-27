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
import vista.CuentaBibliotecarioStage;
import vista.IniciarMenuDesplegable;
import vista.LoginUnisantillanaStage;

/**
 * FXML Controller class
 *
 * @author stiven valencia
 */
public class CuentaBibliotecarioController implements  Initializable{
    
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
    private ImageView imgIconOPAC;
    
    private String idBibliotecario;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        IniciarMenuDesplegable imd = new IniciarMenuDesplegable(drawer, anchorDrawer, hamburger);
        imgIconPrestamo.setImage(new Image("/recursos/iconPrestamo.png"));
        imgIconReserva.setImage(new Image("/recursos/iconReserva.png"));
        imgIconDevolucion.setImage(new Image("/recursos/iconDevolucion.png"));
        imgIconMulta.setImage(new Image("/recursos/iconMulta.png"));
        imgIconOPAC.setImage(new Image("/recursos/iconOPAC.png"));
        loadOPAC();
    }
    
    @FXML
    private void btnPrestamoPressed(ActionEvent event) {
        loadPrestamo();
    }
    
    @FXML
    private void btnReservaPressed(ActionEvent event) {
        loadReserva();
    }
    
    @FXML
    private void btnDevolucionPressed(ActionEvent event) {
        loadDevolucion();
    }
    
    @FXML
    private void btnMultaPressed(ActionEvent event) {
        loadMulta();
    }
    
    @FXML
    private void btnOPACPressed(ActionEvent event) {
        loadOPAC();
    }

    @FXML
    private void itemSalirPressed(ActionEvent event) {
        retornarLoginUnisantillana();
    }

    @FXML
    private void itemAcercaDe(ActionEvent event) {
        
    }
    
    /**
     * Metodo que carga el modulo prestamo del bibliotecario
     */
    private void loadPrestamo(){
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
    private void loadReserva(){
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
    private void loadDevolucion(){
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
    private void loadMulta(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/MultaBibliotecario.fxml"));
            rootModulo.setCenter(loader.load());
            MultaBibliotecarioController control = loader.getController();
        } catch (IOException ex) {
            
        }
    }
    
    /**
     * Metodo que carga el modulo OPAC del estudiante
     */
    private void loadOPAC(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/OPAC.fxml"));
            rootModulo.setCenter(loader.load());
            OPACController control = loader.getController();
            control.setStage(stage);
        } catch (IOException ex) {
            
        }
    }
    
    /**
     * Metodo que muestra el nombre y la identificación del bibliotecario en la ventana
     */
    public void loadDatosBasicosBibliotecario(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/DatosBasicosUsuario.fxml"));
            Parent parent = loader.load();
            rootModulo.setTop(parent);
            DatosBasicosUsuarioController control = loader.getController();
            control.cargarComponentes("bibliotecario",idBibliotecario);
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
     * Método que retorna al login de la universidad.
     */
    private void retornarLoginUnisantillana() {
        CuentaBibliotecarioStage.getInstance().close();
        LoginUnisantillanaStage.getInstance().show();
    }

    /**
     * Metodo que asigna stage de bibliotecario
     * @param stage 
     */
    public void setStage(CuentaBibliotecarioStage stage) {
        this.stage = stage;
    }
}

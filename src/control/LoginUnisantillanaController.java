package control;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import modeloDAO.LoginBibliotecarioDAO;
import modeloDAO.LoginEstudianteDAO;
import vista.AlertBox;
import vista.CuentaBibliotecarioStage;
import vista.CuentaEstudianteStage;
import vista.IAlertBox;
import vista.LoginUnisantillanaStage;

/**
 * FXML Controller class
 *
 * @author stive
 */
public class LoginUnisantillanaController implements Initializable {

    @FXML
    private JFXTextField codEstudianteTxt;
    @FXML
    private JFXTextField idBibliotecarioTxt;
    @FXML
    private JFXPasswordField pwdBibliotecarioTxt;
    
    @FXML
    private ImageView imgEstudiante;
    @FXML
    private ImageView imgBibliotecario;
    @FXML
    private ImageView imgIconTxtEstud;
    @FXML
    private ImageView imgIconTxtBiblio;
    @FXML
    private ImageView imgIconPwdBiblio;
    @FXML
    private ImageView imgClose;
    @FXML
    private ImageView imgMinMax;
    @FXML
    private ImageView imgOcultar;
    @FXML
    private ImageView imgFondoBlancoEst;
    @FXML
    private ImageView imgFondoBlancoBib;
    
    @FXML
    private JFXButton btnLoginEstudiante;
    @FXML
    private JFXButton btnLoginBibliotecario;
    
    private boolean btnMinMaxPressed = false;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        imgEstudiante.setImage(new Image("/recursos/iconStudent.png"));
        imgEstudiante.setPreserveRatio(false);  
        imgBibliotecario.setImage(new Image("/recursos/iconBibliotecario.png"));
        imgClose.setImage(new Image("/recursos/cerrar.png"));
        imgMinMax.setImage(new Image("/recursos/maximizar.png"));
        imgOcultar.setImage(new Image("/recursos/ocultar.png"));
        imgFondoBlancoEst.setImage(new Image("/recursos/fondo-blanco.png"));
        imgFondoBlancoBib.setImage(new Image("/recursos/fondo-blanco.png"));
        
    }

    @FXML
    private void btnLoginEstPressed(ActionEvent event) {
        loguearEstudiante(codEstudianteTxt.getText().trim());
    }

    @FXML
    private void btnLoginBibPressed(ActionEvent event) {
        loguearBibliotecario(idBibliotecarioTxt.getText().trim(), pwdBibliotecarioTxt.getText().trim());

    }
    
    @FXML
    private void btnClosePressed(ActionEvent event){
        System.exit(0);
    }
    
    @FXML
    private void btnMinMaxPressed(ActionEvent event){
        if(!btnMinMaxPressed){
            modificarPosComponentes();
            LoginUnisantillanaStage.getInstance().setMaximized(true);
            imgMinMax.setImage(new Image("/recursos/minimizar.png"));
            btnMinMaxPressed = true;
        }else{
            restablecerPosComponentes();
            LoginUnisantillanaStage.getInstance().setMaximized(false);
            imgMinMax.setImage(new Image("/recursos/maximizar.png"));
            btnMinMaxPressed = false;
        }
    }
    
    @FXML
    private void btnOcultarPressed(ActionEvent event){
        LoginUnisantillanaStage.getInstance().setIconified(true);
    }

    /**
     * Método que loguea a un bibliotecario, por medio de una identificación y
     * contraseña.
     *
     * @param idBibliotecario
     * @param codPassword
     */
    private void loguearBibliotecario(String idBibliotecario, String codPassword) {
        LoginBibliotecarioDAO loginBibliotecario = new LoginBibliotecarioDAO();
        
        if (loginBibliotecario.readDAO(idBibliotecario, codPassword)) {            
            limpiarCamposTextosLoginBibliotecario();
            CuentaBibliotecarioStage.getInstance();
            CuentaBibliotecarioStage.getInstance().cargarIdBibliotecario(idBibliotecario);
            LoginUnisantillanaStage.getInstance().close();
        } else {
            limpiarCamposTextosLoginBibliotecario();
            IAlertBox alert = new AlertBox();
            alert.showAlert("Anuncio", "Login Bibliotecario", "No existe un bibliotecario con ese código");
        }
    }

    /**
     * Métodoq que loguea a un estudiante, por medio de un código.
     *
     * @param codEstudiante
     */
    private void loguearEstudiante(String codEstudiante) {
        LoginEstudianteDAO loginEstudiante = new LoginEstudianteDAO();

        if (loginEstudiante.readDAO(codEstudiante)) {                       
            CuentaEstudianteStage.getInstance();
            CuentaEstudianteStage.getInstance().cargarCodEstudiante(codEstudiante);
            LoginUnisantillanaStage.getInstance().close();
        } else {
            
            IAlertBox alert = new AlertBox();
            alert.showAlert("Anuncio", "Login Estudiante", "No existe un estudiante con ese código");
        }
        limpiarCamposTextosLoginEstudiante();
    }

    /**
     * Método que limpia los campos de texto de identificiación y contrasena.
     */
    private void limpiarCamposTextosLoginBibliotecario() {
        idBibliotecarioTxt.setText("");
        pwdBibliotecarioTxt.setText("");
    }

    /**
     * Método que limpia los campos de textos del código del estudiante.
     */
    private void limpiarCamposTextosLoginEstudiante() {
        codEstudianteTxt.setText("");
    }
    
    /**
     * Metodo que acomoda los  componentes cuando se maximiza la ventana
     */
    private void modificarPosComponentes(){
        //modifica posX en apartado estudiante
        imgFondoBlancoEst.setLayoutX(imgFondoBlancoEst.getLayoutX()+450);
        imgEstudiante.setLayoutX(imgEstudiante.getLayoutX()+450);
        imgIconTxtEstud.setLayoutX(imgIconTxtEstud.getLayoutX()+450);
        codEstudianteTxt.setLayoutX(codEstudianteTxt.getLayoutX()+450);
        btnLoginEstudiante.setLayoutX(btnLoginEstudiante.getLayoutX()+450);
        //modifica posX en apartado bibliotecario
        imgFondoBlancoBib.setLayoutX(imgFondoBlancoBib.getLayoutX()+450);
        imgBibliotecario.setLayoutX(imgBibliotecario.getLayoutX()+450);
        imgIconTxtBiblio.setLayoutX(imgIconTxtBiblio.getLayoutX()+450);
        imgIconPwdBiblio.setLayoutX(imgIconPwdBiblio.getLayoutX()+450);
        idBibliotecarioTxt.setLayoutX(idBibliotecarioTxt.getLayoutX()+450);
        pwdBibliotecarioTxt.setLayoutX(pwdBibliotecarioTxt.getLayoutX()+450);
        btnLoginBibliotecario.setLayoutX(btnLoginBibliotecario.getLayoutX()+450);
        //modifica posY en apartado estudiante
        imgFondoBlancoEst.setLayoutY(imgFondoBlancoEst.getLayoutY()+100);
        imgEstudiante.setLayoutY(imgEstudiante.getLayoutY()+100);
        imgIconTxtEstud.setLayoutY(imgIconTxtEstud.getLayoutY()+100);
        codEstudianteTxt.setLayoutY(codEstudianteTxt.getLayoutY()+100);
        btnLoginEstudiante.setLayoutY(btnLoginEstudiante.getLayoutY()+100);
        //modifica posY en apartado bibliotecario
        imgFondoBlancoBib.setLayoutY(imgFondoBlancoBib.getLayoutY()+100);
        imgBibliotecario.setLayoutY(imgBibliotecario.getLayoutY()+100);
        imgIconTxtBiblio.setLayoutY(imgIconTxtBiblio.getLayoutY()+100);
        imgIconPwdBiblio.setLayoutY(imgIconPwdBiblio.getLayoutY()+100);
        idBibliotecarioTxt.setLayoutY(idBibliotecarioTxt.getLayoutY()+100);
        pwdBibliotecarioTxt.setLayoutY(pwdBibliotecarioTxt.getLayoutY()+100);
        btnLoginBibliotecario.setLayoutY(btnLoginBibliotecario.getLayoutY()+100);
    }
    
    /**
     * Metodo que acomoda los componentes cuando se minimiza la ventana
     */
    private void restablecerPosComponentes(){
        //restablece posX en apartado estudiante
        imgFondoBlancoEst.setLayoutX(imgFondoBlancoEst.getLayoutX()-450);
        imgEstudiante.setLayoutX(imgEstudiante.getLayoutX()-450);
        imgIconTxtEstud.setLayoutX(imgIconTxtEstud.getLayoutX()-450);
        codEstudianteTxt.setLayoutX(codEstudianteTxt.getLayoutX()-450);
        btnLoginEstudiante.setLayoutX(btnLoginEstudiante.getLayoutX()-450);
        //restablece posX en apartado bibliotecario
        imgFondoBlancoBib.setLayoutX(imgFondoBlancoBib.getLayoutX()-450);
        imgBibliotecario.setLayoutX(imgBibliotecario.getLayoutX()-450);
        imgIconTxtBiblio.setLayoutX(imgIconTxtBiblio.getLayoutX()-450);
        imgIconPwdBiblio.setLayoutX(imgIconPwdBiblio.getLayoutX()-450);
        idBibliotecarioTxt.setLayoutX(idBibliotecarioTxt.getLayoutX()-450);
        pwdBibliotecarioTxt.setLayoutX(pwdBibliotecarioTxt.getLayoutX()-450);
        btnLoginBibliotecario.setLayoutX(btnLoginBibliotecario.getLayoutX()-450);
        //restablece posY en apartado estudiante
        imgFondoBlancoEst.setLayoutY(imgFondoBlancoEst.getLayoutY()-100);
        imgEstudiante.setLayoutY(imgEstudiante.getLayoutY()-100);
        imgIconTxtEstud.setLayoutY(imgIconTxtEstud.getLayoutY()-100);
        codEstudianteTxt.setLayoutY(codEstudianteTxt.getLayoutY()-100);
        btnLoginEstudiante.setLayoutY(btnLoginEstudiante.getLayoutY()-100);
        //restablece posY en apartado bibliotecario
        imgFondoBlancoBib.setLayoutY(imgFondoBlancoBib.getLayoutY()-100);
        imgBibliotecario.setLayoutY(imgBibliotecario.getLayoutY()-100);
        imgIconTxtBiblio.setLayoutY(imgIconTxtBiblio.getLayoutY()-100);
        imgIconPwdBiblio.setLayoutY(imgIconPwdBiblio.getLayoutY()-100);
        idBibliotecarioTxt.setLayoutY(idBibliotecarioTxt.getLayoutY()-100);
        pwdBibliotecarioTxt.setLayoutY(pwdBibliotecarioTxt.getLayoutY()-100);
        btnLoginBibliotecario.setLayoutY(btnLoginBibliotecario.getLayoutY()-100);
    }

}

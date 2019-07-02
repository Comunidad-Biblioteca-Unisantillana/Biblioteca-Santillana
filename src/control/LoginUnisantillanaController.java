package control;

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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        imgEstudiante.setImage(new Image("/recursos/iconStudent.png"));
        imgEstudiante.setPreserveRatio(false);  
        imgBibliotecario.setImage(new Image("/recursos/iconBibliotecario.png"));
    }

    @FXML
    private void btnLoginEstPressed(ActionEvent event) {
        loguearEstudiante(codEstudianteTxt.getText().trim());
    }

    @FXML
    private void btnLoginBibPressed(ActionEvent event) {
        loguearBibliotecario(idBibliotecarioTxt.getText().trim(), pwdBibliotecarioTxt.getText().trim());

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

}

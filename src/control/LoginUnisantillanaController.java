package control;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.base.ValidatorBase;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import modelo.ValidatorCodEstudiante;
import modelo.ValidatorIdBibliotecario;
import modelo.ValidatorPwdBibliotecario;
import vista.CuentaBibliotecarioStage;
import vista.CuentaEstudianteStage;
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
    private JFXTextField idProfesorTxt;
    @FXML
    private JFXPasswordField pwdBibliotecarioTxt;

    @FXML
    private ImageView imgEstudiante;
    @FXML
    private ImageView imgBibliotecario;
    @FXML
    private ImageView imgProfesor;
    @FXML
    private ImageView imgIconTxtEstud;
    @FXML
    private ImageView imgIconTxtBiblio;
    @FXML
    private ImageView imgIconPwdBiblio;
    @FXML
    private ImageView imgIconTxtProf;
    @FXML
    private ImageView imgFondoBlancoEst;
    @FXML
    private ImageView imgFondoBlancoBib;
    @FXML
    private ImageView imgFondoBlancoProf;
    @FXML
    private ImageView imgBackground;
    @FXML
    private ImageView imgBackground1;
    @FXML
    private ImageView imgBackground2;
    @FXML
    private ImageView imgBackground3;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        imgEstudiante.setImage(new Image("/recursos/iconStudent.png"));
        imgEstudiante.setPreserveRatio(false);
        imgBibliotecario.setImage(new Image("/recursos/iconBibliotecario.png"));
        imgBibliotecario.setPreserveRatio(false);
        imgProfesor.setImage(new Image("/recursos/iconTeacher.png"));
        imgProfesor.setPreserveRatio(false);
        imgFondoBlancoEst.setImage(new Image("/recursos/fondo-blanco.png"));
        imgFondoBlancoBib.setImage(new Image("/recursos/fondo-blanco.png"));
        imgFondoBlancoProf.setImage(new Image("/recursos/fondo-blanco.png"));
        imgIconTxtEstud.setImage(new Image("/recursos/login-textfield.png"));
        imgIconTxtBiblio.setImage(new Image("/recursos/login-textfield.png"));
        imgIconPwdBiblio.setImage(new Image("/recursos/pasword-textfield.png"));
        imgIconTxtProf.setImage(new Image("/recursos/login-textfield.png"));
        imgBackground.setImage(new Image("/recursos/background-field.png"));
        imgBackground1.setImage(new Image("/recursos/background-field.png"));
        imgBackground2.setImage(new Image("/recursos/background-field.png"));
        imgBackground3.setImage(new Image("/recursos/background-field.png"));
        agregarValidator(new ValidatorCodEstudiante(codEstudianteTxt), "No existe un estudiante con ese codigo", codEstudianteTxt,null);
        agregarValidator(new ValidatorIdBibliotecario(idBibliotecarioTxt), "No existe un bibliotecario con esa identificación", idBibliotecarioTxt,null);
        agregarValidator(new ValidatorPwdBibliotecario(idBibliotecarioTxt, pwdBibliotecarioTxt), "La contraseña es incorrecta", null, pwdBibliotecarioTxt);
    }

    @FXML
    private void btnLoginEstPressed(ActionEvent event) {
        loguearEstudiante(codEstudianteTxt.getText().trim());
    }

    @FXML
    private void btnLoginBibPressed(ActionEvent event) {
        loguearBibliotecario(idBibliotecarioTxt.getText().trim());

    }

    @FXML
    private void btnLoginProfPressed(ActionEvent event) {

    }

    /**
     * Método que loguea a un bibliotecario, por medio de una identificación y
     * contraseña.
     *
     * @param idBibliotecario
     * @param codPassword
     */
    private void loguearBibliotecario(String idBibliotecario) {
        if ((idBibliotecarioTxt.validate()) && (pwdBibliotecarioTxt.validate())) {
            limpiarCamposTextosLoginBibliotecario();
            CuentaBibliotecarioStage.getInstance();
            CuentaBibliotecarioStage.getInstance().cargarIdBibliotecario(idBibliotecario);
            LoginUnisantillanaStage.getInstance().close();
        }
        limpiarCamposTextosLoginBibliotecario();
    }

    /**
     * Métodoq que loguea a un estudiante, por medio de un código.
     *
     * @param codEstudiante
     */
    private void loguearEstudiante(String codEstudiante) {
        if (codEstudianteTxt.validate()) {
            CuentaEstudianteStage.getInstance();
            CuentaEstudianteStage.getInstance().cargarCodEstudiante(codEstudiante);
            LoginUnisantillanaStage.getInstance().close();
        }
        limpiarCamposTextosLoginEstudiante();
    }
    
    /**
     * Metodo que agrega un validador a un JFXTextField
     * @param validator
     * @param mensaje
     * @param txt 
     */
    private void agregarValidator(ValidatorBase validator,String mensaje,JFXTextField txt,JFXPasswordField pwd){
        validator.setMessage(mensaje);
        validator.setIcon(new ImageView("/recursos/error.png"));
        if(pwd == null){
            txt.getValidators().add(validator);
        }else{
            pwd.getValidators().add(validator);
        }
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

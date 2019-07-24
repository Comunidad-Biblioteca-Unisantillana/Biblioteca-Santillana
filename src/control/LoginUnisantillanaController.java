package control;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RequiredFieldValidator;
import com.jfoenix.validation.base.ValidatorBase;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import modelo.ValidatorLoginEstudiante;
import modelo.ValidatorLoginProfesor;
import modelo.ValidatorLoginBibliotecario;
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
    private JFXComboBox<String> comboLogin;
    @FXML
    private JFXTextField UsuarioTxt;
    @FXML
    private JFXPasswordField PasswordTxt;
    @FXML
    private ImageView imgLogin;
    @FXML
    private ImageView imgIconUsuarioTxt;
    @FXML
    private ImageView imgIconPasswordTxt;
    @FXML
    private ImageView imgFondoBlanco;
    @FXML
    private ImageView imgFondoBlancoTxt;
    @FXML
    private ImageView imgFondoBlancoTxt1;
    
    private ValidatorLoginEstudiante validatorEst;
    
    private ValidatorLoginProfesor validatorProf;
    
    private ValidatorLoginBibliotecario validatorBib;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //iniciar evento tecla
        KeyEventJFXTextFieldController control = new KeyEventJFXTextFieldController();
        control.soloNumeros(UsuarioTxt);
        //iniciar validadores
        RequiredFieldValidator v1 = new RequiredFieldValidator();
        iniciarValidador(v1, "","informacion");
        UsuarioTxt.getValidators().add(v1);
        
        RequiredFieldValidator v2 = new RequiredFieldValidator();
        iniciarValidador(v2, "Ingrese contraseña","informacion");
        PasswordTxt.getValidators().add(v2);
        
        validatorEst = new ValidatorLoginEstudiante(UsuarioTxt);
        iniciarValidador(validatorEst, "Codigo incorrecto", "error");
        
        validatorBib = new  ValidatorLoginBibliotecario(UsuarioTxt, PasswordTxt);
        iniciarValidador(validatorBib, "Identificación o contraseña incorrecta", "error");
        
        addEventValidador();
        //Iniciar componentes vista
        comboLogin.getItems().add("Estudiante");
        comboLogin.getItems().add("Bibliotecario");
        comboLogin.getItems().add("Docente");
        comboLogin.getSelectionModel().select(0);

        iniciarComponentesEstudiante();
        imgFondoBlanco.setImage(new Image("/recursos/fondo-blanco.png"));
        imgIconUsuarioTxt.setImage(new Image("/recursos/login-textfield.png"));
        imgFondoBlancoTxt.setImage(new Image("/recursos/background-field.png"));
        imgIconPasswordTxt.setImage(new Image("/recursos/pasword-textfield.png"));
        imgFondoBlancoTxt1.setImage(new Image("/recursos/background-field.png"));
    }

    @FXML
    private void btnComboLoginPressed(ActionEvent event) {
        if (comboLogin.getSelectionModel().getSelectedItem().equals("Estudiante")) {
            iniciarComponentesEstudiante();
        }
        if (comboLogin.getSelectionModel().getSelectedItem().equals("Bibliotecario")) {
            iniciarComponentesBib();
        }
        if (comboLogin.getSelectionModel().getSelectedItem().equals("Docente")) {
            iniciarComponentesProf();
        }
        //Actualizar validadores
        UsuarioTxt.setText("1");
        UsuarioTxt.validate();
        limpiarCamposTextosLogin();
        UsuarioTxt.validate();
        //***************
        UsuarioTxt.setText("1102515566");
        PasswordTxt.setText("1960316ls");
        //**************
    }

    @FXML
    private void btnAccederPressed(ActionEvent event) {
        if (comboLogin.getSelectionModel().getSelectedItem().equals("Estudiante")) {
            loguearEstudiante(UsuarioTxt.getText().trim());
        }
        if (comboLogin.getSelectionModel().getSelectedItem().equals("Bibliotecario")) {
            loguearBibliotecario(UsuarioTxt.getText().trim());
        }
        if (comboLogin.getSelectionModel().getSelectedItem().equals("Docente")) {

        }
    }

    /**
     * Metodo que inicia los componentes del bibliotecario
     */
    private void iniciarComponentesBib() {
        UsuarioTxt.getValidators().get(0).setMessage("Ingrese identificación");
        UsuarioTxt.setPromptText("Identificación");
        
        ComponentesBib(true);
        imgLogin.setImage(new Image("/recursos/iconBibliotecario.png"));

        UsuarioTxt.setLayoutY(271);
        imgIconUsuarioTxt.setLayoutY(272);
        imgFondoBlancoTxt.setLayoutY(253);
    }

    /**
     * Metodo que inicia los componentes del login estudiante
     */
    private void iniciarComponentesEstudiante() {
        UsuarioTxt.getValidators().get(0).setMessage("Ingrese codigo");
        UsuarioTxt.setPromptText("Codigo");
        //***************************
        UsuarioTxt.setText("1760156");
        //***************************
        ComponentesBib(false);
        imgLogin.setImage(new Image("/recursos/iconStudent.png"));

        UsuarioTxt.setLayoutY(320);
        imgIconUsuarioTxt.setLayoutY(325);
        imgFondoBlancoTxt.setLayoutY(302);
    }

    /**
     * Metodo que inicia los componentes del profesor
     */
    private void iniciarComponentesProf() {
        UsuarioTxt.getValidators().get(0).setMessage("Ingrese identificación");
        UsuarioTxt.setPromptText("Identificación");
        
        ComponentesBib(false);
        imgLogin.setImage(new Image("/recursos/iconTeacher.png"));

        UsuarioTxt.setLayoutY(320);
        imgIconUsuarioTxt.setLayoutY(325);
        imgFondoBlancoTxt.setLayoutY(302);
    }

    /**
     * Metodo que oculta los componentes que solo se tienen en cuenta en el
     * bibliotecario
     */
    private void ComponentesBib(boolean v) {
        imgIconPasswordTxt.setVisible(v);
        imgFondoBlancoTxt1.setVisible(v);
        PasswordTxt.setVisible(v);
    }

    /**
     * Metodo que carga los componentes de un validador
     *
     * @param validador
     * @param mensaje
     * @param txt
     */
    private void iniciarValidador(ValidatorBase validador, String mensaje,String nombreImagen) {
        validador.setMessage(mensaje);
        validador.setIcon(new ImageView("/recursos/" + nombreImagen + ".png"));
    }

    /**
     * metodo que inicializa la escucha del JFXTextField y JFXPasswordField
     */
    private void addEventValidador() {
        UsuarioTxt.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            if (!newValue) {
                UsuarioTxt.validate();
                return;
            }
            if (newValue) {
                UsuarioTxt.validate();
            }
        });
        PasswordTxt.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            if (!newValue) {
                PasswordTxt.validate();
            }
        });
    }

    /**
     * Método que loguea a un bibliotecario, por medio de una identificación y
     * contraseña.
     *
     * @param idBibliotecario
     * @param codPassword
     */
    private void loguearBibliotecario(String idBibliotecario) {
        if((UsuarioTxt.getText().equals("")) || (PasswordTxt.getText().equals(""))){
            UsuarioTxt.validate();
            PasswordTxt.validate();
            return;
        }
        UsuarioTxt.getValidators().add(validatorBib);
        if (UsuarioTxt.validate()) {
            CuentaBibliotecarioStage.getInstance();
            CuentaBibliotecarioStage.getInstance().cargarIdBibliotecario(idBibliotecario);
            LoginUnisantillanaStage.getInstance().close();
        }
        UsuarioTxt.getValidators().remove(1);
        limpiarCamposTextosLogin();
    }

    /**
     * Métodoq que loguea a un estudiante, por medio de un código.
     *
     * @param codEstudiante
     */
    private void loguearEstudiante(String codEstudiante) {
        UsuarioTxt.getValidators().add(validatorEst);
        if (UsuarioTxt.validate()) {
            CuentaEstudianteStage.getInstance();
            CuentaEstudianteStage.getInstance().cargarCodEstudiante(codEstudiante);
            LoginUnisantillanaStage.getInstance().close();
        }
        UsuarioTxt.getValidators().remove(1);
        limpiarCamposTextosLogin();
    }

    /**
     * Método que limpia los campos de texto de identificiación y contrasena.
     */
    private void limpiarCamposTextosLogin() {
        UsuarioTxt.setText("");
        PasswordTxt.setText("");
    }

}

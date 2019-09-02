package moduloLogin.control;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RequiredFieldValidator;
import com.jfoenix.validation.base.ValidatorBase;
import general.control.KeyEventJFXTextFieldController;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import moduloLogin.modelo.ValidatorLogin;
import usuarios.vista.CuentaBibliotecarioStage;
import usuarios.vista.CuentaEstudianteStage;
import usuarios.vista.CuentaProfesorStage;
import moduloLogin.vista.LoginUnisantillanaStage;

/**
 * Clase que controla la vista LoginUnisantillana.fxml
 * @author stive
 * Fecha de Creación: 05/09/2018
 * Fecha de ultima Modificación: 04/08/2019
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
    
    private ValidatorLogin validatorLogin;

    /**
     * Método que se ejecuta automáticamente al enlazar<br>
     * este controlador con su respectiva vista
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
        RequiredFieldValidator v2 = new RequiredFieldValidator();
        iniciarValidador(v2, "Ingrese contraseña","informacion");
        UsuarioTxt.getValidators().add(v1);
        PasswordTxt.getValidators().add(v2);
        validatorLogin = new ValidatorLogin(UsuarioTxt,PasswordTxt);
        addEventValidador();
        //Iniciar componentes vista
        comboLogin.getItems().add("Estudiante");
        comboLogin.getItems().add("Bibliotecario");
        comboLogin.getItems().add("Docente");
        comboLogin.getSelectionModel().select(0);
        imgFondoBlanco.setImage(new Image("/general/recursos/img/fondo-blanco.png"));
        imgIconUsuarioTxt.setImage(new Image("/general/recursos/img/login-textfield.png"));
        imgFondoBlancoTxt.setImage(new Image("/general/recursos/img/background-field.png"));
        imgIconPasswordTxt.setImage(new Image("/general/recursos/img/pasword-textfield.png"));
        imgFondoBlancoTxt1.setImage(new Image("/general/recursos/img/background-field.png"));
        iniciarComponentesEstudiante();
    }

    /**
     * Método que cambia los componentes del login
     * @param event 
     */
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

    /**
     * Método que loguea a un usuario
     * @param event 
     */
    @FXML
    private void btnAccederPressed(ActionEvent event) {
        if (comboLogin.getSelectionModel().getSelectedItem().equals("Estudiante")) {
            loguearEstudiante(UsuarioTxt.getText().trim());
        }
        if (comboLogin.getSelectionModel().getSelectedItem().equals("Bibliotecario")) {
            loguearBibliotecario(UsuarioTxt.getText().trim());
        }
        if (comboLogin.getSelectionModel().getSelectedItem().equals("Docente")) {
            loguearProfesor(UsuarioTxt.getText().trim());
        }
    }
    
    /**
     * Método que finaliza la ejecución del programa 
     * @param event 
     */
    @FXML
    private void btnSalirPressed(ActionEvent event){
        System.exit(0);
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
     * Metodo que carga los componentes de un validador
     * @param validador
     * @param mensaje
     * @param txt
     */
    private void iniciarValidador(ValidatorBase validador, String mensaje,String nombreImagen) {
        validador.setMessage(mensaje);
        validador.setIcon(new ImageView("/general/recursos/img/" + nombreImagen + ".png"));
    }

    /**
     * Metodo que inicia los componentes del bibliotecario
     */
    private void iniciarComponentesBib() {
        validatorLogin.setTipoUsuario("bibliotecario");
        iniciarValidador(validatorLogin, "Identificación o contraseña incorrecta", "error");
        UsuarioTxt.getValidators().get(0).setMessage("Ingrese identificación");
        UsuarioTxt.setPromptText("Identificación");
        ComponentesBib(true);
        imgLogin.setImage(new Image("/general/recursos/img/iconBibliotecario.png"));
        UsuarioTxt.setLayoutY(271);
        imgIconUsuarioTxt.setLayoutY(272);
        imgFondoBlancoTxt.setLayoutY(253);
    }

    /**
     * Metodo que inicia los componentes del login estudiante
     */
    private void iniciarComponentesEstudiante() {
        validatorLogin.setTipoUsuario("estudiante");
        iniciarValidador(validatorLogin, "Código incorrecto", "error");
        UsuarioTxt.getValidators().get(0).setMessage("Ingrese código");
        UsuarioTxt.setPromptText("Código");
        ComponentesBib(false);
        imgLogin.setImage(new Image("/general/recursos/img/iconStudent.png"));
        UsuarioTxt.setLayoutY(320);
        imgIconUsuarioTxt.setLayoutY(325);
        imgFondoBlancoTxt.setLayoutY(302);
    }

    /**
     * Metodo que inicia los componentes del profesor
     */
    private void iniciarComponentesProf() {
        validatorLogin.setTipoUsuario("profesor");
        iniciarValidador(validatorLogin, "Identificación incorrecta", "error");
        UsuarioTxt.getValidators().get(0).setMessage("Ingrese identificación");
        UsuarioTxt.setPromptText("Identificación");
        ComponentesBib(false);
        imgLogin.setImage(new Image("/general/recursos/img/iconTeacher.png"));
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
        UsuarioTxt.getValidators().add(validatorLogin);
        if (UsuarioTxt.validate()) {
            CuentaBibliotecarioStage.getInstance().cargarDatosBibliotecario(idBibliotecario);
            LoginUnisantillanaStage.getInstance().close();
        }
        UsuarioTxt.getValidators().remove(1);
        limpiarCamposTextosLogin();
    }

    /**
     * Método que loguea a un estudiante, por medio de un código.
     *
     * @param codEstudiante
     */
    private void loguearEstudiante(String codEstudiante) {
        UsuarioTxt.getValidators().add(validatorLogin);
        if (UsuarioTxt.validate()) {
            CuentaEstudianteStage.getInstance().cargarDatosEstudiante(codEstudiante);
            LoginUnisantillanaStage.getInstance().close();
        }
        UsuarioTxt.getValidators().remove(1);
        limpiarCamposTextosLogin();
    }
    
    /**
     * Método que loguea a un profesor, por medio de una identificación
     */
    private void loguearProfesor(String idProfesor){
        UsuarioTxt.getValidators().add(validatorLogin);
        if(UsuarioTxt.validate()){
            CuentaProfesorStage.getInstance().cargarDatosProfesor(idProfesor);
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

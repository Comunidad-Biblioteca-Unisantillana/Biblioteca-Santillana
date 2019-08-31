package moduloLogin.modelo;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.base.ValidatorBase;
import javafx.scene.control.TextInputControl;
import moduloLogin.DAO.LoginBibliotecarioDAO;
import moduloLogin.DAO.LoginEstudianteDAO;
import moduloLogin.DAO.LoginProfesorDAO;

/**
 * Clase que se encarga de verificar que los datos<br>
 * del usuario existan
 *
 * @author Julian 
 * Fecha de Creación: 10/07/2019 
 * Fecha de ultima Modificación:
 * 04/08/2019
 */
public class ValidatorLogin extends ValidatorBase {

    private final JFXTextField codigo;
    private final JFXPasswordField contrasena;
    private String tipoUsuario = "";

    /**
     * Constructor de la clase
     *
     * @param codigo
     * @param contrasena
     */
    public ValidatorLogin(JFXTextField codigo, JFXPasswordField contrasena) {
        this.codigo = codigo;
        this.contrasena = contrasena;
    }

    /**
     * Método que se encarga de evaluar la entrada<br>
     * del JFXTextField
     */
    @Override
    protected void eval() {
        if (srcControl.get() instanceof TextInputControl) {
            evalTextField();
        }
    }

    /**
     * Método que se encarga de verificar que los<br>
     * datos de la entrada se encuentren dentro de<br>
     * la base de datos
     */
    private void evalTextField() {
        switch (tipoUsuario.toLowerCase()) {
            case "bibliotecario":
                LoginBibliotecarioDAO loginBibliotecario = new LoginBibliotecarioDAO();
                hasErrors.set(!loginBibliotecario.readDAO(codigo.getText().trim(), contrasena.getText().trim()));
                break;
            case "estudiante":
                LoginEstudianteDAO loginEstudiante = new LoginEstudianteDAO();
                hasErrors.set(!loginEstudiante.readDAO(codigo.getText().trim()));
                break;
            case "profesor":
                LoginProfesorDAO loginProfesor = new LoginProfesorDAO();
                hasErrors.set(!loginProfesor.readDAO(codigo.getText().trim()));
                break;
            default:
                System.out.println("error de tipo usuario");
                break;
        }
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

}

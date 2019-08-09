package modelo;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.base.ValidatorBase;
import javafx.scene.control.TextInputControl;
import modeloDAO.LoginBibliotecarioDAO;

/**
 * Clase que se encarga de verificar que los datos<br>
 * del bibliotecario existan
 * @author Julian
 * Fecha de Creación: 10/07/2019
 * Fecha de ultima Modificación: 04/08/2019
 */
public class ValidatorLoginBibliotecario extends ValidatorBase{
    
    private final JFXTextField idBibliotecario;
    private final JFXPasswordField pwdBibliotecario;

    /**
     * Constructor de la clase
     * @param idBibliotecario
     * @param pwdBibliotecario 
     */
    public ValidatorLoginBibliotecario(JFXTextField idBibliotecario, JFXPasswordField pwdBibliotecario) {
        this.idBibliotecario = idBibliotecario;
        this.pwdBibliotecario = pwdBibliotecario;
    }

    /**
     * Método que se encarga de evaluar la entrada<br>
     * del JFXTextField
     */
    @Override
    protected void eval() {
        if(srcControl.get() instanceof TextInputControl) evalTextField();
    }
    
    /**
     * Método que se encarga de verificar que los<br>
     * datos de la entrada y del JFXPasswordField<br>
     * se encuentren dentro de la base de datos
     */
    private void evalTextField(){
        LoginBibliotecarioDAO loginBibliotecario = new LoginBibliotecarioDAO();
        hasErrors.set(!loginBibliotecario.readDAO(idBibliotecario.getText().trim(),pwdBibliotecario.getText().trim()));
    }
}

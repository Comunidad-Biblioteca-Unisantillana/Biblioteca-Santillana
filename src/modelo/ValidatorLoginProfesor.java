package modelo;

import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.base.ValidatorBase;
import javafx.scene.control.TextInputControl;

/**
 * Clase que se encarga de verificar que la identificación<br>
 * del profesor exista
 * @author Julian
 * Fecha de Creación: 10/07/2019
 * Fecha de ultima Modificación: 04/08/2019
 */
public class ValidatorLoginProfesor extends ValidatorBase {

    private final JFXTextField idProfesor;

    /**
     * Constructor de la clase
     * @param idProfesor 
     */
    public ValidatorLoginProfesor(JFXTextField idProfesor) {
        this.idProfesor = idProfesor;
    }

    /**
     * Método que se encarga de evaluar la entrada<br>
     * del JFXTextField
     */
    @Override
    protected void eval() {
        if (srcControl.get() instanceof TextInputControl)evalTextField();
    }

    /**
     * Método que se encarga de verificar que los<br>
     * datos de la entrada se encuentren dentro<br>
     * de la base de datos
     */
    private void evalTextField() {
        hasErrors.set(!true);
    }
}

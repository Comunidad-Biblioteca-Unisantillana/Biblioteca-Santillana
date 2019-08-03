/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.base.ValidatorBase;
import javafx.scene.control.TextInputControl;

/**
 *
 * @author Julian
 */
public class ValidatorLoginProfesor extends ValidatorBase {

    private final JFXTextField idProfesor;

    public ValidatorLoginProfesor(JFXTextField idProfesor) {
        this.idProfesor = idProfesor;
    }

    @Override
    protected void eval() {
        if (srcControl.get() instanceof TextInputControl)evalTextField();
    }

    /**
     * Metodo que valida la informacion que hay dentro del JFXTextField
     */
    private void evalTextField() {
        hasErrors.set(!true);
    }
}

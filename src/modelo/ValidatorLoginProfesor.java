/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.base.ValidatorBase;
import javafx.scene.control.TextInputControl;
import modeloDAO.LoginBibliotecarioDAO;

/**
 *
 * @author Storkolm
 */
public class ValidatorLoginProfesor extends ValidatorBase {

    private final JFXTextField idBibliotecario;

    public ValidatorLoginProfesor(JFXTextField idBIbliotecario) {
        this.idBibliotecario = idBIbliotecario;
    }

    @Override
    protected void eval() {
        if (srcControl.get() instanceof TextInputControl)evalTextField();
    }

    private void evalTextField() {
        LoginBibliotecarioDAO loginBibliotecario = new LoginBibliotecarioDAO();
        hasErrors.set(!loginBibliotecario.readDAO(idBibliotecario.getText().trim(), null));
    }
}

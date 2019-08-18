/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.base.ValidatorBase;
import javafx.scene.control.TextInputControl;
import modeloDAO.LoginEstudianteDAO;

/**
 *
 * @author Julian
 */
public class ValidatorLoginEstudiante extends ValidatorBase{
    
    private final JFXTextField codEstudiante;
    
    public  ValidatorLoginEstudiante(JFXTextField codEstudiante){
        this.codEstudiante = codEstudiante;
    }

    @Override
    protected void eval() {
        if(srcControl.get() instanceof TextInputControl) evalTextField();
    }
    
    private void evalTextField(){
        LoginEstudianteDAO loginEstudiante = new LoginEstudianteDAO();
        hasErrors.set(!loginEstudiante.readDAO(codEstudiante.getText().trim()));
    }
}

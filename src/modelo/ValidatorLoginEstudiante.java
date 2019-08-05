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
 * Clase que se encarga de verificar que el código<br>
 * del estudiante exista
 * @author Julian
 * Fecha de Creación: 10/07/2019
 * Fecha de ultima Modificación: 04/08/2019
 */
public class ValidatorLoginEstudiante extends ValidatorBase{
    
    private final JFXTextField codEstudiante;
    
    /**
     * Constructor de la clase
     * @param codEstudiante 
     */
    public  ValidatorLoginEstudiante(JFXTextField codEstudiante){
        this.codEstudiante = codEstudiante;
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
     * datos de la entrada se encuentren dentro<br>
     * de la base de datos
     */
    private void evalTextField(){
        LoginEstudianteDAO loginEstudiante = new LoginEstudianteDAO();
        hasErrors.set(!loginEstudiante.readDAO(codEstudiante.getText().trim()));
    }
}

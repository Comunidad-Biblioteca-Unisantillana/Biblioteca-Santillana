/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import com.jfoenix.controls.JFXTextField;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

/**
 * 
 * @author Julian
 */
public class KeyEventJFXTextFieldController {
    
    /**
     * Metodo que añade un evento de tecla
     * para que solo se permitan los numeros
     * @param campo 
     */
    public void soloNumeros(JFXTextField campo){
        campo.setOnKeyTyped(new EventHandler<KeyEvent>(){
            @Override
            public void handle(KeyEvent event) {
                char tecla = event.getCharacter().charAt(0);
                if (!Character.isDigit(tecla)) {
                    event.consume();
                }
            }
            
        });
    }
    
}

package control;

import com.jfoenix.controls.JFXTextField;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

/**
 * Clase que controla el ingreso de datos de un JFXTextField
 * @author Julian
 * Fecha de Creación: 14/07/2019
 * Fecha de ultima Modificación: 04/08/2019
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

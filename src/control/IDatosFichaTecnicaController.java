package control;

import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

/**
 * @author Julian
 */
public interface IDatosFichaTecnicaController {
    
    public void insertarDatos(JFXTextField[] TF,JFXTextArea[] TA);
    public String getDatosLabels();
    public int getCantidadTextField();
    public int getCantidadTextArea();
    
}

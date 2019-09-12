package moduloOPAC.control;

import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

/**
 * @author Julian
 * @creado
 * @author Miguel Fernández
 * @modificado 19/08/2019
 */
public interface IDatosFichaTecnicaController {

    /**
     * el metódo insserta los datos del mapa en los textFields y el textArea,
     * correpondientes a la ficha del mapa.
     *
     * @param TF
     * @param TA
     */
    public void insertarDatos(JFXTextField[] TF, JFXTextArea[] TA);

    /**
     * el metódo retorna la cantidad de textField a utilizar, en la
     * visualizacion de la ficha técnica del recurso.
     *
     * @return numero
     */
    public String getDatosLabels();

    public int getCantidadTextField();

    /**
     * el metódo retorna la cantidad de textArea a utilizar, en la visualizacion
     * de la ficha técnica del recurso.
     *
     * @return numero
     */
    public int getCantidadTextArea();

}

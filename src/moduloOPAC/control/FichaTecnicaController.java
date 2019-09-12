package moduloOPAC.control;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.GridPane;

/**
 * Clase que controla la vista FichaTecnica.fxml
 *
 * @author Miguel Fernández
 * @creado 20/08/2019
 * @author Miguel Fernández
 * @modificado 20/08/2019
 */
public class FichaTecnicaController implements Initializable {

    @FXML
    private GridPane gridPaneFicha;

    /**
     * el método que se ejecuta automáticamente al enlazar este controlador con
     * su respectiva vista.
     *
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }
    
    /**
     * el metódo carga la ficha técnica en la ventana.
     *
     * @param gridPaneFicha
     */
    public void setGriddPaneFicha(GridPane gridPaneFicha) {
        this.gridPaneFicha.add(gridPaneFicha, 0, 0);
    }

}

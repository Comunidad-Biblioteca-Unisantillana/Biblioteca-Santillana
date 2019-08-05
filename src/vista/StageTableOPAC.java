package vista;

import control.TablaOPACController;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Stage donde se muestra la información basica de un recurso
 * @author Camilo
 * Fecha de Creación: 05/09/2018
 * Fecha de ultima Modificación: 04/08/2019
 */
public class StageTableOPAC extends Stage{
    
    /**
     * Constructor de BibliotecaStage
     */
    public StageTableOPAC(String cadena, Stage stage) {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/TablaOPAC.fxml"));
            
            Parent root = loader.load();
            TablaOPACController controlador = loader.getController();
            controlador.cargarDatosTabla(cadena);
            
            Scene scene = new Scene(root);
            setScene(scene);
            setTitle("OPAC");
            getIcons().add(new Image("/recursos/iconUniversity.png"));
            initOwner(stage);
            initModality(Modality.WINDOW_MODAL); 
            show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

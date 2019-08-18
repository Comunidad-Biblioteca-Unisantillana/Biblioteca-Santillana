/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import control.TablaOPACController;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import modelo.ConsultaOPAC;

/**
 *
 * @author Camilo
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

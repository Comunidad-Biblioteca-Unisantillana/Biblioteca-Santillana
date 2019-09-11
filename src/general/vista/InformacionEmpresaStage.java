package general.vista;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * Stage donde se muestra la información de la empresa.
 *
 * @author Miguel Fernández
 * @creado: 09/09/2019
 * @autor Miguel Fernández
 * @modificación: 09/09/2019
 */
public class InformacionEmpresaStage extends Stage {

    /**
     * Constructor Ficha Tecnica.
     */
    public InformacionEmpresaStage() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/general/vista/InformacionEmpresa.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);
            setScene(scene);
            setTitle("Acerca");
            getIcons().add(new Image("/general/recursos/img/iconUniversity.png"));
            centerOnScreen();
            setResizable(false);              
            show();
        } catch (IOException e) {
            System.out.println("Error al generar ventana InformacionEmpresaStage");
        }
    }

}

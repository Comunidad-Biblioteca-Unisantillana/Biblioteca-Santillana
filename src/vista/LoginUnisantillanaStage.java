package vista;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * Stage donde se muestra el modulo login del usuario
 * @author stive
 * Fecha de Creación: 05/09/2018
 * Fecha de ultima Modificación: 04/08/2019
 */
public class LoginUnisantillanaStage extends Stage {

    /**
     * Constructor de BibliotecaStage
     */
    private LoginUnisantillanaStage() {

        try {
            Parent root = FXMLLoader.load(getClass().getResource("/vista/LoginUnisantillana.fxml"));
            Scene scene = new Scene(root);
            setScene(scene);
            setTitle("Login Unisantillana");
            getIcons().add(new Image("/recursos/iconUniversity.png"));
            setMinWidth(560);
            setMinHeight(678);
            setMaximized(true);
            setResizable(false);
            show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
   
    /**
     * Método que por medio de una clase estatica retorna<br>
     * una instancia de la clase LoginUnisantillanaStage
     * @return 
     */
    public static LoginUnisantillanaStage getInstance() {
        return LoginUnisantillanaStageHolder.INSTANCE;

    }

    /**
     * Clase estatica que contiene una instancia de la<br>
     * clase LoginUnisantillanaStage
     */
    private static class LoginUnisantillanaStageHolder {
        private static final LoginUnisantillanaStage INSTANCE = new LoginUnisantillanaStage();
    }
}

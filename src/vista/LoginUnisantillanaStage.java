package vista;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *
 * @author stive
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
            show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
   
    public static LoginUnisantillanaStage getInstance() {
        return LoginUnisantillanaStageHolder.INSTANCE;

    }

    private static class LoginUnisantillanaStageHolder {

        private static final LoginUnisantillanaStage INSTANCE = new LoginUnisantillanaStage();
    }
}

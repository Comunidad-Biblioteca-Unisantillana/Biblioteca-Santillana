package vista;

import control.CuentaBibliotecarioController;
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
public class CuentaBibliotecarioStage extends Stage {

    private FXMLLoader loader;
    private CuentaBibliotecarioController cbcc;

    /**
     * Constructor de BibliotecaStage
     */
    public CuentaBibliotecarioStage() {

        try {
            loader = new FXMLLoader(getClass().getResource("/vista/CuentaBibliotecario.fxml"));
            Parent root = loader.load();
            System.out.println(root.getClass().getName());
            cbcc = loader.getController();
            cbcc.setStage(this);
            Scene scene = new Scene(root);
            setScene(scene);
            setTitle("Cuenta Bibliotecario");
            getIcons().add(new Image("/recursos/iconUniversity.png"));
            setMaximized(true);
            show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void cargarDatosBibliotecario(String idBibliotecario) {
        cbcc.setIdBibliotecario(idBibliotecario);
        cbcc.loadDatosBasicosBibliotecario();
    }

    public static CuentaBibliotecarioStage getInstance() {
        return CuentaBibliotecarioStageHolder.INSTANCE;
    }

    private static class CuentaBibliotecarioStageHolder {

        private static final CuentaBibliotecarioStage INSTANCE = new CuentaBibliotecarioStage();
    }
}

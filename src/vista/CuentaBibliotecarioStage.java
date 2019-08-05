package vista;

import control.CuentaBibliotecarioController;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * Stage donde se muestra el modulo del bibliotecario
 * @author stive
 * Fecha de Creación: 05/09/2018
 * Fecha de ultima Modificación: 04/08/2019
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
            setResizable(false);
            show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Método que carga los datos del bibliotecario
     * @param idBibliotecario 
     */
    public void cargarDatosBibliotecario(String idBibliotecario) {
        cbcc.setIdBibliotecario(idBibliotecario);
        cbcc.loadDatosBasicosBibliotecario();
    }
    
    /**
     * Método que cierra el Stage del bibliotecario<br>
     * y borra su instancia
     */
    public static void deleteInstance(){
        CuentaBibliotecarioStageHolder.INSTANCE.close();
        CuentaBibliotecarioStageHolder.INSTANCE = null;
    }

    /**
     * Método que por medio de una clase estatica retorna<br>
     * una instancia de la clase CuentaBibliotecarioStage
     * @return 
     */
    public static CuentaBibliotecarioStage getInstance() {
        return CuentaBibliotecarioStageHolder.INSTANCE = new CuentaBibliotecarioStage();
    }

    /**
     * Clase estatica que contiene una instancia de la<br>
     * clase CuentaBibliotecarioStage
     */
    private static class CuentaBibliotecarioStageHolder {
        private static CuentaBibliotecarioStage INSTANCE;
    }
}

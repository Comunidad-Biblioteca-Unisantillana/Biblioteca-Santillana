package vista;

import control.CuentaEstudianteController;
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
public class CuentaEstudianteStage extends Stage {
    private FXMLLoader loader;
    private CuentaEstudianteController cec;

    private CuentaEstudianteStage() {
        
        try {
            loader = new FXMLLoader(getClass().getResource("/vista/CuentaEstudiante.fxml"));
            Parent root = loader.load();
            cec = loader.getController();
            cec.setStageEst(this);
            Scene scene = new Scene(root);
            setScene(scene);
            setTitle("Cuenta Estudiante"); 
            getIcons().add(new Image("/recursos/iconUniversity.png"));
            setMaximized(true);
            setResizable(false);
            show();  
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Método que carga el nombre y codigo del estudiante.
     * @param codEstudiante 
     */
    public void cargarDatosEstudiante(String codEstudiante) {
        cec.setCodEstudiante(codEstudiante);
        cec.loadDatosBasicosEstudiante();
    }
    
    public static CuentaEstudianteStage getInstance() {
        return CuentaEstudianteStageHolder.INSTANCE;
    }
    
    private static class CuentaEstudianteStageHolder {

        private static final CuentaEstudianteStage INSTANCE = new CuentaEstudianteStage();
    }
}

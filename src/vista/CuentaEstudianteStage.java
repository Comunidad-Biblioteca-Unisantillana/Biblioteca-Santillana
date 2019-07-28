package vista;

import control.CuentaEstudianteProfesorController;
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
    private CuentaEstudianteProfesorController cec;

    private CuentaEstudianteStage() {
        
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/CuentaEstudianteProfesor.fxml"));
            Parent root = loader.load();
            cec = loader.getController();
            cec.setStage(this);
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
     * Método que carga los datos del estudiante.
     * @param codigo 
     */
    public void cargarDatosEstudiante(String codigo) {
        cec.setCodigo(codigo);
        cec.setTipoUsuario("estudiante");
        cec.loadDatosBasicos();
    }
    
    public static CuentaEstudianteStage getInstance() {
        return CuentaEstudianteStageHolder.INSTANCE = new CuentaEstudianteStage();
    }
    
    public static void deleteInstance(){
        CuentaEstudianteStageHolder.INSTANCE.close();
        CuentaEstudianteStageHolder.INSTANCE = null;
    }
    
    private static class CuentaEstudianteStageHolder {
        private static CuentaEstudianteStage INSTANCE;
    }
}

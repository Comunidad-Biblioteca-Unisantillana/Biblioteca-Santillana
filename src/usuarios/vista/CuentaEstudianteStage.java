package usuarios.vista;

import usuarios.control.CuentaEstudianteProfesorController;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * Stage donde se muestra el modulo Estudiante
 * @author stive
 * Fecha de Creación: 05/09/2018
 * Fecha de ultima Modificación: 04/08/2019
 */
public class CuentaEstudianteStage extends Stage {
    
    private FXMLLoader loader;
    private CuentaEstudianteProfesorController cec;

    /**
     * Constructor de EstudianteStage
     */
    private CuentaEstudianteStage() {
        
        try {
            loader = new FXMLLoader(getClass().getResource("/usuarios/vista/CuentaEstudianteProfesor.fxml"));
            Parent root = loader.load();
            cec = loader.getController();
            cec.setStage(this);
            Scene scene = new Scene(root);
            setScene(scene);
            setTitle("Cuenta Estudiante"); 
            getIcons().add(new Image("/general/recursos/img/iconUniversity.png"));
            setMinWidth(java.awt.Toolkit.getDefaultToolkit().getScreenSize().width);
            setMinHeight(java.awt.Toolkit.getDefaultToolkit().getScreenSize().height-47);
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
    
    /**
     * Método que cierra el Stage del Estudiante<br>
     * y borra su instancia
     */
    public static void deleteInstance(){
        CuentaEstudianteStageHolder.INSTANCE.close();
        CuentaEstudianteStageHolder.INSTANCE = null;
    }
    
    /**
     * Método que por medio de una clase estatica retorna<br>
     * una instancia de la clase CuentaEstudianteStage
     * @return 
     */
    public static CuentaEstudianteStage getInstance() {
        return CuentaEstudianteStageHolder.INSTANCE = new CuentaEstudianteStage();
    }
    
    /**
     * Clase estatica que contiene una instancia de la<br>
     * clase CuentaEstudianteStage
     */
    private static class CuentaEstudianteStageHolder {
        private static CuentaEstudianteStage INSTANCE;
    }
}

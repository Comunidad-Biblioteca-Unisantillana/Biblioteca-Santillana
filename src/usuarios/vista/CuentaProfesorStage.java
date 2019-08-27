/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package usuarios.vista;

import usuarios.control.CuentaEstudianteProfesorController;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * Stage donde se muestra el modulo profesor
 * @author Julian
 * Fecha de Creación: 28/07/2019
 * Fecha de ultima Modificación: 04/08/2019
 */
public class CuentaProfesorStage extends Stage {

    private FXMLLoader loader;
    private CuentaEstudianteProfesorController cpc;

    /**
     * Constructor de la clase
     */
    private CuentaProfesorStage() {
        try {
            loader = new FXMLLoader(getClass().getResource("/usuarios/vista/CuentaEstudianteProfesor.fxml"));
            Parent root = loader.load();
            cpc = loader.getController();
            Scene scene = new Scene(root);
            setScene(scene);
            setTitle("Cuenta Profesor");
            getIcons().add(new Image("/general/recursos/img/iconUniversity.png"));
            setMinWidth(java.awt.Toolkit.getDefaultToolkit().getScreenSize().width);
            setMinHeight(java.awt.Toolkit.getDefaultToolkit().getScreenSize().height-47);
            setResizable(false);
            show();
        } catch (IOException ex) {
            Logger.getLogger(CuentaProfesorStage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Método que carga los datos del profesor
     * @param idProfesor 
     */
    public void cargarDatosProfesor(String idProfesor) {
        cpc.setCodigo(idProfesor);
        cpc.setTipoUsuario("profesor");
        cpc.loadDatosBasicos();
    }
    
    /**
     * Método que cierra el Stage del profesor<br>
     * y borra su instancia
     */
    public static void deleteInstance(){
        CuentaProfesorStageHolder.INSTANCE.close();
        CuentaProfesorStageHolder.INSTANCE = null;
    }

    /**
     * Método que por medio de una clase estatica retorna<br>
     * una instancia de la clase CuentaProfesorStage
     * @return 
     */
    public static CuentaProfesorStage getInstance() {
        return CuentaProfesorStageHolder.INSTANCE = new CuentaProfesorStage();
    }

    /**
     * Clase estatica que contiene una instancia de la<br>
     * clase CuentaProfesorStage
     */
    public static class CuentaProfesorStageHolder {
        private static CuentaProfesorStage INSTANCE;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import control.CuentaEstudianteProfesorController;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *
 * @author Julian
 */
public class CuentaProfesorStage extends Stage {

    private CuentaEstudianteProfesorController cpc;

    public CuentaProfesorStage() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/CuentaEstudianteProfesor.fxml"));
            Parent root = loader.load();
            cpc = loader.getController();
            Scene scene = new Scene(root);
            setScene(scene);
            setTitle("Cuenta Profesor");
            getIcons().add(new Image("/recursos/iconUniversity.png"));
            setMaximized(true);
            setResizable(false);
            show();
        } catch (IOException ex) {
            Logger.getLogger(CuentaProfesorStage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void cargarDatosProfesor(String idProfesor) {
        cpc.setCodigo(idProfesor);
        cpc.setTipoUsuario("profesor");
        cpc.loadDatosBasicos();
    }

    public static CuentaProfesorStage getInstance() {
        return CuentaProfesorStageHolder.INSTANCE = new CuentaProfesorStage();
    }
    
    public static void deleteInstance(){
        CuentaProfesorStageHolder.INSTANCE.close();
        CuentaProfesorStageHolder.INSTANCE = null;
    }

    public static class CuentaProfesorStageHolder {
        private static CuentaProfesorStage INSTANCE;
    }

}

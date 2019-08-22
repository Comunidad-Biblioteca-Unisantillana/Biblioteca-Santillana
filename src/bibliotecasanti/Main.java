package bibliotecasanti;

import control.CuentaEstudianteProfesorController;
import javafx.application.Application;
import javafx.stage.Stage;
import modelo.ConnectionBD;
import vista.CuentaBibliotecarioStage;
import vista.CuentaEstudianteStage;
import vista.LoginUnisantillanaStage;

/**
 *
 * @author stive
 */
public class Main extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        LoginUnisantillanaStage.getInstance();
        /*CuentaBibliotecarioStage.getInstance();
        CuentaEstudianteStage.getInstance();*/
        ConnectionBD.getInstance();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}

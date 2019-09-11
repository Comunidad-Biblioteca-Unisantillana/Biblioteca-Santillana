package bibliotecasanti;

import moduloReserva.modelo.VerificaReservaVencida;
import javafx.application.Application;
import javafx.stage.Stage;
import general.modelo.ConnectionBD;
import moduloLogin.vista.LoginUnisantillanaStage;
import moduloMulta.modelo.GeneradorMulta;

/**
 *
 * @author stive
 */
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        LoginUnisantillanaStage.getInstance();
        ConnectionBD.getInstance();
        VerificaReservaVencida.getInstance().start();
        GeneradorMulta.getInstance().start();
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}

package bibliotecasanti;

import moduloReserva.modelo.VerificaReservaVencida;
import javafx.application.Application;
import javafx.stage.Stage;
import general.modelo.ConnectionBD;
import moduloLogin.vista.LoginUnisantillanaStage;

/**
 *
 * @author stive
 */
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        LoginUnisantillanaStage.getInstance();
        ConnectionBD.getInstance();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        VerificaReservaVencida verificaReservaVencida = new VerificaReservaVencida();
        verificaReservaVencida.setName("TareaProgramadaReserva");
        verificaReservaVencida.start();
        launch(args);
    }

}

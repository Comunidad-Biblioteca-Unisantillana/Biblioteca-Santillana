package moduloMulta.modelo;

import general.modelo.ConnectionBD;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Camilo Jaramillo
 * @version 1.0
 * @created 27-ago.-2019 9:27:59 a. m.
 */
public class GeneradorMulta extends Thread {

    private static GeneradorMulta instance;

    private GeneradorMulta() {

    }

    @Override
    public void run() {
        String tipoUsuario[] = {"estudiante", "profesor"};
        String tipoMulta[] = {"diccionario", "enciclopedia", "libro", "mapa", "periodico", "revista"};
        while (true) {
            IMulta multa;
            FabricaMulta multaFab = new FabricaMulta();
            try {
                for (String tipoUsuario1 : tipoUsuario) {
                    for (String tipoMulta1 : tipoMulta) {
                        ConnectionBD.getInstance().closeConnection();
                        ConnectionBD.deleteInstance();
                        sleep(1000);
                        ConnectionBD.getInstance().getConnection();
                        
                        System.out.println("multa " + tipoUsuario1 + " del recurso " + tipoMulta1);
                        multa = multaFab.getMulta(tipoMulta1, tipoUsuario1);
                        multa.generarMulta();
                        multa.actualizarDiasAtrasadosMulta();
                        sleep(1);
                    }
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(GeneradorMulta.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(GeneradorMulta.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    public static final GeneradorMulta getInstance() {
        if (instance == null) {
            instance = new GeneradorMulta();
        }
        return instance;
    }

}

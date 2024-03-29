package moduloMulta.modelo;


import general.modelo.ConnectionBD;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * @author Camilo Jaramillo
 * @version 1.0
 * @created 27-ago.-2019 9:27:59 a. m.
 */
public class GeneradorMulta {

    public GeneradorMulta() {

    }

    public void multas() {
        String tipoUsuario[] = {"profesor","estudiante"};
        String tipoMulta[] = {"diccionario", "enciclopedia", "libro", "mapa", "periodico", "revista"};

        IMulta multa;
        FabricaMulta multaFab = new FabricaMulta();

        for (String tipoUsuario1 : tipoUsuario) {
            for (String tipoMulta1 : tipoMulta) {
                System.out.println("multa " + tipoUsuario1 + " del recurso " + tipoMulta1);
                multa = multaFab.getMulta(tipoMulta1, tipoUsuario1);
                multa.generarMulta();
                multa.actualizarDiasAtrasadosMulta();
            }
        }
    }

}

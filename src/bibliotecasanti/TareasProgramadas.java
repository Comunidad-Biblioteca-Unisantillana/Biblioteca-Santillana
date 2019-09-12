package bibliotecasanti;

import moduloMulta.modelo.GeneradorMulta;
import moduloReserva.modelo.VerificaReservaVencida;

/**
 *
 * @author win10
 */
public class TareasProgramadas {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       VerificaReservaVencida verificaReservaVencida = new VerificaReservaVencida();
       verificaReservaVencida.reserva();
       
       GeneradorMulta generadorMulta = new GeneradorMulta();
       generadorMulta.multas();
    }
    
}

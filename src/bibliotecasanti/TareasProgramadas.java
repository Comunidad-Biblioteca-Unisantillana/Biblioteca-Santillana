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
       GeneradorMulta generadorMulta = new GeneradorMulta();
       generadorMulta.multas();
       
//       VerificaReservaVencida verificaReservaVencida = new VerificaReservaVencida();
//       verificaReservaVencida.reserva();
    }
    
}

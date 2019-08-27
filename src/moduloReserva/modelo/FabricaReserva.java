
package moduloReserva.modelo;

import moduloReserva.fabrica.ReservaColgenEstFab;
import moduloReserva.fabrica.ReservaColgenProfFab;

/**
 *
 * @author Julian
 */
public class FabricaReserva {

    public FabricaReserva() {
    }
    
    public IReserva getReserva(String tipoUsuario){
        IReserva reserva;
        if(tipoUsuario.equalsIgnoreCase("estudiante")){
            reserva =  new ReservaColgenEstFab();
        }else{
            reserva = new ReservaColgenProfFab();
        }
        return reserva;
    }
    
}

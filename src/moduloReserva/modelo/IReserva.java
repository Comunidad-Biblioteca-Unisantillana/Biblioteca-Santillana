package moduloReserva.modelo;

/**
 *
 * @author Julian
 */
public interface IReserva {

    public boolean ejecutarReserva(String codBarras, String codUsuario, String idBibliotecario);
    
}

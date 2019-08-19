package moduloDevolucion;

/**
 * @author Camilo Jaramillo
 * @version 1.0
 * @created 04-ago.-2019 10:38:09 a. m.
 */
public interface IDevolucion {

    public boolean ejecutarDevolucion(String codBarras, String idBibliotecario, String estadoRecurso);

}

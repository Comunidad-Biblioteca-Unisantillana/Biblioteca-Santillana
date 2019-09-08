package moduloDevolucion.DAO;

import java.util.List;
import general.modelo.ConnectionBD;

/**
 * @author Camilo Jaramillo
 * @version 1.0
 * @param <T>
 * @created 04-ago.-2019 10:37:56 a. m.
 */
public abstract class DevolucionRecursoDAOAbs<T> {

    protected ConnectionBD connection;

    public abstract boolean createDAO(T devolucion);

    public abstract boolean deleteDAO(int codigo);

    public abstract List<T> readAllDAO();

    public abstract T readDAO(int codigo);

    public abstract boolean updateDAO(T devolucion);

    public abstract int readCodigoDAO(int codPrestamo);
}

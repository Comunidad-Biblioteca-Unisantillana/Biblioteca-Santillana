
package moduloPrestamoDAO;

import java.util.List;
import modelo.ConnectionBD;

/**
 * Clase abstracta de los prestamos de recursos
 * @author Julian
 * Fecha creación:10/08/2019 
 * Fecha ultima modificación:10/08/2019
 * @param <T>
 */
public abstract class PrestamoRecursoDAOAbs<T> {
    
    protected ConnectionBD connection;
    
    public abstract boolean createDAO(T prestamo);
    
    public abstract T readDAO(int pk);
    
    public abstract boolean updateDAO(T prestamo);
    
    public abstract boolean deleteDAO(int pk);
    
    public abstract List<T> readAllDAO();
    
    public abstract int readCodigoDAO(String codigo);
}

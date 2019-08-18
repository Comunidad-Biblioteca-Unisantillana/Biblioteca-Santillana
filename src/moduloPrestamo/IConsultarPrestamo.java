
package moduloPrestamo;

import javafx.collections.ObservableList;
import modelo.ConnectionBD;

/**
 *
 * @author Julian
 */
public abstract class IConsultarPrestamo {
    
    protected ConnectionBD connection;
    
    public abstract ObservableList<Prestamo> getHistorialPrestamos(String idUsuario);
    
    public abstract ObservableList<Prestamo> getPrestamosActuales(String idUsuario);
    
}

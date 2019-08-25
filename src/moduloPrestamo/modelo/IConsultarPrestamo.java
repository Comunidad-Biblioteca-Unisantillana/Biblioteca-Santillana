package moduloPrestamo.modelo;

import javafx.collections.ObservableList;
import modelo.ConnectionBD;

/**
 *
 * @author Julian
 * @creado 12/08/2018
 * @author Miguel Fernández
 * @modificado 23/08/2019
 */
public abstract class IConsultarPrestamo {

    protected ConnectionBD connection;

    /**
     * el metódo obtiene los préstamos que ha solicitado el usuario.
     *
     * @param idUsuario
     * @return ObservableList<Prestamo>
     */
    public abstract ObservableList<Prestamo> getHistorialPrestamos(String idUsuario);

    /**
     * el metódo obtiene los préstamos vigentes del usuario.
     *
     * @param idUsuario
     * @return ObservableList<Prestamo>
     */
    public abstract ObservableList<Prestamo> getPrestamosActuales(String idUsuario);

}

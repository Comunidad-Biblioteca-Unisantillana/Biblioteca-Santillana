package moduloMulta.DAO;

import general.modelo.ConnectionBD;
import java.util.List;

/**
 * Clase parametrizada. Permite crear un CRUD con cualquier tipo de entidad.
 *
 * @param <T>
 * @author Miguel Fernández
 * @creado: 30/08/2019
 * @author Miguel Fernández
 * @modificado: 30/08/2019
 */
public abstract class MultaDAOAbs<T> {

    protected ConnectionBD connection;

    /**
     * constructor de la clase sin paràmetros.
     */
    public MultaDAOAbs() {

    }

    /**
     * prototipo de método que inserta una tupla de una entidad especifica de la
     * base de datos.
     *
     * @param entity
     * @return boolean
     */
    public abstract boolean createDAO(T entity);

    /**
     * prototipo de método que elimina una tupla de una entidad especifica de la
     * base de datos, por medio del código de la multa.
     *
     * @param pk
     * @return boolean
     */
    public abstract boolean deleteDAO(int pk);

    /**
     * prototipo de método que obtiene todos los datos ingresados de una entidad
     * especifica de la base de datos.
     *
     * @return List<T>
     * @throws Exception
     */
    public abstract List<T> readAllDAO()
            throws Exception;

    /**
     * prototipo de método que obtiene una tupla de una entidad especifica de la
     * base de datos, por medio del código de barras.
     *
     * @param pk
     * @return T
     */
    public abstract T readDAO(int pk);

    /**
     * prototipo de método que obtiene una tupla de una entidad especifica de la
     * base de datos, por medio del código del usuario.
     *
     * @param idUsuario
     * @return boolean
     */
    public abstract boolean readIdUsuarioDAO(String idUsuario);

    /**
     * prototipo de método que actualiza una tupla de una entidad especifica de
     * la base de datos.
     *
     * @param entity
     * @return boolean
     */
    public abstract boolean updateDAO(T entity);

}

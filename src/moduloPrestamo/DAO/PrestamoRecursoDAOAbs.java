package moduloPrestamo.DAO;

import java.util.List;
import modelo.ConnectionBD;

/**
 * clase abstracta de los préstamos de los recursos.
 *
 * @author Julian
 * @creado 10/08/2019
 * @author Miguel Fernández
 * @modificado 24/08/2019
 * @param <T>
 */
public abstract class PrestamoRecursoDAOAbs<T> {

    protected ConnectionBD connection;

    /**
     * el metódo inserta un préstamo de un recurso a un estudiante o profesor en
     * la BD.
     *
     * @param prestamo
     * @return boolean
     */
    public abstract boolean createDAO(T prestamo);

    /**
     * el metódo busca un préstamo en especifico de un estudiante o profesor,
     * por medio del código.
     *
     * @param pk
     * @return T
     */
    public abstract T readDAO(int pk);

    /**
     * el método actuliza un dato del prestamo de un profesor o estudiante.
     *
     * @param prestamo
     * @return boolean
     */
    public abstract boolean updateDAO(T prestamo);

    /**
     * el metódo se encarga de elimnar un préstamo de la BD de un profesor o
     * estudiante.
     *
     * @param pk
     * @return boolean
     */
    public abstract boolean deleteDAO(int pk);

    /**
     * el metódo retorna todos los préstamos de un reurso especifico que se han
     * realizado a estudiantes o profesores.
     *
     * @return List<T>
     */
    public abstract List<T> readAllDAO();

    /**
     * el metódo busca un préstamo en especifico de un estudiante o profesor,
     * por medio del código de barras.
     *
     * @param codigo
     * @return int
     */
    public abstract int readCodigoDAO(String codigo);

}

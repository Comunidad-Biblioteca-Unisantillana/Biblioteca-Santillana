
package modeloDAO;

import java.util.List;
/**
 * Clase parametrizada. 
 * Permite crear un CRUD con cualquier tipo de entidad.
 * @author Camilo
 * @param <T> 
 */
public interface InterfaceCRUD <T>{
    
    public boolean createDAO(T entity);
    
    public T readDAO(int pk);
    
    public boolean updateDAO(T entity);
    
    public boolean deleteDAO(int pk);
    
    public List<T> readAllDAO() throws Exception;
}

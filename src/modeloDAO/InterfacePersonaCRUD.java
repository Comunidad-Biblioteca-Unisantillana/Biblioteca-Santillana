
package modeloDAO;

import java.util.List;
/**
 * Clase parametrizada. 
 * Permite crear un CRUD con cualquier tipo de entidad.
 * @author Camilo
 * @param <T> 
 */
public interface InterfacePersonaCRUD <T>{
    
    public boolean createDAO(T entity);
    
    public T readDAO(String pk);
    
    public boolean updateDAO(T entity);
    
    public boolean deleteDAO(String pk);
    
    public List<T> readAllDAO() throws Exception;
}

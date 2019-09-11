
package moduloMulta.modelo;

import general.modelo.ConnectionBD;
import javafx.collections.ObservableList;
import moduloMulta.entitys.Multa;

/**
 * clase abstracta
 * @author Julian
 * @creado 29/08/2019
 * @modificado 02/09/2019
 * @param <T>
 */
public abstract class ConsultaMultaAbs<T> {
        
    protected ConnectionBD connection;
    
    /**
     * Metodo que elimina una multa de un usuario
     * @param codMulta
     * @param tipoRecurso
     * @param descripcion
     * @return 
     */
    public abstract boolean eliminarMulta(int codMulta,String tipoRecurso,String descripcion);
    
    /**
     * Metodo que obtiene todas las multas de los usuarios
     * @return 
     */
    public abstract ObservableList<Multa> getMultasAll();
    
    /**
     * Metodo que obtiene las multas de un usuario
     * @param idUsuario
     * @return 
     */
    public abstract ObservableList<Multa> getMultasUsuario(String idUsuario);
    
}

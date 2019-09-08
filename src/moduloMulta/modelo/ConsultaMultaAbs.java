
package moduloMulta.modelo;

import general.modelo.ConnectionBD;
import javafx.collections.ObservableList;
import moduloMulta.entitys.Multa;

/**
 * clase absstracta
 * @author Julian
 * @creado 29/08/2019
 * @modificado 31/08/2019
 * @param <T>
 */
public abstract class ConsultaMultaAbs<T> {
    
    protected ConnectionBD connection;
    
    public abstract boolean eliminarMulta(int codMulta,String tipoRecurso);
    
    public abstract ObservableList<Multa> getMultasAll();
    
    public abstract ObservableList<Multa> getMultasUsuario(String idUsuario);
    
}

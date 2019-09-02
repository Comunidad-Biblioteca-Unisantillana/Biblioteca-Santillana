/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moduloReserva.DAO;

import java.util.List;
import general.modelo.ConnectionBD;

/**
 *
 * @author Storkolm
 */
public abstract class ReservaRecursoDAOAbs<T> {
    
    protected ConnectionBD connection;
    
    public abstract boolean createDAO(T reserva);
    
    public abstract boolean deleteDAO(String codigo);
    
    public abstract List<T> readAllDAO();
    
    public abstract T readDAO(String codigo);
    
    public abstract boolean updateDAO(T reserva);
    
}

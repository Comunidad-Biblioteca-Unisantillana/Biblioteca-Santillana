
package moduloLogin.DAO;

import general.modelo.ConnectionBD;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Julian
 */
public class LoginProfesorDAO {
    private ConnectionBD connection;
    
    public LoginProfesorDAO() {
        connection = ConnectionBD.getInstance();
    }
    
    public boolean readDAO(String idProfesor){
        Statement stmt;
        ResultSet rs;

        try {
            stmt = connection.getConnection().createStatement();
            rs = stmt.executeQuery("SELECT idprofesor FROM Profesor WHERE idprofesor = '" + idProfesor + "';");

           while(rs.next()) {
                System.out.println("consulto");
                return true;
            }
            rs.close();            
            
        } catch (SQLException e) {
            System.out.println("El profesor con esta identificaación no existe");
        } catch (Exception e) {
            System.out.println("No se pudo realizar la consulta");
        } 
        return false;
    } 
}


package moduloLogin.DAO;

import general.modelo.ConnectionBD;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author stive
 */
public class LoginEstudianteDAO {
    private ConnectionBD connection;
    
    public LoginEstudianteDAO() {
        connection = ConnectionBD.getInstance();
    }
    /**
     * Método que realiza el read de la BD por medio un código.
     * @param codEstudiante
     * @return 
     */
    public boolean readDAO(String codEstudiante){
        Statement stmt;
        ResultSet rs;

        try {
            stmt = connection.getConnection().createStatement();
            rs = stmt.executeQuery("SELECT * FROM Estudiante WHERE codEstudiante = '" + codEstudiante + "';");

           while(rs.next()) {
                System.out.println("consulto");
                return true;
            }
            rs.close();            
            
        } catch (SQLException e) {
            System.out.println("El estudiante con este codigo no existe");
        } catch (Exception e) {
            System.out.println("No se pudo realizar la consulta");
        } 
        return false;
    } 
}

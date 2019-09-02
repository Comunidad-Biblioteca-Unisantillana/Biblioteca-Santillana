package modeloDAO;

import general.modelo.ConnectionBD;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author stive
 */
public class LoginBibliotecarioDAO {

    private ConnectionBD connection;

    public LoginBibliotecarioDAO() {
        connection = ConnectionBD.getInstance();
    }

    /**
     * Método que realiza las consultas en la BD por medio de una identificación
     * y contraseña.
     *
     * @param codBibliotecario
     * @param codPassword
     * @return 
     */
    public boolean readDAO(String codBibliotecario, String codPassword) {
        Statement stmt;
        ResultSet rs;

        try {
            stmt = connection.getConnection().createStatement();
            rs = stmt.executeQuery("SELECT idBibliotecario FROM Login_Bibliotecario WHERE idBibliotecario = '" + codBibliotecario + "' AND codPassword = '" + codPassword + "';");
            while (rs.next()) {
                System.out.println("consulto");
                return true;
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println("El bibliotecario con esta identificación y contraseña no existe");
        } catch (Exception e) {
            System.out.println("No se pudo realizar la consulta");
        }
        return false;
    }
}

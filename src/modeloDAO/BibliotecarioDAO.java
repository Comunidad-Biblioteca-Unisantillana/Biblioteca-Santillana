/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeloDAO;

import entitysUsuario.Bibliotecario;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import modelo.ConnectionBD;

/**
 *
 * @author Julian
 */
public class BibliotecarioDAO implements InterfacePersonaCRUD<Bibliotecario> {

    private ConnectionBD connection;

    public BibliotecarioDAO() {
        connection = ConnectionBD.getInstance();
    }

    @Override
    public boolean createDAO(Bibliotecario entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Método que realiza las consultas en la BD por medio de una
     * identificación.
     *
     * @param idBibliotecario
     * @return
     */
    @Override
    public Bibliotecario readDAO(String idBibliotecario) {
        Statement stmt;
        ResultSet rs;
        Bibliotecario bibliotecario = null;
        try {
            stmt = connection.getConnection().createStatement();
            rs = stmt.executeQuery("SELECT * FROM Bibliotecario WHERE idBibliotecario = '" + idBibliotecario + "'");
            while (rs.next()) {
                bibliotecario = new Bibliotecario(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),
                        rs.getDate(5),rs.getInt(6),rs.getString(7),rs.getString(8),rs.getString(9),
                        rs.getString(10),rs.getString(11),rs.getString(12),rs.getDouble(13));
            }
            rs.close();
            return bibliotecario;
        } catch (SQLException e) {
            System.out.println("El bibliotecario con esta identificación no existe");
        } catch (Exception e) {
            System.out.println("No se pudo realizar la consulta");
        } 
        return bibliotecario;
    }

    @Override
    public boolean updateDAO(Bibliotecario entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean deleteDAO(String pk) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Bibliotecario> readAllDAO() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeloDAO;

import general.modelo.ConnectionBD;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import usuarios.entitys.Profesor;

/**
 *
 * @author 
 */
public class ProfesorDAO implements InterfacePersonaCRUD<Profesor>{

    private ConnectionBD connection;

    public ProfesorDAO() {
        connection = ConnectionBD.getInstance();
    }
    
    @Override
    public boolean createDAO(Profesor entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Profesor readDAO(String idProfesor) {
        Statement stmt;
        ResultSet rs;
        Profesor profesor = null;
        try {
            stmt = connection.getConnection().createStatement();
            rs = stmt.executeQuery("SELECT * FROM Profesor WHERE idprofesor = '" + idProfesor + "'" );
            
            while (rs.next()) {
                profesor = new Profesor(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getDate(5),
                        rs.getInt(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(11),
                        rs.getString(12));
            }
            rs.close();
            return profesor;
        } catch (SQLException e) {
            System.out.println("El profesor con esta identificación no existe");
        } catch (Exception e) {
            System.out.println("No se pudo realizar la consulta");
        }
        return profesor;
    }

    @Override
    public boolean updateDAO(Profesor entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean deleteDAO(String pk) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Profesor> readAllDAO() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

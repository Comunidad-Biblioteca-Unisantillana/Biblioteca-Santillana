package modeloDAO;

import modelo.ConnectionBD;
import entitys.MultaEnciclopedia;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 * Clase que realiza el CRUD la entidad implementada.
 * @author Camilo
 */
public class MultaEnciclopediaDAO implements InterfaceCRUD <MultaEnciclopedia>{
    
    private ConnectionBD connection;

    public MultaEnciclopediaDAO(){
        connection = ConnectionBD.getInstance();
    }
    
    /**
     * Método que realiza los INSERT en la BD.
     * @param multa
     * @return boolean
     */
    @Override
    public boolean createDAO(MultaEnciclopedia multa) {
        
        String sqlSentence = "INSERT INTO Multa_Enciclopedia (codPrestamoEnciclopedia, diasAtrasados, codPrecioHistMulta, valorTotalMulta, cancelado, tipo)"
                             + " VALUES (?,?,?,?,?,?)";
       
        PreparedStatement pps;
        
        try{
            pps = connection.getConnection().prepareStatement(sqlSentence);
            pps.setInt(1, multa.getCodPrestamoEnciclopedia());
            pps.setInt(2, multa.getDiasAtrasados());
            pps.setInt(3, multa.getCodPrecioHistMulta()); 
            pps.setInt(4, multa.getValorMulta());
            pps.setString(5, multa.getCancelado());
            pps.setString(6, multa.getTipo());
            
            if(pps.executeUpdate() > 0){
                System.out.println("Registro creado");
                return true;
            }
        }catch(SQLException e){
            System.out.println("El registro no se pudo crear " + "\n" + e.getMessage());
        } 
        return false;
    }

    /**
     * Método que realiza las consultas en la BD por medio de un código.
     * @param codigo : int
     * @return MultaEnciclopedia
     */
    @Override
    public MultaEnciclopedia readDAO(int codigo){
        Statement stmt;
        ResultSet rs;
        MultaEnciclopedia multaLibro = null;
        
        try{
            stmt = connection.getConnection().createStatement();
            rs = stmt.executeQuery("SELECT * FROM Multa_Enciclopedia WHERE codMultaEnciclopedia = " + codigo);
            
            while(rs.next()){
                multaLibro = new MultaEnciclopedia(rs.getInt("codPrestamoEnciclopedia"), rs.getInt("diasAtrasados"), rs.getInt("codPrecioHistMulta"),
                rs.getInt("valorTotalMulta"), rs.getString("cancelado"), rs.getString("tipo"));
                multaLibro.setCodMultaEnciclopedia(rs.getInt("codMultaEnciclopedia"));
            }
            rs.close();
            return multaLibro;
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, "La multa de prestamo con ese codigo no existe");
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "No se pudo realizar la consulta");
        }
        return multaLibro;
    }
    
   

    /**
     * Método que realiza las actualizaciones en la BD.
     * @param multa
     * @return : boolean
     */
    @Override
    public boolean updateDAO(MultaEnciclopedia multa) {
        String sqlSentence = "UPDATE Multa_Enciclopedia SET codPrestamoEnciclopedia = ?, diasAtrasados = ?, codPrecioHistMulta = ?, valorTotalMulta = ?, cancelado = ?, tipo = ?"
                + " WHERE codMultaEnciclopedia = ?";
        PreparedStatement pps;
        
        try{       
            pps = connection.getConnection().prepareStatement(sqlSentence);
           
            pps.setInt(1, multa.getCodPrestamoEnciclopedia());
            pps.setInt(2, multa.getDiasAtrasados());
            pps.setInt(3, multa.getCodPrecioHistMulta());
            pps.setInt(4, multa.getValorMulta());
            pps.setString(5, multa.getCancelado());
            pps.setString(6, multa.getTipo());
            pps.setInt(7, multa.getCodMultaEnciclopedia());
            
            if(pps.executeUpdate() > 0){
               System.out.println("Realizo el update");
               return true;
            }
            else
                System.out.println("No existe una multa con ese codigo");
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, "No se pudo realizar el update de multa enciclopedia");
        } 
        return false;
    }

    /**
     * Método que realiza los deleteDAO de la BD por medio de un código.
     * @param pk : int
     * @return : boolean
     */
    @Override
    public boolean deleteDAO(int pk) {
        String sqlSentence = "DELETE FROM Multa_Enciclopedia WHERE codMultaEnciclopedia = ?";
        PreparedStatement pps;
       
        try{
           pps = connection.getConnection().prepareStatement(sqlSentence);
           
           pps.setInt(1, pk);
           
           if(pps.executeUpdate() > 0){
               System.out.println("Hizo el delete");
               return true;
           }
        }
        catch(SQLException e){
           System.err.println("No se pudo realizar el delete sobre la multa enciclopedia");
        }
       return false;
    }   

    /**
     * Método que consulta todas las filas de esa entidad en la base de datos.
     * @return List
     * @throws Exception 
     */
    @Override
    public List<MultaEnciclopedia> readAllDAO() throws Exception {
       
        PreparedStatement pps;
        ResultSet rs;
        ArrayList<MultaEnciclopedia> multas = new ArrayList();
        
        try{
            pps = connection.getConnection().prepareStatement("SELECT * FROM Multa_Enciclopedia");
            rs = pps.executeQuery();
            
            while(rs.next()){
                MultaEnciclopedia multaTmp  = new MultaEnciclopedia(rs.getInt("codPrestamoEnciclopedia"), rs.getInt("diasAtrasados"),
                                        rs.getInt("codPrecioHistMulta"), rs.getInt("valorTotalMulta"), rs.getString("cancelado"), rs.getString("tipo"));
                multaTmp.setCodMultaEnciclopedia(rs.getInt("codMultaEnciclopedia"));
                multas.add(multaTmp);
            }
            rs.close();
        }
        catch(SQLException e){
            System.out.println("No se realizo el readAll correctamente sobre multa enciclopedia");
        }
        catch(Exception e){
            System.out.println("Problema en el readAll");
        }        
        return multas;
    }
}

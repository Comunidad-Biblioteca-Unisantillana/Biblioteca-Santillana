package moduloMultaDAO;

import modelo.ConnectionBD;
import entitysRecursos.MultaLibro;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import modeloDAO.InterfaceCRUD;

/**
 * Clase que realiza el CRUD la entidad implementada.
 * @author Camilo
 */
public class MultaLibroDAO implements InterfaceCRUD <MultaLibro>{
    
    private ConnectionBD connection;

    public MultaLibroDAO(){
        connection = ConnectionBD.getInstance();
    }
    
    /**
     * Método que realiza los INSERT en la BD.
     * @param multa
     * @return boolean
     */
    @Override
    public boolean createDAO(MultaLibro multa) {
        
        String sqlSentence = "INSERT INTO Multa_Libro (codPrestamoLibro, diasAtrasados, codPrecioHistMulta, valorTotalMulta, cancelado, tipo)"
                             + " VALUES (?,?,?,?,?,?)";
       
        PreparedStatement pps;
        
        try{
            pps = connection.getConnection().prepareStatement(sqlSentence);
            pps.setInt(1, multa.getCodPrestamoLibro());
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
     * @return MultaLibro
     */
    @Override
    public MultaLibro readDAO(int codigo){
        Statement stmt;
        ResultSet rs;
        MultaLibro multaLibro = null;
        
        try{
            stmt = connection.getConnection().createStatement();
            rs = stmt.executeQuery("SELECT * FROM Multa_Libro WHERE codMultaLibro = " + codigo);
            
            while(rs.next()){
                multaLibro = new MultaLibro(rs.getInt("codPrestamoLibro"), rs.getInt("diasAtrasados"), rs.getInt("codPrecioHistMulta"),
                rs.getInt("valorTotalMulta"), rs.getString("cancelado"), rs.getString("tipo"));
                multaLibro.setCodMultaLibro(rs.getInt("codMultaLibro"));
            }
            rs.close();
            return multaLibro;
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, "La multa con ese codigo no existe");
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
    public boolean updateDAO(MultaLibro multa) {
        String sqlSentence = "UPDATE Multa_Libro SET codPrestamoLibro = ?, diasAtrasados = ?, codPrecioHistMulta = ?, valorTotalMulta = ?, cancelado = ?, tipo = ?"
                + " WHERE codMultaLibro = ?";
        PreparedStatement pps;
        
        try{       
            pps = connection.getConnection().prepareStatement(sqlSentence);
           
            pps.setInt(1, multa.getCodPrestamoLibro());
            pps.setInt(2, multa.getDiasAtrasados());
            pps.setInt(3, multa.getCodPrecioHistMulta());
            pps.setInt(4, multa.getValorMulta());
            pps.setString(5, multa.getCancelado());
            pps.setString(6, multa.getTipo());            
            pps.setInt(7, multa.getCodMultaLibro());
            
            if(pps.executeUpdate() > 0){
               System.out.println("Realizo el update");
               return true;
            }
            else
                System.out.println("No existe una multa con ese codigo");
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, "No se pudo realizar el update");
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
        String sqlSentence = "DELETE FROM Multa_Libro WHERE codMultaLibro = ?";
        PreparedStatement pps;
       
        try{
           pps = connection.getConnection().prepareStatement(sqlSentence);
           
           pps.setInt(1, pk);
           
           if(pps.executeUpdate() > 0){
               System.out.println("Hizo el delete");
               return true;
           }
           else
                System.out.println("No existe una multa con ese codigo");
        }
        catch(SQLException e){
           System.err.println("No se pudo realizar el delete");
        }
       return false;
    }   

    /**
     * Método que consulta todas las filas de esa entidad en la base de datos.
     * @return List
     * @throws Exception 
     */
    @Override
    public List<MultaLibro> readAllDAO() throws Exception {     
        PreparedStatement pps;
        ResultSet rs;
        ArrayList<MultaLibro> multas = new ArrayList();
        
        try{
            pps = connection.getConnection().prepareStatement("SELECT * FROM Multa_Libro");
            rs = pps.executeQuery();
            
            while(rs.next()){
                MultaLibro multaTmp  = new MultaLibro(rs.getInt("codPrestamoLibro"), rs.getInt("diasAtrasados"),
                                        rs.getInt("codPrecioHistMulta"), rs.getInt("valorTotalMulta"), rs.getString("cancelado"), rs.getString("tipo"));
                multaTmp.setCodMultaLibro(rs.getInt("codMultaLibro"));
                multas.add(multaTmp);
            }
            rs.close();
        }
        catch(SQLException e){
            System.out.println("No se realizo el readAll correctamente");
        }
        catch(Exception e){
            System.out.println("Problema en el readAll");
        }   
        return multas;
    }
}

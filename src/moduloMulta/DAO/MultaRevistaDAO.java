package moduloMulta.DAO;

import general.modelo.ConnectionBD;
import moduloMulta.entitys.MultaRevista;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import modeloDAO.InterfaceCRUD;

/**
 * Clase que realiza el CRUD la entidad implementada.
 * @author Camilo
 */
public class MultaRevistaDAO implements InterfaceCRUD <MultaRevista>{
    
    private ConnectionBD connection;

    public MultaRevistaDAO(){
        connection = ConnectionBD.getInstance();
    }
    
    /**
     * Método que realiza los INSERT en la BD.
     * @param multa
     * @return boolean
     */
    @Override
    public boolean createDAO(MultaRevista multa) {
        
        String sqlSentence = "INSERT INTO Multa_Revista (codPrestamoRevista, diasAtrasados, codPrecioHistMulta, valorTotalMulta, cancelado, tipo)"
                             + " VALUES (?,?,?,?,?,?)";   
        PreparedStatement pps;
        
        try{
            pps = connection.getConnection().prepareStatement(sqlSentence);
            pps.setInt(1, multa.getCodPrestamoRevista());
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
     * @return MultaRevista
     */
    @Override
    public MultaRevista readDAO(int codigo){
        Statement stmt;
        ResultSet rs;
        MultaRevista multa = null;
        
        try{
            stmt = connection.getConnection().createStatement();
            rs = stmt.executeQuery("SELECT * FROM Multa_Revista WHERE codMultaRevista = " + codigo);
            
            while(rs.next()){
                multa = new MultaRevista(rs.getInt("codPrestamoRevista"), rs.getInt("diasAtrasados"), rs.getInt("codPrecioHistMulta"),
                rs.getInt("valorTotalMulta"), rs.getString("cancelado"), rs.getString("tipo"));
                multa.setCodMultaRevista(rs.getInt("codMultaRevista"));
            }
            rs.close();
            return multa;
        }
        catch(SQLException e){
            System.out.println("La multa de prestamo con ese codigo no existe");
        }
        catch(Exception e){
            System.out.println("No se pudo realizar la consulta");
        }
        return multa;
    }
    
    /**
     * Método que realiza las actualizaciones en la BD.
     * @param multa
     * @return : boolean
     */
    @Override
    public boolean updateDAO(MultaRevista multa) {
        String sqlSentence = "UPDATE Multa_Revista SET codPrestamoRevista = ?, diasAtrasados = ?, codPrecioHistMulta = ?, valorTotalMulta = ?, cancelado = ?, tipo = ?"
                + " WHERE codMultaRevista = ?";
        PreparedStatement pps;
        
        try{       
            pps = connection.getConnection().prepareStatement(sqlSentence);      
            pps.setInt(1, multa.getCodPrestamoRevista());
            pps.setInt(2, multa.getDiasAtrasados());
            pps.setInt(3, multa.getCodPrecioHistMulta());
            pps.setInt(4, multa.getValorMulta());
            pps.setString(5, multa.getCancelado());
            pps.setString(6, multa.getTipo());
            pps.setInt(7, multa.getCodMultaRevista());
            
            if(pps.executeUpdate() > 0){
               System.out.println("Realizo el update");
               return true;
            }
            else
                System.out.println("No existe una multa con ese codigo");
        }
        catch(SQLException e){
            System.out.println("No se pudo realizar el update de multa revista");
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
        String sqlSentence = "DELETE FROM Multa_Revista WHERE codMultaRevista = ?";
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
           System.err.println("No se pudo realizar el delete sobre la multa revista");
        }
       return false;
    }   

    /**
     * Método que consulta todas las filas de esa entidad en la base de datos.
     * @return List
     * @throws Exception 
     */
    @Override
    public List<MultaRevista> readAllDAO() throws Exception {
       
        PreparedStatement pps;
        ResultSet rs;
        ArrayList<MultaRevista> multas = new ArrayList();
        
        try{
            pps = connection.getConnection().prepareStatement("SELECT * FROM Multa_Revista");
            rs = pps.executeQuery();
            
            while(rs.next()){
                MultaRevista multaTmp  = new MultaRevista(rs.getInt("codPrestamoRevista"), rs.getInt("diasAtrasados"),
                                        rs.getInt("codPrecioHistMulta"), rs.getInt("valorTotalMulta"), rs.getString("cancelado"), rs.getString("tipo"));
                multaTmp.setCodMultaRevista(rs.getInt("codMultaRevista"));
                multas.add(multaTmp);
            }
            rs.close();
        }
        catch(SQLException e){
            System.out.println("No se realizo el readAll correctamente sobre multa revista");
        }
        catch(Exception e){
            System.out.println("Problema en el readAll");
        }   
        return multas;
    }
}

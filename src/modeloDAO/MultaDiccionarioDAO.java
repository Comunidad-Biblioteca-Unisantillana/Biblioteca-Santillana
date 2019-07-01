package modeloDAO;

import modelo.ConnectionBD;
import entitys.MultaDiccionario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que realiza el CRUD la entidad implementada.
 * @author Camilo
 */
public class MultaDiccionarioDAO implements InterfaceCRUD <MultaDiccionario>{
    
    private ConnectionBD connection;

    public MultaDiccionarioDAO(){
        connection = ConnectionBD.getInstance();
    }
    
    /**
     * Método que realiza los INSERT en la BD.
     * @param multa
     * @return boolean
     */
    @Override
    public boolean createDAO(MultaDiccionario multa) {
        
        String sqlSentence = "INSERT INTO Multa_Diccionario (codPrestamoDiccionario, diasAtrasados, codPrecioHistMulta, valorTotalMulta, cancelado, tipo)"
                             + " VALUES (?,?,?,?,?,?)";   
        PreparedStatement pps;
        
        try{
            pps = connection.getConnection().prepareStatement(sqlSentence);
            pps.setInt(1, multa.getCodPrestamoDiccionario());
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
     * @return MultaDiccionario
     */
    @Override
    public MultaDiccionario readDAO(int codigo){
        Statement stmt;
        ResultSet rs;
        MultaDiccionario multa = null;
        
        try{
            stmt = connection.getConnection().createStatement();
            rs = stmt.executeQuery("SELECT * FROM Multa_Diccionario WHERE codMultaDiccionario = " + codigo);
            
            while(rs.next()){
                multa = new MultaDiccionario(rs.getInt("codPrestamoDiccionario"), rs.getInt("diasAtrasados"), rs.getInt("codPrecioHistMulta"),
                rs.getInt("valorTotalMulta"), rs.getString("cancelado"), rs.getString("tipo"));
                multa.setCodMultaDiccionario(rs.getInt("codMultaDiccionario"));
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
    public boolean updateDAO(MultaDiccionario multa) {
        String sqlSentence = "UPDATE Multa_Diccionario SET codPrestamoDiccionario = ?, diasAtrasados = ?, codPrecioHistMulta = ?, valorTotalMulta = ?, cancelado = ?, tipo = ?"
                + " WHERE codMultaDiccionario = ?";
        PreparedStatement pps;
        
        try{       
            pps = connection.getConnection().prepareStatement(sqlSentence);      
            pps.setInt(1, multa.getCodPrestamoDiccionario());
            pps.setInt(2, multa.getDiasAtrasados());
            pps.setInt(3, multa.getCodPrecioHistMulta());
            pps.setInt(4, multa.getValorMulta());
            pps.setString(5, multa.getCancelado());
            pps.setString(6, multa.getTipo());
            pps.setInt(7, multa.getCodMultaDiccionario());
            
            if(pps.executeUpdate() > 0){
               System.out.println("Realizo el update");
               return true;
            }
            else
                System.out.println("No existe una multa con ese codigo");
        }
        catch(SQLException e){
            System.out.println("No se pudo realizar el update de multa diccionario");
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
        String sqlSentence = "DELETE FROM Multa_Diccionario WHERE codMultaDiccionario = ?";
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
           System.err.println("No se pudo realizar el delete sobre la multa diccionario");
        }
       return false;
    }   

    /**
     * Método que consulta todas las filas de esa entidad en la base de datos.
     * @return List
     * @throws Exception 
     */
    @Override
    public List<MultaDiccionario> readAllDAO() throws Exception {
       
        PreparedStatement pps;
        ResultSet rs;
        ArrayList<MultaDiccionario> multas = new ArrayList();
        
        try{
            pps = connection.getConnection().prepareStatement("SELECT * FROM Multa_Diccionario");
            rs = pps.executeQuery();
            
            while(rs.next()){
                MultaDiccionario multaTmp  = new MultaDiccionario(rs.getInt("codPrestamoDiccionario"), rs.getInt("diasAtrasados"),
                                        rs.getInt("codPrecioHistMulta"), rs.getInt("valorTotalMulta"), rs.getString("cancelado"), rs.getString("tipo"));
                multaTmp.setCodMultaDiccionario(rs.getInt("codMultaDiccionario"));
                multas.add(multaTmp);
            }
            rs.close();
        }
        catch(SQLException e){
            System.out.println("No se realizo el readAll correctamente sobre multa diccionario");
        }
        catch(Exception e){
            System.out.println("Problema en el readAll");
        }   
        return multas;
    }
}

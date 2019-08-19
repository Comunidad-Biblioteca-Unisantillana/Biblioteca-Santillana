package moduloDevolucionDAO_Antiguo;

import modelo.ConnectionBD;
import entitys.DevolucionDiccionario;
import java.sql.Date;
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
public class DevolucionDiccionarioDAO implements InterfaceCRUD <DevolucionDiccionario>{
    
    private ConnectionBD connection;

    public DevolucionDiccionarioDAO(){
        connection = ConnectionBD.getInstance();
    }
    
    /**
     * Método que realiza los INSERT en la BD.
     * @param devolucion
     * @return boolean
     */
    @Override
    public boolean createDAO(DevolucionDiccionario devolucion) {
        
        String sqlSentence = "INSERT INTO Devolucion_Diccionario (codPrestamoDiccionario, idBibliotecario, fechaDevolucion, estadoDevolucion) VALUES (?,?,?,?)";  
        PreparedStatement pps;
        
        try{
            pps = connection.getConnection().prepareStatement(sqlSentence);
            pps.setInt(1, devolucion.getCodPrestamoDiccionario());
            pps.setString(2, devolucion.getIdBibliotecario());
            pps.setDate(3, (Date) devolucion.getFechaDevolucion()); 
            pps.setString(4, devolucion.getEstadoDevolucion());
            
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
     * @return DevolucioDiccionario
     */
    @Override
    public DevolucionDiccionario readDAO(int codigo){
        Statement stmt;
        ResultSet rs;
        DevolucionDiccionario devolucion = null;
        
        try{
            stmt = connection.getConnection().createStatement();
            rs = stmt.executeQuery("SELECT * FROM Devolucion_Diccionario WHERE codDevolucionDiccionario = " + codigo +";");
           
            while(rs.next()){
                devolucion = new DevolucionDiccionario(rs.getInt("codPrestamoDiccionario"), rs.getString("idBibliotecario"), rs.getDate("fechaDevolucion"),
                                   rs.getString("estadoDevolucion"));
                devolucion.setCodDevolucionDiccionario(rs.getInt("codDevolucionDiccionario"));
            }
            rs.close();
            return devolucion;
        }
        catch(SQLException e){
            System.out.println("La devolución de diccionario con ese codigo no existe");
        }
        catch(Exception e){
            System.out.println("No se pudo realizar la consulta");
        } 
        return devolucion;
    }
    
    /**
     * Método que realiza las actualizaciones en la BD.
     * @param devolucion
     * @return : boolean
     */
    @Override
    public boolean updateDAO(DevolucionDiccionario devolucion) {
        String sqlSentence = "UPDATE Devolucion_Diccionario SET codPrestamoDiccionario = ?, idBibliotecario = ?, fechaDevolucion = ?, estadoDevolucion = ?"
                + " WHERE codDevolucionDiccionario = ?";
        PreparedStatement pps;
        
        try{       
            pps = connection.getConnection().prepareStatement(sqlSentence);          
            pps.setInt(1, devolucion.getCodPrestamoDiccionario());
            pps.setString(2, devolucion.getIdBibliotecario());
            pps.setDate(3, (Date) devolucion.getFechaDevolucion()); 
            pps.setString(4, devolucion.getEstadoDevolucion());
            pps.setInt(5, devolucion.getCodDevolucionDiccionario());
            
            if(pps.executeUpdate() > 0){
               System.out.println("Realizo el update");
               return true;
            }
            else
                System.out.println("No existe una devolucion con ese codigo");
        }
        catch(SQLException e){
            System.out.println("No se pudo realizar el update de devolucion diccionario");
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
        String sqlSentence = "DELETE FROM Devolucion_Diccionario WHERE codDevolucionDiccionario = ?";
        PreparedStatement pps;
       
        try{
           pps = connection.getConnection().prepareStatement(sqlSentence);    
           pps.setInt(1, pk);
           
           if(pps.executeUpdate() > 0){
               System.out.println("Hizo el delete");
               return true;
           }
           else
                System.out.println("No existe una devolucion diccionario con ese codigo");
        }
        catch(SQLException e){
           System.err.println("No se pudo realizar el delete de devolucion diccionario");
        }
       return false;
    }   

    /**
     * Método que consulta todas las filas de esa entidad en la base de datos.
     * @return List
     * @throws Exception 
     */
    @Override
    public List<DevolucionDiccionario> readAllDAO() throws Exception { 
        PreparedStatement pps;
        ResultSet rs;
        ArrayList<DevolucionDiccionario> devoluciones = new ArrayList();
        
        try{
            pps = connection.getConnection().prepareStatement("SELECT * FROM Devolucion_Diccionario");
            rs = pps.executeQuery();
            
            while(rs.next()){
                DevolucionDiccionario devolucionTmp = new DevolucionDiccionario(rs.getInt("codPrestamoDiccionario"), rs.getString("idBibliotecario"), 
                        rs.getDate("fechaDevolucion"), rs.getString("estadoDevolucion"));
                devolucionTmp.setCodDevolucionDiccionario(rs.getInt("codDevolucionDiccionario"));
                devoluciones.add(devolucionTmp);
            }
            rs.close();
        }
        catch(SQLException e){
            System.out.println("No se realizo el readAll correctamente sobre devolucion diccionario");
        }
        catch(Exception e){
            System.out.println("Problema en el readAll");
        }      
        return devoluciones;
    }
}

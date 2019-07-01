package modeloDAO;

import modelo.ConnectionBD;
import entitys.DevolucionRevista;
import java.sql.Date;
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
public class DevolucionRevistaDAO implements InterfaceCRUD <DevolucionRevista>{
    
    private ConnectionBD connection;

    public DevolucionRevistaDAO(){
        connection = ConnectionBD.getInstance();
    }
    
    /**
     * Método que realiza los INSERT en la BD.
     * @param devolucion
     * @return boolean
     */
    @Override
    public boolean createDAO(DevolucionRevista devolucion) {
        
        String sqlSentence = "INSERT INTO Devolucion_Revista (codPrestamoRevista, idBibliotecario, fechaDevolucion, estadoDevolucion) VALUES (?,?,?,?)";  
        PreparedStatement pps;
        
        try{
            pps = connection.getConnection().prepareStatement(sqlSentence);
            pps.setInt(1, devolucion.getCodPrestamoRevista());
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
     * @return DevolucioRevista
     */
    @Override
    public DevolucionRevista readDAO(int codigo){
        Statement stmt;
        ResultSet rs;
        DevolucionRevista devolucion = null;
        
        try{
            stmt = connection.getConnection().createStatement();
            rs = stmt.executeQuery("SELECT * FROM Devolucion_Revista WHERE codDevolucionRevista = " + codigo +";");
           
            while(rs.next()){
                devolucion = new DevolucionRevista(rs.getInt("codPrestamoRevista"), rs.getString("idBibliotecario"), rs.getDate("fechaDevolucion"),
                                   rs.getString("estadoDevolucion"));
                devolucion.setCodDevolucionRevista(rs.getInt("codDevolucionRevista"));
            }
            rs.close();
            return devolucion;
        }
        catch(SQLException e){
            System.out.println("La devolución de revista con ese codigo no existe");
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
    public boolean updateDAO(DevolucionRevista devolucion) {
        String sqlSentence = "UPDATE Devolucion_Revista SET codPrestamoRevista = ?, idBibliotecario = ?, fechaDevolucion = ?, estadoDevolucion = ?"
                + " WHERE codDevolucionRevista = ?";
        PreparedStatement pps;
        
        try{       
            pps = connection.getConnection().prepareStatement(sqlSentence);         
            pps.setInt(1, devolucion.getCodPrestamoRevista());
            pps.setString(2, devolucion.getIdBibliotecario());
            pps.setDate(3, (Date) devolucion.getFechaDevolucion()); 
            pps.setString(4, devolucion.getEstadoDevolucion());
            pps.setInt(5, devolucion.getCodDevolucionRevista());
            
            if(pps.executeUpdate() > 0){
               System.out.println("Realizo el update");
               return true;
            }
            else
                System.out.println("No existe una devolucion con ese codigo");
        }
        catch(SQLException e){
            System.out.println("No se pudo realizar el update de devolucion revista");
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
        String sqlSentence = "DELETE FROM Devolucion_Revista WHERE codDevolucionRevista = ?";
        PreparedStatement pps;
       
        try{
           pps = connection.getConnection().prepareStatement(sqlSentence);    
           pps.setInt(1, pk);
           
           if(pps.executeUpdate() > 0){
               System.out.println("Hizo el delete");
               return true;
           }
           else
                System.out.println("No existe una devolucion revista con ese codigo");
        }
        catch(SQLException e){
           System.err.println("No se pudo realizar el delete de devolucion revista");
        }
       return false;
    }   

    /**
     * Método que consulta todas las filas de esa entidad en la base de datos.
     * @return List
     * @throws Exception 
     */
    @Override
    public List<DevolucionRevista> readAllDAO() throws Exception { 
        PreparedStatement pps;
        ResultSet rs;
        ArrayList<DevolucionRevista> devoluciones = new ArrayList();
        
        try{
            pps = connection.getConnection().prepareStatement("SELECT * FROM Devolucion_Revista");
            rs = pps.executeQuery();
            
            while(rs.next()){
                DevolucionRevista devolucionTmp = new DevolucionRevista(rs.getInt("codPrestamoRevista"), rs.getString("idBibliotecario"), 
                        rs.getDate("fechaDevolucion"), rs.getString("estadoDevolucion"));
                devolucionTmp.setCodDevolucionRevista(rs.getInt("codDevolucionRevista"));
                devoluciones.add(devolucionTmp);
            }
            rs.close();
        }
        catch(SQLException e){
            System.out.println("No se realizo el readAll correctamente sobre devolucion revista");
        }
        catch(Exception e){
            System.out.println("Problema en el readAll");
        }     
        return devoluciones;
    }
}

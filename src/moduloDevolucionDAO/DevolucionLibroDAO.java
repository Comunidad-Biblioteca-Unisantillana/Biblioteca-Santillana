package moduloDevolucionDAO;

import modelo.ConnectionBD;
import entitys.DevolucionLibro;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import modeloDAO.InterfaceCRUD;
import vista.AlertBox;
import vista.IAlertBox;

/**
 * Clase que realiza el CRUD la entidad implementada.
 * @author Camilo
 */
public class DevolucionLibroDAO implements InterfaceCRUD <DevolucionLibro>{
    
    private ConnectionBD connection;

    public DevolucionLibroDAO(){
        connection = ConnectionBD.getInstance();
    }
    
    /**
     * Método que realiza los INSERT en la BD.
     * @param devolucion
     * @return boolean
     */
    @Override
    public boolean createDAO(DevolucionLibro devolucion) {
        
        
        String sqlSentence = "INSERT INTO Devolucion_Libro (codPrestamoLibro, idBibliotecario, fechaDevolucion, estadoDevolucion) VALUES (?,?,?,?)";     
        PreparedStatement pps;
        
        try{
            pps = connection.getConnection().prepareStatement(sqlSentence);
            pps.setInt(1, devolucion.getCodPrestamoLibro());
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
     * @return DevolucionLibro
     */
    @Override
    public DevolucionLibro readDAO(int codigo){
        Statement stmt;
        ResultSet rs;
        DevolucionLibro devolucion = null;
        
        try{
            stmt = connection.getConnection().createStatement();
            rs = stmt.executeQuery("SELECT * FROM Devolucion_Libro WHERE codDevolucionLibro = " + codigo +";");
           
            while(rs.next()){
                devolucion = new DevolucionLibro(rs.getInt("codPrestamoLibro"), rs.getString("idBibliotecario"), rs.getDate("fechaDevolucion"),
                                   rs.getString("estadoDevolucion"));
                devolucion.setCodDevolucionLibro(rs.getInt("codDevolucionLibro"));
            }
            rs.close();
            return devolucion;
        }
        catch(SQLException e){
            System.out.println("La devolución con ese codigo no existe");
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
    public boolean updateDAO(DevolucionLibro devolucion) {
        String sqlSentence = "UPDATE Devolucion_Libro SET codPrestamoLibro = ?, idBibliotecario = ?, fechaDevolucion = ?, estadoDevolucion = ?"
                + " WHERE codDevolucionLibro = ?";
        PreparedStatement pps;
        
        try{       
            pps = connection.getConnection().prepareStatement(sqlSentence);         
            pps.setInt(1, devolucion.getCodPrestamoLibro());
            pps.setString(2, devolucion.getIdBibliotecario());
            pps.setDate(3, (Date) devolucion.getFechaDevolucion()); 
            pps.setString(4, devolucion.getEstadoDevolucion());
            pps.setInt(5, devolucion.getCodDevolucionLibro());
            
            if(pps.executeUpdate() > 0){
               System.out.println("Realizo el update");
               return true;
            }
            else
                System.out.println("No existe una devolucion con ese codigo");
        }
        catch(SQLException e){
            System.out.println("No se pudo realizar el update");
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
        String sqlSentence = "DELETE FROM Devolucion_Libro WHERE codDevolucionLibro = ?";
        PreparedStatement pps;
       
        try{
           pps = connection.getConnection().prepareStatement(sqlSentence);    
           pps.setInt(1, pk);
           
           if(pps.executeUpdate() > 0){
               System.out.println("Hizo el delete");
               return true;
           }
           else
                System.out.println("No existe una devolucion con ese codigo");
        }
        catch(SQLException e){
            System.out.println("No se puede realizar el delete de devolucion libro");
        }
       return false;
    }   

    /**
     * Método que consulta todas las filas de esa entidad en la base de datos.
     * @return List
     * @throws Exception 
     */
    @Override
    public List<DevolucionLibro> readAllDAO() throws Exception { 
        PreparedStatement pps;
        ResultSet rs;
        ArrayList<DevolucionLibro> devoluciones = new ArrayList();
        
        try{
            pps = connection.getConnection().prepareStatement("SELECT * FROM Devolucion_Libro");
            rs = pps.executeQuery();
            
            while(rs.next()){
                DevolucionLibro devolucionTmp = new DevolucionLibro(rs.getInt("codPrestamoLibro"), rs.getString("idBibliotecario"), 
                        rs.getDate("fechaDevolucion"), rs.getString("estadoDevolucion"));
                devolucionTmp.setCodDevolucionLibro(rs.getInt("codDevolucionLibro"));
                devoluciones.add(devolucionTmp);
            }
            rs.close();
        }
        catch(SQLException e){
            System.out.println("No se realizo el readAll correctamente");
        }
        catch(Exception e){
            System.out.println("Problema en el readAll");
        }
        return devoluciones;
    }
}

package moduloPrestamoDAO_Antiguo;

import modelo.ConnectionBD;
import entitys.PrestamoDiccionario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import modeloDAO.InterfaceCRUD;
import vista.AlertBox;
import vista.IAlertBox;

/**
 * Clase que realiza el CRUD sobre las entidades de préstamos.
 * @author Camilo
 */
public class PrestamoDiccionarioDAO implements InterfaceCRUD <PrestamoDiccionario>{
    
    private ConnectionBD connection;

    public PrestamoDiccionarioDAO(){
        connection = ConnectionBD.getInstance();
    }
    
    /**
     * Método que realiza los INSERT en la BD.
     * @param prestamo
     * @return boolean
     */
    @Override
    public boolean createDAO(PrestamoDiccionario prestamo) {
        
        String sqlSentence = "INSERT INTO Prestamo_Diccionario (codBarraDiccionario, codEstudiante, idBibliotecario, fechaPrestamo, fechaDevolucion)"
                             + " VALUES (?,?,?,?,?)";    
        PreparedStatement pps;
        
        try{
            pps = connection.getConnection().prepareStatement(sqlSentence);
            pps.setString(1, prestamo.getCodBarraDiccionario());
            pps.setString(2, prestamo.getCodEstudiante());
            pps.setString(3, prestamo.getIdBibliotecario()); 
            pps.setDate(4, prestamo.getFechaPrestamo());
            pps.setDate(5, prestamo.getFechaDevolucion());
            
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
     * @return PrestamoDiccionario
     */
    @Override
    public PrestamoDiccionario readDAO(int codigo){
        Statement stmt;
        ResultSet rs;
        PrestamoDiccionario prestamo = null;
        
        try{
            stmt = connection.getConnection().createStatement();
            rs = stmt.executeQuery("SELECT * FROM Prestamo_Diccionario WHERE codPrestamoDiccionario = " + codigo +";");
           
            while(rs.next()){
                prestamo = new PrestamoDiccionario(rs.getString("codBarraDiccionario"), rs.getString("codEstudiante"), 
                                rs.getString("idBibliotecario"), rs.getDate("fechaPrestamo"), rs.getDate("fechaDevolucion"));
                prestamo.setCodPrestamoDiccionario(rs.getInt("codPrestamoDiccionario"));
            }
            rs.close();
            return prestamo;
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, "El préstamo de diccionario con ese codigo no existe");
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "No se pudo realizar la consulta");
        }
        return prestamo;
    }
    
    /**
     * Método que realiza las consultas en la BD por medio de un código.
     * @param codBarra : String
     * @return boolean
     */
    public PrestamoDiccionario readDAO(String codBarra){
        boolean existeRecurso = false;
        Statement stmt;
        ResultSet rs;
        PrestamoDiccionario prestamo = null;
        
        try{
            stmt = connection.getConnection().createStatement();
            rs = stmt.executeQuery("SELECT * FROM Prestamo_Diccionario WHERE codBarraDiccionario = '" + codBarra +"';");     
            while(rs.next()){
                prestamo = new PrestamoDiccionario(rs.getString("codBarraDiccionario"), rs.getString("codEstudiante"), rs.getString("idBibliotecario"), rs.getDate("fechaPrestamo"), rs.getDate("fechaDevolucion"));
                prestamo.setCodPrestamoDiccionario(rs.getInt("codPrestamoEnciclopedia"));
            }
            rs.close();
            return prestamo;
        }
        catch(SQLException e){
            System.out.println("El préstamo con ese codigo no existe");
        }
        catch(Exception e){
            System.out.println("No se pudo realizar la consulta");
        }
        return prestamo;
    }
    
    /**
     * Método que realiza las actualizaciones en la BD.
     * @param prestamo
     * @return : boolean
     */
    @Override
    public boolean updateDAO(PrestamoDiccionario prestamo) {
        String sqlSentence = "UPDATE Prestamo_Diccionario SET codBarraDiccionario = ?, codEstudiante = ?, idBibliotecario = ?, fechaPrestamo = ?, "
                             + "fechaDevolucion = ? WHERE codPrestamoDiccionario = ?";
        PreparedStatement pps;
        
        try{       
            pps = connection.getConnection().prepareStatement(sqlSentence);          
            pps.setString(1, prestamo.getCodBarraDiccionario());
            pps.setString(2, prestamo.getCodEstudiante());
            pps.setString(3, prestamo.getIdBibliotecario()); 
            pps.setDate(4, prestamo.getFechaPrestamo());
            pps.setDate(5, prestamo.getFechaDevolucion());
            pps.setInt(6, prestamo.getCodPrestamoDiccionario());
            
            if(pps.executeUpdate() > 0){
               System.out.println("Realizo el update");
               return true;
            }
            else
                System.out.println("No existe un prestamo con ese codigo");
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, "No se pudo realizar el update del prestamo diccionario");
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
        String sqlSentence = "DELETE FROM Prestamo_Diccionario WHERE codPrestamoDiccionario = ?";
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
           IAlertBox alert = new AlertBox();
           alert.showAlert("Anuncio", "Multa", "Hay una multa pendiente, por lo tanto, el préstamo no se puede borrar");
        }

       return false;
    }   

   /**
     * Método que consulta todas las filas de esa entidad en la base de datos.
     * @return List
     * @throws Exception 
     */
    @Override
    public List<PrestamoDiccionario> readAllDAO() throws Exception {
       
        PreparedStatement pps;
        ResultSet rs;
        ArrayList<PrestamoDiccionario> prestamos = new ArrayList();
        
        try{
            pps = connection.getConnection().prepareStatement("SELECT * FROM Prestamo_Diccionario");
            rs = pps.executeQuery();
            
            while(rs.next()){
                PrestamoDiccionario prestamoTmp = new PrestamoDiccionario(rs.getString("codBarraDiccionario"), rs.getString("codEstudiante"),
                                                        rs.getString("idBibliotecario"), rs.getDate("fechaPrestamo"), rs.getDate("fechaDevolucion"));
                prestamoTmp.setCodPrestamoDiccionario(rs.getInt("codPrestamoDiccionario"));
                prestamos.add(prestamoTmp);
            }
            rs.close();
        }
        catch(SQLException e){
            System.out.println("No se realizo el readAll correctamente en prestamo diccionario");
        }
        catch(Exception e){
            System.out.println("Problema en el readAll de prestamo diccionario");
        }       
        return prestamos;
    }
}

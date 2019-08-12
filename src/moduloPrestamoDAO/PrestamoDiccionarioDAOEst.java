
package moduloPrestamoDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import modelo.ConnectionBD;
import moduloPrestamo.PrestamoDiccionarioEst1;
import vista.AlertBox;
import vista.IAlertBox;

/**
 * Clase que realiza el CRUD sobre la entidad Prestamo_Diccionario_Estudiante.
 * @author Julian
 * Fecha creación:10/08/2019 
 * Fecha ultima modificación:11/08/2019
 */
public class PrestamoDiccionarioDAOEst extends PrestamoRecursoDAOAbs<PrestamoDiccionarioEst1> {

    public PrestamoDiccionarioDAOEst(){
        connection = ConnectionBD.getInstance();
    }
    
    /**
     * Método que realiza los INSERT en la BD.
     * @param prestamo
     * @return boolean
     */
    @Override
    public boolean createDAO(PrestamoDiccionarioEst1 prestamo) {
        String sqlSentence = "INSERT INTO Prestamo_Diccionario_Estudiante (codBarraDiccionario, codEstudiante, idBibliotecario, fechaPrestamo, fechaDevolucion, devuelto)"
                             + " VALUES (?,?,?,?,?,?)";    
        PreparedStatement pps;
        
        try{
            pps = connection.getConnection().prepareStatement(sqlSentence);
            pps.setString(1, prestamo.getCodBarraDiccionario());
            pps.setString(2, prestamo.getCodEstudiante());
            pps.setString(3, prestamo.getIdBibliotecario()); 
            pps.setDate(4, prestamo.getFechaPrestamo());
            pps.setDate(5, prestamo.getFechaDevolucion());
            pps.setObject(6, prestamo.getDevuelto());
            
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
     * @return PrestamoDiccionarioEstudiante
     */
    @Override
    public PrestamoDiccionarioEst1 readDAO(int codigo) {
        Statement stmt;
        ResultSet rs;
        PrestamoDiccionarioEst1 prestamo = null;
        
        try{
            stmt = connection.getConnection().createStatement();
            rs = stmt.executeQuery("SELECT * FROM Prestamo_Diccionario_Estudiante WHERE codPrestDicEst = " + codigo +";");
           
            while(rs.next()){
                prestamo = new PrestamoDiccionarioEst1(rs.getString("codBarraDiccionario"), rs.getString("codEstudiante"), 
                                rs.getString("idBibliotecario"), rs.getDate("fechaPrestamo"), rs.getDate("fechaDevolucion"));
                prestamo.setCodPrestamoDiccionarioEst(rs.getInt("codPrestDicEst"));
                prestamo.setDevuelto(rs.getString("devuelto").charAt(0));
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
    

    @Override
    public boolean updateDAO(PrestamoDiccionarioEst1 prestamo) {
        String sqlSentence = "UPDATE Prestamo_Diccionario_Estudiante SET codBarraDiccionario = ?, codEstudiante = ?, idBibliotecario = ?, fechaPrestamo = ?, "
                             + "fechaDevolucion = ?,devuelto = ? WHERE codPrestDicEst = ?";
        PreparedStatement pps;
        try{
            pps = connection.getConnection().prepareStatement(sqlSentence);
            pps.setString(1, prestamo.getCodBarraDiccionario());
            pps.setString(2, prestamo.getCodEstudiante());
            pps.setString(3, prestamo.getIdBibliotecario()); 
            pps.setDate(4, prestamo.getFechaPrestamo());
            pps.setDate(5, prestamo.getFechaDevolucion());
            pps.setObject(6, prestamo.getDevuelto());
            
            if(pps.executeUpdate() > 0){
                System.out.println("Realizo el update");
                return true;
            }
            else
                System.out.println("No existe un prestamo con ese codigo");
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, "No se pudo realizar el update del prestamo diccionario de un estudiante");
        } 
        
        return false;
    }

    @Override
    public boolean deleteDAO(int pk) {
        String sqlSentence = "DELETE FROM Prestamo_Diccionario_Estudiante WHERE codPrestDicEst = ?";
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

    @Override
    public List<PrestamoDiccionarioEst1> readAllDAO() {
        PreparedStatement pps;
        ResultSet rs;
        ArrayList<PrestamoDiccionarioEst1> prestamos = new ArrayList();
        
        try{
            pps = connection.getConnection().prepareStatement("SELECT * FROM Prestamo_Diccionario_Estudiante");
            rs = pps.executeQuery();
            
            while(rs.next()){
                PrestamoDiccionarioEst1 prestamoTmp = new PrestamoDiccionarioEst1(rs.getString("codBarraDiccionario"), rs.getString("codEstudiante"), 
                                rs.getString("idBibliotecario"), rs.getDate("fechaPrestamo"), rs.getDate("fechaDevolucion"));
                prestamoTmp.setCodPrestamoDiccionarioEst(rs.getInt("codPrestDicEst"));
                prestamoTmp.setDevuelto(rs.getString("devuelto").charAt(0));
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

    /**
     * Método que realiza las consultas en la BD por medio de un código de barras.
     * @param codBarra : String
     * @return PrestamoDiccionarioEstudiante
     */
    @Override
    public int readCodigoDAO(String codBarra) {
        boolean existeRecurso = false;
        Statement stmt;
        ResultSet rs;
        PrestamoDiccionarioEst1 prestamo;
        
        try{
            stmt = connection.getConnection().createStatement();
            rs = stmt.executeQuery("SELECT * FROM Prestamo_Diccionario_Estudiante WHERE codBarraDiccionario = " + codBarra +";");
           
            while(rs.next()){
                prestamo = new PrestamoDiccionarioEst1(rs.getString("codBarraDiccionario"), rs.getString("codEstudiante"), 
                                rs.getString("idBibliotecario"), rs.getDate("fechaPrestamo"), rs.getDate("fechaDevolucion"));
                prestamo.setCodPrestamoDiccionarioEst(rs.getInt("codPrestDicEst"));
                prestamo.setDevuelto(rs.getString("devuelto").charAt(0));
            }
            rs.close();
            return 1;
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, "El préstamo de diccionario con ese codigo no existe");
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "No se pudo realizar la consulta");
        }
        return 0;
    }
    
}

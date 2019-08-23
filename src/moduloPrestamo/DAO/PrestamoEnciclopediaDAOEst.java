
package moduloPrestamo.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import modelo.ConnectionBD;
import moduloPrestamo.entitys.PrestamoEnciclopediaEst;

/**
 * CClase que realiza el CRUD sobre la entidad de prestamo_enciclopedia_estudiante.
 * @author Julian
 * Fecha creación:11/08/2019 
 * Fecha ultima modificación:11/08/2019
 */
public class PrestamoEnciclopediaDAOEst extends PrestamoRecursoDAOAbs<PrestamoEnciclopediaEst> {

    public PrestamoEnciclopediaDAOEst(){
        connection = ConnectionBD.getInstance();
    }
    
    @Override
    public boolean createDAO(PrestamoEnciclopediaEst prestamo) {
        String sqlSentence = "INSERT INTO Prestamo_Enciclopedia_Estudiante (codBarraEnciclopedia, codEstudiante, idBibliotecario, fechaPrestamo, fechaDevolucion, devuelto)"
                             + " VALUES (?,?,?,?,?,'no')";
       
        PreparedStatement pps;
        
        try{
            pps = connection.getConnection().prepareStatement(sqlSentence);
            pps.setString(1, prestamo.getCodBarraEnciclopedia());
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

    @Override
    public PrestamoEnciclopediaEst readDAO(int codigo) {
        Statement stmt;
        ResultSet rs;
        PrestamoEnciclopediaEst prestamo = null;
        
        try{
            stmt = connection.getConnection().createStatement();
            rs = stmt.executeQuery("SELECT * FROM Prestamo_Enciclopedia_Estudiante WHERE codPrestEncEst = " + codigo +";");
           
            while(rs.next()){
                prestamo = new PrestamoEnciclopediaEst(rs.getString("codBarraEnciclopedia"), rs.getString("codEstudiante"), 
                                rs.getString("idBibliotecario"), rs.getDate("fechaPrestamo"), rs.getDate("fechaDevolucion"));
                prestamo.setCodPrestamoEnciclopediaEst(rs.getInt("codPrestEncEst"));
                prestamo.setDevuelto(rs.getString("devuelto").charAt(0));
            }
            rs.close();
            return prestamo;
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, "El préstamo de enciclopedia con ese codigo no existe");
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "No se pudo realizar la consulta");
        }
        return prestamo;
    }
    

    @Override
    public boolean updateDAO(PrestamoEnciclopediaEst prestamo) {
        String sqlSentence;
        if(prestamo.getDevuelto() == 's'){
            sqlSentence = "UPDATE Prestamo_Enciclopedia_Estudiante SET codBarraEnciclopedia = ?, codEstudiante = ?, idBibliotecario = ?, fechaPrestamo = ?, "
                             + "fechaDevolucion = ?,devuelto = 'si' WHERE codPrestEncEst = ?";
        }else{
            sqlSentence = "UPDATE Prestamo_Enciclopedia_Estudiante SET codBarraEnciclopedia = ?, codEstudiante = ?, idBibliotecario = ?, fechaPrestamo = ?, "
                             + "fechaDevolucion = ?,devuelto = 'no' WHERE codPrestEncEst = ?";
        }
        
        PreparedStatement pps;
        
        try{       
            pps = connection.getConnection().prepareStatement(sqlSentence);
           
            pps.setString(1, prestamo.getCodBarraEnciclopedia());
            pps.setString(2, prestamo.getCodEstudiante());
            pps.setString(3, prestamo.getIdBibliotecario()); 
            pps.setDate(4, prestamo.getFechaPrestamo());
            pps.setDate(5, prestamo.getFechaDevolucion());
            pps.setInt(6, prestamo.getCodPrestamoEnciclopediaEst());
            
            if(pps.executeUpdate() > 0){
               System.out.println("Realizo el update");
               return true;
            }
            else
                System.out.println("No existe un prestamo con ese codigo");
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, "No se pudo realizar el update del prestamo enciclopedia");
        } 

        return false;
    }

    @Override
    public boolean deleteDAO(int pk) {
        String sqlSentence = "DELETE FROM Prestamo_Enciclopedia_Estudiante WHERE codPrestEncEst = ?";
        System.out.println(sqlSentence);
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
           System.err.println("No se pudo realizar el delete de prestamo enciclopedia");
        }
       return false;
    }

    @Override
    public List<PrestamoEnciclopediaEst> readAllDAO() {
        PreparedStatement pps;
        ResultSet rs;
        ArrayList<PrestamoEnciclopediaEst> prestamos = new ArrayList();
        
        try{
            pps = connection.getConnection().prepareStatement("SELECT * FROM Prestamo_Enciclopedia_Estudiante");
            rs = pps.executeQuery();
            
            while(rs.next()){
                PrestamoEnciclopediaEst prestamoTmp = new PrestamoEnciclopediaEst(rs.getString("codBarraEnciclopedia"), rs.getString("codEstudiante"), 
                                rs.getString("idBibliotecario"), rs.getDate("fechaPrestamo"), rs.getDate("fechaDevolucion"));
                prestamoTmp.setCodPrestamoEnciclopediaEst(rs.getInt("codPrestEncEst"));
                prestamoTmp.setDevuelto(rs.getString("devuelto").charAt(0));
                prestamos.add(prestamoTmp);
            }
            rs.close();
        }
        catch(SQLException e){
            System.out.println("No se realizo el readAll correctamente en prestamo enciclopedia");
        }
        catch(Exception e){
            System.out.println("Problema en el readAll de prestamo enciclopedia");
        }
        return prestamos;
    }

    @Override
    public int readCodigoDAO(String codBarra) {
        Statement stmt;
        ResultSet rs;
        int codPrestamo = -1;
        try{
            stmt = connection.getConnection().createStatement();
            rs = stmt.executeQuery("SELECT codPrestEncEst FROM Prestamo_Enciclopedia_Estudiante WHERE codBarraEnciclopedia = " + codBarra +";");
           
            while(rs.next()){
                codPrestamo =  rs.getInt(1);
            }
            rs.close();
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, "El préstamo de enciclopedia con ese codigo no existe");
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "No se pudo realizar la consulta");
        }
        return codPrestamo;
    }
    
}

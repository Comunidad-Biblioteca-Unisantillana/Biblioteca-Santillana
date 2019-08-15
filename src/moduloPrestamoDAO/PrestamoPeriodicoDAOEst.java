
package moduloPrestamoDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import modelo.ConnectionBD;
import moduloPrestamo.PrestamoPeriodicoEst;

/**
 * Clase que realiza el CRUD sobre la entidad prestamo_periodico_estudiante.
 *
 * @author Julian 
 * Fecha creación:11/08/2019 
 * Fecha ultima modificación:11/08/2019
 */
public class PrestamoPeriodicoDAOEst extends PrestamoRecursoDAOAbs<PrestamoPeriodicoEst>{

    public PrestamoPeriodicoDAOEst(){
        connection = ConnectionBD.getInstance();
    }
    
    @Override
    public boolean createDAO(PrestamoPeriodicoEst prestamo) {
        String sqlSentence = "INSERT INTO Prestamo_Periodico_Estudiante (codBarraPeriodico, codEstudiante, idBibliotecario, fechaPrestamo, fechaDevolucion, devuelto)"
                + " VALUES (?,?,?,?,?,?)";

        PreparedStatement pps;

        try {
            pps = connection.getConnection().prepareStatement(sqlSentence);
            pps.setString(1, prestamo.getCodBarraPeriodico());
            pps.setString(2, prestamo.getCodEstudiante());
            pps.setString(3, prestamo.getIdBibliotecario());
            pps.setDate(4, prestamo.getFechaPrestamo());
            pps.setDate(5, prestamo.getFechaDevolucion());
            pps.setObject(6,prestamo.getDevuelto());

            if (pps.executeUpdate() > 0) {
                System.out.println("Registro creado");
                return true;
            }

        } catch (SQLException e) {
            System.out.println("El registro no se pudo crear " + "\n" + e.getMessage());
        }
        return false;
    }

    @Override
    public PrestamoPeriodicoEst readDAO(int codigo) {
        Statement stmt;
        ResultSet rs;
        PrestamoPeriodicoEst prestamo = null;
        try{
            stmt = connection.getConnection().createStatement();
            rs = stmt.executeQuery("SELECT * FROM Prestamo_Periodico_Estudiante WHERE codPrestPeriodicoEst = " + codigo +";");
           
            while(rs.next()){
                prestamo = new PrestamoPeriodicoEst(rs.getString("codBarraPeriodico"), rs.getString("codEstudiante"), 
                                rs.getString("idBibliotecario"), rs.getDate("fechaPrestamo"), rs.getDate("fechaDevolucion"));
                prestamo.setCodPrestamoPeriodicoEst(rs.getInt("codPrestPeriodicoEst"));
                prestamo.setDevuelto(rs.getString("devuelto").charAt(0));
            }
            rs.close();
            return prestamo;
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, "El préstamo de periodico con ese codigo no existe");
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "No se pudo realizar la consulta");
        }
        return prestamo;
    }

    @Override
    public boolean updateDAO(PrestamoPeriodicoEst prestamo) {
        String sqlSentence = "UPDATE Prestamo_Periodico_Estudiante SET codBarraPeriodico = ?, codEstudiante = ?, idBibliotecario = ?, fechaPrestamo = ?, "
                             + "fechaDevolucion = ?,devuelto = ? WHERE codPrestPeriodicoEst = ?";
        PreparedStatement pps;
        
        try{       
            pps = connection.getConnection().prepareStatement(sqlSentence);
           
            pps.setString(1, prestamo.getCodBarraPeriodico());
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
            JOptionPane.showMessageDialog(null, "No se pudo realizar el update del prestamo periodico");
        }
        return false;
    }

    @Override
    public boolean deleteDAO(int pk) {
        String sqlSentence = "DELETE FROM Prestamo_Periodico_Estudiante WHERE codPrestPeriodicoEst = ?";
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
           System.err.println("No se pudo realizar el delete de prestamo Periodico");
        }
       return false;
    }

    @Override
    public List<PrestamoPeriodicoEst> readAllDAO() {
        PreparedStatement pps;
        ResultSet rs;
        ArrayList<PrestamoPeriodicoEst> prestamos = new ArrayList();
        
        try{
            pps = connection.getConnection().prepareStatement("SELECT * FROM Prestamo_Periodico_Estudiante");
            rs = pps.executeQuery();
            
            while(rs.next()){
                PrestamoPeriodicoEst prestamoTmp = new PrestamoPeriodicoEst(rs.getString("codBarraPeriodico"), rs.getString("codEstudiante"), 
                                rs.getString("idBibliotecario"), rs.getDate("fechaPrestamo"), rs.getDate("fechaDevolucion"));
                prestamoTmp.setCodPrestamoPeriodicoEst(rs.getInt("codPrestPeriodicoEst"));
                prestamoTmp.setDevuelto(rs.getString("devuelto").charAt(0));
                prestamos.add(prestamoTmp);
            }
            rs.close();
        }
        catch(SQLException e){
            System.out.println("No se realizo el readAll correctamente en prestamo periodico");
        }
        catch(Exception e){
            System.out.println("Problema en el readAll de prestamo periodico");
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
            rs = stmt.executeQuery("SELECT codPrestPeriodicoEst FROM Prestamo_Periodico_Estudiante WHERE codBarraPeriodico = " + codBarra +";");
           
            while(rs.next()){
                codPrestamo =  rs.getInt(1);
            }
            rs.close();
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, "El préstamo de periodico con ese codigo no existe");
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "No se pudo realizar la consulta");
        }
        return codPrestamo;
    }
    
}

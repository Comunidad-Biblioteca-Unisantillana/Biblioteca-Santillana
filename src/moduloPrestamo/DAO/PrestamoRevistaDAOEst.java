
package moduloPrestamo.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import modelo.ConnectionBD;
import moduloPrestamo.entitys.PrestamoRevistaEst;

/**
 * Clase que realiza el CRUD sobre la entidad prestamo_revista_estudiante.
 *
 * @author Julian 
 * Fecha creación:11/08/2019 
 * Fecha ultima modificación:12/08/2019
 */
public class PrestamoRevistaDAOEst extends PrestamoRecursoDAOAbs<PrestamoRevistaEst> {

    public PrestamoRevistaDAOEst(){
        connection = ConnectionBD.getInstance();
    }
    
    @Override
    public boolean createDAO(PrestamoRevistaEst prestamo) {
        String sqlSentence = "INSERT INTO Prestamo_Revista_Estudiante (codBarraRevista, codEstudiante, idBibliotecario, fechaPrestamo, fechaDevolucion, devuelto)"
                + " VALUES (?,?,?,?,?,'no')";

        PreparedStatement pps;

        try {
            pps = connection.getConnection().prepareStatement(sqlSentence);
            pps.setString(1, prestamo.getCodBarraRevista());
            pps.setString(2, prestamo.getCodEstudiante());
            pps.setString(3, prestamo.getIdBibliotecario());
            pps.setDate(4, prestamo.getFechaPrestamo());
            pps.setDate(5, prestamo.getFechaDevolucion());

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
    public PrestamoRevistaEst readDAO(int codigo) {
        Statement stmt;
        ResultSet rs;
        PrestamoRevistaEst prestamo = null;
        try{
            stmt = connection.getConnection().createStatement();
            rs = stmt.executeQuery("SELECT * FROM Prestamo_Revista_Estudiante WHERE codPrestRevistaEst = " + codigo +";");
           
            while(rs.next()){
                prestamo = new PrestamoRevistaEst(rs.getString("codBarraRevista"), rs.getString("codEstudiante"), 
                                rs.getString("idBibliotecario"), rs.getDate("fechaPrestamo"), rs.getDate("fechaDevolucion"));
                prestamo.setCodPrestamoRevistaEst(rs.getInt("codPrestRevistaEst"));
                prestamo.setDevuelto(rs.getString("devuelto").charAt(0));
            }
            rs.close();
            return prestamo;
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, "El préstamo de revista con ese codigo no existe");
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "No se pudo realizar la consulta");
        }
        return prestamo;
    }
    

    @Override
    public boolean updateDAO(PrestamoRevistaEst prestamo) {
        String sqlSentence;
        if(prestamo.getDevuelto() == 's'){
            sqlSentence = "UPDATE Prestamo_Revista_Estudiante SET codBarraRevista = ?, codEstudiante = ?, idBibliotecario = ?, fechaPrestamo = ?, "
                             + "fechaDevolucion = ?,devuelto = 'si' WHERE codPrestRevistaEst = ?";
        }else{
            sqlSentence = "UPDATE Prestamo_Revista_Estudiante SET codBarraRevista = ?, codEstudiante = ?, idBibliotecario = ?, fechaPrestamo = ?, "
                             + "fechaDevolucion = ?,devuelto = 'no' WHERE codPrestRevistaEst = ?";
        }
        
        PreparedStatement pps;
        
        try{       
            pps = connection.getConnection().prepareStatement(sqlSentence);
           
            pps.setString(1, prestamo.getCodBarraRevista());
            pps.setString(2, prestamo.getCodEstudiante());
            pps.setString(3, prestamo.getIdBibliotecario()); 
            pps.setDate(4, prestamo.getFechaPrestamo());
            pps.setDate(5, prestamo.getFechaDevolucion());
            
            if(pps.executeUpdate() > 0){
               System.out.println("Realizo el update");
               return true;
            }
            else
                System.out.println("No existe un prestamo con ese codigo");
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, "No se pudo realizar el update del prestamo revista");
        }
        return false;
    }

    @Override
    public boolean deleteDAO(int pk) {
        String sqlSentence = "DELETE FROM Prestamo_Revista_Estudiante WHERE codPrestRevistaEst = ?";
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
           System.err.println("No se pudo realizar el delete de prestamo revista");
        }
       return false;
    }

    @Override
    public List<PrestamoRevistaEst> readAllDAO(){
        PreparedStatement pps;
        ResultSet rs;
        ArrayList<PrestamoRevistaEst> prestamos = new ArrayList();
        
        try{
            pps = connection.getConnection().prepareStatement("SELECT * FROM Prestamo_Revista_Estudiante");
            rs = pps.executeQuery();
            
            while(rs.next()){
                PrestamoRevistaEst prestamoTmp = new PrestamoRevistaEst(rs.getString("codBarraRevista"), rs.getString("codEstudiante"), 
                                rs.getString("idBibliotecario"), rs.getDate("fechaPrestamo"), rs.getDate("fechaDevolucion"));
                prestamoTmp.setCodPrestamoRevistaEst(rs.getInt("codPrestRevistaEst"));
                prestamoTmp.setDevuelto(rs.getString("devuelto").charAt(0));
                prestamos.add(prestamoTmp);
            }
            rs.close();
        }
        catch(SQLException e){
            System.out.println("No se realizo el readAll correctamente en prestamo revista");
        }
        catch(Exception e){
            System.out.println("Problema en el readAll de prestamo revista");
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
            rs = stmt.executeQuery("SELECT codPrestRevistaEst FROM Prestamo_Revista_Estudiante WHERE codBarraRevista = " + codBarra +";");
           
            while(rs.next()){
                codPrestamo = rs.getInt(1);
            }
            rs.close();
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, "El préstamo de revista con ese codigo no existe");
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "No se pudo realizar la consulta");
        }
        return codPrestamo;
    }
    
}

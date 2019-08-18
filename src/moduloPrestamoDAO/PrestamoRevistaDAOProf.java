
package moduloPrestamoDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import modelo.ConnectionBD;
import moduloPrestamo.PrestamoRevistaProf;

/**
 * Clase que realiza el CRUD sobre la entidad prestamo_revista_profesor.
 *
 * @author Julian 
 * Fecha creación:11/08/2019 
 * Fecha ultima modificación:11/08/2019
 */
public class PrestamoRevistaDAOProf extends PrestamoRecursoDAOAbs<PrestamoRevistaProf> {
    
    public PrestamoRevistaDAOProf(){
        connection = ConnectionBD.getInstance();
    }

    @Override
    public boolean createDAO(PrestamoRevistaProf prestamo) {
        String sqlSentence = "INSERT INTO Prestamo_Revista_Profesor (codBarraRevista, idProfesor, idBibliotecario, fechaPrestamo, fechaDevolucion, devuelto)"
                + " VALUES (?,?,?,?,?,?)";

        PreparedStatement pps;

        try {
            pps = connection.getConnection().prepareStatement(sqlSentence);
            pps.setString(1, prestamo.getCodBarraRevista());
            pps.setString(2, prestamo.getIdProfesor());
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
    public PrestamoRevistaProf readDAO(int codigo) {
        Statement stmt;
        ResultSet rs;
        PrestamoRevistaProf prestamo = null;
        try{
            stmt = connection.getConnection().createStatement();
            rs = stmt.executeQuery("SELECT * FROM Prestamo_Revista_Profesor WHERE codPrestRevistaProf = " + codigo +";");
           
            while(rs.next()){
                prestamo = new PrestamoRevistaProf(rs.getString("codBarraRevista"), rs.getString("idProfesor"), 
                                rs.getString("idBibliotecario"), rs.getDate("fechaPrestamo"), rs.getDate("fechaDevolucion"));
                prestamo.setCodPrestamoRevistaProf(rs.getInt("codPrestRevistaProf"));
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
    public boolean updateDAO(PrestamoRevistaProf prestamo) {
        String sqlSentence = "UPDATE Prestamo_Revista_Profesor SET codBarraRevista = ?, idProfesor = ?, idBibliotecario = ?, fechaPrestamo = ?, "
                             + "fechaDevolucion = ?,devuelto = ? WHERE codPrestRevistaProf = ?";
        PreparedStatement pps;
        
        try{       
            pps = connection.getConnection().prepareStatement(sqlSentence);
           
            pps.setString(1, prestamo.getCodBarraRevista());
            pps.setString(2, prestamo.getIdProfesor());
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
            JOptionPane.showMessageDialog(null, "No se pudo realizar el update del prestamo revista");
        }
        return false;
    }

    @Override
    public boolean deleteDAO(int pk) {
        String sqlSentence = "DELETE FROM Prestamo_Revista_Profesor WHERE codPrestRevistaProf = ?";
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
    public List<PrestamoRevistaProf> readAllDAO(){
        PreparedStatement pps;
        ResultSet rs;
        ArrayList<PrestamoRevistaProf> prestamos = new ArrayList();
        
        try{
            pps = connection.getConnection().prepareStatement("SELECT * FROM Prestamo_Revista_Profesor");
            rs = pps.executeQuery();
            
            while(rs.next()){
                PrestamoRevistaProf prestamoTmp = new PrestamoRevistaProf(rs.getString("codBarraRevista"), rs.getString("idProfesor"), 
                                rs.getString("idBibliotecario"), rs.getDate("fechaPrestamo"), rs.getDate("fechaDevolucion"));
                prestamoTmp.setCodPrestamoRevistaProf(rs.getInt("codPrestRevistaProf"));
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
            rs = stmt.executeQuery("SELECT codPrestRevistaProf FROM Prestamo_Revista_Profesor WHERE codBarraRevista = " + codBarra +";");
           
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

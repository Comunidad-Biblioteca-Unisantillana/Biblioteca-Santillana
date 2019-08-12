
package moduloPrestamoDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import modelo.ConnectionBD;
import moduloPrestamo.PrestamoMapaProf;

/**
 * Clase que realiza el CRUD sobre la entidad prestamo_mapa_profesor.
 *
 * @author Julian 
 * Fecha creación:11/08/2019 
 * Fecha ultima modificación:11/08/2019
 */
public class PrestamoMapaDAOProf extends PrestamoRecursoDAOAbs<PrestamoMapaProf>{

    public PrestamoMapaDAOProf(){
        connection = ConnectionBD.getInstance();
    }
    
    @Override
    public boolean createDAO(PrestamoMapaProf prestamo) {
        String sqlSentence = "INSERT INTO Prestamo_Mapa_Profesor (codBarraMapa, idProfesor, idBibliotecario, fechaPrestamo, fechaDevolucion, devuelto)"
                + " VALUES (?,?,?,?,?,?)";

        PreparedStatement pps;

        try {
            pps = connection.getConnection().prepareStatement(sqlSentence);
            pps.setString(1, prestamo.getCodBarraMapa());
            pps.setString(2, prestamo.getIdProfesor());
            pps.setString(3, prestamo.getIdBibliotecario());
            pps.setDate(4, prestamo.getFechaPrestamo());
            pps.setDate(5, prestamo.getFechaDevolucion());
            pps.setObject(6, prestamo.getDevuelto());

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
    public PrestamoMapaProf readDAO(int codigo) {
        Statement stmt;
        ResultSet rs;
        PrestamoMapaProf prestamo = null;
        
        try{
            stmt = connection.getConnection().createStatement();
            rs = stmt.executeQuery("SELECT * FROM Prestamo_Mapa_Profesor WHERE codPrestMapaProf = " + codigo +";");
           
            while(rs.next()){
                prestamo = new PrestamoMapaProf(rs.getString("codBarraMapa"), rs.getString("idProfesor"), 
                                rs.getString("idBibliotecario"), rs.getDate("fechaPrestamo"), rs.getDate("fechaDevolucion"));
                prestamo.setCodPrestamoMapaProf(rs.getInt("codPrestMapaProf"));
                prestamo.setDevuelto(rs.getString("devuelto").charAt(0));
            }
            rs.close();
            return prestamo;
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, "El préstamo de mapa con ese codigo no existe");
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "No se pudo realizar la consulta");
        }
        return prestamo;
    }
    

    @Override
    public boolean updateDAO(PrestamoMapaProf prestamo) {
        String sqlSentence = "UPDATE Prestamo_Mapa_Profesor SET codBarraMapa = ?, idProfesor = ?, idBibliotecario = ?, fechaPrestamo = ?, "
                             + "fechaDevolucion = ?,devuelto = ? WHERE codPrestMapaProf = ?";
        PreparedStatement pps;
        
        try{       
            pps = connection.getConnection().prepareStatement(sqlSentence);
           
            pps.setString(1, prestamo.getCodBarraMapa());
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
            JOptionPane.showMessageDialog(null, "No se pudo realizar el update del prestamo mapa");
        }
        return false;
    }

    @Override
    public boolean deleteDAO(int pk) {
        String sqlSentence = "DELETE FROM Prestamo_Mapa_Profesor WHERE codPrestMapaProf = ?";
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
           System.err.println("No se pudo realizar el delete de prestamo mapa");
        }
       return false;
    }

    @Override
    public List<PrestamoMapaProf> readAllDAO(){
        PreparedStatement pps;
        ResultSet rs;
        ArrayList<PrestamoMapaProf> prestamos = new ArrayList();
        
        try{
            pps = connection.getConnection().prepareStatement("SELECT * FROM Prestamo_Mapa_Profesor");
            rs = pps.executeQuery();
            
            while(rs.next()){
                PrestamoMapaProf prestamoTmp = new PrestamoMapaProf(rs.getString("codBarraMapa"), rs.getString("idProfesor"), 
                                rs.getString("idBibliotecario"), rs.getDate("fechaPrestamo"), rs.getDate("fechaDevolucion"));
                prestamoTmp.setCodPrestamoMapaProf(rs.getInt("codPrestMapaProf"));
                prestamoTmp.setDevuelto(rs.getString("devuelto").charAt(0));
                prestamos.add(prestamoTmp);
            }
            rs.close();
        }
        catch(SQLException e){
            System.out.println("No se realizo el readAll correctamente en prestamo mapa");
        }
        catch(Exception e){
            System.out.println("Problema en el readAll de prestamo mapa");
        }
        return prestamos;
    }

    @Override
    public int readCodigoDAO(String codBarra) {
        boolean existeRecurso = false;
        Statement stmt;
        ResultSet rs;
        PrestamoMapaProf prestamo;
        
        try{
            stmt = connection.getConnection().createStatement();
            rs = stmt.executeQuery("SELECT * FROM Prestamo_Mapa_Profesor WHERE codBarraMapa = " + codBarra +";");
           
            while(rs.next()){
                prestamo = new PrestamoMapaProf(rs.getString("codBarraMapa"), rs.getString("idProfesor"), 
                                rs.getString("idBibliotecario"), rs.getDate("fechaPrestamo"), rs.getDate("fechaDevolucion"));
                prestamo.setCodPrestamoMapaProf(rs.getInt("codPrestMapaProf"));
                prestamo.setDevuelto(rs.getString("devuelto").charAt(0));
            }
            rs.close();
            return 1;
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, "El préstamo de mapa con ese codigo no existe");
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "No se pudo realizar la consulta");
        }
        return 0;
    }
    
}

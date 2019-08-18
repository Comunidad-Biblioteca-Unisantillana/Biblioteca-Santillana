package moduloPrestamo.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import modelo.ConnectionBD;
import moduloPrestamo.entitys.PrestamoEnciclopediaProf;

/**
 * Clase que realiza el CRUD sobre las entidad prestamo_enciclopedia_profesor.
 * @author Julian
 * Fecha creación:11/08/2019 
 * Fecha ultima modificación:11/08/2019
 */
public class PrestamoEnciclopediaDAOProf extends PrestamoRecursoDAOAbs<PrestamoEnciclopediaProf>{

    public PrestamoEnciclopediaDAOProf(){
        connection = ConnectionBD.getInstance();
    }
    
    @Override
    public boolean createDAO(PrestamoEnciclopediaProf prestamo) {
        String sqlSentence = "INSERT INTO Prestamo_Enciclopedia_Profesor (codBarraEnciclopedia, idProfesor, idBibliotecario, fechaPrestamo, fechaDevolucion, devuelto)"
                             + " VALUES (?,?,?,?,?,'no')";
       
        PreparedStatement pps;
        
        try{
            pps = connection.getConnection().prepareStatement(sqlSentence);
            pps.setString(1, prestamo.getCodBarraEnciclopedia());
            pps.setString(2, prestamo.getIdProfesor());
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
    public PrestamoEnciclopediaProf readDAO(int codigo) {
        Statement stmt;
        ResultSet rs;
        PrestamoEnciclopediaProf prestamo = null;
        
        try{
            stmt = connection.getConnection().createStatement();
            rs = stmt.executeQuery("SELECT * FROM Prestamo_Enciclopedia_Profesor WHERE codPrestEncProf = " + codigo +";");
           
            while(rs.next()){
                prestamo = new PrestamoEnciclopediaProf(rs.getString("codBarraEnciclopedia"), rs.getString("idProfesor"), 
                                rs.getString("idBibliotecario"), rs.getDate("fechaPrestamo"), rs.getDate("fechaDevolucion"));
                prestamo.setCodPrestamoEnciclopedioProf(rs.getInt("codPrestEncProf"));
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
    public boolean updateDAO(PrestamoEnciclopediaProf prestamo) {
        String sqlSentence;
        if(prestamo.getDevuelto() == 's'){
            sqlSentence = "UPDATE Prestamo_Enciclopedia_Profesor SET codBarraEnciclopedia = ?, idProfesor = ?, idBibliotecario = ?, fechaPrestamo = ?, "
                             + "fechaDevolucion = ?,devuelto = 'si' WHERE codPrestEncProf = ?";
        }else{
            sqlSentence = "UPDATE Prestamo_Enciclopedia_Profesor SET codBarraEnciclopedia = ?, idProfesor = ?, idBibliotecario = ?, fechaPrestamo = ?, "
                             + "fechaDevolucion = ?,devuelto = 'no' WHERE codPrestEncProf = ?";
        }
        
        PreparedStatement pps;
        
        try{       
            pps = connection.getConnection().prepareStatement(sqlSentence);
           
            pps.setString(1, prestamo.getCodBarraEnciclopedia());
            pps.setString(2, prestamo.getIdProfesor());
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
            JOptionPane.showMessageDialog(null, "No se pudo realizar el update del prestamo enciclopedia");
        } 

        return false;
    }

    @Override
    public boolean deleteDAO(int pk) {
        String sqlSentence = "DELETE FROM Prestamo_Enciclopedia_Profesor WHERE codPrestEncProf = ?";
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
    public List<PrestamoEnciclopediaProf> readAllDAO(){
        PreparedStatement pps;
        ResultSet rs;
        ArrayList<PrestamoEnciclopediaProf> prestamos = new ArrayList();
        
        try{
            pps = connection.getConnection().prepareStatement("SELECT * FROM Prestamo_Enciclopedia_Profesor");
            rs = pps.executeQuery();
            
            while(rs.next()){
                PrestamoEnciclopediaProf prestamoTmp = new PrestamoEnciclopediaProf(rs.getString("codBarraEnciclopedia"), rs.getString("idProfesor"), 
                                rs.getString("idBibliotecario"), rs.getDate("fechaPrestamo"), rs.getDate("fechaDevolucion"));
                prestamoTmp.setCodPrestamoEnciclopedioProf(rs.getInt("codPrestEncProf"));
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
            rs = stmt.executeQuery("SELECT codPrestEncProf FROM Prestamo_Enciclopedia_Profesor WHERE codBarraEnciclopedia = " + codBarra +";");
           
            while(rs.next()){
                codPrestamo = rs.getInt(1);
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

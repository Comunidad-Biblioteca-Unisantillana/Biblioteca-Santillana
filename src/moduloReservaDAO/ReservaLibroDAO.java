
package moduloReservaDAO;

import entitysRecursos.ReservaLibro;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import modelo.ConnectionBD;
import modeloDAO.InterfaceCRUD;

/**
 *
 * @author Camilo
 */
public class ReservaLibroDAO implements InterfaceCRUD<ReservaLibro>{
    
     private ConnectionBD connection;

    public ReservaLibroDAO(){
        connection = ConnectionBD.getInstance();
    }
    
    /**
     * Método que realiza los INSERT en la BD.
     * @param reserva
     * @return boolean
     */
    @Override
    public boolean createDAO(ReservaLibro reserva) {
        
        String sqlSentence = "INSERT INTO ReservaLibroColeccionGeneral (codBarraLibro, codEstudiante, idBibliotecario, fechaReserva, fechaLimiteReserva)"
                             + " VALUES (?,?,?,?,?)";    
        PreparedStatement pps;
        
        try{
            pps = connection.getConnection().prepareStatement(sqlSentence);
            pps.setString(1, reserva.getCodBarraLibro());
            pps.setString(2, reserva.getCodEstudiante());
            pps.setString(3, reserva.getIdBibliotecario()); 
            pps.setDate(4, reserva.getFechaReserva());
            pps.setDate(5, reserva.getFechaLimite());
            
            if(pps.executeUpdate() > 0){
                System.out.println("Reserva creada");
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
     * @return PrestamoRevista
     */
    @Override
    public ReservaLibro readDAO(int codigo){
        Statement stmt;
        ResultSet rs;
        ReservaLibro reserva = null;
        
        try{
            stmt = connection.getConnection().createStatement();
            rs = stmt.executeQuery("SELECT * FROM ReservaLibroColeccionGeneral WHERE codBarraLibro = '" + String.valueOf(codigo) +"';");
           
            while(rs.next()){
                reserva = new ReservaLibro(rs.getString("codBarraLibro"), rs.getString("codEstudiante"), 
                                rs.getString("idBibliotecario"), rs.getDate("fechaReserva"), rs.getDate("fechaLimiteReserva"));
                reserva.setCodReservaColGral(rs.getInt("codPrestamoRevista"));
            }
            rs.close();
            return reserva;
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, "El préstamo de revista con ese codigo no existe");
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "No se pudo realizar la consulta");
        }
        return reserva;
    }
    
    /**
     * Método que realiza las actualizaciones en la BD.
     * @param reserva
     * @return : boolean
     */
    @Override
    public boolean updateDAO(ReservaLibro reserva) {
        String sqlSentence = "UPDATE ReservaLibroColeccionGeneral SET codBarraRevista = ?, codEstudiante = ?, idBibliotecario = ?, fechaPrestamo = ?, "
                             + "fechaDevolucion = ? WHERE codReservaColGral = ?";
        PreparedStatement pps;
        
        try{       
            pps = connection.getConnection().prepareStatement(sqlSentence);          
            pps.setString(1, reserva.getCodBarraLibro());
            pps.setString(2, reserva.getCodEstudiante());
            pps.setString(3, reserva.getIdBibliotecario()); 
            pps.setDate(4, reserva.getFechaReserva());
            pps.setDate(5, reserva.getFechaLimite());
            pps.setInt(6, reserva.getCodReservaColGral());
            
            if(pps.executeUpdate() > 0){
               System.out.println("Realizo el update");
               return true;
            }
            else
                System.out.println("No existe una reserva con ese codigo");
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, "No se pudo realizar el update de la reserva");
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
        String sqlSentence = "DELETE FROM ReservaLibroColeccionGeneral WHERE codReservaColGral = ?";
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

   /**
     * Método que consulta todas las filas de esa entidad en la base de datos.
     * @return List
     * @throws Exception 
     */
    @Override
    public List<ReservaLibro> readAllDAO() throws Exception {
       
        PreparedStatement pps;
        ResultSet rs;
        ArrayList<ReservaLibro> reservas = new ArrayList();
        
        try{
            pps = connection.getConnection().prepareStatement("SELECT * FROM ReservaLibroColeccionGeneral");
            rs = pps.executeQuery();
            
            while(rs.next()){
                ReservaLibro reservaTmp=  new ReservaLibro(rs.getString("codBarraLibro"), rs.getString("codEstudiante"),
                                                        rs.getString("idBibliotecario"), rs.getDate("fechaReserva"), rs.getDate("fechaLimiteReserva"));
                reservaTmp.setCodReservaColGral(rs.getInt("codPrestamoRevista"));
                reservas.add(reservaTmp);
            }
            rs.close();
        }
        catch(SQLException e){
            System.out.println("No se realizo el readAll correctamente en reserva");
        }
        catch(Exception e){
            System.out.println("Problema en el readAll de prestamo reserva");
        }     
        return reservas;
    }
}

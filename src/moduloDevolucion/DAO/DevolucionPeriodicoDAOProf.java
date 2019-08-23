package moduloDevolucion.DAO;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import modelo.ConnectionBD;
import moduloDevolucion.entitys.DevolucionPeriodicoEst;
import moduloDevolucion.entitys.DevolucionPeriodicoProf;

/**
 * @author Camilo Jaramillo
 * @version 1.0
 * @created 04-ago.-2019 10:37:49 a. m.
 */
public class DevolucionPeriodicoDAOProf extends DevolucionRecursoDAOAbs<DevolucionPeriodicoProf> {

    public DevolucionPeriodicoDAOProf() {
        connection = ConnectionBD.getInstance();
    }

    @Override
    public boolean createDAO(DevolucionPeriodicoProf devolucion) {
        String sqlSentence = "INSERT INTO Devolucion_Periodico_Profesor (codPrestPeriodicoProf, idBibliotecario, fechaDevolucion, estadoDevolucion) VALUES (?,?,CURRENT_DATE,?)";
        PreparedStatement pps;

        try {
            pps = connection.getConnection().prepareStatement(sqlSentence);
            pps.setInt(1, devolucion.getCodPrestamoPeriodicoProf());
            pps.setString(2, devolucion.getIdBibliotecario());
            pps.setString(3, devolucion.getEstadoDevolucion());

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
    public boolean deleteDAO(int codigo) {
        String sqlSentence = "DELETE FROM Devolucion_Periodico_Profesor WHERE codDevPeriodicoProf = ?";
        PreparedStatement pps;

        try {
            pps = connection.getConnection().prepareStatement(sqlSentence);
            pps.setInt(1, codigo);

            if (pps.executeUpdate() > 0) {
                System.out.println("Hizo el delete");
                return true;
            } else {
                System.out.println("No existe una devolucion de periodico de profesor con ese codigo");
            }
        } catch (SQLException e) {
            System.err.println("No se pudo realizar el delete de devolucion periodico del profesor");
        }
        return false;
    }

    @Override
    public List<DevolucionPeriodicoProf> readAllDAO() {
        PreparedStatement pps;
        ResultSet rs;
        ArrayList<DevolucionPeriodicoProf> devoluciones = new ArrayList();
        
        try{
            pps = connection.getConnection().prepareStatement("SELECT * FROM Devolucion_Periodico_Profesor");
            rs = pps.executeQuery();
            
            while(rs.next()){
                DevolucionPeriodicoProf devolucionTmp = new DevolucionPeriodicoProf(rs.getInt("codPrestPeriodicoProf"), rs.getString("idBibliotecario"), 
                        rs.getDate("fechaDevolucion"), rs.getString("estadoDevolucion"));
                devolucionTmp.setCodDevolucionPeriodicoProf(rs.getInt("codDevPeriodicoProf"));
                devoluciones.add(devolucionTmp);
            }
            rs.close();
        }
        catch(SQLException e){
            System.out.println("No se realizo el readAll correctamente sobre devolucion de periodico del profesor");
        }
        catch(Exception e){
            System.out.println("Problema en el readAll");
        }      
        return devoluciones;
    }

    @Override
    public DevolucionPeriodicoProf readDAO(int codigo) {
        Statement stmt;
        ResultSet rs;
        DevolucionPeriodicoProf devolucion = null;
        
        try{
            stmt = connection.getConnection().createStatement();
            rs = stmt.executeQuery("SELECT * FROM Devolucion_Periodico_Profesor WHERE codDevPeriodicoProf = " + codigo +";");
           
            while(rs.next()){
                devolucion = new DevolucionPeriodicoProf(rs.getInt("codPrestPeriodicoProf"), rs.getString("idBibliotecario"), rs.getDate("fechaDevolucion"),
                                   rs.getString("estadoDevolucion"));
                devolucion.setCodDevolucionPeriodicoProf(rs.getInt("codDevPeriodicoProf"));
            }
            rs.close();
            return devolucion;
        }
        catch(SQLException e){
            System.out.println("La devolución de periodico de profesor con ese codigo no existe");
        }
        catch(Exception e){
            System.out.println("No se pudo realizar la consulta");
        }  
        return devolucion;
    }

    @Override
    public boolean updateDAO(DevolucionPeriodicoProf devolucion) {
        String sqlSentence = "UPDATE Devolucion_Periodico_Profesor SET codPrestPeriodicoProf = ?, idBibliotecario = ?, fechaDevolucion = ?, estadoDevolucion = ?"
                + " WHERE codDevPeriodicoProf = ?";
        PreparedStatement pps;
        
        try{       
            pps = connection.getConnection().prepareStatement(sqlSentence);
           
            pps.setInt(1, devolucion.getCodPrestamoPeriodicoProf());
            pps.setString(2, devolucion.getIdBibliotecario());
            pps.setDate(3, (Date) devolucion.getFechaDevolucion()); 
            pps.setString(4, devolucion.getEstadoDevolucion());
            pps.setInt(5, devolucion.getCodDevolucionPeriodicoProf());
            
            if(pps.executeUpdate() > 0){
               System.out.println("Realizo el update");
               return true;
            }
            else
                System.out.println("No existe una devolucion de periodico de profesor con ese codigo");
        }
        catch(SQLException e){
            System.out.println("No se pudo realizar el update de devolucion de periodico del profesor");
        } 
        return false;
    }

    
}

package moduloDevolucion.DAO;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import general.modelo.ConnectionBD;
import moduloDevolucion.entitys.DevolucionPeriodicoEst;

/**
 * @author Camilo Jaramillo
 * @version 1.0
 * @created 04-ago.-2019 10:37:49 a. m.
 */
public class DevolucionPeriodicoDAOEst extends DevolucionRecursoDAOAbs<DevolucionPeriodicoEst> {

    public DevolucionPeriodicoDAOEst() {
        connection = ConnectionBD.getInstance();
    }

    @Override
    public boolean createDAO(DevolucionPeriodicoEst devolucion) {
        String sqlSentence = "INSERT INTO Devolucion_Periodico_Estudiante (codPrestPeriodicoEst, idBibliotecario, fechaDevolucion, estadoDevolucion) VALUES (?,?,CURRENT_DATE,?)";
        PreparedStatement pps;

        try {
            pps = connection.getConnection().prepareStatement(sqlSentence);
            pps.setInt(1, devolucion.getCodPrestamoPeriodicoEst());
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
        String sqlSentence = "DELETE FROM Devolucion_Periodico_Estudiante WHERE codDevPeriodicoEst = ?";
        PreparedStatement pps;

        try {
            pps = connection.getConnection().prepareStatement(sqlSentence);
            pps.setInt(1, codigo);

            if (pps.executeUpdate() > 0) {
                System.out.println("Hizo el delete");
                return true;
            } else {
                System.out.println("No existe una devolucion de periodico de estudiante con ese codigo");
            }
        } catch (SQLException e) {
            System.err.println("No se pudo realizar el delete de devolucion periodico del estudiante");
        }
        return false;
    }

    @Override
    public List<DevolucionPeriodicoEst> readAllDAO() {
        PreparedStatement pps;
        ResultSet rs;
        ArrayList<DevolucionPeriodicoEst> devoluciones = new ArrayList();
        
        try{
            pps = connection.getConnection().prepareStatement("SELECT * FROM Devolucion_Periodico_Estudiante");
            rs = pps.executeQuery();
            
            while(rs.next()){
                DevolucionPeriodicoEst devolucionTmp = new DevolucionPeriodicoEst(rs.getInt("codPrestPeriodicoEst"), rs.getString("idBibliotecario"), 
                        rs.getDate("fechaDevolucion"), rs.getString("estadoDevolucion"));
                devolucionTmp.setCodDevolucionPeriodicoEst(rs.getInt("codDevPeriodicoEst"));
                devoluciones.add(devolucionTmp);
            }
            rs.close();
        }
        catch(SQLException e){
            System.out.println("No se realizo el readAll correctamente sobre devolucion de periodico del estudiante");
        }
        catch(Exception e){
            System.out.println("Problema en el readAll");
        }      
        return devoluciones;
    }

    @Override
    public DevolucionPeriodicoEst readDAO(int codigo) {
        Statement stmt;
        ResultSet rs;
        DevolucionPeriodicoEst devolucion = null;
        
        try{
            stmt = connection.getConnection().createStatement();
            rs = stmt.executeQuery("SELECT * FROM Devolucion_Periodico_Estudiante WHERE codDevPeriodicoEst = " + codigo +";");
           
            while(rs.next()){
                devolucion = new DevolucionPeriodicoEst(rs.getInt("codPrestPeriodicoEst"), rs.getString("idBibliotecario"), rs.getDate("fechaDevolucion"),
                                   rs.getString("estadoDevolucion"));
                devolucion.setCodDevolucionPeriodicoEst(rs.getInt("codDevPeriodicoEst"));
            }
            rs.close();
            return devolucion;
        }
        catch(SQLException e){
            System.out.println("La devolución de periodico de estudiante con ese codigo no existe");
        }
        catch(Exception e){
            System.out.println("No se pudo realizar la consulta");
        }  
        return devolucion;
    }

    @Override
    public boolean updateDAO(DevolucionPeriodicoEst devolucion) {
        String sqlSentence = "UPDATE Devolucion_Periodico_Estudiante SET codPrestPeriodicoEst = ?, idBibliotecario = ?, fechaDevolucion = ?, estadoDevolucion = ?"
                + " WHERE codDevPeriodicoEst = ?";
        PreparedStatement pps;
        
        try{       
            pps = connection.getConnection().prepareStatement(sqlSentence);
           
            pps.setInt(1, devolucion.getCodPrestamoPeriodicoEst());
            pps.setString(2, devolucion.getIdBibliotecario());
            pps.setDate(3, (Date) devolucion.getFechaDevolucion()); 
            pps.setString(4, devolucion.getEstadoDevolucion());
            pps.setInt(5, devolucion.getCodDevolucionPeriodicoEst());
            
            if(pps.executeUpdate() > 0){
               System.out.println("Realizo el update");
               return true;
            }
            else
                System.out.println("No existe una devolucion de periodico de estudiante con ese codigo");
        }
        catch(SQLException e){
            System.out.println("No se pudo realizar el update de devolucion de periodico del estudiante");
        } 
        return false;
    }

}

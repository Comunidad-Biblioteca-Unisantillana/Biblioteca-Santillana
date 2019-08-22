package moduloDevolucion.DAO;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import modelo.ConnectionBD;
import moduloDevolucion.entitys.DevolucionMapaEst;

/**
 * @author Camilo Jaramillo
 * @version 1.0
 * @created 04-ago.-2019 10:37:42 a. m.
 */
public class DevolucionMapaDAOEst extends DevolucionRecursoDAOAbs<DevolucionMapaEst> {

    public DevolucionMapaDAOEst() {
        connection = ConnectionBD.getInstance();
    }

    @Override
    public boolean createDAO(DevolucionMapaEst devolucion) {
        String sqlSentence = "INSERT INTO Devolucion_Mapa_Estudiante (codPrestMapaEst, idBibliotecario, fechaDevolucion, estadoDevolucion) VALUES (?,?,CURRENT_DATE,?)";
        PreparedStatement pps;

        try {
            pps = connection.getConnection().prepareStatement(sqlSentence);
            pps.setInt(1, devolucion.getCodPrestamoMapaEst());
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
        String sqlSentence = "DELETE FROM Devolucion_Mapa_Estudiante WHERE codDevMapaEst = ?";
        PreparedStatement pps;

        try {
            pps = connection.getConnection().prepareStatement(sqlSentence);
            pps.setInt(1,codigo);

            if (pps.executeUpdate() > 0) {
                System.out.println("Hizo el delete");
                return true;
            } else {
                System.out.println("No existe una devolucion de mapa de estudiante con ese codigo");
            }
        } catch (SQLException e) {
            System.err.println("No se pudo realizar el delete de devolucion de mapa del estudiante");
        }
        return false;
    }

    @Override
    public List<DevolucionMapaEst> readAllDAO() {
        PreparedStatement pps;
        ResultSet rs;
        ArrayList<DevolucionMapaEst> devoluciones = new ArrayList();
        
        try{
            pps = connection.getConnection().prepareStatement("SELECT * FROM Devolucion_Mapa_Estudiante");
            rs = pps.executeQuery();
            
            while(rs.next()){
                DevolucionMapaEst devolucionTmp = new DevolucionMapaEst(rs.getInt("codPrestMapaEst"), rs.getString("idBibliotecario"), 
                        rs.getDate("fechaDevolucion"), rs.getString("estadoDevolucion"));
                devolucionTmp.setCodDevolucionMapaEst(rs.getInt("codDevMapaEst"));
                devoluciones.add(devolucionTmp);
            }
            rs.close();
        }
        catch(SQLException e){
            System.out.println("No se realizo el readAll correctamente sobre la devolucion de mapa del estudiante");
        }
        catch(Exception e){
            System.out.println("Problema en el readAll");
        }   
        return devoluciones;
    }

    @Override
    public DevolucionMapaEst readDAO(int codigo) {
        Statement stmt;
        ResultSet rs;
        DevolucionMapaEst devolucion = null;
        
        try{
            stmt = connection.getConnection().createStatement();
            rs = stmt.executeQuery("SELECT * FROM Devolucion_Mapa_Estudiante WHERE codDevMapaEst = " + codigo +";");
           
            while(rs.next()){
                devolucion = new DevolucionMapaEst(rs.getInt("codPrestMapaEst"), rs.getString("idBibliotecario"), rs.getDate("fechaDevolucion"),
                                   rs.getString("estadoDevolucion"));
                devolucion.setCodDevolucionMapaEst(rs.getInt("codDevMapaEst"));
            }
            rs.close();
            return devolucion;
        }
        catch(SQLException e){
            System.out.println("La devolución de mapa de estudiante con ese codigo no existe");
        }
        catch(Exception e){
            System.out.println("No se pudo realizar la consulta");
        }
        return devolucion;
    }

    @Override
    public boolean updateDAO(DevolucionMapaEst devolucion) {
        String sqlSentence = "UPDATE Devolucion_Mapa_Estudiante SET codPrestMapaEst = ?, idBibliotecario = ?, fechaDevolucion = ?, estadoDevolucion = ?"
                + " WHERE codDevMapaEst = ?";
        PreparedStatement pps;
        
        try{       
            pps = connection.getConnection().prepareStatement(sqlSentence);
           
            pps.setInt(1, devolucion.getCodPrestamoMapaEst());
            pps.setString(2, devolucion.getIdBibliotecario());
            pps.setDate(3, (Date) devolucion.getFechaDevolucion()); 
            pps.setString(4, devolucion.getEstadoDevolucion());
            
            if(pps.executeUpdate() > 0){
               System.out.println("Realizo el update");
               return true;
            }
            else
                System.out.println("No existe una devolucion de estudiante con ese codigo");
        }
        catch(SQLException e){
            System.out.println("No se pudo realizar el update de devolucion de mapa del estudiante");
        } 
        return false;
    }

}

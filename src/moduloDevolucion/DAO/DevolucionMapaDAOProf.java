package moduloDevolucion.DAO;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import general.modelo.ConnectionBD;
import moduloDevolucion.entitys.DevolucionMapaEst;
import moduloDevolucion.entitys.DevolucionMapaProf;

/**
 * @author Camilo Jaramillo
 * @version 1.0
 * @created 04-ago.-2019 10:37:42 a. m.
 */
public class DevolucionMapaDAOProf extends DevolucionRecursoDAOAbs<DevolucionMapaProf> {

    public DevolucionMapaDAOProf() {
        connection = ConnectionBD.getInstance();
    }

    @Override
    public boolean createDAO(DevolucionMapaProf devolucion) {
        String sqlSentence = "INSERT INTO Devolucion_Mapa_Profesor (codPrestMapaProf, idBibliotecario, fechaDevolucion, estadoDevolucion) VALUES (?,?,CURRENT_DATE,?)";
        PreparedStatement pps;

        try {
            pps = connection.getConnection().prepareStatement(sqlSentence);
            pps.setInt(1, devolucion.getCodPrestamoMapaProf());
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
        String sqlSentence = "DELETE FROM Devolucion_Mapa_Profesor WHERE codDevMapaProf = ?";
        PreparedStatement pps;

        try {
            pps = connection.getConnection().prepareStatement(sqlSentence);
            pps.setInt(1,codigo);

            if (pps.executeUpdate() > 0) {
                System.out.println("Hizo el delete");
                return true;
            } else {
                System.out.println("No existe una devolucion de mapa de profesor con ese codigo");
            }
        } catch (SQLException e) {
            System.err.println("No se pudo realizar el delete de devolucion de mapa del profesor");
        }
        return false;
    }

    @Override
    public List<DevolucionMapaProf> readAllDAO() {
        PreparedStatement pps;
        ResultSet rs;
        ArrayList<DevolucionMapaProf> devoluciones = new ArrayList();
        
        try{
            pps = connection.getConnection().prepareStatement("SELECT * FROM Devolucion_Mapa_Profesor");
            rs = pps.executeQuery();
            
            while(rs.next()){
                DevolucionMapaProf devolucionTmp = new DevolucionMapaProf(rs.getInt("codPrestMapaProf"), rs.getString("idBibliotecario"), 
                        rs.getDate("fechaDevolucion"), rs.getString("estadoDevolucion"));
                devolucionTmp.setCodDevolucionMapaProf(rs.getInt("codDevMapaProf"));
                devoluciones.add(devolucionTmp);
            }
            rs.close();
        }
        catch(SQLException e){
            System.out.println("No se realizo el readAll correctamente sobre la devolucion de mapa del profesor");
        }
        catch(Exception e){
            System.out.println("Problema en el readAll");
        }   
        return devoluciones;
    }

    @Override
    public DevolucionMapaProf readDAO(int codigo) {
        Statement stmt;
        ResultSet rs;
        DevolucionMapaProf devolucion = null;
        
        try{
            stmt = connection.getConnection().createStatement();
            rs = stmt.executeQuery("SELECT * FROM Devolucion_Mapa_Profesor WHERE codDevMapaProf = " + codigo +";");
           
            while(rs.next()){
                devolucion = new DevolucionMapaProf(rs.getInt("codPrestMapaProf"), rs.getString("idBibliotecario"), rs.getDate("fechaDevolucion"),
                                   rs.getString("estadoDevolucion"));
                devolucion.setCodDevolucionMapaProf(rs.getInt("codDevMapaProf"));
            }
            rs.close();
            return devolucion;
        }
        catch(SQLException e){
            System.out.println("La devolución de mapa de profesor con ese codigo no existe");
        }
        catch(Exception e){
            System.out.println("No se pudo realizar la consulta");
        }
        return devolucion;
    }

    @Override
    public boolean updateDAO(DevolucionMapaProf devolucion) {
        String sqlSentence = "UPDATE Devolucion_Mapa_Profesor SET codPrestMapaProf = ?, idBibliotecario = ?, fechaDevolucion = ?, estadoDevolucion = ?"
                + " WHERE codDevMapaProf = ?";
        PreparedStatement pps;
        
        try{       
            pps = connection.getConnection().prepareStatement(sqlSentence);
           
            pps.setInt(1, devolucion.getCodPrestamoMapaProf());
            pps.setString(2, devolucion.getIdBibliotecario());
            pps.setDate(3, (Date) devolucion.getFechaDevolucion()); 
            pps.setString(4, devolucion.getEstadoDevolucion());
            pps.setInt(5, devolucion.getCodDevolucionMapaProf());
            
            if(pps.executeUpdate() > 0){
               System.out.println("Realizo el update");
               return true;
            }
            else
                System.out.println("No existe una devolucion de profesor con ese codigo");
        }
        catch(SQLException e){
            System.out.println("No se pudo realizar el update de devolucion de mapa del profesor");
        } 
        return false;
    }

}

package moduloDevolucion.DAO;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import general.modelo.ConnectionBD;
import moduloDevolucion.entitys.DevolucionDiccionarioProf;

/**
 * @author Camilo Jaramillo
 * @version 1.0
 * @created 04-ago.-2019 10:37:18 a. m.
 */
public class DevolucionDiccionarioDAOProf extends DevolucionRecursoDAOAbs<DevolucionDiccionarioProf> {

    public DevolucionDiccionarioDAOProf() {
        connection = ConnectionBD.getInstance();
    }

    @Override
    public boolean createDAO(DevolucionDiccionarioProf devolucion) {

        String sqlSentence = "INSERT INTO Devolucion_Diccionario_Profesor (codPrestDicProf, idBibliotecario, fechaDevolucion, estadoDevolucion) VALUES (?,?,CURRENT_DATE,?)";
        PreparedStatement pps;

        try {
            pps = connection.getConnection().prepareStatement(sqlSentence);
            pps.setInt(1, devolucion.getCodPrestamoDiccionarioProf());
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
        String sqlSentence = "DELETE FROM Devolucion_Diccionario_Profesor WHERE codDevDicProf = ?";
        PreparedStatement pps;

        try {
            pps = connection.getConnection().prepareStatement(sqlSentence);
            pps.setInt(1, codigo);

            if (pps.executeUpdate() > 0) {
                System.out.println("Hizo el delete");
                return true;
            } else {
                System.out.println("No existe una devolucion diccionario de profesor con ese codigo");
            }
        } catch (SQLException e) {
            System.err.println("No se pudo realizar el delete de devolucion de diccionario del profesor ");
        }
        return false;
    }

    @Override
    public List<DevolucionDiccionarioProf> readAllDAO() {
        PreparedStatement pps;
        ResultSet rs;
        ArrayList<DevolucionDiccionarioProf> devoluciones = new ArrayList();

        try {
            pps = connection.getConnection().prepareStatement("SELECT * FROM Devolucion_Diccionario_Profesor");
            rs = pps.executeQuery();

            while (rs.next()) {
                DevolucionDiccionarioProf devolucionTmp = new DevolucionDiccionarioProf(rs.getInt("codPrestDicProf"), rs.getString("idBibliotecario"), rs.getDate("fechaDevolucion"),
                        rs.getString("estadoDevolucion"));
                devolucionTmp.setCodDevolucionDiccionarioProf(rs.getInt("codDevDicProf"));
                devoluciones.add(devolucionTmp);
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println("No se realizo el readAll correctamente sobre devolucion diccionario de profesor");
        } catch (Exception e) {
            System.out.println("Problema en el readAll");
        }
        return devoluciones;
    }

    @Override
    public DevolucionDiccionarioProf readDAO(int codigo) {
        Statement stmt;
        ResultSet rs;
        DevolucionDiccionarioProf devolucion = null;

        try {
            stmt = connection.getConnection().createStatement();
            rs = stmt.executeQuery("SELECT * FROM Devolucion_Diccionario_Profesor WHERE codDevDicProf = " + codigo + ";");

            while (rs.next()) {
                devolucion = new DevolucionDiccionarioProf(rs.getInt("codPrestDicProf"), rs.getString("idBibliotecario"), rs.getDate("fechaDevolucion"),
                        rs.getString("estadoDevolucion"));
                devolucion.setCodDevolucionDiccionarioProf(rs.getInt("codDevDicProf"));
            }
            rs.close();
            return devolucion;
        } catch (SQLException e) {
            System.out.println("La devolución de diccionario de profesor con ese codigo no existe");
        } catch (Exception e) {
            System.out.println("No se pudo realizar la consulta");
        }
        return devolucion;
    }

    @Override
    public boolean updateDAO(DevolucionDiccionarioProf devolucion) {
        String sqlSentence = "UPDATE Devolucion_Diccionario_Profesor SET codPrestDicProf = ?, idBibliotecario = ?, fechaDevolucion = ?, estadoDevolucion = ?"
                + " WHERE codDevDicProf = ?";
        PreparedStatement pps;

        try {
            pps = connection.getConnection().prepareStatement(sqlSentence);
            pps.setInt(1, devolucion.getCodPrestamoDiccionarioProf());
            pps.setString(2, devolucion.getIdBibliotecario());
            pps.setDate(3, (Date) devolucion.getFechaDevolucion());
            pps.setString(4, devolucion.getEstadoDevolucion());
            pps.setInt(5, devolucion.getCodDevolucionDiccionarioProf());

            if (pps.executeUpdate() > 0) {
                System.out.println("Realizo el update");
                return true;
            } else {
                System.out.println("No existe una devolucion con ese codigo");
            }
        } catch (SQLException e) {
            System.out.println("No se pudo realizar el update de devolucion diccionario del profesor");
        }
        return false;
    }

}

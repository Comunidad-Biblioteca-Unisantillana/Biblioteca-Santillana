package moduloDevolucion.DAO;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import general.modelo.ConnectionBD;
import moduloDevolucion.entitys.DevolucionEnciclopediaProf;

/**
 * @author Camilo Jaramillo
 * @version 1.0
 * @created 04-ago.-2019 10:37:27 a. m.
 */
public class DevolucionEnciclopediaDAOProf extends DevolucionRecursoDAOAbs<DevolucionEnciclopediaProf> {

    public DevolucionEnciclopediaDAOProf() {
        connection = ConnectionBD.getInstance();
    }

    @Override
    public boolean createDAO(DevolucionEnciclopediaProf devolucion) {

        String sqlSentence = "INSERT INTO Devolucion_Enciclopedia_Profesor (codPrestEncProf, idBibliotecario, fechaDevolucion, estadoDevolucion) VALUES (?,?,CURRENT_DATE,?)";
        PreparedStatement pps;

        try {
            pps = connection.getConnection().prepareStatement(sqlSentence);
            pps.setInt(1, devolucion.getCodPrestamoEnciclopediaProf());
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
        String sqlSentence = "DELETE FROM Devolucion_Enciclopedia_Profesor WHERE codDevEncProf = ?";
        PreparedStatement pps;

        try {
            pps = connection.getConnection().prepareStatement(sqlSentence);
            pps.setInt(1, codigo);

            if (pps.executeUpdate() > 0) {
                System.out.println("Hizo el delete");
                return true;
            } else {
                System.out.println("No existe una devolucion de profesor con ese codigo");
            }
        } catch (SQLException e) {
            System.err.println("No se pudo realizar el delete de devolucion enciclopedia del profesor");
        }
        return false;
    }

    @Override
    public List<DevolucionEnciclopediaProf> readAllDAO() {
        PreparedStatement pps;
        ResultSet rs;
        ArrayList<DevolucionEnciclopediaProf> devoluciones = new ArrayList();

        try {
            pps = connection.getConnection().prepareStatement("SELECT * FROM Devolucion_Enciclopedia_Profesor");
            rs = pps.executeQuery();

            while (rs.next()) {
                DevolucionEnciclopediaProf devolucionTmp = new DevolucionEnciclopediaProf(rs.getInt("codPrestEncProf"), rs.getString("idBibliotecario"),
                        rs.getDate("fechaDevolucion"), rs.getString("estadoDevolucion"));
                devolucionTmp.setCodDevolucionEnciclopediaProf(rs.getInt("codDevEncProf"));
                devoluciones.add(devolucionTmp);
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println("No se realizo el readAll correctamente sobre devolucion enciclopedia del profesor");
        } catch (Exception e) {
            System.out.println("Problema en el readAll");
        }
        return devoluciones;
    }

    @Override
    public DevolucionEnciclopediaProf readDAO(int codigo) {
        Statement stmt;
        ResultSet rs;
        DevolucionEnciclopediaProf devolucion = null;

        try {
            stmt = connection.getConnection().createStatement();
            rs = stmt.executeQuery("SELECT * FROM Devolucion_Enciclopedia_Profesor WHERE codDevEncProf = " + codigo + ";");

            while (rs.next()) {
                devolucion = new DevolucionEnciclopediaProf(rs.getInt("codPrestEncProf"), rs.getString("idBibliotecario"), rs.getDate("fechaDevolucion"),
                        rs.getString("estadoDevolucion"));
                devolucion.setCodDevolucionEnciclopediaProf(rs.getInt("codDevEncProf"));
            }
            rs.close();
            return devolucion;
        } catch (SQLException e) {
            System.out.println("La devolución de enciclopedia de profesor con ese codigo no existe");
        } catch (Exception e) {
            System.out.println("No se pudo realizar la consulta");
        }
        return devolucion;
    }

    @Override
    public boolean updateDAO(DevolucionEnciclopediaProf devolucion) {
        String sqlSentence = "UPDATE Devolucion_Enciclopedia_Profesor SET codPrestEncProf = ?, idBibliotecario = ?, fechaDevolucion = ?, estadoDevolucion = ?"
                + " WHERE codDevEncProf = ?";
        PreparedStatement pps;

        try {
            pps = connection.getConnection().prepareStatement(sqlSentence);
            pps.setInt(1, devolucion.getCodPrestamoEnciclopediaProf());
            pps.setString(2, devolucion.getIdBibliotecario());
            pps.setDate(3, (Date) devolucion.getFechaDevolucion());
            pps.setString(4, devolucion.getEstadoDevolucion());
            pps.setInt(5, devolucion.getCodDevolucionEnciclopediaProf());

            if (pps.executeUpdate() > 0) {
                System.out.println("Realizo el update");
                return true;
            } else {
                System.out.println("No existe una devolucion de profesor con ese codigo");
            }
        } catch (SQLException e) {
            System.out.println("No se pudo realizar el update de devolucion enciclopedia del profesor");
        }
        return false;
    }

}

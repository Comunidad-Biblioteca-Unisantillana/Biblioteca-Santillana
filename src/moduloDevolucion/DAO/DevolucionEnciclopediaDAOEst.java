package moduloDevolucion.DAO;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import modelo.ConnectionBD;
import moduloDevolucion.entitys.DevolucionEnciclopediaEst;

/**
 * @author Camilo Jaramillo
 * @version 1.0
 * @created 04-ago.-2019 10:37:26 a. m.
 */
public class DevolucionEnciclopediaDAOEst extends DevolucionRecursoDAOAbs<DevolucionEnciclopediaEst> {

    public DevolucionEnciclopediaDAOEst() {
        connection = ConnectionBD.getInstance();
    }

    @Override
    public boolean createDAO(DevolucionEnciclopediaEst devolucion) {

        String sqlSentence = "INSERT INTO Devolucion_Enciclopedia_Estudiante (codPrestEncEst, idBibliotecario, fechaDevolucion, estadoDevolucion) VALUES (?,?,CURRENT_DATE,?)";
        PreparedStatement pps;

        try {
            pps = connection.getConnection().prepareStatement(sqlSentence);
            pps.setInt(1, devolucion.getCodPrestamoEnciclopediaEst());
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
        String sqlSentence = "DELETE FROM Devolucion_Enciclopedia_Estudiante WHERE codDevEncEst = ?";
        PreparedStatement pps;

        try {
            pps = connection.getConnection().prepareStatement(sqlSentence);
            pps.setInt(1, codigo);

            if (pps.executeUpdate() > 0) {
                System.out.println("Hizo el delete");
                return true;
            } else {
                System.out.println("No existe una devolucion de estudiante con ese codigo");
            }
        } catch (SQLException e) {
            System.err.println("No se pudo realizar el delete de devolucion enciclopedia del estudiante");
        }
        return false;
    }

    @Override
    public List<DevolucionEnciclopediaEst> readAllDAO() {
        PreparedStatement pps;
        ResultSet rs;
        ArrayList<DevolucionEnciclopediaEst> devoluciones = new ArrayList();

        try {
            pps = connection.getConnection().prepareStatement("SELECT * FROM Devolucion_Enciclopedia_Estudiante");
            rs = pps.executeQuery();

            while (rs.next()) {
                DevolucionEnciclopediaEst devolucionTmp = new DevolucionEnciclopediaEst(rs.getInt("codPrestEncEst"), rs.getString("idBibliotecario"),
                        rs.getDate("fechaDevolucion"), rs.getString("estadoDevolucion"));
                devolucionTmp.setCodDevolucionEnciclopediaEst(rs.getInt("codDevEncEst"));
                devoluciones.add(devolucionTmp);
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println("No se realizo el readAll correctamente sobre devolucion enciclopedia del estudiante");
        } catch (Exception e) {
            System.out.println("Problema en el readAll");
        }
        return devoluciones;
    }

    @Override
    public DevolucionEnciclopediaEst readDAO(int codigo) {
        Statement stmt;
        ResultSet rs;
        DevolucionEnciclopediaEst devolucion = null;

        try {
            stmt = connection.getConnection().createStatement();
            rs = stmt.executeQuery("SELECT * FROM Devolucion_Enciclopedia_Estudiante WHERE codDevEncEst = " + codigo + ";");

            while (rs.next()) {
                devolucion = new DevolucionEnciclopediaEst(rs.getInt("codPrestEncEst"), rs.getString("idBibliotecario"), rs.getDate("fechaDevolucion"),
                        rs.getString("estadoDevolucion"));
                devolucion.setCodDevolucionEnciclopediaEst(rs.getInt("codDevEncEst"));
            }
            rs.close();
            return devolucion;
        } catch (SQLException e) {
            System.out.println("La devolución de enciclopedia de estudiante con ese codigo no existe");
        } catch (Exception e) {
            System.out.println("No se pudo realizar la consulta");
        }
        return devolucion;
    }

    @Override
    public boolean updateDAO(DevolucionEnciclopediaEst devolucion) {
        String sqlSentence = "UPDATE Devolucion_Enciclopedia_Estudiante SET codPrestEncEst = ?, idBibliotecario = ?, fechaDevolucion = ?, estadoDevolucion = ?"
                + " WHERE codDevEncEst = ?";
        PreparedStatement pps;

        try {
            pps = connection.getConnection().prepareStatement(sqlSentence);
            pps.setInt(1, devolucion.getCodPrestamoEnciclopediaEst());
            pps.setString(2, devolucion.getIdBibliotecario());
            pps.setDate(3, (Date) devolucion.getFechaDevolucion());
            pps.setString(4, devolucion.getEstadoDevolucion());
            pps.setInt(5, devolucion.getCodDevolucionEnciclopediaEst());

            if (pps.executeUpdate() > 0) {
                System.out.println("Realizo el update");
                return true;
            } else {
                System.out.println("No existe una devolucion de estudiante con ese codigo");
            }
        } catch (SQLException e) {
            System.out.println("No se pudo realizar el update de devolucion enciclopedia del estudiante");
        }
        return false;
    }

}

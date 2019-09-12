package moduloDevolucion.DAO;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import general.modelo.ConnectionBD;
import moduloDevolucion.entitys.DevolucionLibroEst;

/**
 * @author Camilo Jaramillo
 * @creado: 04/08/2019
 * @author Miguel Fernandez
 * @modificado: 08/09/2019
 */
public class DevolucionLibroDAOEst extends DevolucionRecursoDAOAbs<DevolucionLibroEst> {

    public DevolucionLibroDAOEst() {
        connection = ConnectionBD.getInstance();
    }

    @Override
    public boolean createDAO(DevolucionLibroEst devolucion) {
        String sqlSentence = "INSERT INTO Devolucion_Libro_Estudiante (codPrestLibroEst, idBibliotecario, fechaDevolucion, estadoDevolucion) VALUES (?,?,CURRENT_DATE,?)";
        PreparedStatement pps;

        try {
            pps = connection.getConnection().prepareStatement(sqlSentence);
            pps.setInt(1, devolucion.getCodPrestamoLibroEst());
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
        String sqlSentence = "DELETE FROM Devolucion_Libro_Estudiante WHERE codDevLibroEst = ?";
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
            System.out.println("No se puede realizar el delete de devolucion de libro del estudiante");
        }
        return false;
    }

    @Override
    public List<DevolucionLibroEst> readAllDAO() {
        PreparedStatement pps;
        ResultSet rs;
        ArrayList<DevolucionLibroEst> devoluciones = new ArrayList();

        try {
            pps = connection.getConnection().prepareStatement("SELECT * FROM Devolucion_Libro_Estudiante");
            rs = pps.executeQuery();

            while (rs.next()) {
                DevolucionLibroEst devolucionTmp = new DevolucionLibroEst(rs.getInt("codPrestLibroEst"), rs.getString("idBibliotecario"),
                        rs.getString("estadoDevolucion"));
                devolucionTmp.setFechaDevolucion(rs.getDate("fechaDevolucion"));
                devolucionTmp.setCodDevolucionLibroEst(rs.getInt("codDevLibroEst"));
                devoluciones.add(devolucionTmp);
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println("No se realizo el readAll correctamente");
        } catch (Exception e) {
            System.out.println("Problema en el readAll");
        }
        return devoluciones;
    }

    @Override
    public DevolucionLibroEst readDAO(int codigo) {
        Statement stmt;
        ResultSet rs;
        DevolucionLibroEst devolucion = null;

        try {
            stmt = connection.getConnection().createStatement();
            rs = stmt.executeQuery("SELECT * FROM Devolucion_Libro_Estudiante WHERE codDevLibroEst = " + codigo + ";");

            while (rs.next()) {
                devolucion = new DevolucionLibroEst(rs.getInt("codPrestLibroEst"), rs.getString("idBibliotecario"),
                        rs.getString("estadoDevolucion"));
                devolucion.setFechaDevolucion(rs.getDate("fechaDevolucion"));
                devolucion.setCodDevolucionLibroEst(rs.getInt("codDevLibroEst"));
            }
            rs.close();
            return devolucion;
        } catch (SQLException e) {
            System.out.println("La devolución de estudiante con ese codigo no existe");
        } catch (Exception e) {
            System.out.println("No se pudo realizar la consulta");
        }
        return devolucion;
    }

    @Override
    public boolean updateDAO(DevolucionLibroEst devolucion) {
        String sqlSentence = "UPDATE Devolucion_Libro_Estudiante SET codPrestLibroEst = ?, idBibliotecario = ?, fechaDevolucion = ?, estadoDevolucion = ?"
                + " WHERE codDevLibroEst = ?";
        PreparedStatement pps;

        try {
            pps = connection.getConnection().prepareStatement(sqlSentence);
            pps.setInt(1, devolucion.getCodPrestamoLibroEst());
            pps.setString(2, devolucion.getIdBibliotecario());
            pps.setDate(3, (Date) devolucion.getFechaDevolucion());
            pps.setString(4, devolucion.getEstadoDevolucion());
            pps.setInt(5, devolucion.getCodDevolucionLibroEst());

            if (pps.executeUpdate() > 0) {
                System.out.println("Realizo el update");
                return true;
            } else {
                System.out.println("No existe una devolucion de estudiante con ese codigo");
            }
        } catch (SQLException e) {
            System.out.println("No se pudo realizar el update");
        }
        return false;
    }

    /**
     * el método realiza la consulta del código de la devolución de un libro del
     * estudiante en la BD, por medio del código del préstamo.
     *
     * @param codPrestamo
     * @return codDevolucion
     */
    @Override
    public int readCodigoDAO(int codPrestamo) {
        Statement stmt;
        ResultSet rs;
        int codDevolucion = -1;

        try {
            stmt = connection.getConnection().createStatement();
            rs = stmt.executeQuery("SELECT codDevLibroEst FROM Devolucion_Libro_Estudiante "
                    + "WHERE codPrestLibroEst = '" + codPrestamo + "';");

            while (rs.next()) {
                codDevolucion = rs.getInt("codDevLibroEst");
            }

            rs.close();
        } catch (SQLException e) {
            System.out.println("Error al realizar el readCodigoDAO, en devolución libro estudiante");
        }

        return codDevolucion;
    }

}

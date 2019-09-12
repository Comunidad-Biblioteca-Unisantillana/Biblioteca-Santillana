package moduloDevolucion.DAO;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import general.modelo.ConnectionBD;
import moduloDevolucion.entitys.DevolucionLibroProf;

/**
 * @author Camilo Jaramillo
 * @creado: 04/08/2019
 * @author Miguel Fernandez
 * @modificado: 08/09/2019
 */
public class DevolucionLibroDAOProf extends DevolucionRecursoDAOAbs<DevolucionLibroProf> {

    public DevolucionLibroDAOProf() {
        connection = ConnectionBD.getInstance();
    }

    @Override
    public boolean createDAO(DevolucionLibroProf devolucion) {
        String sqlSentence = "INSERT INTO Devolucion_Libro_Profesor (codPrestLibroProf, idBibliotecario, fechaDevolucion, estadoDevolucion) VALUES (?,?,CURRENT_DATE,?)";
        PreparedStatement pps;

        try {
            pps = connection.getConnection().prepareStatement(sqlSentence);
            pps.setInt(1, devolucion.getCodPrestamoLibroProf());
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
        String sqlSentence = "DELETE FROM Devolucion_Libro_Profesor WHERE codDevLibroProf = ?";
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
            System.out.println("No se puede realizar el delete de devolucion de libro del profesor");
        }
        return false;
    }

    @Override
    public List<DevolucionLibroProf> readAllDAO() {
        PreparedStatement pps;
        ResultSet rs;
        ArrayList<DevolucionLibroProf> devoluciones = new ArrayList();

        try {
            pps = connection.getConnection().prepareStatement("SELECT * FROM Devolucion_Libro_Profesor");
            rs = pps.executeQuery();

            while (rs.next()) {
                DevolucionLibroProf devolucionTmp = new DevolucionLibroProf(rs.getInt("codPrestLibroProf"), rs.getString("idBibliotecario"),
                        rs.getString("estadoDevolucion"));
                devolucionTmp.setFechaDevolucion(rs.getDate("fechaDevolucion"));
                devolucionTmp.setCodDevolucionLibroProf(rs.getInt("codDevLibroProf"));
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
    public DevolucionLibroProf readDAO(int codigo) {
        Statement stmt;
        ResultSet rs;
        DevolucionLibroProf devolucion = null;

        try {
            stmt = connection.getConnection().createStatement();
            rs = stmt.executeQuery("SELECT * FROM Devolucion_Libro_Profesor WHERE codDevLibroProf = " + codigo + ";");

            while (rs.next()) {
                devolucion = new DevolucionLibroProf(rs.getInt("codPrestLibroProf"), rs.getString("idBibliotecario"),
                        rs.getString("estadoDevolucion"));
                devolucion.setFechaDevolucion(rs.getDate("fechaDevolucion"));
                devolucion.setCodDevolucionLibroProf(rs.getInt("codDevLibroProf"));
            }
            rs.close();
            return devolucion;
        } catch (SQLException e) {
            System.out.println("La devolución de profesor con ese codigo no existe");
        } catch (Exception e) {
            System.out.println("No se pudo realizar la consulta");
        }
        return devolucion;
    }

    @Override
    public boolean updateDAO(DevolucionLibroProf devolucion) {
        String sqlSentence = "UPDATE Devolucion_Libro_Profesor SET codPrestLibroProf = ?, idBibliotecario = ?, fechaDevolucion = ?, estadoDevolucion = ?"
                + " WHERE codDevLibroProf = ?";
        PreparedStatement pps;

        try {
            pps = connection.getConnection().prepareStatement(sqlSentence);
            pps.setInt(1, devolucion.getCodPrestamoLibroProf());
            pps.setString(2, devolucion.getIdBibliotecario());
            pps.setDate(3, (Date) devolucion.getFechaDevolucion());
            pps.setString(4, devolucion.getEstadoDevolucion());
            pps.setInt(5, devolucion.getCodDevolucionLibroProf());

            if (pps.executeUpdate() > 0) {
                System.out.println("Realizo el update");
                return true;
            } else {
                System.out.println("No existe una devolucion de profesor con ese codigo");
            }
        } catch (SQLException e) {
            System.out.println("No se pudo realizar el update");
        }
        return false;
    }

    /**
     * el método realiza la consulta del código de la devolución de un libro del
     * profesor en la BD, por medio del código del préstamo.
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
            rs = stmt.executeQuery("SELECT codDevLibroProf FROM Devolucion_Libro_Profesor "
                    + "WHERE codPrestLibroProf = '" + codPrestamo + "';");

            while (rs.next()) {
                codDevolucion = rs.getInt("codDevLibroProf");
            }

            rs.close();
        } catch (SQLException e) {
            System.out.println("Error al realizar el readCodigoDAO, en devolución libro profesor");
        }

        return codDevolucion;
    }

}

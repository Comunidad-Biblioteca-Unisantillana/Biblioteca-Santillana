package moduloDevolucion.DAO;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import general.modelo.ConnectionBD;
import moduloDevolucion.entitys.DevolucionRevistaProf;

/**
 * @author Camilo Jaramillo
 * @creado: 04/08/2019
 * @author Miguel Fernández
 * @modificado: 08/09/2019
 */
public class DevolucionRevistaDAOProf extends DevolucionRecursoDAOAbs<DevolucionRevistaProf> {

    public DevolucionRevistaDAOProf() {
        connection = ConnectionBD.getInstance();
    }

    @Override
    public boolean createDAO(DevolucionRevistaProf devolucion) {
        String sqlSentence = "INSERT INTO Devolucion_Revista_Profesor (codPrestRevistaProf, idBibliotecario, fechaDevolucion, estadoDevolucion) VALUES (?,?,CURRENT_DATE,?)";
        PreparedStatement pps;

        try {
            pps = connection.getConnection().prepareStatement(sqlSentence);
            pps.setInt(1, devolucion.getCodPrestamoRevistaProf());
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
        String sqlSentence = "DELETE FROM Devolucion_Revista_Profesor WHERE codDevRevistaProf = ?";
        PreparedStatement pps;

        try {
            pps = connection.getConnection().prepareStatement(sqlSentence);
            pps.setInt(1, codigo);

            if (pps.executeUpdate() > 0) {
                System.out.println("Hizo el delete");
                return true;
            } else {
                System.out.println("No existe una devolucion de revista de profesor con ese codigo");
            }
        } catch (SQLException e) {
            System.err.println("No se pudo realizar el delete de devolucion de revista del profesor");
        }
        return false;
    }

    @Override
    public List<DevolucionRevistaProf> readAllDAO() {
        PreparedStatement pps;
        ResultSet rs;
        ArrayList<DevolucionRevistaProf> devoluciones = new ArrayList();

        try {
            pps = connection.getConnection().prepareStatement("SELECT * FROM Devolucion_Revista_Profesor");
            rs = pps.executeQuery();

            while (rs.next()) {
                DevolucionRevistaProf devolucionTmp = new DevolucionRevistaProf(rs.getInt("codPrestRevistaProf"), rs.getString("idBibliotecario"),
                        rs.getString("estadoDevolucion"));
                devolucionTmp.setFechaDevolucion(rs.getDate("fechaDevolucion"));
                devolucionTmp.setCodDevolucionRevistaProf(rs.getInt("codDevRevistaProf"));
                devoluciones.add(devolucionTmp);
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println("No se realizo el readAll correctamente sobre la devolucion de revista del profesor");
        } catch (Exception e) {
            System.out.println("Problema en el readAll");
        }
        return devoluciones;
    }

    @Override
    public DevolucionRevistaProf readDAO(int codigo) {
        Statement stmt;
        ResultSet rs;
        DevolucionRevistaProf devolucion = null;

        try {
            stmt = connection.getConnection().createStatement();
            rs = stmt.executeQuery("SELECT * FROM Devolucion_Revista_Profesor WHERE codDevRevistaProf = " + codigo + ";");

            while (rs.next()) {
                devolucion = new DevolucionRevistaProf(rs.getInt("codPrestRevistaProf"), rs.getString("idBibliotecario"),
                        rs.getString("estadoDevolucion"));
                devolucion.setFechaDevolucion(rs.getDate("fechaDevolucion"));
                devolucion.setCodDevolucionRevistaProf(rs.getInt("codDevRevistaProf"));
            }
            rs.close();
            return devolucion;
        } catch (SQLException e) {
            System.out.println("La devolución de revista de profesor con ese codigo no existe");
        } catch (Exception e) {
            System.out.println("No se pudo realizar la consulta");
        }
        return devolucion;
    }

    @Override
    public boolean updateDAO(DevolucionRevistaProf devolucion) {
        String sqlSentence = "UPDATE Devolucion_Revista_Profesor SET codPrestRevistaProf = ?, idBibliotecario = ?, fechaDevolucion = ?, estadoDevolucion = ?"
                + " WHERE codDevRevistaProf = ?";
        PreparedStatement pps;

        try {
            pps = connection.getConnection().prepareStatement(sqlSentence);
            pps.setInt(1, devolucion.getCodPrestamoRevistaProf());
            pps.setString(2, devolucion.getIdBibliotecario());
            pps.setDate(3, (Date) devolucion.getFechaDevolucion());
            pps.setString(4, devolucion.getEstadoDevolucion());
            pps.setInt(5, devolucion.getCodDevolucionRevistaProf());

            if (pps.executeUpdate() > 0) {
                System.out.println("Realizo el update");
                return true;
            } else {
                System.out.println("No existe una devolucion de reviste de profesor con ese codigo");
            }
        } catch (SQLException e) {
            System.out.println("No se pudo realizar el update de devolucion de revista del profesor");
        }
        return false;
    }

    /**
     * el método realiza la consulta del código de la devolución de una revista
     * del profesor en la BD, por medio del código del préstamo.
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
            rs = stmt.executeQuery("SELECT codDevRevistaProf FROM Devolucion_Revista_Profesor "
                    + "WHERE codPrestRevistaProf = '" + codPrestamo + "';");

            while (rs.next()) {
                codDevolucion = rs.getInt("codDevRevistaProf");
            }

            rs.close();
        } catch (SQLException e) {
            System.out.println("Error al realizar el readCodigoDAO, en devolución revista profesor");
        }

        return codDevolucion;
    }

}

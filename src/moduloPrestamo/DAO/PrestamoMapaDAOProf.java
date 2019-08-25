package moduloPrestamo.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import modelo.ConnectionBD;
import moduloPrestamo.entitys.PrestamoMapaProf;

/**
 * clase que realiza el CRUD sobre la entidad PrestamoMapaProf.
 *
 * @author Julian
 * @creado 11/08/2019
 * @author Miguel Fernández
 * @modificado 24/08/2019
 */
public class PrestamoMapaDAOProf extends PrestamoRecursoDAOAbs<PrestamoMapaProf> {

    /**
     * constructor de la clase sin parámetros.
     */
    public PrestamoMapaDAOProf() {
        connection = ConnectionBD.getInstance();
    }

    /**
     * el método realiza el INSERT en la BD del préstamo de un mapa al profesor.
     *
     * @param prestamo
     * @return boolean
     */
    @Override
    public boolean createDAO(PrestamoMapaProf prestamo) {
        String sqlSentence = "INSERT INTO Prestamo_Mapa_Profesor "
                + "(codBarraMapa, idProfesor, idBibliotecario, fechaPrestamo, fechaDevolucion, devuelto)"
                + " VALUES (?,?,?, CURRENT_DATE(), CURRENT_DATE(), 'no')";

        PreparedStatement pps;

        try {
            pps = connection.getConnection().prepareStatement(sqlSentence);
            pps.setString(1, prestamo.getCodBarraMapa());
            pps.setString(2, prestamo.getIdProfesor());
            pps.setString(3, prestamo.getIdBibliotecario());

            if (pps.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Error al realizar el createDAO, en préstamo mapa profesor");
        }

        return false;
    }

    /**
     * el método realiza la consulta del préstamo de un mapa del profesor en la
     * BD, por medio de un código.
     *
     * @param codigo
     * @return prestamo
     */
    @Override
    public PrestamoMapaProf readDAO(int codigo) {
        Statement stmt;
        ResultSet rs;
        PrestamoMapaProf prestamo = null;

        try {
            stmt = connection.getConnection().createStatement();
            rs = stmt.executeQuery("SELECT * FROM Prestamo_Mapa_Profesor WHERE codPrestMapaProf = " + codigo + ";");

            while (rs.next()) {
                prestamo = new PrestamoMapaProf();
                prestamo.setCodPrestamoMapaProf(rs.getInt("codPrestMapaProf"));
                prestamo.setCodBarraMapa(rs.getString("codBarraMapa"));
                prestamo.setIdProfesor(rs.getString("idProfesor"));
                prestamo.setIdBibliotecario(rs.getString("idBibliotecario"));
                prestamo.setFechaPrestamo(rs.getDate("fechaPrestamo"));
                prestamo.setFechaDevolucion(rs.getDate("fechaDevolucion"));
                prestamo.setDevuelto(rs.getString("devuelto"));
            }

            rs.close();

            return prestamo;
        } catch (SQLException e) {
            System.out.println("Error al realizar el readDAO, en préstamo mapa profesor");
        }

        return prestamo;
    }

    /**
     * el metódo actuliza un atributo o todos del préstamo de un mapa del
     * profesor.
     *
     * @param prestamo
     * @return boolean
     */
    @Override
    public boolean updateDAO(PrestamoMapaProf prestamo) {
        String sqlSentence = "UPDATE Prestamo_Mapa_Profesor "
                + "SET codBarraMapa = ?, idProfesor = ?, idBibliotecario = ?, fechaPrestamo = ?, "
                + "fechaDevolucion = ?, devuelto = ? WHERE codPrestMapaProf = ?";

        PreparedStatement pps;

        try {
            pps = connection.getConnection().prepareStatement(sqlSentence);

            pps.setString(1, prestamo.getCodBarraMapa());
            pps.setString(2, prestamo.getIdProfesor());
            pps.setString(3, prestamo.getIdBibliotecario());
            pps.setDate(4, prestamo.getFechaPrestamo());
            pps.setDate(5, prestamo.getFechaDevolucion());
            pps.setString(6, prestamo.getDevuelto());
            pps.setInt(7, prestamo.getCodPrestamoMapaProf());

            if (pps.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Error al realizar el updateDAO, en préstamo mapa profesor");
        }

        return false;
    }

    /**
     * el metódo elimina el préstamo de un mapa del profesor.
     *
     * @param pk
     * @return boolean
     */
    @Override
    public boolean deleteDAO(int pk) {
        String sqlSentence = "DELETE FROM Prestamo_Mapa_Profesor WHERE codPrestMapaProf = ?";
        PreparedStatement pps;

        try {
            pps = connection.getConnection().prepareStatement(sqlSentence);
            pps.setInt(1, pk);

            if (pps.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Error al realizar el deleteDAO, en préstamo mapa profesor");
        }

        return false;
    }

    /**
     * el metódo retorna una lista con todos los préstamos de mapas a los
     * estudiantes.
     *
     * @return prestamos
     */
    @Override
    public List<PrestamoMapaProf> readAllDAO() {
        PreparedStatement pps;
        ResultSet rs;
        ArrayList<PrestamoMapaProf> prestamos = new ArrayList();

        try {
            pps = connection.getConnection().prepareStatement("SELECT * FROM Prestamo_Mapa_Profesor");
            rs = pps.executeQuery();

            while (rs.next()) {
                PrestamoMapaProf prestamoTmp = new PrestamoMapaProf();
                prestamoTmp.setCodPrestamoMapaProf(rs.getInt("codPrestMapaProf"));
                prestamoTmp.setCodBarraMapa(rs.getString("codBarraMapa"));
                prestamoTmp.setIdProfesor(rs.getString("idProfesor"));
                prestamoTmp.setIdBibliotecario(rs.getString("idBibliotecario"));
                prestamoTmp.setFechaPrestamo(rs.getDate("fechaPrestamo"));
                prestamoTmp.setFechaDevolucion(rs.getDate("fechaDevolucion"));
                prestamoTmp.setDevuelto(rs.getString("devuelto"));
                prestamos.add(prestamoTmp);
            }

            rs.close();
        } catch (SQLException e) {
            System.out.println("Error al realizar el readALLDAO, en préstamo mapa profesor");
        }

        return prestamos;
    }

    /**
     * el método realiza la consulta del código del préstamo de un mapa del
     * profesor en la BD, por medio de un código de barras.
     *
     * @param codBarra
     * @return codPrestamo
     */
    @Override
    public int readCodigoDAO(String codBarra) {
        Statement stmt;
        ResultSet rs;
        int codPrestamo = -1;

        try {
            stmt = connection.getConnection().createStatement();
            rs = stmt.executeQuery("SELECT codPrestMapaProf FROM Prestamo_Mapa_Profesor WHERE codBarraMapa = " + codBarra + ";");

            while (rs.next()) {
                codPrestamo = rs.getInt("codPrestMapaProf");
            }

            rs.close();

        } catch (SQLException e) {
            System.out.println("Error al realizar el readCodigoDAO, en préstamo mapa profesor");
        }

        return codPrestamo;
    }

}

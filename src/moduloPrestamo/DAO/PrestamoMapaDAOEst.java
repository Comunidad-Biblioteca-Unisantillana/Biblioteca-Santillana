package moduloPrestamo.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import modelo.ConnectionBD;
import moduloPrestamo.entitys.PrestamoMapaEst;

/**
 * clase que realiza el CRUD sobre la entidad PrestamoMapaEst.
 *
 * @author Julian
 * @creado 11/08/2019
 * @author Miguel Fernández
 * @modificado 24/08/2019
 */
public class PrestamoMapaDAOEst extends PrestamoRecursoDAOAbs<PrestamoMapaEst> {

    /**
     * constructor de la clase sin parámetros.
     */
    public PrestamoMapaDAOEst() {
        connection = ConnectionBD.getInstance();
    }

    /**
     * el método realiza el INSERT en la BD del préstamo de un mapa al
     * estudiante.
     *
     * @param prestamo
     * @return boolean
     */
    @Override
    public boolean createDAO(PrestamoMapaEst prestamo) {
        String sqlSentence = "INSERT INTO Prestamo_Mapa_Estudiante "
                + "(codBarraMapa, codEstudiante, idBibliotecario, fechaPrestamo, fechaDevolucion, devuelto)"
                + " VALUES (?,?,?, CURRENT_DATE(), CURRENT_DATE(), 'no')";

        PreparedStatement pps;

        try {
            pps = connection.getConnection().prepareStatement(sqlSentence);
            pps.setString(1, prestamo.getCodBarraMapa());
            pps.setString(2, prestamo.getCodEstudiante());
            pps.setString(3, prestamo.getIdBibliotecario());

            if (pps.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Error al realizar el createDAO, en préstamo mapa estudiante");
        }

        return false;
    }

    /**
     * el método realiza la consulta del préstamo de un mapa del estudiante en
     * la BD, por medio de un código.
     *
     * @param codigo
     * @return prestamo
     */
    @Override
    public PrestamoMapaEst readDAO(int codigo) {
        Statement stmt;
        ResultSet rs;
        PrestamoMapaEst prestamo = null;

        try {
            stmt = connection.getConnection().createStatement();
            rs = stmt.executeQuery("SELECT * FROM Prestamo_Mapa_Estudiante WHERE codPrestMapaEst = " + codigo + ";");

            while (rs.next()) {
                prestamo = new PrestamoMapaEst();
                prestamo.setCodPrestamoMapaEst(rs.getInt("codPrestMapaEst"));
                prestamo.setCodBarraMapa(rs.getString("codBarraMapa"));
                prestamo.setCodEstudiante(rs.getString("codEstudiante"));
                prestamo.setIdBibliotecario(rs.getString("idBibliotecario"));
                prestamo.setFechaPrestamo(rs.getDate("fechaPrestamo"));
                prestamo.setFechaDevolucion(rs.getDate("fechaDevolucion"));
                prestamo.setDevuelto(rs.getString("devuelto"));
            }

            rs.close();

            return prestamo;
        } catch (SQLException e) {
            System.out.println("Error al realizar el readDAO, en préstamo mapa estudiante");
        }

        return prestamo;
    }

    /**
     * el metódo actuliza un atributo o todos del préstamo de un mapa del
     * estudiante.
     *
     * @param prestamo
     * @return boolean
     */
    @Override
    public boolean updateDAO(PrestamoMapaEst prestamo) {
        String sqlSentence = "UPDATE Prestamo_Mapa_Estudiante "
                + "SET codBarraMapa = ?, codEstudiante = ?, idBibliotecario = ?, fechaPrestamo = ?, "
                + "fechaDevolucion = ?, devuelto = ? WHERE codPrestMapaEst = ?";

        PreparedStatement pps;

        try {
            pps = connection.getConnection().prepareStatement(sqlSentence);

            pps.setString(1, prestamo.getCodBarraMapa());
            pps.setString(2, prestamo.getCodEstudiante());
            pps.setString(3, prestamo.getIdBibliotecario());
            pps.setDate(4, prestamo.getFechaPrestamo());
            pps.setDate(5, prestamo.getFechaDevolucion());
            pps.setString(6, prestamo.getDevuelto());
            pps.setInt(7, prestamo.getCodPrestamoMapaEst());

            if (pps.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Error al realizar el updateDAO, en préstamo mapa estudiante");
        }

        return false;
    }

    /**
     * el metódo elimina el préstamo de un mapa del estudiante.
     *
     * @param pk
     * @return boolean
     */
    @Override
    public boolean deleteDAO(int pk) {
        String sqlSentence = "DELETE FROM Prestamo_Mapa_Estudiante WHERE codPrestMapaEst = ?";
        PreparedStatement pps;

        try {
            pps = connection.getConnection().prepareStatement(sqlSentence);
            pps.setInt(1, pk);

            if (pps.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Error al realizar el deleteDAO, en préstamo mapa estudiante");
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
    public List<PrestamoMapaEst> readAllDAO() {
        PreparedStatement pps;
        ResultSet rs;
        ArrayList<PrestamoMapaEst> prestamos = new ArrayList();

        try {
            pps = connection.getConnection().prepareStatement("SELECT * FROM Prestamo_Mapa_Estudiante");
            rs = pps.executeQuery();

            while (rs.next()) {
                PrestamoMapaEst prestamoTmp = new PrestamoMapaEst();
                prestamoTmp.setCodPrestamoMapaEst(rs.getInt("codPrestMapaEst"));
                prestamoTmp.setCodBarraMapa(rs.getString("codBarraMapa"));
                prestamoTmp.setCodEstudiante(rs.getString("codEstudiante"));
                prestamoTmp.setIdBibliotecario(rs.getString("idBibliotecario"));
                prestamoTmp.setFechaPrestamo(rs.getDate("fechaPrestamo"));
                prestamoTmp.setFechaDevolucion(rs.getDate("fechaDevolucion"));
                prestamoTmp.setDevuelto(rs.getString("devuelto"));
                prestamos.add(prestamoTmp);
            }

            rs.close();

        } catch (SQLException e) {
            System.out.println("Error al realizar el readALLDAO, en préstamo mapa estudiante");

        }

        return prestamos;
    }

    /**
     * el método realiza la consulta del código del préstamo de un mapa del
     * estudiante en la BD, por medio de un código de barras.
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
            rs = stmt.executeQuery("SELECT codPrestMapaEst FROM Prestamo_Mapa_Estudiante "
                    + "WHERE codBarraMapa = " + codBarra + " AND devuelto = 'no';");

            while (rs.next()) {
                codPrestamo = rs.getInt("codPrestMapaEst");
            }

            rs.close();
        } catch (SQLException e) {
            System.out.println("Error al realizar el readCodigoDAO, en préstamo mapa estudiante");
        }

        return codPrestamo;
    }

}

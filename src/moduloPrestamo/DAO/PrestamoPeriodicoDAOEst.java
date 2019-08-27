package moduloPrestamo.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import general.modelo.ConnectionBD;
import moduloPrestamo.entitys.PrestamoPeriodicoEst;

/**
 * clase que realiza el CRUD sobre la entidad PrestamoPeriodicoEst.
 *
 * @author Julian
 * @creado 11/08/2019
 * @author Miguel Fernández
 * @modificado 24/08/2019
 */
public class PrestamoPeriodicoDAOEst extends PrestamoRecursoDAOAbs<PrestamoPeriodicoEst> {

    /**
     * constructor de la clase sin parámetros.
     */
    public PrestamoPeriodicoDAOEst() {
        connection = ConnectionBD.getInstance();
    }

    /**
     * el método realiza el INSERT en la BD del préstamo de un periódico al
     * estudiante.
     *
     * @param prestamo
     * @return boolean
     */
    @Override
    public boolean createDAO(PrestamoPeriodicoEst prestamo) {
        String sqlSentence = "INSERT INTO Prestamo_Periodico_Estudiante "
                + "(codBarraPeriodico, codEstudiante, idBibliotecario, fechaPrestamo, fechaDevolucion, devuelto)"
                + " VALUES (?,?,?, current_date, current_date, 'no')";

        PreparedStatement pps;

        try {
            pps = connection.getConnection().prepareStatement(sqlSentence);
            pps.setString(1, prestamo.getCodBarraPeriodico());
            pps.setString(2, prestamo.getCodEstudiante());
            pps.setString(3, prestamo.getIdBibliotecario());

            if (pps.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Error al realizar el createDAO, en préstamo periódico estudiante");
        }

        return false;
    }

    /**
     * el método realiza la consulta del préstamo de un periódico del estudiante
     * en la BD, por medio de un código.
     *
     * @param codigo
     * @return prestamo
     */
    @Override
    public PrestamoPeriodicoEst readDAO(int codigo) {
        Statement stmt;
        ResultSet rs;
        PrestamoPeriodicoEst prestamo = null;

        try {
            stmt = connection.getConnection().createStatement();
            rs = stmt.executeQuery("SELECT * FROM Prestamo_Periodico_Estudiante WHERE codPrestPeriodicoEst = '" + codigo + "';");

            while (rs.next()) {
                prestamo = new PrestamoPeriodicoEst();
                prestamo.setCodPrestamoPeriodicoEst(rs.getInt("codPrestPeriodicoEst"));
                prestamo.setCodBarraPeriodico(rs.getString("codBarraPeriodico"));
                prestamo.setCodEstudiante(rs.getString("codEstudiante"));
                prestamo.setIdBibliotecario(rs.getString("idBibliotecario"));
                prestamo.setFechaPrestamo(rs.getDate("fechaPrestamo"));
                prestamo.setFechaDevolucion(rs.getDate("fechaDevolucion"));
                prestamo.setDevuelto(rs.getString("devuelto"));
            }

            rs.close();

            return prestamo;
        } catch (SQLException e) {
            System.out.println("Error al realizar el readDAO, en préstamo periódico estudiante");
        }

        return prestamo;
    }

    /**
     * el metódo actuliza un atributo o todos del préstamo de un periódico del
     * estudiante.
     *
     * @param prestamo
     * @return boolean
     */
    @Override
    public boolean updateDAO(PrestamoPeriodicoEst prestamo) {
        String sqlSentence = "UPDATE Prestamo_Periodico_Estudiante "
                + "SET codBarraPeriodico = ?, codEstudiante = ?, idBibliotecario = ?, fechaPrestamo = ?, "
                + "fechaDevolucion = ?, devuelto = ? WHERE codPrestPeriodicoEst = ?";

        PreparedStatement pps;

        try {
            pps = connection.getConnection().prepareStatement(sqlSentence);

            pps.setString(1, prestamo.getCodBarraPeriodico());
            pps.setString(2, prestamo.getCodEstudiante());
            pps.setString(3, prestamo.getIdBibliotecario());
            pps.setDate(4, prestamo.getFechaPrestamo());
            pps.setDate(5, prestamo.getFechaDevolucion());
            pps.setString(6, prestamo.getDevuelto());
            pps.setInt(7, prestamo.getCodPrestamoPeriodicoEst());

            if (pps.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Error al realizar el updateDAO, en préstamo periódico estudiante");
        }

        return false;
    }

    /**
     * el metódo elimina el préstamo de un periódico del estudiante.
     *
     * @param pk
     * @return boolean
     */
    @Override
    public boolean deleteDAO(int pk) {
        String sqlSentence = "DELETE FROM Prestamo_Periodico_Estudiante WHERE codPrestPeriodicoEst = ?";
        PreparedStatement pps;

        try {
            pps = connection.getConnection().prepareStatement(sqlSentence);
            pps.setInt(1, pk);

            if (pps.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Error al realizar el deleteDAO, en préstamo periódico estudiante");
        }

        return false;
    }

    /**
     * el metódo retorna una lista con todos los préstamos de periódicos a los
     * estudiantes.
     *
     * @return prestamos
     */
    @Override
    public List<PrestamoPeriodicoEst> readAllDAO() {
        PreparedStatement pps;
        ResultSet rs;
        ArrayList<PrestamoPeriodicoEst> prestamos = new ArrayList();

        try {
            pps = connection.getConnection().prepareStatement("SELECT * FROM Prestamo_Periodico_Estudiante");
            rs = pps.executeQuery();

            while (rs.next()) {
                PrestamoPeriodicoEst prestamoTmp = new PrestamoPeriodicoEst();
                prestamoTmp.setCodPrestamoPeriodicoEst(rs.getInt("codPrestPeriodicoEst"));
                prestamoTmp.setCodBarraPeriodico(rs.getString("codBarraPeriodico"));
                prestamoTmp.setCodEstudiante(rs.getString("codEstudiante"));
                prestamoTmp.setIdBibliotecario(rs.getString("idBibliotecario"));
                prestamoTmp.setFechaPrestamo(rs.getDate("fechaPrestamo"));
                prestamoTmp.setFechaDevolucion(rs.getDate("fechaDevolucion"));
                prestamoTmp.setDevuelto(rs.getString("devuelto"));
                prestamos.add(prestamoTmp);
            }

            rs.close();
        } catch (SQLException e) {
            System.out.println("Error al realizar el readALLDAO, en préstamo periódico estudiante");
        }

        return prestamos;
    }

    /**
     * el método realiza la consulta del código del préstamo de un periódico del
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
            rs = stmt.executeQuery("SELECT codPrestPeriodicoEst FROM Prestamo_Periodico_Estudiante "
                    + "WHERE codBarraPeriodico = '" + codBarra + "' AND devuelto = 'no';");

            while (rs.next()) {
                codPrestamo = rs.getInt("codPrestPeriodicoEst");
            }

            rs.close();
        } catch (SQLException e) {
            System.out.println("Error al realizar el readCodigoDAO, en préstamo periódico estudiante");
        }

        return codPrestamo;
    }

}

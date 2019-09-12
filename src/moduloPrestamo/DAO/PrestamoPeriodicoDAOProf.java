package moduloPrestamo.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import general.modelo.ConnectionBD;
import java.sql.Date;
import moduloPrestamo.entitys.PrestamoPeriodicoProf;

/**
 * clase que realiza el CRUD sobre la entidad PrestamoPeriodicoProf.
 *
 * @author Julian
 * @creado 11/08/2019
 * @author Miguel Fernández
 * @modificado 24/08/2019
 */
public class PrestamoPeriodicoDAOProf extends PrestamoRecursoDAOAbs<PrestamoPeriodicoProf> {

    /**
     * constructor de la clase sin parámetros.
     */
    public PrestamoPeriodicoDAOProf() {
        connection = ConnectionBD.getInstance();
    }

    /**
     * el método realiza el INSERT en la BD del préstamo de un periódico al
     * profesor.
     *
     * @param prestamo
     * @return boolean
     */
    @Override
    public boolean createDAO(PrestamoPeriodicoProf prestamo) {
        String sqlSentence = "INSERT INTO Prestamo_Periodico_Profesor "
                + "(codBarraPeriodico, idProfesor, idBibliotecario, fechaPrestamo, fechaDevolucion, devuelto)"
                + " VALUES (?,?,?, current_date, current_date, 'no')";

        PreparedStatement pps;

        try {
            pps = connection.getConnection().prepareStatement(sqlSentence);
            pps.setString(1, prestamo.getCodBarraPeriodico());
            pps.setString(2, prestamo.getIdProfesor());
            pps.setString(3, prestamo.getIdBibliotecario());

            if (pps.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Error al realizar el createDAO, en préstamo periódico profesor");
        }

        return false;
    }

    /**
     * el método realiza la consulta del préstamo de un periódico del profesor
     * en la BD, por medio de un código.
     *
     * @param codigo
     * @return prestamo
     */
    @Override
    public PrestamoPeriodicoProf readDAO(int codigo) {
        Statement stmt;
        ResultSet rs;
        PrestamoPeriodicoProf prestamo = null;

        try {
            stmt = connection.getConnection().createStatement();
            rs = stmt.executeQuery("SELECT * FROM Prestamo_Periodico_Profesor WHERE codPrestPeriodicoProf = '" + codigo + "';");

            while (rs.next()) {
                prestamo = new PrestamoPeriodicoProf();
                prestamo.setCodPrestamoPeriodicoProf(rs.getInt("codPrestPeriodicoProf"));
                prestamo.setCodBarraPeriodico(rs.getString("codBarraPeriodico"));
                prestamo.setIdProfesor(rs.getString("idProfesor"));
                prestamo.setIdBibliotecario(rs.getString("idBibliotecario"));
                prestamo.setFechaPrestamo(rs.getDate("fechaPrestamo"));
                prestamo.setFechaDevolucion(rs.getDate("fechaDevolucion"));
                prestamo.setDevuelto(rs.getString("devuelto"));
            }

            rs.close();

            return prestamo;
        } catch (SQLException e) {
            System.out.println("Error al realizar el readDAO, en préstamo periódico profesor");
        }

        return prestamo;
    }

    /**
     * el metódo actuliza un atributo o todos del préstamo de un periódico del
     * profesor.
     *
     * @param prestamo
     * @return boolean
     */
    @Override
    public boolean updateDAO(PrestamoPeriodicoProf prestamo) {
        String sqlSentence = "UPDATE Prestamo_Periodico_Profesor "
                + "SET codBarraPeriodico = ?, idProfesor = ?, idBibliotecario = ?, fechaPrestamo = ?, "
                + "fechaDevolucion = ?, devuelto = ? WHERE codPrestPeriodicoProf = ?";

        PreparedStatement pps;

        try {
            pps = connection.getConnection().prepareStatement(sqlSentence);

            pps.setString(1, prestamo.getCodBarraPeriodico());
            pps.setString(2, prestamo.getIdProfesor());
            pps.setString(3, prestamo.getIdBibliotecario());
            pps.setDate(4, (Date) prestamo.getFechaPrestamo());
            pps.setDate(5, (Date) prestamo.getFechaDevolucion());
            pps.setString(6, prestamo.getDevuelto());
            pps.setInt(7, prestamo.getCodPrestamoPeriodicoProf());

            if (pps.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Error al realizar el updateDAO, en préstamo periódico profesor");
        }

        return false;
    }

    /**
     * el metódo elimina el préstamo de un periódico del profesor.
     *
     * @param pk
     * @return boolean
     */
    @Override
    public boolean deleteDAO(int pk) {
        String sqlSentence = "DELETE FROM Prestamo_Periodico_Profesor WHERE codPrestPeriodicoProf = ?";
        PreparedStatement pps;

        try {
            pps = connection.getConnection().prepareStatement(sqlSentence);
            pps.setInt(1, pk);

            if (pps.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Error al realizar el deleteDAO, en préstamo periódico profesor");
        }

        return false;
    }

    /**
     * el metódo retorna una lista con todos los préstamos de periódicos a los
     * profesores.
     *
     * @return prestamos
     */
    @Override
    public List<PrestamoPeriodicoProf> readAllDAO() {
        PreparedStatement pps;
        ResultSet rs;
        ArrayList<PrestamoPeriodicoProf> prestamos = new ArrayList();

        try {
            pps = connection.getConnection().prepareStatement("SELECT * FROM Prestamo_Periodico_Profesor");
            rs = pps.executeQuery();

            while (rs.next()) {
                PrestamoPeriodicoProf prestamoTmp = new PrestamoPeriodicoProf();
                prestamoTmp.setCodPrestamoPeriodicoProf(rs.getInt("codPrestPeriodicoProf"));
                prestamoTmp.setCodBarraPeriodico(rs.getString("codBarraPeriodico"));
                prestamoTmp.setIdProfesor(rs.getString("idProfesor"));
                prestamoTmp.setIdBibliotecario(rs.getString("idBibliotecario"));
                prestamoTmp.setFechaPrestamo(rs.getDate("fechaPrestamo"));
                prestamoTmp.setFechaDevolucion(rs.getDate("fechaDevolucion"));
                prestamoTmp.setDevuelto(rs.getString("devuelto"));
                prestamos.add(prestamoTmp);
            }

            rs.close();
        } catch (SQLException e) {
            System.out.println("Error al realizar el readALLDAO, en préstamo periódico profesor");
        }

        return prestamos;
    }

    /**
     * el método realiza la consulta del código del préstamo de un periódico del
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
            rs = stmt.executeQuery("SELECT codPrestPeriodicoProf FROM Prestamo_Periodico_Profesor "
                    + "WHERE codBarraPeriodico = '" + codBarra + "' AND devuelto = 'no';");

            while (rs.next()) {
                codPrestamo = rs.getInt("codPrestPeriodicoProf");
            }

            rs.close();
        } catch (SQLException e) {
            System.out.println("Error al realizar el readCodigoDAO, en préstamo periódico profesor");
        }

        return codPrestamo;
    }

}

package moduloPrestamo.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import general.modelo.ConnectionBD;
import moduloPrestamo.entitys.PrestamoRevistaProf;

/**
 * clase que realiza el CRUD sobre la entidad PrestamoRevistaProf.
 *
 * @author Julian
 * @creado 11/08/2019
 * @author Miguel Fernández
 * @modificado 24/08/2019
 */
public class PrestamoRevistaDAOProf extends PrestamoRecursoDAOAbs<PrestamoRevistaProf> {

    /**
     * constructor de la clase sin parámetros.
     */
    public PrestamoRevistaDAOProf() {
        connection = ConnectionBD.getInstance();
    }

    /**
     * el método realiza el INSERT en la BD del préstamo de una revista al
     * profesor.
     *
     * @param prestamo
     * @return boolean
     */
    @Override
    public boolean createDAO(PrestamoRevistaProf prestamo) {
        String sqlSentence = "INSERT INTO Prestamo_Revista_Profesor "
                + "(codBarraRevista, idProfesor, idBibliotecario, fechaPrestamo, fechaDevolucion, devuelto)"
                + " VALUES (?,?,?, current_date, current_date, 'no')";

        PreparedStatement pps;

        try {
            pps = connection.getConnection().prepareStatement(sqlSentence);
            pps.setString(1, prestamo.getCodBarraRevista());
            pps.setString(2, prestamo.getIdProfesor());
            pps.setString(3, prestamo.getIdBibliotecario());

            if (pps.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Error al realizar el createDAO, en préstamo revista profesor");
        }

        return false;
    }

    /**
     * el método realiza la consulta del préstamo de una revista del profesor en
     * la BD, por medio de un código.
     *
     * @param codigo
     * @return prestamo
     */
    @Override
    public PrestamoRevistaProf readDAO(int codigo) {
        Statement stmt;
        ResultSet rs;
        PrestamoRevistaProf prestamo = null;

        try {
            stmt = connection.getConnection().createStatement();
            rs = stmt.executeQuery("SELECT * FROM Prestamo_Revista_Profesor WHERE codPrestRevistaProf = '" + codigo + "';");

            while (rs.next()) {
                prestamo = new PrestamoRevistaProf();
                prestamo.setCodPrestamoRevistaProf(rs.getInt("codPrestRevistaProf"));
                prestamo.setCodBarraRevista(rs.getString("codBarraRevista"));
                prestamo.setIdProfesor(rs.getString("idProfesor"));
                prestamo.setIdBibliotecario(rs.getString("idBibliotecario"));
                prestamo.setFechaPrestamo(rs.getDate("fechaPrestamo"));
                prestamo.setFechaDevolucion(rs.getDate("fechaDevolucion"));
                prestamo.setDevuelto(rs.getString("devuelto"));
            }

            rs.close();

            return prestamo;
        } catch (SQLException e) {
            System.out.println("Error al realizar el readDAO, en préstamo revista profesor");
        }

        return prestamo;
    }

    /**
     * el metódo actuliza un atributo o todos del préstamo de una revista del
     * profesor.
     *
     * @param prestamo
     * @return boolean
     */
    @Override
    public boolean updateDAO(PrestamoRevistaProf prestamo) {
        String sqlSentence = "UPDATE Prestamo_Revista_Profesor "
                + "SET codBarraRevista = ?, idProfesor = ?, idBibliotecario = ?, fechaPrestamo = ?, "
                + "fechaDevolucion = ?, devuelto = ? WHERE codPrestRevistaProf = ?";

        PreparedStatement pps;

        try {
            pps = connection.getConnection().prepareStatement(sqlSentence);

            pps.setString(1, prestamo.getCodBarraRevista());
            pps.setString(2, prestamo.getIdProfesor());
            pps.setString(3, prestamo.getIdBibliotecario());
            pps.setDate(4, prestamo.getFechaPrestamo());
            pps.setDate(5, prestamo.getFechaDevolucion());
            pps.setString(6, prestamo.getDevuelto());
            pps.setInt(7, prestamo.getCodPrestamoRevistaProf());

            if (pps.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Error al realizar el updateDAO, en préstamo revista profesor");
        }

        return false;
    }

    /**
     * el metódo elimina el préstamo de un revista del profesor.
     *
     * @param pk
     * @return boolean
     */
    @Override
    public boolean deleteDAO(int pk) {
        String sqlSentence = "DELETE FROM Prestamo_Revista_Profesor WHERE codPrestRevistaProf = ?";
        PreparedStatement pps;

        try {
            pps = connection.getConnection().prepareStatement(sqlSentence);
            pps.setInt(1, pk);

            if (pps.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Error al realizar el deleteDAO, en préstamo revista profesor");
        }

        return false;
    }

    /**
     * el metódo retorna una lista con todos los préstamos de revistas a los
     * profesores.
     *
     * @return prestamos
     */
    @Override
    public List<PrestamoRevistaProf> readAllDAO() {
        PreparedStatement pps;
        ResultSet rs;
        ArrayList<PrestamoRevistaProf> prestamos = new ArrayList();

        try {
            pps = connection.getConnection().prepareStatement("SELECT * FROM Prestamo_Revista_Profesor");
            rs = pps.executeQuery();

            while (rs.next()) {
                PrestamoRevistaProf prestamoTmp = new PrestamoRevistaProf();
                prestamoTmp.setCodPrestamoRevistaProf(rs.getInt("codPrestRevistaProf"));
                prestamoTmp.setCodBarraRevista(rs.getString("codBarraRevista"));
                prestamoTmp.setIdProfesor(rs.getString("idProfesor"));
                prestamoTmp.setIdBibliotecario(rs.getString("idBibliotecario"));
                prestamoTmp.setFechaPrestamo(rs.getDate("fechaPrestamo"));
                prestamoTmp.setFechaDevolucion(rs.getDate("fechaDevolucion"));
                prestamoTmp.setDevuelto(rs.getString("devuelto"));
                prestamos.add(prestamoTmp);
            }

            rs.close();
        } catch (SQLException e) {
            System.out.println("Error al realizar el readALLDAO, en préstamo revista profesor");
        }

        return prestamos;
    }

    /**
     * el método realiza la consulta del código del préstamo de una revista del
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
            rs = stmt.executeQuery("SELECT codPrestRevistaProf FROM Prestamo_Revista_Profesor "
                    + "WHERE codBarraRevista = '" + codBarra + "' AND devuelto = 'no';");

            while (rs.next()) {
                codPrestamo = rs.getInt("codPrestRevistaProf");
            }

            rs.close();
        } catch (SQLException e) {
            System.out.println("Error al realizar el readCodigoDAO, en préstamo revista profesor");
        }

        return codPrestamo;
    }

}

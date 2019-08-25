package moduloPrestamo.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import modelo.ConnectionBD;
import moduloPrestamo.entitys.PrestamoRevistaEst;

/**
 * clase que realiza el CRUD sobre la entidad PrestamoRevistaEst.
 *
 * @author Julian
 * @creado 11/08/2019
 * @author Miguel Fernández
 * @modificado 24/08/2019
 */
public class PrestamoRevistaDAOEst extends PrestamoRecursoDAOAbs<PrestamoRevistaEst> {

    /**
     * constructor de la clase sin parámetros.
     */
    public PrestamoRevistaDAOEst() {
        connection = ConnectionBD.getInstance();
    }

    /**
     * el método realiza el INSERT en la BD del préstamo de una revista al
     * estudiante.
     *
     * @param prestamo
     * @return boolean
     */
    @Override
    public boolean createDAO(PrestamoRevistaEst prestamo) {
        String sqlSentence = "INSERT INTO Prestamo_Revista_Estudiante "
                + "(codBarraRevista, codEstudiante, idBibliotecario, fechaPrestamo, fechaDevolucion, devuelto)"
                + " VALUES (?,?,?, current_date, current_date, 'no')";

        PreparedStatement pps;

        try {
            pps = connection.getConnection().prepareStatement(sqlSentence);
            pps.setString(1, prestamo.getCodBarraRevista());
            pps.setString(2, prestamo.getCodEstudiante());
            pps.setString(3, prestamo.getIdBibliotecario());

            if (pps.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Error al realizar el createDAO, en préstamo revista estudiante");
        }

        return false;
    }

    /**
     * el método realiza la consulta del préstamo de una revista del estudiante
     * en la BD, por medio de un código.
     *
     * @param codigo
     * @return prestamo
     */
    @Override
    public PrestamoRevistaEst readDAO(int codigo) {
        Statement stmt;
        ResultSet rs;
        PrestamoRevistaEst prestamo = null;

        try {
            stmt = connection.getConnection().createStatement();
            rs = stmt.executeQuery("SELECT * FROM Prestamo_Revista_Estudiante WHERE codPrestRevistaEst = '" + codigo + "';");

            while (rs.next()) {
                prestamo = new PrestamoRevistaEst();
                prestamo.setCodPrestamoRevistaEst(rs.getInt("codPrestRevistaEst"));
                prestamo.setCodBarraRevista(rs.getString("codBarraRevista"));
                prestamo.setCodEstudiante(rs.getString("codEstudiante"));
                prestamo.setIdBibliotecario(rs.getString("idBibliotecario"));
                prestamo.setFechaPrestamo(rs.getDate("fechaPrestamo"));
                prestamo.setFechaDevolucion(rs.getDate("fechaDevolucion"));
                prestamo.setDevuelto(rs.getString("devuelto"));
            }

            rs.close();

            return prestamo;
        } catch (SQLException e) {
            System.out.println("Error al realizar el readDAO, en préstamo revista estudiante");
        }

        return prestamo;
    }

    /**
     * el metódo actuliza un atributo o todos del préstamo de un revista del
     * estudiante.
     *
     * @param prestamo
     * @return boolean
     */
    @Override
    public boolean updateDAO(PrestamoRevistaEst prestamo) {
        String sqlSentence = "UPDATE Prestamo_Revista_Estudiante "
                + "SET codBarraRevista = ?, codEstudiante = ?, idBibliotecario = ?, fechaPrestamo = ?, "
                + "fechaDevolucion = ?, devuelto = ? WHERE codPrestRevistaEst = ?";

        PreparedStatement pps;

        try {
            pps = connection.getConnection().prepareStatement(sqlSentence);

            pps.setString(1, prestamo.getCodBarraRevista());
            pps.setString(2, prestamo.getCodEstudiante());
            pps.setString(3, prestamo.getIdBibliotecario());
            pps.setDate(4, prestamo.getFechaPrestamo());
            pps.setDate(5, prestamo.getFechaDevolucion());
            pps.setString(6, prestamo.getDevuelto());
            pps.setInt(7, prestamo.getCodPrestamoRevistaEst());

            if (pps.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Error al realizar el updateDAO, en préstamo revista estudiante");
        }

        return false;
    }

    /**
     * el metódo elimina el préstamo de un revista del estudiante.
     *
     * @param pk
     * @return boolean
     */
    @Override
    public boolean deleteDAO(int pk) {
        String sqlSentence = "DELETE FROM Prestamo_Revista_Estudiante WHERE codPrestRevistaEst = ?";
        PreparedStatement pps;

        try {
            pps = connection.getConnection().prepareStatement(sqlSentence);
            pps.setInt(1, pk);

            if (pps.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Error al realizar el deleteDAO, en préstamo revista estudiante");
        }

        return false;
    }

    /**
     * el metódo retorna una lista con todos los préstamos de revistas a los
     * estudiantes.
     *
     * @return prestamos
     */
    @Override
    public List<PrestamoRevistaEst> readAllDAO() {
        PreparedStatement pps;
        ResultSet rs;
        ArrayList<PrestamoRevistaEst> prestamos = new ArrayList();

        try {
            pps = connection.getConnection().prepareStatement("SELECT * FROM Prestamo_Revista_Estudiante");
            rs = pps.executeQuery();

            while (rs.next()) {
                PrestamoRevistaEst prestamoTmp = new PrestamoRevistaEst();
                prestamoTmp.setCodPrestamoRevistaEst(rs.getInt("codPrestRevistaEst"));
                prestamoTmp.setCodBarraRevista(rs.getString("codBarraRevista"));
                prestamoTmp.setCodEstudiante(rs.getString("codEstudiante"));
                prestamoTmp.setIdBibliotecario(rs.getString("idBibliotecario"));
                prestamoTmp.setFechaPrestamo(rs.getDate("fechaPrestamo"));
                prestamoTmp.setFechaDevolucion(rs.getDate("fechaDevolucion"));
                prestamoTmp.setDevuelto(rs.getString("devuelto"));
                prestamos.add(prestamoTmp);
            }

            rs.close();
        } catch (SQLException e) {
            System.out.println("Error al realizar el readALLDAO, en préstamo revista estudiante");
        }

        return prestamos;
    }

    /**
     * el método realiza la consulta del código del préstamo de una revista del
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
            rs = stmt.executeQuery("SELECT codPrestRevistaEst FROM Prestamo_Revista_Estudiante "
                    + "WHERE codBarraRevista = '" + codBarra + "' AND devuelto = 'no';");

            while (rs.next()) {
                codPrestamo = rs.getInt("codPrestRevistaEst");
            }

            rs.close();
        } catch (SQLException e) {
            System.out.println("Error al realizar el readCodigoDAO, en préstamo revista estudiante");
        }

        return codPrestamo;
    }

}

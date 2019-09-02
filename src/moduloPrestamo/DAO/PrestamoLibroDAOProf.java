package moduloPrestamo.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import general.modelo.ConnectionBD;
import moduloPrestamo.entitys.PrestamoLibroProf;

/**
 * clase que realiza el CRUD sobre la entidad PrestamoLibroEst.
 *
 * @author Julian
 * @creado 11/08/2019
 * @author Miguel Fernández
 * @modificado 24/08/2019
 */
public class PrestamoLibroDAOProf extends PrestamoRecursoDAOAbs<PrestamoLibroProf> {

    private int diasPrestamo = 0;

    /**
     * constructor de la clase sin parámetros.
     */
    public PrestamoLibroDAOProf() {
        connection = ConnectionBD.getInstance();
    }

    /**
     * constructor de la clase con parámetros.
     *
     * @param diasPrestamo
     */
    public PrestamoLibroDAOProf(int diasPrestamo) {
        connection = ConnectionBD.getInstance();
        this.diasPrestamo = diasPrestamo;
    }

    /**
     * el método realiza el INSERT en la BD del préstamo de un libro al
     * profesor.
     *
     * @param prestamo
     * @return boolean
     */
    @Override
    public boolean createDAO(PrestamoLibroProf prestamo) {
        String sqlSentence = "INSERT INTO Prestamo_Libro_Profesor "
                + "(codBarraLibro, idProfesor, idBibliotecario, fechaPrestamo, fechaDevolucion, numRenovaciones, devuelto)"
                + " VALUES (?,?,?, current_date, current_date + " + diasPrestamo + ",?,'no')";
        PreparedStatement pps;

        try {
            pps = connection.getConnection().prepareStatement(sqlSentence);
            pps.setString(1, prestamo.getCodBarraLibro());
            pps.setString(2, prestamo.getIdProfesor());
            pps.setString(3, prestamo.getIdBibliotecario());
            pps.setInt(4, prestamo.getNumRenovaciones());

            if (pps.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Error al realizar el createDAO, en préstamo libro profesor");
        }
        return false;
    }

    /**
     * el método realiza la consulta del préstamo de un libro del profesor en la
     * BD, por medio de un código.
     *
     * @param codigo
     * @return prestamo
     */
    @Override
    public PrestamoLibroProf readDAO(int codigo) {
        Statement stmt;
        ResultSet rs;
        PrestamoLibroProf prestamo = null;

        try {
            stmt = connection.getConnection().createStatement();
            rs = stmt.executeQuery("SELECT * FROM Prestamo_Libro_Profesor WHERE codPrestLibroProf = '" + codigo + "';");

            while (rs.next()) {
                prestamo = new PrestamoLibroProf();
                prestamo.setCodPrestamoLibroProf(rs.getInt("codPrestLibroProf"));
                prestamo.setCodBarraLibro(rs.getString("codBarraLibro"));
                prestamo.setIdProfesor(rs.getString("idProfesor"));
                prestamo.setIdBibliotecario(rs.getString("idBibliotecario"));
                prestamo.setFechaPrestamo(rs.getDate("fechaPrestamo"));
                prestamo.setFechaDevolucion(rs.getDate("fechaDevolucion"));
                prestamo.setNumRenovaciones(rs.getInt("numRenovaciones"));
                prestamo.setDevuelto(rs.getString("devuelto"));
            }

            rs.close();

            return prestamo;
        } catch (SQLException e) {
            System.out.println("Error al realizar el readDAO, en préstamo libro profesor");
        }

        return prestamo;
    }

    /**
     * el metódo actuliza un atributo o todos del préstamo de un libro del
     * profesor.
     *
     * @param prestamo
     * @return boolean
     */
    @Override
    public boolean updateDAO(PrestamoLibroProf prestamo) {
        String sqlSentence;

        if (diasPrestamo == 15) {//cuando se realiza una renovación y se actuliza la fecha de devolución
            sqlSentence = "UPDATE Prestamo_Libro_Profesor "
                    + "SET codBarraLibro = ?, idProfesor = ?, idBibliotecario = ?, "
                    + "fechaPrestamo = ?, fechaDevolucion = current_date + " + diasPrestamo + ", numRenovaciones = ?, "
                    + "devuelto = ? WHERE codPrestLibroProf = ?";
        } else {//cuando se dvuelve el recurso y se actuliza el atributo devuelto
            sqlSentence = "UPDATE Prestamo_Libro_Profesor "
                    + "SET codBarraLibro = ?, idProfesor = ?, idBibliotecario = ?, fechaPrestamo = ?, "
                    + "fechaDevolucion = '" + prestamo.getFechaDevolucion() + "', numRenovaciones = ?,"
                    + "devuelto = ? WHERE codPrestLibroProf = ?";
        }

        PreparedStatement pps;

        try {
            pps = connection.getConnection().prepareStatement(sqlSentence);

            pps.setString(1, prestamo.getCodBarraLibro());
            pps.setString(2, prestamo.getIdProfesor());
            pps.setString(3, prestamo.getIdBibliotecario());
            pps.setDate(4, prestamo.getFechaPrestamo());
            pps.setInt(5, prestamo.getNumRenovaciones());
            pps.setString(6, prestamo.getDevuelto());
            pps.setInt(7, prestamo.getCodPrestamoLibroProf());

            if (pps.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Error al realizar el updateDAO, en préstamo libro profesor");
        }

        return false;
    }

    /**
     * el metódo elimina el préstamo de un libro del profesor.
     *
     * @param pk
     * @return boolean
     */
    @Override
    public boolean deleteDAO(int pk) {
        String sqlSentence = "DELETE FROM Prestamo_Libro_Profesor WHERE codPrestLibroProf = ?";
        PreparedStatement pps;

        try {
            pps = connection.getConnection().prepareStatement(sqlSentence);
            pps.setInt(1, pk);

            if (pps.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Error al realizar el deleteDAO, en préstamo libro profesor");
        }

        return false;
    }

    /**
     * el metódo retorna una lista con todos los préstamos de libros a los
     * profesores.
     *
     * @return prestamos
     */
    @Override
    public List<PrestamoLibroProf> readAllDAO() {
        PreparedStatement pps;
        ResultSet rs;
        ArrayList<PrestamoLibroProf> prestamos = new ArrayList();

        try {
            pps = connection.getConnection().prepareStatement("SELECT * FROM Prestamo_Libro_Profesor");
            rs = pps.executeQuery();

            while (rs.next()) {
                PrestamoLibroProf prestamoTmp = new PrestamoLibroProf();
                prestamoTmp.setCodPrestamoLibroProf(rs.getInt("codPrestLibroProf"));
                prestamoTmp.setCodBarraLibro(rs.getString("codBarraLibro"));
                prestamoTmp.setIdProfesor(rs.getString("idProfesor"));
                prestamoTmp.setIdBibliotecario(rs.getString("idBibliotecario"));
                prestamoTmp.setFechaPrestamo(rs.getDate("fechaPrestamo"));
                prestamoTmp.setFechaDevolucion(rs.getDate("fechaDevolucion"));
                prestamoTmp.setNumRenovaciones(rs.getInt("numRenovaciones"));
                prestamoTmp.setDevuelto(rs.getString("devuelto"));
                prestamos.add(prestamoTmp);
            }

            rs.close();
        } catch (SQLException e) {
            System.out.println("Error al realizar el readALLDAO, en préstamo libro profesor");
        }

        return prestamos;
    }

    /**
     * el método realiza la consulta del código del préstamo de un libro del
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
            rs = stmt.executeQuery("SELECT codPrestLibroProf FROM Prestamo_Libro_Profesor "
                    + "WHERE codBarraLibro = '" + codBarra + "' AND devuelto = 'no';");

            while (rs.next()) {
                codPrestamo = rs.getInt("codPrestLibroProf");
            }

            rs.close();
        } catch (SQLException e) {
            System.out.println("Error al realizar el readCodigoDAO, en préstamo libro profesor");
        }

        return codPrestamo;
    }

}

package moduloPrestamo.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import modelo.ConnectionBD;
import moduloPrestamo.entitys.PrestamoLibroEst;

/**
 * clase que realiza el CRUD sobre la entidad PrestamoLibroEst.
 *
 * @author Julian
 * @creado 11/08/2019
 * @author Miguel Fernández
 * @modificado 24/08/2019
 */
public class PrestamoLibroDAOEst extends PrestamoRecursoDAOAbs<PrestamoLibroEst> {

    private int diasPrestamo = 0;

    /**
     * constructor de la clase sin parámetros.
     */
    public PrestamoLibroDAOEst() {
        connection = ConnectionBD.getInstance();
    }

    /**
     * constructor de la clase con parámetros.
     *
     * @param diasPrestamo
     */
    public PrestamoLibroDAOEst(int diasPrestamo) {
        this.diasPrestamo = diasPrestamo;
        connection = ConnectionBD.getInstance();
    }

    /**
     * el método realiza el INSERT en la BD del préstamo de un libro al
     * estudiante.
     *
     * @param prestamo
     * @return boolean
     */
    @Override
    public boolean createDAO(PrestamoLibroEst prestamo) {
        String sqlSentence = "INSERT INTO Prestamo_Libro_Estudiante "
                + "(codBarraLibro, codEstudiante, idBibliotecario, fechaPrestamo, fechaDevolucion, numRenovaciones, devuelto)"
                + " VALUES (?,?,?, CURRENT_DATE(), CURRENT_DATE() + " + diasPrestamo + ",?, 'no')";

        PreparedStatement pps;

        try {
            pps = connection.getConnection().prepareStatement(sqlSentence);
            pps.setString(1, prestamo.getCodBarraLibro());
            pps.setString(2, prestamo.getCodEstudiante());
            pps.setString(3, prestamo.getIdBibliotecario());
            pps.setInt(4, prestamo.getNumRenovaciones());

            if (pps.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Error al realizar el createDAO, en préstamo libro estudiante");
        }
        return false;
    }

    /**
     * el método realiza la consulta del préstamo de un libro del estudiante en
     * la BD, por medio de un código.
     *
     * @param codigo
     * @return prestamo
     */
    @Override
    public PrestamoLibroEst readDAO(int codigo) {
        Statement stmt;
        ResultSet rs;
        PrestamoLibroEst prestamo = null;

        try {
            stmt = connection.getConnection().createStatement();
            rs = stmt.executeQuery("SELECT * FROM Prestamo_Libro_Estudiante WHERE codPrestLibroEst = " + codigo + ";");

            while (rs.next()) {
                prestamo = new PrestamoLibroEst();
                prestamo.setCodPrestamoLibroEst(rs.getInt("codPrestLibroEst"));
                prestamo.setCodBarraLibro(rs.getString("codBarraLibro"));
                prestamo.setCodEstudiante(rs.getString("codEstudiante"));
                prestamo.setIdBibliotecario(rs.getString("idBibliotecario"));
                prestamo.setFechaPrestamo(rs.getDate("fechaPrestamo"));
                prestamo.setFechaDevolucion(rs.getDate("fechaDevolucion"));
                prestamo.setNumRenovaciones(rs.getInt("numRenovaciones"));
                prestamo.setDevuelto(rs.getString("devuelto"));
            }

            rs.close();

            return prestamo;
        } catch (SQLException e) {
            System.out.println("Error al realizar el readDAO, en préstamo libro estudiante");
        }

        return prestamo;
    }

    /**
     * el metódo actuliza un atributo o todoas del préstamo de un libro del
     * estudiante.
     *
     * @param prestamo
     * @return boolean
     */
    @Override
    public boolean updateDAO(PrestamoLibroEst prestamo) {
        String sqlSentence;

        if (diasPrestamo == 15) {//cuando se realiza una renovación y se actuliza la fecha de devolución
            sqlSentence = "UPDATE Prestamo_Libro_Estudiante "
                    + "SET codBarraLibro = ?, codEstudiante = ?, idBibliotecario = ?, fechaPrestamo = ?, "
                    + "fechaDevolucion = CURRENT_DATE() + " + diasPrestamo + ", numRenovaciones = ?, "
                    + "devuelto = ? WHERE codPrestLibroEst = ?";
        } else {//cuando se dvuelve el recurso y se actuliza el atributo devuelto
            sqlSentence = "UPDATE Prestamo_Libro_Estudiante "
                    + "SET codBarraLibro = ?, codEstudiante = ?, idBibliotecario = ?, fechaPrestamo = ?, "
                    + "fechaDevolucion = '" + prestamo.getFechaDevolucion() + "', numRenovaciones = ?, "
                    + "devuelto = ? WHERE codPrestLibroEst = ?";
        }

        PreparedStatement pps;

        try {
            pps = connection.getConnection().prepareStatement(sqlSentence);

            pps.setString(1, prestamo.getCodBarraLibro());
            pps.setString(2, prestamo.getCodEstudiante());
            pps.setString(3, prestamo.getIdBibliotecario());
            pps.setDate(4, prestamo.getFechaPrestamo());
            pps.setInt(5, prestamo.getNumRenovaciones());
            pps.setString(6, prestamo.getDevuelto());
            pps.setInt(7, prestamo.getCodPrestamoLibroEst());

            if (pps.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Error al realizar el updateDAO, en préstamo libro estudiante");
        }

        return false;
    }

    /**
     * el metódo elimina el préstamo de un libro del estudiante.
     *
     * @param pk
     * @return boolean
     */
    @Override
    public boolean deleteDAO(int pk) {
        String sqlSentence = "DELETE FROM Prestamo_Libro_Estudiante WHERE codPrestLibroEst = ?";
        System.out.println(sqlSentence);
        PreparedStatement pps;

        try {
            pps = connection.getConnection().prepareStatement(sqlSentence);

            pps.setInt(1, pk);

            if (pps.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Error al realizar el deleteDAO, en préstamo libro estudiante");
        }

        return false;
    }

    /**
     * el metódo retorna una lista con todos los préstamos de libros a los
     * estudiantes.
     *
     * @return prestamos
     */
    @Override
    public List<PrestamoLibroEst> readAllDAO() {
        PreparedStatement pps;
        ResultSet rs;
        ArrayList<PrestamoLibroEst> prestamos = new ArrayList();

        try {
            pps = connection.getConnection().prepareStatement("SELECT * FROM Prestamo_Libro_Estudiante");
            rs = pps.executeQuery();

            while (rs.next()) {
                PrestamoLibroEst prestamoTmp = new PrestamoLibroEst();
                prestamoTmp.setCodPrestamoLibroEst(rs.getInt("codPrestLibroEst"));
                prestamoTmp.setCodBarraLibro(rs.getString("codBarraLibro"));
                prestamoTmp.setCodEstudiante(rs.getString("codEstudiante"));
                prestamoTmp.setIdBibliotecario(rs.getString("idBibliotecario"));
                prestamoTmp.setFechaPrestamo(rs.getDate("fechaPrestamo"));
                prestamoTmp.setFechaDevolucion(rs.getDate("fechaDevolucion"));
                prestamoTmp.setNumRenovaciones(rs.getInt("numRenovaciones"));
                prestamoTmp.setDevuelto(rs.getString("devuelto"));
                prestamos.add(prestamoTmp);
            }

            rs.close();
        } catch (SQLException e) {
            System.out.println("Error al realizar el readALLDAO, en préstamo libro estudiante");
        }

        return prestamos;
    }

    /**
     * el método realiza la consulta del código del préstamo de un libro del
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
            rs = stmt.executeQuery("SELECT codPrestLibroEst FROM Prestamo_Libro_Estudiante "
                    + "WHERE codBarraLibro = " + codBarra + " AND devuelto = 'no';");

            while (rs.next()) {
                codPrestamo = rs.getInt("codPrestLibroEst");
            }

            rs.close();
        } catch (SQLException e) {
            System.out.println("Error al realizar el readCodigoDAO, en préstamo libro estudiante");
        }

        return codPrestamo;
    }

}

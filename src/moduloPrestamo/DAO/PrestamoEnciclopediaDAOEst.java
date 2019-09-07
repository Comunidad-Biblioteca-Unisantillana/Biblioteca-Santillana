package moduloPrestamo.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import general.modelo.ConnectionBD;
import java.sql.Date;
import moduloPrestamo.entitys.PrestamoEnciclopediaEst;

/**
 * clase que realiza el CRUD sobre la entidad PrestamoEnciclopediaEst.
 *
 * @author Julian
 * @creado 11/08/2019
 * @author Miguel Fernández
 * @modificado 24/08/2019
 */
public class PrestamoEnciclopediaDAOEst extends PrestamoRecursoDAOAbs<PrestamoEnciclopediaEst> {

    /**
     * constructor de la clase sin parámetros.
     */
    public PrestamoEnciclopediaDAOEst() {
        connection = ConnectionBD.getInstance();
    }

    /**
     * el método realiza el INSERT en la BD del préstamo de una enciclopedia al
     * esudiante.
     *
     * @param prestamo
     * @return boolean
     */
    @Override
    public boolean createDAO(PrestamoEnciclopediaEst prestamo) {
        String sqlSentence = "INSERT INTO Prestamo_Enciclopedia_Estudiante "
                + "(codBarraEnciclopedia, codEstudiante, idBibliotecario, fechaPrestamo, fechaDevolucion, devuelto)"
                + " VALUES (?,?,?, current_date, current_date, 'no')";

        PreparedStatement pps;

        try {
            pps = connection.getConnection().prepareStatement(sqlSentence);
            pps.setString(1, prestamo.getCodBarraEnciclopedia());
            pps.setString(2, prestamo.getCodEstudiante());
            pps.setString(3, prestamo.getIdBibliotecario());

            if (pps.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Error al realizar el createDAO, en préstamo enciclopedia estudiante");
        }

        return false;
    }

    /**
     * el método realiza la consulta del préstamo de una enciclopedia del
     * estudiante en la BD, por medio de un código.
     *
     * @param codigo
     * @return
     */
    @Override
    public PrestamoEnciclopediaEst readDAO(int codigo) {
        Statement stmt;
        ResultSet rs;
        PrestamoEnciclopediaEst prestamo = null;

        try {
            stmt = connection.getConnection().createStatement();
            rs = stmt.executeQuery("SELECT * FROM Prestamo_Enciclopedia_Estudiante WHERE codPrestEncEst = '" + codigo + "';");

            while (rs.next()) {
                prestamo = new PrestamoEnciclopediaEst();
                prestamo.setCodPrestamoEnciclopediaEst(rs.getInt("codPrestEncEst"));
                prestamo.setCodBarraEnciclopedia(rs.getString("codBarraEnciclopedia"));
                prestamo.setCodEstudiante(rs.getString("codEstudiante"));
                prestamo.setIdBibliotecario(rs.getString("idBibliotecario"));
                prestamo.setFechaPrestamo(rs.getDate("fechaPrestamo"));
                prestamo.setFechaDevolucion(rs.getDate("fechaDevolucion"));
                prestamo.setDevuelto(rs.getString("devuelto"));
            }

            rs.close();

            return prestamo;
        } catch (SQLException e) {
            System.out.println("Error al realizar el readDAO, en préstamo enciclopedia estudiante");
        }

        return prestamo;
    }

    /**
     * el metódo actuliza un atributo o todos del préstamo de una enciclopedia
     * del estudiante.
     *
     * @param prestamo
     * @return boolean
     */
    @Override
    public boolean updateDAO(PrestamoEnciclopediaEst prestamo) {
        String sqlSentence = "UPDATE Prestamo_Enciclopedia_Estudiante "
                + "SET codBarraEnciclopedia = ?, codEstudiante = ?, idBibliotecario = ?, fechaPrestamo = ?, "
                + "fechaDevolucion = ?, devuelto = ? WHERE codPrestEncEst = ?";
        PreparedStatement pps;

        try {
            pps = connection.getConnection().prepareStatement(sqlSentence);

            pps.setString(1, prestamo.getCodBarraEnciclopedia());
            pps.setString(2, prestamo.getCodEstudiante());
            pps.setString(3, prestamo.getIdBibliotecario());
            pps.setDate(4, (Date) prestamo.getFechaPrestamo());
            pps.setDate(5, (Date) prestamo.getFechaDevolucion());
            pps.setString(6, prestamo.getDevuelto());
            pps.setInt(7, prestamo.getCodPrestamoEnciclopediaEst());

            if (pps.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Error al realizar el updateDAO, en préstamo enciclopedia estudiante");
        }

        return false;
    }

    /**
     * el metódo elimina el préstamo de una enciclopedia del estudiante.
     *
     * @param pk
     * @return
     */
    @Override
    public boolean deleteDAO(int pk) {
        String sqlSentence = "DELETE FROM Prestamo_Enciclopedia_Estudiante WHERE codPrestEncEst = ?";
        PreparedStatement pps;

        try {
            pps = connection.getConnection().prepareStatement(sqlSentence);
            pps.setInt(1, pk);

            if (pps.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Error al realizar el deleteDAO, en préstamo enciclopedia estudiante");
        }

        return false;
    }

    /**
     * el metódo retorna una lista con todos los préstamos de enciclopedias a
     * los estudiantes.
     *
     * @return prestamos
     */
    @Override
    public List<PrestamoEnciclopediaEst> readAllDAO() {
        PreparedStatement pps;
        ResultSet rs;
        ArrayList<PrestamoEnciclopediaEst> prestamos = new ArrayList();

        try {
            pps = connection.getConnection().prepareStatement("SELECT * FROM Prestamo_Enciclopedia_Estudiante");
            rs = pps.executeQuery();

            while (rs.next()) {
                PrestamoEnciclopediaEst prestamoTmp = new PrestamoEnciclopediaEst();
                prestamoTmp.setCodPrestamoEnciclopediaEst(rs.getInt("codPrestEncEst"));
                prestamoTmp.setCodBarraEnciclopedia(rs.getString("codBarraEnciclopedia"));
                prestamoTmp.setCodEstudiante(rs.getString("codEstudiante"));
                prestamoTmp.setIdBibliotecario(rs.getString("idBibliotecario"));
                prestamoTmp.setFechaPrestamo(rs.getDate("fechaPrestamo"));
                prestamoTmp.setFechaDevolucion(rs.getDate("fechaDevolucion"));
                prestamoTmp.setDevuelto(rs.getString("devuelto"));
                prestamos.add(prestamoTmp);
            }

            rs.close();
        } catch (SQLException e) {
            System.out.println("Error al realizar el readALLDAO, en préstamo enciclopedia estudiante");
        }

        return prestamos;
    }

    /**
     * el método realiza la consulta del código del préstamo de una enciclopedia
     * del estudiante en la BD, por medio de un código de barras.
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
            rs = stmt.executeQuery("SELECT codPrestEncEst FROM Prestamo_Enciclopedia_Estudiante "
                    + "WHERE codBarraEnciclopedia = '" + codBarra + "' AND devuelto = 'no';");

            while (rs.next()) {
                codPrestamo = rs.getInt("codPrestEncEst");
            }

            rs.close();
        } catch (SQLException e) {
            System.out.println("Error al realizar el readCodigoDAO, en préstamo enciclopedia estudiante");
        }

        return codPrestamo;
    }

}

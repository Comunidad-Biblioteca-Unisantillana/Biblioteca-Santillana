package moduloPrestamo.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import general.modelo.ConnectionBD;
import moduloPrestamo.entitys.PrestamoEnciclopediaProf;

/**
 * clase que realiza el CRUD sobre la entidad PrestamoEnciclopediaProf.
 *
 * @author Julian
 * @creado 11/08/2019
 * @author Miguel Fernández
 * @modificado 24/08/2019
 */
public class PrestamoEnciclopediaDAOProf extends PrestamoRecursoDAOAbs<PrestamoEnciclopediaProf> {

    /**
     * costructor de la clase sin parámetros.
     */
    public PrestamoEnciclopediaDAOProf() {
        connection = ConnectionBD.getInstance();
    }

    /**
     * el método realiza el INSERT en la BD del préstamo de una enciclopedia al
     * profesor.
     *
     * @param prestamo
     * @return boolean
     */
    @Override
    public boolean createDAO(PrestamoEnciclopediaProf prestamo) {
        String sqlSentence = "INSERT INTO Prestamo_Enciclopedia_Profesor "
                + "(codBarraEnciclopedia, idProfesor, idBibliotecario, fechaPrestamo, fechaDevolucion, devuelto)"
                + " VALUES (?,?,?, current_date, current_date, 'no')";
        PreparedStatement pps;

        try {
            pps = connection.getConnection().prepareStatement(sqlSentence);
            pps.setString(1, prestamo.getCodBarraEnciclopedia());
            pps.setString(2, prestamo.getIdProfesor());
            pps.setString(3, prestamo.getIdBibliotecario());

            if (pps.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Error al realizar el createDAO, en préstamo enciclopedia profesor");
        }

        return false;
    }

    /**
     * el método realiza la consulta del préstamo de una enciclopedia del
     * profesor en la BD, por medio de un código.
     *
     * @param codigo
     * @return prestamo
     */
    @Override
    public PrestamoEnciclopediaProf readDAO(int codigo) {
        Statement stmt;
        ResultSet rs;
        PrestamoEnciclopediaProf prestamo = null;

        try {
            stmt = connection.getConnection().createStatement();
            rs = stmt.executeQuery("SELECT * FROM Prestamo_Enciclopedia_Profesor WHERE codPrestEncProf = '" + codigo + "';");

            while (rs.next()) {
                prestamo = new PrestamoEnciclopediaProf();
                prestamo.setCodPrestamoEnciclopediaProf(rs.getInt("codPrestEncProf"));
                prestamo.setCodBarraEnciclopedia(rs.getString("codBarraEnciclopedia"));
                prestamo.setIdProfesor(rs.getString("idProfesor"));
                prestamo.setIdBibliotecario(rs.getString("idBibliotecario"));
                prestamo.setFechaPrestamo(rs.getDate("fechaPrestamo"));
                prestamo.setFechaDevolucion(rs.getDate("fechaDevolucion"));
                prestamo.setDevuelto(rs.getString("devuelto"));
            }

            rs.close();

            return prestamo;
        } catch (SQLException e) {
            System.out.println("Error al realizar el readDAO, en préstamo enciclopedia profesor");
        }

        return prestamo;
    }

    /**
     * el metódo actuliza un atributo o todos del préstamo de una enciclopedia
     * del profesor.
     *
     * @param prestamo
     * @return
     */
    @Override
    public boolean updateDAO(PrestamoEnciclopediaProf prestamo) {
        String sqlSentence = "UPDATE Prestamo_Enciclopedia_Profesor "
                + "SET codBarraEnciclopedia = ?, idProfesor = ?, idBibliotecario = ?, fechaPrestamo = ?, "
                + "fechaDevolucion = ?, devuelto = ? WHERE codPrestEncProf = ?";

        PreparedStatement pps;

        try {
            pps = connection.getConnection().prepareStatement(sqlSentence);

            pps.setString(1, prestamo.getCodBarraEnciclopedia());
            pps.setString(2, prestamo.getIdProfesor());
            pps.setString(3, prestamo.getIdBibliotecario());
            pps.setDate(4, prestamo.getFechaPrestamo());
            pps.setDate(5, prestamo.getFechaDevolucion());
            pps.setString(6, prestamo.getDevuelto());
            pps.setInt(7, prestamo.getCodPrestamoEnciclopediaProf());

            if (pps.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Error al realizar el updateDAO, en préstamo enciclopedia profesor");
        }

        return false;
    }

    /**
     * el metódo elimina el préstamo de una enciclopedia del profesor.
     *
     * @param pk
     * @return
     */
    @Override
    public boolean deleteDAO(int pk) {
        String sqlSentence = "DELETE FROM Prestamo_Enciclopedia_Profesor WHERE codPrestEncProf = ?";
        PreparedStatement pps;

        try {
            pps = connection.getConnection().prepareStatement(sqlSentence);
            pps.setInt(1, pk);

            if (pps.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Error al realizar el deleteDAO, en préstamo enciclopedia profesor");
        }

        return false;
    }

    /**
     * el metódo retorna una lista con todos los préstamos de enciclopedias a
     * los profesores.
     *
     * @return prestamos
     */
    @Override
    public List<PrestamoEnciclopediaProf> readAllDAO() {
        PreparedStatement pps;
        ResultSet rs;
        ArrayList<PrestamoEnciclopediaProf> prestamos = new ArrayList();

        try {
            pps = connection.getConnection().prepareStatement("SELECT * FROM Prestamo_Enciclopedia_Profesor");
            rs = pps.executeQuery();

            while (rs.next()) {
                PrestamoEnciclopediaProf prestamoTmp = new PrestamoEnciclopediaProf();
                prestamoTmp.setCodPrestamoEnciclopediaProf(rs.getInt("codPrestEncProf"));
                prestamoTmp.setCodBarraEnciclopedia(rs.getString("codBarraEnciclopedia"));
                prestamoTmp.setIdProfesor(rs.getString("idProfesor"));
                prestamoTmp.setIdBibliotecario(rs.getString("idBibliotecario"));
                prestamoTmp.setFechaPrestamo(rs.getDate("fechaPrestamo"));
                prestamoTmp.setFechaDevolucion(rs.getDate("fechaDevolucion"));
                prestamoTmp.setDevuelto(rs.getString("devuelto"));
                prestamos.add(prestamoTmp);
            }

            rs.close();
        } catch (SQLException e) {
            System.out.println("Error al realizar el readALLDAO, en préstamo enciclopedia profesor");
        }

        return prestamos;
    }

    /**
     * el método realiza la consulta del código del préstamo de una enciclopedia
     * del profesor en la BD, por medio de un código de barras.
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
            rs = stmt.executeQuery("SELECT codPrestEncProf FROM Prestamo_Enciclopedia_Profesor "
                    + "WHERE codBarraEnciclopedia = '" + codBarra + "' AND devuelto = 'no';");

            while (rs.next()) {
                codPrestamo = rs.getInt("codPrestEncProf");
            }

            rs.close();
        } catch (SQLException e) {
            System.out.println("Error al realizar el readCodigoDAO, en préstamo enciclopedia profesor");
        }

        return codPrestamo;
    }

}

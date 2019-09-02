package moduloMulta.DAO;

import general.modelo.ConnectionBD;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import moduloMulta.entitys.MultaRevistaEst;

/**
 * la clase se encarga de implementar el crud de la entidad multa revista
 * estudiante.
 *
 * @author Miguel Fernández
 * @creado: 30/08/2019
 * @author Miguel Fernández
 * @modificado: 30/08/2019
 */
public class MultaRevistaDAOEst extends MultaDAOAbs<MultaRevistaEst> {

    private ConnectionBD connection;

    /**
     * constructor de la clase sin parámetros.
     */
    public MultaRevistaDAOEst() {
        connection = ConnectionBD.getInstance();
    }

    /**
     * el método inserta una tupla en la entidad MultaRevistaEst base de datos.
     *
     * @param multa
     * @return boolean
     */
    @Override
    public boolean createDAO(MultaRevistaEst multa) {
        String sqlSentence = "INSERT INTO multa_revista_estudiante (codprestrevistaest, diasatrasados, codpreciomulta, "
                + "valortotalmulta, estadocancelacion, descripcioncancelacion, fechamulta)"
                + " VALUES (?,?,?,?,?,?, current_date);";
        PreparedStatement pps;

        try {
            pps = connection.getConnection().prepareStatement(sqlSentence);
            pps.setInt(1, multa.getCodMultaRevistaEst());
            pps.setInt(2, multa.getDiasAtrasados());
            pps.setInt(3, multa.getCodPrecioMulta());
            pps.setInt(4, multa.getValorMulta());
            pps.setString(5, multa.getEstadoCancelacion());
            pps.setString(6, multa.getDescripcionCancelacion());

            if (pps.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Error al realizar el CreateDAO, en multa revista estudiante");
        }

        return false;
    }

    /**
     * el método elimina una tupla de una entidad MultaRevistaEst de la base de
     * datos, por medio del código de la multa.
     *
     * @param pk
     * @return boolean
     */
    @Override
    public boolean deleteDAO(int pk) {
        PreparedStatement pps;
        String sqlSentence = "DELETE FROM multa_revista_estudiante WHERE codmultarevistaest = ?;";

        try {
            pps = connection.getConnection().prepareStatement(sqlSentence);
            pps.setInt(1, pk);

            if (pps.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException e) {
            System.err.println("Error al realizar el deleteDAO, en multa revista estudiante");
        }

        return false;
    }

    /**
     * el método obtiene todos los datos ingresados en la entidad
     * MultaRevistaEst de la base de datos.
     *
     * @return multas
     * @exception Exception Exception
     */
    @Override
    public List<MultaRevistaEst> readAllDAO() throws Exception {
        PreparedStatement pps;
        ResultSet rs;
        ArrayList<MultaRevistaEst> multas = new ArrayList();

        try {
            pps = connection.getConnection().prepareStatement("SELECT * FROM multa_revista_estudiante;");
            rs = pps.executeQuery();

            while (rs.next()) {
                MultaRevistaEst multaTmp = new MultaRevistaEst();
                multaTmp.setCodMultaRevistaEst(rs.getInt("codmultarevistaest"));
                multaTmp.setCodPrestamoRevistaEst(rs.getInt("codprestrevistaest"));
                multaTmp.setDiasAtrasados(rs.getInt("diasatrasados"));
                multaTmp.setCodPrecioMulta(rs.getInt("codpreciomulta"));
                multaTmp.setValorMulta(rs.getInt("valortotalmulta"));
                multaTmp.setEstadoCancelacion(rs.getString("estadocancelacion"));
                multaTmp.setDescripcionCancelacion(rs.getString("descripcioncancelacion"));
                multaTmp.setFechaMulta(rs.getDate("fechamulta"));
                multas.add(multaTmp);
            }

            rs.close();
        } catch (SQLException e) {
            System.out.println("Error al realizar el readAllDAO, en multa revista estudiante");
        }

        return multas;
    }

    /**
     * el método obtiene una tupla de la entidad MultaRevistaEst de la base de
     * datos, por medio del código de la multa.
     *
     * @param codigo
     * @return multa
     */
    @Override
    public MultaRevistaEst readDAO(int codigo) {
        Statement stmt;
        ResultSet rs;
        MultaRevistaEst multa = null;

        try {
            stmt = connection.getConnection().createStatement();
            rs = stmt.executeQuery("SELECT * FROM multa_revista_estudiante WHERE codmultarevistaest = " + codigo);

            while (rs.next()) {
                multa = new MultaRevistaEst();
                multa.setCodMultaRevistaEst(rs.getInt("codmultarevistaest"));
                multa.setCodPrestamoRevistaEst(rs.getInt("codprestrevistaest"));
                multa.setDiasAtrasados(rs.getInt("diasatrasados"));
                multa.setCodPrecioMulta(rs.getInt("codpreciomulta"));
                multa.setValorMulta(rs.getInt("valortotalmulta"));
                multa.setEstadoCancelacion(rs.getString("estadocancelacion"));
                multa.setDescripcionCancelacion(rs.getString("descripcioncancelacion"));
                multa.setFechaMulta(rs.getDate("fechamulta"));
            }

            rs.close();
        } catch (SQLException e) {
            System.out.println("Error al realizar el readAllDAO, en multa revista estudiante");
        }

        return multa;
    }

    /**
     * el método obtiene una tupla de la entidad MultaRevistaEst de la base de
     * datos, por medio del código del estudiante.
     *
     * @param idUsuario
     * @return boolean
     */
    @Override
    public boolean readIdUsuarioDAO(String idUsuario) {
        PreparedStatement pps;
        ResultSet rs;
        String sqlSentence = "SELECT mre.codmultarevistaest FROM multa_revista_estudiante mre, prestamo_revista_estudiante pre "
                + "WHERE mre.codprestrevistaest = pre.codprestrevistaest AND "
                + "pre.codEstudiante = ? AND mre.estadocancelacion = 'no pagada';";

        try {
            pps = connection.getConnection().prepareStatement(sqlSentence);
            pps.setString(1, idUsuario);
            rs = pps.executeQuery();

            while (rs.next()) {
                return true;
            }

            rs.close();
        } catch (SQLException e) {
            System.out.println("Error al realizar el readIdUsuarioDAO, en multa revista estudiante");
        }

        return false;
    }

    /**
     * el método actualiza una tupla de la entidad MultaRevistaProf de la base
     * de datos.
     *
     * @param multa
     * @return boolean
     */
    @Override
    public boolean updateDAO(MultaRevistaEst multa) {
        String sqlSentence = "UPDATE multa_revista_estudiante "
                + "SET codprestrevistaest = ?, diasatrasados = ?, codpreciomulta = ?, valortotalmulta = ?, "
                + "estadocancelacion = ?, descripcioncancelacion = ?, fechamulta = ? "
                + "WHERE codmultarevistaEst = ?";
        PreparedStatement pps;

        try {
            pps = connection.getConnection().prepareStatement(sqlSentence);
            pps.setInt(1, multa.getCodPrestamoRevistaEst());
            pps.setInt(2, multa.getDiasAtrasados());
            pps.setInt(3, multa.getCodPrecioMulta());
            pps.setInt(4, multa.getValorMulta());
            pps.setString(5, multa.getEstadoCancelacion());
            pps.setString(6, multa.getDescripcionCancelacion());
            pps.setDate(7, multa.getFechaMulta());
            pps.setInt(8, multa.getCodMultaRevistaEst());

            if (pps.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Error al realizar el updateDAO, en multa revista estudiante");
        }

        return false;
    }

}

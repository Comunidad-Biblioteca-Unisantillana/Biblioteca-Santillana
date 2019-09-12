package moduloMulta.DAO;

import general.modelo.ConnectionBD;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import moduloMulta.entitys.MultaRevistaProf;

/**
 * la clase se encarga de implementar el crud de la entidad multa revista
 * profesor.
 *
 * @author Miguel Fernández
 * @creado: 30/08/2019
 * @author Miguel Fernández
 * @modificado: 30/08/2019
 */
public class MultaRevistaDAOProf extends MultaDAOAbs<MultaRevistaProf> {

    /**
     * constructor de la clase sin paràmetros.
     */
    public MultaRevistaDAOProf() {
        connection = ConnectionBD.getInstance();
    }

    /**
     * el método inserta una tupla en la entidad MultaRevistaProf base de datos.
     *
     * @param multa
     * @return boolean
     */
    @Override
    public boolean createDAO(MultaRevistaProf multa) {
        String sqlSentence = "INSERT INTO multa_revista_profesor (codprestrevistaprof, diasatrasados, codpreciomulta, "
                + "valortotalmulta, estadocancelacion, descripcioncancelacion, fechamulta)"
                + " VALUES (?,?,?,?, 'no pagada', 'no aplica', current_date);";
        PreparedStatement pps;

        try {
            pps = connection.getConnection().prepareStatement(sqlSentence);
            pps.setInt(1, multa.getCodPrestamoRevistaProf());
            pps.setInt(2, multa.getDiasAtrasados());
            pps.setInt(3, multa.getCodPrecioMulta());
            pps.setInt(4, multa.getValorMulta());

            if (pps.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Error al realizar el CreateDAO, en multa revista profesor");
        }

        return false;
    }

    /**
     * el método elimina una tupla de una entidad MultaRevistaProf de la base de
     * datos, por medio del código de la multa.
     *
     * @param pk
     * @return boolean
     */
    @Override
    public boolean deleteDAO(int pk) {
        PreparedStatement pps;
        String sqlSentence = "DELETE FROM multa_revista_profesor WHERE codmultarevistaprof = ?;";

        try {
            pps = connection.getConnection().prepareStatement(sqlSentence);
            pps.setInt(1, pk);

            if (pps.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException e) {
            System.err.println("Error al realizar el deleteDAO, en multa revista profesor");
        }

        return false;
    }

    /**
     * el método obtiene todos los datos ingresados en la entidad
     * MultaRevistaProf de la base de datos.
     *
     * @return multas
     * @exception Exception Exception
     */
    @Override
    public List<MultaRevistaProf> readAllDAO() throws Exception {
        PreparedStatement pps;
        ResultSet rs;
        ArrayList<MultaRevistaProf> multas = new ArrayList();

        try {
            pps = connection.getConnection().prepareStatement("SELECT * FROM multa_revista_profesor;");
            rs = pps.executeQuery();

            while (rs.next()) {
                MultaRevistaProf multaTmp = new MultaRevistaProf();
                multaTmp.setCodMultaRevistaProf(rs.getInt("codmultarevistaprof"));
                multaTmp.setCodPrestamoRevistaProf(rs.getInt("codprestrevistaprof"));
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
            System.out.println("Error al realizar el readAllDAO, en multa revista profesor");
        }

        return multas;
    }

    /**
     * el método obtiene una tupla de la entidad MultaRevistaProf de la base de
     * datos, por medio del còdigo de la multa.
     *
     * @param codigo
     * @return multa
     */
    @Override
    public MultaRevistaProf readDAO(int codigo) {
        Statement stmt;
        ResultSet rs;
        MultaRevistaProf multa = null;

        try {
            stmt = connection.getConnection().createStatement();
            rs = stmt.executeQuery("SELECT * FROM multa_revista_profesor WHERE codmultarevistaprof = '" + codigo + "'"
                    + " AND estadocancelacion = 'no pagada';");

            while (rs.next()) {
                multa = new MultaRevistaProf();
                multa.setCodMultaRevistaProf(rs.getInt("codmultarevistaprof"));
                multa.setCodPrestamoRevistaProf(rs.getInt("codprestrevistaprof"));
                multa.setDiasAtrasados(rs.getInt("diasatrasados"));
                multa.setCodPrecioMulta(rs.getInt("codpreciomulta"));
                multa.setValorMulta(rs.getInt("valortotalmulta"));
                multa.setEstadoCancelacion(rs.getString("estadocancelacion"));
                multa.setDescripcionCancelacion(rs.getString("descripcioncancelacion"));
                multa.setFechaMulta(rs.getDate("fechamulta"));
            }

            rs.close();
        } catch (SQLException e) {
            System.out.println("Error al realizar el readAllDAO, en multa revista profesor");
        }

        return multa;
    }

    /**
     * el método obtiene una tupla de la entidad MultaRevistaProf de la base de
     * datos, por medio del código del usuario.
     *
     * @param idUsuario
     * @return boolean
     */
    @Override
    public boolean readIdUsuarioDAO(String idUsuario) {
        PreparedStatement pps;
        ResultSet rs;
        String sqlSentence = "SELECT mrp.codmultarevistaprof FROM multa_revista_profesor mrp, prestamo_revista_profesor prp "
                + "WHERE mrp.codprestrevistaprof = prp.codprestrevistaprof AND "
                + "prp.idprofesor = ? AND mrp.estadocancelacion = 'no pagada';";

        try {
            pps = connection.getConnection().prepareStatement(sqlSentence);
            pps.setString(1, idUsuario);
            rs = pps.executeQuery();

            while (rs.next()) {
                return true;
            }

            rs.close();
        } catch (SQLException e) {
            System.out.println("Error al realizar el readIdUsuarioDAO, en multa revista profesor");
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
    public boolean updateDAO(MultaRevistaProf multa) {
        String sqlSentence = "UPDATE multa_revista_profesor "
                + "SET codprestrevistaprof = ?, diasatrasados = ?, codpreciomulta = ?, valortotalmulta = ?, "
                + "estadocancelacion = ?, descripcioncancelacion = ?, fechamulta = ? "
                + "WHERE codmultarevistaprof = ?";
        PreparedStatement pps;

        try {
            pps = connection.getConnection().prepareStatement(sqlSentence);
            pps.setInt(1, multa.getCodPrestamoRevistaProf());
            pps.setInt(2, multa.getDiasAtrasados());
            pps.setInt(3, multa.getCodPrecioMulta());
            pps.setInt(4, multa.getValorMulta());
            pps.setString(5, multa.getEstadoCancelacion());
            pps.setString(6, multa.getDescripcionCancelacion());
            pps.setDate(7, multa.getFechaMulta());
            pps.setInt(8, multa.getCodMultaRevistaProf());

            if (pps.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Error al realizar el updateDAO, en multa revista profesor");
        }

        return false;
    }

}

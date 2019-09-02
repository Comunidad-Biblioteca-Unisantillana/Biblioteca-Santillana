package moduloMulta.DAO;

import general.modelo.ConnectionBD;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import moduloMulta.entitys.MultaEnciclopediaProf;

/**
 * la clase se encarga de implementar el crud de la entidad multa enciclopedia
 * profesor.
 *
 * @author Miguel Fernández
 * @creado: 30/08/2019
 * @author Miguel Fernández
 * @modificado: 31/08/2019
 */
public class MultaEnciclopediaDAOProf extends MultaDAOAbs<MultaEnciclopediaProf> {

    private ConnectionBD connection;

    /**
     * constructor de la clase sin paràmetros.
     */
    public MultaEnciclopediaDAOProf() {
        connection = general.modelo.ConnectionBD.getInstance();
    }

    /**
     * el método inserta una tupla en la entidad MultaLibroProf base de datos.
     *
     * @param multa
     * @return boolean
     */
    @Override
    public boolean createDAO(MultaEnciclopediaProf multa) {
        String sqlSentence = "INSERT INTO multa_encilopedia_profesor (codprestencprof, diasatrasados, codpreciomulta, "
                + "valortotalmulta, estadocancelacion, descripcioncancelacion, fechamulta)"
                + " VALUES (?,?,?,?,?,?, current_date);";
        PreparedStatement pps;

        try {
            pps = connection.getConnection().prepareStatement(sqlSentence);
            pps.setInt(1, multa.getCodMultaEnciclopediaProf());
            pps.setInt(2, multa.getDiasAtrasados());
            pps.setInt(3, multa.getCodPrecioMulta());
            pps.setInt(4, multa.getValorMulta());
            pps.setString(5, multa.getEstadoCancelacion());
            pps.setString(6, multa.getDescripcionCancelacion());

            if (pps.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Error al realizar el CreateDAO, en multa enciclopedia profesor");
        }

        return false;
    }

    /**
     * el método elimina una tupla de una entidad MultaEnciclopediaProf de la
     * base de datos, por medio del código de la multa.
     *
     * @param pk
     * @return boolean
     */
    @Override
    public boolean deleteDAO(int pk) {
        PreparedStatement pps;
        String sqlSentence = "DELETE FROM multa_enciclopedia_profesor WHERE codmultaencprof = ?;";

        try {
            pps = connection.getConnection().prepareStatement(sqlSentence);
            pps.setInt(1, pk);

            if (pps.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException e) {
            System.err.println("Error al realizar el deleteDAO, en multa enciclopedia profesor");
        }

        return false;
    }

    /**
     * el método obtiene todos los datos ingresados en la entidad
     * MultaEnciclopediaProf de la base de datos.
     *
     * @return multas
     * @exception Exception Exception
     */
    @Override
    public List<MultaEnciclopediaProf> readAllDAO() throws Exception {
        PreparedStatement pps;
        ResultSet rs;
        ArrayList<MultaEnciclopediaProf> multas = new ArrayList();

        try {
            pps = connection.getConnection().prepareStatement("SELECT * FROM multa_enciclopedia_profesor;");
            rs = pps.executeQuery();

            while (rs.next()) {
                MultaEnciclopediaProf multaTmp = new MultaEnciclopediaProf();
                multaTmp.setCodMultaEnciclopediaProf(rs.getInt("codmultaencprof"));
                multaTmp.setCodPrestamoEnciclopediaProf(rs.getInt("codprestencprof"));
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
            System.out.println("Error al realizar el readAllDAO, en multa enciclopedia profesor");
        }

        return multas;
    }

    /**
     * el método obtiene una tupla de la entidad MultaEnciclopediaProf de la
     * base de datos, por medio del código de la multa.
     *
     * @param codigo
     * @return multa
     */
    @Override
    public MultaEnciclopediaProf readDAO(int codigo) {
        Statement stmt;
        ResultSet rs;
        MultaEnciclopediaProf multa = null;

        try {
            stmt = connection.getConnection().createStatement();
            rs = stmt.executeQuery("SELECT * FROM multa_enciclopedia_profesor WHERE codmultaencprof = " + codigo);

            while (rs.next()) {
                multa = new MultaEnciclopediaProf();
                multa.setCodMultaEnciclopediaProf(rs.getInt("codmultaencprof"));
                multa.setCodPrestamoEnciclopediaProf(rs.getInt("codprestencprof"));
                multa.setDiasAtrasados(rs.getInt("diasatrasados"));
                multa.setCodPrecioMulta(rs.getInt("codpreciomulta"));
                multa.setValorMulta(rs.getInt("valortotalmulta"));
                multa.setEstadoCancelacion(rs.getString("estadocancelacion"));
                multa.setDescripcionCancelacion(rs.getString("descripcioncancelacion"));
                multa.setFechaMulta(rs.getDate("fechamulta"));
            }

            rs.close();
        } catch (SQLException e) {
            System.out.println("Error al realizar el readAllDAO, en multa enciclopedia profesor");
        }

        return multa;
    }

    /**
     * el método obtiene una tupla de la entidad MultaEnciclopediaProf de la
     * base de datos, por medio de la identificación del profesor.
     *
     * @param idUsuario
     * @return boolean
     */
    @Override
    public boolean readIdUsuarioDAO(String idUsuario) {
        PreparedStatement pps;
        ResultSet rs;
        String sqlSentence = "SELECT mep.codmultaencprof FROM multa_enciclopedia_profesor mep, prestamo_enciclopedia_profesor pep "
                + "WHERE mep.codprestencprof = pep.codprestencprof AND "
                + "pep.idprofesor = ? AND mep.estadocancelacion = 'no pagada';";

        try {
            pps = connection.getConnection().prepareStatement(sqlSentence);
            pps.setString(1, idUsuario);
            rs = pps.executeQuery();

            while (rs.next()) {
                return true;
            }

            rs.close();
        } catch (SQLException e) {
            System.out.println("Error al realizar el readIdUsuarioDAO, en multa enciclopedia profesor");
        }

        return false;
    }

    /**
     * el método actualiza una tupla de la entidad MultaEnciclopediaProf de la base de
     * datos.
     *
     * @param multa
     * @return boolean
     */
    @Override
    public boolean updateDAO(MultaEnciclopediaProf multa) {
        String sqlSentence = "UPDATE multa_enciclopedia_profesor "
                + "SET codprestencprof = ?, diasatrasados = ?, codpreciomulta = ?, valortotalmulta = ?, "
                + "estadocancelacion = ?, descripcioncancelacion = ?, fechamulta = ? "
                + "WHERE codmultaencprof = ?";
        PreparedStatement pps;

        try {
            pps = connection.getConnection().prepareStatement(sqlSentence);
            pps.setInt(1, multa.getCodPrestamoEnciclopediaProf());
            pps.setInt(2, multa.getDiasAtrasados());
            pps.setInt(3, multa.getCodPrecioMulta());
            pps.setInt(4, multa.getValorMulta());
            pps.setString(5, multa.getEstadoCancelacion());
            pps.setString(6, multa.getDescripcionCancelacion());
            pps.setDate(7, multa.getFechaMulta());
            pps.setInt(8, multa.getCodMultaEnciclopediaProf());

            if (pps.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Error al realizar el updateDAO, en multa enciclopedia profesor");
        }

        return false;
    }

}

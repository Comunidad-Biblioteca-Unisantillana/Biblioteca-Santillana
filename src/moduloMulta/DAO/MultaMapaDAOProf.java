package moduloMulta.DAO;

import general.modelo.ConnectionBD;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import moduloMulta.entitys.MultaMapaProf;

/**
 * la clase se encarga de implementar el crud de la entidad multa mapa profesor.
 *
 * @author Miguel Fernández
 * @creado: 30/08/2019
 * @author Miguel Fernández
 * @modificado: 31/08/2019
 */
public class MultaMapaDAOProf extends MultaDAOAbs<MultaMapaProf> {


    /**
     * constructor de la clase sin paràmetros.
     */
    public MultaMapaDAOProf() {
        connection = general.modelo.ConnectionBD.getInstance();
    }

    /**
     * el método inserta una tupla en la entidad MultaMapaProf base de datos.
     *
     * @param multa
     * @return boolean
     */
    @Override
    public boolean createDAO(MultaMapaProf multa) {
        String sqlSentence = "INSERT INTO multa_mapa_profesor (codprestmapaprof, diasatrasados, codpreciomulta, "
                + "valortotalmulta, estadocancelacion, descripcioncancelacion, fechamulta)"
                + " VALUES (?,?,?,?,?,?, current_date);";
        PreparedStatement pps;

        try {
            pps = connection.getConnection().prepareStatement(sqlSentence);
            pps.setInt(1, multa.getCodPrestamoMapaProf());
            pps.setInt(2, multa.getDiasAtrasados());
            pps.setInt(3, multa.getCodPrecioMulta());
            pps.setInt(4, multa.getValorMulta());
            pps.setString(5, multa.getEstadoCancelacion());
            pps.setString(6, multa.getDescripcionCancelacion());

            if (pps.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Error al realizar el CreateDAO, en multa mapa profesor");
        }

        return false;
    }

    /**
     * el método elimina una tupla de una entidad MultaMapaProf de la base de
     * datos, por medio del código de la multa.
     *
     * @param pk
     * @return boolean
     */
    @Override
    public boolean deleteDAO(int pk) {
        PreparedStatement pps;
        String sqlSentence = "DELETE FROM multa_mapa_profesor WHERE codmultamapaprof = ?;";

        try {
            pps = connection.getConnection().prepareStatement(sqlSentence);
            pps.setInt(1, pk);

            if (pps.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException e) {
            System.err.println("Error al realizar el deleteDAO, en multa mapa profesor");
        }

        return false;
    }

    /**
     * el método obtiene todos los datos ingresados en la entidad MultaMapaProf
     * de la base de datos.
     *
     * @return multas
     * @exception Exception Exception
     */
    @Override
    public List<MultaMapaProf> readAllDAO() throws Exception {
        PreparedStatement pps;
        ResultSet rs;
        ArrayList<MultaMapaProf> multas = new ArrayList();

        try {
            pps = connection.getConnection().prepareStatement("SELECT * FROM multa_mapa_profesor;");
            rs = pps.executeQuery();

            while (rs.next()) {
                MultaMapaProf multaTmp = new MultaMapaProf();
                multaTmp.setCodMultaMapaProf(rs.getInt("codmultamapaprof"));
                multaTmp.setCodPrestamoMapaProf(rs.getInt("codprestmapaprof"));
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
            System.out.println("Error al realizar el readAllDAO, en multa mapa profesor");
        }

        return multas;
    }

    /**
     * el método obtiene una tupla de la entidad MultaMapaProf de la base de
     * datos, por medio del código de la multa.
     *
     * @param codigo
     * @return multa
     */
    @Override
    public MultaMapaProf readDAO(int codigo) {
        Statement stmt;
        ResultSet rs;
        MultaMapaProf multa = null;

        try {
            stmt = connection.getConnection().createStatement();
            rs = stmt.executeQuery("SELECT * FROM multa_mapa_profesor WHERE codmultamapaprof = " + codigo);

            while (rs.next()) {
                multa = new MultaMapaProf();
                multa.setCodMultaMapaProf(rs.getInt("codmultamapaprof"));
                multa.setCodPrestamoMapaProf(rs.getInt("codprestmapaprof"));
                multa.setDiasAtrasados(rs.getInt("diasatrasados"));
                multa.setCodPrecioMulta(rs.getInt("codpreciomulta"));
                multa.setValorMulta(rs.getInt("valortotalmulta"));
                multa.setEstadoCancelacion(rs.getString("estadocancelacion"));
                multa.setDescripcionCancelacion(rs.getString("descripcioncancelacion"));
                multa.setFechaMulta(rs.getDate("fechamulta"));
            }

            rs.close();
        } catch (SQLException e) {
            System.out.println("Error al realizar el readAllDAO, en multa mapa profesor");
        }

        return multa;
    }

    /**
     * el método obtiene una tupla de la entidad MultaMapaProf de la base de
     * datos, por medio de la identificación del profesor.
     *
     * @param idUsuario
     * @return boolean
     */
    @Override
    public boolean readIdUsuarioDAO(String idUsuario) {
        PreparedStatement pps;
        ResultSet rs;
        String sqlSentence = "SELECT mmp.codmultamapaprof FROM multa_mapa_profesor mmp, prestamo_mapa_profesor pmp "
                + "WHERE mmp.codprestmapaprof = pmp.codprestmapaprof AND "
                + "pmp.idprofesor = ? AND mmp.estadocancelacion = 'no pagada';";

        try {
            pps = connection.getConnection().prepareStatement(sqlSentence);
            pps.setString(1, idUsuario);
            rs = pps.executeQuery();

            while (rs.next()) {
                return true;
            }

            rs.close();
        } catch (SQLException e) {
            System.out.println("Error al realizar el readIdUsuarioDAO, en multa mapa profesor");
        }

        return false;
    }

    /**
     * el método actualiza una tupla de la entidad MultaMapaProf de la base de
     * datos.
     *
     * @param multa
     * @return boolean
     */
    @Override
    public boolean updateDAO(MultaMapaProf multa) {
        String sqlSentence = "UPDATE multa_mapa_profesor "
                + "SET codprestmapaprof = ?, diasatrasados = ?, codpreciomulta = ?, valortotalmulta = ?, "
                + "estadocancelacion = ?, descripcioncancelacion = ?, fechamulta = ? "
                + "WHERE codmultamapaprof = ?";
        PreparedStatement pps;

        try {
            pps = connection.getConnection().prepareStatement(sqlSentence);
            pps.setInt(1, multa.getCodPrestamoMapaProf());
            pps.setInt(2, multa.getDiasAtrasados());
            pps.setInt(3, multa.getCodPrecioMulta());
            pps.setInt(4, multa.getValorMulta());
            pps.setString(5, multa.getEstadoCancelacion());
            pps.setString(6, multa.getDescripcionCancelacion());
            pps.setDate(7, multa.getFechaMulta());
            pps.setInt(8, multa.getCodMultaMapaProf());

            if (pps.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Error al realizar el updateDAO, en multa mapa profesor");
        }

        return false;
    }

}

package moduloMulta.DAO;

import general.modelo.ConnectionBD;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import moduloMulta.entitys.MultaMapaEst;

/**
 * la clase se encarga de implementar el crud de la entidad multa mapa
 * estudiante.
 *
 * @author Miguel Fernández
 * @creado: 30/08/2019
 * @author Miguel Fernández
 * @modificado: 31/08/2019
 */
public class MultaMapaDAOEst extends MultaDAOAbs<MultaMapaEst> {


    /**
     * constructor de la clase sin paràmetros.
     */
    public MultaMapaDAOEst() {
        connection = general.modelo.ConnectionBD.getInstance();
    }

    /**
     * el método inserta una tupla en la entidad MultaMapaEst base de datos.
     *
     * @param multa
     * @return boolean
     */
    @Override
    public boolean createDAO(MultaMapaEst multa) {
        String sqlSentence = "INSERT INTO multa_mapa_estudiante (codprestmapaest, diasatrasados, codpreciomulta, "
                + "valortotalmulta, estadocancelacion, descripcioncancelacion, fechamulta)"
                + " VALUES (?,?,?,?,?,?, current_date);";
        PreparedStatement pps;

        try {
            pps = connection.getConnection().prepareStatement(sqlSentence);
            pps.setInt(1, multa.getCodPrestamoMapaEst());
            pps.setInt(2, multa.getDiasAtrasados());
            pps.setInt(3, multa.getCodPrecioMulta());
            pps.setInt(4, multa.getValorMulta());
            pps.setString(5, multa.getEstadoCancelacion());
            pps.setString(6, multa.getDescripcionCancelacion());

            if (pps.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Error al realizar el CreateDAO, en multa mapa estudiante");
        }

        return false;
    }

    /**
     * el método elimina una tupla de una entidad MultaMapaEst de la base de
     * datos, por medio del código de la multa.
     *
     * @param pk
     * @return boolean
     */
    @Override
    public boolean deleteDAO(int pk) {
        PreparedStatement pps;
        String sqlSentence = "DELETE FROM multa_mapa_estudiante WHERE codmultamapaest = ?;";

        try {
            pps = connection.getConnection().prepareStatement(sqlSentence);
            pps.setInt(1, pk);

            if (pps.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException e) {
            System.err.println("Error al realizar el deleteDAO, en multa mapa estudiante");
        }

        return false;
    }

    /**
     * el método obtiene todos los datos ingresados en la entidad MultaMapaEst
     * de la base de datos.
     *
     * @return multas
     * @exception Exception Exception
     */
    @Override
    public List<MultaMapaEst> readAllDAO() throws Exception {
        PreparedStatement pps;
        ResultSet rs;
        ArrayList<MultaMapaEst> multas = new ArrayList();

        try {
            pps = connection.getConnection().prepareStatement("SELECT * FROM multa_mapa_estudiante;");
            rs = pps.executeQuery();

            while (rs.next()) {
                MultaMapaEst multaTmp = new MultaMapaEst();
                multaTmp.setCodMultaMapaEst(rs.getInt("codmultamapaest"));
                multaTmp.setCodPrestamoMapaEst(rs.getInt("codprestmapaest"));
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
            System.out.println("Error al realizar el readAllDAO, en multa mapa estudiante");
        }

        return multas;
    }

    /**
     * el método obtiene una tupla de la entidad MultaMapaEst de la base de
     * datos, por medio del còdigo del estudiante.
     *
     * @param codigo
     * @return multa
     */
    @Override
    public MultaMapaEst readDAO(int codigo) {
        Statement stmt;
        ResultSet rs;
        MultaMapaEst multa = null;

        try {
            stmt = connection.getConnection().createStatement();
            rs = stmt.executeQuery("SELECT * FROM multa_mapa_estudiante WHERE codmultmapaest = " + codigo);

            while (rs.next()) {
                multa = new MultaMapaEst();
                multa.setCodMultaMapaEst(rs.getInt("codmultamapaest"));
                multa.setCodPrestamoMapaEst(rs.getInt("codprestmapaest"));
                multa.setDiasAtrasados(rs.getInt("diasatrasados"));
                multa.setCodPrecioMulta(rs.getInt("codpreciomulta"));
                multa.setValorMulta(rs.getInt("valortotalmulta"));
                multa.setEstadoCancelacion(rs.getString("estadocancelacion"));
                multa.setDescripcionCancelacion(rs.getString("descripcioncancelacion"));
                multa.setFechaMulta(rs.getDate("fechamulta"));
            }

            rs.close();
        } catch (SQLException e) {
            System.out.println("Error al realizar el readAllDAO, en multa mapa estudiante");
        }

        return multa;
    }

    /**
     * el método obtiene una tupla de la entidad MultaMapaEst de la base de
     * datos, por medio del còdigo del estudiante.
     *
     * @param idUsuario
     * @return boolean
     */
    @Override
    public boolean readIdUsuarioDAO(String idUsuario) {
        PreparedStatement pps;
        ResultSet rs;
        String sqlSentence = "SELECT mme.codmultamapaest FROM multa_mapa_estudiante mme, prestamo_mapa_estudiante pme "
                + "WHERE mme.codprestmapaest = pme.codprestmapaest AND "
                + "pme.codestudiante = ? AND mme.estadocancelacion = 'no pagada';";

        try {
            pps = connection.getConnection().prepareStatement(sqlSentence);
            pps.setString(1, idUsuario);
            rs = pps.executeQuery();

            while (rs.next()) {
                return true;
            }

            rs.close();
        } catch (SQLException e) {
            System.out.println("Error al realizar el readIdUsuarioDAO, en multa mapa estudiante");
        }

        return false;
    }

    /**
     * el método actualiza una tupla de la entidad MultaMapaEst de la base de
     * datos.
     *
     * @param multa
     * @return boolean
     */
    @Override
    public boolean updateDAO(MultaMapaEst multa) {
        String sqlSentence = "UPDATE multa_mapa_estudiante "
                + "SET codprestmapaest = ?, diasatrasados = ?, codpreciomulta = ?, valortotalmulta = ?, "
                + "estadocancelacion = ?, descripcioncancelacion = ?, fechamulta = ? "
                + "WHERE codmultamapaest = ?";
        PreparedStatement pps;

        try {
            pps = connection.getConnection().prepareStatement(sqlSentence);
            pps.setInt(1, multa.getCodPrestamoMapaEst());
            pps.setInt(2, multa.getDiasAtrasados());
            pps.setInt(3, multa.getCodPrecioMulta());
            pps.setInt(4, multa.getValorMulta());
            pps.setString(5, multa.getEstadoCancelacion());
            pps.setString(6, multa.getDescripcionCancelacion());
            pps.setDate(7, multa.getFechaMulta());
            pps.setInt(8, multa.getCodMultaMapaEst());

            if (pps.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Error al realizar el updateDAO, en multa mapa estudiante");
        }

        return false;
    }

}

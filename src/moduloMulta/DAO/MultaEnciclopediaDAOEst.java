package moduloMulta.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import moduloMulta.entitys.MultaEnciclopediaEst;

/**
 * la clase se encarga de implementar el crud de la entidad multa enciclopedia
 * estudiante.
 *
 * @author Miguel Fernández
 * @creado: 30/08/2019
 * @author Miguel Fernández
 * @modificado: 31/08/2019
 */
public class MultaEnciclopediaDAOEst extends MultaDAOAbs<MultaEnciclopediaEst> {


    /**
     * constructor de la clase sin paràmetros.
     */
    public MultaEnciclopediaDAOEst() {
        connection = general.modelo.ConnectionBD.getInstance();
    }

    /**
     * el método inserta una tupla en la entidad MultaLibroProf base de datos.
     *
     * @param multa
     * @return boolean
     */
    @Override
    public boolean createDAO(MultaEnciclopediaEst multa) {
        String sqlSentence = "INSERT INTO multa_enciclopedia_estudiante (codprestencest, diasatrasados, codpreciomulta, "
                + "valortotalmulta, estadocancelacion, descripcioncancelacion, fechamulta)"
                + " VALUES (?,?,?,?, 'no pagada', 'no aplica', current_date);";
        PreparedStatement pps;

        try {
            pps = connection.getConnection().prepareStatement(sqlSentence);
            pps.setInt(1, multa.getCodPrestamoEnciclopediaEst());
            pps.setInt(2, multa.getDiasAtrasados());
            pps.setInt(3, multa.getCodPrecioMulta());
            pps.setInt(4, multa.getValorMulta());


            if (pps.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Error al realizar el CreateDAO, en multa enciclopedia estudiante " + e.getMessage());
        }

        return false;
    }

    /**
     * el método elimina una tupla de una entidad MultaEnciclopediaEst de la
     * base de datos, por medio del código de la multa.
     *
     * @param pk
     * @return boolean
     */
    @Override
    public boolean deleteDAO(int pk) {
        PreparedStatement pps;
        String sqlSentence = "DELETE FROM multa_enciclopedia_estudiante WHERE codmultaencest = ?;";

        try {
            pps = connection.getConnection().prepareStatement(sqlSentence);
            pps.setInt(1, pk);

            if (pps.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException e) {
            System.err.println("Error al realizar el deleteDAO, en multa enciclopedia estudiante");
        }

        return false;
    }

    /**
     * el método obtiene todos los datos ingresados en la entidad
     * MultaEnciclopediaEst de la base de datos.
     *
     * @return multas
     * @exception Exception Exception
     */
    @Override
    public List<MultaEnciclopediaEst> readAllDAO() throws Exception {
        PreparedStatement pps;
        ResultSet rs;
        ArrayList<MultaEnciclopediaEst> multas = new ArrayList();

        try {
            pps = connection.getConnection().prepareStatement("SELECT * FROM multa_enciclopedia_estudiante;");
            rs = pps.executeQuery();

            while (rs.next()) {
                MultaEnciclopediaEst multaTmp = new MultaEnciclopediaEst();
                multaTmp.setCodMultaEnciclopediaEst(rs.getInt("codmultaencest"));
                multaTmp.setCodPrestamoEnciclopediaEst(rs.getInt("codprestencest"));
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
            System.out.println("Error al realizar el readAllDAO, en multa enciclopedia estudiante " + e.getMessage());
        }

        return multas;
    }

    /**
     * el método obtiene una tupla de la entidad MultaEnciclopediaEst de la base
     * de datos, por medio del código de la multa.
     *
     * @param codigo
     * @return multa
     */
    @Override
    public MultaEnciclopediaEst readDAO(int codigo) {
        Statement stmt;
        ResultSet rs;
        MultaEnciclopediaEst multa = null;

        try {
            stmt = connection.getConnection().createStatement();
            rs = stmt.executeQuery("SELECT * FROM multa_enciclopedia_estudiante WHERE codmultaencest = '" + codigo + "'"
                    + " AND estadocancelacion = 'no pagada'");

            while (rs.next()) {
                multa = new MultaEnciclopediaEst();
                multa.setCodMultaEnciclopediaEst(rs.getInt("codmultaencest"));
                multa.setCodPrestamoEnciclopediaEst(rs.getInt("codprestencest"));
                multa.setDiasAtrasados(rs.getInt("diasatrasados"));
                multa.setCodPrecioMulta(rs.getInt("codpreciomulta"));
                multa.setValorMulta(rs.getInt("valortotalmulta"));
                multa.setEstadoCancelacion(rs.getString("estadocancelacion"));
                multa.setDescripcionCancelacion(rs.getString("descripcioncancelacion"));
                multa.setFechaMulta(rs.getDate("fechamulta"));
            }

            rs.close();
        } catch (SQLException e) {
            System.out.println("Error al realizar el readAllDAO, en multa enciclopedia estudiante");
        }

        return multa;
    }

    /**
     * el método obtiene una tupla de la entidad MultaEnciclopediaEst de la
     * base de datos, por medio de la identificación del profesor.
     *
     * @param idUsuario
     * @return boolean
     */
    @Override
    public boolean readIdUsuarioDAO(String idUsuario) {
        PreparedStatement pps;
        ResultSet rs;
        String sqlSentence = "SELECT mee.codmultaencest FROM multa_enciclopedia_estudiante mee, prestamo_enciclopedia_estudiante pee "
                + "WHERE mee.codprestencest = pee.codprestencest AND "
                + "pee.codEstudiante = ? AND mee.estadocancelacion = 'no pagada';";

        try {
            pps = connection.getConnection().prepareStatement(sqlSentence);
            pps.setString(1, idUsuario);
            rs = pps.executeQuery();

            while (rs.next()) {
                return true;
            }

            rs.close();
        } catch (SQLException e) {
            System.out.println("Error al realizar el readIdUsuarioDAO, en multa enciclopedia estudiante");
        }

        return false;
    }

    /**
     * el método actualiza una tupla de la entidad MultaEnciclopediaEst de la
     * base de datos.
     *
     * @param multa
     * @return boolean
     */
    @Override
    public boolean updateDAO(MultaEnciclopediaEst multa) {
        String sqlSentence = "UPDATE multa_enciclopedia_estudiante "
                + "SET codprestencest = ?, diasatrasados = ?, codpreciomulta = ?, valortotalmulta = ?, "
                + "estadocancelacion = ?, descripcioncancelacion = ?, fechamulta = ? "
                + "WHERE codmultaencest = ?";
        PreparedStatement pps;

        try {
            pps = connection.getConnection().prepareStatement(sqlSentence);
            pps.setInt(1, multa.getCodPrestamoEnciclopediaEst());
            pps.setInt(2, multa.getDiasAtrasados());
            pps.setInt(3, multa.getCodPrecioMulta());
            pps.setInt(4, multa.getValorMulta());
            pps.setString(5, multa.getEstadoCancelacion());
            pps.setString(6, multa.getDescripcionCancelacion());
            pps.setDate(7, multa.getFechaMulta());
            pps.setInt(8, multa.getCodMultaEnciclopediaEst());

            if (pps.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Error al realizar el updateDAO, en multa enciclopedia estudiante " + e.getMessage());
        }

        return false;
    }

}

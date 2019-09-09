package moduloMulta.DAO;

import general.modelo.ConnectionBD;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import moduloMulta.entitys.MultaLibroProf;

/**
 * la clase se encarga de implementar el crud de la entidad multa libro
 * profesor.
 *
 * @author Miguel Fernández
 * @creado: 30/08/2019
 * @author Miguel Fernández
 * @modificado: 31/08/2019
 */
public class MultaLibroDAOProf extends MultaDAOAbs<MultaLibroProf> {


    /**
     * constructor de la clase sin paràmetros.
     */
    public MultaLibroDAOProf() {
        connection = general.modelo.ConnectionBD.getInstance();
    }

    /**
     * el método inserta una tupla en la entidad MultaLibroProf base de datos.
     *
     * @param multa
     * @return boolean
     */
    @Override
    public boolean createDAO(MultaLibroProf multa) {
        String sqlSentence = "INSERT INTO multa_libro_profesor (codprestlibroprof, diasatrasados, codpreciomulta, "
                + "valortotalmulta, estadocancelacion, descripcioncancelacion, fechamulta)"
                + " VALUES (?,?,?,?,?,?, current_date);";
        PreparedStatement pps;

        try {
            pps = connection.getConnection().prepareStatement(sqlSentence);
            pps.setInt(1, multa.getCodPrestamoLibroProf());
            pps.setInt(2, multa.getDiasAtrasados());
            pps.setInt(3, multa.getCodPrecioMulta());
            pps.setInt(4, multa.getValorMulta());
            pps.setString(5, multa.getEstadoCancelacion());
            pps.setString(6, multa.getDescripcionCancelacion());

            if (pps.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Error al realizar el CreateDAO, en multa libro profesor");
        }

        return false;
    }

    /**
     * el método elimina una tupla de una entidad MultaLibroProf de la base de
     * datos, por medio del código de la multa.
     *
     * @param pk
     * @return boolean
     */
    @Override
    public boolean deleteDAO(int pk) {
        PreparedStatement pps;
        String sqlSentence = "DELETE FROM multa_libro_profesor WHERE codmultalibroprof = ?;";

        try {
            pps = connection.getConnection().prepareStatement(sqlSentence);
            pps.setInt(1, pk);

            if (pps.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException e) {
            System.err.println("Error al realizar el deleteDAO, en multa libro profesor");
        }

        return false;
    }

    /**
     * el método obtiene todos los datos ingresados en la entidad MultaLibroProf
     * de la base de datos.
     *
     * @return multas
     * @exception Exception Exception
     */
    @Override
    public List<MultaLibroProf> readAllDAO() throws Exception {
        PreparedStatement pps;
        ResultSet rs;
        ArrayList<MultaLibroProf> multas = new ArrayList();

        try {
            pps = connection.getConnection().prepareStatement("SELECT * FROM multa_libro_profesor;");
            rs = pps.executeQuery();

            while (rs.next()) {
                MultaLibroProf multaTmp = new MultaLibroProf();
                multaTmp.setCodMultaLibroProf(rs.getInt("codmultalibroprof"));
                multaTmp.setCodPrestamoLibroProf(rs.getInt("codprestlibroprof"));
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
            System.out.println("Error al realizar el readAllDAO, en multa libro profesor");
        }

        return multas;
    }

    /**
     * el método obtiene una tupla de la entidad MultaLibroProf de la base de
     * datos, por medio del código de la multa.
     *
     * @param codigo
     * @return multa
     */
    @Override
    public MultaLibroProf readDAO(int codigo) {
        Statement stmt;
        ResultSet rs;
        MultaLibroProf multa = null;

        try {
            stmt = connection.getConnection().createStatement();
            rs = stmt.executeQuery("SELECT * FROM multa_libro_profesor WHERE codmultalibroprof = " + codigo);

            while (rs.next()) {
                multa = new MultaLibroProf();
                multa.setCodMultaLibroProf(rs.getInt("codmultalibroprof"));
                multa.setCodPrestamoLibroProf(rs.getInt("codprestlibroprof"));
                multa.setDiasAtrasados(rs.getInt("diasatrasados"));
                multa.setCodPrecioMulta(rs.getInt("codpreciomulta"));
                multa.setValorMulta(rs.getInt("valortotalmulta"));
                multa.setEstadoCancelacion(rs.getString("estadocancelacion"));
                multa.setDescripcionCancelacion(rs.getString("descripcioncancelacion"));
                multa.setFechaMulta(rs.getDate("fechamulta"));
            }

            rs.close();
        } catch (SQLException e) {
            System.out.println("Error al realizar el readAllDAO, en multa libro profesor");
        }

        return multa;
    }

    /**
     * el método obtiene una tupla de la entidad MultaLibroProf de la base de
     * datos, por medio de la identificación del profesor.
     *
     * @param idUsuario
     * @return boolean
     */
    @Override
    public boolean readIdUsuarioDAO(String idUsuario) {
        PreparedStatement pps;
        ResultSet rs;
        String sqlSentence = "SELECT mlp.codmultalibroprof FROM multa_libro_profesor mlp, prestamo_libro_profesor plp "
                + "WHERE mlp.codprestlibroprof = plp.codprestlibroprof AND "
                + "plp.idprofesor = ? AND mlp.estadocancelacion = 'no pagada';";

        try {
            pps = connection.getConnection().prepareStatement(sqlSentence);
            pps.setString(1, idUsuario);
            rs = pps.executeQuery();

            while (rs.next()) {
                return true;
            }

            rs.close();
        } catch (SQLException e) {
            System.out.println("Error al realizar el readIdUsuarioDAO, en multa libro profesor");
        }

        return false;
    }

    /**
     * el método actualiza una tupla de la entidad MultaLibroProf de la base de
     * datos.
     *
     * @param multa
     * @return boolean
     */
    @Override
    public boolean updateDAO(MultaLibroProf multa) {
        String sqlSentence = "UPDATE multa_libro_profesor "
                + "SET codprestlibroprof = ?, diasatrasados = ?, codpreciomulta = ?, valortotalmulta = ?, "
                + "estadocancelacion = ?, descripcioncancelacion = ?, fechamulta = ? "
                + "WHERE codmultalibroprof = ?";
        PreparedStatement pps;

        try {
            pps = connection.getConnection().prepareStatement(sqlSentence);
            pps.setInt(1, multa.getCodPrestamoLibroProf());
            pps.setInt(2, multa.getDiasAtrasados());
            pps.setInt(3, multa.getCodPrecioMulta());
            pps.setInt(4, multa.getValorMulta());
            pps.setString(5, multa.getEstadoCancelacion());
            pps.setString(6, multa.getDescripcionCancelacion());
            pps.setDate(7, multa.getFechaMulta());
            pps.setInt(8, multa.getCodMultaLibroProf());

            if (pps.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Error al realizar el updateDAO, en multa libro profesor");
        }

        return false;
    }

}

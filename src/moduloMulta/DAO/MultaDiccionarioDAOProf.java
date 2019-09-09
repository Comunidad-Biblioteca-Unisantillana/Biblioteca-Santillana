package moduloMulta.DAO;

import general.modelo.ConnectionBD;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import moduloMulta.entitys.MultaDiccionarioProf;

/**
 * la clase se encarga de implementar el crud de la entidad multa diccionario
 * profesor.
 *
 * @author Miguel Fernández
 * @creado: 30/08/2019
 * @author Miguel Fernández
 * @modificado: 31/08/2019
 */
public class MultaDiccionarioDAOProf extends MultaDAOAbs<MultaDiccionarioProf> {

    private ConnectionBD connection;

    /**
     * constructor de la clase sin paràmetros.
     */
    public MultaDiccionarioDAOProf() {
        connection = general.modelo.ConnectionBD.getInstance();
    }

    /**
     * el método inserta una tupla en la entidad MultaProf base de datos.
     *
     * @param multa
     * @return boolean
     */
    @Override
    public boolean createDAO(MultaDiccionarioProf multa) {
        String sqlSentence = "INSERT INTO multa_diccionario_profesor (codprestdicprof, diasatrasados, codpreciomulta, "
                + "valortotalmulta, estadocancelacion, descripcioncancelacion, fechamulta)"
                + " VALUES (?,?,?,?,?,?, current_date);";
        PreparedStatement pps;

        try {
            pps = connection.getConnection().prepareStatement(sqlSentence);
            pps.setInt(1, multa.getCodPrestamoDiccionarioProf());
            pps.setInt(2, multa.getDiasAtrasados());
            pps.setInt(3, multa.getCodPrecioMulta());
            pps.setInt(4, multa.getValorMulta());
            pps.setString(5, multa.getEstadoCancelacion());
            pps.setString(6, multa.getDescripcionCancelacion());

            if (pps.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Error al realizar el CreateDAO, en multa diccionario profesor " + e.getMessage());
        }

        return false;
    }

    /**
     * el método elimina una tupla de una entidad MultaDiccionarioProf de la
     * base de datos, por medio del código de la multa.
     *
     * @param pk
     * @return boolean
     */
    @Override
    public boolean deleteDAO(int pk) {
        PreparedStatement pps;
        String sqlSentence = "DELETE FROM multa_diccionario_profesor WHERE codmultadicprof = ?;";

        try {
            pps = connection.getConnection().prepareStatement(sqlSentence);
            pps.setInt(1, pk);

            if (pps.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException e) {
            System.err.println("Error al realizar el deleteDAO, en multa diccionario profesor");
        }

        return false;
    }

    /**
     * el método obtiene todos los datos ingresados en la entidad
     * MultaDiccionarioProf de la base de datos.
     *
     * @return multas
     * @exception Exception Exception
     */
    @Override
    public List<MultaDiccionarioProf> readAllDAO() throws Exception {
        PreparedStatement pps;
        ResultSet rs;
        ArrayList<MultaDiccionarioProf> multas = new ArrayList();

        try {
            pps = connection.getConnection().prepareStatement("SELECT * FROM multa_diccionario_profesor;");
            rs = pps.executeQuery();

            while (rs.next()) {
                MultaDiccionarioProf multaTmp = new MultaDiccionarioProf();
                multaTmp.setCodMultaDiccionarioProf(rs.getInt("codmultadicprof"));
                multaTmp.setCodPrestamoDiccionarioProf(rs.getInt("codprestdicprof"));
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
            System.out.println("Error al realizar el readAllDAO, en multa diccionario profesor " + e.getMessage());
        }

        return multas;
    }

    /**
     * el método obtiene una tupla de la entidad MultaDiccionarioProf de la base
     * de datos, por medio del código de la multa.
     *
     * @param codigo
     * @return multa
     */
    @Override
    public MultaDiccionarioProf readDAO(int codigo) {
        Statement stmt;
        ResultSet rs;
        MultaDiccionarioProf multa = null;

        try {
            stmt = connection.getConnection().createStatement();
            rs = stmt.executeQuery("SELECT * FROM multa_diccionario_profesor WHERE codmultadicprof = " + codigo);

            while (rs.next()) {
                multa = new MultaDiccionarioProf();
                multa.setCodMultaDiccionarioProf(rs.getInt("codmultadicprof"));
                multa.setCodPrestamoDiccionarioProf(rs.getInt("codprestdicprof"));
                multa.setDiasAtrasados(rs.getInt("diasatrasados"));
                multa.setCodPrecioMulta(rs.getInt("codpreciomulta"));
                multa.setValorMulta(rs.getInt("valortotalmulta"));
                multa.setEstadoCancelacion(rs.getString("estadocancelacion"));
                multa.setDescripcionCancelacion(rs.getString("descripcioncancelacion"));
                multa.setFechaMulta(rs.getDate("fechamulta"));
            }

            rs.close();
        } catch (SQLException e) {
            System.out.println("Error al realizar el readAllDAO, en multa diccionario profesor");
        }

        return multa;
    }

    /**
     * el método obtiene una tupla de la entidad MultaDiccionarioProf de la base
     * de datos, por medio de la identificación del profesor.
     *
     * @param idUsuario
     * @return boolean
     */
    @Override
    public boolean readIdUsuarioDAO(String idUsuario) {
        PreparedStatement pps;
        ResultSet rs;
        String sqlSentence = "SELECT mdp.codmultadicprof FROM multa_diccionario_profesor mdp, prestamo_diccionario_profesor pdp "
                + "WHERE mdp.codprestdicprof = pdp.codprestdicprof AND "
                + "pdp.idprofesor = ? AND mdp.estadocancelacion = 'no pagada';";

        try {
            pps = connection.getConnection().prepareStatement(sqlSentence);
            pps.setString(1, idUsuario);
            rs = pps.executeQuery();

            while (rs.next()) {
                return true;
            }

            rs.close();
        } catch (SQLException e) {
            System.out.println("Error al realizar el readIdUsuarioDAO, en multa diccionario profesor");
        }

        return false;
    }

    /**
     * el método actualiza una tupla de la entidad MultaDiccionarioProf de la
     * base de datos.
     *
     * @param multa
     * @return boolean
     */
    @Override
    public boolean updateDAO(MultaDiccionarioProf multa) {
        String sqlSentence = "UPDATE multa_diccionario_profesor "
                + "SET codprestdicprof = ?, diasatrasados = ?, codpreciomulta = ?, valortotalmulta = ?, "
                + "estadocancelacion = ?, descripcioncancelacion = ?, fechamulta = ? "
                + "WHERE codmultadicprof = ?";
        PreparedStatement pps;

        try {
            pps = connection.getConnection().prepareStatement(sqlSentence);
            pps.setInt(1, multa.getCodPrestamoDiccionarioProf());
            pps.setInt(2, multa.getDiasAtrasados());
            pps.setInt(3, multa.getCodPrecioMulta());
            pps.setInt(4, multa.getValorMulta());
            pps.setString(5, multa.getEstadoCancelacion());
            pps.setString(6, multa.getDescripcionCancelacion());
            pps.setDate(7, multa.getFechaMulta());
            pps.setInt(8, multa.getCodMultaDiccionarioProf());

            if (pps.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Error al realizar el updateDAO, en multa diccionario profesor " + e.getMessage());
        }

        return false;
    }

}

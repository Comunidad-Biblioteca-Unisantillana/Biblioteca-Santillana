package moduloMulta.DAO;

import general.modelo.ConnectionBD;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import moduloMulta.entitys.MultaLibroEst;

/**
 * la clase se encarga de implementar el crud de la entidad multa libro
 * estudiante.
 *
 * @author Miguel Fernández
 * @creado: 30/08/2019
 * @author Miguel Fernández
 * @modificado: 31/08/2019
 */
public class MultaLibroDAOEst extends MultaDAOAbs<MultaLibroEst> {


    /**
     * constructor de la clase sin paràmetros.
     */
    public MultaLibroDAOEst() {
        connection = general.modelo.ConnectionBD.getInstance();
    }

    /**
     * el método inserta una tupla en la entidad MultaLibroEst base de datos.
     *
     * @param multa
     * @return boolean
     */
    @Override
    public boolean createDAO(MultaLibroEst multa) {
        String sqlSentence = "INSERT INTO multa_libro_estudiante (codprestlibroest, diasatrasados, codpreciomulta, "
                + "valortotalmulta, estadocancelacion, descripcioncancelacion, fechamulta)"
                + " VALUES (?,?,?,?,?,?, current_date);";
        PreparedStatement pps;

        try {
            pps = connection.getConnection().prepareStatement(sqlSentence);
            pps.setInt(1, multa.getCodPrestamoLibroEst());
            pps.setInt(2, multa.getDiasAtrasados());
            pps.setInt(3, multa.getCodPrecioMulta());
            pps.setInt(4, multa.getValorMulta());
            pps.setString(5, multa.getEstadoCancelacion());
            pps.setString(6, multa.getDescripcionCancelacion());

            if (pps.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Error al realizar el CreateDAO, en multa libro estudiante " + e.getMessage());
        }

        return false;
    }

    /**
     * el método elimina una tupla de una entidad MultaLibroEst de la base de
     * datos, por medio del código de la multa.
     *
     * @param pk
     * @return boolean
     */
    @Override
    public boolean deleteDAO(int pk) {
        PreparedStatement pps;
        String sqlSentence = "DELETE FROM multa_libro_estudiante WHERE codmultalibroest = ?;";

        try {
            pps = connection.getConnection().prepareStatement(sqlSentence);
            pps.setInt(1, pk);

            if (pps.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException e) {
            System.err.println("Error al realizar el deleteDAO, en multa libro estudiante");
        }

        return false;
    }

    /**
     * el método obtiene todos los datos ingresados en la entidad MultaLibroEst
     * de la base de datos.
     *
     * @return multas
     * @exception Exception Exception
     */
    @Override
    public List<MultaLibroEst> readAllDAO() throws Exception {
        PreparedStatement pps;
        ResultSet rs;
        ArrayList<MultaLibroEst> multas = new ArrayList();

        try {
            pps = connection.getConnection().prepareStatement("SELECT * FROM multa_libro_estudiante;");
            rs = pps.executeQuery();

            while (rs.next()) {
                MultaLibroEst multaTmp = new MultaLibroEst();
                multaTmp.setCodMultaLibroEst(rs.getInt("codmultalibroest"));
                multaTmp.setCodPrestamoLibroEst(rs.getInt("codprestlibroest"));
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
            System.out.println("Error al realizar el readAllDAO, en multa libro estudiante");
        }

        return multas;
    }

    /**
     * el método obtiene una tupla de la entidad MultaLibroEst de la base de
     * datos, por medio del código de la multa.
     *
     * @param codigo
     * @return multa
     */
    @Override
    public MultaLibroEst readDAO(int codigo) {
        Statement stmt;
        ResultSet rs;
        MultaLibroEst multa = null;

        try {
            stmt = connection.getConnection().createStatement();
            rs = stmt.executeQuery("SELECT * FROM multa_libro_estudiante WHERE codmultalibroest = " + codigo);

            while (rs.next()) {
                multa = new MultaLibroEst();
                multa.setCodMultaLibroEst(rs.getInt("codmultalibroest"));
                multa.setCodPrestamoLibroEst(rs.getInt("codprestlibroest"));
                multa.setDiasAtrasados(rs.getInt("diasatrasados"));
                multa.setCodPrecioMulta(rs.getInt("codpreciomulta"));
                multa.setValorMulta(rs.getInt("valortotalmulta"));
                multa.setEstadoCancelacion(rs.getString("estadocancelacion"));
                multa.setDescripcionCancelacion(rs.getString("descripcioncancelacion"));
                multa.setFechaMulta(rs.getDate("fechamulta"));
            }

            rs.close();
        } catch (SQLException e) {
            System.out.println("Error al realizar el readAllDAO, en multa libro estudiante");
        }

        return multa;
    }

    /**
     * el método obtiene una tupla de la entidad MultaLibroEst de la base de
     * datos, por medio del código del estudiante.
     *
     * @param idUsuario
     * @return boolean
     */
    @Override
    public boolean readIdUsuarioDAO(String idUsuario) {
        PreparedStatement pps;
        ResultSet rs;
        String sqlSentence = "SELECT mle.codmultalibroest FROM multa_libro_estudiante mle, prestamo_libro_estudiante ple "
                + "WHERE mle.codprestlibroest = ple.codprestlibroest AND "
                + "ple.codEstudiante = ? AND mle.estadocancelacion = 'no pagada';";

        try {
            pps = connection.getConnection().prepareStatement(sqlSentence);
            pps.setString(1, idUsuario);
            rs = pps.executeQuery();

            while (rs.next()) {
                return true;
            }

            rs.close();
        } catch (SQLException e) {
            System.out.println("Error al realizar el readIdUsuarioDAO, en multa libro estudiante");
        }

        return false;
    }

    /**
     * el método actualiza una tupla de la entidad MultaLibroEst de la base de
     * datos.
     *
     * @param multa
     * @return boolean
     */
    @Override
    public boolean updateDAO(MultaLibroEst multa) {
        String sqlSentence = "UPDATE multa_libro_estudiante "
                + "SET codprestlibroest = ?, diasatrasados = ?, codpreciomulta = ?, valortotalmulta = ?, "
                + "estadocancelacion = ?, descripcioncancelacion = ?, fechamulta = ? "
                + "WHERE codmultalibroest = ?";
        PreparedStatement pps;

        try {
            pps = connection.getConnection().prepareStatement(sqlSentence);
            pps.setInt(1, multa.getCodPrestamoLibroEst());
            pps.setInt(2, multa.getDiasAtrasados());
            pps.setInt(3, multa.getCodPrecioMulta());
            pps.setInt(4, multa.getValorMulta());
            pps.setString(5, multa.getEstadoCancelacion());
            pps.setString(6, multa.getDescripcionCancelacion());
            pps.setDate(7, multa.getFechaMulta());
            pps.setInt(8, multa.getCodMultaLibroEst());

            if (pps.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Error al realizar el updateDAO, en multa libro estudiante");
        }

        return false;
    }

}

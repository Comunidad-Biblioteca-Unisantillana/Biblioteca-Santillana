package moduloMulta.DAO;

import general.modelo.ConnectionBD;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import moduloMulta.entitys.MultaPeriodicoEst;

/**
 * la clase se encarga de implementar el crud de la entidad multa periòdico
 * estudiante.
 *
 * @author Miguel Fernández
 * @creado: 30/08/2019
 * @author Miguel Fernández
 * @modificado: 31/08/2019
 */
public class MultaPeriodicoDAOEst extends MultaDAOAbs<MultaPeriodicoEst> {

    private ConnectionBD connection;

    /**
     * constructor de la clase sin paràmetros.
     */
    public MultaPeriodicoDAOEst() {
        connection = ConnectionBD.getInstance();
    }

    /**
     * el método inserta una tupla en la entidad MultaPeriodicoEst base de
     * datos.
     *
     * @param multa
     * @return boolean
     */
    @Override
    public boolean createDAO(MultaPeriodicoEst multa) {
        String sqlSentence = "INSERT INTO multa_periodico_estudiante (codprestperiodicoest, diasatrasados, codpreciomulta, "
                + "valortotalmulta, estadocancelacion, descripcioncancelacion, fechamulta)"
                + " VALUES (?,?,?,?,?,?, current_date);";
        PreparedStatement pps;

        try {
            pps = connection.getConnection().prepareStatement(sqlSentence);
            pps.setInt(1, multa.getCodPrestamoPeriodicoEst());
            pps.setInt(2, multa.getDiasAtrasados());
            pps.setInt(3, multa.getCodPrecioMulta());
            pps.setInt(4, multa.getValorMulta());
            pps.setString(5, multa.getEstadoCancelacion());
            pps.setString(6, multa.getDescripcionCancelacion());

            if (pps.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Error al realizar el CreateDAO, en multa periódico estudiante");
        }

        return false;
    }

    /**
     * el método elimina una tupla de una entidad MultaPeriodicoEst de la base
     * de datos, por medio del código de la multa.
     *
     * @param pk
     * @return boolean
     */
    @Override
    public boolean deleteDAO(int pk) {
        PreparedStatement pps;
        String sqlSentence = "DELETE FROM multa_periodico_estudiante WHERE codmultaperiodicoEst = ?;";

        try {
            pps = connection.getConnection().prepareStatement(sqlSentence);
            pps.setInt(1, pk);

            if (pps.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException e) {
            System.err.println("Error al realizar el deleteDAO, en multa periódico estudiante");
        }

        return false;
    }

    /**
     * el método obtiene todos los datos ingresados en la entidad
     * MultaPeriodicoEst de la base de datos.
     *
     * @return multas
     * @exception Exception Exception
     */
    @Override
    public List<MultaPeriodicoEst> readAllDAO() throws Exception {
        PreparedStatement pps;
        ResultSet rs;
        ArrayList<MultaPeriodicoEst> multas = new ArrayList();

        try {
            pps = connection.getConnection().prepareStatement("SELECT * FROM multa_periodico_estudiante;");
            rs = pps.executeQuery();

            while (rs.next()) {
                MultaPeriodicoEst multaTmp = new MultaPeriodicoEst();
                multaTmp.setCodMultaPeriodicoEst(rs.getInt("codmultaperiodicoest"));
                multaTmp.setCodPrestamoPeriodicoEst(rs.getInt("codprestperiodicoest"));
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
            System.out.println("Error al realizar el readAllDAO, en multa periódico estudiante");
        }

        return multas;
    }

    /**
     * el método obtiene una tupla de la entidad MultaPeriodicoEst de la base de
     * datos, por medio del código de la multa.
     *
     * @param codigo
     * @return multa
     */
    @Override
    public MultaPeriodicoEst readDAO(int codigo) {
        Statement stmt;
        ResultSet rs;
        MultaPeriodicoEst multa = null;

        try {
            stmt = connection.getConnection().createStatement();
            rs = stmt.executeQuery("SELECT * FROM multa_periodico_estudiante WHERE codmultaperiodicoest = " + codigo);

            while (rs.next()) {
                multa = new MultaPeriodicoEst();
                multa.setCodMultaPeriodicoEst(rs.getInt("codmultaperiodicoest"));
                multa.setCodPrestamoPeriodicoEst(rs.getInt("codprestperiodicoest"));
                multa.setDiasAtrasados(rs.getInt("diasatrasados"));
                multa.setCodPrecioMulta(rs.getInt("codpreciomulta"));
                multa.setValorMulta(rs.getInt("valortotalmulta"));
                multa.setEstadoCancelacion(rs.getString("estadocancelacion"));
                multa.setDescripcionCancelacion(rs.getString("descripcioncancelacion"));
                multa.setFechaMulta(rs.getDate("fechamulta"));
            }

            rs.close();
        } catch (SQLException e) {
            System.out.println("Error al realizar el readAllDAO, en multa periódico estudiante");
        }

        return multa;
    }

    /**
     * el método obtiene una tupla de la entidad MultaPeriodicoEst de la base de
     * datos, por medio del código del estudiante.
     *
     * @param idUsuario
     * @return boolean
     */
    @Override
    public boolean readIdUsuarioDAO(String idUsuario) {
        PreparedStatement pps;
        ResultSet rs;
        String sqlSentence = "SELECT mpe.codmultaperiodicoest FROM multa_periodico_estudiante mpe, prestamo_periodico_estudiante ppe "
                + "WHERE mpe.codprestperiodicoest = ppe.codprestperiodicoest AND "
                + "ppe.codEstudiante = ? AND mpe.estadocancelacion = 'no pagada';";

        try {
            pps = connection.getConnection().prepareStatement(sqlSentence);
            pps.setString(1, idUsuario);
            rs = pps.executeQuery();

            while (rs.next()) {
                return true;
            }

            rs.close();
        } catch (SQLException e) {
            System.out.println("Error al realizar el readIdUsuarioDAO, en multa periódico estudiante");
        }

        return false;
    }

    /**
     * el método actualiza una tupla de la entidad MultaPeriodicoEst de la base
     * de datos.
     *
     * @param multa
     * @return boolean
     */
    @Override
    public boolean updateDAO(MultaPeriodicoEst multa) {
        String sqlSentence = "UPDATE multa_periodico_estudiante "
                + "SET codprestperiodicoest = ?, diasatrasados = ?, codpreciomulta = ?, valortotalmulta = ?, "
                + "estadocancelacion = ?, descripcioncancelacion = ?, fechamulta = ? "
                + "WHERE codmultaperiodicoest = ?";
        PreparedStatement pps;

        try {
            pps = connection.getConnection().prepareStatement(sqlSentence);
            pps.setInt(1, multa.getCodPrestamoPeriodicoEst());
            pps.setInt(2, multa.getDiasAtrasados());
            pps.setInt(3, multa.getCodPrecioMulta());
            pps.setInt(4, multa.getValorMulta());
            pps.setString(5, multa.getEstadoCancelacion());
            pps.setString(6, multa.getDescripcionCancelacion());
            pps.setDate(7, multa.getFechaMulta());
            pps.setInt(8, multa.getCodMultaPeriodicoEst());

            if (pps.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Error al realizar el updateDAO, en multa periódico estudiante");
        }

        return false;
    }

}

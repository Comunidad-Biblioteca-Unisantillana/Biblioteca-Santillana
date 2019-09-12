package moduloMulta.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import moduloMulta.entitys.MultaDiccionarioEst;

/**
 * la clase se encarga de implementar el crud de la entidad multa diccionario
 * estudiante.
 *
 * @author Miguel Fernández
 * @creado: 30/08/2019
 * @author Miguel Fernández
 * @modificado: 31/08/2019
 */
public class MultaDiccionarioDAOEst extends MultaDAOAbs<MultaDiccionarioEst> {

    /**
     * constructor de la clase sin paràmetros.
     */
    public MultaDiccionarioDAOEst() {
        connection = general.modelo.ConnectionBD.getInstance();
    }

    /**
     * el método inserta una tupla en la entidad MultaProf base de datos.
     *
     * @param multa
     * @return boolean
     */
    @Override
    public boolean createDAO(MultaDiccionarioEst multa) {
        String sqlSentence = "INSERT INTO multa_diccionario_estudiante (codprestdicest, diasatrasados, codpreciomulta, "
                + "valortotalmulta, estadocancelacion, descripcioncancelacion, fechamulta)"
                + " VALUES (?,?,?,?, 'no pagada', 'no aplica', current_date);";
        PreparedStatement pps;

        try {
            pps = connection.getConnection().prepareStatement(sqlSentence);
            pps.setInt(1, multa.getCodPrestamoDiccionarioEst());
            pps.setInt(2, multa.getDiasAtrasados());
            pps.setInt(3, multa.getCodPrecioMulta());
            pps.setInt(4, multa.getValorMulta());

            if (pps.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Error al realizar el CreateDAO, en multa diccionario estudiante " + e.getMessage());
        }

        return false;
    }

    /**
     * el método elimina una tupla de una entidad MultaDiccionarioEst de la base
     * de datos, por medio del código de la multa.
     *
     * @param pk
     * @return boolean
     */
    @Override
    public boolean deleteDAO(int pk) {
        PreparedStatement pps;
        String sqlSentence = "DELETE FROM multa_diccionario_estudiante WHERE codmultadicest = ?;";

        try {
            pps = connection.getConnection().prepareStatement(sqlSentence);
            pps.setInt(1, pk);

            if (pps.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException e) {
            System.err.println("Error al realizar el deleteDAO, en multa diccionario estudiante");
        }

        return false;
    }

    /**
     * el método obtiene todos los datos ingresados en la entidad
     * MultaDiccionarioEst de la base de datos.
     *
     * @return multas
     * @exception Exception Exception
     */
    @Override
    public List<MultaDiccionarioEst> readAllDAO() throws Exception {
        PreparedStatement pps;
        ResultSet rs;
        ArrayList<MultaDiccionarioEst> multas = new ArrayList();

        try {
            pps = connection.getConnection().prepareStatement("SELECT * FROM multa_diccionario_estudiante;");
            rs = pps.executeQuery();

            while (rs.next()) {
                MultaDiccionarioEst multaTmp = new MultaDiccionarioEst();
                multaTmp.setCodMultaDiccionarioEst(rs.getInt("codmultadicest"));
                multaTmp.setCodPrestamoDiccionarioEst(rs.getInt("codprestdicest"));
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
            System.out.println("Error al realizar el readAllDAO, en multa diccionario estudiante " + e.getMessage());
        }

        return multas;
    }

    /**
     * el método obtiene una tupla de la entidad MultaDiccionarioEst de la base
     * de datos, por medio del código de la multa.
     *
     * @param codigo
     * @return multa
     */
    @Override
    public MultaDiccionarioEst readDAO(int codigo) {
        Statement stmt;
        ResultSet rs;
        MultaDiccionarioEst multa = null;

        try {
            stmt = connection.getConnection().createStatement();
            rs = stmt.executeQuery("SELECT * FROM multa_diccionario_estudiante WHERE codmultadicest = '" + codigo + "'"
                    + " AND estadocancelacion = 'no pagada';");

            while (rs.next()) {
                multa = new MultaDiccionarioEst();
                multa.setCodMultaDiccionarioEst(rs.getInt("codmultadicest"));
                multa.setCodPrestamoDiccionarioEst(rs.getInt("codprestdicest"));
                multa.setDiasAtrasados(rs.getInt("diasatrasados"));
                multa.setCodPrecioMulta(rs.getInt("codpreciomulta"));
                multa.setValorMulta(rs.getInt("valortotalmulta"));
                multa.setEstadoCancelacion(rs.getString("estadocancelacion"));
                multa.setDescripcionCancelacion(rs.getString("descripcioncancelacion"));
                multa.setFechaMulta(rs.getDate("fechamulta"));
            }

            rs.close();
        } catch (SQLException e) {
            System.out.println("Error al realizar el readAllDAO, en multa diccionario estudiante");
        }

        return multa;
    }

    /**
     * el método obtiene una tupla de la entidad MultaDiccionarioEst de la base
     * de datos, por medio del código del estudiante.
     *
     * @param idUsuario
     * @return boolean
     */
    @Override
    public boolean readIdUsuarioDAO(String idUsuario) {
        PreparedStatement pps;
        ResultSet rs;
        String sqlSentence = "SELECT mde.codmultadicest FROM multa_diccionario_estudiante mde, prestamo_diccionario_estudiante pde "
                + "WHERE mde.codprestdicEst = pde.codprestdicest AND "
                + "pde.codEstudiante = ? AND mde.estadocancelacion = 'no pagada';";

        try {
            pps = connection.getConnection().prepareStatement(sqlSentence);
            pps.setString(1, idUsuario);
            rs = pps.executeQuery();

            while (rs.next()) {
                return true;
            }

            rs.close();
        } catch (SQLException e) {
            System.out.println("Error al realizar el readIdUsuarioDAO, en multa diccionario estudiante");
        }

        return false;
    }

    /**
     * el método actualiza una tupla de la entidad MultaDiccionarioEst de la
     * base de datos.
     *
     * @param multa
     * @return boolean
     */
    @Override
    public boolean updateDAO(MultaDiccionarioEst multa) {
        String sqlSentence = "UPDATE multa_diccionario_estudiante "
                + "SET codprestdicest = ?, diasatrasados = ?, codpreciomulta = ?, valortotalmulta = ?, "
                + "estadocancelacion = ?, descripcioncancelacion = ?, fechamulta = ? "
                + "WHERE codmultadicest = ?";
        PreparedStatement pps;

        try {
            pps = connection.getConnection().prepareStatement(sqlSentence);
            pps.setInt(1, multa.getCodPrestamoDiccionarioEst());
            pps.setInt(2, multa.getDiasAtrasados());
            pps.setInt(3, multa.getCodPrecioMulta());
            pps.setInt(4, multa.getValorMulta());
            pps.setString(5, multa.getEstadoCancelacion());
            pps.setString(6, multa.getDescripcionCancelacion());
            pps.setDate(7, multa.getFechaMulta());
            pps.setInt(8, multa.getCodMultaDiccionarioEst());

            if (pps.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Error al realizar el updateDAO, en multa diccionario estudiante " + e.getMessage());
        }

        return false;
    }

}

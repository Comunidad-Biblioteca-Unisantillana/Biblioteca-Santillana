package moduloPrestamo.DAO;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import modelo.ConnectionBD;
import moduloPrestamo.entitys.PrestamoDiccionarioEst;

/**
 * clase que realiza el CRUD sobre la entidad PrestamoDiccionarioEst.
 *
 * @author Julian
 * @creado 10/08/2019
 * @author Miguel Fernández
 * @modificado 24/08/2019
 */
public class PrestamoDiccionarioDAOEst extends PrestamoRecursoDAOAbs<PrestamoDiccionarioEst> {

    /**
     * constructor de la clase sin parámetros.
     */
    public PrestamoDiccionarioDAOEst() {
        connection = ConnectionBD.getInstance();
    }

    /**
     * el método realiza el INSERT en la BD del préstamo de un diccionario al
     * esudiante.
     *
     * @param prestamo
     * @return boolean
     */
    @Override
    public boolean createDAO(PrestamoDiccionarioEst prestamo) {
        String sqlSentence = "INSERT INTO Prestamo_Diccionario_Estudiante "
                + "(codBarraDiccionario, codEstudiante, idBibliotecario, fechaPrestamo, fechaDevolucion, devuelto)"
                + " VALUES (?,?,?, CURRENT_DATE(), CURRENT_DATE(), 'no')";
        PreparedStatement pps;

        try {
            pps = connection.getConnection().prepareStatement(sqlSentence);
            pps.setString(1, prestamo.getCodBarraDiccionario());
            pps.setString(2, prestamo.getCodEstudiante());
            pps.setString(3, prestamo.getIdBibliotecario());

            if (pps.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Error al realizar el createDAO, en préstamo dicionario estudiante");
        }

        return false;
    }

    /**
     * el método realiza la consulta del préstamo de un diccionario del
     * estudiante en la BD, por medio de un código.
     *
     * @param codigo
     * @return prestamo
     */
    @Override
    public PrestamoDiccionarioEst readDAO(int codigo) {
        Statement stmt;
        ResultSet rs;
        PrestamoDiccionarioEst prestamo = null;

        try {
            stmt = connection.getConnection().createStatement();
            rs = stmt.executeQuery("SELECT * FROM Prestamo_Diccionario_Estudiante WHERE codPrestDicEst = " + codigo + ";");

            while (rs.next()) {
                prestamo = new PrestamoDiccionarioEst();
                prestamo.setCodPrestamoDiccionarioEst(rs.getInt("codPrestDicEst"));
                prestamo.setCodBarraDiccionario(rs.getString("codBarraDiccionario"));
                prestamo.setCodEstudiante(rs.getString("codEstudiante"));
                prestamo.setIdBibliotecario(rs.getString("idBibliotecario"));
                prestamo.setFechaPrestamo(rs.getDate("fechaPrestamo"));
                prestamo.setFechaDevolucion(rs.getDate("fechaDevolucion"));
                prestamo.setDevuelto(rs.getString("devuelto"));
            }

            rs.close();

            return prestamo;
        } catch (SQLException e) {
            System.out.println("Error al realizar el readDAO, en préstamo dicionario estudiante");
        }

        return prestamo;
    }

    /**
     * el metódo actuliza un atributo o todos del préstamo de un deccionario del
     * estudiante.
     *
     * @param prestamo
     * @return boolean
     */
    @Override
    public boolean updateDAO(PrestamoDiccionarioEst prestamo) {
        String sqlSentence = "UPDATE Prestamo_Diccionario_Estudiante "
                + "SET codBarraDiccionario = ?, codEstudiante = ?, idBibliotecario = ?, fechaPrestamo = ?, "
                + "fechaDevolucion = ?, devuelto = ? WHERE codPrestDicEst = ?";
        PreparedStatement pps;

        try {
            pps = connection.getConnection().prepareStatement(sqlSentence);
            pps.setString(1, prestamo.getCodBarraDiccionario());
            pps.setString(2, prestamo.getCodEstudiante());
            pps.setString(3, prestamo.getIdBibliotecario());
            pps.setDate(4, (Date) prestamo.getFechaPrestamo());
            pps.setDate(5, (Date) prestamo.getFechaDevolucion());
            pps.setString(6, prestamo.getDevuelto());
            pps.setInt(7, prestamo.getCodPrestamoDiccionarioEst());

            if (pps.executeUpdate() > 0) {
                return true;
            }

        } catch (SQLException e) {
            System.out.println("Error al realizar el updateDAO, en préstamo dicionario estudiante");
        }

        return false;
    }

    /**
     * el metódo elimina el préstamo de un diccionario del estudiante.
     *
     * @param pk
     * @return boolean
     */
    @Override
    public boolean deleteDAO(int pk) {
        String sqlSentence = "DELETE FROM Prestamo_Diccionario_Estudiante WHERE codPrestDicEst = ?";
        PreparedStatement pps;

        try {
            pps = connection.getConnection().prepareStatement(sqlSentence);
            pps.setInt(1, pk);

            if (pps.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Error al realizar el deleteDAO, en préstamo dicionario estudiante");
        }

        return false;
    }

    /**
     * el metódo retorna una lista con todos los préstamos de diccionarios a los
     * estudiantes.
     *
     * @return prestamos
     */
    @Override
    public List<PrestamoDiccionarioEst> readAllDAO() {
        PreparedStatement pps;
        ResultSet rs;
        ArrayList<PrestamoDiccionarioEst> prestamos = new ArrayList();

        try {
            pps = connection.getConnection().prepareStatement("SELECT * FROM Prestamo_Diccionario_Estudiante");
            rs = pps.executeQuery();

            while (rs.next()) {
                PrestamoDiccionarioEst prestamoTmp = new PrestamoDiccionarioEst();
                prestamoTmp.setCodPrestamoDiccionarioEst(rs.getInt("codPrestDicEst"));
                prestamoTmp.setCodBarraDiccionario(rs.getString("codBarraDiccionario"));
                prestamoTmp.setCodEstudiante(rs.getString("codEstudiante"));
                prestamoTmp.setIdBibliotecario(rs.getString("idBibliotecario"));
                prestamoTmp.setFechaPrestamo(rs.getDate("fechaPrestamo"));
                prestamoTmp.setFechaDevolucion(rs.getDate("fechaDevolucion"));
                prestamoTmp.setDevuelto(rs.getString("devuelto"));
                prestamos.add(prestamoTmp);
            }

            rs.close();
        } catch (SQLException e) {
            System.out.println("Error al realizar el readALLDAO, en préstamo dicionario estudiante");
        }

        return prestamos;
    }

    /**
     * el método realiza la consulta del código del préstamo de un diccionario
     * del estudiante en la BD, por medio de un código de barras.
     *
     * @param codBarra
     * @return codPrestamo
     */
    @Override
    public int readCodigoDAO(String codBarra) {
        Statement stmt;
        ResultSet rs;
        int codPrestamo = -1;

        try {
            stmt = connection.getConnection().createStatement();
            rs = stmt.executeQuery("SELECT codPrestDicEst FROM Prestamo_Diccionario_Estudiante WHERE codBarraDiccionario = " + codBarra + ";");

            while (rs.next()) {
                codPrestamo = rs.getInt("codPrestDicEst");
            }

            rs.close();
        } catch (SQLException e) {
            System.out.println("Error al realizar el readCodigoDAO, en préstamo dicionario estudiante");
        }

        return codPrestamo;
    }

}

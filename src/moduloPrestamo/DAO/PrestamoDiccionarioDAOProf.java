package moduloPrestamo.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import modelo.ConnectionBD;
import moduloPrestamo.entitys.PrestamoDiccionarioProf;

/**
 * Clase que realiza el CRUD sobre la entidad PrestamoDiccionarioProf.
 *
 * @author Julian
 * @creado 10/08/2019
 * @author Miguel Fernández
 * @modificado 24/08/2019
 */
public class PrestamoDiccionarioDAOProf extends PrestamoRecursoDAOAbs<PrestamoDiccionarioProf> {

    /**
     * constructor de la clase sin parámetros.
     */
    public PrestamoDiccionarioDAOProf() {
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
    public boolean createDAO(PrestamoDiccionarioProf prestamo) {
        String sqlSentence = "INSERT INTO Prestamo_Diccionario_Profesor "
                + "(codBarraDiccionario, idProfesor, idBibliotecario, fechaPrestamo, fechaDevolucion, devuelto)"
                + " VALUES (?,?,?, CURRENT_DATE(), CURRENT_DATE(), 'no')";
        PreparedStatement pps;

        try {
            pps = connection.getConnection().prepareStatement(sqlSentence);
            pps.setString(1, prestamo.getCodBarraDiccionario());
            pps.setString(2, prestamo.getIdProfesor());
            pps.setString(3, prestamo.getIdBibliotecario());

            if (pps.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Error al realizar el createDAO, en préstamo dicionario profesor");
        }

        return false;
    }

    /**
     * el método realiza la consulta del préstamo de un diccionario del profesor
     * en la BD, por medio de un código.
     *
     * @param codigo
     * @return prestamo
     */
    @Override
    public PrestamoDiccionarioProf readDAO(int codigo) {
        Statement stmt;
        ResultSet rs;
        PrestamoDiccionarioProf prestamo = null;

        try {
            stmt = connection.getConnection().createStatement();
            rs = stmt.executeQuery("SELECT * FROM Prestamo_Diccionario_Profesor WHERE codPrestDicProf = " + codigo + ";");

            while (rs.next()) {
                prestamo = new PrestamoDiccionarioProf();
                prestamo.setCodPrestamoDiccionarioProf(rs.getInt("codPrestDicProf"));
                prestamo.setCodBarraDiccionario(rs.getString("codBarraDiccionario"));
                prestamo.setIdProfesor(rs.getString("idProfesor"));
                prestamo.setIdBibliotecario(rs.getString("idBibliotecario"));
                prestamo.setFechaPrestamo(rs.getDate("fechaPrestamo"));
                prestamo.setFechaDevolucion(rs.getDate("fechaDevolucion"));
                prestamo.setDevuelto(rs.getString("devuelto"));
            }
            rs.close();

            return prestamo;
        } catch (SQLException e) {
            System.out.println("Error al realizar el readDAO, en préstamo dicionario profesor");
        }

        return prestamo;
    }

    /**
     * el metódo actuliza un atributo o todos del préstamo de un deccionario del
     * profesor.
     *
     * @param prestamo
     * @return booelan
     */
    @Override
    public boolean updateDAO(PrestamoDiccionarioProf prestamo) {
        String sqlSentence = "UPDATE Prestamo_Diccionario_Profesor "
                + "SET codBarraDiccionario = ?, idProfesor = ?, idBibliotecario = ?, fechaPrestamo = ?, "
                + "fechaDevolucion = ?, devuelto = ? WHERE codPrestDicProf = ?";
        PreparedStatement pps;

        try {
            pps = connection.getConnection().prepareStatement(sqlSentence);
            pps.setString(1, prestamo.getCodBarraDiccionario());
            pps.setString(2, prestamo.getIdProfesor());
            pps.setString(3, prestamo.getIdBibliotecario());
            pps.setDate(4, prestamo.getFechaPrestamo());
            pps.setDate(5, prestamo.getFechaDevolucion());
            pps.setString(6, prestamo.getDevuelto());
            pps.setInt(7, prestamo.getCodPrestamoDiccionarioProf());

            if (pps.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Error al realizar el updateDAO, en préstamo dicionario profesor");
        }

        return false;
    }

    /**
     * el metódo elimina el préstamo de un diccionario del profesor.
     *
     * @param pk
     * @return boolean
     */
    @Override
    public boolean deleteDAO(int pk) {
        String sqlSentence = "DELETE FROM Prestamo_Diccionario_Profesor WHERE codPrestDicProf = ?";
        PreparedStatement pps;

        try {
            pps = connection.getConnection().prepareStatement(sqlSentence);
            pps.setInt(1, pk);

            if (pps.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Error al realizar el deleteDAO, en préstamo dicionario profesor");
        }

        return false;
    }

    /**
     * el metódo retorna una lista con todos los préstamos de diccionarios a los
     * profesores.
     *
     * @return prestamos
     */
    @Override
    public List<PrestamoDiccionarioProf> readAllDAO() {

        PreparedStatement pps;
        ResultSet rs;
        ArrayList<PrestamoDiccionarioProf> prestamos = new ArrayList();

        try {
            pps = connection.getConnection().prepareStatement("SELECT * FROM Prestamo_Diccionario_Profesor");
            rs = pps.executeQuery();

            while (rs.next()) {
                PrestamoDiccionarioProf prestamoTmp = new PrestamoDiccionarioProf();
                prestamoTmp.setCodPrestamoDiccionarioProf(rs.getInt("codPrestDicProf"));
                prestamoTmp.setCodBarraDiccionario(rs.getString("codBarraDiccionario"));
                prestamoTmp.setIdProfesor(rs.getString("idProfesor"));
                prestamoTmp.setIdBibliotecario(rs.getString("idBibliotecario"));
                prestamoTmp.setFechaPrestamo(rs.getDate("fechaPrestamo"));
                prestamoTmp.setFechaDevolucion(rs.getDate("fechaDevolucion"));
                prestamoTmp.setDevuelto(rs.getString("devuelto"));
                prestamos.add(prestamoTmp);
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println("Error al realizar el readALLDAO, en préstamo dicionario profesor");
        }

        return prestamos;
    }

    /**
     * el método realiza la consulta del código del préstamo de un diccionario
     * del profesor en la BD, por medio de un código de barras.
     *
     * @param codBarra
     * @return
     */
    @Override
    public int readCodigoDAO(String codBarra) {
        Statement stmt;
        ResultSet rs;
        int codPrestamo = -1;

        try {
            stmt = connection.getConnection().createStatement();
            rs = stmt.executeQuery("SELECT codPrestDicProf FROM Prestamo_Diccionario_Profesor WHERE codBarraDiccionario = " + codBarra + ";");

            while (rs.next()) {
                codPrestamo = rs.getInt("codPrestDicProf");
            }

            rs.close();
        } catch (SQLException e) {
            System.out.println("Error al realizar el readCodigoDAO, en préstamo dicionario profesor");
        }

        return codPrestamo;
    }

}

package moduloPrestamo.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import modelo.ConnectionBD;
import moduloPrestamo.entitys.PrestamoLibroEst;

/**
 * Clase que realiza el CRUD sobre la entidad prestamo_libro_estudiante.
 *
 * @author Julian Fecha creación:11/08/2019 Fecha ultima modificación:11/08/2019
 */
public class PrestamoLibroDAOEst extends PrestamoRecursoDAOAbs<PrestamoLibroEst> {

    private int diasPrestamo = 0;

    public PrestamoLibroDAOEst() {
        connection = ConnectionBD.getInstance();
    }

    public PrestamoLibroDAOEst(int diasPrestamo) {
        this.diasPrestamo = diasPrestamo;
        connection = ConnectionBD.getInstance();
    }

    @Override
    public boolean createDAO(PrestamoLibroEst prestamo) {
        String sqlSentence = "INSERT INTO Prestamo_Libro_Estudiante (codBarraLibro, codEstudiante, idBibliotecario, fechaPrestamo, fechaDevolucion, numRenovaciones, devuelto)"
                + " VALUES (?,?,?,?,CURRENT_DATE + " + diasPrestamo + ",?,'no')";
        
        PreparedStatement pps;

        try {
            pps = connection.getConnection().prepareStatement(sqlSentence);
            pps.setString(1, prestamo.getCodBarraLibro());
            pps.setString(2, prestamo.getCodEstudiante());
            pps.setString(3, prestamo.getIdBibliotecario());
            pps.setDate(4, prestamo.getFechaPrestamo());
            pps.setInt(5, prestamo.getNumRenovaciones());

            if (pps.executeUpdate() > 0) {
                System.out.println("Registro creado");
                return true;
            }

        } catch (SQLException e) {
            System.out.println("El registro no se pudo crear " + "\n" + e.getMessage());
        }
        return false;
    }

    @Override
    public PrestamoLibroEst readDAO(int codigo) {
        Statement stmt;
        ResultSet rs;
        PrestamoLibroEst prestamo = null;

        try {
            stmt = connection.getConnection().createStatement();
            rs = stmt.executeQuery("SELECT * FROM Prestamo_Libro_Estudiante WHERE codPrestLibroEst = " + codigo + ";");

            while (rs.next()) {
                prestamo = new PrestamoLibroEst(rs.getString("codBarraLibro"), rs.getString("codEstudiante"),
                        rs.getString("idBibliotecario"), rs.getDate("fechaPrestamo"), rs.getDate("fechaDevolucion"));
                prestamo.setCodPrestamoLibroEst(rs.getInt("codPrestLibroEst"));
                prestamo.setNumRenovaciones(rs.getInt("numRenovaciones"));
                prestamo.setDevuelto(rs.getString("devuelto").charAt(0));
            }
            rs.close();
            return prestamo;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "El préstamo de libro con ese codigo no existe");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se pudo realizar la consulta");
        }
        return prestamo;
    }

    @Override
    public boolean updateDAO(PrestamoLibroEst prestamo) {
        String sqlSentence;
        if(prestamo.getDevuelto() == 's'){
            sqlSentence = "UPDATE Prestamo_Libro_Estudiante SET codBarraLibro = ?, codEstudiante = ?,"
                    + " idBibliotecario = ?, fechaPrestamo = ?, fechaDevolucion = ?,"
                    + " numRenovaciones = ?,devuelto = 'si' WHERE codPrestLibroEst = ?";
        }else{
            sqlSentence = "UPDATE Prestamo_Libro_Estudiante SET codBarraLibro = ?, codEstudiante = ?,"
                    + " idBibliotecario = ?, fechaPrestamo = ?, fechaDevolucion = ?,"
                    + " numRenovaciones = ?,devuelto = 'no' WHERE codPrestLibroEst = ?";
        }
        
        PreparedStatement pps;

        try {
            pps = connection.getConnection().prepareStatement(sqlSentence);

            pps.setString(1, prestamo.getCodBarraLibro());
            pps.setString(2, prestamo.getCodEstudiante());
            pps.setString(3, prestamo.getIdBibliotecario());
            pps.setDate(4, prestamo.getFechaPrestamo());
            pps.setDate(5, prestamo.getFechaDevolucion());
            pps.setInt(6, prestamo.getNumRenovaciones());
            pps.setInt(7,prestamo.getCodPrestamoLibroEst());
            
            if (pps.executeUpdate() > 0) {
                System.out.println("Realizo el update");
                return true;
            } else {
                System.out.println("No existe un prestamo con ese codigo");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No se pudo realizar el update del prestamo libro" + e.getMessage());
        }

        return false;
    }

    @Override
    public boolean deleteDAO(int pk) {
        String sqlSentence = "DELETE FROM Prestamo_Libro_Estudiante WHERE codPrestLibroEst = ?";
        System.out.println(sqlSentence);
        PreparedStatement pps;

        try {
            pps = connection.getConnection().prepareStatement(sqlSentence);

            pps.setInt(1, pk);

            if (pps.executeUpdate() > 0) {
                System.out.println("Hizo el delete");
                return true;
            }
        } catch (SQLException e) {
            System.err.println("No se pudo realizar el delete de prestamo libro");
        }
        return false;
    }

    @Override
    public List<PrestamoLibroEst> readAllDAO() {
        PreparedStatement pps;
        ResultSet rs;
        ArrayList<PrestamoLibroEst> prestamos = new ArrayList();

        try {
            pps = connection.getConnection().prepareStatement("SELECT * FROM Prestamo_Libro_Estudiante");
            rs = pps.executeQuery();

            while (rs.next()) {
                PrestamoLibroEst prestamoTmp = new PrestamoLibroEst(rs.getString("codBarraLibro"), rs.getString("codEstudiante"),
                        rs.getString("idBibliotecario"), rs.getDate("fechaPrestamo"), rs.getDate("fechaDevolucion"));
                prestamoTmp.setCodPrestamoLibroEst(rs.getInt("codPrestLibroEst"));
                prestamoTmp.setNumRenovaciones(rs.getInt("numRenovaciones"));
                prestamoTmp.setDevuelto(rs.getString("devuelto").charAt(0));
                prestamos.add(prestamoTmp);
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println("No se realizo el readAll correctamente en prestamo libro");
        } catch (Exception e) {
            System.out.println("Problema en el readAll de prestamo libro");
        }
        return prestamos;
    }

    @Override
    public int readCodigoDAO(String codBarra) {
        Statement stmt;
        ResultSet rs;
        int codPrestamo = -1;
        try {
            stmt = connection.getConnection().createStatement();
            rs = stmt.executeQuery("SELECT codPrestLibroEst FROM Prestamo_Libro_Estudiante WHERE codBarraLibro = " + codBarra + ";");

            while (rs.next()) {
                codPrestamo = rs.getInt(1);
            }
            rs.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "El préstamo de enciclopedia con ese codigo no existe");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se pudo realizar la consulta");
        }
        return codPrestamo;
    }

}

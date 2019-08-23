package moduloPrestamo.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import modelo.ConnectionBD;
import moduloPrestamo.entitys.PrestamoPeriodicoProf;

/**
 * Clase que realiza el CRUD sobre la entidad prestamo_periodico_profesor.
 *
 * @author Julian Fecha creación:11/08/2019 Fecha ultima modificación:11/08/2019
 */
public class PrestamoPeriodicoDAOProf extends PrestamoRecursoDAOAbs<PrestamoPeriodicoProf> {

    public PrestamoPeriodicoDAOProf() {
        connection = ConnectionBD.getInstance();
    }

    @Override
    public boolean createDAO(PrestamoPeriodicoProf prestamo) {
        String sqlSentence = "INSERT INTO Prestamo_Periodico_Profesor (codBarraPeriodico, idProfesor, idBibliotecario, fechaPrestamo, fechaDevolucion, devuelto)"
                + " VALUES (?,?,?,?,?,'no')";

        PreparedStatement pps;

        try {
            pps = connection.getConnection().prepareStatement(sqlSentence);
            pps.setString(1, prestamo.getCodBarraPeriodico());
            pps.setString(2, prestamo.getIdProfesor());
            pps.setString(3, prestamo.getIdBibliotecario());
            pps.setDate(4, prestamo.getFechaPrestamo());
            pps.setDate(5, prestamo.getFechaDevolucion());

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
    public PrestamoPeriodicoProf readDAO(int codigo) {
        Statement stmt;
        ResultSet rs;
        PrestamoPeriodicoProf prestamo = null;
        try {
            stmt = connection.getConnection().createStatement();
            rs = stmt.executeQuery("SELECT * FROM Prestamo_Periodico_Profesor WHERE codPrestPeriodicoProf = " + codigo + ";");

            while (rs.next()) {
                prestamo = new PrestamoPeriodicoProf(rs.getString("codBarraPeriodico"), rs.getString("idProfesor"),
                        rs.getString("idBibliotecario"), rs.getDate("fechaPrestamo"), rs.getDate("fechaDevolucion"));
                prestamo.setCodPrestamoPeriodicoProf(rs.getInt("codPrestPeriodicoProf"));
                prestamo.setDevuelto(rs.getString("devuelto").charAt(0));
            }
            rs.close();
            return prestamo;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "El préstamo de periodico con ese codigo no existe");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se pudo realizar la consulta");
        }
        return prestamo;
    }

    @Override
    public boolean updateDAO(PrestamoPeriodicoProf prestamo) {
        String sqlSentence;
        if (prestamo.getDevuelto() == 's') {
            sqlSentence = "UPDATE Prestamo_Periodico_Profesor SET codBarraPeriodico = ?, idProfesor = ?, idBibliotecario = ?, fechaPrestamo = ?, "
                    + "fechaDevolucion = ?,devuelto = 'si' WHERE codPrestPeriodicoProf = ?";
        } else {
            sqlSentence = "UPDATE Prestamo_Periodico_Profesor SET codBarraPeriodico = ?, idProfesor = ?, idBibliotecario = ?, fechaPrestamo = ?, "
                    + "fechaDevolucion = ?,devuelto = 'no' WHERE codPrestPeriodicoProf = ?";
        }

        PreparedStatement pps;

        try {
            pps = connection.getConnection().prepareStatement(sqlSentence);

            pps.setString(1, prestamo.getCodBarraPeriodico());
            pps.setString(2, prestamo.getIdProfesor());
            pps.setString(3, prestamo.getIdBibliotecario());
            pps.setDate(4, prestamo.getFechaPrestamo());
            pps.setDate(5, prestamo.getFechaDevolucion());
            pps.setInt(6, prestamo.getCodPrestamoPeriodicoProf());

            if (pps.executeUpdate() > 0) {
                System.out.println("Realizo el update");
                return true;
            } else {
                System.out.println("No existe un prestamo con ese codigo");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No se pudo realizar el update del prestamo periodico");
        }
        return false;
    }

    @Override
    public boolean deleteDAO(int pk) {
        String sqlSentence = "DELETE FROM Prestamo_Periodico_Profesor WHERE codPrestPeriodicoProf = ?";
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
            System.err.println("No se pudo realizar el delete de prestamo Periodico");
        }
        return false;
    }

    @Override
    public List<PrestamoPeriodicoProf> readAllDAO() {
        PreparedStatement pps;
        ResultSet rs;
        ArrayList<PrestamoPeriodicoProf> prestamos = new ArrayList();

        try {
            pps = connection.getConnection().prepareStatement("SELECT * FROM Prestamo_Periodico_Profesor");
            rs = pps.executeQuery();

            while (rs.next()) {
                PrestamoPeriodicoProf prestamoTmp = new PrestamoPeriodicoProf(rs.getString("codBarraPeriodico"), rs.getString("idProfesor"),
                        rs.getString("idBibliotecario"), rs.getDate("fechaPrestamo"), rs.getDate("fechaDevolucion"));
                prestamoTmp.setCodPrestamoPeriodicoProf(rs.getInt("codPrestPeriodicoProf"));
                prestamoTmp.setDevuelto(rs.getString("devuelto").charAt(0));
                prestamos.add(prestamoTmp);
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println("No se realizo el readAll correctamente en prestamo periodico");
        } catch (Exception e) {
            System.out.println("Problema en el readAll de prestamo periodico");
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
            rs = stmt.executeQuery("SELECT codPrestPeriodicoProf FROM Prestamo_Periodico_Profesor WHERE codBarraPeriodico = " + codBarra + ";");

            while (rs.next()) {
                codPrestamo = rs.getInt(1);
            }
            rs.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "El préstamo de periodico con ese codigo no existe");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se pudo realizar la consulta");
        }
        return codPrestamo;
    }

}

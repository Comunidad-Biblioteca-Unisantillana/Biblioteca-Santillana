package moduloPrestamo.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import modelo.ConnectionBD;
import moduloPrestamo.entitys.PrestamoDiccionarioProf;
import vista.AlertBox;
import vista.IAlertBox;

/**
 * Clase que realiza el CRUD sobre las entidades de préstamos.
 *
 * @author Julian Fecha creación:10/08/2019 Fecha ultima modificación:10/08/2019
 */
public class PrestamoDiccionarioDAOProf extends PrestamoRecursoDAOAbs<PrestamoDiccionarioProf> {

    public PrestamoDiccionarioDAOProf() {
        connection = ConnectionBD.getInstance();
    }

    @Override
    public boolean createDAO(PrestamoDiccionarioProf prestamo) {
        String sqlSentence = "INSERT INTO Prestamo_Diccionario_Profesor (codBarraDiccionario, idProfesor, idBibliotecario, fechaPrestamo, fechaDevolucion, devuelto)"
                + " VALUES (?,?,?,?,?,'no')";
        PreparedStatement pps;

        try {
            pps = connection.getConnection().prepareStatement(sqlSentence);
            pps.setString(1, prestamo.getCodBarraDiccionario());
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
    public PrestamoDiccionarioProf readDAO(int codigo) {
        Statement stmt;
        ResultSet rs;
        PrestamoDiccionarioProf prestamo = null;

        try {
            stmt = connection.getConnection().createStatement();
            rs = stmt.executeQuery("SELECT * FROM Prestamo_Diccionario_Profesor WHERE codPrestDicProf = " + codigo + ";");

            while (rs.next()) {
                prestamo = new PrestamoDiccionarioProf(rs.getString("codBarraDiccionario"), rs.getString("idProfesor"),
                        rs.getString("idBibliotecario"), rs.getDate("fechaPrestamo"), rs.getDate("fechaDevolucion"));
                prestamo.setCodPrestamoDiccionarioProf(rs.getInt("codPrestDicProf"));
                prestamo.setDevuelto(rs.getString("devuelto").charAt(0));
            }
            rs.close();
            return prestamo;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "El préstamo de diccionario con ese codigo no existe");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se pudo realizar la consulta");
        }
        return prestamo;
    }

    @Override
    public boolean updateDAO(PrestamoDiccionarioProf prestamo) {
        String sqlSentence;
        if (prestamo.getDevuelto() == 's') {
            sqlSentence = "UPDATE Prestamo_Diccionario_Profesor SET codBarraDiccionario = ?, idProfesor = ?, idBibliotecario = ?, fechaPrestamo = ?, "
                    + "fechaDevolucion = ?,devuelto = 'si' WHERE codPrestDicProf = ?";
        } else {
            sqlSentence = "UPDATE Prestamo_Diccionario_Profesor SET codBarraDiccionario = ?, idProfesor = ?, idBibliotecario = ?, fechaPrestamo = ?, "
                    + "fechaDevolucion = ?,devuelto = 'no' WHERE codPrestDicProf = ?";
        }
        PreparedStatement pps;
        try {
            pps = connection.getConnection().prepareStatement(sqlSentence);
            pps.setString(1, prestamo.getCodBarraDiccionario());
            pps.setString(2, prestamo.getIdProfesor());
            pps.setString(3, prestamo.getIdBibliotecario());
            pps.setDate(4, prestamo.getFechaPrestamo());
            pps.setDate(5, prestamo.getFechaDevolucion());

            if (pps.executeUpdate() > 0) {
                System.out.println("Realizo el update");
                return true;
            } else {
                System.out.println("No existe un prestamo con ese codigo");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No se pudo realizar el update del prestamo diccionario de un estudiante");
        }

        return false;
    }

    @Override
    public boolean deleteDAO(int pk) {
        String sqlSentence = "DELETE FROM Prestamo_Diccionario_Profesor WHERE codPrestDicProf = ?";
        PreparedStatement pps;

        try {
            pps = connection.getConnection().prepareStatement(sqlSentence);
            pps.setInt(1, pk);

            if (pps.executeUpdate() > 0) {
                System.out.println("Hizo el delete");
                return true;
            }
        } catch (SQLException e) {
            IAlertBox alert = new AlertBox();
            alert.showAlert("Anuncio", "Multa", "Hay una multa pendiente, por lo tanto, el préstamo no se puede borrar");
        }

        return false;
    }

    @Override
    public List<PrestamoDiccionarioProf> readAllDAO() {

        PreparedStatement pps;
        ResultSet rs;
        ArrayList<PrestamoDiccionarioProf> prestamos = new ArrayList();

        try {
            pps = connection.getConnection().prepareStatement("SELECT * FROM Prestamo_Diccionario_Profesor");
            rs = pps.executeQuery();

            while (rs.next()) {
                PrestamoDiccionarioProf prestamoTmp = new PrestamoDiccionarioProf(rs.getString("codBarraDiccionario"), rs.getString("idProfesor"),
                        rs.getString("idBibliotecario"), rs.getDate("fechaPrestamo"), rs.getDate("fechaDevolucion"));
                prestamoTmp.setCodPrestamoDiccionarioProf(rs.getInt("codPrestDicProf"));
                prestamoTmp.setDevuelto(rs.getString("devuelto").charAt(0));
                prestamos.add(prestamoTmp);
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println("No se realizo el readAll correctamente en prestamo diccionario");
        } catch (Exception e) {
            System.out.println("Problema en el readAll de prestamo diccionario");
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
            rs = stmt.executeQuery("SELECT codPrestDicProf FROM Prestamo_Diccionario_Profesor WHERE codBarraDiccionario = " + codBarra + ";");
            while (rs.next()) {
                codPrestamo = rs.getInt(1);
            }
            rs.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "El préstamo de diccionario con ese codigo no existe");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se pudo realizar la consulta");
        }
        return codPrestamo;
    }

}
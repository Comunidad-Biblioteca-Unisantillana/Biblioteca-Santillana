/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moduloReserva.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import general.modelo.ConnectionBD;
import moduloReserva.entitys.ReservaColgenEstudiante;

/**
 *
 * @author Julian
 */
public class ReservaColgenDAOEst extends ReservaRecursoDAOAbs<ReservaColgenEstudiante> {

    public ReservaColgenDAOEst() {
        connection = ConnectionBD.getInstance();
    }

    @Override
    public boolean createDAO(ReservaColgenEstudiante reserva) {
        String sqlSentence = "INSERT INTO Reserva_Colgen_Estudiante (codBarraLibro, codEstudiante, idBibliotecario, fechaReserva, fechaRetencion, fechaLimiteReserva)"
                + " VALUES (?,?,?,current_date,?,?)";
        PreparedStatement pps;

        try {
            pps = connection.getConnection().prepareStatement(sqlSentence);
            pps.setString(1, reserva.getCodBarraLibro());
            pps.setString(2, reserva.getCodEstudiante());
            pps.setString(3, reserva.getIdBibliotecario());
            pps.setDate(4, reserva.getFechaRetencion());
            pps.setDate(5, reserva.getFechaLimiteReserva());

            if (pps.executeUpdate() > 0) {
                System.out.println("Reserva creada");
                return true;
            }

        } catch (SQLException e) {
            System.out.println("El registro no se pudo crear " + "\n" + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean deleteDAO(String codigo, String codEstudiante) {
        String sqlSentence = "DELETE FROM Reserva_Colgen_Estudiante WHERE codBarraLibro = ? AND codEstudiante = ?";
        PreparedStatement pps;

        try {
            pps = connection.getConnection().prepareStatement(sqlSentence);
            pps.setString(1, codigo);
            pps.setString(2, codEstudiante);

            if (pps.executeUpdate() > 0) {
                System.out.println("Hizo el delete");
                return true;
            }
        } catch (SQLException e) {
            System.err.println("No se pudo realizar el delete de prestamo revista");
        }
        return false;
    }

    @Override
    public List<ReservaColgenEstudiante> readAllDAO() {

        PreparedStatement pps;
        ResultSet rs;
        ArrayList<ReservaColgenEstudiante> reservas = new ArrayList();

        try {
            pps = connection.getConnection().prepareStatement("SELECT * FROM Reserva_Colgen_Estudiante");
            rs = pps.executeQuery();

            while (rs.next()) {
                ReservaColgenEstudiante reservaTmp = new ReservaColgenEstudiante(rs.getString("codBarraLibro"), rs.getString("codEstudiante"),
                        rs.getString("idBibliotecario"), rs.getDate("fechaReserva"));
                reservaTmp.setCodReservaColgenEst(rs.getInt("codReservaColgenEst"));
                reservaTmp.setFechaLimiteReserva(rs.getDate("fechaLimiteReserva"));
                reservaTmp.setFechaRetencion(rs.getDate("fechaRetencion"));
                reservas.add(reservaTmp);
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println("No se realizo el readAll correctamente en reserva");
        } catch (Exception e) {
            System.out.println("Problema en el readAll de prestamo reserva");
        }
        return reservas;
    }

    @Override
    public ReservaColgenEstudiante readDAO(String codigo) {
        Statement stmt;
        ResultSet rs;
        ReservaColgenEstudiante reserva = null;

        try {
            stmt = connection.getConnection().createStatement();
            rs = stmt.executeQuery("SELECT * FROM Reserva_Colgen_Estudiante WHERE codBarraLibro = '" + codigo + "';");

            while (rs.next()) {
                reserva = new ReservaColgenEstudiante(rs.getString("codBarraLibro"), rs.getString("codEstudiante"),
                        rs.getString("idBibliotecario"), rs.getDate("fechaReserva"));
                reserva.setCodReservaColgenEst(rs.getInt("codReservaColgenEst"));
                reserva.setFechaLimiteReserva(rs.getDate("fechaLimiteReserva"));
                reserva.setFechaRetencion(rs.getDate("fechaRetencion"));
            }
            rs.close();
            return reserva;
        } catch (SQLException e) {
            System.out.println("No existe una reserva con ese codigo");
        } catch (Exception e) {
            System.out.println("No se pudo realizar la consulta");
        }
        return reserva;
    }

    @Override
    public boolean updateDAO(ReservaColgenEstudiante reserva) {
        String sqlSentence = "UPDATE Reserva_Colgen_Estudiante SET codBarraLibro = ?, codEstudiante = ?, idBibliotecario = ?, fechaReserva = ?, "
                + "fechaRetencion = ?,fechaLimiteReserva = ? WHERE codReservaColgenEst = ?";
        PreparedStatement pps;

        try {
            pps = connection.getConnection().prepareStatement(sqlSentence);
            pps.setString(1, reserva.getCodBarraLibro());
            pps.setString(2, reserva.getCodEstudiante());
            pps.setString(3, reserva.getIdBibliotecario());
            pps.setDate(4, reserva.getFechaReserva());
            pps.setDate(5, reserva.getFechaRetencion());
            pps.setDate(6, reserva.getFechaLimiteReserva());
            pps.setInt(7, reserva.getCodReservaColgenEst());

            if (pps.executeUpdate() > 0) {
                System.out.println("Realizo el update");
                return true;
            } else {
                System.out.println("No existe una reserva con ese codigo");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No se pudo realizar el update de la reserva");
        }
        return false;
    }

}

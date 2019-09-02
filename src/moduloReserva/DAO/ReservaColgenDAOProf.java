
package moduloReserva.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import general.modelo.ConnectionBD;
import moduloReserva.entitys.ReservaColgenProfesor;

/**
 *
 * @author Storkolm
 */
public class ReservaColgenDAOProf extends ReservaRecursoDAOAbs<ReservaColgenProfesor>{

    public ReservaColgenDAOProf() {
        connection = ConnectionBD.getInstance();
    }

    @Override
    public boolean createDAO(ReservaColgenProfesor reserva) {
        String sqlSentence = "INSERT INTO Reserva_Colgen_Profesor (codBarraLibro, idProfesor"
                + ", idBibliotecario, fechaReserva, fechaRetencion, fechaLimiteReserva)"
                + " VALUES (?,?,?,current_date,?,?)";
        PreparedStatement pps;

        try {
            pps = connection.getConnection().prepareStatement(sqlSentence);
            pps.setString(1, reserva.getCodBarraLibro());
            pps.setString(2, reserva.getIdProfesor());
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
    public boolean deleteDAO(String codigo,  String idProfesor) {
        String sqlSentence = "DELETE FROM Reserva_Colgen_Profesor WHERE codBarraLibro = ? AND idProfesor = ?";
        PreparedStatement pps;

        try {
            pps = connection.getConnection().prepareStatement(sqlSentence);
            pps.setString(1, codigo);
            pps.setString(2, idProfesor);

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
    public List<ReservaColgenProfesor> readAllDAO() {
        PreparedStatement pps;
        ResultSet rs;
        ArrayList<ReservaColgenProfesor> reservas = new ArrayList();

        try {
            pps = connection.getConnection().prepareStatement("SELECT * FROM Reserva_Colgen_Profesor");
            rs = pps.executeQuery();

            while (rs.next()) {
                ReservaColgenProfesor reservaTmp = new ReservaColgenProfesor(rs.getString("codBarraLibro"), rs.getString("idBibliotecario"),
                        rs.getString("idProfesor"), rs.getDate("fechaReserva"));
                reservaTmp.setCodReservaColgenProf(rs.getInt("codReservaColgenProf"));
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
    public ReservaColgenProfesor readDAO(String codigo) {
        Statement stmt;
        ResultSet rs;
        ReservaColgenProfesor reserva = null;

        try {
            stmt = connection.getConnection().createStatement();
            rs = stmt.executeQuery("SELECT * FROM Reserva_Colgen_Profesor WHERE codBarraLibro = '" + codigo + "';");

            while (rs.next()) {
                reserva = new ReservaColgenProfesor(rs.getString("codBarraLibro"), rs.getString("idBibliotecario"),
                        rs.getString("idProfesor"), rs.getDate("fechaReserva"));
                reserva.setCodReservaColgenProf(rs.getInt("codReservaColgenProf"));
                reserva.setFechaLimiteReserva(rs.getDate("fechaLimiteReserva"));
                reserva.setFechaRetencion(rs.getDate("fechaRetencion"));
            }
            rs.close();
            return reserva;
        } catch (SQLException e) {
            System.out.println("No existe una reserva  con ese codigo");
        } catch (Exception e) {
            System.out.println("No se pudo realizar la consulta");
        }
        return reserva;
    }

    @Override
    public boolean updateDAO(ReservaColgenProfesor reserva) {
        String sqlSentence = "UPDATE Reserva_Colgen_Profesor SET codBarraLibro = ?, idProfesor = ?, idBibliotecario = ?, fechaReserva = ?, "
                + "fechaRetencion = ?,fechaLimiteReserva = ? WHERE codReservaColgenProf = ?";
        PreparedStatement pps;

        try {
            pps = connection.getConnection().prepareStatement(sqlSentence);
            pps.setString(1, reserva.getCodBarraLibro());
            pps.setString(2, reserva.getIdProfesor());
            pps.setString(3, reserva.getIdBibliotecario());
            pps.setDate(4, reserva.getFechaReserva());
            pps.setDate(5, reserva.getFechaRetencion());
            pps.setDate(6, reserva.getFechaLimiteReserva());
            pps.setInt(7, reserva.getCodReservaColgenProf());

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

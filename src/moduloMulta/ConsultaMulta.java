package moduloMulta;

import entitys.Multa;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import modelo.ConnectionBD;
import vista.AlertBox;
import vista.IAlertBox;

/**
 * Clase que consulta las multas de un estudiante.
 *
 * @author Camilo
 */
public class ConsultaMulta {

    public ConsultaMulta() {
    }

    /**
     * Método que se encarga de consultar las multas de un estudiantes.<br>
     * Para llevar a cabo esta función, se realiza una unión de todas las tablas
     * de multas, de este modo, se buscan las multas donde coincidan con el
     * codigo del estudiante que ha sido ingresado.<br>
     * Al final, la consulta se guarda en un objeto de la clase Multa.
     *
     * @param codEstudiante
     * @return ObservableList
     * @throws Exception
     */
    public ObservableList<Multa> getMultas(String codEstudiante) throws Exception {
        ObservableList<Multa> multas = FXCollections.observableArrayList();

        ConnectionBD connection = ConnectionBD.getInstance();
        PreparedStatement pps;
        ResultSet rs;
        ArrayList<Multa> multasTmp = new ArrayList();

        String sqlSentence = "SELECT ml.codMultaLibro AS codMulta, ml.codPrestamoLibro AS codPrestamo, ml.diasAtrasados, ml.valorTotalMulta, ml.cancelado, ml.tipo \n"
                + "	FROM Multa_Libro ml, Prestamo_Libro pl\n"
                + "	WHERE pl.codEstudiante = '" + codEstudiante + "' AND ml.codPrestamoLibro = pl.codPrestamoLibro\n"
                + "UNION \n"
                + "SELECT me.codMultaEnciclopedia, me.codPrestamoEnciclopedia, me.diasAtrasados, me.valorTotalMulta, me.cancelado, me.tipo \n"
                + "	FROM Multa_Enciclopedia me, Prestamo_Enciclopedia pe\n"
                + "	WHERE pe.codEstudiante = '" + codEstudiante + "' AND me.codPrestamoEnciclopedia = pe.codPrestamoEnciclopedia\n"
                + "UNION \n"
                + "SELECT md.codMultaDiccionario, md.codPrestamoDiccionario, md.diasAtrasados, md.valorTotalMulta, md.cancelado, md.tipo \n"
                + "	FROM Multa_Diccionario md, Prestamo_Diccionario pd\n"
                + "	WHERE pd.codEstudiante = '" + codEstudiante + "' AND md.codPrestamoDiccionario = pd.codPrestamoDiccionario\n"
                + "UNION \n"
                + "SELECT mr.codMultaRevista, mr.codPrestamoRevista, mr.diasAtrasados, mr.valorTotalMulta, mr.cancelado, mr.tipo \n"
                + "	FROM Multa_Revista mr, Prestamo_Revista pr\n"
                + "	WHERE pr.codEstudiante = '" + codEstudiante + "' AND mr.codPrestamoRevista = pr.codPrestamoRevista\n"
                + "UNION \n"
                + "SELECT mp.codMultaPeriodico, mp.codPrestamoPeriodico, mp.diasAtrasados, mp.valorTotalMulta, mp.cancelado, mp.tipo \n"
                + "	FROM Multa_Periodico mp, Prestamo_Periodico pp\n"
                + "	WHERE pp.codEstudiante = '" + codEstudiante + "' AND mp.codPrestamoPeriodico = pp.codPrestamoPeriodico\n"
                + "UNION \n"
                + "SELECT mm.codMultaMapa, mm.codPrestamoMapa, mm.diasAtrasados, mm.valorTotalMulta, mm.cancelado, mm.tipo \n"
                + "	FROM Multa_Mapa mm, Prestamo_Mapa pm\n"
                + "	WHERE pm.codEstudiante = '" + codEstudiante + "' AND mm.codPrestamoMapa = pm.codPrestamoMapa";

        try {
            pps = connection.getConnection().prepareStatement(sqlSentence);
            rs = pps.executeQuery();

            while (rs.next()) {
                Multa multaTmp = new Multa(rs.getInt("codMulta"), rs.getInt("codPrestamo"), rs.getInt("diasAtrasados"),
                        rs.getInt("valorTotalMulta"), rs.getString("cancelado"), rs.getString("tipo"));
                multasTmp.add(multaTmp);
            }

            if (!multasTmp.isEmpty()) {
                multasTmp.forEach((mult) -> {
                    multas.add(mult);
                });
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println("No se realizo el readAll correctamente");
        } catch (Exception e) {
            System.out.println("Problema en el readAll");
        } finally {
            if (multas.isEmpty()) {
                IAlertBox alert = new AlertBox();
                alert.showAlert("Anuncio", "Historial de multas de un estudiante", "No existe un estudiante con ese código");
            }
        }
        return multas;
    }

    public boolean eliminarMulta(int codMulta, String entidad) {
        Statement stmt;
        ResultSet rs;

        try {
            stmt = ConnectionBD.getInstance().getConnection().createStatement();
            System.out.println("Se establecio la conexión a la BD");

            String sqlSentence = "DELETE FROM Multa_" + entidad + " WHERE codMulta" + entidad + " = " + codMulta + " AND tipo = '" + entidad + "';";
            rs = stmt.executeQuery(sqlSentence);
            while (rs.next()) {
                return true;
            }
            rs.close();
            System.out.println("Bases de datos cerrada");
        } catch (SQLException ex) {
            
        }

        return false;

    }

}

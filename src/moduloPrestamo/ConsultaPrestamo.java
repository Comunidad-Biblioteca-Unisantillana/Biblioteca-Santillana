/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moduloPrestamo;

import entitysRecursos.Prestamo;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import modelo.ConnectionBD;
import vista.AlertBox;
import vista.IAlertBox;

/**
 *
 * @author stive
 */
public class ConsultaPrestamo {
    public ObservableList<Prestamo> getPrestamos(String codEstudiante) throws Exception {
        ObservableList<Prestamo> prestamos = FXCollections.observableArrayList();

        ConnectionBD connection = ConnectionBD.getInstance();
        PreparedStatement pps;
        ResultSet rs;
        ArrayList<Prestamo> prestamosTmp = new ArrayList();

        String sqlSentence = "SELECT pl.codPrestamoLibro AS codPrestamo, l.titulo AS titulo, pl.fechaPrestamo, pl.fechaDevolucion \n"
                + "	FROM Prestamo_Libro pl, Libro l\n"
                + "	WHERE pl.codBarraLibro = l.codBarraLibro AND pl.codEstudiante = '" + codEstudiante + "'\n"
                + "UNION \n"
                + "SELECT pe.codPrestamoEnciclopedia, e.titulo, pe.fechaPrestamo, pe.fechaDevolucion \n"
                + "	FROM Prestamo_Enciclopedia pe, Enciclopedia e\n"
                + "	WHERE pe.codBarraEnciclopedia = e.codBarraEnciclopedia AND pe.codEstudiante = '" + codEstudiante + "'\n"
                + "UNION \n"
                + "SELECT pd.codPrestamoDiccionario, d.titulo, pd.fechaPrestamo, pd.fechaDevolucion \n"
                + "	FROM Prestamo_Diccionario pd, Diccionario d\n"
                + "	WHERE pd.codBarraDiccionario = d.codBarraDiccionario AND pd.codEstudiante = '" + codEstudiante + "'\n"
                + "UNION \n"
                + "SELECT pr.codPrestamoRevista, r.titulo, pr.fechaPrestamo, pr.fechaDevolucion \n"
                + "	FROM Prestamo_Revista pr, Revista r\n"
                + "	WHERE pr.codBarraRevista = r.codBarraRevista AND pr.codEstudiante = '" + codEstudiante + "'\n"
                + "UNION \n"
                + "SELECT pp.codprestamoPeriodico, p.nombrePeriodico, pp.fechaprestamo, pp.fechaDevolucion \n"
                + "	FROM Prestamo_Periodico pp, Periodico p\n"
                + "	WHERE pp.codBarraPeriodico = p.codBarraPeriodico AND pp.codEstudiante = '" + codEstudiante + "'\n"
                + "UNION \n"
                + "SELECT pm.codprestamoMapa, m.titulo, pm.fechaprestamo, pm.fechaDevolucion \n"
                + "	FROM Prestamo_Mapa pm, Mapa m\n"
                + "	WHERE pm.codBarraMapa = m.codBarraMapa AND pm.codEstudiante = '" + codEstudiante + "'\n";

        try {
            pps = connection.getConnection().prepareStatement(sqlSentence);
            rs = pps.executeQuery();

            while (rs.next()) {
                Prestamo prestamoTmp = new Prestamo(rs.getInt("codPrestamo"), rs.getString("titulo"), rs.getDate("fechaPrestamo"),
                        rs.getDate("fechaDevolucion"));
                prestamosTmp.add(prestamoTmp);
            }

            if (!prestamosTmp.isEmpty()) {
                prestamosTmp.forEach((pres) -> {
                    prestamos.add(pres);
                });
                System.out.println("Cod pres: " + prestamos.get(0).getCodPrestamo());
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println("No se realizo el readAll correctamente");
        } catch (Exception e) {
            System.out.println("Problema en el readAll");
        } finally {
            if (prestamos.isEmpty()) {
                IAlertBox alert = new AlertBox();
                alert.showAlert("Anuncio", "Historial de prestamos de un estudiante", "No existe un estudiante con ese código");
            }
        }
        return prestamos;
    }

}

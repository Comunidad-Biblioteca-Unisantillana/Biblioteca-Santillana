package moduloPrestamo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import modelo.ConnectionBD;

/**
 *
 * @author Julian
 */
public class ConsultaPrestamoEst extends IConsultarPrestamo{

    @Override
    public ObservableList<Prestamo> getHistorialPrestamos(String codEstudiante) {
        ObservableList<Prestamo> prestamos = FXCollections.observableArrayList();

        connection = ConnectionBD.getInstance();
        PreparedStatement pps;
        ResultSet rs;
        ArrayList<Prestamo> prestamosTmp = new ArrayList();

        String sqlSentence = "SELECT ple.codBarraLibro AS codBarraRecurso , l.titulo AS titulo, ple.fechaPrestamo, ple.fechaDevolucion  \n"
                + "	FROM Prestamo_Libro_Estudiante ple, Libro l\n"
                + "	WHERE ple.codBarraLibro = l.codBarraLibro AND ple.codEstudiante = '" + codEstudiante + "'\n"
                + "UNION \n"
                + "SELECT pee.codBarraEnciclopedia, e.titulo, pee.fechaPrestamo, pee.fechaDevolucion \n"
                + "	FROM Prestamo_Enciclopedia_Estudiante pee, Enciclopedia e\n"
                + "	WHERE pee.codBarraEnciclopedia = e.codBarraEnciclopedia AND pee.codEstudiante = '" + codEstudiante + "'\n"
                + "UNION \n"
                + "SELECT pde.codBarraDiccionario, d.titulo, pde.fechaPrestamo, pde.fechaDevolucion \n"
                + "	FROM Prestamo_Diccionario_Estudiante pde, Diccionario d\n"
                + "	WHERE pde.codBarraDiccionario = d.codBarraDiccionario AND pde.codEstudiante = '" + codEstudiante + "'\n"
                + "UNION \n"
                + "SELECT pre.codBarraRevista, r.titulo, pre.fechaPrestamo, pre.fechaDevolucion \n"
                + "	FROM Prestamo_Revista_Estudiante pre, Revista r\n"
                + "	WHERE pre.codBarraRevista = r.codBarraRevista AND pre.codEstudiante = '" + codEstudiante + "'\n"
                + "UNION \n"
                + "SELECT ppe.codBarraPeriodico, p.nombrePeriodico, ppe.fechaPrestamo, ppe.fechaDevolucion\n"
                + "	FROM Prestamo_Periodico_Estudiante ppe, Periodico p\n"
                + "	WHERE ppe.codBarraPeriodico = p.codBarraPeriodico AND ppe.codEstudiante = '" + codEstudiante + "'\n"
                + "UNION \n"
                + "SELECT pme.codBarraMapa, m.titulo, pme.fechaPrestamo, pme.fechaDevolucion\n"
                + "	FROM Prestamo_Mapa_Estudiante pme, Mapa m\n"
                + "	WHERE pme.codBarraMapa = m.codBarraMapa AND pme.codEstudiante = '" + codEstudiante + "'\n"
                + "     ORDER BY fechaPrestamo DESC \n";
        try {
            pps = connection.getConnection().prepareStatement(sqlSentence);
            rs = pps.executeQuery();

            while (rs.next()) {
                Prestamo prestamoTmp = new Prestamo(rs.getString("codBarraRecurso"), rs.getString("titulo"), rs.getDate("fechaPrestamo"),
                        rs.getDate("fechaDevolucion"));
                prestamosTmp.add(prestamoTmp);
            }

            if (!prestamosTmp.isEmpty()) {
                prestamosTmp.forEach((pres) -> {
                    prestamos.add(pres);
                });
                System.out.println("Cod pres: " + prestamos.get(0).getCodBarrasRecurso());
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println("No se realizo el readAll correctamente" + e.getMessage());
        } catch (Exception e) {
            System.out.println("Problema en el readAll");
        } 
        return prestamos;
    }

    @Override
    public ObservableList<Prestamo> getPrestamosActuales(String codEstudiante) {
        ObservableList<Prestamo> prestamos = FXCollections.observableArrayList();

        connection = ConnectionBD.getInstance();
        PreparedStatement pps;
        ResultSet rs;
        ArrayList<Prestamo> prestamosTmp = new ArrayList();

        String sqlSentence = "SELECT ple.codBarraLibro AS codBarraRecurso , l.titulo AS titulo, ple.fechaPrestamo, ple.fechaDevolucion  \n"
                + "	FROM Prestamo_Libro_Estudiante ple, Libro l\n"
                + "	WHERE ple.devuelto = 'no' AND ple.codBarraLibro = l.codBarraLibro AND ple.codEstudiante = '" + codEstudiante + "'\n"
                + "UNION \n"
                + "SELECT pee.codBarraEnciclopedia, e.titulo, pee.fechaPrestamo, pee.fechaDevolucion\n"
                + "	FROM Prestamo_Enciclopedia_Estudiante pee, Enciclopedia e\n"
                + "	WHERE pee.devuelto = 'no' AND pee.codBarraEnciclopedia = e.codBarraEnciclopedia AND pee.codEstudiante = '" + codEstudiante + "'\n"
                + "UNION \n"
                + "SELECT pde.codBarraDiccionario, d.titulo, pde.fechaPrestamo, pde.fechaDevolucion\n"
                + "	FROM Prestamo_Diccionario_Estudiante pde, Diccionario d\n"
                + "	WHERE pde.devuelto = 'no' AND pde.codBarraDiccionario = d.codBarraDiccionario AND pde.codEstudiante = '" + codEstudiante + "'\n"
                + "UNION \n"
                + "SELECT pre.codBarraRevista, r.titulo, pre.fechaPrestamo, pre.fechaDevolucion\n"
                + "	FROM Prestamo_Revista_Estudiante pre, Revista r\n"
                + "	WHERE pre.devuelto = 'no' AND pre.codBarraRevista = r.codBarraRevista AND pre.codEstudiante = '" + codEstudiante + "'\n"
                + "UNION \n"
                + "SELECT ppe.codBarraPeriodico, p.nombrePeriodico, ppe.fechaPrestamo, ppe.fechaDevolucion\n"
                + "	FROM Prestamo_Periodico_Estudiante ppe, Periodico p\n"
                + "	WHERE ppe.devuelto = 'no' AND ppe.codBarraPeriodico = p.codBarraPeriodico AND ppe.codEstudiante = '" + codEstudiante + "'\n"
                + "UNION \n"
                + "SELECT pme.codBarraMapa, m.titulo, pme.fechaPrestamo, pme.fechaDevolucion\n"
                + "	FROM Prestamo_Mapa_Estudiante pme, Mapa m\n"
                + "	WHERE pme.devuelto = 'no' AND pme.codBarraMapa = m.codBarraMapa AND pme.codEstudiante = '" + codEstudiante + "'\n"
                + "     ORDER BY fechaPrestamo DESC \n";
        try {
            pps = connection.getConnection().prepareStatement(sqlSentence);
            rs = pps.executeQuery();

            while (rs.next()) {
                Prestamo prestamoTmp = new Prestamo(rs.getString("codBarraRecurso"), rs.getString("titulo"), rs.getDate("fechaPrestamo"),
                        rs.getDate("fechaDevolucion"));
                prestamosTmp.add(prestamoTmp);
            }

            if (!prestamosTmp.isEmpty()) {
                prestamosTmp.forEach((pres) -> {
                    prestamos.add(pres);
                });
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println("No se realizo el readAll correctamente" + e.getMessage());
        } catch (Exception e) {
            System.out.println("Problema en el readAll");
        } 
        return prestamos;
    }
    
}

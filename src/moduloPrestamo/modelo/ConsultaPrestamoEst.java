package moduloPrestamo.modelo;

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
 * @creado
 * @author Miguel Fernández
 * @modificado 24/08/2019
 */
public class ConsultaPrestamoEst extends IConsultarPrestamo {

    /**
     * constructor de la clase sin parámetros.
     */
    public ConsultaPrestamoEst() {
        connection = ConnectionBD.getInstance();
    }

    /**
     * el metódo busca todos los préstamos que ha tenido un estudiante.
     *
     * @param codEstudiante
     * @return prestamos
     */
    @Override
    public ObservableList<Prestamo> getHistorialPrestamos(String codEstudiante) {
        ObservableList<Prestamo> prestamos = FXCollections.observableArrayList();
        PreparedStatement pps;
        ResultSet rs;
        ArrayList<Prestamo> prestamosTmp = new ArrayList();

        String sqlSentence = "SELECT ple.codBarraLibro AS codBarras, l.titulo AS titulo, ple.fechaPrestamo, ple.fechaDevolucion "
                + "FROM Prestamo_Libro_Estudiante ple, Libro l "
                + "WHERE ple.codBarraLibro = l.codBarraLibro AND ple.codEstudiante = '" + codEstudiante + "' AND ple.devuelto = 'si' "
                + "UNION "
                + "SELECT pee.codBarraEnciclopedia AS codBarras, e.titulo AS titulo, pee.fechaPrestamo, pee.fechaDevolucion "
                + "FROM Prestamo_Enciclopedia_Estudiante pee, Enciclopedia e "
                + "WHERE pee.codBarraEnciclopedia = e.codBarraEnciclopedia AND pee.codEstudiante = '" + codEstudiante + "' AND pee.devuelto = 'si' "
                + "UNION "
                + "SELECT pde.codBarraDiccionario AS codBarras, d.titulo AS titulo, pde.fechaPrestamo, pde.fechaDevolucion "
                + "FROM Prestamo_Diccionario_Estudiante pde, Diccionario d "
                + "WHERE pde.codBarraDiccionario = d.codBarraDiccionario AND pde.codEstudiante = '" + codEstudiante + "' AND pde.devuelto = 'si' "
                + "UNION "
                + "SELECT pre.codBarraRevista AS codBarras, r.titulo AS titulo, pre.fechaPrestamo, pre.fechaDevolucion "
                + "FROM Prestamo_Revista_Estudiante pre, Revista r "
                + "WHERE pre.codBarraRevista = r.codBarraRevista AND pre.codEstudiante = '" + codEstudiante + "' AND pre.devuelto = 'si' "
                + "UNION "
                + "SELECT ppe.codBarraPeriodico AS codBarras, p.nombrePeriodico AS titulo, ppe.fechaPrestamo, ppe.fechaDevolucion "
                + "FROM Prestamo_Periodico_Estudiante ppe, Periodico p "
                + "WHERE ppe.codBarraPeriodico = p.codBarraPeriodico AND ppe.codEstudiante = '" + codEstudiante + "' AND ppe.devuelto = 'si'"
                + "UNION "
                + "SELECT pme.codBarraMapa AS codBarras, m.titulo AS titulo, pme.fechaPrestamo, pme.fechaDevolucion "
                + "FROM Prestamo_Mapa_Estudiante pme, Mapa m "
                + "WHERE pme.codBarraMapa = m.codBarraMapa AND pme.codEstudiante = '" + codEstudiante + "' AND pme.devuelto = 'si' "
                + "ORDER BY fechaPrestamo DESC";

        try {
            pps = connection.getConnection().prepareStatement(sqlSentence);
            rs = pps.executeQuery();

            while (rs.next()) {
                Prestamo prestamoTmp = new Prestamo();
                prestamoTmp.setCodBarrasRecurso(rs.getString("codBarras"));
                prestamoTmp.setTituloRecurso(rs.getString("titulo"));
                prestamoTmp.setFechaPrestamo(rs.getDate("fechaPrestamo"));
                prestamoTmp.setFechaDevolucion(rs.getDate("fechaDevolucion"));
                prestamosTmp.add(prestamoTmp);
            }

            if (!prestamosTmp.isEmpty()) {
                prestamosTmp.forEach((pres) -> {
                    prestamos.add(pres);
                });
            }

            rs.close();
        } catch (SQLException e) {
            System.out.println("Error al consultar el historial de préstamos del profesor");
        }

        return prestamos;
    }

    /**
     * el metódo busca todos los préstamos actuales que tenga el estudiante.
     *
     * @param codEstudiante
     * @return prestamos
     */
    @Override
    public ObservableList<Prestamo> getPrestamosActuales(String codEstudiante) {
        ObservableList<Prestamo> prestamos = FXCollections.observableArrayList();
        PreparedStatement pps;
        ResultSet rs;
        ArrayList<Prestamo> prestamosTmp = new ArrayList();

        String sqlSentence = "SELECT ple.codBarraLibro AS codBarras, l.titulo AS titulo, ple.fechaPrestamo, ple.fechaDevolucion "
                + "FROM Prestamo_Libro_Estudiante ple, Libro l "
                + "WHERE ple.codBarraLibro = l.codBarraLibro AND ple.codEstudiante = '" + codEstudiante + "' AND ple.devuelto = 'no' "
                + "UNION "
                + "SELECT pee.codBarraEnciclopedia AS codBarras, e.titulo AS titulo, pee.fechaPrestamo, pee.fechaDevolucion "
                + "FROM Prestamo_Enciclopedia_Estudiante pee, Enciclopedia e "
                + "WHERE pee.codBarraEnciclopedia = e.codBarraEnciclopedia AND pee.codEstudiante = '" + codEstudiante + "' AND pee.devuelto = 'no' "
                + "UNION "
                + "SELECT pde.codBarraDiccionario AS codBarras, d.titulo AS titulo, pde.fechaPrestamo, pde.fechaDevolucion "
                + "FROM Prestamo_Diccionario_Estudiante pde, Diccionario d "
                + "WHERE pde.codBarraDiccionario = d.codBarraDiccionario AND pde.codEstudiante = '" + codEstudiante + "' AND pde.devuelto = 'no' "
                + "UNION "
                + "SELECT pre.codBarraRevista AS codBarras, r.titulo AS titulo, pre.fechaPrestamo, pre.fechaDevolucion "
                + "FROM Prestamo_Revista_Estudiante pre, Revista r "
                + "WHERE pre.codBarraRevista = r.codBarraRevista AND pre.codEstudiante = '" + codEstudiante + "' AND pre.devuelto = 'no' "
                + "UNION "
                + "SELECT ppe.codBarraPeriodico AS codBarras, p.nombrePeriodico AS titulo, ppe.fechaPrestamo, ppe.fechaDevolucion "
                + "FROM Prestamo_Periodico_Estudiante ppe, Periodico p "
                + "WHERE ppe.codBarraPeriodico = p.codBarraPeriodico AND ppe.codEstudiante = '" + codEstudiante + "' AND ppe.devuelto = 'no' "
                + "UNION "
                + "SELECT pme.codBarraMapa AS codBarras, m.titulo AS titulo, pme.fechaPrestamo, pme.fechaDevolucion "
                + "FROM Prestamo_Mapa_Estudiante pme, Mapa m "
                + "WHERE pme.codBarraMapa = m.codBarraMapa AND pme.codEstudiante = '" + codEstudiante + "' AND pme.devuelto = 'no' "
                + "ORDER BY fechaPrestamo DESC ";

        try {
            pps = connection.getConnection().prepareStatement(sqlSentence);
            rs = pps.executeQuery();

            while (rs.next()) {
                Prestamo prestamoTmp = new Prestamo();
                prestamoTmp.setCodBarrasRecurso(rs.getString("codBarras"));
                prestamoTmp.setTituloRecurso(rs.getString("titulo"));
                prestamoTmp.setFechaPrestamo(rs.getDate("fechaPrestamo"));
                prestamoTmp.setFechaDevolucion(rs.getDate("fechaDevolucion"));
                prestamosTmp.add(prestamoTmp);
            }

            if (!prestamosTmp.isEmpty()) {
                prestamosTmp.forEach((pres) -> {
                    prestamos.add(pres);
                });
            }
            
            rs.close();
        } catch (SQLException e) {
            System.out.println("Error al consultar los préstamos actuales del estudiante");
        }   
        
        return prestamos;
    }

}

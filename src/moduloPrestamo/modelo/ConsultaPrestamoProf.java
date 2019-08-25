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
public class ConsultaPrestamoProf extends IConsultarPrestamo {

    /**
     * constructor de la clase sin parámetros.
     */
    public ConsultaPrestamoProf() {
        connection = ConnectionBD.getInstance();
    }

    /**
     * el metódo busca todos los préstamos que ha tenido un profesor.
     *
     * @param idProfesor
     * @return prestamos
     */
    @Override
    public ObservableList<Prestamo> getHistorialPrestamos(String idProfesor) {
        ObservableList<Prestamo> prestamos = FXCollections.observableArrayList();
        PreparedStatement pps;
        ResultSet rs;
        ArrayList<Prestamo> prestamosTmp = new ArrayList();

        String sqlSentence = "SELECT plp.codBarraLibro AS codBarras, l.titulo, plp.fechaPrestamo, plp.fechaDevolucion "
                + "FROM Prestamo_Libro_Profesor plp, Libro l "
                + "WHERE plp.codBarraLibro = l.codBarraLibro AND plp.idProfesor = '" + idProfesor + "' AND plp.devuelto = 'si'"
                + "UNION "
                + "SELECT pep.codBarraEnciclopedia AS codBarras, e.titulo, pep.fechaPrestamo, pep.fechaDevolucion "
                + "FROM Prestamo_Enciclopedia_Profesor pep, Enciclopedia e "
                + "WHERE pep.codBarraEnciclopedia = e.codBarraEnciclopedia AND pep.idProfesor = '" + idProfesor + "' AND pep.devuelto = 'si'"
                + "UNION "
                + "SELECT pdp.codBarraDiccionario AS codBarras, d.titulo, pdp.fechaPrestamo, pdp.fechaDevolucion "
                + "FROM Prestamo_Diccionario_Profesor pdp, Diccionario d "
                + "WHERE pdp.codBarraDiccionario = d.codBarraDiccionario AND pdp.idProfesor = '" + idProfesor + "' AND pdp.devuelto = 'si'"
                + "UNION "
                + "SELECT prp.codBarraRevista AS codBarras, r.titulo, prp.fechaPrestamo, prp.fechaDevolucion "
                + "FROM Prestamo_Revista_Profesor prp, Revista r "
                + "WHERE prp.codBarraRevista = r.codBarraRevista AND prp.idProfesor = '" + idProfesor + "' AND prp.devuelto = 'si'"
                + "UNION "
                + "SELECT ppp.codBarraPeriodico AS codBarras, p.nombrePeriodico AS titulo, ppp.fechaprestamo, ppp.fechaDevolucion "
                + "FROM Prestamo_Periodico_Profesor ppp, Periodico p "
                + "WHERE ppp.codBarraPeriodico = p.codBarraPeriodico AND ppp.idProfesor = '" + idProfesor + "' AND ppp.devuelto = 'si' "
                + "UNION "
                + "SELECT pmp.codBarraMapa AS codBarras, m.titulo, pmp.fechaprestamo, pmp.fechaDevolucion "
                + "FROM Prestamo_Mapa_Profesor pmp, Mapa m "
                + "WHERE pmp.codBarraMapa = m.codBarraMapa AND pmp.idProfesor = '" + idProfesor + "' AND pmp.devuelto = 'si' "
                + "ORDER BY fechaprestamo DESC";

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
     * el metódo busca todos los préstamos actuales que tenga el profesor.
     *
     * @param idProfesor
     * @return prestamos
     */
    @Override
    public ObservableList<Prestamo> getPrestamosActuales(String idProfesor) {
        ObservableList<Prestamo> prestamos = FXCollections.observableArrayList();
        PreparedStatement pps;
        ResultSet rs;
        ArrayList<Prestamo> prestamosTmp = new ArrayList();

        String sqlSentence = "SELECT plp.codBarraLibro AS codBarras, l.titulo, plp.fechaPrestamo, plp.fechaDevolucion "
                + "FROM Prestamo_Libro_Profesor plp, Libro l "
                + "WHERE plp.codBarraLibro = l.codBarraLibro AND plp.idProfesor = '" + idProfesor + "' AND plp.devuelto = 'no' "
                + "UNION "
                + "SELECT pep.codBarraEnciclopedia AS codBarras, e.titulo, pep.fechaPrestamo, pep.fechaDevolucion "
                + "FROM Prestamo_Enciclopedia_Profesor pep, Enciclopedia e "
                + "WHERE pep.codBarraEnciclopedia = e.codBarraEnciclopedia AND pep.idProfesor = '" + idProfesor + "' AND pep.devuelto = 'no' "
                + "UNION "
                + "SELECT pdp.codBarraDiccionario AS codBarras, d.titulo, pdp.fechaPrestamo, pdp.fechaDevolucion "
                + "FROM Prestamo_Diccionario_Profesor pdp, Diccionario d "
                + "WHERE pdp.codBarraDiccionario = d.codBarraDiccionario AND pdp.idProfesor = '" + idProfesor + "' AND pdp.devuelto = 'no' "
                + "UNION "
                + "SELECT prp.codBarraRevista AS codBarras, r.titulo, prp.fechaPrestamo, prp.fechaDevolucion "
                + "FROM Prestamo_Revista_Profesor prp, Revista r "
                + "WHERE prp.codBarraRevista = r.codBarraRevista AND prp.idProfesor = '" + idProfesor + "' AND prp.devuelto = 'no' "
                + "UNION "
                + "SELECT ppp.codBarraPeriodico AS codBarras, p.nombrePeriodico AS titulo, ppp.fechaprestamo, ppp.fechaDevolucion "
                + "FROM Prestamo_Periodico_Profesor ppp, Periodico p "
                + "WHERE ppp.codBarraPeriodico = p.codBarraPeriodico AND ppp.idProfesor = '" + idProfesor + "' AND ppp.devuelto = 'no' "
                + "UNION "
                + "SELECT pmp.codBarraMapa AS codBarras, m.titulo, pmp.fechaprestamo, pmp.fechaDevolucion "
                + "FROM Prestamo_Mapa_Profesor pmp, Mapa m "
                + "WHERE pmp.codBarraMapa = m.codBarraMapa AND pmp.idProfesor = '" + idProfesor + "' AND pmp.devuelto = 'no' "
                + "ORDER BY fechaprestamo DESC";

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
            System.out.println("Error al consultar los préstamos actuales del profesor");
        }

        return prestamos;
    }

}

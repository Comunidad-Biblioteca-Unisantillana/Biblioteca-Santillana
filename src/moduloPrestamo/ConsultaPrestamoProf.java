
package moduloPrestamo;

import entitys.Prestamo;
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
public class ConsultaPrestamoProf extends IConsultarPrestamo{

    @Override
    public ObservableList<Prestamo> getHistorialPrestamos(String idProfesor) {
        ObservableList<Prestamo> prestamos = FXCollections.observableArrayList();

        connection = ConnectionBD.getInstance();
        PreparedStatement pps;
        ResultSet rs;
        ArrayList<Prestamo> prestamosTmp = new ArrayList();

        String sqlSentence = "SELECT plp.codBarraLibro AS codBarraRecurso , l.titulo AS titulo, plp.fechaPrestamo, plp.fechaDevolucion, plp.devuelto  \n"
                + "	FROM Prestamo_Libro_Profesor plp, Libro l\n"
                + "	WHERE plp.codBarraLibro = l.codBarraLibro AND plp.idProfesor = '" + idProfesor + "'\n"
                + "UNION \n"
                + "SELECT pep.codBarraEnciclopedia, e.titulo, pep.fechaPrestamo, pep.fechaDevolucion, pep.devuelto \n"
                + "	FROM Prestamo_Enciclopedia_Profesor pep, Enciclopedia e\n"
                + "	WHERE pep.codBarraEnciclopedia = e.codBarraEnciclopedia AND pep.idProfesor = '" + idProfesor + "'\n"
                + "UNION \n"
                + "SELECT pdp.codBarraDiccionario, d.titulo, pdp.fechaPrestamo, pdp.fechaDevolucion, pdp.devuelto \n"
                + "	FROM Prestamo_Diccionario_Profesor pdp, Diccionario d\n"
                + "	WHERE pdp.codBarraDiccionario = d.codBarraDiccionario AND pdp.idProfesor = '" + idProfesor + "'\n"
                + "UNION \n"
                + "SELECT prp.codBarraRevista, r.titulo, prp.fechaPrestamo, prp.fechaDevolucion, prp.devuelto \n"
                + "	FROM Prestamo_Revista_Profesor prp, Revista r\n"
                + "	WHERE prp.codBarraRevista = r.codBarraRevista AND prp.idProfesor = '" + idProfesor + "'\n"
                + "UNION \n"
                + "SELECT ppp.codBarraPeriodico, p.nombrePeriodico, ppp.fechaprestamo, ppp.fechaDevolucion, ppp.devuelto \n"
                + "	FROM Prestamo_Periodico_Profesor ppp, Periodico p\n"
                + "	WHERE ppp.codBarraPeriodico = p.codBarraPeriodico AND ppp.idProfesor = '" + idProfesor + "'\n"
                + "UNION \n"
                + "SELECT pmp.codBarraMapa, m.titulo, pmp.fechaprestamo, pmp.fechaDevolucion, pmp.devuelto \n"
                + "	FROM Prestamo_Mapa_Profesor pmp, Mapa m\n"
                + "	WHERE pmp.codBarraMapa = m.codBarraMapa AND pmp.idProfesor = '" + idProfesor + "'\n";

        try {
            pps = connection.getConnection().prepareStatement(sqlSentence);
            rs = pps.executeQuery();

            while (rs.next()) {
                Prestamo prestamoTmp = new Prestamo(rs.getString("codBarraRecurso"), rs.getString("titulo"), rs.getDate("fechaPrestamo"),
                        rs.getDate("fechaDevolucion"));
                prestamoTmp.setDevuelto(rs.getString("devuelto").charAt(0));
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
            System.out.println("No se realizo el readAll correctamente");
        } catch (Exception e) {
            System.out.println("Problema en el readAll");
        } 
        return prestamos;
    }

    @Override
    public ObservableList<Prestamo> getPrestamosActuales(String idProfesor) {
        ObservableList<Prestamo> prestamos = FXCollections.observableArrayList();

        connection = ConnectionBD.getInstance();
        PreparedStatement pps;
        ResultSet rs;
        ArrayList<Prestamo> prestamosTmp = new ArrayList();

        String sqlSentence = "SELECT plp.codBarraLibro AS codBarraRecurso , l.titulo AS titulo, plp.fechaPrestamo, plp.fechaDevolucion\n"
                + "	FROM Prestamo_Libro_Profesor plp, Libro l\n"
                + "	WHERE plp.devuelto = 'no' AND plp.codBarraLibro = l.codBarraLibro AND plp.idProfesor = '" + idProfesor + "'\n"
                + "UNION \n"
                + "SELECT pep.codBarraEnciclopedia, e.titulo, pep.fechaPrestamo, pep.fechaDevolucion\n"
                + "	FROM Prestamo_Enciclopedia_Profesor pep, Enciclopedia e\n"
                + "	WHERE pep.devuelto = 'no' AND pep.codBarraEnciclopedia = e.codBarraEnciclopedia AND pep.idProfesor = '" + idProfesor + "'\n"
                + "UNION \n"
                + "SELECT pdp.codBarraDiccionario, d.titulo, pdp.fechaPrestamo, pdp.fechaDevolucion\n"
                + "	FROM Prestamo_Diccionario_Profesor pdp, Diccionario d\n"
                + "	WHERE pdp.devuelto = 'no' AND pdp.codBarraDiccionario = d.codBarraDiccionario AND pdp.idProfesor = '" + idProfesor + "'\n"
                + "UNION \n"
                + "SELECT prp.codBarraRevista, r.titulo, prp.fechaPrestamo, prp.fechaDevolucion\n"
                + "	FROM Prestamo_Revista_Profesor prp, Revista r\n"
                + "	WHERE prp.devuelto = 'no' AND prp.codBarraRevista = r.codBarraRevista AND prp.idProfesor = '" + idProfesor + "'\n"
                + "UNION \n"
                + "SELECT ppp.codBarraPeriodico, p.nombrePeriodico, ppp.fechaprestamo, ppp.fechaDevolucion\n"
                + "	FROM Prestamo_Periodico_Profesor ppp, Periodico p\n"
                + "	WHERE ppp.devuelto = 'no' AND ppp.codBarraPeriodico = p.codBarraPeriodico AND ppp.idProfesor = '" + idProfesor + "'\n"
                + "UNION \n"
                + "SELECT pmp.codBarraMapa, m.titulo, pmp.fechaprestamo, pmp.fechaDevolucion\n"
                + "	FROM Prestamo_Mapa_Profesor pmp, Mapa m\n"
                + "	WHERE pmp.devuelto = 'no' AND pmp.codBarraMapa = m.codBarraMapa AND pmp.idProfesor = '" + idProfesor + "'\n";

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
            System.out.println("No se realizo el readAll correctamente");
        } catch (Exception e) {
            System.out.println("Problema en el readAll");
        } 
        return prestamos;
    }
    
}

package moduloMulta.modelo;

import general.modelo.ConnectionBD;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import moduloMulta.entitys.Multa;

/**
 * clase que se encarga de consultar y eliminar las multas del estudiante
 *
 * @author Julian
 * @creado 31/08/2019
 * @modificado 31/08/2019
 */
public class ConsultaMultaEst extends ConsultaMultaAbs<Multa> {

    public ConsultaMultaEst() {
        connection = ConnectionBD.getInstance();
    }

    /**
     * Metodo que elimina una multa del estudiante
     *
     * @param codMulta
     * @param tipoRecurso
     * @return
     */
    @Override
    public boolean eliminarMulta(int codMulta, String tipoRecurso) {
        Statement stmt;
        ResultSet rs;
        String atributo;
        if (tipoRecurso.length() > 9) {
            atributo = "codMulta" + tipoRecurso.substring(0, 2) + "Est";
        } else {
            atributo = "codMulta" + tipoRecurso + "Est";
        }
        try {
            stmt = ConnectionBD.getInstance().getConnection().createStatement();
            System.out.println("Se establecio la conexión a la BD");

            String sqlSentence = "DELETE FROM Multa_" + tipoRecurso + "_Estudiante WHERE " + atributo + " = " + codMulta + ";";
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

    /**
     * Metodo que obtiene todas las multas de los estudiantes
     *
     * @return
     */
    @Override
    public ObservableList<Multa> getMultasAll() {
        ObservableList<Multa> multas = FXCollections.observableArrayList();

        ConnectionBD connection = ConnectionBD.getInstance();
        PreparedStatement pps;
        ResultSet rs;
        ArrayList<Multa> multasTmp = new ArrayList();
        String sqlSentence = 
        " SELECT e.codEstudiante, e.nombre, e.apellido, md.codMultaDicEst AS codMulta, d.codBarraDiccionario AS codBarra, d.titulo, md.diasAtrasados, md.valorTotalMulta, md.estadoCancelacion, md.fechaMulta"
            + " FROM Multa_Diccionario_Estudiante md, Diccionario d,Prestamo_Diccionario_Estudiante pd, Estudiante e"
            + " WHERE  pd.codPrestDicEst = md.codPrestDicEst AND pd.codBarraDiccionario = d.codBarraDiccionario AND e.codEstudiante = pd.codEstudiante"
            + " UNION " +
        " SELECT es.codEstudiante, es.nombre, es.apellido, me.codMultaEncEst AS codMulta,e.codBarraEnciclopedia AS codBarra,e.titulo ,me.diasAtrasados,me.valorTotalMulta,me.estadoCancelacion, me.fechaMulta"
            + " FROM Multa_Enciclopedia_Estudiante me, Enciclopedia e,Prestamo_Enciclopedia_Estudiante pe, Estudiante es"
            + " WHERE pe.codPrestEncEst = me.codPrestEncEst AND pe.codBarraEnciclopedia = e.codBarraEnciclopedia AND es.codEstudiante = pe.codEstudiante"
            + " UNION " +
        " SELECT e.codEstudiante, e.nombre, e.apellido, ml.codMultaLibroEst AS codMulta,l.codBarraLibro AS codBarra,l.titulo ,ml.diasAtrasados,ml.valorTotalMulta,ml.estadoCancelacion, ml.fechaMulta "
            + " FROM Multa_Libro_Estudiante ml, Libro l,Prestamo_Libro_Estudiante pl, Estudiante e"
            + " WHERE pl.codPrestLibroEst = ml.codPrestLibroEst AND pl.codBarraLibro = l.codBarraLibro AND e.codEstudiante = pl.codEstudiante"
            + " UNION " +
        "SELECT e.codEstudiante, e.nombre, e.apellido, mmapa.codMultaMapaEst AS codMulta, mapa.codBarraMapa AS codBarra,mapa.titulo ,mmapa.diasAtrasados,mmapa.valorTotalMulta,mmapa.estadoCancelacion, mmapa.fechaMulta"
            + " FROM Multa_Mapa_Estudiante mmapa, Mapa mapa,Prestamo_Mapa_Estudiante pmapa, Estudiante e"
            + " WHERE pmapa.codPrestMapaEst = mmapa.codPrestMapaEst AND pmapa.codBarraMapa = mapa.codBarraMapa AND e.codEstudiante = pmapa.codEstudiante"
            + " UNION " +
        " SELECT e.codEstudiante, e.nombre, e.apellido, mper.codMultaPeriodicoEst AS codMulta, per.codBarraPeriodico AS codBarra,per.nombrePeriodico AS titulo ,mper.diasAtrasados,mper.valorTotalMulta,mper.estadoCancelacion, mper.fechaMulta"
            + " FROM Multa_Periodico_Estudiante mper, Periodico per,Prestamo_Periodico_Estudiante pper, Estudiante e"
            + " WHERE pper.codPrestPeriodicoEst = mper.codPrestPeriodicoEst AND pper.codBarraPeriodico = per.codBarraPeriodico AND e.codEstudiante = pper.codEstudiante"
            + " UNION " +
        " SELECT e.codEstudiante, e.nombre, e.apellido, mr.codMultaRevistaEst AS codMulta, r.codBarraRevista AS codBarra,r.titulo ,mr.diasAtrasados,mr.valorTotalMulta,mr.estadoCancelacion, mr.fechaMulta"
            + " FROM Multa_Revista_Estudiante mr, Revista r,Prestamo_Revista_Estudiante pr, Estudiante e"
            + " WHERE pr.codPrestRevistaEst = mr.codPrestRevistaEst AND pr.codBarraRevista = r.codBarraRevista AND e.codEstudiante = pr.codEstudiante";
        
        try {
            pps = connection.getConnection().prepareStatement(sqlSentence);
            rs = pps.executeQuery();

            while (rs.next()) {
                Multa multaTmp = new Multa(rs.getString("estadoCancelacion"), rs.getInt("codMulta"), rs.getInt("diasAtrasados"), "", rs.getInt("valorTotalMulta"));
                multaTmp.setIdUsuario(rs.getString("codEstudiante"));
                multaTmp.setNombreUsuario(rs.getString("nombre") + " " + rs.getString("apellido"));
                multaTmp.setCodBarrasRecurso(rs.getString("codBarra"));
                multaTmp.setTituloRecurso(rs.getString("titulo"));
                multaTmp.setFechaMulta(rs.getDate("fechaMulta"));
                multasTmp.add(multaTmp);
            }
            rs.close();

            if (!multasTmp.isEmpty()) {
                multasTmp.forEach((mult) -> {
                    multas.add(mult);
                });
            }
        } catch (SQLException e) {
            System.out.println("No se realizo el readAll correctamente" + e.getMessage());
        } catch (Exception e) {
            System.out.println("Problema en el readAll");
        }

        return multas;
    }

    /**
     * Metodo que obtiene las multas del estudiante
     *
     * @param codUsuario
     * @return
     */
    @Override
    public ObservableList<Multa> getMultasUsuario(String codUsuario) {
        ObservableList<Multa> multas = FXCollections.observableArrayList();

        ConnectionBD connection = ConnectionBD.getInstance();
        PreparedStatement pps;
        ResultSet rs;
        ArrayList<Multa> multasTmp = new ArrayList();

        String sqlSentences[] = {
        " SELECT md.codMultaDicEst AS codMulta,d.codBarraDiccionario AS codBarra,d.titulo ,md.diasAtrasados,md.valorTotalMulta,md.estadoCancelacion"
            + "FROM Multa_Diccionario_Estudiante md, Diccionario d,Prestamo_Diccionario_Estudiante pd"
            + "WHERE pd.codEstudiante = '" + codUsuario + "' AND pd.codPrestDicEst = md.codPrestDicEst AND pd.codBarraDiccionario = d.codBarraDiccionario",
        " SELECT me.codMultaEncEst AS codMulta,e.codBarraEnciclopedia AS codBarra,e.titulo ,me.diasAtrasados,me.valorTotalMulta,me.estadoCancelacion"
            + "FROM Multa_Enciclopedia_Estudiante me, Enciclopedia e,Prestamo_Enciclopedia_Estudiante pe"
            + "WHERE pe.codEstudiante = '" + codUsuario + "' AND pe.codPrestEncEst = me.codPrestEncEst AND pe.codBarraEnciclopedia = e.codBarraEnciclopedia",
        " SELECT ml.codMultaLibroEst AS codMulta,l.codBarraLibro AS codBarra,l.titulo ,ml.diasAtrasados,ml.valorTotalMulta,ml.estadoCancelacion "
            + "FROM Multa_Libro_Estudiante ml, Libro l,Prestamo_Libro_Estudiante pl "
            + "WHERE pl.codEstudiante = '" + codUsuario + "' AND pl.codPrestLibroEst = ml.codPrestLibroEst AND pl.codBarraLibro = l.codBarraLibro",
        " SELECT mmapa.codMultaMapaEst AS codMulta, mapa.codBarraMapa AS codBarra,mapa.titulo ,mmapa.diasAtrasados,mmapa.valorTotalMulta,mmapa.estadoCancelacion"
            + "FROM Multa_Mapa_Estudiante mmapa, Mapa mapa,Prestamo_Mapa_Estudiante pmapa"
            + "WHERE pmapa.codEstudiante = '" + codUsuario + "' AND pmapa.codPrestMapaEst = mmapa.codPrestMapaEst AND pmapa.codBarraMapa = mapa.codBarraMapa",
        " SELECT mper.codMultaPeriodicoEst AS codMulta, per.codBarraPeriodico AS codBarra,per.nombrePeriodico AS titulo ,mper.diasAtrasados,mper.valorTotalMulta,mper.estadoCancelacion"
            + "FROM Multa_Periodico_Estudiante mper, Periodico per,Prestamo_Periodico_Estudiante pper"
            + "WHERE pper.codEstudiante = '" + codUsuario + "' AND pper.codPrestPeriodicoEst = mper.codPrestPeriodicoEst AND pper.codBarraPeriodico = per.codBarraPeriodico",
        " SELECT mr.codMultaRevistaEst AS codMulta, r.codBarraRevista AS codBarra,r.titulo ,mr.diasAtrasados,mr.valorTotalMulta,mr.estadoCancelacion"
            + "FROM Multa_Revista_Estudiante mr, Revista r,Prestamo_Revista_Estudiante pr"
            + "WHERE pr.codEstudiante = '" + codUsuario + "' AND pr.codPrestRevistaEst = mr.codPrestRevistaEst AND pr.codBarraRevista = r.codBarraRevista"};
        
        String[] tipoRecursos = {"Diccionario", "Enciclopedia", "Libro", "Mapa", "Periodico", "Revista"};

        for (int i = 0; i < tipoRecursos.length; i++) {
            try {
                pps = connection.getConnection().prepareStatement(sqlSentences[i]);
                rs = pps.executeQuery();

                while (rs.next()) {
                    Multa multaTmp = new Multa(rs.getString("estadoCancelacion"), rs.getInt("codMulta"), rs.getInt("diasAtrasados"), tipoRecursos[i], rs.getInt("valorTotalMulta"));
                    multaTmp.setCodBarrasRecurso(rs.getString("codBarra"));
                    multaTmp.setTituloRecurso(rs.getString("titulo"));
                    multasTmp.add(multaTmp);
                }
                rs.close();
            } catch (SQLException e) {
                System.out.println("no se encontro multa en la entidad " + tipoRecursos[i]);
            } catch (Exception e) {
                System.out.println("Problema en el readAll");
            }
        }
        if (!multasTmp.isEmpty()) {
            multasTmp.forEach((mult) -> {
                multas.add(mult);
            });
        }

        return multas;
    }

}

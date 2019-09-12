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
    public boolean eliminarMulta(int codMulta, String tipoRecurso,String descripcion) {
        String atributo;
        if (tipoRecurso.length() > 9) {
            atributo = "codMulta" + tipoRecurso.substring(0, 3) + "Est";
        } else {
            atributo = "codMulta" + tipoRecurso + "Est";
        }
        try {
            String sqlSentence = "UPDATE Multa_" + tipoRecurso + "_Estudiante SET estadoCancelacion = 'anulada',descripcionCancelacion = ?"
                    + " WHERE " + atributo + " = ?";
            PreparedStatement pps = connection.getConnection().prepareStatement(sqlSentence);
            pps.setString(1, descripcion);
            pps.setInt(2, codMulta);
            if(pps.executeUpdate() > 0){
                System.out.println("Actualizo");
                return true;
            }
        } catch (SQLException ex) {
            System.out.println(ex);
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
        String sqlSentence
                = " SELECT e.codEstudiante, e.nombre, e.apellido, md.codMultaDicEst AS codMulta, d.codBarraDiccionario AS codBarra, d.titulo, md.diasAtrasados, md.valorTotalMulta, md.estadoCancelacion, md.fechaMulta, TABLE_NAME "
                + " FROM Multa_Diccionario_Estudiante md, Diccionario d,Prestamo_Diccionario_Estudiante pd, Estudiante e, information_schema.TABLES "
                + " WHERE  pd.codPrestDicEst = md.codPrestDicEst AND pd.codBarraDiccionario = d.codBarraDiccionario AND e.codEstudiante = pd.codEstudiante AND md.estadoCancelacion = 'no' AND table_name = 'diccionario'"
                + " UNION "
                + " SELECT es.codEstudiante, es.nombre, es.apellido, me.codMultaEncEst AS codMulta,e.codBarraEnciclopedia AS codBarra,e.titulo ,me.diasAtrasados,me.valorTotalMulta,me.estadoCancelacion, me.fechaMulta, TABLE_NAME "
                + " FROM Multa_Enciclopedia_Estudiante me, Enciclopedia e,Prestamo_Enciclopedia_Estudiante pe, Estudiante es, information_schema.TABLES "
                + " WHERE pe.codPrestEncEst = me.codPrestEncEst AND pe.codBarraEnciclopedia = e.codBarraEnciclopedia AND es.codEstudiante = pe.codEstudiante AND me.estadoCancelacion = 'no' AND table_name = 'enciclopedia'"
                + " UNION "
                + " SELECT e.codEstudiante, e.nombre, e.apellido, ml.codMultaLibroEst AS codMulta,l.codBarraLibro AS codBarra,l.titulo ,ml.diasAtrasados,ml.valorTotalMulta,ml.estadoCancelacion, ml.fechaMulta, TABLE_NAME "
                + " FROM Multa_Libro_Estudiante ml, Libro l,Prestamo_Libro_Estudiante pl, Estudiante e, information_schema.TABLES "
                + " WHERE pl.codPrestLibroEst = ml.codPrestLibroEst AND pl.codBarraLibro = l.codBarraLibro AND e.codEstudiante = pl.codEstudiante AND ml.estadoCancelacion = 'no' AND table_name = 'libro'"
                + " UNION "
                + " SELECT e.codEstudiante, e.nombre, e.apellido, mmapa.codMultaMapaEst AS codMulta, mapa.codBarraMapa AS codBarra,mapa.titulo ,mmapa.diasAtrasados,mmapa.valorTotalMulta,mmapa.estadoCancelacion, mmapa.fechaMulta, TABLE_NAME "
                + " FROM Multa_Mapa_Estudiante mmapa, Mapa mapa,Prestamo_Mapa_Estudiante pmapa, Estudiante e, information_schema.TABLES "
                + " WHERE pmapa.codPrestMapaEst = mmapa.codPrestMapaEst AND pmapa.codBarraMapa = mapa.codBarraMapa AND e.codEstudiante = pmapa.codEstudiante AND mmapa.estadoCancelacion = 'no' AND table_name = 'mapa'"
                + " UNION "
                + " SELECT e.codEstudiante, e.nombre, e.apellido, mper.codMultaPeriodicoEst AS codMulta, per.codBarraPeriodico AS codBarra,per.nombrePeriodico AS titulo ,mper.diasAtrasados,mper.valorTotalMulta,mper.estadoCancelacion, mper.fechaMulta, TABLE_NAME "
                + " FROM Multa_Periodico_Estudiante mper, Periodico per,Prestamo_Periodico_Estudiante pper, Estudiante e, information_schema.TABLES "
                + " WHERE pper.codPrestPeriodicoEst = mper.codPrestPeriodicoEst AND pper.codBarraPeriodico = per.codBarraPeriodico AND e.codEstudiante = pper.codEstudiante AND mper.estadoCancelacion = 'no' AND table_name = 'periodico'"
                + " UNION "
                + " SELECT e.codEstudiante, e.nombre, e.apellido, mr.codMultaRevistaEst AS codMulta, r.codBarraRevista AS codBarra,r.titulo ,mr.diasAtrasados,mr.valorTotalMulta,mr.estadoCancelacion, mr.fechaMulta, TABLE_NAME "
                + " FROM Multa_Revista_Estudiante mr, Revista r,Prestamo_Revista_Estudiante pr, Estudiante e, information_schema.TABLES "
                + " WHERE pr.codPrestRevistaEst = mr.codPrestRevistaEst AND pr.codBarraRevista = r.codBarraRevista AND e.codEstudiante = pr.codEstudiante AND mr.estadoCancelacion = 'no' AND table_name = 'revista'"
                + " ORDER BY fechaMulta DESC";

        try {
            pps = connection.getConnection().prepareStatement(sqlSentence);
            rs = pps.executeQuery();

            while (rs.next()) {
                Multa multaTmp = new Multa(rs.getString("estadoCancelacion"), rs.getInt("codMulta"), rs.getInt("diasAtrasados"), rs.getString("TABLE_NAME"), rs.getInt("valorTotalMulta"));
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

        String sqlSentence = 
              " SELECT md.codMultaDicEst AS codMulta,d.codBarraDiccionario AS codBarra,d.titulo ,md.diasAtrasados,md.valorTotalMulta,md.estadoCancelacion, TABLE_NAME "
            + " FROM Multa_Diccionario_Estudiante md, Diccionario d,Prestamo_Diccionario_Estudiante pd, information_schema.TABLES "
            + " WHERE pd.codEstudiante = '" + codUsuario + "' AND pd.codPrestDicEst = md.codPrestDicEst AND pd.codBarraDiccionario = d.codBarraDiccionario AND md.estadoCancelacion = 'no' AND table_name = 'diccionario'"
            + " UNION "
            + " SELECT me.codMultaEncEst AS codMulta,e.codBarraEnciclopedia AS codBarra,e.titulo ,me.diasAtrasados,me.valorTotalMulta,me.estadoCancelacion, TABLE_NAME "
            + " FROM Multa_Enciclopedia_Estudiante me, Enciclopedia e,Prestamo_Enciclopedia_Estudiante pe, information_schema.TABLES "
            + " WHERE pe.codEstudiante = '" + codUsuario + "' AND pe.codPrestEncEst = me.codPrestEncEst AND pe.codBarraEnciclopedia = e.codBarraEnciclopedia AND me.estadoCancelacion = 'no' AND table_name = 'enciclopedia'"
            + " UNION "
            + " SELECT ml.codMultaLibroEst AS codMulta,l.codBarraLibro AS codBarra,l.titulo ,ml.diasAtrasados,ml.valorTotalMulta,ml.estadoCancelacion, TABLE_NAME "
            + " FROM Multa_Libro_Estudiante ml, Libro l,Prestamo_Libro_Estudiante pl, information_schema.TABLES "
            + " WHERE pl.codEstudiante = '" + codUsuario + "' AND pl.codPrestLibroEst = ml.codPrestLibroEst AND pl.codBarraLibro = l.codBarraLibro AND ml.estadoCancelacion = 'no' AND table_name = 'libro'"
            + " UNION "
            + " SELECT mmapa.codMultaMapaEst AS codMulta, mapa.codBarraMapa AS codBarra,mapa.titulo ,mmapa.diasAtrasados,mmapa.valorTotalMulta,mmapa.estadoCancelacion, TABLE_NAME "
            + " FROM Multa_Mapa_Estudiante mmapa, Mapa mapa,Prestamo_Mapa_Estudiante pmapa, information_schema.TABLES "
            + " WHERE pmapa.codEstudiante = '" + codUsuario + "' AND pmapa.codPrestMapaEst = mmapa.codPrestMapaEst AND pmapa.codBarraMapa = mapa.codBarraMapa AND mmapa.estadoCancelacion = 'no' AND table_name = 'mapa'"
            + " UNION "
            + " SELECT mper.codMultaPeriodicoEst AS codMulta, per.codBarraPeriodico AS codBarra,per.nombrePeriodico AS titulo ,mper.diasAtrasados,mper.valorTotalMulta,mper.estadoCancelacion, TABLE_NAME "
            + " FROM Multa_Periodico_Estudiante mper, Periodico per,Prestamo_Periodico_Estudiante pper, information_schema.TABLES "
            + " WHERE pper.codEstudiante = '" + codUsuario + "' AND pper.codPrestPeriodicoEst = mper.codPrestPeriodicoEst AND pper.codBarraPeriodico = per.codBarraPeriodico AND mper.estadoCancelacion = 'no' AND table_name = 'periodico'"
            + " UNION "
            + " SELECT mr.codMultaRevistaEst AS codMulta, r.codBarraRevista AS codBarra,r.titulo ,mr.diasAtrasados,mr.valorTotalMulta,mr.estadoCancelacion, TABLE_NAME "
            + " FROM Multa_Revista_Estudiante mr, Revista r,Prestamo_Revista_Estudiante pr, information_schema.TABLES "
            + " WHERE pr.codEstudiante = '" + codUsuario + "' AND pr.codPrestRevistaEst = mr.codPrestRevistaEst AND pr.codBarraRevista = r.codBarraRevista AND mr.estadoCancelacion = 'no' AND table_name = 'revista'";
        try {
            pps = connection.getConnection().prepareStatement(sqlSentence);
            rs = pps.executeQuery();

            while (rs.next()) {
                Multa multaTmp = new Multa(rs.getString("estadoCancelacion"), rs.getInt("codMulta"), rs.getInt("diasAtrasados"), rs.getString("TABLE_NAME"), rs.getInt("valorTotalMulta"));
                multaTmp.setCodBarrasRecurso(rs.getString("codBarra"));
                multaTmp.setTituloRecurso(rs.getString("titulo"));
                multasTmp.add(multaTmp);
            }
            rs.close();
        } catch (SQLException e) {

        } catch (Exception e) {
            System.out.println("Problema en el readAll");
        }
        if (!multasTmp.isEmpty()) {
            multasTmp.forEach((mult) -> {
                multas.add(mult);
            });
        }

        return multas;
    }

}

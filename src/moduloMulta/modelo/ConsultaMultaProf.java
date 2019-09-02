
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
 * clase que se encarga de consultar y eliminar las multas del profesor
 * @author Julian
 * @creado 31/08/2019
 * @modificado 31/08/2019
 */
public class ConsultaMultaProf extends ConsultaMultaAbs<Multa>{

    /**
     * Constructor
     */
    public ConsultaMultaProf() {
        connection = ConnectionBD.getInstance();
    }
    
    /**
     * Metodo que elimina una multa del profesor
     * @param codMulta
     * @param tipoRecurso
     * @return 
     */
    @Override
    public boolean eliminarMulta(int codMulta, String tipoRecurso) {
        Statement stmt;
        ResultSet rs;
        String atributo;
        if(tipoRecurso.length() > 9){
            atributo = "codMulta" + tipoRecurso.substring(0, 2) + "Prof";
        }else{
            atributo = "codMulta" + tipoRecurso + "Prof";
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
     * Metodo que obtiene todas las multas de los profesores
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
            "SELECT p.idProfesor, p.nombres, p.apellidos, md.codMultaDicProf AS codMulta,d.codBarraDiccionario AS codBarra,d.titulo ,md.diasAtrasados,md.valorTotalMulta,md.estadoCancelacion, md.fechaMulta"
                + " FROM Multa_Diccionario_Profesor md, Diccionario d,Prestamo_Diccionario_Profesor pd, Profesor p"
                + " WHERE pd.codPrestDicProf = md.codPrestDicProf AND pd.codBarraDiccionario = d.codBarraDiccionario AND p.idProfesor = pd.idProfesor"
                + " UNION "
                + " SELECT p.idProfesor, p.nombres, p.apellidos, me.codMultaEncProf AS codMulta,e.codBarraEnciclopedia AS codBarra,e.titulo ,me.diasAtrasados,me.valorTotalMulta,me.estadoCancelacion, me.fechaMulta"
                + " FROM Multa_Enciclopedia_Profesor me, Enciclopedia e,Prestamo_Enciclopedia_Profesor pe, Profesor p"
                + " WHERE pe.codPrestEncProf = me.codPrestEncProf AND pe.codBarraEnciclopedia = e.codBarraEnciclopedia AND p.idProfesor = pe.idProfesor"
                + " UNION "   
                + " SELECT p.idProfesor, p.nombres, p.apellidos, ml.codMultaLibroProf AS codMulta,l.codBarraLibro AS codBarra,l.titulo ,ml.diasAtrasados,ml.valorTotalMulta,ml.estadoCancelacion, ml.fechaMulta "
                + " FROM Multa_Libro_Profesor ml, Libro l,Prestamo_Libro_Profesor pl, Profesor p"
                + " WHERE pl.codPrestLibroProf = ml.codPrestLibroProf AND pl.codBarraLibro = l.codBarraLibro AND p.idProfesor = pl.idProfesor"
                + " UNION "
                + " SELECT p.idProfesor, p.nombres, p.apellidos, mmapa.codMultaMapaProf AS codMulta, mapa.codBarraMapa AS codBarra,mapa.titulo ,mmapa.diasAtrasados,mmapa.valorTotalMulta,mmapa.estadoCancelacion, mmapa.fechaMulta"
                + " FROM Multa_Mapa_Profesor mmapa, Mapa mapa,Prestamo_Mapa_Profesor pmapa, Profesor p"
                + " WHERE pmapa.codPrestMapaProf = mmapa.codPrestMapaProf AND pmapa.codBarraMapa = mapa.codBarraMapa AND p.idProfesor = pmapa.idProfesor"
                + " UNION "
                + " SELECT p.idProfesor, p.nombres, p.apellidos, mper.codMultaPeriodicoProf AS codMulta, per.codBarraPeriodico AS codBarra,per.nombrePeriodico AS titulo ,mper.diasAtrasados,mper.valorTotalMulta,mper.estadoCancelacion, mper.fechaMulta"
                + " FROM Multa_Periodico_Profesor mper, Periodico per,Prestamo_Periodico_Profesor pper, Profesor p"
                + " WHERE pper.codPrestPeriodicoProf = mper.codPrestPeriodicoProf AND pper.codBarraPeriodico = per.codBarraPeriodico AND p.idProfesor = pper.idProfesor"
                + " UNION "
                + " SELECT p.idProfesor, p.nombres, p.apellidos, mr.codMultaRevistaProf AS codMulta, r.codBarraRevista AS codBarra,r.titulo ,mr.diasAtrasados,mr.valorTotalMulta,mr.estadoCancelacion, mr.fechaMulta"
                + " FROM Multa_Revista_Profesor mr, Revista r,Prestamo_Revista_Profesor pr, Profesor p"
                + " WHERE pr.codPrestRevistaProf = mr.codPrestRevistaProf AND pr.codBarraRevista = r.codBarraRevista AND p.idProfesor = pr.idProfesor";
        try {
            pps = connection.getConnection().prepareStatement(sqlSentence);
            rs = pps.executeQuery();

            while (rs.next()) {
                Multa multaTmp = new Multa(rs.getString("estadoCancelacion"), rs.getInt("codMulta"), rs.getInt("diasAtrasados"), "", rs.getInt("valorTotalMulta"));
                multaTmp.setIdUsuario(rs.getString("idProfesor"));
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
            }else{
                System.out.println("no hay multas");
            }
        } catch (SQLException e) {
            System.out.println("No se realizo el readAll correctamente" + e.getMessage());
        } catch (Exception e) {
            System.out.println("Problema en el readAll");
        }
        
        return multas;
    }

    /**
     * Metodo que obtiene las multas de un profesor
     * @param idUsuario
     * @return 
     */
    @Override
    public ObservableList<Multa> getMultasUsuario(String idUsuario) {
        ObservableList<Multa> multas = FXCollections.observableArrayList();

        ConnectionBD connection = ConnectionBD.getInstance();
        PreparedStatement pps;
        ResultSet rs;
        ArrayList<Multa> multasTmp = new ArrayList();
        
        String sqlSentences[] = {
            "SELECT md.codMultaDicProf AS codMulta,d.codBarraDiccionario AS codBarra,d.titulo ,md.diasAtrasados,md.valorTotalMulta,md.estadoCancelacion"
                + "FROM Multa_Diccionario_Profesor md, Diccionario d,Prestamo_Diccionario_Profesor pd"
                + "WHERE pd.idProfesor = '" + idUsuario + "' AND pd.codPrestDicProf = md.codPrestDicProf AND pd.codBarraDiccionario = d.codBarraDiccionario"
          , "SELECT me.codMultaEncProf AS codMulta,e.codBarraEnciclopedia AS codBarra,e.titulo ,me.diasAtrasados,me.valorTotalMulta,me.estadoCancelacion"
                + "FROM Multa_Enciclopedia_Profesor me, Enciclopedia e,Prestamo_Enciclopedia_Profesor pe"
                + "WHERE pe.idProfesor = '" + idUsuario + "' AND pe.codPrestEncProf = me.codPrestEncProf AND pe.codBarraEnciclopedia = e.codBarraEnciclopedia"
          , "SELECT ml.codMultaLibroProf AS codMulta,l.codBarraLibro AS codBarra,l.titulo ,ml.diasAtrasados,ml.valorTotalMulta,ml.estadoCancelacion "
                + "FROM Multa_Libro_Profesor ml, Libro l,Prestamo_Libro_Profesor pl "
                + "WHERE pl.idProfesor = '" + idUsuario + "' AND pl.codPrestLibroProf = ml.codPrestLibroProf AND pl.codBarraLibro = l.codBarraLibro"
          , "SELECT mmapa.codMultaMapaProf AS codMulta, mapa.codBarraMapa AS codBarra,mapa.titulo ,mmapa.diasAtrasados,mmapa.valorTotalMulta,mmapa.estadoCancelacion"
                + "FROM Multa_Mapa_Profesor mmapa, Mapa mapa,Prestamo_Mapa_Profesor pmapa"
                + "WHERE pmapa.idProfesor = '" + idUsuario + "' AND pmapa.codPrestMapaProf = mmapa.codPrestMapaProf AND pmapa.codBarraMapa = mapa.codBarraMapa"
          , "SELECT mper.codMultaPeriodicoProf AS codMulta, per.codBarraPeriodico AS codBarra,per.nombrePeriodico AS titulo ,mper.diasAtrasados,mper.valorTotalMulta,mper.estadoCancelacion"
                + "FROM Multa_Periodico_Profesor mper, Periodico per,Prestamo_Periodico_Profesor pper"
                + "WHERE pper.idProfesor = '" + idUsuario + "' AND pper.codPrestPeriodicoProf = mper.codPrestPeriodicoProf AND pper.codBarraPeriodico = per.codBarraPeriodico"
          , "SELECT mr.codMultaRevistaProf AS codMulta, r.codBarraRevista AS codBarra,r.titulo ,mr.diasAtrasados,mr.valorTotalMulta,mr.estadoCancelacion"
                + "FROM Multa_Revista_Profesor mr, Revista r,Prestamo_Revista_Profesor pr"
                + "WHERE pr.idProfesor = '" + idUsuario + "' AND pr.codPrestRevistaProf = mr.codPrestRevistaProf AND pr.codBarraRevista = r.codBarraRevista"};
        
        String[] tipoRecursos = {"Diccionario","Enciclopedia","Libro","Mapa","Periodico","Revista"};
        try {
            for (int i = 0; i < tipoRecursos.length; i++) {
                pps = connection.getConnection().prepareStatement(sqlSentences[i]);
                rs = pps.executeQuery();

                while (rs.next()) {
                    Multa multaTmp = new Multa(rs.getString("estadoCancelacion"), rs.getInt("codMulta"),rs.getInt("diasAtrasados") ,tipoRecursos[i], rs.getInt("valorTotalMulta"));
                    multaTmp.setCodBarrasRecurso(rs.getString("codBarra"));
                    multaTmp.setTituloRecurso(rs.getString("titulo"));
                    multasTmp.add(multaTmp);
                }
                rs.close();
            }
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
    
}

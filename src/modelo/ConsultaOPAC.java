
package modelo;

import controllers.DiccionarioJpaController;
import controllers.EnciclopediaJpaController;
import controllers.LibroJpaController;
import controllers.MapaJpaController;
import controllers.PeriodicoJpaController;
import controllers.RevistaJpaController;
import entitys.Diccionario;
import entitys.Enciclopedia;
import entitys.Libro;
import entitys.Mapa;
import entitys.Multa;
import entitys.Periodico;
import entitys.Recurso;
import entitys.Revista;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Camilo
 */
public class ConsultaOPAC {
    
    public ConsultaOPAC(){

    }
    
    public ObservableList<Recurso> consultarRecursos(String cadena) throws Exception{
        ObservableList<Recurso> recursos = FXCollections.observableArrayList();
        ArrayList<Recurso> listaRecursos = new ArrayList<>();
        
        List<Libro> listaLibros = consultarLibros(cadena);
        List<Enciclopedia> listaEnciclopedias = consultarEnciclopedias(cadena);
        List<Diccionario> listaDiccionarios = consultarDiccionarios(cadena);
        List<Revista> listaRevistas = consultarRevistas(cadena);
        List<Periodico> listaPeriodicos = consultarPeriodicos(cadena);
        List<Mapa> listaMapas = consultarMapas(cadena);
        
        for(Libro libs : listaLibros) {
            Recurso recursoTmp = new Recurso(libs.getCodbarralibro(), libs.getIsbn(), libs.getTitulo(), libs.getIdioma(), "N/A",libs.getDisponibilidad());
            listaRecursos.add(recursoTmp);
        }
        
        for(Enciclopedia encs : listaEnciclopedias) {
            Recurso recursoTmp = new Recurso(encs.getCodbarraenciclopedia(), encs.getIsbn(), encs.getTitulo(), encs.getIdioma(), "N/A",encs.getDisponibilidad());
            listaRecursos.add(recursoTmp);
        }
        
        for(Diccionario dics : listaDiccionarios) {
            Recurso recursoTmp = new Recurso(dics.getCodbarradiccionario(), dics.getIsbn(), dics.getTitulo(), dics.getIdioma(), "N/A",dics.getDisponibilidad());
            listaRecursos.add(recursoTmp);
        }
        
        for(Revista revs : listaRevistas) {
            Recurso recursoTmp = new Recurso(revs.getCodbarrarevista(), revs.getIssn(), revs.getTitulo(), revs.getIdioma(), "N/A",revs.getDisponibilidad());
            listaRecursos.add(recursoTmp);
        }
        
        for(Periodico pers : listaPeriodicos) {
            Recurso recursoTmp = new Recurso(pers.getCodbarraperiodico(), pers.getIssn(), pers.getNombreperiodico(), "N/A", "N/A",pers.getDisponibilidad());
            listaRecursos.add(recursoTmp);
        }
        
        for(Mapa maps : listaMapas) {
            Recurso recursoTmp = new Recurso(maps.getCodbarramapa(), maps.getIsbn(), maps.getTitulo(), "N/A", "N/A",maps.getDisponibilidad());
            listaRecursos.add(recursoTmp);
        }
        
        if(!listaRecursos.isEmpty()){ listaRecursos.forEach((rec) -> {recursos.add(rec); });}

        return recursos;
    }
    
    /**
     * Método que obtiene la tabla que se va a consultar.
     * @param codBarras
     * @return 
     */
    public String consultarTabla(String codBarras) {
        String nombreEntidad = "";
        Statement stmt;
        ResultSet rs;
        String consulta;
        String entidades[] = {"libro", "enciclopedia", "mapa", "revista", "periodico", "diccionario"};
        try {
            stmt = ConnectionBD.getInstance().getConnection().createStatement();
            System.out.println("Se establecio la conexión a la BD");

            for (int i = 0; i < entidades.length; i++) {
                consulta = "SELECT * FROM " + entidades[i] + " WHERE codbarra" + entidades[i] + " = '" + codBarras + "'";
                rs = stmt.executeQuery(consulta);
                if (rs.next()) {
                    nombreEntidad = entidades[i];
                    rs.close();
                    break;
                }
            }

            stmt.close();
        } catch (SQLException ex) {
            System.out.println("Error al encontrar la tabla");
        }
        return nombreEntidad;
    }
    
    private List<Libro> consultarLibros(String cadena) throws Exception{      
        LibroJpaController control = new LibroJpaController();
        List<Libro> libros = control.findLibroTitulo(cadena);
        
        for(Libro libs : libros) System.out.println(libs);
        return libros;
    }
    
    private List<Enciclopedia> consultarEnciclopedias(String cadena) throws Exception{      
        EnciclopediaJpaController control = new EnciclopediaJpaController();
        List<Enciclopedia> enciclopedias = control.findEnciclopediaTitulo(cadena);
        
        for(Enciclopedia encs : enciclopedias) System.out.println(encs);
        return enciclopedias;
    }
    
    private List<Diccionario> consultarDiccionarios(String cadena) throws Exception{      
        DiccionarioJpaController control = new DiccionarioJpaController();
        List<Diccionario> diccionarios = control.findDiccionarioTitulo(cadena);
        
        for(Diccionario dics : diccionarios) System.out.println(dics);
        return diccionarios;
    }
    
    private List<Revista> consultarRevistas(String cadena) throws Exception{      
        RevistaJpaController control = new RevistaJpaController();
        List<Revista> revistas = control.findRevistaTitulo(cadena);
        
        for(Revista revs : revistas) System.out.println(revs);
        return revistas;
    }
    
    private List<Periodico> consultarPeriodicos(String cadena) throws Exception{      
        PeriodicoJpaController control = new PeriodicoJpaController();
        List<Periodico> periodicos = control.findPeriodicoTitulo(cadena);
        
        for(Periodico pers : periodicos) System.out.println(pers);
        return periodicos;
    }
    
    private List<Mapa> consultarMapas(String cadena) throws Exception{      
        MapaJpaController control = new MapaJpaController();
        List<Mapa> mapas = control.findMapaTitulo(cadena);
        
        for(Mapa maps : mapas) System.out.println(maps);
        return mapas;
    }
}

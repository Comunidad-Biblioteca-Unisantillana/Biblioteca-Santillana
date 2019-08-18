/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import controllers.AutorDiccionarioJpaController;
import controllers.AutorEnciclopediaJpaController;
import controllers.AutorLibroJpaController;
import controllers.AutorPorDiccionarioJpaController;
import controllers.AutorPorEnciclopediaJpaController;
import controllers.AutorPorLibroJpaController;
import controllers.MateriaPorDiccionarioJpaController;
import controllers.MateriaPorEnciclopediaJpaController;
import controllers.MateriaPorLibroJpaController;
import controllers.MateriaPorRevistaJpaController;
import entitys.AutorDiccionario;
import entitys.AutorEnciclopedia;
import entitys.AutorLibro;
import entitys.Materia;
import entitys.MateriaPorDiccionario;
import entitys.MateriaPorEnciclopedia;
import entitys.MateriaPorLibro;
import entitys.MateriaPorRevista;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author stive
 */
public class QueryControl {
    private ArrayList<String> atributos, listaNombres;
    private Statement stmt;
    private ResultSet rs;
    private String nombreTabla;
    private String cadenaAutores = "";
    private String cadenaMaterias = "";
    
    private QueryControl() {
        atributos = new ArrayList();
        listaNombres = new ArrayList();
    }

    /**
     * Método que se encarga de consultar los autores de un recurso determinado.
     * @param codBarras
     * @param tipoRecurso 
     */
    public void consultarAutorRecurso(String codBarras, String tipoRecurso) {     
        cadenaAutores = "";
        switch(tipoRecurso.toLowerCase()){
            case "libro":
                AutorLibroJpaController ctrlLib = new AutorLibroJpaController();
                AutorPorLibroJpaController controladorAutLib = new AutorPorLibroJpaController();
                
                List<AutorLibro> listAutLib =  controladorAutLib.findAutorPorLibroCodBarras(codBarras); 
                if(listAutLib != null){
                    for(AutorLibro aut: listAutLib) cadenaAutores += "- " + aut.getNombres()+ " " + aut.getApellidos()+"\n";              
                }
                break;
            case "enciclopedia":
                AutorEnciclopediaJpaController ctrlEnc = new AutorEnciclopediaJpaController();
                AutorPorEnciclopediaJpaController controladorAutEnc = new AutorPorEnciclopediaJpaController();
                
                List<AutorEnciclopedia> listAutEnc =  controladorAutEnc.findAutorPorEnciclopediaCodBarras(codBarras); 
                if(listAutEnc != null){
                    for(AutorEnciclopedia aut: listAutEnc) cadenaAutores += "- " + aut.getNombres()+ " " + aut.getApellidos()+"\n";              
                }

                break;
            
            case "diccionario":
                AutorDiccionarioJpaController ctrlDic = new AutorDiccionarioJpaController();
                AutorPorDiccionarioJpaController controladorAutDic = new AutorPorDiccionarioJpaController();
                
                List<AutorDiccionario> listAutDic =  controladorAutDic.findAutorPorDiccionarioCodBarras(codBarras); 
                if(listAutDic != null){
                    for(AutorDiccionario aut: listAutDic) cadenaAutores += "- " + aut.getNombres()+ " " + aut.getApellidos()+"\n";              
                }
                break;
            default: break;
        }
    }
    
    public void consultarMaterias(String codBarras, String tipoRecurso){
        cadenaMaterias = "";
        switch(tipoRecurso.toLowerCase()){
            case "libro":
                MateriaPorLibroJpaController ctrlLib = new MateriaPorLibroJpaController();       
                List<MateriaPorLibro> listMatLib =  ctrlLib.findMateriaPorLibroCodBarras(codBarras); 
                if(listMatLib != null){
                    for(MateriaPorLibro mat: listMatLib){
                        Materia materia = new Materia(mat.getCodmaterialibro());
                        cadenaMaterias += "- " + materia.getNombremateria() +"\n";  
                    }            
                }
                break;
            case "enciclopedia":
                MateriaPorEnciclopediaJpaController ctrlEnc = new MateriaPorEnciclopediaJpaController();       
                List<MateriaPorEnciclopedia> listMatEnc =  ctrlEnc.findMateriaPorEnciclopediaCodBarras(codBarras); 
                if(listMatEnc != null){
                    for(MateriaPorEnciclopedia mat: listMatEnc) {
                        Materia materia = new Materia(mat.getCodmateriaenciclopedia());
                        cadenaMaterias += "- " + materia.getNombremateria()+"\n";
                    }
                }
                break;
            case "diccionario":
                MateriaPorDiccionarioJpaController ctrlDic = new MateriaPorDiccionarioJpaController();       
                List<MateriaPorDiccionario> listMatDic =  ctrlDic.findMateriaPorDiccionarioCodBarras(codBarras); 
                if(listMatDic != null){
                    for(MateriaPorDiccionario mat: listMatDic){
                        Materia materia = new Materia(mat.getCodmateriadiccionario());
                        cadenaMaterias += "- " + materia.getNombremateria() +"\n";
                }
                }
                break;
            case "revista":
                MateriaPorRevistaJpaController ctrlRev = new MateriaPorRevistaJpaController();       
                List<MateriaPorRevista> listMatRev =  ctrlRev.findMateriaPorRevistaCodBarras(codBarras); 
                if(listMatRev != null){
                    for(MateriaPorRevista mat: listMatRev){ 
                        Materia materia = new Materia(mat.getCodmateriarevista());
                        cadenaMaterias += "- " + materia.getNombremateria() +"\n";
                    }            
                }
                break;
        }
    }

    
    public void ordernarTabla() {
        String tempTitulo, auxTitulo, tempNota, auxNota, tempResumen, auxResumen;

        for (int i = 0; i < atributos.size(); i++) {
            if (atributos.get(i).equalsIgnoreCase("titulo")) {
                tempTitulo = atributos.get(i);
                auxTitulo = listaNombres.get(i);

                atributos.remove(i);
                listaNombres.remove(i);

                atributos.add(tempTitulo);
                listaNombres.add(auxTitulo);
                break;
            }
        }
        for (int i = 0; i < atributos.size(); i++) {
            if (atributos.get(i).equalsIgnoreCase("nota")) {
                tempNota = atributos.get(i);
                auxNota = listaNombres.get(i);

                atributos.remove(i);
                listaNombres.remove(i);

                atributos.add(tempNota);
                listaNombres.add(auxNota);
                break;
            }
        }
        for (int i = 0; i < atributos.size(); i++) {
            if (atributos.get(i).equalsIgnoreCase("resumen") || atributos.get(i).equalsIgnoreCase("descripcion")) {
                tempResumen = atributos.get(i);
                auxResumen = listaNombres.get(i);

                atributos.remove(i);
                listaNombres.remove(i);

                atributos.add(tempResumen);
                listaNombres.add(auxResumen);
                break;
            }
        }
        
        for(int i=0; i<atributos.size(); i++){
            System.out.println(atributos.get(i));
        }

    }

    public String getCadenaAutores() {
        return cadenaAutores;
    }

    public String getCadenaMaterias() {
        return cadenaMaterias;
    }

    public ArrayList<String> getAtributos() {
        return atributos;
    }

    public ArrayList<String> getListaNombres() {
        return listaNombres;
    }

    public String getNombreTabla() {
        return nombreTabla;
    }
    public static QueryControl getInstance() {
        return QueryControlHolder.INSTANCE;
    }
    
    private static class QueryControlHolder {
        private static final QueryControl INSTANCE = new QueryControl();
    }
    
}

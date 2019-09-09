/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package general.modelo;

import recursos.controllers.DiccionarioJpaController;
import recursos.controllers.EnciclopediaJpaController;
import recursos.controllers.LibroJpaController;
import recursos.controllers.MapaJpaController;
import recursos.controllers.PeriodicoJpaController;
import recursos.controllers.RevistaJpaController;
import recursos.entitys.Diccionario;
import recursos.entitys.Enciclopedia;
import recursos.entitys.Libro;
import recursos.entitys.Mapa;
import recursos.entitys.Periodico;
import recursos.entitys.Revista;

/**
 * Clase que retorna las consultas sobre un recurso.
 * @author Camilo
 */
public class QueryRecurso {
    
    public QueryRecurso(){
        
    }
    
    /**
     * Método que se encarga de consultar un libro por medio del código de barras.
     * @param codBarra
     * @return
     * @throws Exception 
     */
    public static Libro consultarLibro(String codBarra) throws Exception{      
        LibroJpaController control = new LibroJpaController();
        Libro libro = control.findLibro(codBarra);
        
        return libro;
    }
    
    /**
     * Método que se encarga de consultar una enciclopedia por medio del código de barras.
     * @param codBarra
     * @return
     * @throws Exception 
     */
    public static Enciclopedia consultarEnciclopedia(String codBarra) throws Exception{
        EnciclopediaJpaController control = new EnciclopediaJpaController();
        Enciclopedia enciclopedia = control.findEnciclopedia(codBarra);
        System.out.println("Enc " + enciclopedia);
        
        return enciclopedia;
    }

    /**
     * Método que se encarga de consultar un diccionario por medio del código de barras.
     * @param codBarra
     * @return
     * @throws Exception 
     */
    public static Diccionario consultarDiccionario(String codBarra) throws Exception{      
        DiccionarioJpaController control = new DiccionarioJpaController();
        Diccionario diccionario = control.findDiccionario(codBarra);
        
        return diccionario;
    }
    
    /**
     * Método que se encarga de consultar una revista por medio del código de barras.
     * @param codBarra
     * @return
     * @throws Exception 
     */
    public static Revista consultarRevista(String codBarra) throws Exception{      
        RevistaJpaController control = new RevistaJpaController();
        Revista revista = control.findRevista(codBarra);
        
        return revista;
    }
    
    /**
     * Método que se encarga de consultar un periodico por medio del código de barras.
     * @param codBarra
     * @return
     * @throws Exception 
     */
    public static Periodico consultarPeriodico(String codBarra) throws Exception{      
        PeriodicoJpaController control = new PeriodicoJpaController();
        Periodico periodico = control.findPeriodico(codBarra);
        
        return periodico;
    }
    
    /**
     * Método que se encarga de consultar una mapa por medio del código de barras.
     * @param codBarra
     * @return
     * @throws Exception 
     */
    public static Mapa consultarMapa(String codBarra) throws Exception{      
        MapaJpaController control = new MapaJpaController();
        Mapa mapa = control.findMapa(codBarra);
        
        return mapa;
    }
}
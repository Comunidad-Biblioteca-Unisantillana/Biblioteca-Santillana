package moduloPrestamo;

import controllers.DiccionarioJpaController;
import controllers.EnciclopediaJpaController;
import controllers.LibroJpaController;
import controllers.MapaJpaController;
import controllers.PeriodicoJpaController;
import controllers.RevistaJpaController;
import entitysRecursos.Diccionario;
import entitysRecursos.Enciclopedia;
import entitysUsuarios.Estudiante;
import entitysRecursos.Libro;
import entitysRecursos.Mapa;
import entitysRecursos.Periodico;
import entitysRecursos.PrestamoDiccionario;
import entitysRecursos.PrestamoEnciclopedia;
import entitysRecursos.PrestamoLibro;
import entitysRecursos.PrestamoMapa;
import entitysRecursos.PrestamoPeriodico;
import entitysRecursos.PrestamoRevista;
import entitysRecursos.Revista;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javafx.scene.control.TextField;
import modelo.QueryRecurso;
import modelo.ServicioFecha;
import modeloDAO.EstudianteDAO;
import moduloPrestamoDAO.PrestamoDiccionarioDAO;
import moduloPrestamoDAO.PrestamoEnciclopediaDAO;
import moduloPrestamoDAO.PrestamoLibroDAO;
import moduloPrestamoDAO.PrestamoMapaDAO;
import moduloPrestamoDAO.PrestamoPeriodicoDAO;
import moduloPrestamoDAO.PrestamoRevistaDAO;
import vista.AlertBox;
import vista.IAlertBox;

/**
 *
 * @author Camilo
 */
public class GeneradorPrestamoRecurso {
    
    public GeneradorPrestamoRecurso(){
        
    }
    
    /**
     * Método que se encarga de crear el préstamo de un recurso.
     * @param codBarras
     * @param codEstudiante
     * @param idBibliotecario
     * @param tipoPrestamo
     * @param txtFechaAct
     * @param txtFechaDev
     * @return
     * @throws Exception 
     */
    public boolean createPrestamo(String codBarras, String codEstudiante, String idBibliotecario, String tipoPrestamo, TextField txtFechaAct, 
                                TextField txtFechaDev) throws Exception{
        
        java.util.Date fechaActual = new java.util.Date();
        java.util.Date fechaDevolucion = null;
        boolean validarPrestamo = false;
        IAlertBox alert = new AlertBox();
        
        EstudianteDAO estDAO = new EstudianteDAO();
        Estudiante estudiante = estDAO.readDAO(codEstudiante);
                    
        if(estudiante != null){
            switch(tipoPrestamo.toLowerCase()){

                case "libro":
                    Libro libro = QueryRecurso.consultarLibro(codBarras);
                    if(libro != null){
                        if(libro.getDisponibilidad().equalsIgnoreCase("disponible")){    
                            int diasDev = libro.getCodcategoriacoleccion().getCodcategoriacoleccion().equalsIgnoreCase("colgen") ? 15 : 2;
                            fechaDevolucion = ServicioFecha.sumarDiasAFecha(fechaActual, diasDev);

                            PrestamoLibro presLib = new PrestamoLibro(codBarras, codEstudiante, idBibliotecario, 
                                new Date(fechaActual.getTime()), new Date(fechaDevolucion.getTime()));

                            PrestamoLibroDAO presLibDAO = new PrestamoLibroDAO();  
                            presLibDAO.createDAO(presLib);
                            validarPrestamo = true;    
                            
                            //---------------------------------------------------------------
                            System.out.println("Cambiando disponibilidad del libro...");
                            LibroJpaController control = new LibroJpaController(); //Se cambia el estado a "prestado"
                            libro.setDisponibilidad("prestado");                        
                            control.edit(libro);       
                        }
                        else{
                            alert.showAlert("Anuncio", "Libro", "El libro no se encuentra disponible");
                        }
                    }
                    else{
                        alert.showAlert("Anuncio", "Libro", "No existe un libro con ese código en la base de datos");
                    }
                    break;
                case "enciclopedia":
                    Enciclopedia enciclopedia = QueryRecurso.consultarEnciclopedia(codBarras);
                    if(enciclopedia != null){
                        if(enciclopedia.getDisponibilidad().equalsIgnoreCase("disponible")){ 
                            int diasDev = 0;
                            fechaDevolucion = ServicioFecha.sumarDiasAFecha(fechaActual, diasDev);
                            PrestamoEnciclopedia presEnc = new PrestamoEnciclopedia(codBarras, codEstudiante, idBibliotecario, 
                                new Date(fechaActual.getTime()), new Date(fechaDevolucion.getTime()));

                            PrestamoEnciclopediaDAO presEncDAO = new PrestamoEnciclopediaDAO();  
                            presEncDAO.createDAO(presEnc);
                            validarPrestamo = true;    
                            
                            //---------------------------------------------------------------
                            System.out.println("Cambiando disponibilidad de la enciclopedia...");
                            EnciclopediaJpaController control = new EnciclopediaJpaController(); //Se cambia el estado a "prestado"
                            enciclopedia.setDisponibilidad("prestado"); 
                            control.edit(enciclopedia); 
                        }
                        else{
                            alert.showAlert("Anuncio", "Enciclopedia", "La enciclopedia no se encuentra disponible");
                        }
                    }
                    else{
                        alert.showAlert("Anuncio", "Enciclopedia", "No existe una enciclopedia con ese código en la base de datos");
                    }
                    break;
                case "diccionario":
                    Diccionario diccionario = QueryRecurso.consultarDiccionario(codBarras);
                    if(diccionario != null){
                        if(diccionario.getDisponibilidad().equalsIgnoreCase("disponible")){ 
                            int diasDev = 0;
                            fechaDevolucion = ServicioFecha.sumarDiasAFecha(fechaActual, diasDev);
                            PrestamoDiccionario presDic = new PrestamoDiccionario(codBarras, codEstudiante, idBibliotecario, 
                                new Date(fechaActual.getTime()), new Date(fechaDevolucion.getTime()));

                            PrestamoDiccionarioDAO presDicDAO = new PrestamoDiccionarioDAO();  
                            presDicDAO.createDAO(presDic);
                            validarPrestamo = true;
                            
                            //---------------------------------------------------------------
                            System.out.println("Cambiando disponibilidad del diccionario...");
                            DiccionarioJpaController control = new DiccionarioJpaController(); //Se cambia el estado a "prestado"
                            diccionario.setDisponibilidad("prestado"); 
                            control.edit(diccionario); 
                        }
                        else{
                            alert.showAlert("Anuncio", "Diccionario", "El diccionario no se encuentra disponible");
                        }
                    }
                    else{
                        alert.showAlert("Anuncio", "Diccionario", "No existe un diccionario con ese código en la base de datos");
                    }
                    break;
                case "revista":
                    Revista revista = QueryRecurso.consultarRevista(codBarras);
                    if(revista != null){
                        if(revista.getDisponibilidad().equalsIgnoreCase("disponible")){
                            int diasDev = 0;
                            fechaDevolucion = ServicioFecha.sumarDiasAFecha(fechaActual, diasDev);
                            PrestamoRevista presRev = new PrestamoRevista(codBarras, codEstudiante, idBibliotecario, 
                                new Date(fechaActual.getTime()), new Date(fechaDevolucion.getTime()));

                            PrestamoRevistaDAO presRevDAO = new PrestamoRevistaDAO();  
                            presRevDAO.createDAO(presRev);
                            validarPrestamo = true;   
                            
                            //---------------------------------------------------------------
                            System.out.println("Cambiando disponibilidad de la revista...");
                            RevistaJpaController control = new RevistaJpaController(); //Se cambia el estado a "prestado"
                            revista.setDisponibilidad("prestado");
                            control.edit(revista); 
                        }
                        else{
                            alert.showAlert("Anuncio", "Revista", "La revista no se encuentra disponible");
                        }
                    }
                    else{
                        alert.showAlert("Anuncio", "Revista", "No existe una revista con ese código en la base de datos");
                    }
                    break;
                case "periodico":
                    Periodico periodico = QueryRecurso.consultarPeriodico(codBarras);
                    if(periodico != null){
                        if(periodico.getDisponibilidad().equalsIgnoreCase("disponible")){
                            int diasDev = 0;
                            fechaDevolucion = ServicioFecha.sumarDiasAFecha(fechaActual, diasDev);
                            PrestamoPeriodico presPer = new PrestamoPeriodico(codBarras, codEstudiante, idBibliotecario, 
                                new Date(fechaActual.getTime()), new Date(fechaDevolucion.getTime()));

                            PrestamoPeriodicoDAO presPerDAO = new PrestamoPeriodicoDAO();  
                            presPerDAO.createDAO(presPer);
                            validarPrestamo = true; 
                            
                            //---------------------------------------------------------------
                            System.out.println("Cambiando disponibilidad del periodico...");
                            PeriodicoJpaController control = new PeriodicoJpaController(); //Se cambia el estado a "prestado"
                            periodico.setDisponibilidad("prestado"); 
                            control.edit(periodico); 
                        }
                        else{
                            alert.showAlert("Anuncio", "Periodico", "El periodico no se encuentra disponible");
                        }
                    }
                    else{
                        alert.showAlert("Anuncio", "Periodico", "No existe un periodico con ese código en la base de datos");
                    }
                    break;
                 case "mapa":
                    Mapa mapa = QueryRecurso.consultarMapa(codBarras);
                    if(mapa != null){
                        if(mapa.getDisponibilidad().equalsIgnoreCase("disponible")){
                            int diasDev = 0;
                            fechaDevolucion = ServicioFecha.sumarDiasAFecha(fechaActual, diasDev);
                            PrestamoMapa presMap = new PrestamoMapa(codBarras, codEstudiante, idBibliotecario, 
                                new Date(fechaActual.getTime()), new Date(fechaDevolucion.getTime()));

                            PrestamoMapaDAO presMapDAO = new PrestamoMapaDAO();  
                            presMapDAO.createDAO(presMap);
                            validarPrestamo = true;  
                            
                            //---------------------------------------------------------------
                            System.out.println("Cambiando disponibilidad del mapa...");
                            MapaJpaController control = new MapaJpaController(); //Se cambia el estado a "prestado"
                            mapa.setDisponibilidad("prestado"); 
                            control.edit(mapa); 
                        }
                        else{
                            alert.showAlert("Anuncio", "Mapa", "El mapa no se encuentra disponible");
                        }
                    }
                    else{
                        alert.showAlert("Anuncio", "Mapa", "No existe un mapa con ese código en la base de datos");
                    }
                    break;
            }
            if(validarPrestamo){
                DateFormat formatoFecha = new SimpleDateFormat("YYYY-MM-dd");
                txtFechaAct.setText(formatoFecha.format(fechaActual));
                txtFechaDev.setText(formatoFecha.format(fechaDevolucion));
            }
        }
        else{
            alert.showAlert("Anuncio", "Estudiante", "No existe un estudiante con ese código en la base de datos");
        }
        
        return validarPrestamo;
    }
    
    
}

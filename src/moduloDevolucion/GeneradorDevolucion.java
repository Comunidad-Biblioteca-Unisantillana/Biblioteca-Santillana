
package moduloDevolucion;

import controllers.DiccionarioJpaController;
import controllers.EnciclopediaJpaController;
import controllers.LibroJpaController;
import controllers.MapaJpaController;
import controllers.PeriodicoJpaController;
import controllers.RevistaJpaController;
import entitysRecursos.DevolucionDiccionario;
import entitysRecursos.DevolucionEnciclopedia;
import entitysRecursos.DevolucionLibro;
import entitysRecursos.DevolucionMapa;
import entitysRecursos.DevolucionPeriodico;
import entitysRecursos.DevolucionRevista;
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
import modelo.QueryRecurso;
import moduloDevolucionDAO.DevolucionDiccionarioDAO;
import moduloDevolucionDAO.DevolucionEnciclopediaDAO;
import moduloDevolucionDAO.DevolucionLibroDAO;
import moduloDevolucionDAO.DevolucionMapaDAO;
import moduloDevolucionDAO.DevolucionPeriodicoDAO;
import moduloDevolucionDAO.DevolucionRevistaDAO;
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
public class GeneradorDevolucion {
    
    public GeneradorDevolucion(){
        
    }
    
    public boolean createDevolucion(String codBarras, String idBibliotecario, String tipoRecurso, String estadoRecurso) throws Exception{
        
        java.util.Date fechaActual = new java.util.Date();
        boolean validarDevolucion = false;
        IAlertBox alert = new AlertBox();
        
        switch(tipoRecurso.toLowerCase()){
            case "libro":
                Libro libro = QueryRecurso.consultarLibro(codBarras);
                if(libro != null){
                    PrestamoLibroDAO presLibDAO = new PrestamoLibroDAO();
                    PrestamoLibro presLib = presLibDAO.readDAO(codBarras);
                    if(presLib != null){
                        DevolucionLibroDAO devLibDAO = new DevolucionLibroDAO();                       
                        DevolucionLibro devLib = new DevolucionLibro(presLib.getCodPrestamoLibro(), idBibliotecario, new Date(fechaActual.getTime()), 
                                                                    estadoRecurso.toLowerCase());
                 
                        devLibDAO.createDAO(devLib);
                        libro.setDisponibilidad("disponible");
                        LibroJpaController ctrlLib = new LibroJpaController();
                        ctrlLib.edit(libro);
                        validarDevolucion = true;
                        System.out.println("------------------------- \n Borrando prestamo");
                        presLibDAO.deleteDAO(presLib.getCodPrestamoLibro());
                    }
                    else
                        alert.showAlert("Aviso", "Devolución", "No existe un préstamo de libro con ese código");
                }
                else
                    alert.showAlert("Aviso", "Devolución", "No existe un libro con ese código");
                break;
            case "enciclopedia":
                Enciclopedia enciclopedia = QueryRecurso.consultarEnciclopedia(codBarras);
                if(enciclopedia != null){
                    PrestamoEnciclopediaDAO presEncDAO = new PrestamoEnciclopediaDAO();
                    PrestamoEnciclopedia presEnc = presEncDAO.readDAO(codBarras);
                    if(presEnc != null){
                        DevolucionEnciclopediaDAO devEncDAO = new DevolucionEnciclopediaDAO();                       
                        DevolucionEnciclopedia devEnc = new DevolucionEnciclopedia(presEnc.getCodPrestamoEnciclopedia(), idBibliotecario, 
                                                              new Date(fechaActual.getTime()) , estadoRecurso.toLowerCase());
                        
                        System.out.println("------------------------- \n Cambiando estado enciclopedia...");
                        devEncDAO.createDAO(devEnc);
                        enciclopedia.setDisponibilidad("disponible");
                        EnciclopediaJpaController ctrlEnc = new EnciclopediaJpaController();
                        ctrlEnc.edit(enciclopedia);
                        validarDevolucion = true;
                        System.out.println("------------------------- \n Borrando prestamo");
                        System.out.println("El cod es " + presEnc.getCodPrestamoEnciclopedia());
                        presEncDAO.deleteDAO(presEnc.getCodPrestamoEnciclopedia());
                    }
                    else
                        alert.showAlert("Aviso", "Devolución", "No existe un préstamo de enciclopedia con ese código");
                }
                else
                    alert.showAlert("Aviso", "Devolución", "No existe una enciclopedia con ese código");
                break;
            case "diccionario":
                Diccionario diccionario = QueryRecurso.consultarDiccionario(codBarras);
               
                if(diccionario != null){
                    System.out.println("Entro aqui");
                    PrestamoDiccionarioDAO presDicDAO = new PrestamoDiccionarioDAO();
                    PrestamoDiccionario presDic = presDicDAO.readDAO(codBarras);
                    if(presDic != null){
                        DevolucionDiccionarioDAO devDicDAO = new DevolucionDiccionarioDAO();                       
                        DevolucionDiccionario devDic = new DevolucionDiccionario(presDic.getCodPrestamoDiccionario(), idBibliotecario, 
                                                            new Date(fechaActual.getTime()), estadoRecurso.toLowerCase());
                        
                        System.out.println("------------------------- \n Cambiando estado diccionario...");
                        devDicDAO.createDAO(devDic);
                        diccionario.setDisponibilidad("disponible");
                        DiccionarioJpaController ctrlDic = new DiccionarioJpaController();
                        ctrlDic.edit(diccionario);
                        validarDevolucion = true;
                        System.out.println("------------------------- \n Borrando prestamo");
                        presDicDAO.deleteDAO(presDic.getCodPrestamoDiccionario());
                    }
                    else
                        alert.showAlert("Aviso", "Devolución", "No existe un préstamo de diccionario con ese código");
                }
                else
                    alert.showAlert("Aviso", "Devolución", "No existe un diccionario con ese código");
                break;
            case "revista":
                Revista revista = QueryRecurso.consultarRevista(codBarras);
                if(revista != null){
                    PrestamoRevistaDAO presRevDAO = new PrestamoRevistaDAO();
                    PrestamoRevista presRev = presRevDAO.readDAO(codBarras);
                    if(presRev != null){
                        DevolucionRevistaDAO devRevDAO = new DevolucionRevistaDAO();                       
                        DevolucionRevista devRev = new DevolucionRevista(presRev.getCodPrestamoRevista(), idBibliotecario, new Date(fechaActual.getTime()), 
                                                        estadoRecurso.toLowerCase());
                        
                        System.out.println("------------------------- \n Cambiando estado revista...");
                        devRevDAO.createDAO(devRev);
                        revista.setDisponibilidad("disponible");
                        RevistaJpaController ctrlRev = new RevistaJpaController();
                        ctrlRev.edit(revista);
                        validarDevolucion = true;
                        System.out.println("------------------------- \n Borrando prestamo");
                        presRevDAO.deleteDAO(presRev.getCodPrestamoRevista());
                    }
                    else
                        alert.showAlert("Aviso", "Devolución", "No existe un préstamo de revista con ese código");
                }
                else
                    alert.showAlert("Aviso", "Devolución", "No existe una revista con ese código");
                break;
            case "periodico":
                Periodico periodico = QueryRecurso.consultarPeriodico(codBarras);
                if(periodico != null){
                    PrestamoPeriodicoDAO presPerDAO = new PrestamoPeriodicoDAO();
                    PrestamoPeriodico presPer = presPerDAO.readDAO(codBarras);
                    if(presPer != null){
                        DevolucionPeriodicoDAO devPerDAO = new DevolucionPeriodicoDAO();                       
                        DevolucionPeriodico devPer = new DevolucionPeriodico(presPer.getCodPrestamoPeriodico(), idBibliotecario,
                                                        new Date(fechaActual.getTime()), estadoRecurso.toLowerCase());
                        
                        System.out.println("------------------------- \n Cambiando estado periodico...");
                        devPerDAO.createDAO(devPer);
                        periodico.setDisponibilidad("disponible");
                        PeriodicoJpaController ctrlPer = new PeriodicoJpaController();
                        ctrlPer.edit(periodico);
                        validarDevolucion = true;
                        System.out.println("------------------------- \n Borrando prestamo");
                        presPerDAO.deleteDAO(presPer.getCodPrestamoPeriodico());
                    }
                    else
                        alert.showAlert("Aviso", "Devolución", "No existe un préstamo de periodico con ese código");
                }
                else
                    alert.showAlert("Aviso", "Devolución", "No existe un periodico con ese código");
                break;
            case "mapa":
                Mapa mapa = QueryRecurso.consultarMapa(codBarras);
                if(mapa != null){
                    PrestamoMapaDAO presMapDAO = new PrestamoMapaDAO();
                    PrestamoMapa presMap = presMapDAO.readDAO(codBarras);
                    if(presMap != null){
                        DevolucionMapaDAO devMapDAO = new DevolucionMapaDAO();                       
                        DevolucionMapa devMap = new DevolucionMapa(presMap.getCodPrestamoMapa(), idBibliotecario, 
                                                    new Date(fechaActual.getTime()), estadoRecurso.toLowerCase());
                        
                        System.out.println("------------------------- \n Cambiando estado mapa...");
                        devMapDAO.createDAO(devMap);
                        mapa.setDisponibilidad("disponible");
                        MapaJpaController ctrlMap = new MapaJpaController();
                        ctrlMap.edit(mapa);
                        validarDevolucion = true;
                        System.out.println("------------------------- \n Borrando prestamo");
                        presMapDAO.deleteDAO(presMap.getCodPrestamoMapa());
                    }
                    else
                        alert.showAlert("Aviso", "Devolución", "No existe un préstamo de mapa con ese código");
                }
                else
                    alert.showAlert("Aviso", "Devolución", "No existe un mapa con ese código");
                break;
            default : break; 
        }
        return validarDevolucion;
    }
}


package moduloReserva;

import recursos1.controllers.LibroJpaController;
import entitysUsuario.Estudiante;
import recursos1.entitys.Libro;
import entitys.PrestamoLibro;
import entitys.ReservaLibro;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javafx.scene.control.TextField;
import modelo.QueryRecurso;
import modelo.ServicioFecha;
import modeloDAO.EstudianteDAO;
import moduloPrestamoDAO_Antiguo.PrestamoLibroDAO;
import moduloReservaDAO.ReservaLibroDAO;
import vista.AlertBox;
import vista.IAlertBox;

/**
 * Clase que se encarga de generar la reserva de un libro de colección general.
 * @author Camilo
 */
public class GeneradorReserva {
    
    public GeneradorReserva(){
        
    }
    
    /**
     * Método que se encarga de crear una reserva de la colección general.
     * @param codBarras
     * @param codEstudiante
     * @param idBibliotecario
     * @param txtFechaAct
     * @param txtFechaLim
     * @return
     * @throws Exception 
     */
    public boolean createReserva(String codBarras, String codEstudiante, String idBibliotecario, TextField txtFechaAct, 
                                TextField txtFechaLim) throws Exception{
        
        java.util.Date fechaActual = new java.util.Date();
        java.util.Date fechaLimite = null;
        boolean validarReserva = false;
        IAlertBox alert = new AlertBox();
        
        EstudianteDAO estDAO = new EstudianteDAO();
        Estudiante estudiante = estDAO.readDAO(codEstudiante);
        
        
        if(estudiante != null){
            Libro libro = QueryRecurso.consultarLibro(codBarras);
            if(libro != null){
                if(libro.getDisponibilidad().equalsIgnoreCase("prestado")){  
                    if(libro.getCodcategoriacoleccion().getCodcategoriacoleccion().equalsIgnoreCase("colgen")){
                        
                        PrestamoLibroDAO presDAO = new PrestamoLibroDAO();
                        PrestamoLibro prestamo = presDAO.readDAO(libro.getCodbarralibro());
                        
                        if(prestamo != null){
                            int diasPlazo = 5;
                            fechaLimite = ServicioFecha.sumarDiasAFecha(prestamo.getFechaDevolucion(), diasPlazo);

                            ReservaLibro reserva = new ReservaLibro(codBarras, codEstudiante, idBibliotecario, 
                                new Date(fechaActual.getTime()), new Date(fechaLimite.getTime()));

                            ReservaLibroDAO resDAO = new ReservaLibroDAO();  
                            resDAO.createDAO(reserva);
                            validarReserva = true;    

                            //---------------------------------------------------------------
                            System.out.println("Cambiando disponibilidad del libro...");
                            LibroJpaController control = new LibroJpaController(); //Se cambia el estado a "prestado"
                            libro.setDisponibilidad("reservado");                        
                            control.edit(libro); 
                        }
                        else{
                            System.out.println("El prestamo es null");
                        }
                    }
                    else{
                         alert.showAlert("Anuncio", "Reserva", "El libro no se puede reservar, no es de colección general");
                    }
                }
                else{             
                    alert.showAlert("Anuncio", "Reserva", "El libro no se puede reservar, no se encuentra prestado");
                }
            }
            else{
                alert.showAlert("Anuncio", "Libro", "No existe un libro con ese código en la base de datos");
            }
            if(validarReserva){
                DateFormat formatoFecha = new SimpleDateFormat("YYYY-MM-dd");
                txtFechaAct.setText(formatoFecha.format(fechaActual));
                txtFechaLim.setText(formatoFecha.format(fechaLimite));
            }
        }
        else{
            alert.showAlert("Anuncio", "Estudiante", "No existe un estudiante con ese código en la base de datos");
        }
        return validarReserva;
    }
}

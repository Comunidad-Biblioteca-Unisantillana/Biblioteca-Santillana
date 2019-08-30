
package moduloReserva.modelo;

import usuarios.control.EstudianteJpaController;
import usuarios.control.ProfesorJpaController;
import general.vista.AlertBox;
import general.vista.IAlertBox;

/**
 * Clase que se encarga de generar una reserva
 * @author Julian
 */
public class GeneradorReserva {

    public GeneradorReserva() {
    }
    
    public void createReserva(String codBarras,String codUsuario,String tipoUsuario,String idBibliotecario){
        IAlertBox alert = new AlertBox();
        if(tipoUsuario.equalsIgnoreCase("estudiante")){
            EstudianteJpaController estudiante =  new EstudianteJpaController();
            if(estudiante.findEstudiante(codUsuario) != null){
                if(generarReserva(codBarras, codUsuario, tipoUsuario, idBibliotecario)){
                    alert.showAlert("Anuncio", "Reserva estudiante", "La reserva se realizo con exito");
                }
            } else{
                alert.showAlert("Anuncio", "Reserva estudiante", "No existe un estudiante con ese código");
            }
        }else{
            ProfesorJpaController profesor = new ProfesorJpaController();
            if(profesor.findProfesor(codUsuario) != null){
                if(generarReserva(codBarras, codUsuario, tipoUsuario, idBibliotecario)){
                    alert.showAlert("Anuncio", "Reserva profesor", "La reserva se realizo con exito");
                }
            }else{
                alert.showAlert("Anuncio", "Reserva profesor", "No existe un profesor con esa identificación");
            }
        }
    }
    
    private boolean generarReserva(String codBarras,String codUsuario,String tipoUsuario,String idBibliotecario){
        FabricaReserva fabrica = new FabricaReserva();
        IReserva reserva = fabrica.getReserva(tipoUsuario);
        if(reserva.ejecutarReserva(codBarras, codUsuario, idBibliotecario)){
            return true;
        }
        return false;
    }
    
}

package control;

import entitysUsuario.Bibliotecario;
import entitysUsuario.BibliotecarioJpaController;
import entitysUsuario.Estudiante;
import entitysUsuario.EstudianteJpaController;
import entitysUsuario.Profesor;
import entitysUsuario.ProfesorJpaController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

/**
 * Clase que controla la vista DatosBasicosUsuario.fxml
 * @author Julian
 * Fecha de Creación: 26/07/2019
 * Fecha de ultima Modificación: 04/08/2019
 */
public class DatosBasicosUsuarioController {
    
    @FXML
    private Label lblCodId;
    @FXML
    private Label lblNombre;
    
    /**
     * Método que carga el nombre y código del usuario
     * @param nombreEntidad
     * @param codigo 
     */
    public void cargarComponentes(String nombreEntidad,String codigo){
        if(nombreEntidad.equalsIgnoreCase("estudiante")){
            EstudianteJpaController estJpaController = new EstudianteJpaController();
            Estudiante est = estJpaController.findEstudiante(codigo);
            cargarTextoLabels(est.getNombre() + " " + est.getApellido() ,"Código: " + codigo);
        }else if (nombreEntidad.equalsIgnoreCase("bibliotecario")){
            BibliotecarioJpaController bibJpaController = new BibliotecarioJpaController();
            Bibliotecario bib = bibJpaController.findBibliotecario(codigo);
            cargarTextoLabels(bib.getNombres()+ " " + bib.getApellidos(),"Identificación: " + codigo);
        }else if (nombreEntidad.equalsIgnoreCase("profesor")){
            ProfesorJpaController profJpaController= new ProfesorJpaController();
            Profesor prof = profJpaController.findProfesor(codigo);
            cargarTextoLabels(prof.getNombres() + " " + prof.getApellidos(), "Identificación: " + codigo);
        }
    }
    
    /**
     * Método que muestra el nombre y el código del usuario en pantalla
     * @param nombreCompleto
     * @param codigo 
     */
    private void cargarTextoLabels(String nombreCompleto,String codigo){
        lblNombre.setText("Nombre: " + nombreCompleto);
        lblCodId.setText(codigo);
    }
}

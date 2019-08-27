package control;


import usuario.controllers.BibliotecarioJpaController;
import usuario.controllers.EstudianteJpaController;
import usuario.controllers.ProfesorJpaController;
import usuario.entitys.Bibliotecario;
import usuario.entitys.Estudiante;
import usuario.entitys.Profesor;
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
            EstudianteJpaController estJPA =  new EstudianteJpaController();
            Estudiante est = estJPA.findEstudiante(codigo);
            cargarTextoLabels(est.getNombre() + " " + est.getApellido() ,"Código: " + codigo);
        }else if (nombreEntidad.equalsIgnoreCase("bibliotecario")){
            BibliotecarioJpaController bibJPA =  new BibliotecarioJpaController();
            Bibliotecario bib = bibJPA.findBibliotecario(codigo);
            cargarTextoLabels(bib.getNombres() + " " + bib.getApellidos() ,"Identificación: " + codigo);
        }else if (nombreEntidad.equalsIgnoreCase("profesor")){
            ProfesorJpaController profJPA = new ProfesorJpaController();
            Profesor prof = profJPA.findProfesor(codigo);
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

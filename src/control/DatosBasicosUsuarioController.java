package control;

import entitysUsuario.Bibliotecario;
import entitysUsuario.Estudiante;
import entitysUsuario.Profesor;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import modeloDAO.BibliotecarioDAO;
import modeloDAO.EstudianteDAO;
import modeloDAO.ProfesorDAO;

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
            EstudianteDAO estDAO = new EstudianteDAO();
            Estudiante est = estDAO.readDAO(codigo);
            cargarTextoLabels(est.getNombre() + " " + est.getApellido() ,"Codigo: " + codigo);
        }else if (nombreEntidad.equalsIgnoreCase("bibliotecario")){
            BibliotecarioDAO bibDAO = new BibliotecarioDAO();
            Bibliotecario bib = bibDAO.readDAO(codigo);
            cargarTextoLabels(bib.getNombre() + " " + bib.getApellido() ,"Identificación: " + codigo);
        }else if (nombreEntidad.equalsIgnoreCase("profesor")){
            ProfesorDAO profDAO = new ProfesorDAO();
            Profesor prof = profDAO.readDAO(codigo);
            cargarTextoLabels(prof.getNombres() + " " + prof.getApellidos(), "Identificación" + codigo);
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

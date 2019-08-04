/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import entitysRecursos.Bibliotecario;
import entitysRecursos.Estudiante;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import modeloDAO.BibliotecarioDAO;
import modeloDAO.EstudianteDAO;

/**
 *
 * @author Julian
 */
public class DatosBasicosUsuarioController {
    
    @FXML
    private Label lblCodId;
    @FXML
    private Label lblNombre;
    
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
            
        }
    }
    
    private void cargarTextoLabels(String nombreCompleto,String codigo){
        lblNombre.setText("Nombre: " + nombreCompleto);
        lblCodId.setText(codigo);
    }
}

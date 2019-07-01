package control;

import entitys.Periodico;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import modelo.QueryRecurso;

/**
 * FXML Controller class
 *
 * @author stive
 */
public class FichaTecnicaPeriodicoController {

    @FXML
    private TextField txtISSN;
    @FXML
    private TextField txtFechaPublicacion;
    @FXML
    private TextField txtCiudad;
    @FXML
    private TextField txtPaginas;
    @FXML
    private TextField txtEditorial;
    @FXML
    private TextField txtDisponibilidad;
    @FXML
    private TextField txtEstadoFisico;
    @FXML
    private TextField txtNombrePeriodico;
    @FXML
    private TextField txtCategoria;
    
    /**
     * Método que carga los datos en la ficha tecnica.
     * @param codBarra
     * @throws Exception 
     */
    public void cargarDatos(String codBarra) throws Exception{      
        Periodico periodico = QueryRecurso.consultarPeriodico(codBarra);
        DateFormat formatoFecha = new SimpleDateFormat("YYYY-MM-dd");
        
        txtISSN.setText(periodico.getIssn());
        txtNombrePeriodico.setText(periodico.getNombreperiodico());
        txtCiudad.setText(periodico.getCiudad());
        txtFechaPublicacion.setText(formatoFecha.format(periodico.getFechapublicacion()));
        txtPaginas.setText(String.valueOf(periodico.getPaginas()));
        txtEditorial.setText(periodico.getEditorial());
        txtDisponibilidad.setText(periodico.getDisponibilidad());
        txtEstadoFisico.setText(periodico.getEstadofisico());
        txtCategoria.setText(periodico.getCodcategoriacoleccion().getNombrecol()); 
    }  
    
        public void limpiarCampos(){
        txtISSN.setText("");
        txtNombrePeriodico.setText("");
        txtCiudad.setText("");
        txtFechaPublicacion.setText("");
        txtPaginas.setText("");
        txtEditorial.setText("");
        txtDisponibilidad.setText("");
        txtEstadoFisico.setText("");
        txtCategoria.setText(""); 
    }
}

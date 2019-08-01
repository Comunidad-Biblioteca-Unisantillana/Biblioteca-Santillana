package control;

import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import entitysRecursos.Revista;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javafx.fxml.FXML;
import modelo.QueryRecurso;

/**
 * FXML Controller class
 *
 * @author stive
 */
public class FichaTecnicaRevistaController{

    @FXML
    private JFXTextField txtISSN;
    @FXML
    private JFXTextField txtFechaPublicacion;
    @FXML
    private JFXTextField txtPublicador;
    @FXML
    private JFXTextField txtPaisPublicacion;
    @FXML
    private JFXTextField txtDisponibilidad;
    @FXML
    private JFXTextField txtEstadoFisico;
    @FXML
    private JFXTextField txtCategoria;
    @FXML
    private JFXTextField txtIdioma;
    @FXML
    private JFXTextField txtTitulo;
    @FXML
    private JFXTextArea txAreaMateria;
    
    /**
     * Método que carga los datos en la ficha tecnica.
     * @param codBarra
     * @throws Exception 
     */
    public void cargarDatos(String codBarra) throws Exception{      
        Revista revista = QueryRecurso.consultarRevista(codBarra);
        DateFormat formatoFecha = new SimpleDateFormat("YYYY-MM-dd");
        
        txtISSN.setText(revista.getIssn());
        txtTitulo.setText(revista.getTitulo());
        txtIdioma.setText(revista.getIdioma());
        txtPaisPublicacion.setText(revista.getPaispublicacion());
        txtFechaPublicacion.setText(formatoFecha.format(revista.getFechapublicacion()));
        txtPublicador.setText(revista.getPublicador());
        txtDisponibilidad.setText(revista.getDisponibilidad());
        txtEstadoFisico.setText(revista.getEstadofisico());
        txtCategoria.setText(revista.getCodcategoriacoleccion().getNombrecol()); 
        
        txAreaMateria.setText(QueryControl.getInstance().getCadenaMaterias());
        txAreaMateria.setWrapText(true);
    }  
        public void limpiarCampos() {
        txtISSN.setText("");
        txtTitulo.setText("");
        txtIdioma.setText("");
        txtPaisPublicacion.setText("");
        txtFechaPublicacion.setText("");
        txtPublicador.setText("");
        txtDisponibilidad.setText("");
        txtEstadoFisico.setText("");
        txtCategoria.setText("");
        txAreaMateria.setText("");
    }
}

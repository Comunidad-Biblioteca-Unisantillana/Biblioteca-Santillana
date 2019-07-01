package control;

import entitys.Revista;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import modelo.QueryRecurso;

/**
 * FXML Controller class
 *
 * @author stive
 */
public class FichaTecnicaRevistaController{

    @FXML
    private TextField txtISSN;
    @FXML
    private TextField txtFechaPublicacion;
    @FXML
    private TextField txtPublicador;
    @FXML
    private TextField txtPaisPublicacion;
    @FXML
    private TextField txtDisponibilidad;
    @FXML
    private TextField txtEstadoFisico;
    @FXML
    private TextField txtCategoria;
    @FXML
    private TextField txtIdioma;
    @FXML
    private TextField txtTitulo;
    @FXML
    private TextArea txAreaMateria;
    
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

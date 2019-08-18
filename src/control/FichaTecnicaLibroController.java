package control;

import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import entitysRecursos.Libro;
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
public class FichaTecnicaLibroController {

    @FXML
    private JFXTextField txtFechaPublicacion;
    @FXML
    private JFXTextField txtISBN;
    @FXML
    private JFXTextField txtIdioma;
    @FXML
    private JFXTextField txtPaisPublicacion;
    @FXML
    private JFXTextField txtEditorial;
    @FXML
    private JFXTextField txtEdicion;
    @FXML
    private JFXTextField txtPaginas;
    @FXML
    private JFXTextField txtCubierta;
    @FXML
    private JFXTextField txtDimensiones;
    @FXML
    private JFXTextField txtSignatura;
    @FXML
    private JFXTextField txtDisponibilidad;
    @FXML
    private JFXTextField txtEstado;
    @FXML
    private JFXTextField txtArea;
    @FXML
    private JFXTextField txtCategoria;
    @FXML
    private JFXTextArea txAreaNota;
    @FXML
    private JFXTextArea txAreaResumen;
    @FXML
    private JFXTextField txtTitulo;
    @FXML
    private JFXTextArea txAreaAutores;
    @FXML
    private JFXTextArea txAreaMateria;

    /**
     * Método que carga los datos en la ficha tecnica.
     * @param codBarra
     * @throws Exception 
     */
    public void cargarDatos(String codBarra) throws Exception{      
        Libro libro = QueryRecurso.consultarLibro(codBarra);
        DateFormat formatoFecha = new SimpleDateFormat("YYYY-MM-dd");
        
        txtISBN.setText(libro.getIsbn());
        txtTitulo.setText(libro.getTitulo());
        txtIdioma.setText(libro.getIdioma());
        txtPaisPublicacion.setText(libro.getPaispublicacion());
        txtFechaPublicacion.setText(formatoFecha.format(libro.getFechapublicacion()));
        txtEditorial.setText(libro.getEditorial());
        txtEdicion.setText(String.valueOf(libro.getNumedicion()));
        txtPaginas.setText(String.valueOf(libro.getNumpaginas()));
        txtCubierta.setText(libro.getCubierta());
        txtDimensiones.setText(libro.getDimensiones());
        txAreaNota.setText(libro.getNota());
        txAreaResumen.setText(libro.getResumen());
        txtSignatura.setText(libro.getSignatura());
        txtDisponibilidad.setText(libro.getDisponibilidad());
        txtEstado.setText(libro.getEstadofisico());
        txtArea.setText(libro.getArea());
        txtCategoria.setText(libro.getCodcategoriacoleccion().getNombrecol());  
        
        txAreaMateria.setText(QueryControl.getInstance().getCadenaMaterias());
        txAreaAutores.setText(QueryControl.getInstance().getCadenaAutores());
        
        txAreaNota.setWrapText(true);
        txAreaResumen.setWrapText(true);
        txAreaMateria.setWrapText(true);
        txAreaAutores.setWrapText(true); 
    }   
    
    
    public void limpiarCampos(){
        txtISBN.setText("");
        txtTitulo.setText("");
        txtIdioma.setText("");
        txtPaisPublicacion.setText("");
        txtFechaPublicacion.setText("");
        txtEditorial.setText("");
        txtEdicion.setText("");
        txtPaginas.setText("");
        txtCubierta.setText("");
        txtDimensiones.setText("");
        txAreaNota.setText("");
        txAreaResumen.setText("");
        txtSignatura.setText("");
        txtDisponibilidad.setText("");
        txtEstado.setText("");
        txtArea.setText("");
        txtCategoria.setText("");  
        txAreaAutores.setText("");
        txAreaMateria.setText("");
    }
}

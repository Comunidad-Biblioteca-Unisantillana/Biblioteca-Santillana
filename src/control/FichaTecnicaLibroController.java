package control;

import entitys.Libro;
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
    private TextField txtFechaPublicacion;
    @FXML
    private TextField txtISBN;
    @FXML
    private TextField txtIdioma;
    @FXML
    private TextField txtPaisPublicacion;
    @FXML
    private TextField txtEditorial;
    @FXML
    private TextField txtEdicion;
    @FXML
    private TextField txtPaginas;
    @FXML
    private TextField txtCubierta;
    @FXML
    private TextField txtDimensiones;
    @FXML
    private TextField txtSignatura;
    @FXML
    private TextField txtDisponibilidad;
    @FXML
    private TextField txtEstado;
    @FXML
    private TextField txtArea;
    @FXML
    private TextField txtCategoria;
    @FXML
    private TextArea txAreaNota;
    @FXML
    private TextArea txAreaResumen;
    @FXML
    private TextField txtTitulo;
    @FXML
    private TextArea txAreaAutores;
    @FXML
    private TextArea txAreaMateria;

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

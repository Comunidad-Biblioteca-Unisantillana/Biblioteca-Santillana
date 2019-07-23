package control;


import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import entitys.Enciclopedia;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javafx.fxml.FXML;
import modelo.QueryRecurso;

/**
 * FXML Controller class
 *
 * @author stive
 */
public class FichaTecnicaEnciclopediaController {


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
        Enciclopedia enciclopedia = QueryRecurso.consultarEnciclopedia(codBarra);
        DateFormat formatoFecha = new SimpleDateFormat("YYYY-MM-dd");
           
        txtISBN.setText(enciclopedia.getIsbn());
        txtTitulo.setText(enciclopedia.getTitulo());
        txtIdioma.setText(enciclopedia.getIdioma());
        txtPaisPublicacion.setText(enciclopedia.getPaispublicacion());
        txtFechaPublicacion.setText(formatoFecha.format(enciclopedia.getFechapublicacion()));
        txtEditorial.setText(enciclopedia.getEditorial());
        txtEdicion.setText(String.valueOf(enciclopedia.getNumedicion()));
        txtPaginas.setText(String.valueOf(enciclopedia.getNumpaginas()));
        txtCubierta.setText(enciclopedia.getCubierta());
        txtDimensiones.setText(enciclopedia.getDimensiones());
        txAreaNota.setText(enciclopedia.getNota());
        txAreaResumen.setText(enciclopedia.getResumen());
        txtSignatura.setText(enciclopedia.getSignatura());
        txtDisponibilidad.setText(enciclopedia.getDisponibilidad());
        txtEstado.setText(enciclopedia.getEstadofisico());
        txtArea.setText(enciclopedia.getArea());
        txtCategoria.setText(enciclopedia.getCodcategoriacoleccion().getNombrecol());  
        
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

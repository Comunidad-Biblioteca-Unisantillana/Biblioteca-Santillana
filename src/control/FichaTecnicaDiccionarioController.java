package control;

import entitys.Diccionario;
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
public class FichaTecnicaDiccionarioController {

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
    private TextField txtPaginas;
    @FXML
    private TextField txtCubierta;
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

        Diccionario diccionario = QueryRecurso.consultarDiccionario(codBarra);
        DateFormat formatoFecha = new SimpleDateFormat("YYYY-MM-dd");
           
        txtISBN.setText(diccionario.getIsbn());
        txtTitulo.setText(diccionario.getTitulo());
        txtIdioma.setText(diccionario.getIdioma());
        txtPaisPublicacion.setText(diccionario.getPaispublicacion());
        txtFechaPublicacion.setText(formatoFecha.format(diccionario.getFechapublicacion()));
        txtEditorial.setText(diccionario.getEditorial());
        txtPaginas.setText(String.valueOf(diccionario.getNumpaginas()));
        txtCubierta.setText(diccionario.getCubierta());
        txAreaNota.setText(diccionario.getNota());
        txAreaResumen.setText(diccionario.getResumen());
        txtSignatura.setText(diccionario.getSignatura());
        txtDisponibilidad.setText(diccionario.getDisponibilidad());
        txtEstado.setText(diccionario.getEstadofisico());
        txtArea.setText(diccionario.getArea());
        txtCategoria.setText(diccionario.getCodcategoriacoleccion().getNombrecol());  
        
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
        txtPaginas.setText("");
        txtCubierta.setText("");
        txAreaNota.setText("");
        txAreaResumen.setText("");
        txtSignatura.setText("");
        txtDisponibilidad.setText("");
        txtEstado.setText("");
        txtArea.setText("");
        txtCategoria.setText("");         
        txAreaMateria.setText("");
        txAreaAutores.setText("");
    }
}

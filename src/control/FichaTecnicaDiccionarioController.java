package control;

import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import entitys.Diccionario;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javafx.fxml.FXML;
import modelo.QueryRecurso;

/**
 * FXML Controller class
 *
 * @author stive
 */
public class FichaTecnicaDiccionarioController {

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
    private JFXTextField txtPaginas;
    @FXML
    private JFXTextField txtCubierta;
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

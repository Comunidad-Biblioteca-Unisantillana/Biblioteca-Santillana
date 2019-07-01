package control;

import entitys.Mapa;
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
public class FichaTecnicaMapaController {

    @FXML
    private TextField txtISBN;
    @FXML
    private TextField txtEscalaRepresentativa;
    @FXML
    private TextField txtFormatoMapa;
    @FXML
    private TextField txtPresentacion;
    @FXML
    private TextField txtDisponibilidad;
    @FXML
    private TextField txtEstadoFisico;
    @FXML
    private TextField txtCategoria;
    @FXML
    private TextArea txAreaDescripcion;
    @FXML
    private TextField txtTitulo;
    
    /**
     * Método que carga los datos en la ficha tecnica.
     * @param codBarra
     * @throws Exception 
     */
    public void cargarDatos(String codBarra) throws Exception{      
        Mapa mapa = QueryRecurso.consultarMapa(codBarra);
        DateFormat formatoFecha = new SimpleDateFormat("YYYY-MM-dd");

        txtEscalaRepresentativa.setText(mapa.getEscalarepresentativa());
        txtFormatoMapa.setText(mapa.getFormatomapa());
        txAreaDescripcion.setText(mapa.getDescripcion());
        txtPresentacion.setText(mapa.getPresentacion());
        txtDisponibilidad.setText(mapa.getDisponibilidad());
        txtEstadoFisico.setText(mapa.getEstadofisico());
        txtCategoria.setText(mapa.getCodcategoriacoleccion().getNombrecol()); 
        
        txAreaDescripcion.setWrapText(true);
    }     
    
       public void limpiarCampos(){
       txtISBN.setText("");
        txtTitulo.setText("");
        txtEscalaRepresentativa.setText("");
        txtFormatoMapa.setText("");
        txAreaDescripcion.setText("");
        txtPresentacion.setText("");
        txtDisponibilidad.setText("");
        txtEstadoFisico.setText("");
        txtCategoria.setText("");        
    }
}

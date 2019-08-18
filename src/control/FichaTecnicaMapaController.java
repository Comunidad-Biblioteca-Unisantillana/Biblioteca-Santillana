package control;

import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import entitysRecursos.Mapa;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javafx.fxml.FXML;
import modelo.QueryRecurso;

/**
 * FXML Controller class
 *
 * @author stive
 */
public class FichaTecnicaMapaController {

    @FXML
    private JFXTextField txtISBN;
    @FXML
    private JFXTextField txtEscalaRepresentativa;
    @FXML
    private JFXTextField txtFormatoMapa;
    @FXML
    private JFXTextField txtPresentacion;
    @FXML
    private JFXTextField txtDisponibilidad;
    @FXML
    private JFXTextField txtEstadoFisico;
    @FXML
    private JFXTextField txtCategoria;
    @FXML
    private JFXTextArea txAreaDescripcion;
    @FXML
    private JFXTextField txtTitulo;
    
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

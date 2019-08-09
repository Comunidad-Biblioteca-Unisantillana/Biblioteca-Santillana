
package modelo;

import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import entitys.Mapa;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 *
 * @author Julian
 */
public class DatosFichaMapa implements IDatosFichaTecnica{
    
    private Mapa mapa;

    public DatosFichaMapa(String codBarra) {
        try {
            mapa = QueryRecurso.consultarMapa(codBarra);
        } catch (Exception ex) {
        }
    }

    @Override
    public void insertarDatos(JFXTextField[] TF, JFXTextArea[] TA) {
        TF[0].setText(mapa.getTitulo());
        TF[1].setText(mapa.getIsbn());
        TF[2].setText(mapa.getEscalarepresentativa());
        TF[3].setText(mapa.getFormatomapa());
        TF[4].setText(mapa.getPresentacion());
        TF[5].setText(mapa.getDisponibilidad());
        TF[6].setText(mapa.getEstadofisico());
        TF[7].setText(mapa.getCodcategoriacoleccion().getNombrecol());
        
        TA[0].setText(mapa.getDescripcion());
    }

    @Override
    public String getDatosLabels() {
        return "Titulo,ISBN,Escala Representativa,Formato mapa,Presentación,Disponibilidad,"
                + "Estado fisico,Categoria,Descripción,";
    }

    @Override
    public int getCantidadTextField() {
        return 8;
    }

    @Override
    public int getCantidadTextArea() {
        return 1;
    }
    
}

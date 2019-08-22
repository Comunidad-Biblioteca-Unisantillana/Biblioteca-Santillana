package moduloOPAC.control;

import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import recursos1.entitys.Mapa;

/**
 * Clase que carga los datos de la consulta en la ficha del mapa.
 *
 * @author Jualian
 * @creado
 * @author Miguel Fernández
 * @modificado 19/08/2019
 */
public class DatosFichaMapaController implements IDatosFichaTecnicaController {

    private Mapa mapa;
    /**
     * constructor por parámetros.
     *
     * @param mapa
     */
    public DatosFichaMapaController(Mapa mapa) {
        this.mapa = mapa;
    }

    /**
     * el metódo insserta los datos del mapa en los textFields y el textArea,
     * correpondientes a la ficha del mapa.
     *
     * @param TF
     * @param TA
     */
    @Override
    public void insertarDatos(JFXTextField[] TF, JFXTextArea[] TA) {
        TF[0].setText(mapa.getTitulo());
        TF[1].setText(mapa.getCodbarramapa());
        TF[2].setText(mapa.getIsbn());
        TF[3].setText(mapa.getEscalarepresentativa());
        TF[4].setText(mapa.getFormatomapa());
        TF[5].setText(mapa.getPresentacion());
        TF[6].setText(mapa.getDisponibilidad());
        TF[7].setText(mapa.getEstadofisico());
        TF[8].setText("colección " + mapa.getCodcategoriacoleccion().getNombrecol());

        TA[0].setText(mapa.getDescripcion());
    }

    /**
     * el metódo retorna una cadena con los nombres de los tributos del mapa.
     *
     * @return cadena
     */
    @Override
    public String getDatosLabels() {
        return "Ficha Técnica Mapa,Título,Código de barras,ISBN,Escala Representativa,Formato mapa,"
                + "Presentación,Disponibilidad,Estado fisico,Categoria,Descripción,"; // -> cadena
    }

    /**
     * el metódo retorna la cantidad de textField a utilizar.
     *
     * @return 8
     */
    @Override
    public int getCantidadTextField() {
        return 8;
    }

    /**
     * el metódo retorna la cantidad de textArea a utilizar.
     *
     * @return 1
     */
    @Override
    public int getCantidadTextArea() {
        return 1;
    }

}

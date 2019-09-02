package moduloOPAC.control;

import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import recursos.entitys.Enciclopedia;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Clase que carga los datos de la consulta en la ficha de la enciclopedia.
 *
 * @author Jualian
 * @creado
 * @author Miguel Fernández
 * @modificado 19/08/2019
 */
public class DatosFichaEnciclopediaController implements IDatosFichaTecnicaController {

    private Enciclopedia enciclopedia;
    private String autores;
    private String materias;

    /**
     * constructor por parámetros.
     *
     * @param enciclopedia
     * @param autores
     * @param materias
     */
    public DatosFichaEnciclopediaController(Enciclopedia enciclopedia, String autores, String materias) {
        this.enciclopedia = enciclopedia;
        this.autores = autores;
        this.materias = materias;
    }

    /**
     * el metódo insserta los datos de la enciclopedia en los textFields y el
     * textArea, correpondientes a la ficha de la enciclopedia.
     *
     * @param TF
     * @param TA
     */
    @Override
    public void insertarDatos(JFXTextField[] TF, JFXTextArea[] TA) {
        DateFormat formatoFecha = new SimpleDateFormat("YYYY-MM-dd");

        TF[0].setText(enciclopedia.getTitulo());
        TF[1].setText(enciclopedia.getCodbarraenciclopedia());
        TF[2].setText(enciclopedia.getIsbn());
        TF[3].setText(enciclopedia.getIdioma());
        TF[4].setText(enciclopedia.getPaispublicacion());
        TF[5].setText(formatoFecha.format(enciclopedia.getFechapublicacion()));
        TF[6].setText(enciclopedia.getEditorial());
        TF[7].setText(String.valueOf(enciclopedia.getNumvolumen()));
        TF[8].setText(String.valueOf(enciclopedia.getNumpaginas()));
        TF[9].setText(enciclopedia.getCubierta());
        TF[10].setText(enciclopedia.getDimensiones());
        TF[11].setText(enciclopedia.getSignatura());
        TF[12].setText(enciclopedia.getDisponibilidad());
        TF[13].setText(enciclopedia.getEstadofisico());
        TF[14].setText("colección " + enciclopedia.getCodcategoriacoleccion().getNombrecol());

        TA[0].setText(autores);
        TA[1].setText(materias);
        TA[2].setText(enciclopedia.getNota());
        TA[3].setText(enciclopedia.getResumen());
    }

    /**
     * el metódo retorna una cadena con los nombres de los tributos de la
     * enciclopedia.
     *
     * @return cadena
     */
    @Override
    public String getDatosLabels() {
        return "Ficha Técnica Enciclopedia  ,Título,Código de barras,ISBN,Idioma,País de publicación,Fecha de publicación,Editorial,Edición,"
                + "Número de páginas,Cubierta,Dimensiones,Signatura,Disponibilidad,Estado fisico,"
                + "Categoria,Autores,Materias,Nota,Resumen,"; // -> cadena
    }

    /**
     * el metódo retorna la cantidad de textField a utilizar.
     *
     * @return 15
     */
    @Override
    public int getCantidadTextField() {
        return 15;
    }

    /**
     * el metódo retorna la cantidad de textArea a utilizar.
     *
     * @return 4
     */
    @Override
    public int getCantidadTextArea() {
        return 4;
    }

}

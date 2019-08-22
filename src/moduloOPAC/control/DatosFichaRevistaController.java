package moduloOPAC.control;

import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import recursos1.entitys.Revista;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Clase que carga los datos de la consulta en la ficha de la revista.
 *
 * @author Jualian
 * @creado
 * @author Miguel Fernández
 * @modificado 19/08/2019
 */
public class DatosFichaRevistaController implements IDatosFichaTecnicaController {

    private Revista revista;
    private String materias;
    
    /**
     * constructor por parámetros.
     *
     * @param revista
     * @param materias
     */
    public DatosFichaRevistaController(Revista revista, String materias) {
        this.revista = revista;
        this.materias = materias;
    }

    /**
     * el metódo insserta los datos de la revista en los textFields y el
     * textArea, correpondientes a la ficha de la revista.
     *
     * @param TF
     * @param TA
     */
    @Override
    public void insertarDatos(JFXTextField[] TF, JFXTextArea[] TA) {
        DateFormat formatoFecha = new SimpleDateFormat("YYYY-MM-dd");

        TF[0].setText(revista.getTitulo());
        TF[1].setText(revista.getCodbarrarevista());
        TF[2].setText(revista.getIssn());
        TF[3].setText(revista.getIdioma());
        TF[4].setText(revista.getPaispublicacion());
        TF[5].setText(formatoFecha.format(revista.getFechapublicacion()));
        TF[6].setText(revista.getPublicador());
        TF[7].setText(revista.getDisponibilidad());
        TF[8].setText(revista.getEstadofisico());
        TF[9].setText("colección " + revista.getCodcategoriacoleccion().getNombrecol());

        TA[0].setText(materias);
    }

    /**
     * el metódo insserta los datos de la revista en los textFields y el
     * textArea, correpondientes a la ficha de la revista.
     *
     * @param TF
     * @param TA
     */
    @Override
    public String getDatosLabels() {
        return "Ficha Técnica Revista,Título,Código de barras,ISSN,Idioma,País de publicación,Fecha de publicación,"
                + "Publicador,Disponibilidad,Estado fisico,Categoria,Materias,";
    }

    /**
     * el metódo retorna la cantidad de textField a utilizar.
     *
     * @return 10
     */
    @Override
    public int getCantidadTextField() {
        return 10;
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

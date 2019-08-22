package moduloOPAC.control;

import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import recursos1.entitys.Diccionario;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Clase que carga los datos de la consulta en la ficha del diccionario.
 *
 * @author Jualian
 * @creado
 * @author Miguel Fernández
 * @modificado 19/08/2019
 */
public class DatosFichaDiccionarioController implements IDatosFichaTecnicaController {

    private Diccionario diccionario;
    private String autores;
    private String materias;

    /**
     * constructor por parámetro.
     *
     * @param diccionario
     * @param autores
     * @param materias
     */
    public DatosFichaDiccionarioController(Diccionario diccionario, String autores, String materias) {
        this.diccionario = diccionario;
        this.autores = autores;
        this.materias = materias;
    }

    /**
     * el metódo insserta los datos del diccionario en los textFields y el
     * textArea, correpondientes a la ficha del diccionario.
     *
     * @param TF
     * @param TA
     */
    @Override
    public void insertarDatos(JFXTextField[] TF, JFXTextArea[] TA) {
        DateFormat formatoFecha = new SimpleDateFormat("YYYY-MM-dd");

        TF[0].setText(diccionario.getTitulo());
        TF[1].setText(diccionario.getCodbarradiccionario());
        TF[2].setText(diccionario.getIsbn());
        TF[3].setText(diccionario.getIdioma());
        TF[4].setText(diccionario.getPaispublicacion());
        TF[5].setText(formatoFecha.format(diccionario.getFechapublicacion()));
        TF[6].setText(diccionario.getEditorial());
        TF[7].setText(String.valueOf(diccionario.getNumpaginas()));
        TF[8].setText(diccionario.getCubierta());
        TF[9].setText(diccionario.getSignatura());
        TF[10].setText(diccionario.getDisponibilidad());
        TF[11].setText(diccionario.getEstadofisico());
        TF[12].setText("colección " + diccionario.getCodcategoriacoleccion().getNombrecol());

        TA[0].setText(autores);
        TA[1].setText(materias);
        TA[2].setText(diccionario.getNota());
        TA[3].setText(diccionario.getResumen());
    }

    /**
     * el metódo retorna una cadena con los nombres de los tributos del
     * diccionario.
     *
     * @return cadena
     */
    @Override
    public String getDatosLabels() {
        return "Ficha Técnica Diccionario,Título, Código de barras,ISBN,Idioma,País de publicación,Fecha de publicación,Editorial,"
                + "Número de páginas,Cubierta,Signatura,Disponibilidad,Estado fisico,Categoria,"
                + "Autores,Materias,Nota,Resumen,"; // -> cadena
    }

    /**
     * el metódo retorna la cantidad de textField a utilizar.
     *
     * @return 13
     */
    @Override
    public int getCantidadTextField() {
        return 13;
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

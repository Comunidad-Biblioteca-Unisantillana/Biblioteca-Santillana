package moduloOPAC.control;

import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import recursos1.entitys.Libro;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Clase que carga los datos de la consulta en la ficha del libro.
 *
 * @author Jualian
 * @creado
 * @author Miguel Fernández
 * @modificado 19/08/2019
 */
public class DatosFichaLibroController implements IDatosFichaTecnicaController {

    private Libro libro;
    private String autores;
    private String materias;

    /**
     * constructor por parámetros.
     *
     * @param libro
     * @param autores
     * @param materias
     */
    public DatosFichaLibroController(Libro libro, String autores, String materias) {
        this.libro = libro;
        this.autores = autores;
        this.materias = materias;
    }

    /**
     * el metódo insserta los datos del libro en los textFields y el textArea,
     * correpondientes a la ficha del libro.
     *
     * @param TF
     * @param TA
     */
    @Override
    public void insertarDatos(JFXTextField[] TF, JFXTextArea[] TA) {
        DateFormat formatoFecha = new SimpleDateFormat("YYYY-MM-dd");

        TF[0].setText(libro.getTitulo());
        TF[1].setText(libro.getCodbarralibro());
        TF[2].setText(libro.getIsbn());
        TF[3].setText(libro.getIdioma());
        TF[4].setText(libro.getPaispublicacion());
        TF[5].setText(formatoFecha.format(libro.getFechapublicacion()));
        TF[6].setText(libro.getEditorial());
        TF[7].setText(String.valueOf(libro.getNumedicion()));
        TF[8].setText(String.valueOf(libro.getNumpaginas()));
        TF[9].setText(libro.getCubierta());
        TF[10].setText(libro.getDimensiones());
        TF[11].setText(libro.getSignatura());
        TF[12].setText(libro.getDisponibilidad());
        TF[13].setText(libro.getEstadofisico());
        TF[14].setText("colección " + libro.getCodcategoriacoleccion().getNombrecol());

        TA[0].setText(autores);
        TA[1].setText(materias);
        TA[2].setText(libro.getNota());
        TA[3].setText(libro.getResumen());
    }

    /**
     * el metódo retorna una cadena con los nombres de los tributos del libro.
     *
     * @return cadena
     */
    @Override
    public String getDatosLabels() {
        return "Ficha Técnica Libro,Título,Código de barras,ISBN,Idioma,País de publicación,Fecha de publicación,Editorial,Edición,"
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

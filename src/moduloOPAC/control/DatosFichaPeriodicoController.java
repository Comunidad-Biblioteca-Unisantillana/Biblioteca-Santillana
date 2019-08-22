package moduloOPAC.control;

import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import recursos1.entitys.Periodico;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Clase que carga los datos de la consulta en la ficha del periódico.
 *
 * @author Jualian
 * @creado
 * @author Miguel Fernández
 * @modificado 19/08/2019
 */
public class DatosFichaPeriodicoController implements IDatosFichaTecnicaController {

    private Periodico periodico;

    /**
     * constructor por parámetros.
     *
     * @param periodico
     * @param autores
     */
    public DatosFichaPeriodicoController(Periodico periodico) {
        this.periodico = periodico;
    }

    /**
     * el metódo insserta los datos del periódico en los textFields y el
     * textArea, correpondientes a la ficha del periódico.
     *
     * @param TF
     * @param TA
     */
    @Override
    public void insertarDatos(JFXTextField[] TF, JFXTextArea[] TA) {
        DateFormat formatoFecha = new SimpleDateFormat("YYYY-MM-dd");

        TF[0].setText(periodico.getNombreperiodico());
        TF[1].setText(periodico.getCodbarraperiodico());
        TF[2].setText(periodico.getIssn());
        TF[3].setText(formatoFecha.format(periodico.getFechapublicacion()));
        TF[4].setText(periodico.getCiudad());
        TF[5].setText(String.valueOf(periodico.getPaginas()));
        TF[6].setText(periodico.getEditorial());
        TF[7].setText(periodico.getDisponibilidad());
        TF[8].setText(periodico.getEstadofisico());
        TF[9].setText("colección " + periodico.getCodcategoriacoleccion().getNombrecol());
    }

    /**
     * el metódo retorna una cadena con los nombres de los tributos del periódico.
     *
     * @return cadena
     */
    @Override
    public String getDatosLabels() {
        return "Ficha Técnica Periódico,Nombre del periódico,Código de Barras,ISSN,Fecha de publicación,"
                + "Ciudad,Número de páginas,Editorial,Disponibilidad,Estado fisico,Categoria,"; // -> cadena
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
     * @return 0
     */
    @Override
    public int getCantidadTextArea() {
        return 0;
    }

}

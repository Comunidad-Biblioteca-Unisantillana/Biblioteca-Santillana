package modelo;

import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import entitysRecursos.Periodico;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 *
 * @author Julian
 */
public class DatosFichaPeriodico implements IDatosFichaTecnica {

    private Periodico periodico;

    public DatosFichaPeriodico(String codBarra) {
        try {
            periodico = QueryRecurso.consultarPeriodico(codBarra);
        } catch (Exception ex) {
        }
    }

    @Override
    public void insertarDatos(JFXTextField[] TF, JFXTextArea[] TA) {
        DateFormat formatoFecha = new SimpleDateFormat("YYYY-MM-dd");

        TF[0].setText(periodico.getNombreperiodico());
        TF[1].setText(periodico.getIssn());
        TF[2].setText(formatoFecha.format(periodico.getFechapublicacion()));
        TF[3].setText(periodico.getCiudad());
        TF[4].setText(String.valueOf(periodico.getPaginas()));
        TF[5].setText(periodico.getEditorial());
        TF[6].setText(periodico.getDisponibilidad());
        TF[7].setText(periodico.getEstadofisico());
        TF[8].setText(periodico.getCodcategoriacoleccion().getNombrecol());

    }

    @Override
    public String getDatosLabels() {
        return "Nombre,ISSN,Fecha de publicación,Ciudad,Paginas,Editorial,Disponibilidad,"
                + "Estado fisico,Categoria,";
    }

    @Override
    public int getCantidadTextField() {
        return 9;
    }

    @Override
    public int getCantidadTextArea() {
        return 0;
    }

}

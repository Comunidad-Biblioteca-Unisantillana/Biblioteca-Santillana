package modelo;

import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import control.QueryControl;
import entitysRecursos.Diccionario;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 *
 * @author Julian
 */
public class DatosFichaDiccionaria implements IDatosFichaTecnica {

    private Diccionario diccionario;

    public DatosFichaDiccionaria(String codBarra) {
        try {
            diccionario = QueryRecurso.consultarDiccionario(codBarra);
        } catch (Exception ex) {
        }
    }

    @Override
    public void insertarDatos(JFXTextField[] TF, JFXTextArea[] TA) {
        DateFormat formatoFecha = new SimpleDateFormat("YYYY-MM-dd");
        
        TF[0].setText(diccionario.getTitulo());
        TF[1].setText(diccionario.getIsbn());
        TF[2].setText(diccionario.getIdioma());
        TF[3].setText(diccionario.getPaispublicacion());
        TF[4].setText(formatoFecha.format(diccionario.getFechapublicacion()));
        TF[5].setText(diccionario.getEditorial());
        TF[6].setText(String.valueOf(diccionario.getNumpaginas()));
        TF[7].setText(diccionario.getCubierta());
        TF[8].setText(diccionario.getSignatura());
        TF[9].setText(diccionario.getDisponibilidad());
        TF[10].setText(diccionario.getEstadofisico());
        TF[11].setText(diccionario.getArea());
        TF[12].setText(diccionario.getCodcategoriacoleccion().getNombrecol());  
        
        QueryControl.getInstance().consultarAutorRecurso(diccionario.getCodbarradiccionario(), "diccionario");
        QueryControl.getInstance().consultarMaterias(diccionario.getCodbarradiccionario(), "diccionario");
        
        TA[0].setText(QueryControl.getInstance().getCadenaAutores());
        TA[1].setText(QueryControl.getInstance().getCadenaMaterias());
        TA[2].setText(diccionario.getNota());
        TA[3].setText(diccionario.getResumen());
    }

    @Override
    public String getDatosLabels() {
        return "Titulo,ISBN,Idioma,País de publicación,Fecha de publicación,Editorial,"
                + "Páginas,Cubierta,Signatura,Disponobilidad,Estado fisico,Area,Categoria,"
                + "Autores,Materia,Nota,Resumen,";
    }

    @Override
    public int getCantidadTextField() {
        return 13;
    }

    @Override
    public int getCantidadTextArea() {
        return 4;
    }

}

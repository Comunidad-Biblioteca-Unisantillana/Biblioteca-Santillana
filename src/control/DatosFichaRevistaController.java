/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import control.QueryControl;
import entitys.Revista;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import modelo.QueryRecurso;

/**
 *
 * @author Julian
 */
public class DatosFichaRevistaController implements IDatosFichaTecnicaController {

    private Revista revista;

    public DatosFichaRevistaController(String codBarra) {
        try {
            revista = QueryRecurso.consultarRevista(codBarra);
        } catch (Exception ex) {
        }
    }

    @Override
    public void insertarDatos(JFXTextField[] TF, JFXTextArea[] TA) {
        DateFormat formatoFecha = new SimpleDateFormat("YYYY-MM-dd");

        TF[0].setText(revista.getTitulo());
        TF[1].setText(revista.getPublicador());
        TF[2].setText(revista.getIssn());
        TF[3].setText(revista.getIdioma());
        TF[4].setText(revista.getPaispublicacion());
        TF[5].setText(formatoFecha.format(revista.getFechapublicacion()));
        TF[6].setText(revista.getDisponibilidad());
        TF[7].setText(revista.getEstadofisico());

        QueryControl.getInstance().consultarMaterias(revista.getCodbarrarevista(), "revista");

        TF[8].setText(QueryControl.getInstance().getCadenaMaterias());
        TF[9].setText(revista.getCodcategoriacoleccion().getNombrecol());
    }

    @Override
    public String getDatosLabels() {
        return "Nombre,Publicador,ISSN,Idioma,País de publicación,Fecha de publicación"
                + ",Disponibilidad,Estado fisico,Materia,Categoria,";
    }

    @Override
    public int getCantidadTextField() {
        return 10;
    }

    @Override
    public int getCantidadTextArea() {
        return 0;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import control.QueryControl;
import entitys.Enciclopedia;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import modelo.QueryRecurso;

/**
 *
 * @author Julian
 */
public class DatosFichaEnciclopediaController implements IDatosFichaTecnicaController {
    
    private Enciclopedia enciclopedia;

    public DatosFichaEnciclopediaController(String codBarra) {
        try {
            enciclopedia = QueryRecurso.consultarEnciclopedia(codBarra);
        } catch (Exception ex) {
        }
    }
    
    @Override
    public void insertarDatos(JFXTextField[] TF, JFXTextArea[] TA) {
        System.out.println("qs");
        DateFormat formatoFecha = new SimpleDateFormat("YYYY-MM-dd");
        
        TF[0].setText(enciclopedia.getTitulo());
        TF[1].setText(enciclopedia.getIsbn());
        TF[2].setText(enciclopedia.getIdioma());
        TF[3].setText(enciclopedia.getPaispublicacion());
        TF[4].setText(formatoFecha.format(enciclopedia.getFechapublicacion()));
        TF[5].setText(enciclopedia.getEditorial());
        TF[6].setText(String.valueOf("enciclopedia.getNumedicion()"));
        TF[7].setText(String.valueOf(enciclopedia.getNumpaginas()));
        TF[8].setText(enciclopedia.getCubierta());
        TF[9].setText(enciclopedia.getDimensiones());
        TF[10].setText(enciclopedia.getSignatura());
        TF[11].setText(enciclopedia.getDisponibilidad());
        TF[12].setText(enciclopedia.getEstadofisico());
        TF[13].setText("enciclopedia.getArea()");
        TF[14].setText(enciclopedia.getCodcategoriacoleccion().getNombrecol());

        QueryControl.getInstance().consultarAutorRecurso(enciclopedia.getCodbarraenciclopedia(), "enciclopedia");
        QueryControl.getInstance().consultarMaterias(enciclopedia.getCodbarraenciclopedia(), "enciclopedia");

        TA[0].setText(QueryControl.getInstance().getCadenaAutores());
        TA[1].setText(QueryControl.getInstance().getCadenaMaterias());
        TA[2].setText(enciclopedia.getNota());
        TA[3].setText(enciclopedia.getResumen());
    }

    @Override
    public String getDatosLabels() {
        return "Titulo,ISBN,Idioma,País de publicación,Fecha de publicación,Editorial,Edición,"
                + "Páginas,Cubierta,Dimensiones,Signatura,Disponibilidad,Estado"
                + ",Area,Categoria,Autores,Materia,Nota,Resumen,";
    }

    @Override
    public int getCantidadTextField() {
        return 15;
    }

    @Override
    public int getCantidadTextArea() {
        return 4;
    }

}

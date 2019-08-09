/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import control.QueryControl;
import entitys.Libro;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import modelo.QueryRecurso;

/**
 * Clase que obtiene los datos de la consulta de la ficha del libro
 *
 * @author Jualian
 */
public class DatosFichaLibroController implements IDatosFichaTecnicaController {

    private Libro libro;

    public DatosFichaLibroController(String codBarra) {
        try {
            libro = QueryRecurso.consultarLibro(codBarra);
        } catch (Exception ex) {
        }
    }

    @Override
    public void insertarDatos(JFXTextField[] TF, JFXTextArea[] TA) {
        DateFormat formatoFecha = new SimpleDateFormat("YYYY-MM-dd");

        TF[0].setText(libro.getTitulo());
        TF[1].setText(libro.getIsbn());
        TF[2].setText(libro.getIdioma());
        TF[3].setText(libro.getPaispublicacion());
        TF[4].setText(formatoFecha.format(libro.getFechapublicacion()));
        TF[5].setText(libro.getEditorial());
        TF[6].setText(String.valueOf(libro.getNumedicion()));
        TF[7].setText(String.valueOf(libro.getNumpaginas()));
        TF[8].setText(libro.getCubierta());
        TF[9].setText(libro.getDimensiones());
        TF[10].setText(libro.getSignatura());
        TF[11].setText(libro.getDisponibilidad());
        TF[12].setText(libro.getEstadofisico());
        TF[13].setText(libro.getArea());
        TF[14].setText(libro.getCodcategoriacoleccion().getNombrecol());

        QueryControl.getInstance().consultarAutorRecurso(libro.getCodbarralibro(), "libro");
        QueryControl.getInstance().consultarMaterias(libro.getCodbarralibro(), "libro");

        TA[0].setText(QueryControl.getInstance().getCadenaAutores());
        TA[1].setText(QueryControl.getInstance().getCadenaMaterias());
        TA[2].setText(libro.getNota());
        TA[3].setText(libro.getResumen());
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

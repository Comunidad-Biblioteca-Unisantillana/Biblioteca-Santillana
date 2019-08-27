package moduloOPAC.vista;

import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import moduloOPAC.control.DatosFichaDiccionarioController;
import moduloOPAC.control.DatosFichaEnciclopediaController;
import moduloOPAC.control.DatosFichaLibroController;
import moduloOPAC.control.DatosFichaMapaController;
import moduloOPAC.control.DatosFichaPeriodicoController;
import moduloOPAC.control.DatosFichaRevistaController;
import moduloOPAC.control.IDatosFichaTecnicaController;
import recursos.entitys.Diccionario;
import recursos.entitys.Enciclopedia;
import recursos.entitys.Libro;
import recursos.entitys.Mapa;
import recursos.entitys.Periodico;
import recursos.entitys.Revista;

/**
 * Clase que se encarga de crear la vista de una ficha técnica.
 *
 * @author Julian
 * @creado 05/08/2019
 * @author Miguel Fernández
 * @modificado 18/08/2019
 */
public class FichaTecnicaPanel {

    private GridPane rootFicha;
    private IDatosFichaTecnicaController datosFicha;
    private final Label[] arregloLabel;
    private final JFXTextField[] arregloTextField;
    private final JFXTextArea[] arregloTextArea;
    private String textoLabel;

    /**
     * Constructor sin parámetros.
     */
    public FichaTecnicaPanel() {
        arregloLabel = new Label[20];
        arregloTextField = new JFXTextField[15];
        arregloTextArea = new JFXTextArea[4];
        rootFicha = new GridPane();
        rootFicha.getStylesheets().add("/general/recursos/styles/stylesFichaTecnica.css");
        rootFicha.setStyle("-fx-border-color: black;"
                + "        -fx-border-width: 2 px;");
    }

    /**
     * el metódo carga la ficha técnica de un recurso especiico.
     *
     * @param nombreFicha
     * @param codBarras
     * @return
     */
    public GridPane cargarFichaTecnica(Object recurso, String autores, String materias) {        
        if (recurso instanceof Libro) {            
            datosFicha = new DatosFichaLibroController((Libro) recurso, autores, materias);
            componentesFicha_1();
        } else if (recurso instanceof Enciclopedia) {            
            datosFicha = new DatosFichaEnciclopediaController((Enciclopedia) recurso, autores, materias);
            componentesFicha_1();
        } else if (recurso instanceof Diccionario) {            
            datosFicha = new DatosFichaDiccionarioController((Diccionario) recurso, autores, materias);
            componentesFicha_2();
        } else if (recurso instanceof Revista) {
            datosFicha = new DatosFichaRevistaController((Revista) recurso, materias);
            componentesFicha_3();
        } else if (recurso instanceof Periodico) {
            datosFicha = new DatosFichaPeriodicoController((Periodico) recurso);
            componentesFicha_4();
        } else {
            datosFicha = new DatosFichaMapaController((Mapa) recurso);
            componentesFicha_4();
            cargarTextArea(5, 3, 0, 2, 1);
        }

        datosFicha.insertarDatos(arregloTextField, arregloTextArea);
        desabilitarEditableText(datosFicha.getCantidadTextField(), datosFicha.getCantidadTextArea());

        return rootFicha;
    }

    /**
     * el metódo carga los componentes base para la ficha técnica de la
     * enciclopedia o el libro.
     */
    private void componentesFicha_1() {
        textoLabel = datosFicha.getDatosLabels();
        cargarLabels(0, 1, 0, 0);
        cargarLabels(1, 11, 0, 1);
        cargarLabels(2, 7, 2, 10);
        cargarLabels(7, 8, 2, 16);
        cargarLabels(9, 10, 2, 17);
        cargarLabels(2, 3, 4, 18);
        cargarLabels(6, 7, 4, 19);
        cargarTextFields(1, 2, 1, 0, 3);
        cargarTextFields(2, 11, 1, 1, 1);
        cargarTextFields(2, 7, 3, 10, 1);
        cargarTextArea(7, 3, 0, 2, 1);
        cargarTextArea(9, 3, 1, 2, 1);
        cargarTextArea(2, 5, 2, 4, 1);
        cargarTextArea(6, 5, 3, 4, 1);
    }

    /**
     * el metódo carga los componentes base para la ficha técnica de
     * diccionario.
     */
    private void componentesFicha_2() {
        textoLabel = datosFicha.getDatosLabels();
        cargarLabels(0, 1, 0, 0);
        cargarLabels(1, 10, 0, 1);
        cargarLabels(2, 7, 2, 9);
        cargarLabels(8, 9, 2, 15);
        cargarLabels(2, 3, 4, 16);
        cargarLabels(6, 7, 4, 17);
        cargarTextFields(1, 2, 1, 0, 3);
        cargarTextFields(2, 10, 1, 1, 1);
        cargarTextFields(2, 6, 3, 9, 1);
        cargarTextArea(6, 3, 0, 2, 1);
        cargarTextArea(8, 3, 1, 2, 1);
        cargarTextArea(2, 5, 2, 4, 2);
        cargarTextArea(6, 5, 3, 4, 2);
    }

    /**
     * el metódo carga los componentes base para la ficha técnica de revista.
     */
    private void componentesFicha_3() {
        textoLabel = datosFicha.getDatosLabels();
        cargarLabels(0, 1, 0, 0);
        cargarLabels(1, 8, 0, 1);
        cargarLabels(2, 6, 2, 8);
        cargarTextFields(1, 2, 1, 0, 3);
        cargarTextFields(2, 8, 1, 1, 1);
        cargarTextFields(2, 5, 3, 7, 1);
        cargarTextArea(5, 3, 0, 2, 1);
    }

    /**
     * el metódo carga los componentes base para la ficha técnica del periódico
     * o el mapa.
     */
    private void componentesFicha_4() {
        textoLabel = datosFicha.getDatosLabels();
        cargarLabels(0, 1, 0, 0);
        cargarLabels(1, 7, 0, 1);
        cargarLabels(2, 6, 2, 7);
        cargarTextFields(1, 2, 1, 0, 3);
        cargarTextFields(2, 7, 1, 1, 1);
        cargarTextFields(2, 6, 3, 6, 1);
    }

    /**
     * el metódo carga los labels en pantalla.
     *
     * @param inicioFila
     * @param limiteFila
     * @param posColumna
     * @param posArreglo
     */
    private void cargarLabels(int inicioFila, int limiteFila, int posColumna, int posArreglo) {
        for (int i = inicioFila; i < limiteFila; i++) {
            arregloLabel[posArreglo] = new Label(obtenerCadena());
            rootFicha.add(arregloLabel[posArreglo], posColumna, i);
            posArreglo++;
        }
    }

    /**
     * el metódo obtiene una cadena con los atributos del recurso (de la cadena
     * textoLabel) y tambien la elimina.
     *
     * @return cadena
     */
    private String obtenerCadena() {
        String cadena = "";

        for (int j = 0; j < textoLabel.length(); j++) {
            if (String.valueOf(textoLabel.charAt(j)).equals(",")) {
                cadena = textoLabel.substring(0, j);
                textoLabel = textoLabel.substring(j + 1);
                break;
            }
        }

        return cadena;
    }

    /**
     * Metódo que carga los TextFileds en pantalla.
     *
     * @param inicioFila
     * @param limiteFila
     * @param posColumna
     * @param posArreglo
     */
    private void cargarTextFields(int inicioFila, int limiteFila, int posColumna, int posArreglo, int repetirColumna) {
        for (int i = inicioFila; i < limiteFila; i++) {
            arregloTextField[posArreglo] = new JFXTextField();
            rootFicha.add(arregloTextField[posArreglo], posColumna, i, repetirColumna, 1);
            posArreglo++;
        }
    }

    /**
     * Metódo que carga los TextAreas en pantalla.
     *
     * @param inicioFila
     * @param limiteFila
     * @param posColumna
     * @param posArreglo
     */
    private void cargarTextArea(int inicioFila, int posColumna, int posArreglo, int repetirFila, int repetirColumna) {
        arregloTextArea[posArreglo] = new JFXTextArea();
        rootFicha.add(arregloTextArea[posArreglo], posColumna, inicioFila, repetirColumna, repetirFila);
    }

    /**
     * el metódo restringe la entrada de datos en los TextField.
     */
    private void desabilitarEditableText(int pos, int pos1) {
        for (int i = 0; i < pos; i++) {
            arregloTextField[i].setEditable(false);
        }

        for (int i = 0; i < pos1; i++) {
            arregloTextArea[i].setEditable(false);
        }
    }

}

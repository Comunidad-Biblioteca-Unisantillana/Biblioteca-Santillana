package vista;

import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

/**
 * Clase que se encarga de crear la vista una Ficha tecnica
 * @author Julian 
 * Fecha de Creación: 05/08/2019 
 * Fecha de ultima Modificación:06/08/2019
 */
public class FichaTecnicaPanel {

    private final Label[] arregloLabel;
    private final JFXTextField[] arregloTextField;
    private final JFXTextArea[] arregloTextArea;
    private final String nombreRecurso;
    private final GridPane rootFicha;
    private String textoLabel;

    /**
     * Constructor de la clase
     * @param nombreRecurso 
     */
    public FichaTecnicaPanel(String nombreRecurso) {
        this.nombreRecurso = nombreRecurso;
        rootFicha = new GridPane();
        rootFicha.getStylesheets().add("recursos/stylesFichaTecnica.css");
        arregloLabel = new Label[19];
        arregloTextField = new JFXTextField[15];
        arregloTextArea = new JFXTextArea[4];
    }

    /**
     * Metodo que carga una ficha tecnica
     * @return
     */
    public GridPane cargarFichaTecnica() {
        switch (nombreRecurso.toLowerCase()) {
            case "libro":
                cargarFichaTecnicaLibro();
                break;
            case "revista":
                cargarFichaTecnicaPeriodico();
                break;
        }
        return rootFicha;
    }

    /**
     * Metodo que carga la ficha tecnica libro
     */
    private void cargarFichaTecnicaLibro() {
        textoLabel = "Titulo,ISBN,Idioma,País de publicación,Fecha de publicación,Editorial,Edición,"
                + "Páginas,Cubierta,Dimensiones,Signatura,Disponibilidad,Estado"
                + ",Area,Categoria,Autores,Materia,Nota,Resumen,";
        
        cargarLabels(0, 10, 0, 0);
        cargarLabels(1, 7, 2, 10);
        cargarLabels(8, 9, 2, 16);
        cargarLabels(1, 2, 4, 17);
        cargarLabels(6, 7, 4, 18);

        cargarTextFields(0, 1, 1, 0, 2);
        cargarTextFields(1, 10, 1, 1, 1);
        cargarTextFields(1, 6, 3, 10, 1);

        cargarTextAreas(6, 7, 3, 0, 2, 1);
        cargarTextAreas(8, 9, 3, 1, 2, 1);
        cargarTextAreas(1, 2, 5, 2, 4, 3);
        cargarTextAreas(6, 7, 5, 3, 4, 3);
    }

    private void cargarFichaTecnicaPeriodico() {
        textoLabel = "Nombre,ISSN,Fecha de publicación,Ciudad,Paginas,Editorial,Disponibilidad,"
                + "Estado fisico,Categoria";
    }

    /**
     * Metodo que carga los labels en pantalla
     * @param inicioFila
     * @param limiteFila
     * @param posColumna
     * @param posArreglo
     */
    private void cargarLabels(int inicioFila, int limiteFila, int posColumna, int posArreglo) {
        for (int i = inicioFila; i < limiteFila; i++) {
            arregloLabel[posArreglo] = new Label(obtenerFrase());
            rootFicha.add(arregloLabel[posArreglo], posColumna, i);
            posArreglo++;
        }
    }

    /**
     * Metodo que obtiene una palabra o frase<br>
     * de la cadena textoLabel y tambien la<br>
     * elimina
     * @return
     */
    private String obtenerFrase() {
        for (int j = 0; j < textoLabel.length(); j++) {
            if (String.valueOf(textoLabel.charAt(j)).equals(",")) {
                String cadena = textoLabel.substring(0, j);
                textoLabel = textoLabel.substring(j + 1);
                return cadena;
            }
        }
        return "";
    }

    /**
     * Metodo que carga los TextFileds en pantalla
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
     * Metodo que carga los TextAreas en pantalla
     *
     * @param inicioFila
     * @param limiteFila
     * @param posColumna
     * @param posArreglo
     */
    private void cargarTextAreas(int inicioFila, int limiteFila, int posColumna, int posArreglo, int repetirFila, int repetirColumna) {
        for (int i = inicioFila; i < limiteFila; i++) {
            arregloTextArea[posArreglo] = new JFXTextArea();
            rootFicha.add(arregloTextArea[posArreglo], posColumna, i, repetirColumna, repetirFila);
        }
    }

    /**
     * Metodo que restringe la entrada de datos de los TextField
     */
    private void desabilitarEditableText() {
        for (JFXTextField arregloTextField1 : arregloTextField) {
            arregloTextField1.setEditable(false);
        }
        for (JFXTextArea arregloTextArea1 : arregloTextArea) {
            arregloTextArea1.setEditable(false);
        }
    }

}

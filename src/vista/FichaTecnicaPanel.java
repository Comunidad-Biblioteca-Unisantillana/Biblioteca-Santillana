package vista;

import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import control.DatosFichaDiccionariaController;
import control.DatosFichaEnciclopediaController;
import control.DatosFichaLibroController;
import control.DatosFichaMapaController;
import control.DatosFichaPeriodicoController;
import control.DatosFichaRevistaController;
import control.IDatosFichaTecnicaController;

/**
 * Clase que se encarga de crear la vista una Ficha tecnica
 *
 * @author Julian 
 * Fecha de Creación: 05/08/2019 Fecha de ultima
 * Modificación:06/08/2019
 */
public class FichaTecnicaPanel {

    private GridPane rootFicha;
    private IDatosFichaTecnicaController datosFicha;
    private final Label[] arregloLabel;
    private final JFXTextField[] arregloTextField;
    private final JFXTextArea[] arregloTextArea;
    private String textoLabel;

    /**
     * Constructor de la clase
     */
    public FichaTecnicaPanel() {
        arregloLabel = new Label[19];
        arregloTextField = new JFXTextField[15];
        arregloTextArea = new JFXTextArea[4];
    }

    /**
     * Metodo que carga una ficha tecnica
     *
     * @param nombreFicha
     * @param codBarras
     * @return
     */
    public GridPane cargarFichaTecnica(String nombreFicha, String codBarras) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/FichaTecnica.fxml"));
            rootFicha = loader.load();
            rootFicha.getStylesheets().add("/recursos/stylesFichaTecnica.css");
            switch (nombreFicha.toLowerCase()) {
                case "libro":
                    datosFicha = new DatosFichaLibroController(codBarras);
                    vistaFichaTecnicaLibro();
                    break;
                case "enciclopedia":
                    datosFicha = new DatosFichaEnciclopediaController(codBarras);
                    vistaFichaTecnicaEnciclopedia();
                    break;
                case "diccionario":
                    datosFicha = new DatosFichaDiccionariaController(codBarras);
                    vistaFichaTecnicaDiccionario();
                    break;
                case "revista":
                    datosFicha = new DatosFichaRevistaController(codBarras);
                    vistaFichaTecnicaRevista();
                    break;
                case "mapa":
                    datosFicha = new DatosFichaMapaController(codBarras);
                    vistaFichaTecnicaMapa();
                    break;
                case "periodico":
                    datosFicha = new DatosFichaPeriodicoController(codBarras);
                    vistaFichaTecnicaPeriodico();
                    break;
            }
            datosFicha.insertarDatos(arregloTextField, arregloTextArea);
            desabilitarEditableText(datosFicha.getCantidadTextField(),datosFicha.getCantidadTextArea());
        } catch (IOException ex) {
            System.out.println("error al cargar ficha");
        }
        return rootFicha;
    }

    /**
     * metodo que carga la ficha tecnica de libro
     */
    private void vistaFichaTecnicaLibro() {
        textoLabel = datosFicha.getDatosLabels();
        componentesFicha_1();
    }

    /**
     * metodo que carga la ficha tecnica de enciclopedia
     */
    private void vistaFichaTecnicaEnciclopedia() {
        textoLabel = datosFicha.getDatosLabels();
        componentesFicha_1();
    }

    /**
     * metodo que carga la ficha tecnica de periodico
     */
    private void vistaFichaTecnicaPeriodico() {
        textoLabel = datosFicha.getDatosLabels();
        componentesFicha_2(1);
        cargarLabels(4, 5, 3, 8);
        cargarTextFields(4, 5, 4, 8, 1);
    }

    /**
     * metodo que carga la ficha tecnica de mapa
     */
    private void vistaFichaTecnicaMapa() {
        textoLabel = datosFicha.getDatosLabels();
        componentesFicha_2(0);
        cargarLabels(1, 2, 4, 8);
        cargarTextArea(1, 5, 0, 4, 2);
    }

    /**
     * metodo que carga la ficha tecnica de revista
     */
    private void vistaFichaTecnicaRevista() {
        textoLabel = datosFicha.getDatosLabels();
        cargarLabels(0, 6, 0, 0);
        cargarLabels(2, 6, 3, 6);
        cargarTextFields(0, 2, 1, 0, 3);
        cargarTextFields(2, 6, 1, 2, 1);
        cargarTextFields(2, 6, 4, 6, 1);
    }

    /**
     * metodo que carga la ficha tecnica de diccionario
     */
    private void vistaFichaTecnicaDiccionario() {
        textoLabel = datosFicha.getDatosLabels();
        cargarLabels(0, 9, 0, 0);
        cargarLabels(1, 6, 2, 9);
        cargarLabels(7, 8, 2, 14);
        cargarLabels(1, 2, 4, 15);
        cargarLabels(5, 6, 4, 16);
        cargarTextFields(0, 1, 1, 0, 3);
        cargarTextFields(1, 9, 1, 1, 1);
        cargarTextFields(1, 5, 3, 9, 1);
        cargarTextArea(5, 3, 0, 2, 1);
        cargarTextArea(7, 3, 1, 2, 1);
        cargarTextArea(1, 5, 2, 4, 2);
        cargarTextArea(5, 5, 3, 4, 2);
    }

    /**
     * Metodo que carga los componentes base para la ficha de enciclopedia y
     * libro
     */
    private void componentesFicha_1() {
        cargarLabels(0, 10, 0, 0);
        cargarLabels(1, 7, 2, 10);
        cargarLabels(8, 9, 2, 16);
        cargarLabels(1, 2, 4, 17);
        cargarLabels(6, 7, 4, 18);
        cargarTextFields(0, 1, 1, 0, 3);
        cargarTextFields(1, 10, 1, 1, 1);
        cargarTextFields(1, 6, 3, 10, 1);
        cargarTextArea(6, 3, 0, 2, 1);
        cargarTextArea(8, 3, 1, 2, 1);
        cargarTextArea(1, 5, 2, 4, 3);
        cargarTextArea(6, 5, 3, 4, 3);
    }

    /**
     * Metodo que carga los componentes base parta la ficha de periodico y mapa
     *
     * @param columna
     */
    private void componentesFicha_2(int columna) {
        cargarLabels(0, 5, 0, 0);
        cargarLabels(1, 4, 2 + columna, 5);
        cargarTextFields(0, 1, 1, 0, 3);
        cargarTextFields(1, 5, 1, 1, 1);
        cargarTextFields(1, 4, 3 + columna, 5, 1);
    }

    /**
     * Metodo que carga los labels en pantalla
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
     * Metodo que obtiene una cadena<br>
     * de la cadena textoLabel y tambien la<br>
     * elimina
     *
     * @return
     */
    private String obtenerCadena() {
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
     * Metodo que carga los TextAreas en pantalla
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
     * Metodo que restringe la entrada de datos de los TextField
     */
    private void desabilitarEditableText(int pos,int pos1) {
        for(int i=0;i<pos;i++){
            arregloTextField[i].setEditable(false);
        }
        for(int i=0;i<pos1;i++){
            arregloTextArea[i].setEditable(false);
        }
    }

}

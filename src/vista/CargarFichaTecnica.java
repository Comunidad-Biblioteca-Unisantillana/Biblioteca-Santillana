/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import control.FichaTecnicaDiccionarioController;
import control.FichaTecnicaEnciclopediaController;
import control.FichaTecnicaLibroController;
import control.FichaTecnicaMapaController;
import control.FichaTecnicaPeriodicoController;
import control.FichaTecnicaRevistaController;
import control.QueryControl;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.GridPane;

/**
 * Clase que se encarga de cargar las fichas tecnicas a la interfaz
 *
 * @author julian
 */
public class CargarFichaTecnica {

    private FichaTecnicaLibroController ftlc;
    private FichaTecnicaEnciclopediaController ftec;
    private FichaTecnicaMapaController ftmc;
    private FichaTecnicaPeriodicoController ftpc;
    private FichaTecnicaRevistaController ftrc;
    private FichaTecnicaDiccionarioController ftdc;

    private Parent rootFTLibro;
    private Parent rootFTEnciclopedia;
    private Parent rootFTMapa;
    private Parent rootFTPeriodico;
    private Parent rootFTRevista;
    private Parent rootFTDiccionario;

    private String nombreFicha = "";

    /**
     * Contructor de la clase
     * @param nombreFicha 
     */
    public CargarFichaTecnica(String nombreFicha) {
        this.nombreFicha = nombreFicha.toLowerCase();
        try {
            switch (nombreFicha) {
                case "libro":
                    FXMLLoader loaderLibro = new FXMLLoader(getClass().getResource("/vista/FichaTecnicaLibro.fxml"));
                    rootFTLibro = loaderLibro.load();
                    ftlc = loaderLibro.getController();
                    break;
                case "enciclopedia":
                    FXMLLoader loaderEnciclopedia = new FXMLLoader(getClass().getResource("/vista/FichaTecnicaEnciclopedia.fxml"));
                    rootFTEnciclopedia = loaderEnciclopedia.load();
                    ftec = loaderEnciclopedia.getController();
                    break;
                case "periodico":
                    FXMLLoader loaderPeriodico = new FXMLLoader(getClass().getResource("/vista/FichaTecnicaPeriodico.fxml"));
                    rootFTPeriodico = loaderPeriodico.load();
                    ftpc = loaderPeriodico.getController();
                    break;
                case "mapa":
                    FXMLLoader loaderMapa = new FXMLLoader(getClass().getResource("/vista/FichaTecnicaMapa.fxml"));
                    rootFTMapa = loaderMapa.load();
                    ftmc = loaderMapa.getController();
                    break;
                case "revista":
                    FXMLLoader loaderRevista = new FXMLLoader(getClass().getResource("/vista/FichaTecnicaRevista.fxml"));
                    rootFTRevista = loaderRevista.load();
                    ftrc = loaderRevista.getController();
                    break;
                case "diccionario":
                    FXMLLoader loaderDiccionario = new FXMLLoader(getClass().getResource("/vista/FichaTecnicaDiccionario.fxml"));
                    rootFTDiccionario = loaderDiccionario.load();
                    ftdc = loaderDiccionario.getController();
                default:
                    System.out.println("Error al cargar ficha");
                    break;
            }

        } catch (IOException ex) {

        }
    }

    /**
     * Metodo que crea una ficha tecnica
     * @param panel
     * @param codBarras
     */
    public void crearFichaTecnica(GridPane panel, String codBarras) {
        try {
            if (nombreFicha.equalsIgnoreCase("libro")) {
                QueryControl.getInstance().consultarAutorRecurso(codBarras, nombreFicha);
                QueryControl.getInstance().consultarMaterias(codBarras, nombreFicha);
                anadirPanel(rootFTLibro, panel);
                ftlc.cargarDatos(codBarras);
            } else if (nombreFicha.equalsIgnoreCase("enciclopedia")) {

                QueryControl.getInstance().consultarAutorRecurso(codBarras, nombreFicha);
                QueryControl.getInstance().consultarMaterias(codBarras, nombreFicha);
                anadirPanel(rootFTEnciclopedia, panel);
                ftec.cargarDatos(codBarras);
            } else if (nombreFicha.equalsIgnoreCase("periodico")) {

                anadirPanel(rootFTPeriodico, panel);
                ftpc.cargarDatos(codBarras);
            } else if (nombreFicha.equalsIgnoreCase("mapa")) {

                anadirPanel(rootFTMapa, panel);
                ftmc.cargarDatos(codBarras);
            } else if (nombreFicha.equalsIgnoreCase("revista")) {

                QueryControl.getInstance().consultarMaterias(codBarras, nombreFicha);
                anadirPanel(rootFTRevista, panel);
                ftrc.cargarDatos(codBarras);
            } else if (nombreFicha.equalsIgnoreCase("diccionario")) {

                QueryControl.getInstance().consultarAutorRecurso(codBarras, nombreFicha);
                QueryControl.getInstance().consultarMaterias(codBarras, nombreFicha);
                anadirPanel(rootFTDiccionario, panel);
                ftdc.cargarDatos(codBarras);
            }
        } catch (Exception e) {
            System.out.println("Error al momento de cargar la ficha tecnica");
        }
    }

    /**
     * Metodo que agrega la ficha tecnica a un panel
     *
     * @param estadoPanel
     * @param root
     * @param panel
     * @param nombrePanel
     */
    private void anadirPanel(Parent root, GridPane panel) {
        panel.add(root, 0, 0);
        root.setVisible(true);
    }
    
    /**
     * Metodo que limpia los campos de la ficha tecnica
     */
    public void limpiarCamposTextos() {
        switch (nombreFicha.toLowerCase()) {
            case "libro":
                ftlc.limpiarCampos();
                break;
            case "enciclopedia":
                ftec.limpiarCampos();
                break;
            case "periodico":
                ftpc.limpiarCampos();
                break;
            case "mapa":
                ftpc.limpiarCampos();
                break;
            case "diccionario":
                ftdc.limpiarCampos();
                break;
            case "revista":
                ftrc.limpiarCampos();
                break;
        }
    }
}

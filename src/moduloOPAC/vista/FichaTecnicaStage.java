package moduloOPAC.vista;

import java.io.IOException;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import moduloOPAC.control.FichaTecnicaController;
import moduloOPAC.control.TablaOPACController;

/**
 * Stage donde se muestra la ficha tecnica de un recurso.
 *
 * @author Miguel Fernández
 * @creado: 20/08/2019
 * @autor Miguel Fernández
 * @modificación: 20/08/2019
 */
public class FichaTecnicaStage extends Stage {

    private FichaTecnicaController fichaTecnicaController;
    private TablaOPACController tablaOPACController;

    /**
     * Constructor Ficha Tecnica.
     */
    public FichaTecnicaStage(int alto, int ancho) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/moduloOPAC/vista/FichaTecnica.fxml"));
            Parent root = loader.load();
            fichaTecnicaController = loader.getController();

            Scene scene = new Scene(root);
            setScene(scene);
            setTitle("Ficha Técnica");
            getIcons().add(new Image("/recursos/iconUniversity.png"));
            centerOnScreen();
            setResizable(false);
            setHeight(alto);
            setWidth(ancho);
            setOnCloseRequest(new EventHandler<WindowEvent>() {
                public void handle(WindowEvent we) {
                    tablaOPACController.setEstadoVentanaFicha(false);
                }
            });
            show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * el metódo carga la ficha en el controlador de la ventana.
     *
     * @param gridPaneFicha
     */
    public void setGridPaneFicha(GridPane gridPaneFicha) {
        fichaTecnicaController.setGriddPaneFicha(gridPaneFicha);
    }

    /**
     * el metódo carga el controlador TablaOPACController en el controlador de
     * la ventana.
     *
     * @param tablaOPACController
     */
    public void setTablaOPACController(TablaOPACController tablaOPACController) {
        this.tablaOPACController = tablaOPACController;
    }

}

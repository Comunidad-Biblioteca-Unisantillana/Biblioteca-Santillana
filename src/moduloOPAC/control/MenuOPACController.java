package moduloOPAC.control;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;

/**
 * Clase que controla la vista MenuOPAC.fxml
 *
 * @author Miguel Fernández
 * @creado 21/08/2019
 * @author Miguel Fernández
 * @modificadi 21/08/2019
 */
public class MenuOPACController implements Initializable {

    private BorderPane rootModulo;
    @FXML
    private ImageView imgIconMenu;
    @FXML
    private JFXButton btnCodigo;
    @FXML
    private JFXButton btnAutor;
    @FXML
    private JFXButton btnPalabraClave;
    @FXML
    private JFXButton bntTituloYAutor;

    /**
     * el método que se ejecuta automáticamente al enlazar este controlador con
     * su respectiva vista.
     *
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        imgIconMenu.setImage(new Image("/recursos/iconOPAC.png"));
    }

    /**
     * el metódo cambia el tipo de busqueda a: busqueda por codigo.
     *
     * @param event
     */
    @FXML
    private void btnCodigoOPACPressed(ActionEvent event) {
        System.out.println("hola");
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/moduloOPAC/vista/CodigoOPAC.fxml"));
            rootModulo.setCenter(loader.load());

            CodigoOPACController codigoOPACController = loader.getController();
            codigoOPACController.cargarMenuOPAC(rootModulo);
        } catch (IOException ex) {

        }
    }

    /**
     * el metódo cambia el tipo de busqueda a: busqueda por autor.
     *
     * @param event
     */
    @FXML
    private void btnAutorOPACPressed(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/moduloOPAC/vista/AutorOPAC.fxml"));
            rootModulo.setCenter(loader.load());

            AutorOPACController autorOPACController = loader.getController();
            autorOPACController.cargarMenuOPAC(rootModulo);
        } catch (IOException ex) {

        }
    }

    /**
     * el metódo cambia el tipo de busqueda a: busqueda por palabra clave.
     *
     * @param event
     */
    @FXML
    private void btnPalabraClaveOPACPressed(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/moduloOPAC/vista/PalabraClaveOPAC.fxml"));
            rootModulo.setCenter(loader.load());

            PalabraClaveOPACController palabraClaveOPACController = loader.getController();
            palabraClaveOPACController.cargarMenuOPAC(rootModulo);
        } catch (IOException ex) {

        }
    }

    /**
     * el metódo cambia el tipo de busqueda a: busqueda por titulo y autor.
     *
     * @param event
     */
    @FXML
    private void btnTituloYAutorOPACPressed(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/moduloOPAC/vista/TituloYAutorOPAC.fxml"));
            rootModulo.setCenter(loader.load());

            TituloYAutorOPACController tituloYAutorOPACController = loader.getController();
            tituloYAutorOPACController.cargarMenuOPAC(rootModulo);
        } catch (IOException ex) {

        }
    }

    /**
     * el metódo recibe el panel de la vista estudiante, profesor o
     * bibliotecario.
     *
     * @param rootModulo
     */
    public void setBorderPane(BorderPane rootModulo) {
        this.rootModulo = rootModulo;
    }

    /**
     * el metódo inhabilita el botón que se haya seleccionado.
     *
     * @param busqueda
     */
    public void setDisableButton(String busqueda) {
        if (busqueda.equals("codigo")) {
            btnCodigo.setDisable(true);
        } else if (busqueda.equals("autor")) {
            btnAutor.setDisable(true);
        } else if (busqueda.equals("palabraClave")) {
            btnPalabraClave.setDisable(true);
        } else {
            bntTituloYAutor.setDisable(true);
        }
    }
    
}

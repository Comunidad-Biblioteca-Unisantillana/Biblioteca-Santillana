
package moduloMulta.vista;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import moduloMulta.control.CancelarMultaController;
import moduloMulta.entitys.Multa;

/**
 *
 * @author Storkolm
 */
public class CancelarMultaStage extends Stage{
    
    private CancelarMultaController control;

    public CancelarMultaStage() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/moduloMulta/vista/CancelarMulta.fxml"));
            setScene(new Scene(loader.load()));
            control = loader.getController();
            setTitle("Anular Multa");
            getIcons().add(new Image("/general/recursos/img/iconUniversity.png"));
            show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void cargarComponentes(String tipoUsuario,TableView<Multa> tableMulta){
        control.setTipoUsuario(tipoUsuario);
        control.setStage(this);
        control.setTableMulta(tableMulta);
    }
    
}

package vista;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.DialogPane;
import javafx.stage.Stage;

/**
 * Clase que crea una alerta.
 * @author Camilo
 */
public class AlertBox implements IAlertBox{
    private Alert alert;
	
    /**
    * Metodo que crea una ventana de información
     * @param cadena1
     * @param cadena2
     * @param cadena3
	 */
    @Override
    public void showAlert(String cadena1,String cadena2,String cadena3) {
        alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(cadena1);
        alert.setHeaderText(cadena2);
        alert.setContentText(cadena3);
        cargarComponentes();
        alert.showAndWait();
    }


    /**
     * Metodo que carga los componentes del Alert
     */
    private void cargarComponentes() {
        DialogPane ventana = alert.getDialogPane();
        ventana.getStylesheets().add(getClass().getResource("/recursos/stylesAlert.css").toExternalForm());
        ventana.getStyleClass().add("myDialog");

        //Cambia el icono de la ventana
        Stage stage = (Stage)alert.getDialogPane().getScene().getWindow();
        //stage.getIcons().add(new Image("file:imagenes/login.png"));

        //inserta la imagen 
        //ImageView imagen = new ImageView("file:imagenes/login.png");
        //imagen.setFitHeight(50);
        //imagen.setFitWidth(50);
        //ventana.setGraphic(imagen);
    }
	
}

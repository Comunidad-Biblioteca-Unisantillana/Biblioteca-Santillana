package moduloRenovacion.vista;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import moduloPrestamo.control.PrestamoEstudianteProfesorController;
import moduloPrestamo.modelo.Prestamo;
import moduloRenovacion.control.MensajeRenovarController;

/**
 * Stage donde se carga la vista MensajeRenovar.fxml.
 *
 * @author Miguel Fernández
 * @creado 25/08/2019
 * @autor Miguel Fernández
 * @modificado 25/08/2019
 */
public class MensajeRenovarStage extends Stage {

    private MensajeRenovarController mensajeRenovarController;
    private PrestamoEstudianteProfesorController prestEstProfController;

    /**
     * constructor de la clase sin parámetros.
     */
    public MensajeRenovarStage() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/moduloRenovacion/vista/MensajeRenovar.fxml"));
            Parent root = loader.load();
            mensajeRenovarController = loader.getController();

            Scene scene = new Scene(root);
            setScene(scene);
            setTitle("Renovar recurso");
            getIcons().add(new Image("/general/recursos/img/iconUniversity.png"));
            centerOnScreen();
            setResizable(false);

            setOnCloseRequest((WindowEvent we) -> {
                prestEstProfController.setEstadoRenovarRecurso(false);
            });

            show();
        } catch (IOException e) {
            System.err.println("Error al generar ventana FichaTecnicaStage");
        }
    }

    /**
     * el metódo carga en el controlador de la ventana el préstamo selecionado y
     * otros datos relevantes.
     *
     * @param prestamo
     * @param tipoUsuario
     * @param codUsuario
     * @param prestEstProfController
     */
    public void cargarDatosMensajeRenovarController(Prestamo prestamo, String codUsuario, String tipoUsuario,
            PrestamoEstudianteProfesorController prestEstProfController) {
        this.prestEstProfController = prestEstProfController;
        mensajeRenovarController.cargarDatos(prestamo, codUsuario, tipoUsuario, prestEstProfController);
    }

}

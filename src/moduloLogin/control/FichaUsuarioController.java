package moduloLogin.control;

import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import usuarios.entitys.Estudiante;
import usuarios.entitys.Profesor;

/**
 * la clase controla la vista FichaUsuario.fxml.
 *
 * @author Miguel Fernández
 * @creado: 08/09/2019
 * @author Miguel Fernández
 * @modificado: 08/09/2019
 */
public class FichaUsuarioController implements Initializable {

    @FXML
    private Label labelFicha;
    @FXML
    private Label labelCodigo;
    @FXML
    private Label labelPlan;
    @FXML
    private JFXTextField txtCodigo;
    @FXML
    private JFXTextField txtPlan;
    @FXML
    private JFXTextField txtIdentificacion;
    @FXML
    private JFXTextField txtTipoID;
    @FXML
    private JFXTextField txtNombres;
    @FXML
    private JFXTextField txtApellidos;
    @FXML
    private JFXTextField txtFechaNac;
    @FXML
    private JFXTextField txtEdad;
    @FXML
    private JFXTextField txtCiudad;
    @FXML
    private JFXTextField txtDireccion;
    @FXML
    private JFXTextField txtCorreo;
    @FXML
    private JFXTextField txtNacionalidad;
    private Estudiante estudiante;
    private Profesor profesor;

    /**
     * el metódo se ejecuta automáticamente al enlazar este controlador con su
     * respectiva vista.
     *
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    /**
     * el método carga la información del profeesor o estudiante.
     *
     * @param usuario
     */
    private void cargarDatosUsuario(String usuario) {
        DateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");

        if (usuario.equalsIgnoreCase("estudiante")) {
            txtCodigo.setText(estudiante.getCodestudiante());
            txtPlan.setText(estudiante.getPlan());
            txtIdentificacion.setText(estudiante.getIdentificacion());
            txtTipoID.setText(estudiante.getTipoid());
            txtNombres.setText(estudiante.getNombre());
            txtApellidos.setText(estudiante.getApellido());
            txtFechaNac.setText(formatoFecha.format(estudiante.getFechanacimiento()));
            txtEdad.setText(String.valueOf(estudiante.getEdad()));
            txtCiudad.setText(estudiante.getCiudadresidencia());
            txtDireccion.setText(estudiante.getDireccionresidencia());
            txtCorreo.setText(estudiante.getCorreoelectronico());
            txtNacionalidad.setText(estudiante.getNacionalidad());
        } else {
            txtIdentificacion.setText(profesor.getIdprofesor());
            txtTipoID.setText(profesor.getTipoid());
            txtNombres.setText(profesor.getNombres());
            txtApellidos.setText(profesor.getApellidos());
            txtFechaNac.setText(formatoFecha.format(profesor.getFechanacimiento()));
            txtEdad.setText(String.valueOf(profesor.getEdad()));
            txtCiudad.setText(profesor.getCiudadresidencia());
            txtDireccion.setText(profesor.getDireccionresidencia());
            txtCorreo.setText(profesor.getCorreoelectronico());
            txtNacionalidad.setText(profesor.getNacionalidad());
        }
    }

    /**
     * el método oculta o muestra unos botones den la interfaz dependiendo del
     * usuario.
     *
     * @param usuario
     */
    private void mostrarOcultarBotones(String usuario) {
        if (usuario.equalsIgnoreCase("estudiante")) {
            labelFicha.setText("Datos del Estudiante");
            labelCodigo.setVisible(true);
            labelPlan.setVisible(true);
            txtCodigo.setVisible(true);
            txtPlan.setVisible(true);
        } else {
            labelFicha.setText("Datos del Profesor");
            labelCodigo.setVisible(false);
            labelPlan.setVisible(false);
            txtCodigo.setVisible(false);
            txtPlan.setVisible(false);
        }
    }

    /**
     * el método recibe un objeto de tipo estudiante o profesor con la
     * información de este.
     *
     * @param usuario
     */
    public void usuario(Object usuario) {
        if (usuario instanceof Estudiante) {
            estudiante = (Estudiante) usuario;
            mostrarOcultarBotones("estudiante");
            cargarDatosUsuario("estudiante");
        } else {
            profesor = (Profesor) usuario;
            mostrarOcultarBotones("profesor");
            cargarDatosUsuario("profesor");
        }
    }

}

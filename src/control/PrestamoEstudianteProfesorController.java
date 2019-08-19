/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import com.jfoenix.controls.JFXRadioButton;
import java.net.URL;
import moduloPrestamo.Prestamo;
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import moduloPrestamo.ConsultaPrestamoEst;
import moduloPrestamo.ConsultaPrestamoProf;
import moduloPrestamo.IConsultarPrestamo;

/**
 * Clase que controla el panel prestamo del estudiante
 *
 * @author Julian
 */
public class PrestamoEstudianteProfesorController implements Initializable {

    @FXML
    private TableView<Prestamo> tablePrestamo;
    @FXML
    private TableColumn<Prestamo, Integer> codPrestamosTable;
    @FXML
    private TableColumn<Prestamo, Date> fechaPrestamoTable;
    @FXML
    private TableColumn<Prestamo, Date> fechaDevolucionTable;
    @FXML
    private TableColumn<Prestamo, String> tituloTable;
    @FXML
    private JFXRadioButton radioPrestActual;
    @FXML
    private JFXRadioButton radioHistorialPrest;

    private ToggleGroup togleRadio;

    private String codUsuario;

    private IConsultarPrestamo consulta;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        radioHistorialPrest.setSelected(true);

        togleRadio = new ToggleGroup();
        radioHistorialPrest.setToggleGroup(togleRadio);
        radioPrestActual.setToggleGroup(togleRadio);
        radioHistorialPrest.setSelectedColor(Color.CORNFLOWERBLUE);
        radioPrestActual.setSelectedColor(Color.CORNFLOWERBLUE);
    }

    @FXML
    private void btnHistorialPrestamos(ActionEvent event) {
        if(event.getSource().equals(radioHistorialPrest)){
            tablePrestamo.setItems(consulta.getHistorialPrestamos(codUsuario));
        }else if(event.getSource().equals(radioPrestActual)){
            tablePrestamo.setItems(consulta.getPrestamosActuales(codUsuario));
        }
    }

    public void cargarDatosUsuario(String codUsuario, String tipoUsuario) {
        this.codUsuario = codUsuario;
        if (tipoUsuario.toLowerCase().equals("profesor")) {
            consulta = new ConsultaPrestamoProf();
        } else if (tipoUsuario.toLowerCase().equals("estudiante")) {
            consulta = new ConsultaPrestamoEst();
        }
        codPrestamosTable.setCellValueFactory(new PropertyValueFactory<>("codBarrasRecurso"));
        tituloTable.setCellValueFactory(new PropertyValueFactory<>("tituloRecurso"));
        fechaPrestamoTable.setCellValueFactory(new PropertyValueFactory<>("fechaPrestamo"));
        fechaDevolucionTable.setCellValueFactory(new PropertyValueFactory<>("fechaDevolucion"));
        tablePrestamo.setItems(consulta.getHistorialPrestamos(codUsuario));
    }

}

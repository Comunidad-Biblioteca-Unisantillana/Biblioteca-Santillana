/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import entitys.Prestamo;
import java.sql.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import moduloPrestamo.ConsultaPrestamo;

/**
 * Clase que controla el panel prestamo del estudiante
 *
 * @author Julian
 */
public class PrestamoEstudianteProfesorController {

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

    /**
     * Metodo que carga la tabla con los datos de los <br>
     * prestamos del estudiante o profesor
     * @param codEstudiante
     * @param tipoUsuario
     */
    public void cargarDatosTablePrestamos(String codEstudiante,String tipoUsuario) {
        if(tipoUsuario.equals("profesor"))return;
        try {
            ConsultaPrestamo consulta = new ConsultaPrestamo();

            codPrestamosTable.setCellValueFactory(new PropertyValueFactory<>("codPrestamo"));
            tituloTable.setCellValueFactory(new PropertyValueFactory<>("titulo"));
            fechaPrestamoTable.setCellValueFactory(new PropertyValueFactory<>("fechaPrestamo"));
            fechaDevolucionTable.setCellValueFactory(new PropertyValueFactory<>("fechaDevolucion"));
            tablePrestamo.setItems(consulta.getPrestamos(codEstudiante));
        } catch (Exception ex) {
            Logger.getLogger(CuentaEstudianteProfesorController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

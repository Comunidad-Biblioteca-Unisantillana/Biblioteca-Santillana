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
import moduloPrestamo.ConsultaPrestamoEst;
import moduloPrestamo.ConsultaPrestamoProf;
import moduloPrestamo.IConsultarPrestamo;

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
    @FXML
    private TableColumn<Prestamo, Character> devueltoTable;

    /**
     * Metodo que carga la tabla con los datos de los <br>
     * prestamos del estudiante o profesor
     *
     * @param codUsuario
     * @param tipoUsuario
     */
    public void cargarDatosTablePrestamos(String codUsuario, String tipoUsuario) {
        try {
            IConsultarPrestamo consulta = null;
            if (tipoUsuario.toLowerCase().equals("profesor")) {
                consulta = new ConsultaPrestamoProf();
            } else if (tipoUsuario.toLowerCase().equals("estudiante")) {
                consulta = new ConsultaPrestamoEst();
            }
            codPrestamosTable.setCellValueFactory(new PropertyValueFactory<>("codBarrasRecurso"));
            tituloTable.setCellValueFactory(new PropertyValueFactory<>("tituloRecurso"));
            fechaPrestamoTable.setCellValueFactory(new PropertyValueFactory<>("fechaPrestamo"));
            fechaDevolucionTable.setCellValueFactory(new PropertyValueFactory<>("fechaDevolucion"));
            devueltoTable.setCellValueFactory(new PropertyValueFactory<>("devuelto"));
            tablePrestamo.setItems(consulta.getHistorialPrestamos(codUsuario));
        } catch (Exception e) {
        }
    }
}


package moduloMulta.control;

import usuarios.control.CuentaEstudianteProfesorController;
import moduloMulta.entitys.Multa;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import moduloMulta.modelo.ConsultaMultaAbs;
import moduloMulta.modelo.ConsultaMultaEst;
import moduloMulta.modelo.ConsultaMultaProf;

/**
 * clase que controla el panel multa del estudiante
 *
 * @author Julian
 */
public class MultaEstudianteProfesorController {

    @FXML
    private TableView<Multa> tableMulta;
    @FXML
    private TableColumn<Multa, Integer> colCodMulta;
    @FXML
    private TableColumn<Multa, String> colCodBarra;
    @FXML
    private TableColumn<Multa, String> tituloRecurso;
    @FXML
    private TableColumn<Multa, String> colTipo;
    @FXML
    private TableColumn<Multa, Integer> colDiasAtrasados;
    @FXML
    private TableColumn<Multa, Integer> colValorTot;
    @FXML
    private TableColumn<Multa, String> colCancelado;
    

    /**
     * Metodo que carga los datos de las multas de un estudiante o profesor
     *
     * @param codUsuario
     * @param tipoUsuario
     */
    public void cargarDatosTableMultas(String codUsuario,String tipoUsuario)  {
        ConsultaMultaAbs consultas;
        if(tipoUsuario.equals("profesor")){
            consultas = new ConsultaMultaProf();
        }else{
            consultas = new ConsultaMultaEst();
        }
        try {
            colCodMulta.setCellValueFactory(new PropertyValueFactory<>("codMulta"));
            colCodBarra.setCellValueFactory(new PropertyValueFactory<>("codBarrasRecurso"));
            tituloRecurso.setCellValueFactory(new PropertyValueFactory<>("tituloRecurso"));
            colTipo.setCellValueFactory(new PropertyValueFactory<>("tipoRecurso"));
            colDiasAtrasados.setCellValueFactory(new PropertyValueFactory<>("diasAtrasados"));
            colValorTot.setCellValueFactory(new PropertyValueFactory<>("valorTotal"));
            colCancelado.setCellValueFactory(new PropertyValueFactory<>("candelado"));
            
            tableMulta.setItems(consultas.getMultasUsuario(codUsuario));
        } catch (Exception ex) {
            Logger.getLogger(CuentaEstudianteProfesorController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

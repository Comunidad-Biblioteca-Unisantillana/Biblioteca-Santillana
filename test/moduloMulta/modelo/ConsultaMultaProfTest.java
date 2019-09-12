package moduloMulta.modelo;

import javafx.collections.ObservableList;
import moduloMulta.entitys.Multa;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Stiven
 */
public class ConsultaMultaProfTest {
    
    public ConsultaMultaProfTest() {
    }
    
    /**
     * Test que verifica todas las multas de todos los estudiantes.
     */
    @Test
    public void testGetMultasAll() {
        System.out.println("Ejecutando test multas estudiantes");
        ConsultaMultaProf instance = new ConsultaMultaProf();
        ObservableList<Multa> multas = instance.getMultasAll();
        boolean resultado = true;
        if(multas.isEmpty()){
            resultado = false;
        }
        assertEquals(true, resultado);
    }
    /**
     * Test que verifica todas las multas de un profesor.
     */
    @Test
    public void testGetMultasUsuario() {
        System.out.println("Ejecutando test multas profesor");
        String idUsuario = "946789878";
        ConsultaMultaProf instance = new ConsultaMultaProf();
        ObservableList<Multa> multas = instance.getMultasUsuario(idUsuario);
        boolean resultado = true;
        if(multas.isEmpty()){
            resultado = false;
        }
        assertEquals(true, resultado);
    }
    
}

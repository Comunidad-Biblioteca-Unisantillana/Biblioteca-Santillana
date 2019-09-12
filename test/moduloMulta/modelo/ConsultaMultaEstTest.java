/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moduloMulta.modelo;

import javafx.collections.ObservableList;
import moduloMulta.entitys.Multa;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Stiven
 */
public class ConsultaMultaEstTest {
    
    public ConsultaMultaEstTest() {
    }
    /**
     * Test que verifica todas las multas de todos los estudiantes.
     */
    @Test
    public void testGetMultasAll() {
        System.out.println("Ejecutando test multas de estudiantes");
        ConsultaMultaEst instance = new ConsultaMultaEst();
        ObservableList<Multa> multas = instance.getMultasAll();
        boolean resultado = true;
        if(multas.isEmpty()){
            resultado = false;
        }
        assertEquals(true, resultado);
    }
    /**
     * Método que verifica todas las multas de un estudiante.
     */
    @Test
    public void testGetMultasUsuario() {
        System.out.println("Ejecutando test multas estudiante");
        String codUsuario = "1760158";
        ConsultaMultaEst instance = new ConsultaMultaEst();
        ObservableList<Multa> multas = instance.getMultasUsuario(codUsuario);
        boolean resultado = true;
        if(multas.isEmpty()){
            resultado = false;
        }
        assertEquals(true, resultado);

    }
    
}

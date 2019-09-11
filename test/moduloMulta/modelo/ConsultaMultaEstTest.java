/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moduloMulta.modelo;

import javafx.collections.ObservableList;
import moduloMulta.entitys.Multa;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Stiven
 */
public class ConsultaMultaEstTest {
    
    public ConsultaMultaEstTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    
    /**
     * Método que verifica todas las multas de todos los estudiantes.
     */
    @Test
    public void testGetMultasAll() {
        System.out.println("Ejecutando test multas de estudiantes");
        ConsultaMultaEst instance = new ConsultaMultaEst();
        ObservableList<Multa> expResult = null;
        ObservableList<Multa> result = instance.getMultasAll();
        assertEquals(expResult, result);
    }
    /**
     * Método que verifica todas las multas de un estudiante.
     */
    @Test
    public void testGetMultasUsuario() {
        System.out.println("Ejecutando test multas estudiante");
        String codUsuario = "1760161";
        ConsultaMultaEst instance = new ConsultaMultaEst();
        ObservableList<Multa> expResult = null;
        ObservableList<Multa> result = instance.getMultasUsuario(codUsuario);
        assertEquals(expResult, result);

    }
    
}

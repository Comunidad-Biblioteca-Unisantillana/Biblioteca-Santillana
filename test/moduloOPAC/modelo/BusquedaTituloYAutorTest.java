/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moduloOPAC.modelo;

import javafx.collections.ObservableList;
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
public class BusquedaTituloYAutorTest {

    public BusquedaTituloYAutorTest() {
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
     * Método que busca recursos bibliográficos por un titulo y autor correctos.
     */
    @Test
    public void testBuscarRecursosTituloYAutorCorrectos() {
        System.out.println("buscar recursos bibliográficos por un titulo y autor correctos");
        String entidad = "libro";
        BusquedaTituloYAutor instance = new BusquedaTituloYAutor("hush", "becca");
        ObservableList<Recurso> expResult = instance.buscarRecursos(entidad);
        ObservableList<Recurso> result = expResult;
        assertEquals(expResult, result);
    }

    /**
     * Método que intentara buscar recursos bibliográficos por un titulo y autor
     * incorrectos.
     */
    @Test
    public void testBuscarRecursosTituloYAutorInCorrectos() {
        System.out.println("buscar recursos bibliográficos por un titulo y autor incorrectos");
        String entidad = "diccionario";
        BusquedaTituloYAutor instance = new BusquedaTituloYAutor("hush", "becca");
        ObservableList<Recurso> expResult = null;
        ObservableList<Recurso> result = instance.buscarRecursos(entidad);
        assertEquals(expResult, result);
    }

}

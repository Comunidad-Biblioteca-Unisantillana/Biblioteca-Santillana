/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moduloRenovacion.fabrica;

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
public class RenovacionColgenProfFabTest {

    public RenovacionColgenProfFabTest() {
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
     * Método que se encarga de renovar un libro de la colección general.
     */
    @Test
    public void testEjecutarRenovacion() {
        System.out.println("Ejecutar renovacion");
        String codBarras = "582292";
        String idUsuario = "946789878";
        RenovacionColgenProfFab instance = new RenovacionColgenProfFab();
        boolean expResult = true;
        boolean result = instance.ejecutarRenovacion(codBarras, idUsuario);
        assertEquals(expResult, result);
    }

    /**
     * Método que se encarga de renovar un libro de la colección reserva.
     */
    @Test
    public void testEjecutarRenovacionErronea() {
        System.out.println("Ejecutar renovacion erronea");
        String codBarras = "582288";
        String idUsuario = "946789878";
        RenovacionColgenProfFab instance = new RenovacionColgenProfFab();
        boolean expResult = false;
        boolean result = instance.ejecutarRenovacion(codBarras, idUsuario);
        assertEquals(expResult, result);
    }

}

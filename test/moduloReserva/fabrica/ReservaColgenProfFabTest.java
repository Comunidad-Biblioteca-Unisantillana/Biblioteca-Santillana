/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moduloReserva.fabrica;

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
public class ReservaColgenProfFabTest {

    public ReservaColgenProfFabTest() {
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
     * Método para ejecutar una reserva de un libro de la colección general a un
     * profesor.
     */
    @Test
    public void testEjecutarReserva() {
        System.out.println("Ejecutar reserva correcta profesor");
        String codBarras = "582279";
        String codUsuario = "90856987";
        String idBibliotecario = "1102515566";
        ReservaColgenProfFab instance = new ReservaColgenProfFab();
        boolean expResult = true;
        boolean result = instance.ejecutarReserva(codBarras, codUsuario, idBibliotecario);
        assertEquals(expResult, result);
    }

    /**
     * Método que intenta ejecutar una reserva de un libro de la colección
     * reserva a un profesor.
     */
    @Test
    public void testEjecutarReservaErronea() {
        System.out.println("Ejecutar reserva incorrecta profesor");
        String codBarras = "472601";
        String codUsuario = "90856987";
        String idBibliotecario = "1102515566";
        ReservaColgenProfFab instance = new ReservaColgenProfFab();
        boolean expResult = false;
        boolean result = instance.ejecutarReserva(codBarras, codUsuario, idBibliotecario);
        assertEquals(expResult, result);
    }

}

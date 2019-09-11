/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moduloRenovacion.fabrica;

import java.util.logging.Level;
import java.util.logging.Logger;
import moduloPrestamo.DAO.PrestamoLibroDAOProf;
import moduloPrestamo.modelo.GeneradorPrestamo;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Stiven
 */
public class RenovacionColgenProfFabTest {

    public RenovacionColgenProfFabTest() {
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

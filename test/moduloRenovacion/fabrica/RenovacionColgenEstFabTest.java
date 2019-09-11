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
public class RenovacionColgenEstFabTest {
    
    public RenovacionColgenEstFabTest() {
    }
    
    /**
     * Método que se encarga de renovar un libro de la colección general.
     */
    @Test
    public void testEjecutarRenovacion() {
        System.out.println("Ejecutar renovacion");
        String codBarras = "582293";
        String idUsuario = "1760204";
        RenovacionColgenEstFab instance = new RenovacionColgenEstFab();
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
        String codBarras = "466875";
        String idUsuario = "1760204";
        RenovacionColgenEstFab instance = new RenovacionColgenEstFab();
        boolean expResult = false;
        boolean result = instance.ejecutarRenovacion(codBarras, idUsuario);
        assertEquals(expResult, result);
    }
    
}

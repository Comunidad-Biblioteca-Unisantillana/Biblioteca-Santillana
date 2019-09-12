/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moduloOPAC.modelo;

import org.junit.Test;
import static org.junit.Assert.*;
import recursos.entitys.Diccionario;
import recursos.entitys.Enciclopedia;
import recursos.entitys.Libro;
import recursos.entitys.Mapa;
import recursos.entitys.Periodico;
import recursos.entitys.Revista;

/**
 *
 * @author Stiven
 */
public class BusquedaCodigoBarrasTest {

    public BusquedaCodigoBarrasTest() {
    }

    /**
     * Test que se encarga de buscar el nombre de una entidad ingresando un código de barras correcto.
     */
    @Test
    public void testBuscarEntidadCorrecta() {
        System.out.println("buscar el nombre de una entidad ingresando un código de barras correcto");
        String codBarras = "472601";
        BusquedaCodigoBarras instance = new BusquedaCodigoBarras();
        String expResult = "libro";

        String result = instance.buscarEntidad(codBarras);
        assertEquals(expResult, result);
    }

    /**
     * Test que se encarga de buscar el nombre de una entidad ingresando un código de barras erroneo.
     */
    @Test
    public void testBuscarEntidadErronea() {
        System.out.println("buscar el nombre de una entidad ingresando un código de barras erroneo");
        String codBarras = "66545rfuvbi909";
        BusquedaCodigoBarras instance = new BusquedaCodigoBarras();
        String expResult = "";

        String result = instance.buscarEntidad(codBarras);
        assertEquals(expResult, result);
    }

    /**
     * Test que se encarga de buscar un libro ingresando un código de barras correcto.
     */
    @Test
    public void testBuscarLibroPorCodigoCorrecto() {
        System.out.println("buscar un libro ingresando un código de barras correcto");
        String codBarras = "472601";
        BusquedaCodigoBarras instance = new BusquedaCodigoBarras();
        Libro expResult = instance.buscarLibroPorCodigo(codBarras);
        Libro result = instance.buscarLibroPorCodigo(codBarras);
        assertEquals(expResult, result);
    }

    /**
     * Test que se encarga de buscar un libro ingresando un código de barras erroneo.
     */
    @Test
    public void testBuscarLibroPorCodigoErroneo() {
        System.out.println("buscar un libro ingresando un código de barras erroneo");
        String codBarras = "idoi2ndoi2n1";
        BusquedaCodigoBarras instance = new BusquedaCodigoBarras();
        Libro expResult = null;
        Libro result = instance.buscarLibroPorCodigo(codBarras);
        assertEquals(expResult, result);
    }

    /**
     * Test que se encarga de buscar una enciclopedia ingresando un código de barras correcto.
     */
    @Test
    public void testBuscarEnciclopediaPorCodigoCorrecto() {
        System.out.println("buscar una enciclopedia ingresando un código de barras correcto");
        String codBarras = "484963";
        BusquedaCodigoBarras instance = new BusquedaCodigoBarras();
        Enciclopedia expResult = instance.buscarEnciclopediaPorCodigo(codBarras);
        Enciclopedia result = instance.buscarEnciclopediaPorCodigo(codBarras);
        assertEquals(expResult, result);
    }

    /**
     * Test que se encarga de buscar una enciclopedia ingresando un código de barras erroneo.
     */
    @Test
    public void testBuscarEnciclopediaPorCodigoErroneo() {
        System.out.println("buscar una enciclopedia ingresando un código de barras erroneo");
        String codBarras = "dwmdpo2d02j2odjpow";
        BusquedaCodigoBarras instance = new BusquedaCodigoBarras();
        Enciclopedia expResult = null;
        Enciclopedia result = instance.buscarEnciclopediaPorCodigo(codBarras);
        assertEquals(expResult, result);
    }

    /**
     * Test que se encarga de buscar un diccionario ingresando un código de barras correcto.
     */
    @Test
    public void testBuscarDiccionarioPorCodigoCorrecto() {
        System.out.println("buscar un diccionario ingresando un código de barras correcto");
        String codBarras = "254916";
        BusquedaCodigoBarras instance = new BusquedaCodigoBarras();
        Diccionario expResult = instance.buscarDiccionarioPorCodigo(codBarras);
        Diccionario result = instance.buscarDiccionarioPorCodigo(codBarras);
        assertEquals(expResult, result);
    }

    /**
     * Test que se encarga de buscar un diccionario ingresando un código de barras erroneo.
     */
    @Test
    public void testBuscarDiccionarioPorCodigoErroneo() {
        System.out.println("buscar un diccionario ingresando un código de barras erroneo");
        String codBarras = "d2jdoinx9dnoincw";
        BusquedaCodigoBarras instance = new BusquedaCodigoBarras();
        Diccionario expResult = null;
        Diccionario result = instance.buscarDiccionarioPorCodigo(codBarras);
        assertEquals(expResult, result);
    }

    /**
     * Test que se encarga de buscar una revista ingresando un código de barras correcto.
     */
    @Test
    public void testBuscarRevistaPorCodigoCorrecto() {
        System.out.println("buscar una revista ingresando un código de barras correcto");
        String codBarras = "421257";
        BusquedaCodigoBarras instance = new BusquedaCodigoBarras();
        Revista expResult = instance.buscarRevistaPorCodigo(codBarras);
        Revista result = instance.buscarRevistaPorCodigo(codBarras);
        assertEquals(expResult, result);

    }

    /**
     * Test que se encarga de buscar una revista ingresando un código de barras erroneo.
     */
    @Test
    public void testBuscarRevistaPorCodigoErroneo() {
        System.out.println("buscar una revista ingresando un código de barras erroneo");
        String codBarras = "ncskncsnacpnc";
        BusquedaCodigoBarras instance = new BusquedaCodigoBarras();
        Revista expResult = null;
        Revista result = instance.buscarRevistaPorCodigo(codBarras);
        assertEquals(expResult, result);

    }

    /**
     * Test que se encarga de buscar un periodico ingresando un código de barras correcto.
     */
    @Test
    public void testBuscarPeriodicoPorCodigoCorrecto() {
        System.out.println("buscar un periodico ingresando un código de barras correcto");
        String codBarras = "158570";
        BusquedaCodigoBarras instance = new BusquedaCodigoBarras();
        Periodico expResult = instance.buscarPeriodicoPorCodigo(codBarras);
        Periodico result = instance.buscarPeriodicoPorCodigo(codBarras);
        assertEquals(expResult, result);

    }

    /**
     * Test que se encarga de buscar un periodico ingresando un código de barras erroneo.
     */
    @Test
    public void testBuscarPeriodicoPorCodigoErroneo() {
        System.out.println("buscar un periodico ingresando un código de barras erroneo");
        String codBarras = "1223456677";
        BusquedaCodigoBarras instance = new BusquedaCodigoBarras();
        Periodico expResult = null;
        Periodico result = instance.buscarPeriodicoPorCodigo(codBarras);
        assertEquals(expResult, result);

    }

    /**
     * Test que se encarga de buscar un mapa ingresando un código de barras correcto.
     */
    @Test
    public void testBuscarMapaPorCodigoCorrecto() {
        System.out.println("buscar un mapa ingresando un código de barras correcto");
        String codBarras = "459858";
        BusquedaCodigoBarras instance = new BusquedaCodigoBarras();
        Mapa expResult = instance.buscarMapaPorCodigo(codBarras);
        Mapa result = instance.buscarMapaPorCodigo(codBarras);
        assertEquals(expResult, result);
    }

    /**
     * Test que se encarga de buscar un mapa ingresando un código de barras erroneo.
     */
    @Test
    public void testBuscarMapaPorCodigoErroneo() {
        System.out.println("buscar un mapa ingresando un código de barras erroneo");
        String codBarras = "ii2dk2kcjck";
        BusquedaCodigoBarras instance = new BusquedaCodigoBarras();
        Mapa expResult = null;
        Mapa result = instance.buscarMapaPorCodigo(codBarras);
        assertEquals(expResult, result);
    }

}

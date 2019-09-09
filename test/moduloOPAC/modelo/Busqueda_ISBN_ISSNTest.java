/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moduloOPAC.modelo;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
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
public class Busqueda_ISBN_ISSNTest {

    public Busqueda_ISBN_ISSNTest() {
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
     * Test que se encarga de buscar una entidad por un código ISSN correcto.
     */
    @Test
    public void testBuscarEntidadIssnCorrecto() {
        System.out.println("buscar una entidad por un código ISSN correcto");
        String isbn_issn = "15197080";
        Busqueda_ISBN_ISSN instance = new Busqueda_ISBN_ISSN();
        String expResult = "revista";
        String result = instance.buscarEntidad(isbn_issn);
        assertEquals(expResult, result);
    }

    /**
     * Test que se encarga de buscar una entidad por un código ISBN correcto.
     */
    @Test
    public void testBuscarEntidadIsbnCorrecto() {
        System.out.println("buscar una entidad por un código ISBN correcto");
        String isbn_issn = "9752214522026";
        Busqueda_ISBN_ISSN instance = new Busqueda_ISBN_ISSN();
        String expResult = "mapa";
        String result = instance.buscarEntidad(isbn_issn);
        assertEquals(expResult, result);
    }

    /**
     * Test que se encarga de buscar una entidad por un código incorrecto.
     */
    @Test
    public void testBuscarEntidadCodigoErroneo() {
        System.out.println("buscar una entidad por un código incorrecto");
        String isbn_issn = "dmkfn3ohnf34044";
        Busqueda_ISBN_ISSN instance = new Busqueda_ISBN_ISSN();
        String expResult = "";
        String result = instance.buscarEntidad(isbn_issn);
        assertEquals(expResult, result);
    }

    /**
     * Test que se encarga de buscar un libro por un código ISBN correcto.
     */
    @Test
    public void testBuscarLibroPorCodigo() {
        System.out.println("buscar un libro por un código ISBN correcto");
        String isbn = "9788466646390";
        Busqueda_ISBN_ISSN instance = new Busqueda_ISBN_ISSN();
        Libro expResult = instance.buscarLibroPorCodigo(isbn);
        Libro result = instance.buscarLibroPorCodigo(isbn);
        assertEquals(expResult, result);
    }

    /**
     * Test que se encarga de buscar un libro por un código incorrecto.
     */
    @Test
    public void testBuscarLibroPorCodigoErroneo() {
        System.out.println("buscar un libro por un código incorrecto");
        String isbn = "d2cxf243fer4g45552";
        Busqueda_ISBN_ISSN instance = new Busqueda_ISBN_ISSN();
        Libro expResult = null;
        Libro result = instance.buscarLibroPorCodigo(isbn);
        assertEquals(expResult, result);
    }

    /**
     * Test que se encarga de buscar una enciclopedia por un código ISBN.
     * correcto.
     */
    @Test
    public void testBuscarEnciclopediaPorCodigo() {
        System.out.println("buscar una enciclopedia por un código ISBN");
        String isbn = "8451202653";
        Busqueda_ISBN_ISSN instance = new Busqueda_ISBN_ISSN();
        Enciclopedia expResult = instance.buscarEnciclopediaPorCodigo(isbn);
        Enciclopedia result = instance.buscarEnciclopediaPorCodigo(isbn);
        assertEquals(expResult, result);
    }

    /**
     * Test que se encarga de buscar una enciclopedia por un código incorrecto.
     */
    @Test
    public void testBuscarEnciclopediaPorCodigoErroneo() {
        System.out.println("buscar una enciclopedia por un código incorrecto");
        String isbn = "cdnqiondcoin2fc2pfc2";
        Busqueda_ISBN_ISSN instance = new Busqueda_ISBN_ISSN();
        Enciclopedia expResult = null;
        Enciclopedia result = instance.buscarEnciclopediaPorCodigo(isbn);
        assertEquals(expResult, result);
    }

    /**
     * Test que se encarga de buscar un diccionario por un código ISBN correcto.
     */
    @Test
    public void testBuscarDiccionarioPorCodigo() {
        System.out.println("buscar un diccionario por un código ISBN correcto");
        String isbn = "9582661652";
        Busqueda_ISBN_ISSN instance = new Busqueda_ISBN_ISSN();
        Diccionario expResult = instance.buscarDiccionarioPorCodigo(isbn);
        Diccionario result = instance.buscarDiccionarioPorCodigo(isbn);
        assertEquals(expResult, result);
    }

    /**
     * Test que se encarga de buscar un diccionario por un código incorrecto.
     */
    @Test
    public void testBuscarDiccionarioPorCodigoErroneo() {
        System.out.println("buscar un diccionario por un código incorrecto");
        String isbn = "xowqindcoi2nfpcnweofb2";
        Busqueda_ISBN_ISSN instance = new Busqueda_ISBN_ISSN();
        Diccionario expResult = null;
        Diccionario result = instance.buscarDiccionarioPorCodigo(isbn);
        assertEquals(expResult, result);
    }

    /**
     * Test que se encarga de buscar una revista por un código ISSN correcto.
     */
    @Test
    public void testBuscarRevistaPorCodigo() {
        System.out.println("buscar una revista por un código ISSN correcto");
        String issn = "15197081";
        Busqueda_ISBN_ISSN instance = new Busqueda_ISBN_ISSN();
        Revista expResult = instance.buscarRevistaPorCodigo(issn);
        Revista result = instance.buscarRevistaPorCodigo(issn);
        assertEquals(expResult, result);
    }

    /**
     * Test que se encarga de buscar una revista por un código incorrecto.
     */
    @Test
    public void testBuscarRevistaPorCodigoErroneo() {
        System.out.println("buscar una revista por un código incorrecto");
        String issn = "qncoqi2ncp2nofb2p";
        Busqueda_ISBN_ISSN instance = new Busqueda_ISBN_ISSN();
        Revista expResult = null;
        Revista result = instance.buscarRevistaPorCodigo(issn);
        assertEquals(expResult, result);
    }

    /**
     * Test que se encarga de buscar un periódico por un código ISSN correcto.
     */
    @Test
    public void testBuscarPeriodicoPorCodigo() {
        System.out.println("buscar un periódico por un código ISSN correcto");
        String issn = "15197327";
        Busqueda_ISBN_ISSN instance = new Busqueda_ISBN_ISSN();
        Periodico expResult = instance.buscarPeriodicoPorCodigo(issn);
        Periodico result = instance.buscarPeriodicoPorCodigo(issn);
        assertEquals(expResult, result);
    }

    /**
     * Test que se encarga de buscar un periódico por un código incorrecto.
     */
    @Test
    public void testBuscarPeriodicoPorCodigoIncorrecto() {
        System.out.println("buscar un periódico por un código incorrecto");
        String issn = "dnxqpwndcpqin2";
        Busqueda_ISBN_ISSN instance = new Busqueda_ISBN_ISSN();
        Periodico expResult = null;
        Periodico result = instance.buscarPeriodicoPorCodigo(issn);
        assertEquals(expResult, result);
    }

    /**
     * Test que se encarga de buscar un mapa por un código ISBN correcto.
     */
    @Test
    public void testBuscarMapaPorCodigo() {
        System.out.println("buscar un mapa por un código ISBN correcto");
        String isbn = "9752214522025";
        Busqueda_ISBN_ISSN instance = new Busqueda_ISBN_ISSN();
        Mapa expResult = instance.buscarMapaPorCodigo(isbn);
        Mapa result = instance.buscarMapaPorCodigo(isbn);
        assertEquals(expResult, result);
    }

    /**
     * Test que se encarga de buscar un mapa por un código incorrecto.
     */
    @Test
    public void testBuscarMapaPorCodigoErroneo() {
        System.out.println("buscar un mapa por un código incorrecto");
        String isbn = "dnndiop12mdp12qe2od2";
        Busqueda_ISBN_ISSN instance = new Busqueda_ISBN_ISSN();
        Mapa expResult = null;
        Mapa result = instance.buscarMapaPorCodigo(isbn);
        assertEquals(expResult, result);
    }

}

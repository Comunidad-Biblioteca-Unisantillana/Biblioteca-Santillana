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
public class Busqueda_ISBN_ISSNTest {

    public Busqueda_ISBN_ISSNTest() {
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
        String codigos[] = {"472601", "158570", "158573", "dwqd23d", "123dcc12"};
        Busqueda_ISBN_ISSN instance = new Busqueda_ISBN_ISSN();

        boolean resultado = true;
        for (String codigo : codigos) {
            String result = instance.buscarEntidad(codigo);
            if (result != "") {
                resultado = false;
            }
            assertEquals(true, resultado);
        }

    }

    /**
     * Test que se encarga de buscar un libro por un código ISBN correcto.
     */
    @Test
    public void testBuscarLibroPorCodigo() {
        System.out.println("buscar un libro por un código ISBN correcto");
        String isbn = "9788466646390";
        Busqueda_ISBN_ISSN instance = new Busqueda_ISBN_ISSN();
        Libro result = instance.buscarLibroPorCodigo(isbn);

        boolean resultado = true;
        if (result == null) {
            resultado = false;
        }
        assertEquals(true, resultado);
    }

    /**
     * Test que se encarga de buscar un libro por un código incorrecto.
     */
    @Test
    public void testBuscarLibroPorCodigoErroneo() {
        System.out.println("buscar un libro por un código incorrecto");
        String codigos[] = {"421257", "421259", "421260", "15197326", "15197327", "15197329d1x2xwq"};
        Busqueda_ISBN_ISSN instance = new Busqueda_ISBN_ISSN();

        boolean resultado = true;
        for (String codigo : codigos) {
            Libro result = instance.buscarLibroPorCodigo(codigo);
            if (result != null) {
                resultado = false;
            }
            assertEquals(true, resultado);
        }

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
        Enciclopedia result = instance.buscarEnciclopediaPorCodigo(isbn);

        boolean resultado = true;
        if (result == null) {
            resultado = false;
        }
        assertEquals(true, resultado);
    }

    /**
     * Test que se encarga de buscar una enciclopedia por un código incorrecto.
     */
    @Test
    public void testBuscarEnciclopediaPorCodigoErroneo() {
        System.out.println("buscar una enciclopedia por un código incorrecto");
        Busqueda_ISBN_ISSN instance = new Busqueda_ISBN_ISSN();

        boolean resultado = true;
        String codigos[] = {"421257", "421259", "421260", "15197326", "15197327", "15197329d1x2xwq"};
        for (String codigo : codigos) {
            Enciclopedia result = instance.buscarEnciclopediaPorCodigo(codigo);
            if (result != null) {
                resultado = false;
            }
            assertEquals(true, resultado);
        }
    }

    /**
     * Test que se encarga de buscar un diccionario por un código ISBN correcto.
     */
    @Test
    public void testBuscarDiccionarioPorCodigo() {
        System.out.println("buscar un diccionario por un código ISBN correcto");
        String isbn = "9582661652";
        Busqueda_ISBN_ISSN instance = new Busqueda_ISBN_ISSN();
        Diccionario result = instance.buscarDiccionarioPorCodigo(isbn);

        boolean resultado = true;
        if (result == null) {
            resultado = false;
        }
        assertEquals(true, resultado);
    }

    /**
     * Test que se encarga de buscar un diccionario por un código incorrecto.
     */
    @Test
    public void testBuscarDiccionarioPorCodigoErroneo() {
        System.out.println("buscar un diccionario por un código incorrecto");
        Busqueda_ISBN_ISSN instance = new Busqueda_ISBN_ISSN();

        boolean resultado = true;
        String codigos[] = {"421257", "421259", "421260", "15197326", "15197327", "15197329d1x2xwq"};
        for (String codigo : codigos) {
            Diccionario result = instance.buscarDiccionarioPorCodigo(codigo);
            if (result != null) {
                resultado = false;
            }
            assertEquals(true, resultado);
        }

    }

    /**
     * Test que se encarga de buscar una revista por un código ISSN correcto.
     */
    @Test
    public void testBuscarRevistaPorCodigo() {
        System.out.println("buscar una revista por un código ISSN correcto");
        String issn = "15197081";
        Busqueda_ISBN_ISSN instance = new Busqueda_ISBN_ISSN();

        Revista result = instance.buscarRevistaPorCodigo(issn);
        boolean resultado = true;
        if (result == null) {
            resultado = false;
        }
        assertEquals(true, resultado);
    }

    /**
     * Test que se encarga de buscar una revista por un código incorrecto.
     */
    @Test
    public void testBuscarRevistaPorCodigoErroneo() {
        System.out.println("buscar una revista por un código incorrecto");
        Busqueda_ISBN_ISSN instance = new Busqueda_ISBN_ISSN();

        boolean resultado = true;
        String codigos[] = {"421257", "421259", "421260", "9789587587623", "9788466646390"};
        for (String codigo : codigos) {
            Revista result = instance.buscarRevistaPorCodigo(codigo);
            if (result != null) {
                resultado = false;
            }
            assertEquals(true, resultado);
        }

    }

    /**
     * Test que se encarga de buscar un periódico por un código ISSN correcto.
     */
    @Test
    public void testBuscarPeriodicoPorCodigo() {
        System.out.println("buscar un periódico por un código ISSN correcto");
        String issn = "15197327";
        Busqueda_ISBN_ISSN instance = new Busqueda_ISBN_ISSN();

        Periodico result = instance.buscarPeriodicoPorCodigo(issn);
        boolean resultado = true;
        if (result == null) {
            resultado = false;
        }
        assertEquals(true, resultado);
    }

    /**
     * Test que se encarga de buscar un periódico por un código incorrecto.
     */
    @Test
    public void testBuscarPeriodicoPorCodigoIncorrecto() {
        System.out.println("buscar un periódico por un código incorrecto");
        Busqueda_ISBN_ISSN instance = new Busqueda_ISBN_ISSN();

        boolean resultado = true;
        String codigos[] = {"421257", "421259", "421260", "9789587587623", "9788466646390"};
        for (String codigo : codigos) {
            Periodico result = instance.buscarPeriodicoPorCodigo(codigo);
            if (result != null) {
                resultado = false;
            }
            assertEquals(true, resultado);
        }

    }

    /**
     * Test que se encarga de buscar un mapa por un código ISBN correcto.
     */
    @Test
    public void testBuscarMapaPorCodigo() {
        System.out.println("buscar un mapa por un código ISBN correcto");
        String isbn = "9752214522025";
        Busqueda_ISBN_ISSN instance = new Busqueda_ISBN_ISSN();

        Mapa result = instance.buscarMapaPorCodigo(isbn);
        boolean resultado = true;
        if (result == null) {
            resultado = false;
        }
        assertEquals(true, resultado);

    }

    /**
     * Test que se encarga de buscar un mapa por un código incorrecto.
     */
    @Test
    public void testBuscarMapaPorCodigoErroneo() {
        System.out.println("buscar un mapa por un código incorrecto");
        Busqueda_ISBN_ISSN instance = new Busqueda_ISBN_ISSN();

        boolean resultado = true;
        String codigos[] = {"421257", "421259", "421260", "15197326", "15197327", "15197329d1x2xwq"};
        for (String codigo : codigos) {
            Mapa result = instance.buscarMapaPorCodigo(codigo);
            if (result != null) {
                resultado = false;
            }
            assertEquals(true, resultado);
        }

    }

}

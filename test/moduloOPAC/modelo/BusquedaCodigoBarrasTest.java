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
     * Test que se encarga de buscar el nombre de una entidad ingresando un
     * código de barras correcto.
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
     * Test que se encarga de buscar el nombre de una entidad ingresando un
     * código de barras erroneo.
     */
    @Test
    public void testBuscarEntidadErronea() {
        System.out.println("buscar el nombre de una entidad ingresando un código de barras erroneo");
        String codBarras[] = {"472601", "158570", "158573", "dwqd23d"};
        BusquedaCodigoBarras instance = new BusquedaCodigoBarras();
        boolean resultado = true;

        for (String codBarra : codBarras) {
            String entidad = instance.buscarEntidad(codBarra);
            if ("revista".equals(entidad)) {
                resultado = false;
            }
            assertEquals(true, resultado);
        }

    }

    /**
     * Test que se encarga de buscar un libro ingresando un código de barras
     * correcto.
     */
    @Test
    public void testBuscarLibroPorCodigoCorrecto() {
        System.out.println("buscar un libro ingresando un código de barras correcto");
        String codBarras = "472601";
        BusquedaCodigoBarras instance = new BusquedaCodigoBarras();
        Libro libro = instance.buscarLibroPorCodigo(codBarras);

        boolean resultado = true;
        if (libro == null) {
            resultado = false;
        }
        assertEquals(true, resultado);

    }

    /**
     * Test que se encarga de buscar un libro ingresando un código de barras
     * erroneo.
     */
    @Test
    public void testBuscarLibroPorCodigoErroneo() {
        System.out.println("buscar un libro ingresando un código de barras erroneo");
        String codBarras[] = {"158570", "158573", "dwqd23d", "484963"};
        BusquedaCodigoBarras instance = new BusquedaCodigoBarras();

        boolean resultado = true;
        for (String codBarra : codBarras) {
            Libro libro = instance.buscarLibroPorCodigo(codBarra);
            if (libro != null) {
                resultado = false;
            }
            assertEquals(true, resultado);
        }

    }

    /**
     * Test que se encarga de buscar una enciclopedia ingresando un código de
     * barras correcto.
     */
    @Test
    public void testBuscarEnciclopediaPorCodigoCorrecto() {
        System.out.println("buscar una enciclopedia ingresando un código de barras correcto");
        String codBarras = "484963";
        BusquedaCodigoBarras instance = new BusquedaCodigoBarras();
        Enciclopedia enciclopedia = instance.buscarEnciclopediaPorCodigo(codBarras);

        boolean resultado = true;
        if (enciclopedia == null) {
            resultado = false;
        }
        assertEquals(true, resultado);
    }

    /**
     * Test que se encarga de buscar una enciclopedia ingresando un código de
     * barras erroneo.
     */
    @Test
    public void testBuscarEnciclopediaPorCodigoErroneo() {
        System.out.println("buscar una enciclopedia ingresando un código de barras erroneo");
        String codBarras[] = {"dwqd23d", "254916", "421257", "158570", "459858"};
        BusquedaCodigoBarras instance = new BusquedaCodigoBarras();

        boolean resultado = true;
        for (String codBarra : codBarras) {
            Enciclopedia enciclopedia = instance.buscarEnciclopediaPorCodigo(codBarra);

            if (enciclopedia != null) {
                resultado = false;
            }
            assertEquals(true, resultado);
        }

    }

    /**
     * Test que se encarga de buscar un diccionario ingresando un código de
     * barras correcto.
     */
    @Test
    public void testBuscarDiccionarioPorCodigoCorrecto() {
        System.out.println("buscar un diccionario ingresando un código de barras correcto");
        String codBarras = "254916";
        BusquedaCodigoBarras instance = new BusquedaCodigoBarras();
        Diccionario diccionario = instance.buscarDiccionarioPorCodigo(codBarras);

        boolean resultado = true;
        if (diccionario == null) {
            resultado = false;
        }
        assertEquals(true, resultado);
    }

    /**
     * Test que se encarga de buscar un diccionario ingresando un código de
     * barras erroneo.
     */
    @Test
    public void testBuscarDiccionarioPorCodigoErroneo() {
        System.out.println("buscar un diccionario ingresando un código de barras erroneo");
        String codBarras[] = {"dwqd23d", "158570", "459858"};
        BusquedaCodigoBarras instance = new BusquedaCodigoBarras();

        boolean resultado = true;
        for (String codBarra : codBarras) {
            Diccionario diccionario = instance.buscarDiccionarioPorCodigo(codBarra);

            if (diccionario != null) {
                resultado = false;
            }
            assertEquals(true, resultado);
        }

    }

    /**
     * Test que se encarga de buscar una revista ingresando un código de barras
     * correcto.
     */
    @Test
    public void testBuscarRevistaPorCodigoCorrecto() {
        System.out.println("buscar una revista ingresando un código de barras correcto");
        String codBarras = "421257";
        BusquedaCodigoBarras instance = new BusquedaCodigoBarras();
        Revista revista = instance.buscarRevistaPorCodigo(codBarras);

        boolean resultado = true;
        if (revista == null) {
            resultado = false;
        }
        assertEquals(true, resultado);
    }

    /**
     * Test que se encarga de buscar una revista ingresando un código de barras
     * erroneo.
     */
    @Test
    public void testBuscarRevistaPorCodigoErroneo() {
        System.out.println("buscar una revista ingresando un código de barras erroneo");
        String codBarras[] = {"dwqd23d", "158570", "459858"};
        BusquedaCodigoBarras instance = new BusquedaCodigoBarras();

        boolean resultado = true;
        for (String codBarra : codBarras) {
            Revista revista = instance.buscarRevistaPorCodigo(codBarra);

            if (revista != null) {
                resultado = false;
            }
            assertEquals(true, resultado);
        }
    }

    /**
     * Test que se encarga de buscar un periodico ingresando un código de barras
     * correcto.
     */
    @Test
    public void testBuscarPeriodicoPorCodigoCorrecto() {
        System.out.println("buscar un periodico ingresando un código de barras correcto");
        String codBarras = "158570";
        BusquedaCodigoBarras instance = new BusquedaCodigoBarras();
        Periodico periodico = instance.buscarPeriodicoPorCodigo(codBarras);

        boolean resultado = true;
        if (periodico == null) {
            resultado = false;
        }
        assertEquals(true, resultado);

    }

    /**
     * Test que se encarga de buscar un periodico ingresando un código de barras
     * erroneo.
     */
    @Test
    public void testBuscarPeriodicoPorCodigoErroneo() {
        System.out.println("buscar un periodico ingresando un código de barras erroneo");
        String codBarras[] = {"dwqd23d", "459858", "472601", "421257"};
        BusquedaCodigoBarras instance = new BusquedaCodigoBarras();

        boolean resultado = true;
        for (String codBarra : codBarras) {
            Periodico periodico = instance.buscarPeriodicoPorCodigo(codBarra);

            if (periodico != null) {
                resultado = false;
            }
            assertEquals(true, resultado);
        }

    }

    /**
     * Test que se encarga de buscar un mapa ingresando un código de barras
     * correcto.
     */
    @Test
    public void testBuscarMapaPorCodigoCorrecto() {
        System.out.println("buscar un mapa ingresando un código de barras correcto");
        String codBarras = "459858";
        BusquedaCodigoBarras instance = new BusquedaCodigoBarras();
        Mapa mapa = instance.buscarMapaPorCodigo(codBarras);

        boolean resultado = true;
        if (mapa == null) {
            resultado = false;
        }
        assertEquals(true, resultado);

    }

    /**
     * Test que se encarga de buscar un mapa ingresando un código de barras
     * erroneo.
     */
    @Test
    public void testBuscarMapaPorCodigoErroneo() {
        System.out.println("buscar un mapa ingresando un código de barras erroneo");
        String codBarras[] = {"dwqd23d", "158570", "472601", "421257"};
        BusquedaCodigoBarras instance = new BusquedaCodigoBarras();

        boolean resultado = true;
        for (String codBarra : codBarras) {
            Mapa mapa = instance.buscarMapaPorCodigo(codBarra);
            if (mapa != null) {
                resultado = false;
            }
            assertEquals(true, resultado);
        }
    }

}

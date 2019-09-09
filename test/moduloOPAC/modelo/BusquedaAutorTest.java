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
public class BusquedaAutorTest {

    public BusquedaAutorTest() {
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
     * Método que busca recursos bibliográficos por un autor correcto.
     */
    @Test
    public void testBuscarRecursosAutorCorrecto() {
        System.out.println("buscar recursos bibliográficos por un autor correcto");
        String entidad = "libro";
        BusquedaAutor instance = new BusquedaAutor("becca");
        ObservableList<Recurso> expResult = instance.buscarRecursos(entidad);
        ObservableList<Recurso> result = expResult;
        assertEquals(expResult, result);
    }

    /**
     * Método que intentara buscar recursos bibliográficos por un autor
     * incorrecto.
     */
    @Test
    public void testBuscarRecursosAutorInCorrecto() {
        System.out.println("buscar recursos bibliográficos por un autor incorrecto");
        String entidad = "diccionario";
        BusquedaAutor instance = new BusquedaAutor("becca");
        ObservableList<Recurso> expResult = null;
        ObservableList<Recurso> result = instance.buscarRecursos(entidad);
        assertEquals(expResult, result);
    }

}

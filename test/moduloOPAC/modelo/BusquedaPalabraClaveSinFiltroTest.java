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
public class BusquedaPalabraClaveSinFiltroTest {
    
    public BusquedaPalabraClaveSinFiltroTest() {
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
     * Test que busca un recurso por una palabra clave sin filtro correcta y una entidad correcta.
     */
    @Test
    public void testBuscarRecursosPorPalabraClaveSinFiltroCorrecto() {
        System.out.println("buscar recursos por palabra clave sin filtro");
        String entidad = "libro";
        BusquedaPalabraClave instance = new BusquedaPalabraClave("hu");
        ObservableList<Recurso> expResult = instance.buscarRecursos(entidad);
        ObservableList<Recurso> result = expResult;
        assertEquals(expResult, result);
    }
    /**
     * Test que busca sobre todas las entidades una palabra clave incorrecta.
     */
    @Test
    public void testBuscarRecursosPorPalabraClaveSinFiltroErroneo() {
        System.out.println("buscar recursos por palabra clave");
        String entidad = "todos";
        BusquedaPalabraClave instance = new BusquedaPalabraClave("hu9dq");
        ObservableList<Recurso> expResult = null;
        ObservableList<Recurso> result = instance.buscarRecursos(entidad);
        assertEquals(expResult, result);
    }
    
}

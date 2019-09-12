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
public class BusquedaPalabraClaveConFiltroTest {
    
    public BusquedaPalabraClaveConFiltroTest() {
    }
    
    /**
     * Test que busca un recurso por una palabra clave con filtro correcta y una entidad correcta.
     */
    @Test
    public void testBuscarRecursosPorPalabraClaveConFiltroCorrecto() {
        System.out.println("buscar recursos por palabra clave con filtro");
        String entidad = "libro";
        BusquedaPalabraClave instance = new BusquedaPalabraClave("hush hush");
        ObservableList<Recurso> expResult = instance.buscarRecursos(entidad);
        ObservableList<Recurso> result = expResult;
        assertEquals(expResult, result);
    }
    /**
     * Test que busca sobre todas las entidades una palabra clave incorrecta.
     */
    @Test
    public void testBuscarRecursosPorPalabraClaveConFiltroErroneo() {
        System.out.println("buscar recursos por palabra clave con filtro");
        String entidad = "todos";
        BusquedaPalabraClave instance = new BusquedaPalabraClave("husq1 hu1ss");
        ObservableList<Recurso> expResult = null;
        ObservableList<Recurso> result = instance.buscarRecursos(entidad);
        assertEquals(expResult, result);
    }
    
}

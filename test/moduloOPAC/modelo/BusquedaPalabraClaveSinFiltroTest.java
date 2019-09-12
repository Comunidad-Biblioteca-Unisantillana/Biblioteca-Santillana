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

    /**
     * Test que busca un recurso por una palabra clave sin filtro correcta y una
     * entidad correcta.
     */
    @Test
    public void testBuscarRecursosPorPalabraClaveSinFiltroCorrecto() {
        System.out.println("buscar recursos por palabra clave sin filtro");
        String entidad = "libro";
        BusquedaPalabraClave instance = new BusquedaPalabraClave("hu");
        ObservableList<Recurso> recursos = instance.buscarRecursos(entidad);

        boolean resultado = true;
        if (recursos.isEmpty()) {
            resultado = false;
        }
        assertEquals(true, resultado);
    }

    /**
     * Test que busca sobre todas las entidades una palabra clave incorrecta.
     */
    @Test
    public void testBuscarRecursosPorPalabraClaveSinFiltroErroneo() {
        System.out.println("buscar recursos por palabra clave");
        String entidad = "todos";
        String palabras[] = {"husq1 hu1ss", "1234", "uaml", "vajra"};
        
        for (String palabra : palabras) {
            BusquedaPalabraClave instance = new BusquedaPalabraClave(palabra);
            ObservableList<Recurso> recursos = instance.buscarRecursos(entidad);

            boolean resultado = true;
            if (recursos.isEmpty()) {
                resultado = false;
            }
            assertEquals(false, resultado);
        }
    }

}

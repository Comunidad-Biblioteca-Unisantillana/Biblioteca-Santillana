package moduloOPAC.modelo;

import javafx.collections.ObservableList;
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
     * Test que busca un recurso por una palabra clave con filtro correcta y una
     * entidad correcta.
     */
    @Test
    public void testBuscarRecursosPorPalabraClaveConFiltroCorrecto() {
        System.out.println("buscar recursos por palabra clave con filtro");
        String entidad = "libro";
        BusquedaPalabraClave instance = new BusquedaPalabraClave("hush hush");
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
    public void testBuscarRecursosPorPalabraClaveConFiltroErroneo() {
        System.out.println("buscar recursos por palabra clave con filtro");
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

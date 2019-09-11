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

    /**
     * Método que busca recursos bibliográficos por un autor correcto.
     */
    @Test
    public void testBuscarRecursosAutorCorrecto() {
        System.out.println("buscar recursos bibliográficos por un autor correcto");
        String entidad = "libro";
        BusquedaAutor instance = new BusquedaAutor("becca");
        ObservableList<Recurso> recursos = instance.buscarRecursos(entidad);
        boolean resultado = true;
        if(recursos.isEmpty()){
            resultado = false;
        }
        assertEquals(true, resultado);
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
        ObservableList<Recurso> recursos = instance.buscarRecursos(entidad);
        boolean resultado = true;
        if(recursos.isEmpty()){
            resultado = false;
        }
        assertEquals(false, resultado);
    }

}

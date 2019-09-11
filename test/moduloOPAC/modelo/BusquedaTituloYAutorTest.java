/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
public class BusquedaTituloYAutorTest {

    public BusquedaTituloYAutorTest() {
    }

    /**
     * Método que busca recursos bibliográficos por un titulo y autor correctos.
     */
    @Test
    public void testBuscarRecursosTituloYAutorCorrectos() {
        System.out.println("buscar recursos bibliográficos por un titulo y autor correctos");
        String entidad = "libro";
        BusquedaTituloYAutor instance = new BusquedaTituloYAutor("hush", "becca");
        ObservableList<Recurso> recursos = instance.buscarRecursos(entidad);
        boolean resultado = true;
        if (recursos.isEmpty()) {
            resultado = false;
        }
        assertEquals(true, resultado);
    }

    /**
     * Método que intentara buscar recursos bibliográficos por un titulo y autor
     * incorrectos.
     */
    @Test
    public void testBuscarRecursosTituloYAutorInCorrectos() {
        System.out.println("buscar recursos bibliográficos por un titulo y autor incorrectos");
        String entidad = "diccionario";
        BusquedaTituloYAutor instance = new BusquedaTituloYAutor("hush", "becca");
        ObservableList<Recurso> recursos = instance.buscarRecursos(entidad);
        boolean resultado = true;
        if(recursos.isEmpty()){
            resultado = false;
        }
        assertEquals(false, resultado);
    }

}

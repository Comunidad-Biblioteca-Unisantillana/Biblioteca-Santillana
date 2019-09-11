/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moduloPrestamo.fabrica;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import moduloPrestamo.DAO.PrestamoMapaDAOProf;
import moduloPrestamo.entitys.PrestamoMapaProf;
import moduloPrestamo.modelo.GeneradorPrestamo;
import org.junit.Test;
import static org.junit.Assert.*;
import recursos.controllers.MapaJpaController;
import recursos.entitys.Mapa;
import usuarios.control.ProfesorJpaController;
import usuarios.entitys.Profesor;

/**
 *
 * @author Miguel
 */
public class PrestamoMapaProfFabTest {

    public PrestamoMapaProfFabTest() {
    }

    /**
     * Prueba #1
     */
     @Test
    public void testEjecutarPrestamo() {
        System.out.println("Prueba 1... GENERAR PRESTAMO");
        String codBarras = "459885";
        String idBibliotecario = "1102515566";
        ProfesorJpaController controlProf = new ProfesorJpaController();
        List<Profesor> profesores = controlProf.findProfesorEntities();
        PrestamoMapaProfFab instance = new PrestamoMapaProfFab();
        boolean result = false;
        for (int i = 0; i < profesores.size(); i++) {
            result = instance.ejecutarPrestamo(codBarras, profesores.get(i).getIdprofesor(), idBibliotecario);
            if (result) {
                destruirPrestamocreao(instance);
            } else {
                System.out.println("Error con el codigo de profesor: " + profesores.get(i).getIdprofesor());
                break;
            }
        }
        assertEquals(true, result);
    }

    /**
     * Metodo que se encarga de mostrar los prestamos realizados<br>
     * y de destruirlos
     * pruebas
     */
    private void destruirPrestamocreao(PrestamoMapaProfFab instance) {
        if (instance != null) {
            try {
                PrestamoMapaDAOProf prestDAO = new PrestamoMapaDAOProf();
                List<PrestamoMapaProf> prestamo = prestDAO.readAllDAO();
                MapaJpaController mapJPA = new MapaJpaController();
                Mapa map = mapJPA.findMapa(prestamo.get(prestamo.size() - 1).getCodBarraMapa());
                prestDAO.deleteDAO(prestamo.get(prestamo.size() - 1).getCodPrestamoMapaProf());System.out.println("*****************************************************************");
                map.setDisponibilidad("disponible");
                mapJPA.edit(map);
            } catch (Exception ex) {
                Logger.getLogger(PrestamoLibroProfFabTest.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * Prueba #2
     */
    @Test
    public void testProfesorNA() {
        System.out.println("PRUEBA 2... PROFESOR NO ENCONTRADO");
        String codProfesor = "946789878";
        GeneradorPrestamo instance = new GeneradorPrestamo();
        boolean result = true;
        try {
            instance.createPrestamo("459887", codProfesor, "1102515566", "mapa", "profesor");
        } catch (ExceptionInInitializerError ex) {
            result = false;
        } catch (Exception ex) {

        }
        assertEquals(false, result);
    }
    
    /**
     * Prueba 3
     */
    @Test
    public void testCodBarraNA() {
        System.out.println("PRUEBA 3.... CODIGO DE BARRAS NO ENCONTRADO");
        String codBarras = "459888747";
        PrestamoMapaProfFab instance = new PrestamoMapaProfFab();
        boolean result = false;
        try {
            result = instance.ejecutarPrestamo(codBarras, "1760156", "1102515566");
            System.out.println("El prestamo se realizo");
        } catch (Exception e) {
        } catch (NoClassDefFoundError | ExceptionInInitializerError ex) {
            System.out.println("El prestamo no se realizo");
            result = false;
        }
        assertEquals(false, result);
    }

    /**
     * Prueba 5
     */
    @Test
    public void testRecursoNoDisponible() {
        System.out.println("PRUEBA 5.... RECURSO NO DISPONIBLE");
        PrestamoMapaProfFab instance = new PrestamoMapaProfFab();
        MapaJpaController controlmap = new MapaJpaController();
        List<Mapa> map = controlmap.findMapaEntities();
        boolean result = false;
        for (int i = 0; i < map.size(); i++) {
            if (!map.get(i).getDisponibilidad().equalsIgnoreCase("disponible")) {
                try {
                    result = instance.ejecutarPrestamo(map.get(i).getCodbarramapa(), "946789878", "1102515566");
                    System.out.println("El prestaamo se realizo");
                    break;
                } catch (Exception e) {
                } catch (NoClassDefFoundError | ExceptionInInitializerError ex) {
                    System.out.println("El prestamo no se realizo");
                    result = false;
                }
            }
        }
        assertEquals(false, result);
    }
    
    /**
     * Prueba 6,7,8
     */
    @Test
    public void testActualizarDisponibilidad() {
        System.out.println("PRUEBAS DEL 6 AL 8....");
        PrestamoMapaProfFab instance = new PrestamoMapaProfFab();
        MapaJpaController controlMap = new MapaJpaController();
        List<Mapa> map = controlMap.findMapaEntities();
        boolean result = false;
        for (int i = 0; i < map.size(); i++) {
            if (map.get(i).getDisponibilidad().equalsIgnoreCase("disponible")) {
                try {
                    result = instance.ejecutarPrestamo(map.get(i).getCodbarramapa(), "946789878", "1102515566");
                    
                    destruirPrestamocreao(instance);
                } catch (Exception e) {
                    System.out.println("El prestamo no se realizo");
                    result = false;
                    break;
                }
            }else{
                System.out.println("|\n|");
                System.out.println("El diccionario con codigo de barra " + map.get(i).getCodbarramapa()+ " se encuentra prestado o reservado");
            }
        }
        assertEquals(true, result);
    }
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moduloPrestamo.fabrica;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import moduloPrestamo.DAO.PrestamoLibroDAOProf;
import moduloPrestamo.entitys.PrestamoLibroProf;
import moduloPrestamo.modelo.GeneradorPrestamo;
import org.junit.Test;
import static org.junit.Assert.*;
import recursos.controllers.LibroJpaController;
import recursos.entitys.Libro;
import usuarios.control.ProfesorJpaController;
import usuarios.entitys.Profesor;

/**
 *
 * @author Miguel
 */
public class PrestamoLibroProfFabTest {

    public PrestamoLibroProfFabTest() {
    }

    /**
     * Prueba #1
     */
     @Test
    public void testEjecutarPrestamo() {
        System.out.println("Prueba 1...  GENERAR PRESTAMO");
        String codBarras = "582278";
        String idBibliotecario = "1102515566";
        ProfesorJpaController controlProf = new ProfesorJpaController();
        List<Profesor> profesores = controlProf.findProfesorEntities();
        PrestamoLibroProfFab instance = new PrestamoLibroProfFab();
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
     * 
     * pruebas
     */
    private void destruirPrestamocreao(PrestamoLibroProfFab instance) {
        if (instance != null) {
            try {
                PrestamoLibroDAOProf prestDAO = new PrestamoLibroDAOProf();
                List<PrestamoLibroProf> prestamo = prestDAO.readAllDAO();
                LibroJpaController libJPA = new LibroJpaController();
                Libro lib = libJPA.findLibro(prestamo.get(prestamo.size() - 1).getCodBarraLibro());
                prestDAO.deleteDAO(prestamo.get(prestamo.size() - 1).getCodPrestamoLibroProf());
                lib.setDisponibilidad("disponible");
                libJPA.edit(lib);
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
        System.out.println("PRUEBA 2.... PROFESOR NO ENCONTRADO");
        String codProfesor = "946789878";
        GeneradorPrestamo instance = new GeneradorPrestamo();
        boolean result = true;
        try {
            instance.createPrestamo("464865", codProfesor, "1102515566", "diccionario", "profesor");
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
        System.out.println("PRUEBA 3... CODIGO DE BARRAS NO ENCONTRADO");
        String codBarras = "46486545";
        PrestamoLibroProfFab instance = new PrestamoLibroProfFab();
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
        PrestamoLibroProfFab instance = new PrestamoLibroProfFab();
        LibroJpaController controlib = new LibroJpaController();
        List<Libro> lib = controlib.findLibroEntities();
        boolean result = false;
        for (int i = 0; i < lib.size(); i++) {
            if (!lib.get(i).getDisponibilidad().equalsIgnoreCase("disponible")) {
                try {
                    result = instance.ejecutarPrestamo(lib.get(i).getCodbarralibro(), "946789878", "1102515566");
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
        System.out.println("PRUEBAS DEL 6 AL 8.....");
        PrestamoLibroProfFab instance = new PrestamoLibroProfFab();
        LibroJpaController controlLib = new LibroJpaController();
        List<Libro> lib = controlLib.findLibroEntities();
        boolean result = false;
        for (int i = 0; i < lib.size(); i++) {
            if (lib.get(i).getDisponibilidad().equalsIgnoreCase("disponible")) {
                try {
                    result = instance.ejecutarPrestamo(lib.get(i).getCodbarralibro(), "946789878", "1102515566");
                    
                    destruirPrestamocreao(instance);
                } catch (Exception e) {
                    System.out.println("El prestamo no se realizo");
                    result = false;
                    break;
                }
            }else{
                System.out.println("|\n|");
                System.out.println("El diccionario con codigo de barra " + lib.get(i).getCodbarralibro()+ " se encuentra prestado o reservado");
            }
        }
        assertEquals(true, result);
    }
}
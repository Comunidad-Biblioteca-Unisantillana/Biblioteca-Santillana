/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moduloPrestamo.fabrica;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import moduloPrestamo.DAO.PrestamoRevistaDAOProf;
import moduloPrestamo.entitys.PrestamoRevistaProf;
import moduloPrestamo.modelo.GeneradorPrestamo;
import org.junit.Test;
import static org.junit.Assert.*;
import recursos.controllers.RevistaJpaController;
import recursos.entitys.Revista;
import usuarios.control.ProfesorJpaController;
import usuarios.entitys.Profesor;

/**
 *
 * @author Miguel
 */
public class PrestamoRevistaProfFabTest {

    public PrestamoRevistaProfFabTest() {
    }

    /**
     * Prueba #1
     */
     @Test
    public void testEjecutarPrestamo() {
        System.out.println("Prueba 1... GENERAR PRESTAMO");
        String codBarras = "421272";
        String idBibliotecario = "1102515566";
        ProfesorJpaController controlProf = new ProfesorJpaController();
        List<Profesor> profesores = controlProf.findProfesorEntities();
        PrestamoRevistaProfFab instance = new PrestamoRevistaProfFab();
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
    private void destruirPrestamocreao(PrestamoRevistaProfFab instance) {
        if (instance != null) {
            try {
                PrestamoRevistaDAOProf prestDAO = new PrestamoRevistaDAOProf();
                List<PrestamoRevistaProf> prestamo = prestDAO.readAllDAO();
                RevistaJpaController revJPA = new RevistaJpaController();
                Revista rev = revJPA.findRevista(prestamo.get(prestamo.size() - 1).getCodBarraRevista());
                prestDAO.deleteDAO(prestamo.get(prestamo.size() - 1).getCodPrestamoRevistaProf());
                rev.setDisponibilidad("disponible");
                revJPA.edit(rev);
            } catch (Exception ex) {
                Logger.getLogger(PrestamoRevistaProfFabTest.class.getName()).log(Level.SEVERE, null, ex);
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
            instance.createPrestamo("158583", codProfesor, "1102515566", "revista", "profesor");
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
        String codBarras = "46486545";
        PrestamoRevistaProfFab instance = new PrestamoRevistaProfFab();
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
        System.out.println("PRUEBA 5... RECURSO NO DISPONIBLE");
        PrestamoRevistaProfFab instance = new PrestamoRevistaProfFab();
        RevistaJpaController controlrev = new RevistaJpaController();
        List<Revista> rev = controlrev.findRevistaEntities();
        boolean result = false;
        for (int i = 0; i < rev.size(); i++) {
            if (!rev.get(i).getDisponibilidad().equalsIgnoreCase("disponible")) {
                try {
                    result = instance.ejecutarPrestamo(rev.get(i).getCodbarrarevista(), "946789878", "1102515566");
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
        System.out.println("PRUEBAS DEL 6 AL 8....");;
        PrestamoRevistaProfFab instance = new PrestamoRevistaProfFab();
        RevistaJpaController controlrev = new RevistaJpaController();
        List<Revista> rev = controlrev.findRevistaEntities();
        boolean result = false;
        for (int i = 0; i < rev.size(); i++) {
            if (rev.get(i).getDisponibilidad().equalsIgnoreCase("disponible")) {
                try {
                    result = instance.ejecutarPrestamo(rev.get(i).getCodbarrarevista(), "946789878", "1102515566");
                    
                    destruirPrestamocreao(instance);
                } catch (Exception e) {
                    System.out.println("El prestamo no se realizo");
                    result = false;
                    break;
                }
            }else{
                System.out.println("|\n|");
                System.out.println("El diccionario con codigo de barra " + rev.get(i).getCodbarrarevista()+ " se encuentra prestado o reservado");
            }
        }
        assertEquals(true, result);
    }
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moduloPrestamo.fabrica;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import moduloPrestamo.DAO.PrestamoPeriodicoDAOProf;
import moduloPrestamo.entitys.PrestamoPeriodicoProf;
import moduloPrestamo.modelo.GeneradorPrestamo;
import org.junit.Test;
import static org.junit.Assert.*;
import recursos.controllers.PeriodicoJpaController;
import recursos.entitys.Periodico;
import usuarios.control.ProfesorJpaController;
import usuarios.entitys.Profesor;

/**
 *
 * @author Miguel
 */
public class PrestamoPeriodicoProfFabTest {

    public PrestamoPeriodicoProfFabTest() {
    }

    /**
     * Prueba #1
     */
     @Test
    public void testEjecutarPrestamo() {
        System.out.println("Prueba 1... GENERAR PRESTAMO");
        String codBarras = "158583";
        String idBibliotecario = "1102515566";
        ProfesorJpaController controlProf = new ProfesorJpaController();
        List<Profesor> profesores = controlProf.findProfesorEntities();
        PrestamoPeriodicoProfFab instance = new PrestamoPeriodicoProfFab();
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
    private void destruirPrestamocreao(PrestamoPeriodicoProfFab instance) {
        if (instance != null) {
            try {
                PrestamoPeriodicoDAOProf prestDAO = new PrestamoPeriodicoDAOProf();
                List<PrestamoPeriodicoProf> prestamo = prestDAO.readAllDAO();
                PeriodicoJpaController periJPA = new PeriodicoJpaController();
                Periodico peri = periJPA.findPeriodico(prestamo.get(prestamo.size() - 1).getCodBarraPeriodico());
                prestDAO.deleteDAO(prestamo.get(prestamo.size() - 1).getCodPrestamoPeriodicoProf());System.out.println("*****************************************************************");
                peri.setDisponibilidad("disponible");
                periJPA.edit(peri);
            } catch (Exception ex) {
                Logger.getLogger(PrestamoPeriodicoProfFabTest.class.getName()).log(Level.SEVERE, null, ex);
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
            instance.createPrestamo("158583", codProfesor, "1102515566", "periodico", "profesor");
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
        PrestamoPeriodicoProfFab instance = new PrestamoPeriodicoProfFab();
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
        System.out.println("PRUEBA 5..... RECURSO NO DISPONIBLE");
        PrestamoPeriodicoProfFab instance = new PrestamoPeriodicoProfFab();
        PeriodicoJpaController controperi = new PeriodicoJpaController();
        List<Periodico> peri = controperi.findPeriodicoEntities();
        boolean result = false;
        for (int i = 0; i < peri.size(); i++) {
            if (!peri.get(i).getDisponibilidad().equalsIgnoreCase("disponible")) {
                try {
                    result = instance.ejecutarPrestamo(peri.get(i).getCodbarraperiodico(), "946789878", "1102515566");
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
        PrestamoPeriodicoProfFab instance = new PrestamoPeriodicoProfFab();
        PeriodicoJpaController controlPeri = new PeriodicoJpaController();
        List<Periodico> peri = controlPeri.findPeriodicoEntities();
        boolean result = false;
        for (int i = 0; i < peri.size(); i++) {
            if (peri.get(i).getDisponibilidad().equalsIgnoreCase("disponible")) {
                try {
                    result = instance.ejecutarPrestamo(peri.get(i).getCodbarraperiodico(), "946789878", "1102515566");
                    System.out.println("|\n|");
                    destruirPrestamocreao(instance);
                } catch (Exception e) {
                    System.out.println("El prestamo no se realizo");
                    result = false;
                    break;
                }
            }else{
                System.out.println("|\n|");
                System.out.println("El diccionario con codigo de barra " + peri.get(i).getCodbarraperiodico()+ " se encuentra prestado o reservado");
            }
        }
        assertEquals(true, result);
    }
}
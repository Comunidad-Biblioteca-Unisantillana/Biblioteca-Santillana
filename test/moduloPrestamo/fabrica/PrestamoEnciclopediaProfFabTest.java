/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moduloPrestamo.fabrica;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import moduloPrestamo.DAO.PrestamoEnciclopediaDAOProf;
import moduloPrestamo.entitys.PrestamoEnciclopediaProf;
import moduloPrestamo.modelo.GeneradorPrestamo;
import org.junit.Test;
import static org.junit.Assert.*;
import recursos.controllers.EnciclopediaJpaController;
import recursos.entitys.Enciclopedia;
import usuarios.control.ProfesorJpaController;
import usuarios.entitys.Profesor;

/**
 *
 * @author Miguel
 */
public class PrestamoEnciclopediaProfFabTest {

    public PrestamoEnciclopediaProfFabTest() {
    }

    /**
     * Prueba #1
     */
     @Test
    public void testEjecutarPrestamo() {
        System.out.println("Prueba 1...    GENERAR PRESTAMO");
        String codBarras = "496838";
        String idBibliotecario = "1102515566";
        ProfesorJpaController controlProf = new ProfesorJpaController();
        List<Profesor> profesores = controlProf.findProfesorEntities();
        PrestamoEnciclopediaProfFab instance = new PrestamoEnciclopediaProfFab();
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
     *
     */
    private void destruirPrestamocreao(PrestamoEnciclopediaProfFab instance) {
        if (instance != null) {
            try {
                PrestamoEnciclopediaDAOProf prestDAO = new PrestamoEnciclopediaDAOProf();
                List<PrestamoEnciclopediaProf> prestamo = prestDAO.readAllDAO();
                EnciclopediaJpaController enciJPA = new EnciclopediaJpaController();
                Enciclopedia enci = enciJPA.findEnciclopedia(prestamo.get(prestamo.size() - 1).getCodBarraEnciclopedia());
                prestDAO.deleteDAO(prestamo.get(prestamo.size() - 1).getCodPrestamoEnciclopediaProf());
                enci.setDisponibilidad("disponible");
                enciJPA.edit(enci);
            } catch (Exception ex) {
                Logger.getLogger(PrestamoEnciclopediaDAOProf.class.getName()).log(Level.SEVERE, null, ex);
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
            instance.createPrestamo("496838", codProfesor, "1102515566", "enciclopedia", "profesor");
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
        String codBarras = "496838";
        PrestamoEnciclopediaProfFab instance = new PrestamoEnciclopediaProfFab();
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
    public void testRecursoNA() {
        System.out.println("PRUEBA 5....  RECURSO NO ENCONTRADO");
        PrestamoEnciclopediaProfFab instance = new PrestamoEnciclopediaProfFab();
        EnciclopediaJpaController controlrev = new EnciclopediaJpaController();
        List<Enciclopedia> rev = controlrev.findEnciclopediaEntities();
        boolean result = false;
        for (int i = 0; i < rev.size(); i++) {
            if (!rev.get(i).getDisponibilidad().equalsIgnoreCase("disponible")) {
                try {
                    result = instance.ejecutarPrestamo(rev.get(i).getCodbarraenciclopedia(), "946789878", "1102515566");
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
        System.out.println("Prueba 6 - 8...");
        PrestamoEnciclopediaProfFab instance = new PrestamoEnciclopediaProfFab();
        EnciclopediaJpaController controlrev = new EnciclopediaJpaController();
        List<Enciclopedia> enci = controlrev.findEnciclopediaEntities();
        boolean result = false;
        for (int i = 0; i < enci.size(); i++) {
            if (enci.get(i).getDisponibilidad().equalsIgnoreCase("disponible")) {
                try {
                    result = instance.ejecutarPrestamo(enci.get(i).getCodbarraenciclopedia(), "946789878", "1102515566");
                    destruirPrestamocreao(instance);
                } catch (Exception e) {
                    System.out.println("El prestamo no se realizo");
                    result = false;
                    break;
                }
            }else{
                System.out.println("|\n|");
                System.out.println("El diccionario con codigo de barra " + enci.get(i).getCodbarraenciclopedia()+ " se encuentra prestado o reservado");
            }
        }
        assertEquals(true, result);
    }
}
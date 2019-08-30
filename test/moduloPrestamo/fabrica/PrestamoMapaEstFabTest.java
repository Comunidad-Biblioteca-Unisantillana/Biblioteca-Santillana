/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moduloPrestamo.fabrica;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import moduloPrestamo.DAO.PrestamoMapaDAOEst;
import moduloPrestamo.entitys.PrestamoMapaEst;
import org.junit.Test;
import static org.junit.Assert.*;
import recursos1.controllers.MapaJpaController;
import recursos1.entitys.Mapa;
import usuario.controllers.EstudianteJpaController;
import usuario.entitys.Estudiante;

/**
 *
 * @author Storkolm
 */
public class PrestamoMapaEstFabTest {
    
    public PrestamoMapaEstFabTest() {
    }
   
    /**
     * Prueba #1
     */
    @Test
    public void testEjecutarPrestamo() {
        System.out.println("--------------------------Prueba 1--------------------------------------");
        String codBarras = "459887";
        String idBibliotecario = "1102515566";
        EstudianteJpaController controlEst = new EstudianteJpaController();
        List<Estudiante> estudiantes = controlEst.findEstudianteEntities();
        PrestamoMapaEstFab instance = new PrestamoMapaEstFab();
        boolean result = false;
        for (int i = 0; i < estudiantes.size(); i++) {
            result = instance.ejecutarPrestamo(codBarras, estudiantes.get(i).getCodestudiante(), idBibliotecario);
            if (result) {
                destroyPrestamo(instance);
            } else {
                System.out.println("Error con el codigo de estudiante: " + estudiantes.get(i).getCodestudiante());
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
    private void destroyPrestamo(PrestamoMapaEstFab instance) {
        if (instance != null) {
            try {
                PrestamoMapaDAOEst prestDAO = new PrestamoMapaDAOEst();
                List<PrestamoMapaEst> prestamo = prestDAO.readAllDAO();
                MapaJpaController recursoJPA = new MapaJpaController();
                Mapa recurso = recursoJPA.findMapa(prestamo.get(prestamo.size() - 1).getCodBarraMapa());
                System.out.println("*****************************************************************");
                System.out.println("El prestamo se realizo con exito\n");
                System.out.println("------Datos prestamo Mapa    ");
                System.out.println("código estudiante: " + prestamo.get(prestamo.size() - 1).getCodEstudiante());
                System.out.println("fecha prestamo: " + prestamo.get(prestamo.size() - 1).getFechaPrestamo());
                System.out.println("fecha devolucion: " + prestamo.get(prestamo.size() - 1).getFechaDevolucion());
                System.out.println("destruyendo prestamo........\n");
                prestDAO.deleteDAO(prestamo.get(prestamo.size() - 1).getCodPrestamoMapaEst());
                System.out.println("------Datos Mapa");
                System.out.println("codigo de barra: " + recurso.getCodbarramapa());
                System.out.println("Disponibilidad: " + recurso.getDisponibilidad());
                System.out.println("cambiando disponibilidad Mapa..........");
                System.out.println("*****************************************************************");
                recurso.setDisponibilidad("disponible");
                recursoJPA.edit(recurso);
            } catch (Exception ex) {
            }
        }
    }
    
    /**
     * Prueba 3
     */
    @Test
    public void testCodBarraNA() {
        System.out.println("--------------------------Prueba 3-----------------------------");
        String codBarras = "8789";
        PrestamoMapaEstFab instance = new PrestamoMapaEstFab();
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
        System.out.println("--------------------------Prueba 5-----------------------------");
        PrestamoMapaEstFab instance = new PrestamoMapaEstFab();
        MapaJpaController control = new MapaJpaController();
        List<Mapa> recurso = control.findMapaEntities();
        boolean result = false;
        for (int i = 0; i < recurso.size(); i++) {
            if (!recurso.get(i).getDisponibilidad().equalsIgnoreCase("disponible")) {
                try {
                    result = instance.ejecutarPrestamo(recurso.get(i).getCodbarramapa(), "1760156", "1102515566");
                    System.out.println("El prestamo se realizo");
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
        System.out.println("--------------------------Prueba 6,7,8-----------------------------------------");
        System.out.println("-------------------------------------------------------------------------------");
        PrestamoMapaEstFab instance = new PrestamoMapaEstFab();
        MapaJpaController control = new MapaJpaController();
        List<Mapa> recurso = control.findMapaEntities();
        boolean result = false;
        for (int i = 0; i < recurso.size(); i++) {
            if (recurso.get(i).getDisponibilidad().equalsIgnoreCase("disponible")) {
                try {
                    result = instance.ejecutarPrestamo(recurso.get(i).getCodbarramapa(), "1760156", "1102515566");
                    System.out.println("|\n|");
                    destroyPrestamo(instance);
                } catch (Exception e) {
                    System.out.println("El prestamo no se realizo");
                    result = false;
                    break;
                }
            }else{
                System.out.println("|\n|");
                System.out.println("El mapa con codigo de barra " + recurso.get(i).getCodbarramapa() + " se encuentra prestado o reservado");
            }
        }
        assertEquals(true, result);
    }
}

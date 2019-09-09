/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moduloPrestamo.fabrica;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import moduloPrestamo.DAO.PrestamoLibroDAOEst;
import moduloPrestamo.entitys.PrestamoLibroEst;
import org.junit.Test;
import static org.junit.Assert.*;
import recursos.controllers.LibroJpaController;
import recursos.entitys.Libro;
import usuarios.control.EstudianteJpaController;
import usuarios.entitys.Estudiante;


/**
 *
 * @author Storkolm
 */
public class PrestamoLibroEstFabTest {
    
    public PrestamoLibroEstFabTest() {
    }
    
    
    /**
     * Prueba #1
     * Metodo que se encarga de probar que todos los estudiantes<br>
     * puedan registrar prestamos
     */
    @Test
    public void testEjecutarPrestamoEstudiantes() {
        System.out.println("--------------------------Prueba 1--------------------------------------");
        String codBarras = "558770";
        String idBibliotecario = "1102515566";
        EstudianteJpaController controlEst = new EstudianteJpaController();
        List<Estudiante> estudiantes = controlEst.findEstudianteEntities();
        PrestamoLibroEstFab instance = new PrestamoLibroEstFab();
        boolean result = false;
        for (int i = 0; i < estudiantes.size(); i++) {
            result = instance.ejecutarPrestamo(codBarras, estudiantes.get(i).getCodestudiante(), idBibliotecario);
            if (result) {
                readAndDeletePrestamoTest(instance);
            } else {
                System.out.println("Error con el codigo de estudiante: " + estudiantes.get(i).getCodestudiante());
                break;
            }
        }
        assertEquals(true, result);
    }

    
    /**
     * Metodo que se encarga de mostrar los prestamos<br>
     * realizados por las pruebas y de borralos
     */
    private void readAndDeletePrestamoTest(PrestamoLibroEstFab instance) {
        if (instance != null) {
            try {
                PrestamoLibroDAOEst prestDAO = new PrestamoLibroDAOEst();
                List<PrestamoLibroEst> prestamo = prestDAO.readAllDAO();
                LibroJpaController libJPA = new LibroJpaController();
                Libro lib = libJPA.findLibro(prestamo.get(prestamo.size() - 1).getCodBarraLibro());
                System.out.println("*****************************************************************");
                System.out.println("El prestamo se realizo con exito\n");
                System.out.println("------Datos prestamo Libro   ");
                System.out.println("código estudiante: " + prestamo.get(prestamo.size() - 1).getCodEstudiante());
                System.out.println("fecha prestamo: " + prestamo.get(prestamo.size() - 1).getFechaPrestamo());
                System.out.println("fecha devolucion: " + prestamo.get(prestamo.size() - 1).getFechaDevolucion());
                System.out.println("destruyendo prestamo........\n");
                prestDAO.deleteDAO(prestamo.get(prestamo.size() - 1).getCodPrestamoLibroEst());
                System.out.println("------Datos Libro");
                System.out.println("codigo de barra: " + lib.getCodbarralibro());
                System.out.println("Disponibilidad: " + lib.getDisponibilidad());
                System.out.println("cambiando disponibilidad Libro..........");
                System.out.println("*****************************************************************");
                lib.setDisponibilidad("disponible");
                libJPA.edit(lib);
            } catch (Exception ex) {
                Logger.getLogger(PrestamoDiccionarioEstFabTest.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    /**
     * Prueba 3
     * Metodo que prueba cualquier tipo de entrada<br>
     * en el campo de codigo de barras
     */
    @Test
    public void testCodBarraNA() {
        System.out.println("--------------------------Prueba 3-----------------------------");
        String codBarras[] = {"", "gut1tg8dg127dg12d89129gd8gdqw98d1892981y2dy982d1982yd1",
            "82157378125637816523786123876382176328", "asjdgg44sad90d¿1'2}'12d'kdj23bfdu9h31e89hb12e9uh1de2uwdh9",
            "/**/ -*/*-//*-/ewd*-213e/*23/e-*d/23*-d/*-23d/*-23d/*-32d*-23/d*-2/3*e/wqd54qew4213efwefvefwk","123123123",
        "123123123","123123123","123123123","123123123","123123123","123123123","123123123","123123123","123123123","123123123"
        ,"123123123","123123123","123123123","123123123","123123123","123123123","123123123","123123123","123123123","123123123"};
        boolean result = false;
        PrestamoLibroEstFab instance = new PrestamoLibroEstFab();
        for (String codBarra : codBarras) {
            try {
                result = instance.ejecutarPrestamo(codBarra, "1760156", "1102515566");
                System.out.println("El prestamo se realizo");
                break;
            } catch (Exception e) {
            } catch (NoClassDefFoundError | ExceptionInInitializerError ex) {
                System.out.println("El prestamo no se realizo");
                result = false;
            }
        }
        assertEquals(false, result);
    }
    
    /**
     * Prueba 5
     * Metodo que se encarga de probar que todos los recursos<br>
     * que no esten disponibles no se puedan prestar
     * 
     */
    @Test
    public void testRecursoNoDisponible() {
        System.out.println("--------------------------Prueba 5-----------------------------");
        PrestamoLibroEstFab instance = new PrestamoLibroEstFab();
        LibroJpaController controlLib = new LibroJpaController();
        List<Libro> lib = controlLib.findLibroEntities();
        boolean result = false;
        for (int i = 0; i < lib.size(); i++) {
            if (!lib.get(i).getDisponibilidad().equalsIgnoreCase("disponible")) {
                try {
                    result = instance.ejecutarPrestamo(lib.get(i).getCodbarralibro(), "1760156", "1102515566");
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
        PrestamoLibroEstFab instance = new PrestamoLibroEstFab();
        LibroJpaController control = new LibroJpaController();
        List<Libro> recurso = control.findLibroEntities();
        boolean result = false;
        for (int i = 0; i < recurso.size(); i++) {
            if (recurso.get(i).getDisponibilidad().equalsIgnoreCase("disponible")) {
                try {
                    result = instance.ejecutarPrestamo(recurso.get(i).getCodbarralibro(), "1760156", "1102515566");
                    System.out.println("|\n|");
                    readAndDeletePrestamoTest(instance);
                } catch (Exception e) {
                    System.out.println("El prestamo no se realizo");
                    result = false;
                    break;
                }
            }else{
                System.out.println("|\n|");
                System.out.println("El libro con codigo de barra " + recurso.get(i).getCodbarralibro() + " se encuentra prestado o reservado");
            }
        }
        assertEquals(true, result);
    }
}
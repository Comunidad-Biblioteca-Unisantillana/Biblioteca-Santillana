/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moduloPrestamo.fabrica;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import moduloPrestamo.DAO.PrestamoRevistaDAOEst;
import moduloPrestamo.entitys.PrestamoRevistaEst;
import org.junit.Test;
import static org.junit.Assert.*;
import recursos.controllers.RevistaJpaController;
import recursos.entitys.Revista;
import usuarios.control.EstudianteJpaController;
import usuarios.entitys.Estudiante;


/**
 *
 * @author Storkolm
 */
public class PrestamoRevistaEstFabTest {
    
    public PrestamoRevistaEstFabTest() {
    }
    
    /**
     * Prueba #1
     * Metodo que se encarga de probar que todos los estudiantes<br>
     * puedan registrar prestamos
     */
    @Test
    public void testEjecutarPrestamoEstudiantes() {
        System.out.println("--------------------------Prueba 1--------------------------------------");
        String codBarras = "421263";
        String idBibliotecario = "1102515566";
        EstudianteJpaController controlEst = new EstudianteJpaController();
        List<Estudiante> estudiantes = controlEst.findEstudianteEntities();
        PrestamoRevistaEstFab instance = new PrestamoRevistaEstFab();
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
    private void readAndDeletePrestamoTest(PrestamoRevistaEstFab instance) {
        if (instance != null) {
            try {
                PrestamoRevistaDAOEst prestDAO = new PrestamoRevistaDAOEst();
                List<PrestamoRevistaEst> prestamo = prestDAO.readAllDAO();
                RevistaJpaController revJPA = new RevistaJpaController();
                Revista rev = revJPA.findRevista(prestamo.get(prestamo.size() - 1).getCodBarraRevista());
                System.out.println("*****************************************************************");
                System.out.println("El prestamo se realizo con exito\n");
                System.out.println("------Datos prestamo Revista   ");
                System.out.println("código estudiante: " + prestamo.get(prestamo.size() - 1).getCodEstudiante());
                System.out.println("fecha prestamo: " + prestamo.get(prestamo.size() - 1).getFechaPrestamo());
                System.out.println("fecha devolucion: " + prestamo.get(prestamo.size() - 1).getFechaDevolucion());
                System.out.println("destruyendo prestamo........\n");
                prestDAO.deleteDAO(prestamo.get(prestamo.size() - 1).getCodPrestamoRevistaEst());
                System.out.println("------Datos Revista");
                System.out.println("codigo de barra: " + rev.getCodbarrarevista());
                System.out.println("Disponibilidad: " + rev.getDisponibilidad());
                System.out.println("cambiando disponibilidad Revista..........");
                System.out.println("*****************************************************************");
                rev.setDisponibilidad("disponible");
                revJPA.edit(rev);
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
        PrestamoRevistaEstFab instance = new PrestamoRevistaEstFab();
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
        PrestamoRevistaEstFab instance = new PrestamoRevistaEstFab();
        RevistaJpaController controlRev = new RevistaJpaController();
        List<Revista> rev = controlRev.findRevistaEntities();
        boolean result = false;
        for (int i = 0; i < rev.size(); i++) {
            if (!rev.get(i).getDisponibilidad().equalsIgnoreCase("disponible")) {
                try {
                    result = instance.ejecutarPrestamo(rev.get(i).getCodbarrarevista(), "1760156", "1102515566");
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
        PrestamoRevistaEstFab instance = new PrestamoRevistaEstFab();
        RevistaJpaController control = new RevistaJpaController();
        List<Revista> recurso = control.findRevistaEntities();
        boolean result = false;
        for (int i = 0; i < recurso.size(); i++) {
            if (recurso.get(i).getDisponibilidad().equalsIgnoreCase("disponible")) {
                try {
                    result = instance.ejecutarPrestamo(recurso.get(i).getCodbarrarevista(), "1760156", "1102515566");
                    System.out.println("|\n|");
                    readAndDeletePrestamoTest(instance);
                } catch (Exception e) {
                    System.out.println("El prestamo no se realizo");
                    result = false;
                    break;
                }
            }else{
                System.out.println("|\n|");
                System.out.println("la revista con codigo de barra " + recurso.get(i).getCodbarrarevista() + " se encuentra prestado o reservado");
            }
        }
        assertEquals(true, result);
    }
}

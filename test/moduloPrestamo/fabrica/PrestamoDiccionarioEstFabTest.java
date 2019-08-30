/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moduloPrestamo.fabrica;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import moduloPrestamo.DAO.PrestamoDiccionarioDAOEst;
import moduloPrestamo.entitys.PrestamoDiccionarioEst;
import moduloPrestamo.modelo.GeneradorPrestamo;
import org.junit.Test;
import static org.junit.Assert.*;
import recursos1.controllers.DiccionarioJpaController;
import recursos1.entitys.Diccionario;
import usuario.controllers.EstudianteJpaController;
import usuario.entitys.Estudiante;

/**
 *
 * @author Storkolm
 */
public class PrestamoDiccionarioEstFabTest {

    public PrestamoDiccionarioEstFabTest() {
    }

    /**
     * Prueba #1
     * Meetodo que se encarga de probar que todos los estudiantes<br>
     * puedan registrar prestamos
     */
    @Test
    public void testEjecutarPrestamoEstudiantes() {
        System.out.println("--------------------------Prueba 1--------------------------------------");
        String codBarras = "254922";
        String idBibliotecario = "1102515566";
        EstudianteJpaController controlEst = new EstudianteJpaController();
        List<Estudiante> estudiantes = controlEst.findEstudianteEntities();
        PrestamoDiccionarioEstFab instance = new PrestamoDiccionarioEstFab();
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
    private void readAndDeletePrestamoTest(PrestamoDiccionarioEstFab instance) {
        if (instance != null) {
            try {
                PrestamoDiccionarioDAOEst prestDAO = new PrestamoDiccionarioDAOEst();
                List<PrestamoDiccionarioEst> prestamo = prestDAO.readAllDAO();
                DiccionarioJpaController diccJPA = new DiccionarioJpaController();
                Diccionario dicc = diccJPA.findDiccionario(prestamo.get(prestamo.size() - 1).getCodBarraDiccionario());
                System.out.println("*****************************************************************");
                System.out.println("El prestamo se realizo con exito\n");
                System.out.println("------Datos prestamo DICCIONARIO    ");
                System.out.println("código estudiante: " + prestamo.get(prestamo.size() - 1).getCodEstudiante());
                System.out.println("fecha prestamo: " + prestamo.get(prestamo.size() - 1).getFechaPrestamo());
                System.out.println("fecha devolucion: " + prestamo.get(prestamo.size() - 1).getFechaDevolucion());
                System.out.println("destruyendo prestamo........\n");
                prestDAO.deleteDAO(prestamo.get(prestamo.size() - 1).getCodPrestamoDiccionarioEst());
                System.out.println("------Datos DICCIONARIO");
                System.out.println("codigo de barra: " + dicc.getCodbarradiccionario());
                System.out.println("Disponibilidad: " + dicc.getDisponibilidad());
                System.out.println("cambiando disponibilidad DICCIONARIO..........");
                System.out.println("*****************************************************************");
                dicc.setDisponibilidad("disponible");
                diccJPA.edit(dicc);
            } catch (Exception ex) {
                Logger.getLogger(PrestamoDiccionarioEstFabTest.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * Prueba #2
     * Metodo que se encarga de 
     * Nota: este metodo no aplica para estas pruebas porque el estudiante no se verifica en la parte donde se hacen pruebas
     */
    @Test
    public void testEstudianteNA() {
        System.out.println("--------------------------Prueba 2-----------------------------");
        String codEstudiantes[] = {"", "gut1tg8dg127dg12d89129gd8gdqw98d1892981y2dy982d1982yd1",
            "82157378125637816523786123876382176328", "asjdgg44sad90d¿1'2}'12d'kdj23bfdu9h31e89hb12e9uh1de2uwdh9",
            "/**/ -*/*-//*-/ewd*-213e/*23/e-*d/23*-d/*-23d/*-23d/*-32d*-23/d*-2/3*e/wqd54qew4213efwefvefwk","123123123",
        "123123123","123123123","123123123","123123123","123123123","123123123","123123123","123123123","123123123","123123123"
        ,"123123123","123123123","123123123","123123123","123123123","123123123","123123123","123123123","123123123","123123123"};
        boolean result = true;
        for (String codEstudiante : codEstudiantes) {
            GeneradorPrestamo instance = new GeneradorPrestamo();
            try {
                instance.createPrestamo("254922", codEstudiante, "1102515566", "diccionario", "estudiante");
                result = true;
                break;
            } catch (ExceptionInInitializerError | NoClassDefFoundError ex) {
                result = false;
            } catch (Exception ex) {

            }
        }
        assertEquals(false, result);
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
        PrestamoDiccionarioEstFab instance = new PrestamoDiccionarioEstFab();
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
        PrestamoDiccionarioEstFab instance = new PrestamoDiccionarioEstFab();
        DiccionarioJpaController controlDicc = new DiccionarioJpaController();
        List<Diccionario> dicc = controlDicc.findDiccionarioEntities();
        boolean result = false;
        for (int i = 0; i < dicc.size(); i++) {
            if (!dicc.get(i).getDisponibilidad().equalsIgnoreCase("disponible")) {
                try {
                    result = instance.ejecutarPrestamo(dicc.get(i).getCodbarradiccionario(), "1760156", "1102515566");
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
     * Metodo que  se encarga de probar que se actualize la disponibilidad<br>
     * del recurso, tambien verifica que el sistema se encarga de colocar<br>
     * la fecha de prestamo y de devolucion
     */
    @Test
    public void testCrearPrestamoRecursos() {
        System.out.println("--------------------------Prueba 6,7,8-----------------------------------------");
        System.out.println("-------------------------------------------------------------------------------");
        PrestamoDiccionarioEstFab instance = new PrestamoDiccionarioEstFab();
        DiccionarioJpaController controlDicc = new DiccionarioJpaController();
        List<Diccionario> dicc = controlDicc.findDiccionarioEntities();
        boolean result = false;
        for (int i = 0; i < dicc.size(); i++) {
            if (dicc.get(i).getDisponibilidad().equalsIgnoreCase("disponible")) {
                try {
                    result = instance.ejecutarPrestamo(dicc.get(i).getCodbarradiccionario(), "1760156", "1102515566");
                    System.out.println("|\n|");
                    readAndDeletePrestamoTest(instance);
                } catch (Exception e) {
                    System.out.println("El prestamo no se realizo");
                    result = false;
                    break;
                }
            } else {
                System.out.println("|\n|");
                System.out.println("El diccionario con codigo de barra " + dicc.get(i).getCodbarradiccionario() + " se encuentra prestado o reservado");
            }
        }
        assertEquals(true, result);
    }

}

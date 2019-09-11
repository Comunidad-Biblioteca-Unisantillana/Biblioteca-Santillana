/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moduloPrestamo.fabrica;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import moduloPrestamo.DAO.PrestamoDiccionarioDAOProf;
import moduloPrestamo.entitys.PrestamoDiccionarioProf;
import moduloPrestamo.modelo.GeneradorPrestamo;
import org.junit.Test;
import static org.junit.Assert.*;
import recursos.controllers.DiccionarioJpaController;
import recursos.entitys.Diccionario;
import usuarios.control.ProfesorJpaController;
import usuarios.entitys.Profesor;

/**
 *
 * @author Storkolm
 */
public class PrestamoDiccionarioProfFabTest {

    public PrestamoDiccionarioProfFabTest() {
    }

    /**
     * Prueba #1
     */
     @Test
    public void testEjecutarPrestamo() {
        System.out.println("PRUEBA 1.... GENERAR PRESTAMO");
        String codBarras = "254925";
        String idBibliotecario = "1102515566";
        ProfesorJpaController controlProf = new ProfesorJpaController();
        List<Profesor> profesores = controlProf.findProfesorEntities();
        PrestamoDiccionarioProfFab instance = new PrestamoDiccionarioProfFab();
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
    private void destruirPrestamocreao(PrestamoDiccionarioProfFab instance) {
        if (instance != null) {
            try {
                PrestamoDiccionarioDAOProf prestDAO = new PrestamoDiccionarioDAOProf();
                List<PrestamoDiccionarioProf> prestamo = prestDAO.readAllDAO();
                DiccionarioJpaController diccJPA = new DiccionarioJpaController();
                Diccionario dicc = diccJPA.findDiccionario(prestamo.get(prestamo.size() - 1).getCodBarraDiccionario());
                prestDAO.deleteDAO(prestamo.get(prestamo.size() - 1).getCodPrestamoDiccionarioProf());
                dicc.setDisponibilidad("disponible");
                diccJPA.edit(dicc);
            } catch (Exception ex) {
                Logger.getLogger(PrestamoDiccionarioProfFabTest.class.getName()).log(Level.SEVERE, null, ex);
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
            instance.createPrestamo("254922", codProfesor, "1102515566", "diccionario", "profesor");
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
        String codBarras = "34234234";
        PrestamoDiccionarioProfFab instance = new PrestamoDiccionarioProfFab();
        boolean result = false;
        try {
            result = instance.ejecutarPrestamo(codBarras, "946789878", "1102515566");
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
        PrestamoDiccionarioProfFab instance = new PrestamoDiccionarioProfFab();
        DiccionarioJpaController controlDicc = new DiccionarioJpaController();
        List<Diccionario> dic = controlDicc.findDiccionarioEntities();
        boolean result = false;
        for (int i = 0; i < dic.size(); i++) {
            if (!dic.get(i).getDisponibilidad().equalsIgnoreCase("disponible")) {
                try {
                    result = instance.ejecutarPrestamo(dic.get(i).getCodbarradiccionario(), "946789878", "1102515566");
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
        System.out.println("PRUEBAS DEL 6 AL 8...." );
        PrestamoDiccionarioProfFab instance = new PrestamoDiccionarioProfFab();
        DiccionarioJpaController controlDicc = new DiccionarioJpaController();
        List<Diccionario> dicc = controlDicc.findDiccionarioEntities();
        boolean result = false;
        for (int i = 0; i < dicc.size(); i++) {
            if (dicc.get(i).getDisponibilidad().equalsIgnoreCase("disponible")) {
                try {
                    result = instance.ejecutarPrestamo(dicc.get(i).getCodbarradiccionario(), "946789878", "1102515566");
                    destruirPrestamocreao(instance);
                } catch (Exception e) {
                    System.out.println("El prestamo no se realizo");
                    result = false;
                    break;
                }
            }else{
                System.out.println("|\n|");
                System.out.println("El diccionario con codigo de barra " + dicc.get(i).getCodbarradiccionario() + " se encuentra prestado o reservado");
            }
        }
        assertEquals(true, result);
    }
    

}
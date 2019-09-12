/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Prestamo;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import moduloPrestamo.DAO.PrestamoPeriodicoDAOProf;
import moduloPrestamo.entitys.PrestamoPeriodicoProf;
import moduloPrestamo.fabrica.PrestamoPeriodicoProfFab;
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
     * Prueba #1 Genera el prestamo
     */
    @Test
    public void testEjecutarPrestamoEstudiantes() {
        System.out.println("PRUEBA 1..... GENERAR PRESTAMO");
        String codBarras = "158578";
        String idBibliotecario = "1102515566";
        ProfesorJpaController controlProf = new ProfesorJpaController();
        List<Profesor> profesores = controlProf.findProfesorEntities();
        PrestamoPeriodicoProfFab instance = new PrestamoPeriodicoProfFab();
        boolean result = false;
        for (int i = 0; i < profesores.size(); i++) {

            result = instance.ejecutarPrestamo(codBarras, profesores.get(i).getIdprofesor(), idBibliotecario);
            if (result) {
                System.out.println("bmmn ns ba bnavdnvasnbvabndvsabn");
                destruirPrestamocreao(instance);
            } else {
                System.out.println("Error con el codigo de profesor: " + profesores.get(i).getIdprofesor());
                break;
            }
            assertEquals(true, result);
        }

    }

    /**
     *
     * pruebas
     */
    private void destruirPrestamocreao(PrestamoPeriodicoProfFab instance) {
        if (instance != null) {
            try {
                PrestamoPeriodicoDAOProf prestDAO = new PrestamoPeriodicoDAOProf();
                List<PrestamoPeriodicoProf> prestamo = prestDAO.readAllDAO();
                PeriodicoJpaController periJPA = new PeriodicoJpaController();
                Periodico peri = periJPA.findPeriodico(prestamo.get(prestamo.size() - 1).getCodBarraPeriodico());
                prestDAO.deleteDAO(prestamo.get(prestamo.size() - 1).getCodPrestamoPeriodicoProf());
                peri.setDisponibilidad("disponible");
                periJPA.edit(peri);
            } catch (Exception ex) {
                Logger.getLogger(PrestamoPeriodicoProfFabTest.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * Prueba #2 Verifica que el usuario no exista y no permite el prestamo.
     *
     * Prueba #10 Verifica que los datos ingresados solo sean numericos
     */
    @Test
    public void testProfesorNA() {
        System.out.println("PRUEBA 2.... PROFESOR NO ENCONTRADO");
        String codProfesores[] = {" ", "gd1",
            "821573781256378", "asjdggwdh9",
            "/**/vefwk", "123123123"};
        boolean result = true;
        for (String codProfesor : codProfesores) {
            GeneradorPrestamo instance = new GeneradorPrestamo();
            try {
                instance.createPrestamo("158572", codProfesor, "1102515566", "periodico", "profesor");
                result = true;
                break;
            } catch (ExceptionInInitializerError | NoClassDefFoundError ex) {
                result = false;
            } catch (Exception ex) {

            }

            assertEquals(false, result);
        }

    }

    /**
     * Prueba 3: Verifica que el codigo de barras no exista y no permite el
     * prestamo.
     *
     * Prueba 9: Solo permite caracteres numericos en el codigo de barras.
     */
    @Test
    public void testCodBarraNA() {
        System.out.println("PRUEBA 3... CODIGO DE BARRAS NO ENCONTRADO");
        String codBarra[] = {" ", "gd1",
            "821573781256378", "asjdggwdh9",
            "/**/vefwk", "123123123"};
        boolean result = false;
        PrestamoPeriodicoProfFab instance = new PrestamoPeriodicoProfFab();
        for (String codBarras : codBarra) {
            try {
                result = instance.ejecutarPrestamo(codBarras, "1760156", "1102515566");
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
     * Prueba 4 Verifica que todos los campos esten completos.
     */
    public void testCamposVacios() {
        System.out.println("ejecutarDevolucion");
        String codBarras[] = {" ", "32215648651", "bcnh*-+54515"};
        String codUsuario[] = {"C03085988", " ", "99393293"};
        String codBibliotecario[] = {"1102523252", " ", "1102515566"};
        boolean result = true;
        for (int i = 0; i < codBarras.length; i++) {
            PrestamoPeriodicoProfFab instance = new PrestamoPeriodicoProfFab();
            for (int j = 0; j < codUsuario.length; j++) {
                for (int k = 0; k < codBibliotecario.length; k++) {
                    result = instance.ejecutarPrestamo(codBarras[i], codUsuario[j], codBibliotecario[k]);
                }
            }
            assertEquals(false, result);
        }
    }

    /**
     * Prueba 5 verifica que el recurso este disponible, de lo contrario no hara
     * el prestamo.
     */
    @Test
    public void testRecursoNoDisponible() {
        System.out.println("PRUEBA 5.... RECURSO NO DISPONIBLE");
        PrestamoPeriodicoProfFab instance = new PrestamoPeriodicoProfFab();
        PeriodicoJpaController controlib = new PeriodicoJpaController();
        List<Periodico> peri = controlib.findPeriodicoEntities();
        boolean result = false;
        for (int i = 0; i < peri.size(); i++) {
            if (!peri.get(i).getDisponibilidad().equalsIgnoreCase("disponible")) {
                try {
                    result = instance.ejecutarPrestamo(peri.get(i).getCodbarraperiodico(), "946789878", "1102515566");
                    System.out.println("El prestaamo se realizo");
                    destruirPrestamocreao(instance);
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
        PrestamoPeriodicoProfFab instance = new PrestamoPeriodicoProfFab();
        PeriodicoJpaController controlPeri = new PeriodicoJpaController();
        List<Periodico> perico = controlPeri.findPeriodicoEntities();
        boolean result = false;
        for (int i = 0; i < perico.size(); i++) {
            if (perico.get(i).getDisponibilidad().equalsIgnoreCase("disponible")) {
                try {
                    result = instance.ejecutarPrestamo(perico.get(i).getCodbarraperiodico(), "90298374", "1102515566");
                    destruirPrestamocreao(instance);
                } catch (Exception e) {
                    System.out.println("El prestamo no se realizo");
                    result = false;
                    break;
                }
            } else {
                System.out.println("El periodico con codigo de barra " + perico.get(i).getCodbarraperiodico() + " se encuentra prestado o reservado");
            }
        }
        assertEquals(true, result);
    }
    
    
    /*
    *Prueba 11:
    * No permitir hacer prestamo a un estudiante multado.
    *
    */
    
//    public void GenerarPrestamoMulta(){
//        
//        System.out.println("PRUEBA 12..... GENERAR PRESTAMO");
//        String codBarras = "254898";
//        String idBibliotecario = "1102515566";
//        ProfesorJpaController controlProf = new ProfesorJpaController();
//        List<Profesor> profesores = controlProf.findProfesorEntities();
//        PrestamoPeriodicoProfFab instance = new PrestamoPeriodicoProfFab();
//        boolean result = true;
//        for (int i = 0; i < profesores.size(); i++) {
//            result = instance.ejecutarPrestamo(codBarras, profesores.get(i).getIdprofesor(), idBibliotecario);
//            if (result) {
//                destruirPrestamocreao(instance);
//            } else {
//                System.out.println("Error con el codigo de profesor: " + profesores.get(i).getIdprofesor());
//                break;
//            }
//            assertEquals(false, result);
//        }
}


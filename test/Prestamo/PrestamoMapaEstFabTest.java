/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Prestamo;

import java.util.List;
import moduloPrestamo.DAO.PrestamoMapaDAOEst;
import moduloPrestamo.entitys.PrestamoMapaEst;
import moduloPrestamo.fabrica.PrestamoMapaEstFab;
import moduloPrestamo.modelo.GeneradorPrestamo;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import recursos.controllers.MapaJpaController;
import recursos.entitys.Mapa;
import usuarios.control.EstudianteJpaController;
import usuarios.entitys.Estudiante;

/**
 *
 * @author Storkolm
 */
public class PrestamoMapaEstFabTest {
    
    /**
     * Prueba #1 Genera el prestamo
     */
    @Test
    public void testEjecutarPrestamoEstudiantes() {
        System.out.println("PRUEBA 1..... GENERAR PRESTAMO");
        String codBarras = "459865";
        String idBibliotecario = "1102515566";
        EstudianteJpaController controlProf = new EstudianteJpaController();
        List<Estudiante> profesores = controlProf.findEstudianteEntities();
        PrestamoMapaEstFab instance = new PrestamoMapaEstFab();
        boolean result = false;
        for (int i = 0; i < profesores.size(); i++) {
            result = instance.ejecutarPrestamo(codBarras, profesores.get(i).getCodestudiante(), idBibliotecario);
            if (result) {
                destruirPrestamocreao(instance);
            } else {
                System.out.println("Error con el codigo de profesor: " + profesores.get(i).getCodestudiante());
                break;
            }
            assertEquals(true, result);
        }

    }

    /**
     *
     * pruebas
     */
    private void destruirPrestamocreao(PrestamoMapaEstFab instance) {
        if (instance != null) {
            try {
                PrestamoMapaDAOEst prestDAO = new PrestamoMapaDAOEst();
                List<PrestamoMapaEst> prestamo = prestDAO.readAllDAO();
                MapaJpaController mapJPA = new MapaJpaController();
                Mapa map = mapJPA.findMapa(prestamo.get(prestamo.size() - 1).getCodBarraMapa());
                prestDAO.deleteDAO(prestamo.get(prestamo.size() - 1).getCodPrestamoMapaEst());
                map.setDisponibilidad("disponible");
                mapJPA.edit(map);
            } catch (Exception ex) {
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
                instance.createPrestamo("459864", codProfesor, "1102515566", "mapa", "profesor");
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
     * Prueba 3:
     * Verifica que el codigo de barras no exista y no permite el prestamo.
     * 
     * Prueba 9:
     * Solo permite caracteres numericos en el codigo de barras.
     */
    @Test
    public void testCodBarraNA() {
        System.out.println("PRUEBA 3... CODIGO DE BARRAS NO ENCONTRADO");
        String codBarra[] = {" ", "gut1tg8yd1",
            "82157378125637828", "asjdg2uwdh9",
            "/**/ -*/*wk", "123123123"};
        boolean result = false;
        PrestamoMapaEstFab instance = new PrestamoMapaEstFab();
        for (String codBarras : codBarra) {
            try {
                result = instance.ejecutarPrestamo(codBarras, "946789878", "1102515566");
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
     * Prueba 4
     * Verifica que todos los campos esten completos.
     */
     public void testCamposVacios() {
        System.out.println("ejecutarDevolucion");
        String codBarras[] = {" ", "32215648651", "bcnh*-+54515"};
        String codUsuario[] = {"C03085988", " ", "99393293"};
        String codBibliotecario[] = {"1102523252", " ", "1102515566"};
        boolean result =true;
        for(int i=0; i<codBarras.length ;i++){
            PrestamoMapaEstFab instance = new PrestamoMapaEstFab();
            for(int j=0; j<codUsuario.length;j++){
                for(int k=0 ;k<codBibliotecario.length;k++){
                    result = instance.ejecutarPrestamo(codBarras[i], codUsuario[j], codBibliotecario[k]);
                }
            }
            assertEquals(false, result);
        }
    }

    /**
     * Prueba 5
     * verifica que el recurso este disponible, de lo contrario no hara el prestamo.
     */
    @Test
    public void testRecursoNoDisponible() {
        System.out.println("PRUEBA 5.... RECURSO NO DISPONIBLE");
        PrestamoMapaEstFab instance = new PrestamoMapaEstFab();
        MapaJpaController contromap = new MapaJpaController();
        List<Mapa> map = contromap.findMapaEntities();
        boolean result = false;
        for (int i = 0; i < map.size(); i++) {
            if (!map.get(i).getDisponibilidad().equalsIgnoreCase("disponible")) {
                try {
                    result = instance.ejecutarPrestamo(map.get(i).getCodbarramapa(), "946789878", "1102515566");
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
        PrestamoMapaEstFab instance = new PrestamoMapaEstFab();
        MapaJpaController controlMap = new MapaJpaController();
        List<Mapa> map = controlMap.findMapaEntities();
        boolean result = false;
        for (int i = 0; i < map.size(); i++) {
            if (map.get(i).getDisponibilidad().equalsIgnoreCase("disponible")) {
                try {
                    result = instance.ejecutarPrestamo(map.get(i).getCodbarramapa(), "90298374", "1102515566");
                    destruirPrestamocreao(instance);
                } catch (Exception e) {
                    System.out.println("El prestamo no se realizo");
                    result = false;
                    break;
                }
            } else {
                System.out.println("|\n|");
                System.out.println("El mapa con codigo de barra " + map.get(i).getCodbarramapa() + " se encuentra prestado o reservado");
            }
        }
        assertEquals(true, result);
    }
}

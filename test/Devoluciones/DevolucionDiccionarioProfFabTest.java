package Devoluciones;

import controller.exceptions.NonexistentEntityException;
import org.junit.*;
import static org.junit.Assert.*;

import java.util.List;
import moduloDevolucion.DAO.DevolucionDiccionarioDAOProf;
import moduloDevolucion.entitys.DevolucionDiccionarioProf;
import moduloDevolucion.fabrica.DevolucionDiccionarioProfFab;
import moduloPrestamo.DAO.PrestamoDiccionarioDAOProf;
import moduloPrestamo.entitys.PrestamoDiccionarioProf;
import recursos.controllers.DiccionarioJpaController;

import recursos.entitys.Diccionario;

/**
 *
 * @author Felipe
 */
public class DevolucionDiccionarioProfFabTest {

    public DevolucionDiccionarioProfFabTest() {
    }

    /**
     * Test para verificar si devuelve un recurso prestado. NOTA: para realizar
     * este test,se debe tener en cuenta que el codBarras del recurso debe estar
     * prestado.
     */
    @Test
    public void testEjecutarDevolucion() {
        System.out.println("ejecutarDevolucion");
        String codBarras = "254906";
        String idBibliotecario = "1102515566";
        String estadoRecurso = "bueno";
        DevolucionDiccionarioProfFab instance = new DevolucionDiccionarioProfFab();
        boolean expResult = true;
        boolean result = instance.ejecutarDevolucion(codBarras, idBibliotecario, estadoRecurso);
        if (result) {
            try {
                //borramos devolucion
                DevolucionDiccionarioDAOProf devolucionDAO = new DevolucionDiccionarioDAOProf();
                List<DevolucionDiccionarioProf> devolucion = devolucionDAO.readAllDAO();
                devolucionDAO.deleteDAO(devolucion.get(devolucion.size() - 1).getCodDevolucionDiccionarioProf());
                //editamos la disponibilidad del recurso a prestado
                DiccionarioJpaController diccionarioJPA = new DiccionarioJpaController();
                Diccionario diccionario = diccionarioJPA.findDiccionario(codBarras);
                diccionario.setDisponibilidad("prestado");
                diccionarioJPA.edit(diccionario);
                //editamos el prestamo
                PrestamoDiccionarioDAOProf prestamoDAO = new PrestamoDiccionarioDAOProf();
                List<PrestamoDiccionarioProf> prestamos = prestamoDAO.readAllDAO();
                prestamos.get(prestamos.size() - 1).setDevuelto("no");
                prestamoDAO.updateDAO(prestamos.get(prestamos.size() - 1));

            } catch (NonexistentEntityException ex) {
            } catch (Exception ex) {
            }
        }
        assertEquals(expResult, result);
    }

    @Test
    public void testCamposVacios() {
        System.out.println("ejecutarDevolucion");
        String codBarras[] = {" ", "32215648651", "bcnh*-+54515"};
        String idBibliotecario[] = {"1102515566", " ", "1102515566"};
        String estadoRecurso[] = {"bueno", "buen5645o", " "};
        boolean result = true;
        for (int i = 0; i < codBarras.length; i++) {
            DevolucionDiccionarioProfFab instance = new DevolucionDiccionarioProfFab();
            for (int j = 0; j < idBibliotecario.length; j++) {
                for (int k = 0; k < estadoRecurso.length; k++) {
                    result = instance.ejecutarDevolucion(codBarras[i], idBibliotecario[j], estadoRecurso[k]);
                }
            }
            assertEquals(false, result);
        }
    }

    @Test
    public void testCodbarrasNA() {
        System.out.println("ejecutarDevolucion");
        String idBibliotecario = "1102515566";
        String estadoRecurso = "bueno";
        String codBarras[] = {"gut1tg8dg127dg12d89129gd8gdqw98d1892981y2dy982d1982yd1",
            "821573781256378", "asjdgg44sad90d�1'2}'12d'kdj23bfdu9h31e89hb12e9uh1de2uwdh9",
            "/**/ -*/*-//*-/ewd*", "123123123"};
        boolean result = true;
        for (String codBarra : codBarras) {
            DevolucionDiccionarioProfFab instance = new DevolucionDiccionarioProfFab();
            try {
                instance.ejecutarDevolucion(codBarra, idBibliotecario, estadoRecurso);
                result = true;
                break;
            } catch (ExceptionInInitializerError | NoClassDefFoundError ex) {
                result = false;
            } catch (Exception ex) {

            }
            assertEquals(false, result);
        }

    }

    @Test
    public void testdisponibilidad() {
        System.out.println("PRUEBA 4.... ");
        DiccionarioJpaController controlib = new DiccionarioJpaController();
        List<Diccionario> lib = controlib.findDiccionarioEntities();
        boolean result = false;
        for (int i = 0; i < lib.size(); i++) {
            if (lib.get(i).getCodbarradiccionario().equalsIgnoreCase("254920")) {
                if (lib.get(i).getDisponibilidad().equalsIgnoreCase("disponible")) {
                    try {
                        result = true;
                        break;
                    } catch (Exception e) {
                    } catch (NoClassDefFoundError | ExceptionInInitializerError ex) {
                        result = false;
                    }
                }
            }
        }
        assertEquals(true, result);

    }

}

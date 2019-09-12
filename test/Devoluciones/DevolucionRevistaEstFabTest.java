package moduloDevolucion.fabrica;

import controller.exceptions.NonexistentEntityException;
import org.junit.*;
import static org.junit.Assert.*;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import moduloDevolucion.DAO.DevolucionRevistaDAOEst;
import moduloDevolucion.entitys.DevolucionRevistaEst;
import moduloDevolucion.fabrica.DevolucionRevistaEstFab;
import moduloPrestamo.DAO.PrestamoRevistaDAOEst;
import moduloPrestamo.entitys.PrestamoRevistaEst;
import recursos.controllers.LibroJpaController;

import recursos.controllers.RevistaJpaController;
import recursos.entitys.Libro;
import recursos.entitys.Revista;

/**
 *
 * @author Felipe
 */
public class DevolucionRevistaEstFabTest {

    public DevolucionRevistaEstFabTest() {
    }

    /**
     * Test para verificar si devuelve un recurso prestado. NOTA: para realizar
     * este test,se debe tener en cuenta que el codBarras del recurso debe estar
     * prestado.
     */
    @Test
    public void testEjecutarDevolucion() {
        System.out.println("ejecutarDevolucion");
        String codBarras = "421264";
        String idBibliotecario = "1102515566";
        String estadoRecurso = "bueno";
        DevolucionRevistaEstFab instance = new DevolucionRevistaEstFab();
        boolean expResult = true;
        boolean result = instance.ejecutarDevolucion(codBarras, idBibliotecario, estadoRecurso);
        if (result) {
            try {
                //borramos devolucion
                DevolucionRevistaDAOEst devolucionDAO = new DevolucionRevistaDAOEst();
                List<DevolucionRevistaEst> devolucion = devolucionDAO.readAllDAO();
                devolucionDAO.deleteDAO(devolucion.get(devolucion.size() - 1).getCodDevolucionRevistaEst());
                //editamos la disponibilidad del recurso a prestado
                RevistaJpaController revistaJPA = new RevistaJpaController();
                Revista revista = revistaJPA.findRevista(codBarras);
                revista.setDisponibilidad("prestado");
                revistaJPA.edit(revista);
                //editamos el prestamo
                PrestamoRevistaDAOEst prestamoDAO = new PrestamoRevistaDAOEst();
                List<PrestamoRevistaEst> prestamos = prestamoDAO.readAllDAO();
                prestamos.get(prestamos.size()-1).setDevuelto("no");
                prestamoDAO.updateDAO(prestamos.get(prestamos.size()-1));
                
            } catch (NonexistentEntityException ex) {
            } catch (Exception ex) {
            }
        }
        assertEquals(expResult, result);
    }

    @Test
    public void testCamposVacios() {
        System.out.println("ejecutarDevolucion");
        String codBarras[] = {" ", "32215648651", "421259"};
        String idBibliotecario[] = {"1102515566", " ", "1102515566"};
        String estadoRecurso[] = {"bueno", "buen5645o", " "};
        boolean result = true;
        for (int i = 0; i < codBarras.length; i++) {
            DevolucionRevistaEstFab instance = new DevolucionRevistaEstFab();
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
            "/**/ -*/*-//*-/ewd*", "NA"};
        boolean result = true;
        for (String codBarra : codBarras) {
            DevolucionRevistaEstFab instance = new DevolucionRevistaEstFab();
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
        RevistaJpaController controlib = new RevistaJpaController();
        List<Revista> lib = controlib.findRevistaEntities();
        boolean result = false;
        for (int i = 0; i < lib.size(); i++) {
            if (lib.get(i).getCodbarrarevista().equalsIgnoreCase("421257")) {
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

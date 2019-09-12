package Devoluciones;

import controller.exceptions.NonexistentEntityException;
import org.junit.*;
import static org.junit.Assert.*;

import java.util.List;
import moduloDevolucion.DAO.DevolucionEnciclopediaDAOProf;
import moduloDevolucion.entitys.DevolucionEnciclopediaProf;
import moduloDevolucion.fabrica.DevolucionEnciclopediaProfFab;
import moduloPrestamo.DAO.PrestamoEnciclopediaDAOProf;
import moduloPrestamo.entitys.PrestamoEnciclopediaProf;

import recursos.controllers.EnciclopediaJpaController;
import recursos.entitys.Enciclopedia;

/**
 *
 * @author Felipe
 */
public class DevolucionEnciclopediaProfFabTest {

    public DevolucionEnciclopediaProfFabTest() {
    }

    /**
     * Test para verificar si devuelve un recurso prestado.
     * NOTA: para realizar este test,se debe tener en cuenta que el codBarras del recurso debe estar prestado.
     */
     @Test
    public void testEjecutarDevolucion() {
        System.out.println("ejecutarDevolucion");
        String codBarras = "484966";
        String idBibliotecario = "1102515566";
        String estadoRecurso = "bueno";
        DevolucionEnciclopediaProfFab instance = new DevolucionEnciclopediaProfFab();
        boolean expResult = true;
        boolean result = instance.ejecutarDevolucion(codBarras, idBibliotecario, estadoRecurso);
        if (result) {
            try {
                //borramos devolucion
                DevolucionEnciclopediaDAOProf devolucionDAO = new DevolucionEnciclopediaDAOProf();
                List<DevolucionEnciclopediaProf> devolucion = devolucionDAO.readAllDAO();
                devolucionDAO.deleteDAO(devolucion.get(devolucion.size() - 1).getCodDevolucionEnciclopediaProf());
                //editamos la disponibilidad del recurso a prestado
                EnciclopediaJpaController enciclopediaJPA = new EnciclopediaJpaController();
                Enciclopedia enciclopedia = enciclopediaJPA.findEnciclopedia(codBarras);
                enciclopedia.setDisponibilidad("prestado");
                enciclopediaJPA.edit(enciclopedia);
                //editamos el prestamo
                PrestamoEnciclopediaDAOProf prestamoDAO = new PrestamoEnciclopediaDAOProf();
                List<PrestamoEnciclopediaProf> prestamos = prestamoDAO.readAllDAO();
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
        boolean result =true;
        for(int i=0; i<codBarras.length ;i++){
            DevolucionEnciclopediaProfFab instance = new DevolucionEnciclopediaProfFab();
            for(int j=0; j<idBibliotecario.length;j++){
                for(int k=0 ;k<estadoRecurso.length;k++){
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
            DevolucionEnciclopediaProfFab instance = new DevolucionEnciclopediaProfFab();
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
        EnciclopediaJpaController controlib = new EnciclopediaJpaController();
        List<Enciclopedia> lib = controlib.findEnciclopediaEntities();
        boolean result = false;
        for (int i = 0; i < lib.size(); i++) {
            if (lib.get(i).getCodbarraenciclopedia().equalsIgnoreCase("484964")) {
                if(lib.get(i).getDisponibilidad().equalsIgnoreCase("disponible"))
                try {
                    result = true;
                    break;
                } catch (Exception e) {
                } catch (NoClassDefFoundError | ExceptionInInitializerError ex) {
                    result = false;
                }
            }
        }
        assertEquals(true, result);
    
}
    
}

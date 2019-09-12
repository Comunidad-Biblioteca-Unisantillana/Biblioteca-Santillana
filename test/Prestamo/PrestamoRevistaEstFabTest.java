
package Prestamo;

import java.util.List;
import moduloPrestamo.DAO.PrestamoRevistaDAOEst;
import moduloPrestamo.entitys.PrestamoRevistaEst;
import moduloPrestamo.fabrica.PrestamoRevistaEstFab;
import moduloPrestamo.modelo.GeneradorPrestamo;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import recursos.controllers.RevistaJpaController;
import recursos.entitys.Revista;
import usuarios.control.EstudianteJpaController;
import usuarios.entitys.Estudiante;

/**
 *
 * @author Storkolm
 */
public class PrestamoRevistaEstFabTest {
      
    /**
     * Prueba #1 Genera el prestamo
     */
    @Test
    public void testEjecutarPrestamoEstudiantes() {
        System.out.println("PRUEBA 1..... GENERAR PRESTAMO");
        String codBarras = "421266";
        String idBibliotecario = "1102515566";
        EstudianteJpaController controlProf = new EstudianteJpaController();
        List<Estudiante> profesores = controlProf.findEstudianteEntities();
        PrestamoRevistaEstFab instance = new PrestamoRevistaEstFab();
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
    private void destruirPrestamocreao(PrestamoRevistaEstFab instance) {
        if (instance != null) {
            try {
                PrestamoRevistaDAOEst prestDAO = new PrestamoRevistaDAOEst();
                List<PrestamoRevistaEst> prestamo = prestDAO.readAllDAO();
                RevistaJpaController revJPA = new RevistaJpaController();
                Revista rev = revJPA.findRevista(prestamo.get(prestamo.size() - 1).getCodBarraRevista());
                prestDAO.deleteDAO(prestamo.get(prestamo.size() - 1).getCodPrestamoRevistaEst());
                rev.setDisponibilidad("disponible");
                revJPA.edit(rev);
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
                instance.createPrestamo("421266", codProfesor, "1102515566", "revista", "profesor");
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
        String codBarra[] = {"", "gut1tg8dg127dg12d89129gd8gdqw98d1892981y2dy982d1982yd1",
            "82157378125637816523786123876382176328", "asjdgg44sad90d¿1'2}'12d'kdj23bfdu9h31e89hb12e9uh1de2uwdh9",
            "/**/ -*/*-//*-/ewd*-213e/*23/e-*d/23*-d/*-23d/*-23d/*-32d*-23/d*-2/3*e/wqd54qew4213efwefvefwk", "123123123"};
        boolean result = false;
        PrestamoRevistaEstFab instance = new PrestamoRevistaEstFab();
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
            PrestamoRevistaEstFab instance = new PrestamoRevistaEstFab();
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
        PrestamoRevistaEstFab instance = new PrestamoRevistaEstFab();
        RevistaJpaController controrev = new RevistaJpaController();
        List<Revista> rev = controrev.findRevistaEntities();
        boolean result = false;
        for (int i = 0; i < rev.size(); i++) {
            if (!rev.get(i).getDisponibilidad().equalsIgnoreCase("disponible")) {
                try {
                    result = instance.ejecutarPrestamo(rev.get(i).getCodbarrarevista(), "946789878", "1102515566");
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
        PrestamoRevistaEstFab instance = new PrestamoRevistaEstFab();
        RevistaJpaController controlRev = new RevistaJpaController();
        List<Revista> rev = controlRev.findRevistaEntities();
        boolean result = false;
        for (int i = 0; i < rev.size(); i++) {
            if (rev.get(i).getDisponibilidad().equalsIgnoreCase("disponible")) {
                try {
                    result = instance.ejecutarPrestamo(rev.get(i).getCodbarrarevista(), "90298374", "1102515566");
                    destruirPrestamocreao(instance);
                } catch (Exception e) {
                    System.out.println("El prestamo no se realizo");
                    result = false;
                    break;
                }
            } else {
                System.out.println("|\n|");
                System.out.println("El mapa con codigo de barra " + rev.get(i).getCodbarrarevista()+ " se encuentra prestado o reservado");
            }
        }
        assertEquals(true, result);
    }
}

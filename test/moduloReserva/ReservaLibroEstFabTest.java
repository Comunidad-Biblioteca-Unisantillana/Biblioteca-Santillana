package moduloReserva;

import controller.exceptions.NonexistentEntityException;
import java.util.List;
import moduloReserva.DAO.ReservaColgenDAOEst;

import moduloReserva.fabrica.ReservaColgenEstFab;
import moduloReserva.modelo.GeneradorReserva;
import org.junit.Assert;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import recursos.controllers.CategoriaColeccionJpaController;
import recursos.controllers.LibroJpaController;
import recursos.entitys.CategoriaColeccion;
import recursos.entitys.Libro;
import usuarios.control.EstudianteJpaController;
import usuarios.entitys.Estudiante;

/**
 *
 * @author Felipe
 */
public class ReservaLibroEstFabTest {

    /**
     * En este metodo se ejecutan todas las pruebas
     */
    @Test
    public void testCodigo() {
        testCodBarraNA();
        pruebas_3_4_12();
        testCodBarraNA();
        testCodbarrasEst();
        codigoCategoriaColeccion();
        testdisponibilidadPrestado();
        testdisponibilidad();
    }

    /**
     * Prueba 2 Metodo que prueba cualquier tipo de entrada<br>
     * en el campo de codigo de barras
     */
    public void testCodBarraNA() {
        System.out.println("--------------------------Prueba 2-----------------------------");
        String codBarras[] = {"", "gut1tg8dg127dg12d89129gd8gdqw98d1892981y2dy982d1982yd1",
            "82157378125637816523786123876382176328", "asjdgg44sad90d¿1'2}'12d'kdj23bfdu9h31e89hb12e9uh1de2uwdh9",
            "/**/ -*/*-//*-/ewd*-213e/*23/e-*d/23*-d/*-23d/*-23d/*-32d*-23/d*-2/3*e/wqd54qew4213efwefvefwk", "123123123",
            "123123123", "123123123", "123123123", "123123123", "123123123", "123123123", "123123123", "123123123", "123123123", "123123123",
            "123123123", "123123123", "123123123", "123123123", "123123123", "123123123", "123123123", "123123123", "123123123", "123123123"};
        boolean result = false;
        ReservaColgenEstFab instance = new ReservaColgenEstFab();
        for (String codBarra : codBarras) {
            try {
                result = instance.ejecutarReserva(codBarra, "1760156", "1102515566");
                System.out.println("La reserva se realizo");
                break;
            } catch (Exception e) {
            } catch (NoClassDefFoundError | ExceptionInInitializerError ex) {
                System.out.println("La reserva no se realizo");
                result = false;
            }
        }
        assertEquals(false, result);
    }

    /**
     * Prueba 3 que se encarga de verificar que el usuario exista.
     * Prueba 4 que se encarga de verificar que el lubro exista y que se pueda reservar
     * Prueba 12 que se encarga de verificar que la disponibilidad se actualize
     */
    public void pruebas_3_4_12() {
        System.out.println("PRUEBA 3 y 4");
        EstudianteJpaController controlest = new EstudianteJpaController();
        List<Estudiante> est = controlest.findEstudianteEntities();
        GeneradorReserva generador = new GeneradorReserva();
        LibroJpaController libroJPA = new LibroJpaController();
        List<Libro> libros = libroJPA.findLibroEntities();
        boolean resultado = true;
        String codigoLibro = "NA";
        //buscamos un libro que se pueda reservar
        for (int i = 0; i < libros.size(); i++) {
            if (libros.get(i).getDisponibilidad().equalsIgnoreCase("prestado")
                    && (libros.get(i).getCodcategoriacoleccion().getCodcategoriacoleccion().equalsIgnoreCase("colgen"))) {
                codigoLibro = libros.get(i).getCodbarralibro();
                break;
            }else{
                System.out.println("El libro no se puede reservar");
            }
        }
        
        if (!codigoLibro.equalsIgnoreCase("NA")) {//si no se encuentra un libro las pruebas fallan
            for (int i = 0; i < est.size(); i++) {
                System.out.println("ejecutando prueba");
                resultado = generador.createReserva(codigoLibro, est.get(i).getCodestudiante(), "estudiante", "1102515566");
                if (resultado) {
                    try {
                        System.out.println("borrando los cambios que se hicieron en las pruebas");
                        ReservaColgenDAOEst reservaDAO = new ReservaColgenDAOEst();
                        reservaDAO.deleteDAO(codigoLibro, est.get(i).getCodestudiante());
                        Libro libro = libroJPA.findLibro(codigoLibro);
                        libro.setDisponibilidad("prestado");
                        libroJPA.edit(libro);
                        if (i == 10) {
                            break;
                        }
                    } catch (NonexistentEntityException ex) {
                    } catch (Exception ex) {
                    }
                } else {
                    resultado = false;
                    break;
                }
            }
        } else {
            System.out.println("la prueba no se pudo ejecutar porque no hay ningun libro de colección general que se encuentre prestado");
            Assert.fail("ERROR PRUEBA 3");
        }

        assertEquals(true, resultado);
    }

    /**
     * Prueba 5 Metodo que prueba cualquier tipo de entrada<br>
     * en el campo de codigo de barras
     */
    public void testCodbarrasNA() {
        System.out.println("PRUEBA 5");
        String idBibliotecario = "1102515566";
        String codUsuario = "1760157";
        String codBarras[] = {"gut1tg8dg127dg12d89129gd8gdqw98d1892981y2dy982d1982yd1",
            "821573781256378", "asjdgg44sad90d¿1'2}'12d'kdj23bfdu9h31e89hb12e9uh1de2uwdh9",
            "/**/ -*/*-//*-/ewd*", "123123123"};
        boolean result = true;
        for (String codBarra : codBarras) {
            ReservaColgenEstFab instance = new ReservaColgenEstFab();
            try {
                instance.ejecutarReserva(codBarra, idBibliotecario, codUsuario);
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
     * Prueba 6 Metodo que prueba cualquier tipo de entrada<br>
     * en el campo de codigo de estudiante
     */
    public void testCodbarrasEst() {
        System.out.println("PRUEBA 6");
        String idBibliotecario = "1102515566";
        String codUsuario[] = {"ojajfoaejfoaeojfaejfo",
            "3129031", "asjdgg44sad90d¿1'2}'12d'kdj23bfdu9h31e89hb12e9uh1de2uwdh9",
            "/**/ -*/*-//*-/ewd*", "12721", "1760127"};
        String codBarras = "1213123";

        boolean result = true;
        for (String CodUsu : codUsuario) {
            ReservaColgenEstFab instance = new ReservaColgenEstFab();
            try {
                instance.ejecutarReserva(codBarras, CodUsu, idBibliotecario);
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
     * Prueba 7 Metodo que el recurso a reservar sea de la categoria coleccion
     * general
     *
     */
    public void codigoCategoriaColeccion() {
        System.out.println("Prueba 7");
        CategoriaColeccionJpaController controlib = new CategoriaColeccionJpaController();
        List<CategoriaColeccion> lib = controlib.findCategoriaColeccionEntities();
        boolean result = false;
        for (int i = 0; i < lib.size(); i++) {
            if (lib.get(i).getCodcategoriacoleccion().equalsIgnoreCase("colgen")) {
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

    /**
     * Prueba 8 Metodo que prueba que el recurso se encuentre en prestamo al
     * momento de realizar la reserva
     */
    public void testdisponibilidadPrestado() {
        System.out.println("PRUEBA 8 ");
        LibroJpaController controlib = new LibroJpaController();
        List<Libro> lib = controlib.findLibroEntities();
        boolean result = false;
        for (int i = 0; i < lib.size(); i++) {
            try {
                if (lib.get(i).getDisponibilidad().equalsIgnoreCase("prestado")) {
                    System.out.println("el libro se encuentra prestado");
                    result = true;
                    break;
                }
            } catch (Exception e) {
            } catch (NoClassDefFoundError | ExceptionInInitializerError ex) {
                result = false;
            }
        }
        assertEquals(true, result);
    }

    /**
     * Prueba 9 Metodo que prueba que el recurso no se encuentre "disponible" al
     * momento de realizar la reserva
     */
    public void testdisponibilidad() {
        System.out.println("PRUEBA 9.... ");
        LibroJpaController controlib = new LibroJpaController();
        List<Libro> lib = controlib.findLibroEntities();
        boolean result = false;
        for (int i = 0; i < lib.size(); i++) {
            if (lib.get(i).getCodbarralibro().equalsIgnoreCase("254920")) {
                if (lib.get(i).getDisponibilidad().equalsIgnoreCase("disponible")) {
                    try {
                        result = false;
                        break;
                    } catch (Exception e) {
                    } catch (NoClassDefFoundError | ExceptionInInitializerError ex) {
                        result = true;
                    }
                }
            }
        }
        assertEquals(false, result);
    }

}

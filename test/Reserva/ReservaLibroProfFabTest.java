package Reserva;

import controller.exceptions.NonexistentEntityException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import moduloDevolucion.modelo.GeneradorDevolucion;
import moduloMulta.modelo.IVerificaMulta;
import moduloMulta.modelo.VerificaMultaProfesor;
import moduloPrestamo.modelo.GeneradorPrestamo;
import moduloReserva.DAO.ReservaColgenDAOProf;
import moduloReserva.entitys.ReservaColgenProfesor;
import moduloReserva.fabrica.ReservaColgenProfFab;
import moduloReserva.modelo.GeneradorReserva;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import recursos.controllers.CategoriaColeccionJpaController;
import recursos.controllers.LibroJpaController;
import recursos.entitys.CategoriaColeccion;
import recursos.entitys.Libro;
import usuarios.control.ProfesorJpaController;
import usuarios.entitys.Profesor;

/**
 *
 * @author Felipe
 */
public class ReservaLibroProfFabTest {

    /**
     * En este metodo se ejecutan todas las pruebas
     */
    @Test
    public void testCodigo() {
        prueba_1_2();
        prueba_3();
        prueba_4();
        codigoCategoriaColeccion();
        pruebas_10();
        prueba_11();
        testCodbarrasEst();
        testdisponibilidad();
        
        prueba_12_15_16_17();
    }

    /**
     * Prueba 1 Metodo que prueba registrar la reservas, verificar que se asigne
     * la fecha actual<br>
     * y de probar que el usuario no cuente con multas
     *
     */
    public void prueba_1_2() {
        System.out.println("PRUEBA 1");
        ProfesorJpaController controlest = new ProfesorJpaController();
        List<Profesor> est = controlest.findProfesorEntities();
        GeneradorReserva generador = new GeneradorReserva();
        LibroJpaController libroJPA = new LibroJpaController();
        List<Libro> libros = libroJPA.findLibroEntities();
        boolean resultado = true;

        IVerificaMulta multa = new VerificaMultaProfesor();
        String codProfesor = "NA";
        for (int i = 0; i < est.size(); i++) {
            if (!multa.verificarMultaUsuario(est.get(i).getIdprofesor())) {
                codProfesor = est.get(i).getIdprofesor();
            }
        }
        String codigoLibro = "NA";
        for (int i = 0; i < libros.size(); i++) {
            if (libros.get(i).getDisponibilidad().equalsIgnoreCase("prestado")
                    && (libros.get(i).getCodcategoriacoleccion().getCodcategoriacoleccion().equalsIgnoreCase("colgen"))) {
                codigoLibro = libros.get(i).getCodbarralibro();
                break;
            }
        }
        resultado = generador.createReserva(codigoLibro, codProfesor, "profesor", "1102515566");
        if (resultado) {
            try {
                System.out.println("borrando los cambios que se hicieron en las pruebas");
                ReservaColgenDAOProf reservaDAO = new ReservaColgenDAOProf();
                reservaDAO.deleteDAO(codigoLibro, codProfesor);
                Libro libro = libroJPA.findLibro(codigoLibro);
                libro.setDisponibilidad("prestado");
                libroJPA.edit(libro);

            } catch (NonexistentEntityException ex) {
            } catch (Exception ex) {
            }
        } else {
            resultado = false;
        }
    }

    /**
     * Prueba 3 que verifica si el codigo de usuario no existe
     */
    public void prueba_3() {
        System.out.println("PRUEBA 3");
        ProfesorJpaController controlest = new ProfesorJpaController();
        List<Profesor> est = controlest.findProfesorEntities();
        GeneradorReserva generador = new GeneradorReserva();
        LibroJpaController libroJPA = new LibroJpaController();
        List<Libro> libros = libroJPA.findLibroEntities();
        boolean resultado = true;

        String codigoLibro = "NA";

        String[] codProfesores = {"", "gut1tg8dg127dg12d89129gd8gdqw98d1892981y2dy982d1982yd1",
            "82157378125637816523786123876382176328", "asjdgg44sad90d¿1'2}'12d'kdj23bfdu9h31e89hb12e9uh1de2uwdh9",
            "/**/ -*/*-//*-/ewd*-213e/*23/e-*d/23*-d/*-23d/*-23d/*-32d*-23/d*-2/3*e/wqd54qew4213efwefvefwk", "123123123"};

        for (int i = 0; i < codProfesores.length; i++) {
            resultado = generador.createReserva(codigoLibro, codProfesores[i], "profesor", "1102515566");
            if (resultado) {
                try {
                    System.out.println("borrando los cambios que se hicieron en las pruebas");
                    ReservaColgenDAOProf reservaDAO = new ReservaColgenDAOProf();
                    reservaDAO.deleteDAO(codigoLibro, codProfesores[i]);
                    Libro libro = libroJPA.findLibro(codigoLibro);
                    libro.setDisponibilidad("prestado");
                    libroJPA.edit(libro);
                    break;
                } catch (NonexistentEntityException ex) {
                } catch (Exception ex) {
                }
            } else {
                resultado = false;
            }
        }
        assertEquals(false, resultado);
    }

    /**
     * Prueba 4 Metodo que prueba cualquier tipo de entrada<br>
     * en el campo de codigo de barras
     */
    public void prueba_4() {
        System.out.println("--------------------------Prueba 4-----------------------------");
        String codBarras[] = {"", "gut1tg8dg127dg12d89129gd8gdqw98d1892981y2dy982d1982yd1",
            "82157378125637816523786123876382176328", "asjdgg44sad90d¿1'2}'12d'kdj23bfdu9h31e89hb12e9uh1de2uwdh9",
            "/**/ -*/*-//*-/ewd*-213e/*23/e-*d/23*-d/*-23d/*-23d/*-32d*-23/d*-2/3*e/wqd54qew4213efwefvefwk", "123123123"};
        boolean result = false;
        ReservaColgenProfFab instance = new ReservaColgenProfFab();
        for (String codBarra : codBarras) {
            try {
                result = instance.ejecutarReserva(codBarra, "90298374", "1102515566");
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
     * Prueba 10 que se encarga de que un recurso a reservar este prestado
     * 
     */
    public void pruebas_10() {
        System.out.println("PRUEBA 3 y 4");
        ProfesorJpaController controlest = new ProfesorJpaController();
        List<Profesor> est = controlest.findProfesorEntities();
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
            } else {
                System.out.println("El libro no se puede reservar");
            }

        }

        if (!codigoLibro.equalsIgnoreCase("NA")) {//si no se encuentra un libro las pruebas fallan
            for (int i = 0; i < est.size(); i++) {
                System.out.println("ejecutando prueba");
                resultado = generador.createReserva(codigoLibro, "91393849", "profesor", "1102515566");
                if (resultado) {
                    try {
                        System.out.println("borrando los cambios que se hicieron en las pruebas");
                        ReservaColgenDAOProf reservaDAO = new ReservaColgenDAOProf();
                        reservaDAO.deleteDAO(codigoLibro, est.get(i).getIdprofesor());
                        Libro libro = libroJPA.findLibro(codigoLibro);
                        libro.setDisponibilidad("prestado");
                        libroJPA.edit(libro);
                        break;
                    } catch (NonexistentEntityException ex) {
                    } catch (Exception ex) {
                    }
                } else {
                    resultado = false;
                    break;
                }
            }
        }

        assertEquals(true, resultado);
    }

    /**
     * Prueba 11 que se encarga de revizar que un recurso se encuentre
     * disponible
     */
    public void prueba_11() {
        System.out.println("PRUEBA 3 y 4");
        ProfesorJpaController controlest = new ProfesorJpaController();
        List<Profesor> est = controlest.findProfesorEntities();
        GeneradorReserva generador = new GeneradorReserva();
        LibroJpaController libroJPA = new LibroJpaController();
        List<Libro> libros = libroJPA.findLibroEntities();
        boolean resultado = true;
        String codigoLibro = "NA";
        //buscamos un libro que este disponible
        for (int i = 0; i < libros.size(); i++) {
            if (libros.get(i).getDisponibilidad().equalsIgnoreCase("disponible")) {
                codigoLibro = libros.get(i).getCodbarralibro();
                break;
            }
        }
        if (!codigoLibro.equalsIgnoreCase("NA")) {//si no se encuentra un libro las pruebas fallan
            for (int i = 0; i < est.size(); i++) {
                System.out.println("ejecutando prueba");
                resultado = generador.createReserva(codigoLibro, est.get(i).getIdprofesor(), "profesor", "1102515566");
                if (resultado) {
                    try {
                        System.out.println("borrando los cambios que se hicieron en las pruebas");
                        ReservaColgenDAOProf reservaDAO = new ReservaColgenDAOProf();
                        reservaDAO.deleteDAO(codigoLibro, est.get(i).getIdprofesor());
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
        }
        assertEquals(true, resultado);
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
            ReservaColgenProfFab instance = new ReservaColgenProfFab();
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

    public void prueba_12_15_16_17() {
        GeneradorPrestamo generador = new GeneradorPrestamo();
        GeneradorReserva generadorReserva = new GeneradorReserva();
        GeneradorDevolucion generadorDevolucion = new GeneradorDevolucion();

        try {
            generador.createPrestamo("507468", "90298374", "1102515566", "libro", "profesor");
            generadorReserva.createReserva("507468", "90298374", "profesor", "1102515566");
            generadorDevolucion.createDevolucion("507468", "1102515566", "libro", "bueno");

            ReservaColgenDAOProf reservaDAO = new ReservaColgenDAOProf();
            ReservaColgenProfesor reserva = reservaDAO.readDAO("507468");
            System.out.println("Fecha retencion: " + reserva.getFechaRetencion().getTime());
            System.out.println("Fecha limite retencion: " + reserva.getFechaLimiteReserva().getTime());
            LibroJpaController libroJPA = new LibroJpaController();
            Libro libro = libroJPA.findLibro("507468");
            System.out.println("Disponibilidad libro: " + libro.getDisponibilidad());
            libro.setDisponibilidad("disponible");
            libroJPA.edit(libro);
            reservaDAO.deleteDAO("507468", "1760204");

        } catch (Exception ex) {
            Logger.getLogger(ReservaLibroProfFabTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

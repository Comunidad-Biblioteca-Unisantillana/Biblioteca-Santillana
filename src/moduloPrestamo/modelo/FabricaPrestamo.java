package moduloPrestamo.modelo;

import moduloPrestamo.fabrica.PrestamoDiccionarioEstFab;
import moduloPrestamo.fabrica.PrestamoDiccionarioProfFab;
import moduloPrestamo.fabrica.PrestamoEnciclopediaEstFab;
import moduloPrestamo.fabrica.PrestamoEnciclopediaProfFab;
import moduloPrestamo.fabrica.PrestamoLibroEstFab;
import moduloPrestamo.fabrica.PrestamoLibroProfFab;
import moduloPrestamo.fabrica.PrestamoMapaEstFab;
import moduloPrestamo.fabrica.PrestamoMapaProfFab;
import moduloPrestamo.fabrica.PrestamoPeriodicoEstFab;
import moduloPrestamo.fabrica.PrestamoPeriodicoProfFab;
import moduloPrestamo.fabrica.PrestamoRevistaEstFab;
import moduloPrestamo.fabrica.PrestamoRevistaProfFab;

/**
 * Clase que se encarga de fabricar un préstamo de cualquier entidad tanto de
 * profesor como estudiante.
 *
 * @author Julian
 * @creado
 * @author
 * @modificado
 */
public class FabricaPrestamo {

    /**
     * constructor de la clase sin parámetros.
     */
    public FabricaPrestamo() {

    }

    /**
     * el metódo crea el objeto de la entidad respectiva de estudiante o
     * profesor, para generar el préstamo.
     *
     * @param tipoPrestamo
     * @param usuario
     * @return prestamo
     */
    public IPrestamo getPrestamo(String tipoPrestamo, String usuario) {
        IPrestamo prestamo = null;

        if (usuario.equalsIgnoreCase("estudiante")) {
            switch (tipoPrestamo) {
                case "libro":
                    prestamo = new PrestamoLibroEstFab();
                    break;
                case "enciclopedia":
                    prestamo = new PrestamoEnciclopediaEstFab();
                    break;
                case "diccionario":
                    prestamo = new PrestamoDiccionarioEstFab();
                    break;
                case "revista":
                    prestamo = new PrestamoRevistaEstFab();
                    break;
                case "periodico":
                    prestamo = new PrestamoPeriodicoEstFab();
                    break;
                case "mapa":
                    prestamo = new PrestamoMapaEstFab();
                    break;
                default:
                    System.out.println("Error al crear un préstamo al estudiante de la entidad: " + tipoPrestamo + ", que no existe.");
                    break;
            }
        } else {
            switch (tipoPrestamo) {
                case "libro":
                    prestamo = new PrestamoLibroProfFab();
                    break;
                case "enciclopedia":
                    prestamo = new PrestamoEnciclopediaProfFab();
                    break;
                case "diccionario":
                    prestamo = new PrestamoDiccionarioProfFab();
                    break;
                case "revista":
                    prestamo = new PrestamoRevistaProfFab();
                    break;
                case "periodico":
                    prestamo = new PrestamoPeriodicoProfFab();
                    break;
                case "mapa":
                    prestamo = new PrestamoMapaProfFab();
                    break;
                default:
                    System.out.println("Error al crear un préstamo al profesor de la entidad: " + tipoPrestamo + ", que no existe.");
                    break;
            }
        }

        return prestamo;
    }

}

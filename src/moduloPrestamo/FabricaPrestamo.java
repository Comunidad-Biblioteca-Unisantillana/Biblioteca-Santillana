package moduloPrestamo;

import moduloPrestamo.fabrica.PrestamoEnciclopediaProfFab;
import moduloPrestamo.fabrica.PrestamoLibroProfFab;
import moduloPrestamo.fabrica.PrestamoRevistaEstFab;
import moduloPrestamo.fabrica.PrestamoMapaProfFab;
import moduloPrestamo.fabrica.PrestamoDiccionarioEstFab;
import moduloPrestamo.fabrica.PrestamoLibroEstFab;
import moduloPrestamo.fabrica.PrestamoMapaEstFab;
import moduloPrestamo.fabrica.PrestamoEnciclopediaEstFab;
import moduloPrestamo.fabrica.PrestamoRevistaProfFab;
import moduloPrestamo.fabrica.PrestamoDiccionarioProfFab;
import moduloPrestamo.fabrica.PrestamoPeriodicoProfFab;
import moduloPrestamo.fabrica.PrestamoPeriodicoEstFab;


/**
 * Clase que se encarga de fabricar un prestamo de cualquier entidad
 *
 * @author Julian
 */
public class FabricaPrestamo {

    public FabricaPrestamo() {

    }

    public IPrestamo getPrestamo(String tipoPrestamo, String usuario) {
        IPrestamo prestamo = null;
        if (usuario.equalsIgnoreCase("estudiante")) {
            switch (tipoPrestamo.toLowerCase()) {
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
                    System.out.println("error al crear un prestamo de recurso de estudiante");
                    break;
            }
        } else {
            switch (tipoPrestamo.toLowerCase()) {
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
                    System.out.println("error al crear un prestamo de recurso de profesor");
                    break;
            }
        }
        return prestamo;
    }

}
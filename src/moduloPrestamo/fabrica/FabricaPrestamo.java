package moduloPrestamo.fabrica;

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
                    System.out.println("Error al crear un préstamo al estudiante de la entidad: " + tipoPrestamo);
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
                    System.out.println("Error al crear un préstamo al profesor de la entidad: " + tipoPrestamo);
                    break;
            }
        }
        
        return prestamo;
    }

}

package moduloDevolucion;

import moduloDevolucion.fabrica.*;

/**
 * @author Camilo Jaramillo
 * @version 1.0
 * @created 04-ago.-2019 10:38:06 a. m.
 */
public class FabricaDevolucion {

    public FabricaDevolucion() {

    }

    public IDevolucion getDevolucion(String tipoRecurso, String usuario) {
        IDevolucion devolucion = null;
        if (usuario.equalsIgnoreCase("estudiante")) {
            switch (tipoRecurso.toLowerCase()) {
                case "libro":
                    devolucion = new DevolucionLibroEstFab();
                    break;
                case "enciclopedia":
                    devolucion = new DevolucionEnciclopediaEstFab();
                    break;
                case "diccionario":
                    devolucion = new DevolucionDiccionarioEstFab();
                    break;
                case "revista":
                    devolucion = new DevolucionRevistaEstFab();
                    break;
                case "periodico":
                    devolucion = new DevolucionPeriodicoEstFab();
                    break;
                case "mapa":
                    devolucion = new DevolucionMapaEstFab();
                    break;
                default:
                    System.out.println("error al crear una devolucion de recurso de estudiante");
                    break;
            }
        } else {
            switch (tipoRecurso.toLowerCase()) {
                case "libro":
                    devolucion = new DevolucionLibroProfFab();
                    break;
                case "enciclopedia":
                    devolucion = new DevolucionEnciclopediaProfFab();
                    break;
                case "diccionario":
                    devolucion = new DevolucionDiccionarioProfFab();
                    break;
                case "revista":
                    devolucion = new DevolucionRevistaProfFab();
                    break;
                case "periodico":
                    devolucion = new DevolucionPeriodicoProfFab();
                    break;
                case "mapa":
                    devolucion = new DevolucionMapaProfFab();
                    break;
                default:
                    System.out.println("error al crear una devolucion de recurso de profesor");
                    break;
            }
        }
        return devolucion;
    }
}//end FabricaDevolucion

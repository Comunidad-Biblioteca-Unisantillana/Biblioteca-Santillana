package moduloDevolucion.fabrica;

import moduloDevolucion.IDevolucion;

/**
 * @author Camilo Jaramillo
 * @version 1.0
 * @created 04-ago.-2019 10:37:29 a. m.
 */
public class DevolucionEnciclopediaEstFab implements IDevolucion {

    public DevolucionEnciclopediaEstFab() {

    }

    @Override
    public boolean ejecutarDevolucion(String codBarras, String idBibliotecario, String estadoRecurso) {
        return false;
    }

    public int consultarPrestamoEnciclopedia(String codBarras) {
        return 0;
    }

    public void notificarDevolucion(String codEstudiante, String tituloRecurso, java.util.Date fechaDevolucion) {

    }
}

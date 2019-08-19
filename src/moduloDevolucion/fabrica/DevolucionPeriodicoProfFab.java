package moduloDevolucion.fabrica;

import moduloDevolucion.IDevolucion;

/**
 * @author Camilo Jaramillo
 * @version 1.0
 * @created 04-ago.-2019 10:37:55 a. m.
 */
public class DevolucionPeriodicoProfFab implements IDevolucion {

    public DevolucionPeriodicoProfFab() {

    }

    @Override
    public boolean ejecutarDevolucion(String codBarras, String idBibliotecario, String estadoRecurso) {
        return false;
    }

    public int consultarPrestamoPeriodico(String codBarras) {
        return 0;
    }

    public void notificarDevolucion(String idProfesor, String tituloRecurso, java.util.Date fechaDevolucion) {

    }
}

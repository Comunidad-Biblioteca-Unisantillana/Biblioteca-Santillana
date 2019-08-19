package moduloDevolucion.fabrica;

import moduloDevolucion.IDevolucion;

/**
 * @author Camilo Jaramillo
 * @version 1.0
 * @created 04-ago.-2019 10:37:52 a. m.
 */
public class DevolucionPeriodicoEstFab implements IDevolucion {

    public DevolucionPeriodicoEstFab() {

    }

    @Override
    public boolean ejecutarDevolucion(String codBarras, String idBibliotecario, String estadoRecurso) {
        return false;
    }

    public int consultarPrestamoPeriodico(String codBarras) {
        return 0;
    }

    public void notificarDevolucion(String codEstudiante, String tituloRecurso, java.util.Date fechaDevolucion) {

    }
}

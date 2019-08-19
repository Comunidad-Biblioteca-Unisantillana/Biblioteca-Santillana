package moduloDevolucion.fabrica;

import moduloDevolucion.IDevolucion;

/**
 * @author Camilo Jaramillo
 * @version 1.0
 * @created 04-ago.-2019 10:37:38 a. m.
 */
public class DevolucionLibroEstFab implements IDevolucion {

    public DevolucionLibroEstFab() {

    }

    @Override
    public boolean ejecutarDevolucion(String codBarras, String idBibliotecario, String estadoRecurso) {
        return false;
    }

    public int consultarPrestamoLibro(String codBarras) {
        return 0;
    }

    public boolean verificarReserva(String codBarras) {
        return false;
    }

    public void notificarDevolucion(String codEstudiante, String tituloRecurso, java.util.Date fechaDevolucion) {

    }
}

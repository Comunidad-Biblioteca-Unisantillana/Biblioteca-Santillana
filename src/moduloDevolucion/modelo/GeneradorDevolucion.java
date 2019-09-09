package moduloDevolucion.modelo;

/**
 * @author Camilo Jaramillo
 * @version 1.0
 * @created 04-ago.-2019 10:38:07 a. m.
 */
public class GeneradorDevolucion {

    public GeneradorDevolucion() {

    }

    public String createDevolucion(String codBarras, String idBibliotecario, String tipoRecurso, String estadoRecurso) {
        if (!generarDevolucion(codBarras, idBibliotecario, tipoRecurso, estadoRecurso, "estudiante")) {
            generarDevolucion(codBarras, idBibliotecario, tipoRecurso, estadoRecurso, "profesor");
        }
        return "";
    }

    private boolean generarDevolucion(String codBarras, String idBibliotecario, String tipoRecurso, String estadoRecurso, String tipoUsuario) {
        FabricaDevolucion fabrica = new FabricaDevolucion();
        IDevolucion devolucion = fabrica.getDevolucion(tipoRecurso, tipoUsuario);
        if (devolucion.ejecutarDevolucion(codBarras, idBibliotecario, estadoRecurso)) {
            return true;
        }
        return false;
    }
}

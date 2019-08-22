package moduloDevolucion;

/**
 * @author Camilo Jaramillo
 * @version 1.0
 * @created 04-ago.-2019 10:38:07 a. m.
 */
public class GeneradorDevolucion {

    public GeneradorDevolucion() {

    }

    public String createDevolucion(String codBarras, String idBibliotecario, String tipoRecurso, String estadoRecurso) {
        String mensaje = "";
        if(generarDevolucionEstudiante(codBarras, idBibliotecario, tipoRecurso, estadoRecurso)){
            
        }else if(generarDevolucionProfesor(codBarras, idBibliotecario, tipoRecurso, estadoRecurso)){
            
        }else{
            
        }
        return mensaje;
    }

    private boolean generarDevolucionEstudiante(String codBarras, String idBibliotecario, String tipoRecurso, String estadoRecurso){
        FabricaDevolucion fabrica = new FabricaDevolucion();
        IDevolucion devolucion = fabrica.getDevolucion(tipoRecurso, "estudiante");
        if(devolucion.ejecutarDevolucion(codBarras, idBibliotecario, estadoRecurso)){
            return true;
        }
        return false;
    }
    
    private boolean generarDevolucionProfesor(String codBarras, String idBibliotecario, String tipoRecurso, String estadoRecurso){
        FabricaDevolucion fabrica = new FabricaDevolucion();
        IDevolucion devolucion = fabrica.getDevolucion(tipoRecurso, "estudiante");
        if(devolucion.ejecutarDevolucion(codBarras, idBibliotecario, estadoRecurso)){
            return true;
        }
        return false;
    }
}

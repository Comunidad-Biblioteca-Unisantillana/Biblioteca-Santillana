package moduloRenovacion.modelo;

import moduloRenovacion.fabrica.RenovacionColgenProfFab;
import moduloRenovacion.fabrica.RenovacionColgenEstFab;

/**
 * Clase que se encarga de fabricar la renovación de un libro tanto de profesor
 * como estudiante.
 *
 * @author Miguel Fernández
 * @creado 24/08/2019
 * @author Miguel Fernández
 * @modificado 25/08/2019
 */
public class FabricaRenovacion {

    /**
     * el metódo crea el objeto de la entidad respectiva de estudiante o
     * profesor, para generar la renovación.
     *
     * @param tipoUsuario
     * @return renovar
     */
    public IRenovacion getRenovacion(String tipoUsuario) {
        IRenovacion renovar = null;

        if (tipoUsuario.equals("estudiante")) {
            renovar = new RenovacionColgenEstFab();
        } else {
            renovar = new RenovacionColgenProfFab();
        }

        return renovar;
    }

}

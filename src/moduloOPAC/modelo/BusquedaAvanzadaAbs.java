package moduloOPAC.modelo;

import javafx.collections.ObservableList;
import modelo.ConnectionBD;

/**
 *
 * @author Miguel Fernández
 * @creado 17/08/2019
 * @author Miguel Fernández
 * @modificado 18/08/2019
 */
public abstract class BusquedaAvanzadaAbs {

    protected ConnectionBD connection;

    /**
     * el metódo dependiendo del tipo de busqueda (si es en una entidad
     * especifica o en todas) y delega la busqueda a otro metódo, retornando los
     * recursos encontrados. Desarrollado en las clases hijas.
     *
     * @param entidad
     * @return recursos
     */
    public abstract ObservableList<Recurso> buscarRecursos(String entidad);

}

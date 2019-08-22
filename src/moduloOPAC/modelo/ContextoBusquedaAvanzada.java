package moduloOPAC.modelo;

import javafx.collections.ObservableList;

/**
 *
 * @author Miguel Fernández
 * @creado 17/08/2019
 * @author Miguel Fernández
 * @modificado 18/08/2019
 */
public class ContextoBusquedaAvanzada {

    private BusquedaAvanzadaAbs busquedaAvanzada;

    /**
     * constructor sin parámetros.
     */
    public ContextoBusquedaAvanzada() {

    }

    /**
     * el metódo dependiendo del tipo de busqueda (si es en una entidad
     * especifica o en todas) y delega la busqueda a otro metódo de la clse
     * inresada, retornando los recursos encontrados.
     *
     * @param entidad
     * @return recursos
     */
    public ObservableList<Recurso> buscarRecursos(String entidad) {
        return busquedaAvanzada.buscarRecursos(entidad); // -> recursos
    }

    /**
     * el metódo recibe la clase que se encargará de la busqueda de los
     * recursos.
     *
     * @param busquedaAvanzada
     */
    public void setBusquedaAvanzada(BusquedaAvanzadaAbs busquedaAvanzada) {
        this.busquedaAvanzada = busquedaAvanzada;
    }

}

package moduloOPAC.modelo;

import java.util.List;
import recursos.controllers.AutorPorEnciclopediaJpaController;
import recursos.entitys.AutorEnciclopedia;

/**
 * @author Miguel Fernández
 * @creado 18/08/2019
 * @author Miguel Fernández
 * @modificado 19/08/2019
 */
public class BusquedaAutorEnciclopedia implements IBusquedaAutorRecurso {

    private String cadenaAutores;

    /**
     * constructor sin parámetros.
     */
    public BusquedaAutorEnciclopedia() {
        cadenaAutores = "";
    }

    /**
     * el metódo busca los autores de una enciclopedia, por medio del codigo de
     * barras.
     *
     * @param codBarras
     */
    @Override
    public void buscarAutorRecurso(String codBarras) {
        AutorPorEnciclopediaJpaController autorPorEncJC = new AutorPorEnciclopediaJpaController();
        List<AutorEnciclopedia> listAutEnc = autorPorEncJC.findAutorPorEnciclopediaCodBarras(codBarras);

        if (!listAutEnc.isEmpty()) {
            listAutEnc.forEach((aut) -> {
                cadenaAutores += "• " + aut.getNombres() + " " + aut.getApellidos() + "\n";
            });
        }
    }

    /**
     * el metódo retorna una cadena con el nombre y apellido de los autores de
     * una enciclopedia.
     *
     * @return cadenaAutores
     */
    @Override
    public String getCadenaAutores() {
        return cadenaAutores.replaceAll("null", "");
    }
    
}

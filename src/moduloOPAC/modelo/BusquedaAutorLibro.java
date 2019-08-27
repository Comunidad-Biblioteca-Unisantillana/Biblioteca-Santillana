package moduloOPAC.modelo;

import java.util.List;
import recursos.controllers.AutorPorLibroJpaController;
import recursos.entitys.AutorLibro;

/**
 * @author Miguel Fernández
 * @creado 18/08/2019
 * @author Miguel Fernández
 * @modificado 19/08/2019
 */
public class BusquedaAutorLibro implements IBusquedaAutorRecurso {

    private String cadenaAutores;

    /**
     * constructor sin parámetros.
     */
    public BusquedaAutorLibro() {
        cadenaAutores = "";
    }

    /**
     * el metódo busca los autores de un libro, por medio del codigo de barras.
     *
     * @param codBarras
     */
    @Override
    public void buscarAutorRecurso(String codBarras) {
        AutorPorLibroJpaController autorPorLibJC = new AutorPorLibroJpaController();
        List<AutorLibro> listAutLib = autorPorLibJC.findAutorPorLibroCodBarras(codBarras);

        if (!listAutLib.isEmpty()) {
            for (AutorLibro aut: listAutLib) {
                cadenaAutores += "• " + aut.getNombres() + " " + aut.getApellidos() + "\n";
            }
        }
    }

    /**
     * el metódo retorna una cadena con el nombre y apellido de los autores de
     * un libro.
     *
     * @return cadenaAutores
     */
    @Override
    public String getCadenaAutores() {
        return cadenaAutores.replaceAll("null", "");
    }

}

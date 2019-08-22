package moduloOPAC.modelo;

import java.util.List;
import recursos1.controllers.AutorPorDiccionarioJpaController;
import recursos1.entitys.AutorDiccionario;

/**
 * @author Miguel Fernández
 * @creado 18/08/2019
 * @author Miguel Fernández
 * @modificado 19/08/2019
 */
public class BusquedaAutorDiccionario implements IBusquedaAutorRecurso {

    private String cadenaAutores;

    /**
     * constructor sin parámetros.
     */
    public BusquedaAutorDiccionario() {
        cadenaAutores = "";
    }

    /**
     * el metódo busca los autores de un diccionario, por medio del codigo de barras.
     *
     * @param codBarras
     */
    @Override
    public void buscarAutorRecurso(String codBarras) {
        AutorPorDiccionarioJpaController autorPorDicJC = new AutorPorDiccionarioJpaController();
        List<AutorDiccionario> listAutDic = autorPorDicJC.findAutorPorDiccionarioCodBarras(codBarras);

        if (!listAutDic.isEmpty()) {
            listAutDic.forEach((aut) -> {
                cadenaAutores += "• " + aut.getNombres() + " " + aut.getApellidos() + "\n";
            });
        }
    }

    /**
     * el metódo retorna una cadena con el nombre y apellido de los autores de
     * un diccionario.
     *
     * @return cadenaAutores
     */
    @Override
    public String getCadenaAutores() {
        return cadenaAutores.replaceAll("null", "");
    }

}

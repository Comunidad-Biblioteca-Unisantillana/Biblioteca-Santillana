package moduloOPAC.modelo;

import recursos1.controllers.DiccionarioJpaController;
import recursos1.controllers.EnciclopediaJpaController;
import recursos1.controllers.LibroJpaController;
import recursos1.controllers.MapaJpaController;
import recursos1.controllers.PeriodicoJpaController;
import recursos1.controllers.RevistaJpaController;
import recursos1.entitys.Diccionario;
import recursos1.entitys.Enciclopedia;
import recursos1.entitys.Libro;
import recursos1.entitys.Mapa;
import recursos1.entitys.Periodico;
import recursos1.entitys.Revista;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import modelo.ConnectionBD;

/**
 *
 * @author Miguel Fernández
 * @creado 17/08/2019
 * @author Miguel Fernández
 * @modificado 18/08/2019
 */
public class BusquedaCodigoBarras extends BusquedaCodigoAbs {

    /**
     * constructor sin parámetros.
     */
    public BusquedaCodigoBarras() {
        connection = ConnectionBD.getInstance();
    }

    /**
     * el metódo retorna el nombre de la entidad asociada al codigo de barras ingresado.
     *
     * @param codBarras
     * @return nombreEntidad
     */
    @Override
    public String buscarEntidad(String codBarras) {
        String nombreEntidad = "";

        try {
            Statement stmt = connection.getConnection().createStatement();
            ResultSet rs = null;
            String query;

            for (String entidad : entidades) {
                query = "SELECT * FROM " + entidad + " WHERE codbarra" + entidad + " = '" + codBarras + "'";
                rs = stmt.executeQuery(query);
                
                if (rs.next()) {
                    nombreEntidad = entidad;
                    break;
                }
            }

            rs.close();
        } catch (SQLException ex) {
            System.out.println("Error al encontrar la entidad, busqueda por codigo de barras.");
        }

        return nombreEntidad;
    }

    /**
     * el metódo retorna un libro asociado al codigo de barras ingresado.
     *
     * @param codBarras
     * @return libro
     */
    @Override
    public Libro buscarLibroPorCodigo(String codBarras) {
        LibroJpaController libroJC = new LibroJpaController();
        return libroJC.findLibro(codBarras);                  // -> libro
    }

    /**
     * el metódo retorna una enciclopedia asociado al codigo de barras
     * ingresado.
     *
     * @param codBarras
     * @return enciclopedia
     */
    @Override
    public Enciclopedia buscarEnciclopediaPorCodigo(String codBarras) {
        EnciclopediaJpaController enciclopediaJC = new EnciclopediaJpaController();
        return enciclopediaJC.findEnciclopedia(codBarras);                         // -> enciclopedia
    }

    /**
     * el metódo retorna un diccionario asociado al codigo de barras ingresado.
     *
     * @param codBarras
     * @return diccionario
     */
    @Override
    public Diccionario buscarDiccionarioPorCodigo(String codBarras) {
        DiccionarioJpaController diccionarioJC = new DiccionarioJpaController();
        return diccionarioJC.findDiccionario(codBarras);                        // -> diccionario
    }

    /**
     * el metódo retorna una revista asociado al codigo de barras ingresado.
     *
     * @param codBarras
     * @return revista
     */
    @Override
    public Revista buscarRevistaPorCodigo(String codBarras) {
        RevistaJpaController revistaJC = new RevistaJpaController();
        return revistaJC.findRevista(codBarras);                    // -> revista
    }

    /**
     * el metódo retorna un periodico asociado al codigo de barras ingresado.
     *
     * @param codBarras
     * @return periodico
     */
    @Override
    public Periodico buscarPeriodicoPorCodigo(String codBarras) {
        PeriodicoJpaController periodicoJC = new PeriodicoJpaController();
        return periodicoJC.findPeriodico(codBarras);                      // -> periodico
    }

    /**
     * el metódo retorna un mapa asociado al codigo de barras ingresado.
     *
     * @param codBarras
     * @return mapa
     */
    @Override
    public Mapa buscarMapaPorCodigo(String codBarras) {
        MapaJpaController mapaJC = new MapaJpaController();
        return mapaJC.findMapa(codBarras);                 // -> mapa
    }

}

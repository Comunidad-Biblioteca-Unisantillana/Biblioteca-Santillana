package moduloOPAC.modelo;

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
import recursos1.controllers.DiccionarioJpaController;
import recursos1.controllers.EnciclopediaJpaController;
import recursos1.controllers.LibroJpaController;
import recursos1.controllers.MapaJpaController;
import recursos1.controllers.PeriodicoJpaController;
import recursos1.controllers.RevistaJpaController;

/**
 *
 * @author Miguel Fernández
 * @creado 17/08/2019
 * @author Miguel Fernández
 * @modificado 19/08/2019
 */
public class Busqueda_ISBN_ISSN extends BusquedaCodigoAbs {

    /**
     * constructor sin parámetros.
     */
    public Busqueda_ISBN_ISSN() {
        connection = ConnectionBD.getInstance();
    }

    /**
     * el metódo retorna el nombre de la entidad asociada a un isbn o issn.
     *
     * @param isbn_issn
     * @return nombreEntidad
     */
    @Override
    public String buscarEntidad(String isbn_issn) {
        String nombreEntidad = "";

        try {
            Statement stmt = connection.getConnection().createStatement();
            ResultSet rs;
            String query;

            for (int i = 5; i < entidades.length; i++) {
                query = "SELECT * FROM " + entidades[i] + " WHERE issn = '" + isbn_issn + "'";

                rs = stmt.executeQuery(query);

                if (rs.next()) {
                    nombreEntidad = entidades[i];
                    break;
                }
                
                rs.close();
            }

            if (nombreEntidad.isEmpty()) {
                for (int i = 0; i < (entidades.length - 2); i++) {
                    query = "SELECT * FROM " + entidades[i] + " WHERE isbn = '" + isbn_issn + "'";

                    rs = stmt.executeQuery(query);

                    if (rs.next()) {
                        nombreEntidad = entidades[i];
                        break;
                    }
                    
                    rs.close();
                }
            }

        } catch (SQLException ex) {
            System.out.println("Error al encontrar la entidad, busqueda por codigo (isbn o issn).");
        }

        return nombreEntidad;
    }

    /**
     * el metódo retorna un libro asociado al isbn ingresado.
     *
     * @param isbn
     * @return libro
     */
    @Override
    public Libro buscarLibroPorCodigo(String isbn) {
        LibroJpaController libroJC = new LibroJpaController();
        return libroJC.findLibroISBN(isbn);                   // -> libro
    }

    /**
     * el metódo retorna una enciclopedia asociada al isbn ingresado.
     *
     * @param isbn
     * @return enciclopedia
     */
    @Override
    public Enciclopedia buscarEnciclopediaPorCodigo(String isbn) {
        EnciclopediaJpaController enciclopediaJC = new EnciclopediaJpaController();
        return enciclopediaJC.findEnciclopediaISBN(isbn);                          // -> enciclopedia
    }

    /**
     * el metódo retorna un diccionario asociado al isbn ingresado.
     *
     * @param isbn
     * @return diccionario
     */
    @Override
    public Diccionario buscarDiccionarioPorCodigo(String isbn) {
        DiccionarioJpaController diccionarioJC = new DiccionarioJpaController();
        return diccionarioJC.findDiccionarioISBN(isbn);                         // -> diccionario
    }

    /**
     * el metódo retorna una revista asociado al issn ingresado.
     *
     * @param issn
     * @return revista
     */
    @Override
    public Revista buscarRevistaPorCodigo(String issn) {
        RevistaJpaController revistaJC = new RevistaJpaController();
        return revistaJC.findRevistaISSN(issn);                     // -> revista
    }

    /**
     * el metódo retorna un periodico asociado al issn ingresado.
     *
     * @param issn
     * @return periodico
     */
    @Override
    public Periodico buscarPeriodicoPorCodigo(String issn) {
        PeriodicoJpaController periodicoJC = new PeriodicoJpaController();
        return periodicoJC.findPeriodicoISSN(issn);                       // -> periodico
    }

    /**
     * el metódo retorna un mapa asociado al isbn ingresado.
     *
     * @param isbn
     * @return mapa
     */
    @Override
    public Mapa buscarMapaPorCodigo(String isbn) {
        MapaJpaController mapaJC = new MapaJpaController();
        return mapaJC.findMapaISBN(isbn);                  // -> mapa
    }

}

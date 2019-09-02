package moduloOPAC.modelo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import general.modelo.ConnectionBD;

/**
 *
 * @author Miguel Fernández
 * @creado 17/08/2019
 * @author Miguel Fernández
 * @modificado 18/08/2019
 */
public class BusquedaPalabraClave extends BusquedaAvanzadaAbs {

    private String palabraClave;

    /**
     * Constructor por parámetros.
     *
     * @param palabraClave
     */
    public BusquedaPalabraClave(String palabraClave) {
        this.palabraClave = palabraClave;
        connection = ConnectionBD.getInstance();
    }

    /**
     * el metódo busca en todas las entidades ("libro", "enciclopedia",
     * "diccionario", "revista", "periodico", "mapa") y delega la busqueda a
     * otro metódo, retornando los recursos encontrados.
     *
     * @param palabraClave
     * @return listaRecursos
     */
    private ArrayList<Recurso> buscarPalabraClave() {
        ArrayList<Recurso> listaRecursos = new ArrayList<>();
        ArrayList<Recurso> listaRecursosTMP;
        String[] entidades = {"libro", "enciclopedia", "diccionario", "revista", "periodico", "mapa"};

        for (String entidad : entidades) {
            listaRecursosTMP = buscarPalabraClaveRestringida(entidad);

            if (!listaRecursosTMP.isEmpty()) {
                listaRecursos.addAll(listaRecursosTMP);
            }
        }

        return listaRecursos;
    }

    /**
     * el metódo busca en una entidad especifica ("libro", "enciclopedia",
     * "diccionario", "revista", "periodico", "mapa"), retornando los recursos
     * que cumplan con el criterio de busqueda (titulo).
     *
     * @param entidad
     * @return listaRecursos
     */
    private ArrayList<Recurso> buscarPalabraClaveRestringida(String entidad) {
        ArrayList<Recurso> listaRecursos = new ArrayList<>();

        try {
            Statement stmt = connection.getConnection().createStatement();
            String queryPalabraClave = getQueryPalabraClave(entidad);
            ResultSet rs = stmt.executeQuery(queryPalabraClave);

            while (rs.next()) {
                Recurso recurso = new Recurso();
                recurso.setCodBarras(rs.getString("codbarras"));
                recurso.setIsbn_issn(rs.getString("isbn_issn"));
                recurso.setTitulo(rs.getString("titulo"));

                if (entidad.equals("periodico") || entidad.equals("mapa")) {
                    recurso.setIdioma("N/A");
                } else {
                    recurso.setIdioma(rs.getString("idioma"));
                }

                recurso.setDisponibilidad(rs.getString("disponibilidad"));
                recurso.setTipoRecurso(entidad);
                listaRecursos.add(recurso);
            }

            rs.close();
        } catch (SQLException ex) {
            System.out.println("Error al consultar en la entidad " + entidad + ", por palabra clave.");
        }

        return listaRecursos;
    }

    /**
     * el metódo dependiendo del tipo de busqueda (si es en una entidad
     * especifica o en todas) y delega la busqueda a otro metódo, retornando los
     * recursos encontrados.
     *
     * @param entidad
     * @return recursos
     */
    @Override
    public ObservableList<Recurso> buscarRecursos(String entidad) {
        ObservableList<Recurso> recursos = FXCollections.observableArrayList();
        ArrayList<Recurso> listaRecursos;

        if (entidad.equals("todos")) {
            listaRecursos = buscarPalabraClave();
        } else {
            listaRecursos = buscarPalabraClaveRestringida(entidad);
        }

        if (!listaRecursos.isEmpty()) {
            for (int i = 0; i < listaRecursos.size(); i++) {
                recursos.add(listaRecursos.get(i));
            }
        }

        return recursos;
    }

    /**
     * el metódo retorna la consulta dependiendo de la entidad ingresada. <br>
     * NOTA: para solucionar el problema para ignorar acentos en consultas
     * PostgreSql Instalar la extensión unaccents -> create extension unaccent;
     *
     * @param entidad
     * @return query
     */
    private String getQueryPalabraClave(String entidad) {
        String query = "";

        switch (entidad) {
            case "libro":
                query = "SELECT codbarralibro AS codbarras, isbn AS isbn_issn, titulo, idioma, disponibilidad FROM libro "
                        + "WHERE unaccent(titulo) ILIKE unaccent('%" + palabraClave + "%');";
                break;
            case "enciclopedia":
                query = "SELECT codbarraenciclopedia AS codbarras, isbn AS isbn_issn, titulo, idioma, disponibilidad FROM enciclopedia "
                        + "WHERE unaccent(titulo) ILIKE unaccent('%" + palabraClave + "%');";
                break;
            case "diccionario":
                query = "SELECT codbarradiccionario AS codbarras, isbn AS isbn_issn, titulo, idioma, disponibilidad FROM diccionario "
                        + "WHERE unaccent(titulo) ILIKE unaccent('%" + palabraClave + "%');";
                break;
            case "revista":
                query = "SELECT codbarrarevista AS codbarras, issn AS isbn_issn, titulo, idioma, disponibilidad FROM revista "
                        + "WHERE unaccent(titulo) ILIKE unaccent('%" + palabraClave + "%');";
                break;
            case "periodico":
                query = "SELECT codbarraperiodico AS codbarras, issn AS isbn_issn, nombreperiodico AS titulo, disponibilidad FROM periodico "
                        + "WHERE unaccent(nombreperiodico) ILIKE unaccent('%" + palabraClave + "%');";
                break;
            default:
                query = "SELECT codbarramapa AS codbarras, isbn AS isbn_issn, titulo, disponibilidad FROM mapa "
                        + "WHERE unaccent(titulo) ILIKE unaccent('%" + palabraClave + "%');";
                break;
        }

        return query;
    }

}

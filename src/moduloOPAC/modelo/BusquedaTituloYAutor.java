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
public class BusquedaTituloYAutor extends BusquedaAvanzadaAbs {

    private String titulo;
    private String nombreAutor;

    /**
     * constructor por parámetros.
     *
     * @param titulo
     * @param nombreAutor
     */
    public BusquedaTituloYAutor(String titulo, String nombreAutor) {
        this.titulo = titulo;
        this.nombreAutor = nombreAutor;
        connection = ConnectionBD.getInstance();
    }

    /**
     * el metódo busca en todas las entidades ("libro", "enciclopedia",
     * "diccionario") y delega la busqueda a otro metódo, retornando los
     * recursos encontrados.
     *
     * @return listaRecursos.
     */
    private ArrayList<Recurso> buscarPorTituloYAutor() {
        ArrayList<Recurso> listaRecursos = new ArrayList<>();
        ArrayList<Recurso> listaRecursosTMP;
        String[] entidades = {"libro", "enciclopedia", "diccionario"};

        for (String entidad : entidades) {
            listaRecursosTMP = buscarPorTituloYAutorRestringido(entidad);

            if (!listaRecursosTMP.isEmpty()) {
                listaRecursos.addAll(listaRecursosTMP);
            }
        }

        return listaRecursos;
    }

    /**
     * el metódo busca en una entidad especifica ("libro", "enciclopedia",
     * "diccionario"), retornando los recursos que cumplan con el criterio de
     * busqueda (titulo y nombreAutor).
     *
     * @return recursos
     */
    private ArrayList<Recurso> buscarPorTituloYAutorRestringido(String entidad) {
        ArrayList<Recurso> listaRecursos = new ArrayList<>();

        try {
            Statement stmt = connection.getConnection().createStatement();
            String queryTitAut = getQueryTituloAutor(entidad);
            ResultSet rs = stmt.executeQuery(queryTitAut);

            while (rs.next()) {
                Recurso recurso = new Recurso();
                recurso.setCodBarras(rs.getString("codbarras"));
                recurso.setIsbn_issn(rs.getString("isbn"));
                recurso.setTitulo(rs.getString("titulo"));
                recurso.setIdioma(rs.getString("idioma"));
                recurso.setDisponibilidad(rs.getString("disponibilidad"));
                recurso.setTipoRecurso(entidad);
                listaRecursos.add(recurso);
            }

            rs.close();
        } catch (SQLException ex) {
            System.out.println("Error al consultar el " + entidad + ", por titulo y autor.");
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
            listaRecursos = buscarPorTituloYAutor();
        } else {
            listaRecursos = buscarPorTituloYAutorRestringido(entidad);
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
    private String getQueryTituloAutor(String entidad) {
        String query = "";

        switch (entidad) {
            case "libro":
                query = "SELECT codbarralibro AS codbarras , isbn, titulo, idioma, disponibilidad FROM libro "
                        + "WHERE unaccent(titulo) ILIKE unaccent('%" + titulo + "%') AND "
                        + "codbarralibro IN (SELECT codbarralibro FROM autor_por_libro "
                        + "WHERE codautorlibro IN (SELECT codautorlibro FROM autor_libro "
                        + "WHERE unaccent(nombres || ' ' || apellidos) ILIKE unaccent('%" + nombreAutor + "%')));";
                break;
            case "enciclopedia":
                query = "SELECT codbarraenciclopedia AS codbarras , isbn, titulo, idioma, disponibilidad FROM enciclopedia "
                        + "WHERE unaccent(titulo) ILIKE unaccent('%" + titulo + "%') AND "
                        + "codbarraenciclopedia IN (SELECT codbarraenciclopedia FROM autor_por_enciclopedia "
                        + "WHERE codautorenciclopedia IN (SELECT codautorenciclopedia FROM autor_enciclopedia "
                        + "WHERE unaccent(nombres || ' ' || apellidos) ILIKE unaccent('%" + nombreAutor + "%')));";
                break;
            default:
                query = "SELECT codbarradiccionario AS codbarras , isbn, titulo, idioma, disponibilidad FROM diccionario "
                        + "WHERE unaccent(titulo) ILIKE unaccent('%" + titulo + "%') AND "
                        + "codbarradiccionario IN (SELECT codbarradiccionario FROM autor_por_diccionario "
                        + "WHERE codautordiccionario IN (SELECT codautordiccionario FROM autor_diccionario "
                        + "WHERE unaccent(nombres || ' ' || apellidos) ILIKE unaccent('%" + nombreAutor + "%')));";
                break;
        }

        return query;
    }

}

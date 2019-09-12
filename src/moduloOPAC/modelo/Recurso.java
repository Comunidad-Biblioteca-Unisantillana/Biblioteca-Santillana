package moduloOPAC.modelo;

/**
 *
 * @author Miguel Fernández
 * @creado 17/08/2019
 * @author Miguel Fernández
 * @modificado 17/08/2019
 */
public class Recurso {

    private String codBarras;
    private String isbn_issn;
    private String titulo;
    private String idioma;
    private String disponibilidad;
    private String tipoRecurso;

    /**
     * constructor sin parámetros.
     */
    public Recurso() {

    }

    /**
     * constructor por parámetros.
     *
     * @param codBarras
     * @param isbn_issn
     * @param titulo
     * @param idioma
     * @param disponibilidad
     * @param tipoRecurso
     */
    public Recurso(String codBarras, String isbn_issn, String titulo, String idioma, String disponibilidad, String tipoRecurso) {
        this.codBarras = codBarras;
        this.isbn_issn = isbn_issn;
        this.titulo = titulo;
        this.idioma = idioma;
        this.disponibilidad = disponibilidad;
        this.tipoRecurso = tipoRecurso;
    }

    /**
     * el metódo retorna el codigo de barras.
     *
     * @return codBarras
     */
    public String getCodBarras() {
        return codBarras;
    }

    /**
     * el metódo retorna el isbn_issn.
     *
     * @return isbn_issn
     */
    public String getIsbn_issn() {
        return isbn_issn;
    }

    /**
     * el metódo retorna el titulo.
     *
     * @return titulo
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * el metódo retorna el titulo.
     *
     * @return idioma
     */
    public String getIdioma() {
        return idioma;
    }

    /**
     * el metódo retorna la disponibilidad.
     *
     * @return disponibilidad
     */
    public String getDisponibilidad() {
        return disponibilidad;
    }

    /**
     * el metódo retorna el tipo de recurso.
     *
     * @return tipoRecurso
     */
    public String getTipoRecurso() {
        return tipoRecurso;
    }

    /**
     * el metódo modifica el codigo de barras.
     *
     * @param codBarras
     */
    public void setCodBarras(String codBarras) {
        this.codBarras = codBarras;
    }

    /**
     * el metódo modifica el isbn_issn.
     *
     * @param isbn_issn
     */
    public void setIsbn_issn(String isbn_issn) {
        this.isbn_issn = isbn_issn;
    }

    /**
     * el metódo modifica el titulo.
     *
     * @param titulo
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * el metódo modificael idioma.
     *
     * @param idioma
     */
    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    /**
     * el metódo modifica la disponibilidad.
     *
     * @param disponibilidad
     */
    public void setDisponibilidad(String disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    /**
     * el metódo modifica el tipo de recurso.
     *
     * @param tipoRecurso
     */
    public void setTipoRecurso(String tipoRecurso) {
        this.tipoRecurso = tipoRecurso;
    }

}

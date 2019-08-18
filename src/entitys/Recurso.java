
package entitys;

/**
 *
 * @author Camilo
 */
public class Recurso {
    
    private String codBarras;
    private String isbn_issn;
    private String titulo;
    private String idioma;
    private String area;
    private String disponibilidad;

    public Recurso(String codBarras, String isbn_issn, String titulo, String idioma, String area, String disponibilidad) {
        this.codBarras = codBarras;
        this.isbn_issn = isbn_issn;
        this.titulo = titulo;
        this.idioma = idioma;
        this.area = area;
        this.disponibilidad = disponibilidad;
    }

    public String getCodBarras() {
        return codBarras;
    }

    public void setCodBarras(String codBarras) {
        this.codBarras = codBarras;
    }

    public String getIsbn_issn() {
        return isbn_issn;
    }

    public void setIsbn_issn(String isbn_issn) {
        this.isbn_issn = isbn_issn;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(String disponibilidad) {
        this.disponibilidad = disponibilidad;
    }
    
    
}

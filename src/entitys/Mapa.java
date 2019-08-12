
package entitys;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Camilo
 */
@Entity
@Table(name = "mapa")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Mapa.findAll", query = "SELECT m FROM Mapa m")
    , @NamedQuery(name = "Mapa.findByCodbarramapa", query = "SELECT m FROM Mapa m WHERE m.codbarramapa = :codbarramapa")
    , @NamedQuery(name = "Mapa.findByIsbn", query = "SELECT m FROM Mapa m WHERE m.isbn = :isbn")
    , @NamedQuery(name = "Mapa.findByTitulo", query = "SELECT m FROM Mapa m WHERE m.titulo = :titulo")
    , @NamedQuery(name = "Mapa.findByEscalarepresentativa", query = "SELECT m FROM Mapa m WHERE m.escalarepresentativa = :escalarepresentativa")
    , @NamedQuery(name = "Mapa.findByFormatomapa", query = "SELECT m FROM Mapa m WHERE m.formatomapa = :formatomapa")
    , @NamedQuery(name = "Mapa.findByDescripcion", query = "SELECT m FROM Mapa m WHERE m.descripcion = :descripcion")
    , @NamedQuery(name = "Mapa.findByPresentacion", query = "SELECT m FROM Mapa m WHERE m.presentacion = :presentacion")
    , @NamedQuery(name = "Mapa.findByDisponibilidad", query = "SELECT m FROM Mapa m WHERE m.disponibilidad = :disponibilidad")
    , @NamedQuery(name = "Mapa.findByEstadofisico", query = "SELECT m FROM Mapa m WHERE m.estadofisico = :estadofisico")})
public class Mapa implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codbarramapa")
    private Collection<PrestamoMapaProfesor> prestamoMapaProfesorCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codbarramapa")
    private Collection<PrestamoMapaEstudiante> prestamoMapaEstudianteCollection;

    

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "codbarramapa")
    private String codbarramapa;
    @Basic(optional = false)
    @Column(name = "isbn")
    private String isbn;
    @Basic(optional = false)
    @Column(name = "titulo")
    private String titulo;
    @Basic(optional = false)
    @Column(name = "escalarepresentativa")
    private String escalarepresentativa;
    @Basic(optional = false)
    @Column(name = "formatomapa")
    private String formatomapa;
    @Basic(optional = false)
    @Column(name = "descripcion")
    private String descripcion;
    @Basic(optional = false)
    @Column(name = "presentacion")
    private String presentacion;
    @Basic(optional = false)
    @Column(name = "disponibilidad")
    private String disponibilidad;
    @Basic(optional = false)
    @Column(name = "estadofisico")
    private String estadofisico;
    @JoinColumn(name = "codcategoriacoleccion", referencedColumnName = "codcategoriacoleccion")
    @ManyToOne(optional = false)
    private CategoriaColeccion codcategoriacoleccion;

    public Mapa() {
    }

    public Mapa(String codbarramapa) {
        this.codbarramapa = codbarramapa;
    }

    public Mapa(String codbarramapa, String isbn, String titulo, String escalarepresentativa, String formatomapa, String descripcion, String presentacion, String disponibilidad, String estadofisico) {
        this.codbarramapa = codbarramapa;
        this.isbn = isbn;
        this.titulo = titulo;
        this.escalarepresentativa = escalarepresentativa;
        this.formatomapa = formatomapa;
        this.descripcion = descripcion;
        this.presentacion = presentacion;
        this.disponibilidad = disponibilidad;
        this.estadofisico = estadofisico;
    }

    public String getCodbarramapa() {
        return codbarramapa;
    }

    public void setCodbarramapa(String codbarramapa) {
        this.codbarramapa = codbarramapa;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getEscalarepresentativa() {
        return escalarepresentativa;
    }

    public void setEscalarepresentativa(String escalarepresentativa) {
        this.escalarepresentativa = escalarepresentativa;
    }

    public String getFormatomapa() {
        return formatomapa;
    }

    public void setFormatomapa(String formatomapa) {
        this.formatomapa = formatomapa;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getPresentacion() {
        return presentacion;
    }

    public void setPresentacion(String presentacion) {
        this.presentacion = presentacion;
    }

    public String getDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(String disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    public String getEstadofisico() {
        return estadofisico;
    }

    public void setEstadofisico(String estadofisico) {
        this.estadofisico = estadofisico;
    }

    public CategoriaColeccion getCodcategoriacoleccion() {
        return codcategoriacoleccion;
    }

    public void setCodcategoriacoleccion(CategoriaColeccion codcategoriacoleccion) {
        this.codcategoriacoleccion = codcategoriacoleccion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codbarramapa != null ? codbarramapa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Mapa)) {
            return false;
        }
        Mapa other = (Mapa) object;
        if ((this.codbarramapa == null && other.codbarramapa != null) || (this.codbarramapa != null && !this.codbarramapa.equals(other.codbarramapa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entitys.Mapa[ codbarramapa=" + codbarramapa + " ]";
    }

    @XmlTransient
    public Collection<PrestamoMapaProfesor> getPrestamoMapaProfesorCollection() {
        return prestamoMapaProfesorCollection;
    }

    public void setPrestamoMapaProfesorCollection(Collection<PrestamoMapaProfesor> prestamoMapaProfesorCollection) {
        this.prestamoMapaProfesorCollection = prestamoMapaProfesorCollection;
    }

    @XmlTransient
    public Collection<PrestamoMapaEstudiante> getPrestamoMapaEstudianteCollection() {
        return prestamoMapaEstudianteCollection;
    }

    public void setPrestamoMapaEstudianteCollection(Collection<PrestamoMapaEstudiante> prestamoMapaEstudianteCollection) {
        this.prestamoMapaEstudianteCollection = prestamoMapaEstudianteCollection;
    }
}

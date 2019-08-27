package recursos.entitys;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author
 * @creado 
 * @author 
 * @modificado 
 */
@Entity
@Table(name = "enciclopedia")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Enciclopedia.findAll", query = "SELECT e FROM Enciclopedia e")
    , @NamedQuery(name = "Enciclopedia.findByCodbarraenciclopedia", query = "SELECT e FROM Enciclopedia e WHERE e.codbarraenciclopedia = :codbarraenciclopedia")
    , @NamedQuery(name = "Enciclopedia.findByIsbn", query = "SELECT e FROM Enciclopedia e WHERE e.isbn = :isbn")
    , @NamedQuery(name = "Enciclopedia.findByTitulo", query = "SELECT e FROM Enciclopedia e WHERE e.titulo = :titulo")
    , @NamedQuery(name = "Enciclopedia.findByIdioma", query = "SELECT e FROM Enciclopedia e WHERE e.idioma = :idioma")
    , @NamedQuery(name = "Enciclopedia.findByPaispublicacion", query = "SELECT e FROM Enciclopedia e WHERE e.paispublicacion = :paispublicacion")
    , @NamedQuery(name = "Enciclopedia.findByFechapublicacion", query = "SELECT e FROM Enciclopedia e WHERE e.fechapublicacion = :fechapublicacion")
    , @NamedQuery(name = "Enciclopedia.findByEditorial", query = "SELECT e FROM Enciclopedia e WHERE e.editorial = :editorial")
    , @NamedQuery(name = "Enciclopedia.findByNumvolumen", query = "SELECT e FROM Enciclopedia e WHERE e.numvolumen = :numvolumen")
    , @NamedQuery(name = "Enciclopedia.findByNumpaginas", query = "SELECT e FROM Enciclopedia e WHERE e.numpaginas = :numpaginas")
    , @NamedQuery(name = "Enciclopedia.findByCubierta", query = "SELECT e FROM Enciclopedia e WHERE e.cubierta = :cubierta")
    , @NamedQuery(name = "Enciclopedia.findByDimensiones", query = "SELECT e FROM Enciclopedia e WHERE e.dimensiones = :dimensiones")
    , @NamedQuery(name = "Enciclopedia.findByNota", query = "SELECT e FROM Enciclopedia e WHERE e.nota = :nota")
    , @NamedQuery(name = "Enciclopedia.findByResumen", query = "SELECT e FROM Enciclopedia e WHERE e.resumen = :resumen")
    , @NamedQuery(name = "Enciclopedia.findBySignatura", query = "SELECT e FROM Enciclopedia e WHERE e.signatura = :signatura")
    , @NamedQuery(name = "Enciclopedia.findByDisponibilidad", query = "SELECT e FROM Enciclopedia e WHERE e.disponibilidad = :disponibilidad")
    , @NamedQuery(name = "Enciclopedia.findByEstadofisico", query = "SELECT e FROM Enciclopedia e WHERE e.estadofisico = :estadofisico")})
public class Enciclopedia implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "codbarraenciclopedia")
    private String codbarraenciclopedia;
    @Basic(optional = false)
    @Column(name = "isbn")
    private String isbn;
    @Basic(optional = false)
    @Column(name = "titulo")
    private String titulo;
    @Basic(optional = false)
    @Column(name = "idioma")
    private String idioma;
    @Basic(optional = false)
    @Column(name = "paispublicacion")
    private String paispublicacion;
    @Basic(optional = false)
    @Column(name = "fechapublicacion")
    @Temporal(TemporalType.DATE)
    private Date fechapublicacion;
    @Basic(optional = false)
    @Column(name = "editorial")
    private String editorial;
    @Basic(optional = false)
    @Column(name = "numvolumen")
    private int numvolumen;
    @Basic(optional = false)
    @Column(name = "numpaginas")
    private int numpaginas;
    @Basic(optional = false)
    @Column(name = "cubierta")
    private String cubierta;
    @Basic(optional = false)
    @Column(name = "dimensiones")
    private String dimensiones;
    @Basic(optional = false)
    @Column(name = "nota")
    private String nota;
    @Basic(optional = false)
    @Column(name = "resumen")
    private String resumen;
    @Basic(optional = false)
    @Column(name = "signatura")
    private String signatura;
    @Basic(optional = false)
    @Column(name = "disponibilidad")
    private String disponibilidad;
    @Basic(optional = false)
    @Column(name = "estadofisico")
    private String estadofisico;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codbarraenciclopedia")
    private List<MateriaPorEnciclopedia> materiaPorEnciclopediaList;
    @JoinColumn(name = "codcategoriacoleccion", referencedColumnName = "codcategoriacoleccion")
    @ManyToOne(optional = false)
    private CategoriaColeccion codcategoriacoleccion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codbarraenciclopedia")
    private List<AutorPorEnciclopedia> autorPorEnciclopediaList;

    public Enciclopedia() {
    }

    public Enciclopedia(String codbarraenciclopedia) {
        this.codbarraenciclopedia = codbarraenciclopedia;
    }

    public Enciclopedia(String codbarraenciclopedia, String isbn, String titulo, String idioma, String paispublicacion, Date fechapublicacion, String editorial, int numvolumen, int numpaginas, String cubierta, String dimensiones, String nota, String resumen, String signatura, String disponibilidad, String estadofisico) {
        this.codbarraenciclopedia = codbarraenciclopedia;
        this.isbn = isbn;
        this.titulo = titulo;
        this.idioma = idioma;
        this.paispublicacion = paispublicacion;
        this.fechapublicacion = fechapublicacion;
        this.editorial = editorial;
        this.numvolumen = numvolumen;
        this.numpaginas = numpaginas;
        this.cubierta = cubierta;
        this.dimensiones = dimensiones;
        this.nota = nota;
        this.resumen = resumen;
        this.signatura = signatura;
        this.disponibilidad = disponibilidad;
        this.estadofisico = estadofisico;
    }

    public String getCodbarraenciclopedia() {
        return codbarraenciclopedia;
    }

    public void setCodbarraenciclopedia(String codbarraenciclopedia) {
        this.codbarraenciclopedia = codbarraenciclopedia;
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

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public String getPaispublicacion() {
        return paispublicacion;
    }

    public void setPaispublicacion(String paispublicacion) {
        this.paispublicacion = paispublicacion;
    }

    public Date getFechapublicacion() {
        return fechapublicacion;
    }

    public void setFechapublicacion(Date fechapublicacion) {
        this.fechapublicacion = fechapublicacion;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public int getNumvolumen() {
        return numvolumen;
    }

    public void setNumvolumen(int numvolumen) {
        this.numvolumen = numvolumen;
    }

    public int getNumpaginas() {
        return numpaginas;
    }

    public void setNumpaginas(int numpaginas) {
        this.numpaginas = numpaginas;
    }

    public String getCubierta() {
        return cubierta;
    }

    public void setCubierta(String cubierta) {
        this.cubierta = cubierta;
    }

    public String getDimensiones() {
        return dimensiones;
    }

    public void setDimensiones(String dimensiones) {
        this.dimensiones = dimensiones;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }

    public String getResumen() {
        return resumen;
    }

    public void setResumen(String resumen) {
        this.resumen = resumen;
    }

    public String getSignatura() {
        return signatura;
    }

    public void setSignatura(String signatura) {
        this.signatura = signatura;
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

    @XmlTransient
    public List<MateriaPorEnciclopedia> getMateriaPorEnciclopediaList() {
        return materiaPorEnciclopediaList;
    }

    public void setMateriaPorEnciclopediaList(List<MateriaPorEnciclopedia> materiaPorEnciclopediaList) {
        this.materiaPorEnciclopediaList = materiaPorEnciclopediaList;
    }

    public CategoriaColeccion getCodcategoriacoleccion() {
        return codcategoriacoleccion;
    }

    public void setCodcategoriacoleccion(CategoriaColeccion codcategoriacoleccion) {
        this.codcategoriacoleccion = codcategoriacoleccion;
    }

    @XmlTransient
    public List<AutorPorEnciclopedia> getAutorPorEnciclopediaList() {
        return autorPorEnciclopediaList;
    }

    public void setAutorPorEnciclopediaList(List<AutorPorEnciclopedia> autorPorEnciclopediaList) {
        this.autorPorEnciclopediaList = autorPorEnciclopediaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codbarraenciclopedia != null ? codbarraenciclopedia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Enciclopedia)) {
            return false;
        }
        Enciclopedia other = (Enciclopedia) object;
        if ((this.codbarraenciclopedia == null && other.codbarraenciclopedia != null) || (this.codbarraenciclopedia != null && !this.codbarraenciclopedia.equals(other.codbarraenciclopedia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entitys.Enciclopedia[ codbarraenciclopedia=" + codbarraenciclopedia + " ]";
    }
    
}

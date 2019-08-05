/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entitysRecursos;

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
 * @author Camilo
 */
@Entity
@Table(name = "libro")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Libro.findAll", query = "SELECT l FROM Libro l")
    , @NamedQuery(name = "Libro.findByCodbarralibro", query = "SELECT l FROM Libro l WHERE l.codbarralibro = :codbarralibro")
    , @NamedQuery(name = "Libro.findByIsbn", query = "SELECT l FROM Libro l WHERE l.isbn = :isbn")
    , @NamedQuery(name = "Libro.findByTitulo", query = "SELECT l FROM Libro l WHERE l.titulo = :titulo")
    , @NamedQuery(name = "Libro.findByIdioma", query = "SELECT l FROM Libro l WHERE l.idioma = :idioma")
    , @NamedQuery(name = "Libro.findByPaispublicacion", query = "SELECT l FROM Libro l WHERE l.paispublicacion = :paispublicacion")
    , @NamedQuery(name = "Libro.findByFechapublicacion", query = "SELECT l FROM Libro l WHERE l.fechapublicacion = :fechapublicacion")
    , @NamedQuery(name = "Libro.findByEditorial", query = "SELECT l FROM Libro l WHERE l.editorial = :editorial")
    , @NamedQuery(name = "Libro.findByNumedicion", query = "SELECT l FROM Libro l WHERE l.numedicion = :numedicion")
    , @NamedQuery(name = "Libro.findByNumpaginas", query = "SELECT l FROM Libro l WHERE l.numpaginas = :numpaginas")
    , @NamedQuery(name = "Libro.findByCubierta", query = "SELECT l FROM Libro l WHERE l.cubierta = :cubierta")
    , @NamedQuery(name = "Libro.findByDimensiones", query = "SELECT l FROM Libro l WHERE l.dimensiones = :dimensiones")
    , @NamedQuery(name = "Libro.findByNota", query = "SELECT l FROM Libro l WHERE l.nota = :nota")
    , @NamedQuery(name = "Libro.findByResumen", query = "SELECT l FROM Libro l WHERE l.resumen = :resumen")
    , @NamedQuery(name = "Libro.findBySignatura", query = "SELECT l FROM Libro l WHERE l.signatura = :signatura")
    , @NamedQuery(name = "Libro.findByDisponibilidad", query = "SELECT l FROM Libro l WHERE l.disponibilidad = :disponibilidad")
    , @NamedQuery(name = "Libro.findByEstadofisico", query = "SELECT l FROM Libro l WHERE l.estadofisico = :estadofisico")
    , @NamedQuery(name = "Libro.findByArea", query = "SELECT l FROM Libro l WHERE l.area = :area")})
public class Libro implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "codbarralibro")
    private String codbarralibro;
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
    @Column(name = "numedicion")
    private int numedicion;
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
    @Basic(optional = false)
    @Column(name = "area")
    private String area;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codbarralibro")
    private List<MateriaPorLibro> materiaPorLibroList;
    @JoinColumn(name = "codcategoriacoleccion", referencedColumnName = "codcategoriacoleccion")
    @ManyToOne(optional = false)
    private CategoriaColeccion codcategoriacoleccion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codbarralibro")
    private List<AutorPorLibro> autorPorLibroList;

    public Libro() {
    }

    public Libro(String codbarralibro) {
        this.codbarralibro = codbarralibro;
    }

    public Libro(String codbarralibro, String isbn, String titulo, String idioma, String paispublicacion, Date fechapublicacion, String editorial, int numedicion, int numpaginas, String cubierta, String dimensiones, String nota, String resumen, String signatura, String disponibilidad, String estadofisico, String area) {
        this.codbarralibro = codbarralibro;
        this.isbn = isbn;
        this.titulo = titulo;
        this.idioma = idioma;
        this.paispublicacion = paispublicacion;
        this.fechapublicacion = fechapublicacion;
        this.editorial = editorial;
        this.numedicion = numedicion;
        this.numpaginas = numpaginas;
        this.cubierta = cubierta;
        this.dimensiones = dimensiones;
        this.nota = nota;
        this.resumen = resumen;
        this.signatura = signatura;
        this.disponibilidad = disponibilidad;
        this.estadofisico = estadofisico;
        this.area = area;
    }

    public String getCodbarralibro() {
        return codbarralibro;
    }

    public void setCodbarralibro(String codbarralibro) {
        this.codbarralibro = codbarralibro;
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

    public int getNumedicion() {
        return numedicion;
    }

    public void setNumedicion(int numedicion) {
        this.numedicion = numedicion;
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

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    @XmlTransient
    public List<MateriaPorLibro> getMateriaPorLibroList() {
        return materiaPorLibroList;
    }

    public void setMateriaPorLibroList(List<MateriaPorLibro> materiaPorLibroList) {
        this.materiaPorLibroList = materiaPorLibroList;
    }

    public CategoriaColeccion getCodcategoriacoleccion() {
        return codcategoriacoleccion;
    }

    public void setCodcategoriacoleccion(CategoriaColeccion codcategoriacoleccion) {
        this.codcategoriacoleccion = codcategoriacoleccion;
    }

    @XmlTransient
    public List<AutorPorLibro> getAutorPorLibroList() {
        return autorPorLibroList;
    }

    public void setAutorPorLibroList(List<AutorPorLibro> autorPorLibroList) {
        this.autorPorLibroList = autorPorLibroList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codbarralibro != null ? codbarralibro.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Libro)) {
            return false;
        }
        Libro other = (Libro) object;
        if ((this.codbarralibro == null && other.codbarralibro != null) || (this.codbarralibro != null && !this.codbarralibro.equals(other.codbarralibro))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entitys.Libro[ codbarralibro=" + codbarralibro + " ]";
    }
    
}

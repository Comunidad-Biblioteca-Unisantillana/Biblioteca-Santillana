/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entitys;

import java.io.Serializable;
import java.util.Collection;
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
@Table(name = "diccionario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Diccionario.findAll", query = "SELECT d FROM Diccionario d")
    , @NamedQuery(name = "Diccionario.findByCodbarradiccionario", query = "SELECT d FROM Diccionario d WHERE d.codbarradiccionario = :codbarradiccionario")
    , @NamedQuery(name = "Diccionario.findByIsbn", query = "SELECT d FROM Diccionario d WHERE d.isbn = :isbn")
    , @NamedQuery(name = "Diccionario.findByTitulo", query = "SELECT d FROM Diccionario d WHERE d.titulo = :titulo")
    , @NamedQuery(name = "Diccionario.findByIdioma", query = "SELECT d FROM Diccionario d WHERE d.idioma = :idioma")
    , @NamedQuery(name = "Diccionario.findByPaispublicacion", query = "SELECT d FROM Diccionario d WHERE d.paispublicacion = :paispublicacion")
    , @NamedQuery(name = "Diccionario.findByFechapublicacion", query = "SELECT d FROM Diccionario d WHERE d.fechapublicacion = :fechapublicacion")
    , @NamedQuery(name = "Diccionario.findByEditorial", query = "SELECT d FROM Diccionario d WHERE d.editorial = :editorial")
    , @NamedQuery(name = "Diccionario.findByNumpaginas", query = "SELECT d FROM Diccionario d WHERE d.numpaginas = :numpaginas")
    , @NamedQuery(name = "Diccionario.findByCubierta", query = "SELECT d FROM Diccionario d WHERE d.cubierta = :cubierta")
    , @NamedQuery(name = "Diccionario.findByNota", query = "SELECT d FROM Diccionario d WHERE d.nota = :nota")
    , @NamedQuery(name = "Diccionario.findByResumen", query = "SELECT d FROM Diccionario d WHERE d.resumen = :resumen")
    , @NamedQuery(name = "Diccionario.findBySignatura", query = "SELECT d FROM Diccionario d WHERE d.signatura = :signatura")
    , @NamedQuery(name = "Diccionario.findByDisponibilidad", query = "SELECT d FROM Diccionario d WHERE d.disponibilidad = :disponibilidad")
    , @NamedQuery(name = "Diccionario.findByEstadofisico", query = "SELECT d FROM Diccionario d WHERE d.estadofisico = :estadofisico")
    , @NamedQuery(name = "Diccionario.findByArea", query = "SELECT d FROM Diccionario d WHERE d.area = :area")})
public class Diccionario implements Serializable {

    @Basic(optional = false)
    @Column(name = "dimensiones")
    private String dimensiones;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codbarradiccionario")
    private Collection<PrestamoDiccionarioEstudiante> prestamoDiccionarioEstudianteCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codbarradiccionario")
    private Collection<PrestamoDiccionarioProfesor> prestamoDiccionarioProfesorCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "codbarradiccionario")
    private String codbarradiccionario;
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
    @Column(name = "numpaginas")
    private int numpaginas;
    @Basic(optional = false)
    @Column(name = "cubierta")
    private String cubierta;
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
    @JoinColumn(name = "codcategoriacoleccion", referencedColumnName = "codcategoriacoleccion")
    @ManyToOne(optional = false)
    private CategoriaColeccion codcategoriacoleccion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codbarradiccionario")
    private List<AutorPorDiccionario> autorPorDiccionarioList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codbarradiccionario")
    private List<MateriaPorDiccionario> materiaPorDiccionarioList;

    public Diccionario() {
    }

    public Diccionario(String codbarradiccionario) {
        this.codbarradiccionario = codbarradiccionario;
    }

    public Diccionario(String codbarradiccionario, String isbn, String titulo, String idioma, String paispublicacion, Date fechapublicacion, String editorial, int numpaginas, String cubierta, String nota, String resumen, String signatura, String disponibilidad, String estadofisico, String area) {
        this.codbarradiccionario = codbarradiccionario;
        this.isbn = isbn;
        this.titulo = titulo;
        this.idioma = idioma;
        this.paispublicacion = paispublicacion;
        this.fechapublicacion = fechapublicacion;
        this.editorial = editorial;
        this.numpaginas = numpaginas;
        this.cubierta = cubierta;
        this.nota = nota;
        this.resumen = resumen;
        this.signatura = signatura;
        this.disponibilidad = disponibilidad;
        this.estadofisico = estadofisico;
        this.area = area;
    }

    public String getCodbarradiccionario() {
        return codbarradiccionario;
    }

    public void setCodbarradiccionario(String codbarradiccionario) {
        this.codbarradiccionario = codbarradiccionario;
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

    public CategoriaColeccion getCodcategoriacoleccion() {
        return codcategoriacoleccion;
    }

    public void setCodcategoriacoleccion(CategoriaColeccion codcategoriacoleccion) {
        this.codcategoriacoleccion = codcategoriacoleccion;
    }

    @XmlTransient
    public List<AutorPorDiccionario> getAutorPorDiccionarioList() {
        return autorPorDiccionarioList;
    }

    public void setAutorPorDiccionarioList(List<AutorPorDiccionario> autorPorDiccionarioList) {
        this.autorPorDiccionarioList = autorPorDiccionarioList;
    }

    @XmlTransient
    public List<MateriaPorDiccionario> getMateriaPorDiccionarioList() {
        return materiaPorDiccionarioList;
    }

    public void setMateriaPorDiccionarioList(List<MateriaPorDiccionario> materiaPorDiccionarioList) {
        this.materiaPorDiccionarioList = materiaPorDiccionarioList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codbarradiccionario != null ? codbarradiccionario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Diccionario)) {
            return false;
        }
        Diccionario other = (Diccionario) object;
        if ((this.codbarradiccionario == null && other.codbarradiccionario != null) || (this.codbarradiccionario != null && !this.codbarradiccionario.equals(other.codbarradiccionario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entitys.Diccionario[ codbarradiccionario=" + codbarradiccionario + " ]";
    }

    public String getDimensiones() {
        return dimensiones;
    }

    public void setDimensiones(String dimensiones) {
        this.dimensiones = dimensiones;
    }

    @XmlTransient
    public Collection<PrestamoDiccionarioEstudiante> getPrestamoDiccionarioEstudianteCollection() {
        return prestamoDiccionarioEstudianteCollection;
    }

    public void setPrestamoDiccionarioEstudianteCollection(Collection<PrestamoDiccionarioEstudiante> prestamoDiccionarioEstudianteCollection) {
        this.prestamoDiccionarioEstudianteCollection = prestamoDiccionarioEstudianteCollection;
    }

    @XmlTransient
    public Collection<PrestamoDiccionarioProfesor> getPrestamoDiccionarioProfesorCollection() {
        return prestamoDiccionarioProfesorCollection;
    }

    public void setPrestamoDiccionarioProfesorCollection(Collection<PrestamoDiccionarioProfesor> prestamoDiccionarioProfesorCollection) {
        this.prestamoDiccionarioProfesorCollection = prestamoDiccionarioProfesorCollection;
    }
    
}

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
@Table(name = "revista")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Revista.findAll", query = "SELECT r FROM Revista r")
    , @NamedQuery(name = "Revista.findByCodbarrarevista", query = "SELECT r FROM Revista r WHERE r.codbarrarevista = :codbarrarevista")
    , @NamedQuery(name = "Revista.findByIssn", query = "SELECT r FROM Revista r WHERE r.issn = :issn")
    , @NamedQuery(name = "Revista.findByTitulo", query = "SELECT r FROM Revista r WHERE r.titulo = :titulo")
    , @NamedQuery(name = "Revista.findByIdioma", query = "SELECT r FROM Revista r WHERE r.idioma = :idioma")
    , @NamedQuery(name = "Revista.findByPaispublicacion", query = "SELECT r FROM Revista r WHERE r.paispublicacion = :paispublicacion")
    , @NamedQuery(name = "Revista.findByFechapublicacion", query = "SELECT r FROM Revista r WHERE r.fechapublicacion = :fechapublicacion")
    , @NamedQuery(name = "Revista.findByPublicador", query = "SELECT r FROM Revista r WHERE r.publicador = :publicador")
    , @NamedQuery(name = "Revista.findByDisponibilidad", query = "SELECT r FROM Revista r WHERE r.disponibilidad = :disponibilidad")
    , @NamedQuery(name = "Revista.findByEstadofisico", query = "SELECT r FROM Revista r WHERE r.estadofisico = :estadofisico")})
public class Revista implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "codbarrarevista")
    private String codbarrarevista;
    @Basic(optional = false)
    @Column(name = "issn")
    private String issn;
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
    @Column(name = "publicador")
    private String publicador;
    @Basic(optional = false)
    @Column(name = "disponibilidad")
    private String disponibilidad;
    @Basic(optional = false)
    @Column(name = "estadofisico")
    private String estadofisico;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codbarrarevista")
    private List<MateriaPorRevista> materiaPorRevistaList;
    @JoinColumn(name = "codcategoriacoleccion", referencedColumnName = "codcategoriacoleccion")
    @ManyToOne(optional = false)
    private CategoriaColeccion codcategoriacoleccion;

    public Revista() {
    }

    public Revista(String codbarrarevista) {
        this.codbarrarevista = codbarrarevista;
    }

    public Revista(String codbarrarevista, String issn, String titulo, String idioma, String paispublicacion, Date fechapublicacion, String publicador, String disponibilidad, String estadofisico) {
        this.codbarrarevista = codbarrarevista;
        this.issn = issn;
        this.titulo = titulo;
        this.idioma = idioma;
        this.paispublicacion = paispublicacion;
        this.fechapublicacion = fechapublicacion;
        this.publicador = publicador;
        this.disponibilidad = disponibilidad;
        this.estadofisico = estadofisico;
    }

    public String getCodbarrarevista() {
        return codbarrarevista;
    }

    public void setCodbarrarevista(String codbarrarevista) {
        this.codbarrarevista = codbarrarevista;
    }

    public String getIssn() {
        return issn;
    }

    public void setIssn(String issn) {
        this.issn = issn;
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

    public String getPublicador() {
        return publicador;
    }

    public void setPublicador(String publicador) {
        this.publicador = publicador;
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
    public List<MateriaPorRevista> getMateriaPorRevistaList() {
        return materiaPorRevistaList;
    }

    public void setMateriaPorRevistaList(List<MateriaPorRevista> materiaPorRevistaList) {
        this.materiaPorRevistaList = materiaPorRevistaList;
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
        hash += (codbarrarevista != null ? codbarrarevista.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Revista)) {
            return false;
        }
        Revista other = (Revista) object;
        if ((this.codbarrarevista == null && other.codbarrarevista != null) || (this.codbarrarevista != null && !this.codbarrarevista.equals(other.codbarrarevista))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entitys.Revista[ codbarrarevista=" + codbarrarevista + " ]";
    }
    
}

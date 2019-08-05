/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entitysRecursos;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
@Table(name = "categoria_coleccion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CategoriaColeccion.findAll", query = "SELECT c FROM CategoriaColeccion c")
    , @NamedQuery(name = "CategoriaColeccion.findByCodcategoriacoleccion", query = "SELECT c FROM CategoriaColeccion c WHERE c.codcategoriacoleccion = :codcategoriacoleccion")
    , @NamedQuery(name = "CategoriaColeccion.findByNombrecol", query = "SELECT c FROM CategoriaColeccion c WHERE c.nombrecol = :nombrecol")
    , @NamedQuery(name = "CategoriaColeccion.findByDiasprestamo", query = "SELECT c FROM CategoriaColeccion c WHERE c.diasprestamo = :diasprestamo")
    , @NamedQuery(name = "CategoriaColeccion.findByPrestamoexterno", query = "SELECT c FROM CategoriaColeccion c WHERE c.prestamoexterno = :prestamoexterno")
    , @NamedQuery(name = "CategoriaColeccion.findByPermitereservas", query = "SELECT c FROM CategoriaColeccion c WHERE c.permitereservas = :permitereservas")})
public class CategoriaColeccion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "codcategoriacoleccion")
    private String codcategoriacoleccion;
    @Basic(optional = false)
    @Column(name = "nombrecol")
    private String nombrecol;
    @Basic(optional = false)
    @Column(name = "diasprestamo")
    private int diasprestamo;
    @Basic(optional = false)
    @Column(name = "prestamoexterno")
    private boolean prestamoexterno;
    @Basic(optional = false)
    @Column(name = "permitereservas")
    private boolean permitereservas;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codcategoriacoleccion")
    private List<Libro> libroList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codcategoriacoleccion")
    private List<Revista> revistaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codcategoriacoleccion")
    private List<Diccionario> diccionarioList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codcategoriacoleccion")
    private List<Periodico> periodicoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codcategoriacoleccion")
    private List<Mapa> mapaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codcategoriacoleccion")
    private List<Enciclopedia> enciclopediaList;

    public CategoriaColeccion() {
    }

    public CategoriaColeccion(String codcategoriacoleccion) {
        this.codcategoriacoleccion = codcategoriacoleccion;
    }

    public CategoriaColeccion(String codcategoriacoleccion, String nombrecol, int diasprestamo, boolean prestamoexterno, boolean permitereservas) {
        this.codcategoriacoleccion = codcategoriacoleccion;
        this.nombrecol = nombrecol;
        this.diasprestamo = diasprestamo;
        this.prestamoexterno = prestamoexterno;
        this.permitereservas = permitereservas;
    }

    public String getCodcategoriacoleccion() {
        return codcategoriacoleccion;
    }

    public void setCodcategoriacoleccion(String codcategoriacoleccion) {
        this.codcategoriacoleccion = codcategoriacoleccion;
    }

    public String getNombrecol() {
        return nombrecol;
    }

    public void setNombrecol(String nombrecol) {
        this.nombrecol = nombrecol;
    }

    public int getDiasprestamo() {
        return diasprestamo;
    }

    public void setDiasprestamo(int diasprestamo) {
        this.diasprestamo = diasprestamo;
    }

    public boolean getPrestamoexterno() {
        return prestamoexterno;
    }

    public void setPrestamoexterno(boolean prestamoexterno) {
        this.prestamoexterno = prestamoexterno;
    }

    public boolean getPermitereservas() {
        return permitereservas;
    }

    public void setPermitereservas(boolean permitereservas) {
        this.permitereservas = permitereservas;
    }

    @XmlTransient
    public List<Libro> getLibroList() {
        return libroList;
    }

    public void setLibroList(List<Libro> libroList) {
        this.libroList = libroList;
    }

    @XmlTransient
    public List<Revista> getRevistaList() {
        return revistaList;
    }

    public void setRevistaList(List<Revista> revistaList) {
        this.revistaList = revistaList;
    }

    @XmlTransient
    public List<Diccionario> getDiccionarioList() {
        return diccionarioList;
    }

    public void setDiccionarioList(List<Diccionario> diccionarioList) {
        this.diccionarioList = diccionarioList;
    }

    @XmlTransient
    public List<Periodico> getPeriodicoList() {
        return periodicoList;
    }

    public void setPeriodicoList(List<Periodico> periodicoList) {
        this.periodicoList = periodicoList;
    }

    @XmlTransient
    public List<Mapa> getMapaList() {
        return mapaList;
    }

    public void setMapaList(List<Mapa> mapaList) {
        this.mapaList = mapaList;
    }

    @XmlTransient
    public List<Enciclopedia> getEnciclopediaList() {
        return enciclopediaList;
    }

    public void setEnciclopediaList(List<Enciclopedia> enciclopediaList) {
        this.enciclopediaList = enciclopediaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codcategoriacoleccion != null ? codcategoriacoleccion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CategoriaColeccion)) {
            return false;
        }
        CategoriaColeccion other = (CategoriaColeccion) object;
        if ((this.codcategoriacoleccion == null && other.codcategoriacoleccion != null) || (this.codcategoriacoleccion != null && !this.codcategoriacoleccion.equals(other.codcategoriacoleccion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entitys.CategoriaColeccion[ codcategoriacoleccion=" + codcategoriacoleccion + " ]";
    }
    
}

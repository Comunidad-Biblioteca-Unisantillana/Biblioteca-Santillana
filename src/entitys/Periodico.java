/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entitys;

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
 * @author Storkolm
 */
@Entity
@Table(name = "periodico")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Periodico.findAll", query = "SELECT p FROM Periodico p")
    , @NamedQuery(name = "Periodico.findByCodbarraperiodico", query = "SELECT p FROM Periodico p WHERE p.codbarraperiodico = :codbarraperiodico")
    , @NamedQuery(name = "Periodico.findByIssn", query = "SELECT p FROM Periodico p WHERE p.issn = :issn")
    , @NamedQuery(name = "Periodico.findByNombreperiodico", query = "SELECT p FROM Periodico p WHERE p.nombreperiodico = :nombreperiodico")
    , @NamedQuery(name = "Periodico.findByFechapublicacion", query = "SELECT p FROM Periodico p WHERE p.fechapublicacion = :fechapublicacion")
    , @NamedQuery(name = "Periodico.findByCiudad", query = "SELECT p FROM Periodico p WHERE p.ciudad = :ciudad")
    , @NamedQuery(name = "Periodico.findByPaginas", query = "SELECT p FROM Periodico p WHERE p.paginas = :paginas")
    , @NamedQuery(name = "Periodico.findByEditorial", query = "SELECT p FROM Periodico p WHERE p.editorial = :editorial")
    , @NamedQuery(name = "Periodico.findByDisponibilidad", query = "SELECT p FROM Periodico p WHERE p.disponibilidad = :disponibilidad")
    , @NamedQuery(name = "Periodico.findByEstadofisico", query = "SELECT p FROM Periodico p WHERE p.estadofisico = :estadofisico")})
public class Periodico implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "codbarraperiodico")
    private String codbarraperiodico;
    @Basic(optional = false)
    @Column(name = "issn")
    private String issn;
    @Basic(optional = false)
    @Column(name = "nombreperiodico")
    private String nombreperiodico;
    @Basic(optional = false)
    @Column(name = "fechapublicacion")
    @Temporal(TemporalType.DATE)
    private Date fechapublicacion;
    @Basic(optional = false)
    @Column(name = "ciudad")
    private String ciudad;
    @Basic(optional = false)
    @Column(name = "paginas")
    private int paginas;
    @Basic(optional = false)
    @Column(name = "editorial")
    private String editorial;
    @Basic(optional = false)
    @Column(name = "disponibilidad")
    private String disponibilidad;
    @Basic(optional = false)
    @Column(name = "estadofisico")
    private String estadofisico;
    @JoinColumn(name = "codcategoriacoleccion", referencedColumnName = "codcategoriacoleccion")
    @ManyToOne(optional = false)
    private CategoriaColeccion codcategoriacoleccion;

    public Periodico() {
    }

    public Periodico(String codbarraperiodico) {
        this.codbarraperiodico = codbarraperiodico;
    }

    public Periodico(String codbarraperiodico, String issn, String nombreperiodico, Date fechapublicacion, String ciudad, int paginas, String editorial, String disponibilidad, String estadofisico) {
        this.codbarraperiodico = codbarraperiodico;
        this.issn = issn;
        this.nombreperiodico = nombreperiodico;
        this.fechapublicacion = fechapublicacion;
        this.ciudad = ciudad;
        this.paginas = paginas;
        this.editorial = editorial;
        this.disponibilidad = disponibilidad;
        this.estadofisico = estadofisico;
    }

    public String getCodbarraperiodico() {
        return codbarraperiodico;
    }

    public void setCodbarraperiodico(String codbarraperiodico) {
        this.codbarraperiodico = codbarraperiodico;
    }

    public String getIssn() {
        return issn;
    }

    public void setIssn(String issn) {
        this.issn = issn;
    }

    public String getNombreperiodico() {
        return nombreperiodico;
    }

    public void setNombreperiodico(String nombreperiodico) {
        this.nombreperiodico = nombreperiodico;
    }

    public Date getFechapublicacion() {
        return fechapublicacion;
    }

    public void setFechapublicacion(Date fechapublicacion) {
        this.fechapublicacion = fechapublicacion;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public int getPaginas() {
        return paginas;
    }

    public void setPaginas(int paginas) {
        this.paginas = paginas;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
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
        hash += (codbarraperiodico != null ? codbarraperiodico.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Periodico)) {
            return false;
        }
        Periodico other = (Periodico) object;
        if ((this.codbarraperiodico == null && other.codbarraperiodico != null) || (this.codbarraperiodico != null && !this.codbarraperiodico.equals(other.codbarraperiodico))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entitys.Periodico[ codbarraperiodico=" + codbarraperiodico + " ]";
    }

    
    
}

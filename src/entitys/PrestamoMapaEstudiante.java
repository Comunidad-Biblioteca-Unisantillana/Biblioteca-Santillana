/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entitys;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "prestamo_mapa_estudiante")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PrestamoMapaEstudiante.findAll", query = "SELECT p FROM PrestamoMapaEstudiante p")
    , @NamedQuery(name = "PrestamoMapaEstudiante.findByCodprestmapaest", query = "SELECT p FROM PrestamoMapaEstudiante p WHERE p.codprestmapaest = :codprestmapaest")
    , @NamedQuery(name = "PrestamoMapaEstudiante.findByFechaprestamo", query = "SELECT p FROM PrestamoMapaEstudiante p WHERE p.fechaprestamo = :fechaprestamo")
    , @NamedQuery(name = "PrestamoMapaEstudiante.findByFechadevolucion", query = "SELECT p FROM PrestamoMapaEstudiante p WHERE p.fechadevolucion = :fechadevolucion")
    , @NamedQuery(name = "PrestamoMapaEstudiante.findByDevuelto", query = "SELECT p FROM PrestamoMapaEstudiante p WHERE p.devuelto = :devuelto")})
public class PrestamoMapaEstudiante implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codprestmapaest")
    private Integer codprestmapaest;
    @Basic(optional = false)
    @Column(name = "fechaprestamo")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaprestamo;
    @Basic(optional = false)
    @Column(name = "fechadevolucion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechadevolucion;
    @Basic(optional = false)
    @Column(name = "devuelto")
    private String devuelto;
    @JoinColumn(name = "idbibliotecario", referencedColumnName = "idbibliotecario")
    @ManyToOne(optional = false)
    private Bibliotecario idbibliotecario;
    @JoinColumn(name = "codestudiante", referencedColumnName = "codestudiante")
    @ManyToOne(optional = false)
    private Estudiante codestudiante;
    @JoinColumn(name = "codbarramapa", referencedColumnName = "codbarramapa")
    @ManyToOne(optional = false)
    private Mapa codbarramapa;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codprestmapaest")
    private Collection<MultaMapaEstudiante> multaMapaEstudianteCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codprestmapaest")
    private Collection<DevolucionMapaEstudiante> devolucionMapaEstudianteCollection;

    public PrestamoMapaEstudiante() {
    }

    public PrestamoMapaEstudiante(Integer codprestmapaest) {
        this.codprestmapaest = codprestmapaest;
    }

    public PrestamoMapaEstudiante(Integer codprestmapaest, Date fechaprestamo, Date fechadevolucion, String devuelto) {
        this.codprestmapaest = codprestmapaest;
        this.fechaprestamo = fechaprestamo;
        this.fechadevolucion = fechadevolucion;
        this.devuelto = devuelto;
    }

    public Integer getCodprestmapaest() {
        return codprestmapaest;
    }

    public void setCodprestmapaest(Integer codprestmapaest) {
        this.codprestmapaest = codprestmapaest;
    }

    public Date getFechaprestamo() {
        return fechaprestamo;
    }

    public void setFechaprestamo(Date fechaprestamo) {
        this.fechaprestamo = fechaprestamo;
    }

    public Date getFechadevolucion() {
        return fechadevolucion;
    }

    public void setFechadevolucion(Date fechadevolucion) {
        this.fechadevolucion = fechadevolucion;
    }

    public String getDevuelto() {
        return devuelto;
    }

    public void setDevuelto(String devuelto) {
        this.devuelto = devuelto;
    }

    public Bibliotecario getIdbibliotecario() {
        return idbibliotecario;
    }

    public void setIdbibliotecario(Bibliotecario idbibliotecario) {
        this.idbibliotecario = idbibliotecario;
    }

    public Estudiante getCodestudiante() {
        return codestudiante;
    }

    public void setCodestudiante(Estudiante codestudiante) {
        this.codestudiante = codestudiante;
    }

    public Mapa getCodbarramapa() {
        return codbarramapa;
    }

    public void setCodbarramapa(Mapa codbarramapa) {
        this.codbarramapa = codbarramapa;
    }

    @XmlTransient
    public Collection<MultaMapaEstudiante> getMultaMapaEstudianteCollection() {
        return multaMapaEstudianteCollection;
    }

    public void setMultaMapaEstudianteCollection(Collection<MultaMapaEstudiante> multaMapaEstudianteCollection) {
        this.multaMapaEstudianteCollection = multaMapaEstudianteCollection;
    }

    @XmlTransient
    public Collection<DevolucionMapaEstudiante> getDevolucionMapaEstudianteCollection() {
        return devolucionMapaEstudianteCollection;
    }

    public void setDevolucionMapaEstudianteCollection(Collection<DevolucionMapaEstudiante> devolucionMapaEstudianteCollection) {
        this.devolucionMapaEstudianteCollection = devolucionMapaEstudianteCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codprestmapaest != null ? codprestmapaest.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PrestamoMapaEstudiante)) {
            return false;
        }
        PrestamoMapaEstudiante other = (PrestamoMapaEstudiante) object;
        if ((this.codprestmapaest == null && other.codprestmapaest != null) || (this.codprestmapaest != null && !this.codprestmapaest.equals(other.codprestmapaest))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entitys.PrestamoMapaEstudiante[ codprestmapaest=" + codprestmapaest + " ]";
    }
    
}

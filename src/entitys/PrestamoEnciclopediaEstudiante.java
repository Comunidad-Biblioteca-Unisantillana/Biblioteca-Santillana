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
@Table(name = "prestamo_enciclopedia_estudiante")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PrestamoEnciclopediaEstudiante.findAll", query = "SELECT p FROM PrestamoEnciclopediaEstudiante p")
    , @NamedQuery(name = "PrestamoEnciclopediaEstudiante.findByCodprestencest", query = "SELECT p FROM PrestamoEnciclopediaEstudiante p WHERE p.codprestencest = :codprestencest")
    , @NamedQuery(name = "PrestamoEnciclopediaEstudiante.findByFechaprestamo", query = "SELECT p FROM PrestamoEnciclopediaEstudiante p WHERE p.fechaprestamo = :fechaprestamo")
    , @NamedQuery(name = "PrestamoEnciclopediaEstudiante.findByFechadevolucion", query = "SELECT p FROM PrestamoEnciclopediaEstudiante p WHERE p.fechadevolucion = :fechadevolucion")
    , @NamedQuery(name = "PrestamoEnciclopediaEstudiante.findByDevuelto", query = "SELECT p FROM PrestamoEnciclopediaEstudiante p WHERE p.devuelto = :devuelto")})
public class PrestamoEnciclopediaEstudiante implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codprestencest")
    private Integer codprestencest;
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
    private Bibliotecario_1 idbibliotecario;
    @JoinColumn(name = "codbarraenciclopedia", referencedColumnName = "codbarraenciclopedia")
    @ManyToOne(optional = false)
    private Enciclopedia codbarraenciclopedia;
    @JoinColumn(name = "codestudiante", referencedColumnName = "codestudiante")
    @ManyToOne(optional = false)
    private Estudiante_1 codestudiante;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codprestencest")
    private Collection<MultaEnciclopediaEstudiante> multaEnciclopediaEstudianteCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codprestencest")
    private Collection<DevolucionEnciclopediaEstudiante> devolucionEnciclopediaEstudianteCollection;

    public PrestamoEnciclopediaEstudiante() {
    }

    public PrestamoEnciclopediaEstudiante(Integer codprestencest) {
        this.codprestencest = codprestencest;
    }

    public PrestamoEnciclopediaEstudiante(Integer codprestencest, Date fechaprestamo, Date fechadevolucion, String devuelto) {
        this.codprestencest = codprestencest;
        this.fechaprestamo = fechaprestamo;
        this.fechadevolucion = fechadevolucion;
        this.devuelto = devuelto;
    }

    public Integer getCodprestencest() {
        return codprestencest;
    }

    public void setCodprestencest(Integer codprestencest) {
        this.codprestencest = codprestencest;
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

    public Bibliotecario_1 getIdbibliotecario() {
        return idbibliotecario;
    }

    public void setIdbibliotecario(Bibliotecario_1 idbibliotecario) {
        this.idbibliotecario = idbibliotecario;
    }

    public Enciclopedia getCodbarraenciclopedia() {
        return codbarraenciclopedia;
    }

    public void setCodbarraenciclopedia(Enciclopedia codbarraenciclopedia) {
        this.codbarraenciclopedia = codbarraenciclopedia;
    }

    public Estudiante_1 getCodestudiante() {
        return codestudiante;
    }

    public void setCodestudiante(Estudiante_1 codestudiante) {
        this.codestudiante = codestudiante;
    }

    @XmlTransient
    public Collection<MultaEnciclopediaEstudiante> getMultaEnciclopediaEstudianteCollection() {
        return multaEnciclopediaEstudianteCollection;
    }

    public void setMultaEnciclopediaEstudianteCollection(Collection<MultaEnciclopediaEstudiante> multaEnciclopediaEstudianteCollection) {
        this.multaEnciclopediaEstudianteCollection = multaEnciclopediaEstudianteCollection;
    }

    @XmlTransient
    public Collection<DevolucionEnciclopediaEstudiante> getDevolucionEnciclopediaEstudianteCollection() {
        return devolucionEnciclopediaEstudianteCollection;
    }

    public void setDevolucionEnciclopediaEstudianteCollection(Collection<DevolucionEnciclopediaEstudiante> devolucionEnciclopediaEstudianteCollection) {
        this.devolucionEnciclopediaEstudianteCollection = devolucionEnciclopediaEstudianteCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codprestencest != null ? codprestencest.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PrestamoEnciclopediaEstudiante)) {
            return false;
        }
        PrestamoEnciclopediaEstudiante other = (PrestamoEnciclopediaEstudiante) object;
        if ((this.codprestencest == null && other.codprestencest != null) || (this.codprestencest != null && !this.codprestencest.equals(other.codprestencest))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entitysRecursos.PrestamoEnciclopediaEstudiante[ codprestencest=" + codprestencest + " ]";
    }
    
}

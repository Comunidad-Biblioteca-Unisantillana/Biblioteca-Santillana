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
@Table(name = "prestamo_revista_estudiante")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PrestamoRevistaEstudiante.findAll", query = "SELECT p FROM PrestamoRevistaEstudiante p")
    , @NamedQuery(name = "PrestamoRevistaEstudiante.findByCodprestrevistaest", query = "SELECT p FROM PrestamoRevistaEstudiante p WHERE p.codprestrevistaest = :codprestrevistaest")
    , @NamedQuery(name = "PrestamoRevistaEstudiante.findByFechaprestamo", query = "SELECT p FROM PrestamoRevistaEstudiante p WHERE p.fechaprestamo = :fechaprestamo")
    , @NamedQuery(name = "PrestamoRevistaEstudiante.findByFechadevolucion", query = "SELECT p FROM PrestamoRevistaEstudiante p WHERE p.fechadevolucion = :fechadevolucion")
    , @NamedQuery(name = "PrestamoRevistaEstudiante.findByDevuelto", query = "SELECT p FROM PrestamoRevistaEstudiante p WHERE p.devuelto = :devuelto")})
public class PrestamoRevistaEstudiante implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codprestrevistaest")
    private Integer codprestrevistaest;
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
    @JoinColumn(name = "codbarrarevista", referencedColumnName = "codbarrarevista")
    @ManyToOne(optional = false)
    private Revista codbarrarevista;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codprestrevistaest")
    private Collection<DevolucionRevistaEstudiante> devolucionRevistaEstudianteCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codprestrevistaest")
    private Collection<MultaRevistaEstudiante> multaRevistaEstudianteCollection;

    public PrestamoRevistaEstudiante() {
    }

    public PrestamoRevistaEstudiante(Integer codprestrevistaest) {
        this.codprestrevistaest = codprestrevistaest;
    }

    public PrestamoRevistaEstudiante(Integer codprestrevistaest, Date fechaprestamo, Date fechadevolucion, String devuelto) {
        this.codprestrevistaest = codprestrevistaest;
        this.fechaprestamo = fechaprestamo;
        this.fechadevolucion = fechadevolucion;
        this.devuelto = devuelto;
    }

    public Integer getCodprestrevistaest() {
        return codprestrevistaest;
    }

    public void setCodprestrevistaest(Integer codprestrevistaest) {
        this.codprestrevistaest = codprestrevistaest;
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

    public Revista getCodbarrarevista() {
        return codbarrarevista;
    }

    public void setCodbarrarevista(Revista codbarrarevista) {
        this.codbarrarevista = codbarrarevista;
    }

    @XmlTransient
    public Collection<DevolucionRevistaEstudiante> getDevolucionRevistaEstudianteCollection() {
        return devolucionRevistaEstudianteCollection;
    }

    public void setDevolucionRevistaEstudianteCollection(Collection<DevolucionRevistaEstudiante> devolucionRevistaEstudianteCollection) {
        this.devolucionRevistaEstudianteCollection = devolucionRevistaEstudianteCollection;
    }

    @XmlTransient
    public Collection<MultaRevistaEstudiante> getMultaRevistaEstudianteCollection() {
        return multaRevistaEstudianteCollection;
    }

    public void setMultaRevistaEstudianteCollection(Collection<MultaRevistaEstudiante> multaRevistaEstudianteCollection) {
        this.multaRevistaEstudianteCollection = multaRevistaEstudianteCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codprestrevistaest != null ? codprestrevistaest.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PrestamoRevistaEstudiante)) {
            return false;
        }
        PrestamoRevistaEstudiante other = (PrestamoRevistaEstudiante) object;
        if ((this.codprestrevistaest == null && other.codprestrevistaest != null) || (this.codprestrevistaest != null && !this.codprestrevistaest.equals(other.codprestrevistaest))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entitys.PrestamoRevistaEstudiante[ codprestrevistaest=" + codprestrevistaest + " ]";
    }
    
}

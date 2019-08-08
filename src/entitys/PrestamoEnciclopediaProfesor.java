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
@Table(name = "prestamo_enciclopedia_profesor")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PrestamoEnciclopediaProfesor.findAll", query = "SELECT p FROM PrestamoEnciclopediaProfesor p")
    , @NamedQuery(name = "PrestamoEnciclopediaProfesor.findByCodprestencprof", query = "SELECT p FROM PrestamoEnciclopediaProfesor p WHERE p.codprestencprof = :codprestencprof")
    , @NamedQuery(name = "PrestamoEnciclopediaProfesor.findByFechaprestamo", query = "SELECT p FROM PrestamoEnciclopediaProfesor p WHERE p.fechaprestamo = :fechaprestamo")
    , @NamedQuery(name = "PrestamoEnciclopediaProfesor.findByFechadevolucion", query = "SELECT p FROM PrestamoEnciclopediaProfesor p WHERE p.fechadevolucion = :fechadevolucion")
    , @NamedQuery(name = "PrestamoEnciclopediaProfesor.findByDevuelto", query = "SELECT p FROM PrestamoEnciclopediaProfesor p WHERE p.devuelto = :devuelto")})
public class PrestamoEnciclopediaProfesor implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codprestencprof")
    private Integer codprestencprof;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codprestencprof")
    private Collection<DevolucionEnciclopediaProfesor> devolucionEnciclopediaProfesorCollection;
    @JoinColumn(name = "idbibliotecario", referencedColumnName = "idbibliotecario")
    @ManyToOne(optional = false)
    private Bibliotecario_1 idbibliotecario;
    @JoinColumn(name = "codbarraenciclopedia", referencedColumnName = "codbarraenciclopedia")
    @ManyToOne(optional = false)
    private Enciclopedia codbarraenciclopedia;
    @JoinColumn(name = "idprofesor", referencedColumnName = "idprofesor")
    @ManyToOne(optional = false)
    private Profesor idprofesor;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codprestencprof")
    private Collection<MultaEnciclopediaProfesor> multaEnciclopediaProfesorCollection;

    public PrestamoEnciclopediaProfesor() {
    }

    public PrestamoEnciclopediaProfesor(Integer codprestencprof) {
        this.codprestencprof = codprestencprof;
    }

    public PrestamoEnciclopediaProfesor(Integer codprestencprof, Date fechaprestamo, Date fechadevolucion, String devuelto) {
        this.codprestencprof = codprestencprof;
        this.fechaprestamo = fechaprestamo;
        this.fechadevolucion = fechadevolucion;
        this.devuelto = devuelto;
    }

    public Integer getCodprestencprof() {
        return codprestencprof;
    }

    public void setCodprestencprof(Integer codprestencprof) {
        this.codprestencprof = codprestencprof;
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

    @XmlTransient
    public Collection<DevolucionEnciclopediaProfesor> getDevolucionEnciclopediaProfesorCollection() {
        return devolucionEnciclopediaProfesorCollection;
    }

    public void setDevolucionEnciclopediaProfesorCollection(Collection<DevolucionEnciclopediaProfesor> devolucionEnciclopediaProfesorCollection) {
        this.devolucionEnciclopediaProfesorCollection = devolucionEnciclopediaProfesorCollection;
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

    public Profesor getIdprofesor() {
        return idprofesor;
    }

    public void setIdprofesor(Profesor idprofesor) {
        this.idprofesor = idprofesor;
    }

    @XmlTransient
    public Collection<MultaEnciclopediaProfesor> getMultaEnciclopediaProfesorCollection() {
        return multaEnciclopediaProfesorCollection;
    }

    public void setMultaEnciclopediaProfesorCollection(Collection<MultaEnciclopediaProfesor> multaEnciclopediaProfesorCollection) {
        this.multaEnciclopediaProfesorCollection = multaEnciclopediaProfesorCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codprestencprof != null ? codprestencprof.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PrestamoEnciclopediaProfesor)) {
            return false;
        }
        PrestamoEnciclopediaProfesor other = (PrestamoEnciclopediaProfesor) object;
        if ((this.codprestencprof == null && other.codprestencprof != null) || (this.codprestencprof != null && !this.codprestencprof.equals(other.codprestencprof))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entitysRecursos.PrestamoEnciclopediaProfesor[ codprestencprof=" + codprestencprof + " ]";
    }
    
}

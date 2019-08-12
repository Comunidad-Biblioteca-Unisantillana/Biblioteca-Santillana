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
@Table(name = "prestamo_revista_profesor")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PrestamoRevistaProfesor.findAll", query = "SELECT p FROM PrestamoRevistaProfesor p")
    , @NamedQuery(name = "PrestamoRevistaProfesor.findByCodprestrevistaprof", query = "SELECT p FROM PrestamoRevistaProfesor p WHERE p.codprestrevistaprof = :codprestrevistaprof")
    , @NamedQuery(name = "PrestamoRevistaProfesor.findByFechaprestamo", query = "SELECT p FROM PrestamoRevistaProfesor p WHERE p.fechaprestamo = :fechaprestamo")
    , @NamedQuery(name = "PrestamoRevistaProfesor.findByFechadevolucion", query = "SELECT p FROM PrestamoRevistaProfesor p WHERE p.fechadevolucion = :fechadevolucion")
    , @NamedQuery(name = "PrestamoRevistaProfesor.findByDevuelto", query = "SELECT p FROM PrestamoRevistaProfesor p WHERE p.devuelto = :devuelto")})
public class PrestamoRevistaProfesor implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codprestrevistaprof")
    private Integer codprestrevistaprof;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codprestrevistaprof")
    private Collection<DevolucionRevistaProfesor> devolucionRevistaProfesorCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codprestrevistaprof")
    private Collection<MultaRevistaProfesor> multaRevistaProfesorCollection;
    @JoinColumn(name = "idbibliotecario", referencedColumnName = "idbibliotecario")
    @ManyToOne(optional = false)
    private Bibliotecario idbibliotecario;
    @JoinColumn(name = "idprofesor", referencedColumnName = "idprofesor")
    @ManyToOne(optional = false)
    private Profesor idprofesor;
    @JoinColumn(name = "codbarrarevista", referencedColumnName = "codbarrarevista")
    @ManyToOne(optional = false)
    private Revista codbarrarevista;

    public PrestamoRevistaProfesor() {
    }

    public PrestamoRevistaProfesor(Integer codprestrevistaprof) {
        this.codprestrevistaprof = codprestrevistaprof;
    }

    public PrestamoRevistaProfesor(Integer codprestrevistaprof, Date fechaprestamo, Date fechadevolucion, String devuelto) {
        this.codprestrevistaprof = codprestrevistaprof;
        this.fechaprestamo = fechaprestamo;
        this.fechadevolucion = fechadevolucion;
        this.devuelto = devuelto;
    }

    public Integer getCodprestrevistaprof() {
        return codprestrevistaprof;
    }

    public void setCodprestrevistaprof(Integer codprestrevistaprof) {
        this.codprestrevistaprof = codprestrevistaprof;
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
    public Collection<DevolucionRevistaProfesor> getDevolucionRevistaProfesorCollection() {
        return devolucionRevistaProfesorCollection;
    }

    public void setDevolucionRevistaProfesorCollection(Collection<DevolucionRevistaProfesor> devolucionRevistaProfesorCollection) {
        this.devolucionRevistaProfesorCollection = devolucionRevistaProfesorCollection;
    }

    @XmlTransient
    public Collection<MultaRevistaProfesor> getMultaRevistaProfesorCollection() {
        return multaRevistaProfesorCollection;
    }

    public void setMultaRevistaProfesorCollection(Collection<MultaRevistaProfesor> multaRevistaProfesorCollection) {
        this.multaRevistaProfesorCollection = multaRevistaProfesorCollection;
    }

    public Bibliotecario getIdbibliotecario() {
        return idbibliotecario;
    }

    public void setIdbibliotecario(Bibliotecario idbibliotecario) {
        this.idbibliotecario = idbibliotecario;
    }

    public Profesor getIdprofesor() {
        return idprofesor;
    }

    public void setIdprofesor(Profesor idprofesor) {
        this.idprofesor = idprofesor;
    }

    public Revista getCodbarrarevista() {
        return codbarrarevista;
    }

    public void setCodbarrarevista(Revista codbarrarevista) {
        this.codbarrarevista = codbarrarevista;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codprestrevistaprof != null ? codprestrevistaprof.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PrestamoRevistaProfesor)) {
            return false;
        }
        PrestamoRevistaProfesor other = (PrestamoRevistaProfesor) object;
        if ((this.codprestrevistaprof == null && other.codprestrevistaprof != null) || (this.codprestrevistaprof != null && !this.codprestrevistaprof.equals(other.codprestrevistaprof))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entitys.PrestamoRevistaProfesor[ codprestrevistaprof=" + codprestrevistaprof + " ]";
    }
    
}

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
@Table(name = "prestamo_mapa_profesor")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PrestamoMapaProfesor.findAll", query = "SELECT p FROM PrestamoMapaProfesor p")
    , @NamedQuery(name = "PrestamoMapaProfesor.findByCodprestmapaprof", query = "SELECT p FROM PrestamoMapaProfesor p WHERE p.codprestmapaprof = :codprestmapaprof")
    , @NamedQuery(name = "PrestamoMapaProfesor.findByFechaprestamo", query = "SELECT p FROM PrestamoMapaProfesor p WHERE p.fechaprestamo = :fechaprestamo")
    , @NamedQuery(name = "PrestamoMapaProfesor.findByFechadevolucion", query = "SELECT p FROM PrestamoMapaProfesor p WHERE p.fechadevolucion = :fechadevolucion")
    , @NamedQuery(name = "PrestamoMapaProfesor.findByDevuelto", query = "SELECT p FROM PrestamoMapaProfesor p WHERE p.devuelto = :devuelto")})
public class PrestamoMapaProfesor implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codprestmapaprof")
    private Integer codprestmapaprof;
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
    @JoinColumn(name = "codbarramapa", referencedColumnName = "codbarramapa")
    @ManyToOne(optional = false)
    private Mapa codbarramapa;
    @JoinColumn(name = "idprofesor", referencedColumnName = "idprofesor")
    @ManyToOne(optional = false)
    private Profesor idprofesor;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codprestmapaprof")
    private Collection<MultaMapaProfesor> multaMapaProfesorCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codprestmapaprof")
    private Collection<DevolucionMapaProfesor> devolucionMapaProfesorCollection;

    public PrestamoMapaProfesor() {
    }

    public PrestamoMapaProfesor(Integer codprestmapaprof) {
        this.codprestmapaprof = codprestmapaprof;
    }

    public PrestamoMapaProfesor(Integer codprestmapaprof, Date fechaprestamo, Date fechadevolucion, String devuelto) {
        this.codprestmapaprof = codprestmapaprof;
        this.fechaprestamo = fechaprestamo;
        this.fechadevolucion = fechadevolucion;
        this.devuelto = devuelto;
    }

    public Integer getCodprestmapaprof() {
        return codprestmapaprof;
    }

    public void setCodprestmapaprof(Integer codprestmapaprof) {
        this.codprestmapaprof = codprestmapaprof;
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

    public Mapa getCodbarramapa() {
        return codbarramapa;
    }

    public void setCodbarramapa(Mapa codbarramapa) {
        this.codbarramapa = codbarramapa;
    }

    public Profesor getIdprofesor() {
        return idprofesor;
    }

    public void setIdprofesor(Profesor idprofesor) {
        this.idprofesor = idprofesor;
    }

    @XmlTransient
    public Collection<MultaMapaProfesor> getMultaMapaProfesorCollection() {
        return multaMapaProfesorCollection;
    }

    public void setMultaMapaProfesorCollection(Collection<MultaMapaProfesor> multaMapaProfesorCollection) {
        this.multaMapaProfesorCollection = multaMapaProfesorCollection;
    }

    @XmlTransient
    public Collection<DevolucionMapaProfesor> getDevolucionMapaProfesorCollection() {
        return devolucionMapaProfesorCollection;
    }

    public void setDevolucionMapaProfesorCollection(Collection<DevolucionMapaProfesor> devolucionMapaProfesorCollection) {
        this.devolucionMapaProfesorCollection = devolucionMapaProfesorCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codprestmapaprof != null ? codprestmapaprof.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PrestamoMapaProfesor)) {
            return false;
        }
        PrestamoMapaProfesor other = (PrestamoMapaProfesor) object;
        if ((this.codprestmapaprof == null && other.codprestmapaprof != null) || (this.codprestmapaprof != null && !this.codprestmapaprof.equals(other.codprestmapaprof))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entitys.PrestamoMapaProfesor[ codprestmapaprof=" + codprestmapaprof + " ]";
    }
    
}

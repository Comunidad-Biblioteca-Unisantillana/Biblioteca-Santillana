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
@Table(name = "prestamo_periodico_profesor")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PrestamoPeriodicoProfesor.findAll", query = "SELECT p FROM PrestamoPeriodicoProfesor p")
    , @NamedQuery(name = "PrestamoPeriodicoProfesor.findByCodprestperiodicoprof", query = "SELECT p FROM PrestamoPeriodicoProfesor p WHERE p.codprestperiodicoprof = :codprestperiodicoprof")
    , @NamedQuery(name = "PrestamoPeriodicoProfesor.findByFechaprestamo", query = "SELECT p FROM PrestamoPeriodicoProfesor p WHERE p.fechaprestamo = :fechaprestamo")
    , @NamedQuery(name = "PrestamoPeriodicoProfesor.findByFechadevolucion", query = "SELECT p FROM PrestamoPeriodicoProfesor p WHERE p.fechadevolucion = :fechadevolucion")
    , @NamedQuery(name = "PrestamoPeriodicoProfesor.findByDevuelto", query = "SELECT p FROM PrestamoPeriodicoProfesor p WHERE p.devuelto = :devuelto")})
public class PrestamoPeriodicoProfesor implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codprestperiodicoprof")
    private Integer codprestperiodicoprof;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codprestperiodicoprof")
    private Collection<DevolucionPeriodicoProfesor> devolucionPeriodicoProfesorCollection;
    @JoinColumn(name = "idbibliotecario", referencedColumnName = "idbibliotecario")
    @ManyToOne(optional = false)
    private Bibliotecario idbibliotecario;
    @JoinColumn(name = "codbarraperiodico", referencedColumnName = "codbarraperiodico")
    @ManyToOne(optional = false)
    private Periodico codbarraperiodico;
    @JoinColumn(name = "idprofesor", referencedColumnName = "idprofesor")
    @ManyToOne(optional = false)
    private Profesor idprofesor;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codprestperiodicoprof")
    private Collection<MultaPeriodicoProfesor> multaPeriodicoProfesorCollection;

    public PrestamoPeriodicoProfesor() {
    }

    public PrestamoPeriodicoProfesor(Integer codprestperiodicoprof) {
        this.codprestperiodicoprof = codprestperiodicoprof;
    }

    public PrestamoPeriodicoProfesor(Integer codprestperiodicoprof, Date fechaprestamo, Date fechadevolucion, String devuelto) {
        this.codprestperiodicoprof = codprestperiodicoprof;
        this.fechaprestamo = fechaprestamo;
        this.fechadevolucion = fechadevolucion;
        this.devuelto = devuelto;
    }

    public Integer getCodprestperiodicoprof() {
        return codprestperiodicoprof;
    }

    public void setCodprestperiodicoprof(Integer codprestperiodicoprof) {
        this.codprestperiodicoprof = codprestperiodicoprof;
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
    public Collection<DevolucionPeriodicoProfesor> getDevolucionPeriodicoProfesorCollection() {
        return devolucionPeriodicoProfesorCollection;
    }

    public void setDevolucionPeriodicoProfesorCollection(Collection<DevolucionPeriodicoProfesor> devolucionPeriodicoProfesorCollection) {
        this.devolucionPeriodicoProfesorCollection = devolucionPeriodicoProfesorCollection;
    }

    public Bibliotecario getIdbibliotecario() {
        return idbibliotecario;
    }

    public void setIdbibliotecario(Bibliotecario idbibliotecario) {
        this.idbibliotecario = idbibliotecario;
    }

    public Periodico getCodbarraperiodico() {
        return codbarraperiodico;
    }

    public void setCodbarraperiodico(Periodico codbarraperiodico) {
        this.codbarraperiodico = codbarraperiodico;
    }

    public Profesor getIdprofesor() {
        return idprofesor;
    }

    public void setIdprofesor(Profesor idprofesor) {
        this.idprofesor = idprofesor;
    }

    @XmlTransient
    public Collection<MultaPeriodicoProfesor> getMultaPeriodicoProfesorCollection() {
        return multaPeriodicoProfesorCollection;
    }

    public void setMultaPeriodicoProfesorCollection(Collection<MultaPeriodicoProfesor> multaPeriodicoProfesorCollection) {
        this.multaPeriodicoProfesorCollection = multaPeriodicoProfesorCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codprestperiodicoprof != null ? codprestperiodicoprof.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PrestamoPeriodicoProfesor)) {
            return false;
        }
        PrestamoPeriodicoProfesor other = (PrestamoPeriodicoProfesor) object;
        if ((this.codprestperiodicoprof == null && other.codprestperiodicoprof != null) || (this.codprestperiodicoprof != null && !this.codprestperiodicoprof.equals(other.codprestperiodicoprof))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entitys.PrestamoPeriodicoProfesor[ codprestperiodicoprof=" + codprestperiodicoprof + " ]";
    }
    
}

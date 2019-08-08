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
@Table(name = "prestamo_periodico_estudiante")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PrestamoPeriodicoEstudiante.findAll", query = "SELECT p FROM PrestamoPeriodicoEstudiante p")
    , @NamedQuery(name = "PrestamoPeriodicoEstudiante.findByCodprestperiodicoest", query = "SELECT p FROM PrestamoPeriodicoEstudiante p WHERE p.codprestperiodicoest = :codprestperiodicoest")
    , @NamedQuery(name = "PrestamoPeriodicoEstudiante.findByFechaprestamo", query = "SELECT p FROM PrestamoPeriodicoEstudiante p WHERE p.fechaprestamo = :fechaprestamo")
    , @NamedQuery(name = "PrestamoPeriodicoEstudiante.findByFechadevolucion", query = "SELECT p FROM PrestamoPeriodicoEstudiante p WHERE p.fechadevolucion = :fechadevolucion")
    , @NamedQuery(name = "PrestamoPeriodicoEstudiante.findByDevuelto", query = "SELECT p FROM PrestamoPeriodicoEstudiante p WHERE p.devuelto = :devuelto")})
public class PrestamoPeriodicoEstudiante implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codprestperiodicoest")
    private Integer codprestperiodicoest;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codprestperiodicoest")
    private Collection<DevolucionPeriodicoEstudiante> devolucionPeriodicoEstudianteCollection;
    @JoinColumn(name = "idbibliotecario", referencedColumnName = "idbibliotecario")
    @ManyToOne(optional = false)
    private Bibliotecario_1 idbibliotecario;
    @JoinColumn(name = "codestudiante", referencedColumnName = "codestudiante")
    @ManyToOne(optional = false)
    private Estudiante_1 codestudiante;
    @JoinColumn(name = "codbarraperiodico", referencedColumnName = "codbarraperiodico")
    @ManyToOne(optional = false)
    private Periodico codbarraperiodico;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codprestperiodicoest")
    private Collection<MultaPeriodicoEstudiante> multaPeriodicoEstudianteCollection;

    public PrestamoPeriodicoEstudiante() {
    }

    public PrestamoPeriodicoEstudiante(Integer codprestperiodicoest) {
        this.codprestperiodicoest = codprestperiodicoest;
    }

    public PrestamoPeriodicoEstudiante(Integer codprestperiodicoest, Date fechaprestamo, Date fechadevolucion, String devuelto) {
        this.codprestperiodicoest = codprestperiodicoest;
        this.fechaprestamo = fechaprestamo;
        this.fechadevolucion = fechadevolucion;
        this.devuelto = devuelto;
    }

    public Integer getCodprestperiodicoest() {
        return codprestperiodicoest;
    }

    public void setCodprestperiodicoest(Integer codprestperiodicoest) {
        this.codprestperiodicoest = codprestperiodicoest;
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
    public Collection<DevolucionPeriodicoEstudiante> getDevolucionPeriodicoEstudianteCollection() {
        return devolucionPeriodicoEstudianteCollection;
    }

    public void setDevolucionPeriodicoEstudianteCollection(Collection<DevolucionPeriodicoEstudiante> devolucionPeriodicoEstudianteCollection) {
        this.devolucionPeriodicoEstudianteCollection = devolucionPeriodicoEstudianteCollection;
    }

    public Bibliotecario_1 getIdbibliotecario() {
        return idbibliotecario;
    }

    public void setIdbibliotecario(Bibliotecario_1 idbibliotecario) {
        this.idbibliotecario = idbibliotecario;
    }

    public Estudiante_1 getCodestudiante() {
        return codestudiante;
    }

    public void setCodestudiante(Estudiante_1 codestudiante) {
        this.codestudiante = codestudiante;
    }

    public Periodico getCodbarraperiodico() {
        return codbarraperiodico;
    }

    public void setCodbarraperiodico(Periodico codbarraperiodico) {
        this.codbarraperiodico = codbarraperiodico;
    }

    @XmlTransient
    public Collection<MultaPeriodicoEstudiante> getMultaPeriodicoEstudianteCollection() {
        return multaPeriodicoEstudianteCollection;
    }

    public void setMultaPeriodicoEstudianteCollection(Collection<MultaPeriodicoEstudiante> multaPeriodicoEstudianteCollection) {
        this.multaPeriodicoEstudianteCollection = multaPeriodicoEstudianteCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codprestperiodicoest != null ? codprestperiodicoest.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PrestamoPeriodicoEstudiante)) {
            return false;
        }
        PrestamoPeriodicoEstudiante other = (PrestamoPeriodicoEstudiante) object;
        if ((this.codprestperiodicoest == null && other.codprestperiodicoest != null) || (this.codprestperiodicoest != null && !this.codprestperiodicoest.equals(other.codprestperiodicoest))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entitysRecursos.PrestamoPeriodicoEstudiante[ codprestperiodicoest=" + codprestperiodicoest + " ]";
    }
    
}

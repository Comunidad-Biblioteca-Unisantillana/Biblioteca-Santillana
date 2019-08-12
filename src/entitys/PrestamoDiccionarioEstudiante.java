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
@Table(name = "prestamo_diccionario_estudiante")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PrestamoDiccionarioEstudiante.findAll", query = "SELECT p FROM PrestamoDiccionarioEstudiante p")
    , @NamedQuery(name = "PrestamoDiccionarioEstudiante.findByCodprestdicest", query = "SELECT p FROM PrestamoDiccionarioEstudiante p WHERE p.codprestdicest = :codprestdicest")
    , @NamedQuery(name = "PrestamoDiccionarioEstudiante.findByFechaprestamo", query = "SELECT p FROM PrestamoDiccionarioEstudiante p WHERE p.fechaprestamo = :fechaprestamo")
    , @NamedQuery(name = "PrestamoDiccionarioEstudiante.findByFechadevolucion", query = "SELECT p FROM PrestamoDiccionarioEstudiante p WHERE p.fechadevolucion = :fechadevolucion")
    , @NamedQuery(name = "PrestamoDiccionarioEstudiante.findByDevuelto", query = "SELECT p FROM PrestamoDiccionarioEstudiante p WHERE p.devuelto = :devuelto")})
public class PrestamoDiccionarioEstudiante implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codprestdicest")
    private Integer codprestdicest;
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
    @JoinColumn(name = "codbarradiccionario", referencedColumnName = "codbarradiccionario")
    @ManyToOne(optional = false)
    private Diccionario codbarradiccionario;
    @JoinColumn(name = "codestudiante", referencedColumnName = "codestudiante")
    @ManyToOne(optional = false)
    private Estudiante codestudiante;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codprestdicest")
    private Collection<DevolucionDiccionarioEstudiante> devolucionDiccionarioEstudianteCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codprestdicest")
    private Collection<MultaDiccionarioEstudiante> multaDiccionarioEstudianteCollection;

    public PrestamoDiccionarioEstudiante() {
    }

    public PrestamoDiccionarioEstudiante(Integer codprestdicest) {
        this.codprestdicest = codprestdicest;
    }

    public PrestamoDiccionarioEstudiante(Integer codprestdicest, Date fechaprestamo, Date fechadevolucion, String devuelto) {
        this.codprestdicest = codprestdicest;
        this.fechaprestamo = fechaprestamo;
        this.fechadevolucion = fechadevolucion;
        this.devuelto = devuelto;
    }

    public Integer getCodprestdicest() {
        return codprestdicest;
    }

    public void setCodprestdicest(Integer codprestdicest) {
        this.codprestdicest = codprestdicest;
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

    public Diccionario getCodbarradiccionario() {
        return codbarradiccionario;
    }

    public void setCodbarradiccionario(Diccionario codbarradiccionario) {
        this.codbarradiccionario = codbarradiccionario;
    }

    public Estudiante getCodestudiante() {
        return codestudiante;
    }

    public void setCodestudiante(Estudiante codestudiante) {
        this.codestudiante = codestudiante;
    }

    @XmlTransient
    public Collection<DevolucionDiccionarioEstudiante> getDevolucionDiccionarioEstudianteCollection() {
        return devolucionDiccionarioEstudianteCollection;
    }

    public void setDevolucionDiccionarioEstudianteCollection(Collection<DevolucionDiccionarioEstudiante> devolucionDiccionarioEstudianteCollection) {
        this.devolucionDiccionarioEstudianteCollection = devolucionDiccionarioEstudianteCollection;
    }

    @XmlTransient
    public Collection<MultaDiccionarioEstudiante> getMultaDiccionarioEstudianteCollection() {
        return multaDiccionarioEstudianteCollection;
    }

    public void setMultaDiccionarioEstudianteCollection(Collection<MultaDiccionarioEstudiante> multaDiccionarioEstudianteCollection) {
        this.multaDiccionarioEstudianteCollection = multaDiccionarioEstudianteCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codprestdicest != null ? codprestdicest.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PrestamoDiccionarioEstudiante)) {
            return false;
        }
        PrestamoDiccionarioEstudiante other = (PrestamoDiccionarioEstudiante) object;
        if ((this.codprestdicest == null && other.codprestdicest != null) || (this.codprestdicest != null && !this.codprestdicest.equals(other.codprestdicest))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entitys.PrestamoDiccionarioEstudiante[ codprestdicest=" + codprestdicest + " ]";
    }
    
}

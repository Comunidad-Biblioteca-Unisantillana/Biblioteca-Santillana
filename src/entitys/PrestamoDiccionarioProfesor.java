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
@Table(name = "prestamo_diccionario_profesor")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PrestamoDiccionarioProfesor.findAll", query = "SELECT p FROM PrestamoDiccionarioProfesor p")
    , @NamedQuery(name = "PrestamoDiccionarioProfesor.findByCodprestdicprof", query = "SELECT p FROM PrestamoDiccionarioProfesor p WHERE p.codprestdicprof = :codprestdicprof")
    , @NamedQuery(name = "PrestamoDiccionarioProfesor.findByFechaprestamo", query = "SELECT p FROM PrestamoDiccionarioProfesor p WHERE p.fechaprestamo = :fechaprestamo")
    , @NamedQuery(name = "PrestamoDiccionarioProfesor.findByFechadevolucion", query = "SELECT p FROM PrestamoDiccionarioProfesor p WHERE p.fechadevolucion = :fechadevolucion")
    , @NamedQuery(name = "PrestamoDiccionarioProfesor.findByDevuelto", query = "SELECT p FROM PrestamoDiccionarioProfesor p WHERE p.devuelto = :devuelto")})
public class PrestamoDiccionarioProfesor implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codprestdicprof")
    private Integer codprestdicprof;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codprestdicprof")
    private Collection<MultaDiccionarioProfesor> multaDiccionarioProfesorCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codprestdicprof")
    private Collection<DevolucionDiccionarioProfesor> devolucionDiccionarioProfesorCollection;
    @JoinColumn(name = "idbibliotecario", referencedColumnName = "idbibliotecario")
    @ManyToOne(optional = false)
    private Bibliotecario idbibliotecario;
    @JoinColumn(name = "codbarradiccionario", referencedColumnName = "codbarradiccionario")
    @ManyToOne(optional = false)
    private Diccionario codbarradiccionario;
    @JoinColumn(name = "idprofesor", referencedColumnName = "idprofesor")
    @ManyToOne(optional = false)
    private Profesor idprofesor;

    public PrestamoDiccionarioProfesor() {
    }

    public PrestamoDiccionarioProfesor(Integer codprestdicprof) {
        this.codprestdicprof = codprestdicprof;
    }

    public PrestamoDiccionarioProfesor(Integer codprestdicprof, Date fechaprestamo, Date fechadevolucion, String devuelto) {
        this.codprestdicprof = codprestdicprof;
        this.fechaprestamo = fechaprestamo;
        this.fechadevolucion = fechadevolucion;
        this.devuelto = devuelto;
    }

    public Integer getCodprestdicprof() {
        return codprestdicprof;
    }

    public void setCodprestdicprof(Integer codprestdicprof) {
        this.codprestdicprof = codprestdicprof;
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
    public Collection<MultaDiccionarioProfesor> getMultaDiccionarioProfesorCollection() {
        return multaDiccionarioProfesorCollection;
    }

    public void setMultaDiccionarioProfesorCollection(Collection<MultaDiccionarioProfesor> multaDiccionarioProfesorCollection) {
        this.multaDiccionarioProfesorCollection = multaDiccionarioProfesorCollection;
    }

    @XmlTransient
    public Collection<DevolucionDiccionarioProfesor> getDevolucionDiccionarioProfesorCollection() {
        return devolucionDiccionarioProfesorCollection;
    }

    public void setDevolucionDiccionarioProfesorCollection(Collection<DevolucionDiccionarioProfesor> devolucionDiccionarioProfesorCollection) {
        this.devolucionDiccionarioProfesorCollection = devolucionDiccionarioProfesorCollection;
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

    public Profesor getIdprofesor() {
        return idprofesor;
    }

    public void setIdprofesor(Profesor idprofesor) {
        this.idprofesor = idprofesor;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codprestdicprof != null ? codprestdicprof.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PrestamoDiccionarioProfesor)) {
            return false;
        }
        PrestamoDiccionarioProfesor other = (PrestamoDiccionarioProfesor) object;
        if ((this.codprestdicprof == null && other.codprestdicprof != null) || (this.codprestdicprof != null && !this.codprestdicprof.equals(other.codprestdicprof))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entitys.PrestamoDiccionarioProfesor[ codprestdicprof=" + codprestdicprof + " ]";
    }
    
}

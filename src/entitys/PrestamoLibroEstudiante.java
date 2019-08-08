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
@Table(name = "prestamo_libro_estudiante")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PrestamoLibroEstudiante.findAll", query = "SELECT p FROM PrestamoLibroEstudiante p")
    , @NamedQuery(name = "PrestamoLibroEstudiante.findByCodprestlibroest", query = "SELECT p FROM PrestamoLibroEstudiante p WHERE p.codprestlibroest = :codprestlibroest")
    , @NamedQuery(name = "PrestamoLibroEstudiante.findByFechaprestamo", query = "SELECT p FROM PrestamoLibroEstudiante p WHERE p.fechaprestamo = :fechaprestamo")
    , @NamedQuery(name = "PrestamoLibroEstudiante.findByFechadevolucion", query = "SELECT p FROM PrestamoLibroEstudiante p WHERE p.fechadevolucion = :fechadevolucion")
    , @NamedQuery(name = "PrestamoLibroEstudiante.findByNumrenovaciones", query = "SELECT p FROM PrestamoLibroEstudiante p WHERE p.numrenovaciones = :numrenovaciones")
    , @NamedQuery(name = "PrestamoLibroEstudiante.findByDevuelto", query = "SELECT p FROM PrestamoLibroEstudiante p WHERE p.devuelto = :devuelto")})
public class PrestamoLibroEstudiante implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codprestlibroest")
    private Integer codprestlibroest;
    @Basic(optional = false)
    @Column(name = "fechaprestamo")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaprestamo;
    @Basic(optional = false)
    @Column(name = "fechadevolucion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechadevolucion;
    @Basic(optional = false)
    @Column(name = "numrenovaciones")
    private int numrenovaciones;
    @Basic(optional = false)
    @Column(name = "devuelto")
    private String devuelto;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codprestlibroest")
    private Collection<DevolucionLibroEstudiante> devolucionLibroEstudianteCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codprestlibroest")
    private Collection<MultaLibroEstudiante> multaLibroEstudianteCollection;
    @JoinColumn(name = "idbibliotecario", referencedColumnName = "idbibliotecario")
    @ManyToOne(optional = false)
    private Bibliotecario_1 idbibliotecario;
    @JoinColumn(name = "codestudiante", referencedColumnName = "codestudiante")
    @ManyToOne(optional = false)
    private Estudiante_1 codestudiante;
    @JoinColumn(name = "codbarralibro", referencedColumnName = "codbarralibro")
    @ManyToOne(optional = false)
    private Libro codbarralibro;

    public PrestamoLibroEstudiante() {
    }

    public PrestamoLibroEstudiante(Integer codprestlibroest) {
        this.codprestlibroest = codprestlibroest;
    }

    public PrestamoLibroEstudiante(Integer codprestlibroest, Date fechaprestamo, Date fechadevolucion, int numrenovaciones, String devuelto) {
        this.codprestlibroest = codprestlibroest;
        this.fechaprestamo = fechaprestamo;
        this.fechadevolucion = fechadevolucion;
        this.numrenovaciones = numrenovaciones;
        this.devuelto = devuelto;
    }

    public Integer getCodprestlibroest() {
        return codprestlibroest;
    }

    public void setCodprestlibroest(Integer codprestlibroest) {
        this.codprestlibroest = codprestlibroest;
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

    public int getNumrenovaciones() {
        return numrenovaciones;
    }

    public void setNumrenovaciones(int numrenovaciones) {
        this.numrenovaciones = numrenovaciones;
    }

    public String getDevuelto() {
        return devuelto;
    }

    public void setDevuelto(String devuelto) {
        this.devuelto = devuelto;
    }

    @XmlTransient
    public Collection<DevolucionLibroEstudiante> getDevolucionLibroEstudianteCollection() {
        return devolucionLibroEstudianteCollection;
    }

    public void setDevolucionLibroEstudianteCollection(Collection<DevolucionLibroEstudiante> devolucionLibroEstudianteCollection) {
        this.devolucionLibroEstudianteCollection = devolucionLibroEstudianteCollection;
    }

    @XmlTransient
    public Collection<MultaLibroEstudiante> getMultaLibroEstudianteCollection() {
        return multaLibroEstudianteCollection;
    }

    public void setMultaLibroEstudianteCollection(Collection<MultaLibroEstudiante> multaLibroEstudianteCollection) {
        this.multaLibroEstudianteCollection = multaLibroEstudianteCollection;
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

    public Libro getCodbarralibro() {
        return codbarralibro;
    }

    public void setCodbarralibro(Libro codbarralibro) {
        this.codbarralibro = codbarralibro;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codprestlibroest != null ? codprestlibroest.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PrestamoLibroEstudiante)) {
            return false;
        }
        PrestamoLibroEstudiante other = (PrestamoLibroEstudiante) object;
        if ((this.codprestlibroest == null && other.codprestlibroest != null) || (this.codprestlibroest != null && !this.codprestlibroest.equals(other.codprestlibroest))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entitysRecursos.PrestamoLibroEstudiante[ codprestlibroest=" + codprestlibroest + " ]";
    }
    
}

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
@Table(name = "prestamo_libro_profesor")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PrestamoLibroProfesor.findAll", query = "SELECT p FROM PrestamoLibroProfesor p")
    , @NamedQuery(name = "PrestamoLibroProfesor.findByCodprestlibroprof", query = "SELECT p FROM PrestamoLibroProfesor p WHERE p.codprestlibroprof = :codprestlibroprof")
    , @NamedQuery(name = "PrestamoLibroProfesor.findByFechaprestamo", query = "SELECT p FROM PrestamoLibroProfesor p WHERE p.fechaprestamo = :fechaprestamo")
    , @NamedQuery(name = "PrestamoLibroProfesor.findByFechadevolucion", query = "SELECT p FROM PrestamoLibroProfesor p WHERE p.fechadevolucion = :fechadevolucion")
    , @NamedQuery(name = "PrestamoLibroProfesor.findByNumrenovaciones", query = "SELECT p FROM PrestamoLibroProfesor p WHERE p.numrenovaciones = :numrenovaciones")
    , @NamedQuery(name = "PrestamoLibroProfesor.findByDevuelto", query = "SELECT p FROM PrestamoLibroProfesor p WHERE p.devuelto = :devuelto")})
public class PrestamoLibroProfesor implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codprestlibroprof")
    private Integer codprestlibroprof;
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
    @JoinColumn(name = "idbibliotecario", referencedColumnName = "idbibliotecario")
    @ManyToOne(optional = false)
    private Bibliotecario_1 idbibliotecario;
    @JoinColumn(name = "codbarralibro", referencedColumnName = "codbarralibro")
    @ManyToOne(optional = false)
    private Libro codbarralibro;
    @JoinColumn(name = "idprofesor", referencedColumnName = "idprofesor")
    @ManyToOne(optional = false)
    private Profesor idprofesor;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codprestlibroprof")
    private Collection<DevolucionLibroProfesor> devolucionLibroProfesorCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codprestlibroprof")
    private Collection<MultaLibroProfesor> multaLibroProfesorCollection;

    public PrestamoLibroProfesor() {
    }

    public PrestamoLibroProfesor(Integer codprestlibroprof) {
        this.codprestlibroprof = codprestlibroprof;
    }

    public PrestamoLibroProfesor(Integer codprestlibroprof, Date fechaprestamo, Date fechadevolucion, int numrenovaciones, String devuelto) {
        this.codprestlibroprof = codprestlibroprof;
        this.fechaprestamo = fechaprestamo;
        this.fechadevolucion = fechadevolucion;
        this.numrenovaciones = numrenovaciones;
        this.devuelto = devuelto;
    }

    public Integer getCodprestlibroprof() {
        return codprestlibroprof;
    }

    public void setCodprestlibroprof(Integer codprestlibroprof) {
        this.codprestlibroprof = codprestlibroprof;
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

    public Bibliotecario_1 getIdbibliotecario() {
        return idbibliotecario;
    }

    public void setIdbibliotecario(Bibliotecario_1 idbibliotecario) {
        this.idbibliotecario = idbibliotecario;
    }

    public Libro getCodbarralibro() {
        return codbarralibro;
    }

    public void setCodbarralibro(Libro codbarralibro) {
        this.codbarralibro = codbarralibro;
    }

    public Profesor getIdprofesor() {
        return idprofesor;
    }

    public void setIdprofesor(Profesor idprofesor) {
        this.idprofesor = idprofesor;
    }

    @XmlTransient
    public Collection<DevolucionLibroProfesor> getDevolucionLibroProfesorCollection() {
        return devolucionLibroProfesorCollection;
    }

    public void setDevolucionLibroProfesorCollection(Collection<DevolucionLibroProfesor> devolucionLibroProfesorCollection) {
        this.devolucionLibroProfesorCollection = devolucionLibroProfesorCollection;
    }

    @XmlTransient
    public Collection<MultaLibroProfesor> getMultaLibroProfesorCollection() {
        return multaLibroProfesorCollection;
    }

    public void setMultaLibroProfesorCollection(Collection<MultaLibroProfesor> multaLibroProfesorCollection) {
        this.multaLibroProfesorCollection = multaLibroProfesorCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codprestlibroprof != null ? codprestlibroprof.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PrestamoLibroProfesor)) {
            return false;
        }
        PrestamoLibroProfesor other = (PrestamoLibroProfesor) object;
        if ((this.codprestlibroprof == null && other.codprestlibroprof != null) || (this.codprestlibroprof != null && !this.codprestlibroprof.equals(other.codprestlibroprof))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entitysRecursos.PrestamoLibroProfesor[ codprestlibroprof=" + codprestlibroprof + " ]";
    }
    
}

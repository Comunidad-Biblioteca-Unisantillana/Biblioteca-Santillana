/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entitys;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Storkolm
 */
@Entity
@Table(name = "devolucion_libro_estudiante")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DevolucionLibroEstudiante.findAll", query = "SELECT d FROM DevolucionLibroEstudiante d")
    , @NamedQuery(name = "DevolucionLibroEstudiante.findByCoddevlibroest", query = "SELECT d FROM DevolucionLibroEstudiante d WHERE d.coddevlibroest = :coddevlibroest")
    , @NamedQuery(name = "DevolucionLibroEstudiante.findByFechadevolucion", query = "SELECT d FROM DevolucionLibroEstudiante d WHERE d.fechadevolucion = :fechadevolucion")
    , @NamedQuery(name = "DevolucionLibroEstudiante.findByEstadodevolucion", query = "SELECT d FROM DevolucionLibroEstudiante d WHERE d.estadodevolucion = :estadodevolucion")})
public class DevolucionLibroEstudiante implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "coddevlibroest")
    private Integer coddevlibroest;
    @Basic(optional = false)
    @Column(name = "fechadevolucion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechadevolucion;
    @Basic(optional = false)
    @Column(name = "estadodevolucion")
    private String estadodevolucion;
    @JoinColumn(name = "idbibliotecario", referencedColumnName = "idbibliotecario")
    @ManyToOne(optional = false)
    private Bibliotecario_1 idbibliotecario;
    @JoinColumn(name = "codprestlibroest", referencedColumnName = "codprestlibroest")
    @ManyToOne(optional = false)
    private PrestamoLibroEstudiante codprestlibroest;

    public DevolucionLibroEstudiante() {
    }

    public DevolucionLibroEstudiante(Integer coddevlibroest) {
        this.coddevlibroest = coddevlibroest;
    }

    public DevolucionLibroEstudiante(Integer coddevlibroest, Date fechadevolucion, String estadodevolucion) {
        this.coddevlibroest = coddevlibroest;
        this.fechadevolucion = fechadevolucion;
        this.estadodevolucion = estadodevolucion;
    }

    public Integer getCoddevlibroest() {
        return coddevlibroest;
    }

    public void setCoddevlibroest(Integer coddevlibroest) {
        this.coddevlibroest = coddevlibroest;
    }

    public Date getFechadevolucion() {
        return fechadevolucion;
    }

    public void setFechadevolucion(Date fechadevolucion) {
        this.fechadevolucion = fechadevolucion;
    }

    public String getEstadodevolucion() {
        return estadodevolucion;
    }

    public void setEstadodevolucion(String estadodevolucion) {
        this.estadodevolucion = estadodevolucion;
    }

    public Bibliotecario_1 getIdbibliotecario() {
        return idbibliotecario;
    }

    public void setIdbibliotecario(Bibliotecario_1 idbibliotecario) {
        this.idbibliotecario = idbibliotecario;
    }

    public PrestamoLibroEstudiante getCodprestlibroest() {
        return codprestlibroest;
    }

    public void setCodprestlibroest(PrestamoLibroEstudiante codprestlibroest) {
        this.codprestlibroest = codprestlibroest;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (coddevlibroest != null ? coddevlibroest.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DevolucionLibroEstudiante)) {
            return false;
        }
        DevolucionLibroEstudiante other = (DevolucionLibroEstudiante) object;
        if ((this.coddevlibroest == null && other.coddevlibroest != null) || (this.coddevlibroest != null && !this.coddevlibroest.equals(other.coddevlibroest))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entitysRecursos.DevolucionLibroEstudiante[ coddevlibroest=" + coddevlibroest + " ]";
    }
    
}

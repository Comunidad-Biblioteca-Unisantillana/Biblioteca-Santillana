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
@Table(name = "devolucion_enciclopedia_estudiante")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DevolucionEnciclopediaEstudiante.findAll", query = "SELECT d FROM DevolucionEnciclopediaEstudiante d")
    , @NamedQuery(name = "DevolucionEnciclopediaEstudiante.findByCoddevencest", query = "SELECT d FROM DevolucionEnciclopediaEstudiante d WHERE d.coddevencest = :coddevencest")
    , @NamedQuery(name = "DevolucionEnciclopediaEstudiante.findByFechadevolucion", query = "SELECT d FROM DevolucionEnciclopediaEstudiante d WHERE d.fechadevolucion = :fechadevolucion")
    , @NamedQuery(name = "DevolucionEnciclopediaEstudiante.findByEstadodevolucion", query = "SELECT d FROM DevolucionEnciclopediaEstudiante d WHERE d.estadodevolucion = :estadodevolucion")})
public class DevolucionEnciclopediaEstudiante implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "coddevencest")
    private Integer coddevencest;
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
    @JoinColumn(name = "codprestencest", referencedColumnName = "codprestencest")
    @ManyToOne(optional = false)
    private PrestamoEnciclopediaEstudiante codprestencest;

    public DevolucionEnciclopediaEstudiante() {
    }

    public DevolucionEnciclopediaEstudiante(Integer coddevencest) {
        this.coddevencest = coddevencest;
    }

    public DevolucionEnciclopediaEstudiante(Integer coddevencest, Date fechadevolucion, String estadodevolucion) {
        this.coddevencest = coddevencest;
        this.fechadevolucion = fechadevolucion;
        this.estadodevolucion = estadodevolucion;
    }

    public Integer getCoddevencest() {
        return coddevencest;
    }

    public void setCoddevencest(Integer coddevencest) {
        this.coddevencest = coddevencest;
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

    public PrestamoEnciclopediaEstudiante getCodprestencest() {
        return codprestencest;
    }

    public void setCodprestencest(PrestamoEnciclopediaEstudiante codprestencest) {
        this.codprestencest = codprestencest;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (coddevencest != null ? coddevencest.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DevolucionEnciclopediaEstudiante)) {
            return false;
        }
        DevolucionEnciclopediaEstudiante other = (DevolucionEnciclopediaEstudiante) object;
        if ((this.coddevencest == null && other.coddevencest != null) || (this.coddevencest != null && !this.coddevencest.equals(other.coddevencest))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entitysRecursos.DevolucionEnciclopediaEstudiante[ coddevencest=" + coddevencest + " ]";
    }
    
}

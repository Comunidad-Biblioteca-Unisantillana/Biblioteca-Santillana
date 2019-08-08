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
@Table(name = "devolucion_periodico_estudiante")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DevolucionPeriodicoEstudiante.findAll", query = "SELECT d FROM DevolucionPeriodicoEstudiante d")
    , @NamedQuery(name = "DevolucionPeriodicoEstudiante.findByCoddevperiodicoest", query = "SELECT d FROM DevolucionPeriodicoEstudiante d WHERE d.coddevperiodicoest = :coddevperiodicoest")
    , @NamedQuery(name = "DevolucionPeriodicoEstudiante.findByFechadevolucion", query = "SELECT d FROM DevolucionPeriodicoEstudiante d WHERE d.fechadevolucion = :fechadevolucion")
    , @NamedQuery(name = "DevolucionPeriodicoEstudiante.findByEstadodevolucion", query = "SELECT d FROM DevolucionPeriodicoEstudiante d WHERE d.estadodevolucion = :estadodevolucion")})
public class DevolucionPeriodicoEstudiante implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "coddevperiodicoest")
    private Integer coddevperiodicoest;
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
    @JoinColumn(name = "codprestperiodicoest", referencedColumnName = "codprestperiodicoest")
    @ManyToOne(optional = false)
    private PrestamoPeriodicoEstudiante codprestperiodicoest;

    public DevolucionPeriodicoEstudiante() {
    }

    public DevolucionPeriodicoEstudiante(Integer coddevperiodicoest) {
        this.coddevperiodicoest = coddevperiodicoest;
    }

    public DevolucionPeriodicoEstudiante(Integer coddevperiodicoest, Date fechadevolucion, String estadodevolucion) {
        this.coddevperiodicoest = coddevperiodicoest;
        this.fechadevolucion = fechadevolucion;
        this.estadodevolucion = estadodevolucion;
    }

    public Integer getCoddevperiodicoest() {
        return coddevperiodicoest;
    }

    public void setCoddevperiodicoest(Integer coddevperiodicoest) {
        this.coddevperiodicoest = coddevperiodicoest;
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

    public PrestamoPeriodicoEstudiante getCodprestperiodicoest() {
        return codprestperiodicoest;
    }

    public void setCodprestperiodicoest(PrestamoPeriodicoEstudiante codprestperiodicoest) {
        this.codprestperiodicoest = codprestperiodicoest;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (coddevperiodicoest != null ? coddevperiodicoest.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DevolucionPeriodicoEstudiante)) {
            return false;
        }
        DevolucionPeriodicoEstudiante other = (DevolucionPeriodicoEstudiante) object;
        if ((this.coddevperiodicoest == null && other.coddevperiodicoest != null) || (this.coddevperiodicoest != null && !this.coddevperiodicoest.equals(other.coddevperiodicoest))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entitysRecursos.DevolucionPeriodicoEstudiante[ coddevperiodicoest=" + coddevperiodicoest + " ]";
    }
    
}

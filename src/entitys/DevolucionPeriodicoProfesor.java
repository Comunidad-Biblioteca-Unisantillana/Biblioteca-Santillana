/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entitys;

import entitysUsuario.Bibliotecario;
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
@Table(name = "devolucion_periodico_profesor")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DevolucionPeriodicoProfesor.findAll", query = "SELECT d FROM DevolucionPeriodicoProfesor d")
    , @NamedQuery(name = "DevolucionPeriodicoProfesor.findByCoddevperiodicoprof", query = "SELECT d FROM DevolucionPeriodicoProfesor d WHERE d.coddevperiodicoprof = :coddevperiodicoprof")
    , @NamedQuery(name = "DevolucionPeriodicoProfesor.findByFechadevolucion", query = "SELECT d FROM DevolucionPeriodicoProfesor d WHERE d.fechadevolucion = :fechadevolucion")
    , @NamedQuery(name = "DevolucionPeriodicoProfesor.findByEstadodevolucion", query = "SELECT d FROM DevolucionPeriodicoProfesor d WHERE d.estadodevolucion = :estadodevolucion")})
public class DevolucionPeriodicoProfesor implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "coddevperiodicoprof")
    private Integer coddevperiodicoprof;
    @Basic(optional = false)
    @Column(name = "fechadevolucion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechadevolucion;
    @Basic(optional = false)
    @Column(name = "estadodevolucion")
    private String estadodevolucion;
    @JoinColumn(name = "idbibliotecario", referencedColumnName = "idbibliotecario")
    @ManyToOne(optional = false)
    private Bibliotecario idbibliotecario;
    @JoinColumn(name = "codprestperiodicoprof", referencedColumnName = "codprestperiodicoprof")
    @ManyToOne(optional = false)
    private PrestamoPeriodicoProfesor codprestperiodicoprof;

    public DevolucionPeriodicoProfesor() {
    }

    public DevolucionPeriodicoProfesor(Integer coddevperiodicoprof) {
        this.coddevperiodicoprof = coddevperiodicoprof;
    }

    public DevolucionPeriodicoProfesor(Integer coddevperiodicoprof, Date fechadevolucion, String estadodevolucion) {
        this.coddevperiodicoprof = coddevperiodicoprof;
        this.fechadevolucion = fechadevolucion;
        this.estadodevolucion = estadodevolucion;
    }

    public Integer getCoddevperiodicoprof() {
        return coddevperiodicoprof;
    }

    public void setCoddevperiodicoprof(Integer coddevperiodicoprof) {
        this.coddevperiodicoprof = coddevperiodicoprof;
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

    public Bibliotecario getIdbibliotecario() {
        return idbibliotecario;
    }

    public void setIdbibliotecario(Bibliotecario idbibliotecario) {
        this.idbibliotecario = idbibliotecario;
    }

    public PrestamoPeriodicoProfesor getCodprestperiodicoprof() {
        return codprestperiodicoprof;
    }

    public void setCodprestperiodicoprof(PrestamoPeriodicoProfesor codprestperiodicoprof) {
        this.codprestperiodicoprof = codprestperiodicoprof;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (coddevperiodicoprof != null ? coddevperiodicoprof.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DevolucionPeriodicoProfesor)) {
            return false;
        }
        DevolucionPeriodicoProfesor other = (DevolucionPeriodicoProfesor) object;
        if ((this.coddevperiodicoprof == null && other.coddevperiodicoprof != null) || (this.coddevperiodicoprof != null && !this.coddevperiodicoprof.equals(other.coddevperiodicoprof))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entitysRecursos.DevolucionPeriodicoProfesor[ coddevperiodicoprof=" + coddevperiodicoprof + " ]";
    }
    
}

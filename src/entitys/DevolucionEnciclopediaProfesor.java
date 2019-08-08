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
@Table(name = "devolucion_enciclopedia_profesor")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DevolucionEnciclopediaProfesor.findAll", query = "SELECT d FROM DevolucionEnciclopediaProfesor d")
    , @NamedQuery(name = "DevolucionEnciclopediaProfesor.findByCoddevencprof", query = "SELECT d FROM DevolucionEnciclopediaProfesor d WHERE d.coddevencprof = :coddevencprof")
    , @NamedQuery(name = "DevolucionEnciclopediaProfesor.findByFechadevolucion", query = "SELECT d FROM DevolucionEnciclopediaProfesor d WHERE d.fechadevolucion = :fechadevolucion")
    , @NamedQuery(name = "DevolucionEnciclopediaProfesor.findByEstadodevolucion", query = "SELECT d FROM DevolucionEnciclopediaProfesor d WHERE d.estadodevolucion = :estadodevolucion")})
public class DevolucionEnciclopediaProfesor implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "coddevencprof")
    private Integer coddevencprof;
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
    @JoinColumn(name = "codprestencprof", referencedColumnName = "codprestencprof")
    @ManyToOne(optional = false)
    private PrestamoEnciclopediaProfesor codprestencprof;

    public DevolucionEnciclopediaProfesor() {
    }

    public DevolucionEnciclopediaProfesor(Integer coddevencprof) {
        this.coddevencprof = coddevencprof;
    }

    public DevolucionEnciclopediaProfesor(Integer coddevencprof, Date fechadevolucion, String estadodevolucion) {
        this.coddevencprof = coddevencprof;
        this.fechadevolucion = fechadevolucion;
        this.estadodevolucion = estadodevolucion;
    }

    public Integer getCoddevencprof() {
        return coddevencprof;
    }

    public void setCoddevencprof(Integer coddevencprof) {
        this.coddevencprof = coddevencprof;
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

    public PrestamoEnciclopediaProfesor getCodprestencprof() {
        return codprestencprof;
    }

    public void setCodprestencprof(PrestamoEnciclopediaProfesor codprestencprof) {
        this.codprestencprof = codprestencprof;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (coddevencprof != null ? coddevencprof.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DevolucionEnciclopediaProfesor)) {
            return false;
        }
        DevolucionEnciclopediaProfesor other = (DevolucionEnciclopediaProfesor) object;
        if ((this.coddevencprof == null && other.coddevencprof != null) || (this.coddevencprof != null && !this.coddevencprof.equals(other.coddevencprof))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entitysRecursos.DevolucionEnciclopediaProfesor[ coddevencprof=" + coddevencprof + " ]";
    }
    
}

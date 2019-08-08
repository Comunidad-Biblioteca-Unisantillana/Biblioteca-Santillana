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
@Table(name = "devolucion_mapa_profesor")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DevolucionMapaProfesor.findAll", query = "SELECT d FROM DevolucionMapaProfesor d")
    , @NamedQuery(name = "DevolucionMapaProfesor.findByCoddevmapaprof", query = "SELECT d FROM DevolucionMapaProfesor d WHERE d.coddevmapaprof = :coddevmapaprof")
    , @NamedQuery(name = "DevolucionMapaProfesor.findByFechadevolucion", query = "SELECT d FROM DevolucionMapaProfesor d WHERE d.fechadevolucion = :fechadevolucion")
    , @NamedQuery(name = "DevolucionMapaProfesor.findByEstadodevolucion", query = "SELECT d FROM DevolucionMapaProfesor d WHERE d.estadodevolucion = :estadodevolucion")})
public class DevolucionMapaProfesor implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "coddevmapaprof")
    private Integer coddevmapaprof;
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
    @JoinColumn(name = "codprestmapaprof", referencedColumnName = "codprestmapaprof")
    @ManyToOne(optional = false)
    private PrestamoMapaProfesor codprestmapaprof;

    public DevolucionMapaProfesor() {
    }

    public DevolucionMapaProfesor(Integer coddevmapaprof) {
        this.coddevmapaprof = coddevmapaprof;
    }

    public DevolucionMapaProfesor(Integer coddevmapaprof, Date fechadevolucion, String estadodevolucion) {
        this.coddevmapaprof = coddevmapaprof;
        this.fechadevolucion = fechadevolucion;
        this.estadodevolucion = estadodevolucion;
    }

    public Integer getCoddevmapaprof() {
        return coddevmapaprof;
    }

    public void setCoddevmapaprof(Integer coddevmapaprof) {
        this.coddevmapaprof = coddevmapaprof;
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

    public PrestamoMapaProfesor getCodprestmapaprof() {
        return codprestmapaprof;
    }

    public void setCodprestmapaprof(PrestamoMapaProfesor codprestmapaprof) {
        this.codprestmapaprof = codprestmapaprof;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (coddevmapaprof != null ? coddevmapaprof.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DevolucionMapaProfesor)) {
            return false;
        }
        DevolucionMapaProfesor other = (DevolucionMapaProfesor) object;
        if ((this.coddevmapaprof == null && other.coddevmapaprof != null) || (this.coddevmapaprof != null && !this.coddevmapaprof.equals(other.coddevmapaprof))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entitysRecursos.DevolucionMapaProfesor[ coddevmapaprof=" + coddevmapaprof + " ]";
    }
    
}

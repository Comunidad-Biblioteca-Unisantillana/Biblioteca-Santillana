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
@Table(name = "devolucion_revista_profesor")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DevolucionRevistaProfesor.findAll", query = "SELECT d FROM DevolucionRevistaProfesor d")
    , @NamedQuery(name = "DevolucionRevistaProfesor.findByCoddevrevistaprof", query = "SELECT d FROM DevolucionRevistaProfesor d WHERE d.coddevrevistaprof = :coddevrevistaprof")
    , @NamedQuery(name = "DevolucionRevistaProfesor.findByFechadevolucion", query = "SELECT d FROM DevolucionRevistaProfesor d WHERE d.fechadevolucion = :fechadevolucion")
    , @NamedQuery(name = "DevolucionRevistaProfesor.findByEstadodevolucion", query = "SELECT d FROM DevolucionRevistaProfesor d WHERE d.estadodevolucion = :estadodevolucion")})
public class DevolucionRevistaProfesor implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "coddevrevistaprof")
    private Integer coddevrevistaprof;
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
    @JoinColumn(name = "codprestrevistaprof", referencedColumnName = "codprestrevistaprof")
    @ManyToOne(optional = false)
    private PrestamoRevistaProfesor codprestrevistaprof;

    public DevolucionRevistaProfesor() {
    }

    public DevolucionRevistaProfesor(Integer coddevrevistaprof) {
        this.coddevrevistaprof = coddevrevistaprof;
    }

    public DevolucionRevistaProfesor(Integer coddevrevistaprof, Date fechadevolucion, String estadodevolucion) {
        this.coddevrevistaprof = coddevrevistaprof;
        this.fechadevolucion = fechadevolucion;
        this.estadodevolucion = estadodevolucion;
    }

    public Integer getCoddevrevistaprof() {
        return coddevrevistaprof;
    }

    public void setCoddevrevistaprof(Integer coddevrevistaprof) {
        this.coddevrevistaprof = coddevrevistaprof;
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

    public PrestamoRevistaProfesor getCodprestrevistaprof() {
        return codprestrevistaprof;
    }

    public void setCodprestrevistaprof(PrestamoRevistaProfesor codprestrevistaprof) {
        this.codprestrevistaprof = codprestrevistaprof;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (coddevrevistaprof != null ? coddevrevistaprof.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DevolucionRevistaProfesor)) {
            return false;
        }
        DevolucionRevistaProfesor other = (DevolucionRevistaProfesor) object;
        if ((this.coddevrevistaprof == null && other.coddevrevistaprof != null) || (this.coddevrevistaprof != null && !this.coddevrevistaprof.equals(other.coddevrevistaprof))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entitysRecursos.DevolucionRevistaProfesor[ coddevrevistaprof=" + coddevrevistaprof + " ]";
    }
    
}

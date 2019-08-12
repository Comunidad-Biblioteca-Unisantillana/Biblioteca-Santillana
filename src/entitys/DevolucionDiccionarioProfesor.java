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
@Table(name = "devolucion_diccionario_profesor")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DevolucionDiccionarioProfesor.findAll", query = "SELECT d FROM DevolucionDiccionarioProfesor d")
    , @NamedQuery(name = "DevolucionDiccionarioProfesor.findByCoddevdicprof", query = "SELECT d FROM DevolucionDiccionarioProfesor d WHERE d.coddevdicprof = :coddevdicprof")
    , @NamedQuery(name = "DevolucionDiccionarioProfesor.findByFechadevolucion", query = "SELECT d FROM DevolucionDiccionarioProfesor d WHERE d.fechadevolucion = :fechadevolucion")
    , @NamedQuery(name = "DevolucionDiccionarioProfesor.findByEstadodevolucion", query = "SELECT d FROM DevolucionDiccionarioProfesor d WHERE d.estadodevolucion = :estadodevolucion")})
public class DevolucionDiccionarioProfesor implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "coddevdicprof")
    private Integer coddevdicprof;
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
    @JoinColumn(name = "codprestdicprof", referencedColumnName = "codprestdicprof")
    @ManyToOne(optional = false)
    private PrestamoDiccionarioProfesor codprestdicprof;

    public DevolucionDiccionarioProfesor() {
    }

    public DevolucionDiccionarioProfesor(Integer coddevdicprof) {
        this.coddevdicprof = coddevdicprof;
    }

    public DevolucionDiccionarioProfesor(Integer coddevdicprof, Date fechadevolucion, String estadodevolucion) {
        this.coddevdicprof = coddevdicprof;
        this.fechadevolucion = fechadevolucion;
        this.estadodevolucion = estadodevolucion;
    }

    public Integer getCoddevdicprof() {
        return coddevdicprof;
    }

    public void setCoddevdicprof(Integer coddevdicprof) {
        this.coddevdicprof = coddevdicprof;
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

    public PrestamoDiccionarioProfesor getCodprestdicprof() {
        return codprestdicprof;
    }

    public void setCodprestdicprof(PrestamoDiccionarioProfesor codprestdicprof) {
        this.codprestdicprof = codprestdicprof;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (coddevdicprof != null ? coddevdicprof.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DevolucionDiccionarioProfesor)) {
            return false;
        }
        DevolucionDiccionarioProfesor other = (DevolucionDiccionarioProfesor) object;
        if ((this.coddevdicprof == null && other.coddevdicprof != null) || (this.coddevdicprof != null && !this.coddevdicprof.equals(other.coddevdicprof))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entitysRecursos.DevolucionDiccionarioProfesor[ coddevdicprof=" + coddevdicprof + " ]";
    }
    
}

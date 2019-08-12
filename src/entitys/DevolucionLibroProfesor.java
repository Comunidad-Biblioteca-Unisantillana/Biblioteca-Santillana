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
@Table(name = "devolucion_libro_profesor")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DevolucionLibroProfesor.findAll", query = "SELECT d FROM DevolucionLibroProfesor d")
    , @NamedQuery(name = "DevolucionLibroProfesor.findByCoddevlibroprof", query = "SELECT d FROM DevolucionLibroProfesor d WHERE d.coddevlibroprof = :coddevlibroprof")
    , @NamedQuery(name = "DevolucionLibroProfesor.findByFechadevolucion", query = "SELECT d FROM DevolucionLibroProfesor d WHERE d.fechadevolucion = :fechadevolucion")
    , @NamedQuery(name = "DevolucionLibroProfesor.findByEstadodevolucion", query = "SELECT d FROM DevolucionLibroProfesor d WHERE d.estadodevolucion = :estadodevolucion")})
public class DevolucionLibroProfesor implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "coddevlibroprof")
    private Integer coddevlibroprof;
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
    @JoinColumn(name = "codprestlibroprof", referencedColumnName = "codprestlibroprof")
    @ManyToOne(optional = false)
    private PrestamoLibroProfesor codprestlibroprof;

    public DevolucionLibroProfesor() {
    }

    public DevolucionLibroProfesor(Integer coddevlibroprof) {
        this.coddevlibroprof = coddevlibroprof;
    }

    public DevolucionLibroProfesor(Integer coddevlibroprof, Date fechadevolucion, String estadodevolucion) {
        this.coddevlibroprof = coddevlibroprof;
        this.fechadevolucion = fechadevolucion;
        this.estadodevolucion = estadodevolucion;
    }

    public Integer getCoddevlibroprof() {
        return coddevlibroprof;
    }

    public void setCoddevlibroprof(Integer coddevlibroprof) {
        this.coddevlibroprof = coddevlibroprof;
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

    public PrestamoLibroProfesor getCodprestlibroprof() {
        return codprestlibroprof;
    }

    public void setCodprestlibroprof(PrestamoLibroProfesor codprestlibroprof) {
        this.codprestlibroprof = codprestlibroprof;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (coddevlibroprof != null ? coddevlibroprof.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DevolucionLibroProfesor)) {
            return false;
        }
        DevolucionLibroProfesor other = (DevolucionLibroProfesor) object;
        if ((this.coddevlibroprof == null && other.coddevlibroprof != null) || (this.coddevlibroprof != null && !this.coddevlibroprof.equals(other.coddevlibroprof))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entitysRecursos.DevolucionLibroProfesor[ coddevlibroprof=" + coddevlibroprof + " ]";
    }
    
}

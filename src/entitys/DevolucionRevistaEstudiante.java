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
@Table(name = "devolucion_revista_estudiante")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DevolucionRevistaEstudiante.findAll", query = "SELECT d FROM DevolucionRevistaEstudiante d")
    , @NamedQuery(name = "DevolucionRevistaEstudiante.findByCoddevrevistaest", query = "SELECT d FROM DevolucionRevistaEstudiante d WHERE d.coddevrevistaest = :coddevrevistaest")
    , @NamedQuery(name = "DevolucionRevistaEstudiante.findByFechadevolucion", query = "SELECT d FROM DevolucionRevistaEstudiante d WHERE d.fechadevolucion = :fechadevolucion")
    , @NamedQuery(name = "DevolucionRevistaEstudiante.findByEstadodevolucion", query = "SELECT d FROM DevolucionRevistaEstudiante d WHERE d.estadodevolucion = :estadodevolucion")})
public class DevolucionRevistaEstudiante implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "coddevrevistaest")
    private Integer coddevrevistaest;
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
    @JoinColumn(name = "codprestrevistaest", referencedColumnName = "codprestrevistaest")
    @ManyToOne(optional = false)
    private PrestamoRevistaEstudiante codprestrevistaest;

    public DevolucionRevistaEstudiante() {
    }

    public DevolucionRevistaEstudiante(Integer coddevrevistaest) {
        this.coddevrevistaest = coddevrevistaest;
    }

    public DevolucionRevistaEstudiante(Integer coddevrevistaest, Date fechadevolucion, String estadodevolucion) {
        this.coddevrevistaest = coddevrevistaest;
        this.fechadevolucion = fechadevolucion;
        this.estadodevolucion = estadodevolucion;
    }

    public Integer getCoddevrevistaest() {
        return coddevrevistaest;
    }

    public void setCoddevrevistaest(Integer coddevrevistaest) {
        this.coddevrevistaest = coddevrevistaest;
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

    public PrestamoRevistaEstudiante getCodprestrevistaest() {
        return codprestrevistaest;
    }

    public void setCodprestrevistaest(PrestamoRevistaEstudiante codprestrevistaest) {
        this.codprestrevistaest = codprestrevistaest;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (coddevrevistaest != null ? coddevrevistaest.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DevolucionRevistaEstudiante)) {
            return false;
        }
        DevolucionRevistaEstudiante other = (DevolucionRevistaEstudiante) object;
        if ((this.coddevrevistaest == null && other.coddevrevistaest != null) || (this.coddevrevistaest != null && !this.coddevrevistaest.equals(other.coddevrevistaest))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entitysRecursos.DevolucionRevistaEstudiante[ coddevrevistaest=" + coddevrevistaest + " ]";
    }
    
}

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
@Table(name = "devolucion_mapa_estudiante")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DevolucionMapaEstudiante.findAll", query = "SELECT d FROM DevolucionMapaEstudiante d")
    , @NamedQuery(name = "DevolucionMapaEstudiante.findByCoddevmapaest", query = "SELECT d FROM DevolucionMapaEstudiante d WHERE d.coddevmapaest = :coddevmapaest")
    , @NamedQuery(name = "DevolucionMapaEstudiante.findByFechadevolucion", query = "SELECT d FROM DevolucionMapaEstudiante d WHERE d.fechadevolucion = :fechadevolucion")
    , @NamedQuery(name = "DevolucionMapaEstudiante.findByEstadodevolucion", query = "SELECT d FROM DevolucionMapaEstudiante d WHERE d.estadodevolucion = :estadodevolucion")})
public class DevolucionMapaEstudiante implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "coddevmapaest")
    private Integer coddevmapaest;
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
    @JoinColumn(name = "codprestmapaest", referencedColumnName = "codprestmapaest")
    @ManyToOne(optional = false)
    private PrestamoMapaEstudiante codprestmapaest;

    public DevolucionMapaEstudiante() {
    }

    public DevolucionMapaEstudiante(Integer coddevmapaest) {
        this.coddevmapaest = coddevmapaest;
    }

    public DevolucionMapaEstudiante(Integer coddevmapaest, Date fechadevolucion, String estadodevolucion) {
        this.coddevmapaest = coddevmapaest;
        this.fechadevolucion = fechadevolucion;
        this.estadodevolucion = estadodevolucion;
    }

    public Integer getCoddevmapaest() {
        return coddevmapaest;
    }

    public void setCoddevmapaest(Integer coddevmapaest) {
        this.coddevmapaest = coddevmapaest;
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

    public PrestamoMapaEstudiante getCodprestmapaest() {
        return codprestmapaest;
    }

    public void setCodprestmapaest(PrestamoMapaEstudiante codprestmapaest) {
        this.codprestmapaest = codprestmapaest;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (coddevmapaest != null ? coddevmapaest.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DevolucionMapaEstudiante)) {
            return false;
        }
        DevolucionMapaEstudiante other = (DevolucionMapaEstudiante) object;
        if ((this.coddevmapaest == null && other.coddevmapaest != null) || (this.coddevmapaest != null && !this.coddevmapaest.equals(other.coddevmapaest))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entitysRecursos.DevolucionMapaEstudiante[ coddevmapaest=" + coddevmapaest + " ]";
    }
    
}

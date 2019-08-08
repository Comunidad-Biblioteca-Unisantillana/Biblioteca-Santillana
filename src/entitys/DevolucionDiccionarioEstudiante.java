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
@Table(name = "devolucion_diccionario_estudiante")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DevolucionDiccionarioEstudiante.findAll", query = "SELECT d FROM DevolucionDiccionarioEstudiante d")
    , @NamedQuery(name = "DevolucionDiccionarioEstudiante.findByCoddevdicest", query = "SELECT d FROM DevolucionDiccionarioEstudiante d WHERE d.coddevdicest = :coddevdicest")
    , @NamedQuery(name = "DevolucionDiccionarioEstudiante.findByFechadevolucion", query = "SELECT d FROM DevolucionDiccionarioEstudiante d WHERE d.fechadevolucion = :fechadevolucion")
    , @NamedQuery(name = "DevolucionDiccionarioEstudiante.findByEstadodevolucion", query = "SELECT d FROM DevolucionDiccionarioEstudiante d WHERE d.estadodevolucion = :estadodevolucion")})
public class DevolucionDiccionarioEstudiante implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "coddevdicest")
    private Integer coddevdicest;
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
    @JoinColumn(name = "codprestdicest", referencedColumnName = "codprestdicest")
    @ManyToOne(optional = false)
    private PrestamoDiccionarioEstudiante codprestdicest;

    public DevolucionDiccionarioEstudiante() {
    }

    public DevolucionDiccionarioEstudiante(Integer coddevdicest) {
        this.coddevdicest = coddevdicest;
    }

    public DevolucionDiccionarioEstudiante(Integer coddevdicest, Date fechadevolucion, String estadodevolucion) {
        this.coddevdicest = coddevdicest;
        this.fechadevolucion = fechadevolucion;
        this.estadodevolucion = estadodevolucion;
    }

    public Integer getCoddevdicest() {
        return coddevdicest;
    }

    public void setCoddevdicest(Integer coddevdicest) {
        this.coddevdicest = coddevdicest;
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

    public PrestamoDiccionarioEstudiante getCodprestdicest() {
        return codprestdicest;
    }

    public void setCodprestdicest(PrestamoDiccionarioEstudiante codprestdicest) {
        this.codprestdicest = codprestdicest;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (coddevdicest != null ? coddevdicest.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DevolucionDiccionarioEstudiante)) {
            return false;
        }
        DevolucionDiccionarioEstudiante other = (DevolucionDiccionarioEstudiante) object;
        if ((this.coddevdicest == null && other.coddevdicest != null) || (this.coddevdicest != null && !this.coddevdicest.equals(other.coddevdicest))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entitysRecursos.DevolucionDiccionarioEstudiante[ coddevdicest=" + coddevdicest + " ]";
    }
    
}

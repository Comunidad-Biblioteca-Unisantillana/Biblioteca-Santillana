/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entitys;

import entitysUsuario.Estudiante;
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
@Table(name = "reserva_colgen_estudiante")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ReservaColgenEstudiante.findAll", query = "SELECT r FROM ReservaColgenEstudiante r")
    , @NamedQuery(name = "ReservaColgenEstudiante.findByCodreservacolgenest", query = "SELECT r FROM ReservaColgenEstudiante r WHERE r.codreservacolgenest = :codreservacolgenest")
    , @NamedQuery(name = "ReservaColgenEstudiante.findByFechareserva", query = "SELECT r FROM ReservaColgenEstudiante r WHERE r.fechareserva = :fechareserva")
    , @NamedQuery(name = "ReservaColgenEstudiante.findByFecharetencion", query = "SELECT r FROM ReservaColgenEstudiante r WHERE r.fecharetencion = :fecharetencion")
    , @NamedQuery(name = "ReservaColgenEstudiante.findByFechalimitereserva", query = "SELECT r FROM ReservaColgenEstudiante r WHERE r.fechalimitereserva = :fechalimitereserva")})
public class ReservaColgenEstudiante implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codreservacolgenest")
    private Integer codreservacolgenest;
    @Basic(optional = false)
    @Column(name = "fechareserva")
    @Temporal(TemporalType.DATE)
    private Date fechareserva;
    @Column(name = "fecharetencion")
    @Temporal(TemporalType.DATE)
    private Date fecharetencion;
    @Column(name = "fechalimitereserva")
    @Temporal(TemporalType.DATE)
    private Date fechalimitereserva;
    @JoinColumn(name = "idbibliotecario", referencedColumnName = "idbibliotecario")
    @ManyToOne(optional = false)
    private Bibliotecario idbibliotecario;
    @JoinColumn(name = "codestudiante", referencedColumnName = "codestudiante")
    @ManyToOne(optional = false)
    private Estudiante codestudiante;
    @JoinColumn(name = "codbarralibro", referencedColumnName = "codbarralibro")
    @ManyToOne(optional = false)
    private Libro codbarralibro;

    public ReservaColgenEstudiante() {
    }

    public ReservaColgenEstudiante(Integer codreservacolgenest) {
        this.codreservacolgenest = codreservacolgenest;
    }

    public ReservaColgenEstudiante(Integer codreservacolgenest, Date fechareserva) {
        this.codreservacolgenest = codreservacolgenest;
        this.fechareserva = fechareserva;
    }

    public Integer getCodreservacolgenest() {
        return codreservacolgenest;
    }

    public void setCodreservacolgenest(Integer codreservacolgenest) {
        this.codreservacolgenest = codreservacolgenest;
    }

    public Date getFechareserva() {
        return fechareserva;
    }

    public void setFechareserva(Date fechareserva) {
        this.fechareserva = fechareserva;
    }

    public Date getFecharetencion() {
        return fecharetencion;
    }

    public void setFecharetencion(Date fecharetencion) {
        this.fecharetencion = fecharetencion;
    }

    public Date getFechalimitereserva() {
        return fechalimitereserva;
    }

    public void setFechalimitereserva(Date fechalimitereserva) {
        this.fechalimitereserva = fechalimitereserva;
    }

    public Bibliotecario getIdbibliotecario() {
        return idbibliotecario;
    }

    public void setIdbibliotecario(Bibliotecario idbibliotecario) {
        this.idbibliotecario = idbibliotecario;
    }

    public Estudiante getCodestudiante() {
        return codestudiante;
    }

    public void setCodestudiante(Estudiante codestudiante) {
        this.codestudiante = codestudiante;
    }

    public Libro getCodbarralibro() {
        return codbarralibro;
    }

    public void setCodbarralibro(Libro codbarralibro) {
        this.codbarralibro = codbarralibro;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codreservacolgenest != null ? codreservacolgenest.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ReservaColgenEstudiante)) {
            return false;
        }
        ReservaColgenEstudiante other = (ReservaColgenEstudiante) object;
        if ((this.codreservacolgenest == null && other.codreservacolgenest != null) || (this.codreservacolgenest != null && !this.codreservacolgenest.equals(other.codreservacolgenest))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entitysRecursos.ReservaColgenEstudiante[ codreservacolgenest=" + codreservacolgenest + " ]";
    }
    
}

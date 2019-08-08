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
@Table(name = "reserva_colgen_profesor")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ReservaColgenProfesor.findAll", query = "SELECT r FROM ReservaColgenProfesor r")
    , @NamedQuery(name = "ReservaColgenProfesor.findByCodreservacolgenprof", query = "SELECT r FROM ReservaColgenProfesor r WHERE r.codreservacolgenprof = :codreservacolgenprof")
    , @NamedQuery(name = "ReservaColgenProfesor.findByFechareserva", query = "SELECT r FROM ReservaColgenProfesor r WHERE r.fechareserva = :fechareserva")
    , @NamedQuery(name = "ReservaColgenProfesor.findByFecharetencion", query = "SELECT r FROM ReservaColgenProfesor r WHERE r.fecharetencion = :fecharetencion")
    , @NamedQuery(name = "ReservaColgenProfesor.findByFechalimitereserva", query = "SELECT r FROM ReservaColgenProfesor r WHERE r.fechalimitereserva = :fechalimitereserva")})
public class ReservaColgenProfesor implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codreservacolgenprof")
    private Integer codreservacolgenprof;
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
    private Bibliotecario_1 idbibliotecario;
    @JoinColumn(name = "codbarralibro", referencedColumnName = "codbarralibro")
    @ManyToOne(optional = false)
    private Libro codbarralibro;
    @JoinColumn(name = "idprofesor", referencedColumnName = "idprofesor")
    @ManyToOne(optional = false)
    private Profesor idprofesor;

    public ReservaColgenProfesor() {
    }

    public ReservaColgenProfesor(Integer codreservacolgenprof) {
        this.codreservacolgenprof = codreservacolgenprof;
    }

    public ReservaColgenProfesor(Integer codreservacolgenprof, Date fechareserva) {
        this.codreservacolgenprof = codreservacolgenprof;
        this.fechareserva = fechareserva;
    }

    public Integer getCodreservacolgenprof() {
        return codreservacolgenprof;
    }

    public void setCodreservacolgenprof(Integer codreservacolgenprof) {
        this.codreservacolgenprof = codreservacolgenprof;
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

    public Bibliotecario_1 getIdbibliotecario() {
        return idbibliotecario;
    }

    public void setIdbibliotecario(Bibliotecario_1 idbibliotecario) {
        this.idbibliotecario = idbibliotecario;
    }

    public Libro getCodbarralibro() {
        return codbarralibro;
    }

    public void setCodbarralibro(Libro codbarralibro) {
        this.codbarralibro = codbarralibro;
    }

    public Profesor getIdprofesor() {
        return idprofesor;
    }

    public void setIdprofesor(Profesor idprofesor) {
        this.idprofesor = idprofesor;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codreservacolgenprof != null ? codreservacolgenprof.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ReservaColgenProfesor)) {
            return false;
        }
        ReservaColgenProfesor other = (ReservaColgenProfesor) object;
        if ((this.codreservacolgenprof == null && other.codreservacolgenprof != null) || (this.codreservacolgenprof != null && !this.codreservacolgenprof.equals(other.codreservacolgenprof))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entitysRecursos.ReservaColgenProfesor[ codreservacolgenprof=" + codreservacolgenprof + " ]";
    }
    
}

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
@Table(name = "multa_revista_profesor")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MultaRevistaProfesor.findAll", query = "SELECT m FROM MultaRevistaProfesor m")
    , @NamedQuery(name = "MultaRevistaProfesor.findByCodmultarevistaprof", query = "SELECT m FROM MultaRevistaProfesor m WHERE m.codmultarevistaprof = :codmultarevistaprof")
    , @NamedQuery(name = "MultaRevistaProfesor.findByDiasatrasados", query = "SELECT m FROM MultaRevistaProfesor m WHERE m.diasatrasados = :diasatrasados")
    , @NamedQuery(name = "MultaRevistaProfesor.findByValortotalmulta", query = "SELECT m FROM MultaRevistaProfesor m WHERE m.valortotalmulta = :valortotalmulta")
    , @NamedQuery(name = "MultaRevistaProfesor.findByEstadocancelacion", query = "SELECT m FROM MultaRevistaProfesor m WHERE m.estadocancelacion = :estadocancelacion")
    , @NamedQuery(name = "MultaRevistaProfesor.findByDescripcioncancelacion", query = "SELECT m FROM MultaRevistaProfesor m WHERE m.descripcioncancelacion = :descripcioncancelacion")
    , @NamedQuery(name = "MultaRevistaProfesor.findByFechamulta", query = "SELECT m FROM MultaRevistaProfesor m WHERE m.fechamulta = :fechamulta")})
public class MultaRevistaProfesor implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codmultarevistaprof")
    private Integer codmultarevistaprof;
    @Basic(optional = false)
    @Column(name = "diasatrasados")
    private int diasatrasados;
    @Basic(optional = false)
    @Column(name = "valortotalmulta")
    private int valortotalmulta;
    @Basic(optional = false)
    @Column(name = "estadocancelacion")
    private String estadocancelacion;
    @Basic(optional = false)
    @Column(name = "descripcioncancelacion")
    private String descripcioncancelacion;
    @Basic(optional = false)
    @Column(name = "fechamulta")
    @Temporal(TemporalType.DATE)
    private Date fechamulta;
    @JoinColumn(name = "codpreciomulta", referencedColumnName = "codpreciomulta")
    @ManyToOne(optional = false)
    private ControlPrecioMulta codpreciomulta;
    @JoinColumn(name = "codprestrevistaprof", referencedColumnName = "codprestrevistaprof")
    @ManyToOne(optional = false)
    private PrestamoRevistaProfesor codprestrevistaprof;

    public MultaRevistaProfesor() {
    }

    public MultaRevistaProfesor(Integer codmultarevistaprof) {
        this.codmultarevistaprof = codmultarevistaprof;
    }

    public MultaRevistaProfesor(Integer codmultarevistaprof, int diasatrasados, int valortotalmulta, String estadocancelacion, String descripcioncancelacion, Date fechamulta) {
        this.codmultarevistaprof = codmultarevistaprof;
        this.diasatrasados = diasatrasados;
        this.valortotalmulta = valortotalmulta;
        this.estadocancelacion = estadocancelacion;
        this.descripcioncancelacion = descripcioncancelacion;
        this.fechamulta = fechamulta;
    }

    public Integer getCodmultarevistaprof() {
        return codmultarevistaprof;
    }

    public void setCodmultarevistaprof(Integer codmultarevistaprof) {
        this.codmultarevistaprof = codmultarevistaprof;
    }

    public int getDiasatrasados() {
        return diasatrasados;
    }

    public void setDiasatrasados(int diasatrasados) {
        this.diasatrasados = diasatrasados;
    }

    public int getValortotalmulta() {
        return valortotalmulta;
    }

    public void setValortotalmulta(int valortotalmulta) {
        this.valortotalmulta = valortotalmulta;
    }

    public String getEstadocancelacion() {
        return estadocancelacion;
    }

    public void setEstadocancelacion(String estadocancelacion) {
        this.estadocancelacion = estadocancelacion;
    }

    public String getDescripcioncancelacion() {
        return descripcioncancelacion;
    }

    public void setDescripcioncancelacion(String descripcioncancelacion) {
        this.descripcioncancelacion = descripcioncancelacion;
    }

    public Date getFechamulta() {
        return fechamulta;
    }

    public void setFechamulta(Date fechamulta) {
        this.fechamulta = fechamulta;
    }

    public ControlPrecioMulta getCodpreciomulta() {
        return codpreciomulta;
    }

    public void setCodpreciomulta(ControlPrecioMulta codpreciomulta) {
        this.codpreciomulta = codpreciomulta;
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
        hash += (codmultarevistaprof != null ? codmultarevistaprof.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MultaRevistaProfesor)) {
            return false;
        }
        MultaRevistaProfesor other = (MultaRevistaProfesor) object;
        if ((this.codmultarevistaprof == null && other.codmultarevistaprof != null) || (this.codmultarevistaprof != null && !this.codmultarevistaprof.equals(other.codmultarevistaprof))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entitys.MultaRevistaProfesor[ codmultarevistaprof=" + codmultarevistaprof + " ]";
    }
    
}

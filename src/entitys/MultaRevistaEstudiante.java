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
@Table(name = "multa_revista_estudiante")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MultaRevistaEstudiante.findAll", query = "SELECT m FROM MultaRevistaEstudiante m")
    , @NamedQuery(name = "MultaRevistaEstudiante.findByCodmultarevistaest", query = "SELECT m FROM MultaRevistaEstudiante m WHERE m.codmultarevistaest = :codmultarevistaest")
    , @NamedQuery(name = "MultaRevistaEstudiante.findByDiasatrasados", query = "SELECT m FROM MultaRevistaEstudiante m WHERE m.diasatrasados = :diasatrasados")
    , @NamedQuery(name = "MultaRevistaEstudiante.findByValortotalmulta", query = "SELECT m FROM MultaRevistaEstudiante m WHERE m.valortotalmulta = :valortotalmulta")
    , @NamedQuery(name = "MultaRevistaEstudiante.findByEstadocancelacion", query = "SELECT m FROM MultaRevistaEstudiante m WHERE m.estadocancelacion = :estadocancelacion")
    , @NamedQuery(name = "MultaRevistaEstudiante.findByDescripcioncancelacion", query = "SELECT m FROM MultaRevistaEstudiante m WHERE m.descripcioncancelacion = :descripcioncancelacion")
    , @NamedQuery(name = "MultaRevistaEstudiante.findByFechamulta", query = "SELECT m FROM MultaRevistaEstudiante m WHERE m.fechamulta = :fechamulta")})
public class MultaRevistaEstudiante implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codmultarevistaest")
    private Integer codmultarevistaest;
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
    @JoinColumn(name = "codprestrevistaest", referencedColumnName = "codprestrevistaest")
    @ManyToOne(optional = false)
    private PrestamoRevistaEstudiante codprestrevistaest;

    public MultaRevistaEstudiante() {
    }

    public MultaRevistaEstudiante(Integer codmultarevistaest) {
        this.codmultarevistaest = codmultarevistaest;
    }

    public MultaRevistaEstudiante(Integer codmultarevistaest, int diasatrasados, int valortotalmulta, String estadocancelacion, String descripcioncancelacion, Date fechamulta) {
        this.codmultarevistaest = codmultarevistaest;
        this.diasatrasados = diasatrasados;
        this.valortotalmulta = valortotalmulta;
        this.estadocancelacion = estadocancelacion;
        this.descripcioncancelacion = descripcioncancelacion;
        this.fechamulta = fechamulta;
    }

    public Integer getCodmultarevistaest() {
        return codmultarevistaest;
    }

    public void setCodmultarevistaest(Integer codmultarevistaest) {
        this.codmultarevistaest = codmultarevistaest;
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

    public PrestamoRevistaEstudiante getCodprestrevistaest() {
        return codprestrevistaest;
    }

    public void setCodprestrevistaest(PrestamoRevistaEstudiante codprestrevistaest) {
        this.codprestrevistaest = codprestrevistaest;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codmultarevistaest != null ? codmultarevistaest.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MultaRevistaEstudiante)) {
            return false;
        }
        MultaRevistaEstudiante other = (MultaRevistaEstudiante) object;
        if ((this.codmultarevistaest == null && other.codmultarevistaest != null) || (this.codmultarevistaest != null && !this.codmultarevistaest.equals(other.codmultarevistaest))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entitys.MultaRevistaEstudiante[ codmultarevistaest=" + codmultarevistaest + " ]";
    }
    
}

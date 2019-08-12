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
@Table(name = "multa_enciclopedia_estudiante")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MultaEnciclopediaEstudiante.findAll", query = "SELECT m FROM MultaEnciclopediaEstudiante m")
    , @NamedQuery(name = "MultaEnciclopediaEstudiante.findByCodmultaencest", query = "SELECT m FROM MultaEnciclopediaEstudiante m WHERE m.codmultaencest = :codmultaencest")
    , @NamedQuery(name = "MultaEnciclopediaEstudiante.findByDiasatrasados", query = "SELECT m FROM MultaEnciclopediaEstudiante m WHERE m.diasatrasados = :diasatrasados")
    , @NamedQuery(name = "MultaEnciclopediaEstudiante.findByValortotalmulta", query = "SELECT m FROM MultaEnciclopediaEstudiante m WHERE m.valortotalmulta = :valortotalmulta")
    , @NamedQuery(name = "MultaEnciclopediaEstudiante.findByEstadocancelacion", query = "SELECT m FROM MultaEnciclopediaEstudiante m WHERE m.estadocancelacion = :estadocancelacion")
    , @NamedQuery(name = "MultaEnciclopediaEstudiante.findByDescripcioncancelacion", query = "SELECT m FROM MultaEnciclopediaEstudiante m WHERE m.descripcioncancelacion = :descripcioncancelacion")
    , @NamedQuery(name = "MultaEnciclopediaEstudiante.findByFechamulta", query = "SELECT m FROM MultaEnciclopediaEstudiante m WHERE m.fechamulta = :fechamulta")})
public class MultaEnciclopediaEstudiante implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codmultaencest")
    private Integer codmultaencest;
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
    @JoinColumn(name = "codprestencest", referencedColumnName = "codprestencest")
    @ManyToOne(optional = false)
    private PrestamoEnciclopediaEstudiante codprestencest;

    public MultaEnciclopediaEstudiante() {
    }

    public MultaEnciclopediaEstudiante(Integer codmultaencest) {
        this.codmultaencest = codmultaencest;
    }

    public MultaEnciclopediaEstudiante(Integer codmultaencest, int diasatrasados, int valortotalmulta, String estadocancelacion, String descripcioncancelacion, Date fechamulta) {
        this.codmultaencest = codmultaencest;
        this.diasatrasados = diasatrasados;
        this.valortotalmulta = valortotalmulta;
        this.estadocancelacion = estadocancelacion;
        this.descripcioncancelacion = descripcioncancelacion;
        this.fechamulta = fechamulta;
    }

    public Integer getCodmultaencest() {
        return codmultaencest;
    }

    public void setCodmultaencest(Integer codmultaencest) {
        this.codmultaencest = codmultaencest;
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

    public PrestamoEnciclopediaEstudiante getCodprestencest() {
        return codprestencest;
    }

    public void setCodprestencest(PrestamoEnciclopediaEstudiante codprestencest) {
        this.codprestencest = codprestencest;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codmultaencest != null ? codmultaencest.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MultaEnciclopediaEstudiante)) {
            return false;
        }
        MultaEnciclopediaEstudiante other = (MultaEnciclopediaEstudiante) object;
        if ((this.codmultaencest == null && other.codmultaencest != null) || (this.codmultaencest != null && !this.codmultaencest.equals(other.codmultaencest))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entitys.MultaEnciclopediaEstudiante[ codmultaencest=" + codmultaencest + " ]";
    }
    
}

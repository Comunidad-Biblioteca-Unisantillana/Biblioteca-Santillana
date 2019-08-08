/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entitys;

import java.io.Serializable;
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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Storkolm
 */
@Entity
@Table(name = "multa_mapa_estudiante")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MultaMapaEstudiante.findAll", query = "SELECT m FROM MultaMapaEstudiante m")
    , @NamedQuery(name = "MultaMapaEstudiante.findByCodmultamapaest", query = "SELECT m FROM MultaMapaEstudiante m WHERE m.codmultamapaest = :codmultamapaest")
    , @NamedQuery(name = "MultaMapaEstudiante.findByDiasatrasados", query = "SELECT m FROM MultaMapaEstudiante m WHERE m.diasatrasados = :diasatrasados")
    , @NamedQuery(name = "MultaMapaEstudiante.findByValortotalmulta", query = "SELECT m FROM MultaMapaEstudiante m WHERE m.valortotalmulta = :valortotalmulta")
    , @NamedQuery(name = "MultaMapaEstudiante.findByEstadocancelacion", query = "SELECT m FROM MultaMapaEstudiante m WHERE m.estadocancelacion = :estadocancelacion")
    , @NamedQuery(name = "MultaMapaEstudiante.findByDescripcioncancelacion", query = "SELECT m FROM MultaMapaEstudiante m WHERE m.descripcioncancelacion = :descripcioncancelacion")})
public class MultaMapaEstudiante implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codmultamapaest")
    private Integer codmultamapaest;
    @Basic(optional = false)
    @Column(name = "diasatrasados")
    private int diasatrasados;
    @Basic(optional = false)
    @Column(name = "valortotalmulta")
    private int valortotalmulta;
    @Basic(optional = false)
    @Column(name = "estadocancelacion")
    private String estadocancelacion;
    @Column(name = "descripcioncancelacion")
    private String descripcioncancelacion;
    @JoinColumn(name = "codpreciomulta", referencedColumnName = "codpreciomulta")
    @ManyToOne(optional = false)
    private ControlPrecioMulta codpreciomulta;
    @JoinColumn(name = "codprestmapaest", referencedColumnName = "codprestmapaest")
    @ManyToOne(optional = false)
    private PrestamoMapaEstudiante codprestmapaest;

    public MultaMapaEstudiante() {
    }

    public MultaMapaEstudiante(Integer codmultamapaest) {
        this.codmultamapaest = codmultamapaest;
    }

    public MultaMapaEstudiante(Integer codmultamapaest, int diasatrasados, int valortotalmulta, String estadocancelacion) {
        this.codmultamapaest = codmultamapaest;
        this.diasatrasados = diasatrasados;
        this.valortotalmulta = valortotalmulta;
        this.estadocancelacion = estadocancelacion;
    }

    public Integer getCodmultamapaest() {
        return codmultamapaest;
    }

    public void setCodmultamapaest(Integer codmultamapaest) {
        this.codmultamapaest = codmultamapaest;
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

    public ControlPrecioMulta getCodpreciomulta() {
        return codpreciomulta;
    }

    public void setCodpreciomulta(ControlPrecioMulta codpreciomulta) {
        this.codpreciomulta = codpreciomulta;
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
        hash += (codmultamapaest != null ? codmultamapaest.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MultaMapaEstudiante)) {
            return false;
        }
        MultaMapaEstudiante other = (MultaMapaEstudiante) object;
        if ((this.codmultamapaest == null && other.codmultamapaest != null) || (this.codmultamapaest != null && !this.codmultamapaest.equals(other.codmultamapaest))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entitysRecursos.MultaMapaEstudiante[ codmultamapaest=" + codmultamapaest + " ]";
    }
    
}

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
@Table(name = "multa_periodico_estudiante")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MultaPeriodicoEstudiante.findAll", query = "SELECT m FROM MultaPeriodicoEstudiante m")
    , @NamedQuery(name = "MultaPeriodicoEstudiante.findByCodmultaperiodicoest", query = "SELECT m FROM MultaPeriodicoEstudiante m WHERE m.codmultaperiodicoest = :codmultaperiodicoest")
    , @NamedQuery(name = "MultaPeriodicoEstudiante.findByDiasatrasados", query = "SELECT m FROM MultaPeriodicoEstudiante m WHERE m.diasatrasados = :diasatrasados")
    , @NamedQuery(name = "MultaPeriodicoEstudiante.findByValortotalmulta", query = "SELECT m FROM MultaPeriodicoEstudiante m WHERE m.valortotalmulta = :valortotalmulta")
    , @NamedQuery(name = "MultaPeriodicoEstudiante.findByEstadocancelacion", query = "SELECT m FROM MultaPeriodicoEstudiante m WHERE m.estadocancelacion = :estadocancelacion")
    , @NamedQuery(name = "MultaPeriodicoEstudiante.findByDescripcioncancelacion", query = "SELECT m FROM MultaPeriodicoEstudiante m WHERE m.descripcioncancelacion = :descripcioncancelacion")})
public class MultaPeriodicoEstudiante implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codmultaperiodicoest")
    private Integer codmultaperiodicoest;
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
    @JoinColumn(name = "codprestperiodicoest", referencedColumnName = "codprestperiodicoest")
    @ManyToOne(optional = false)
    private PrestamoPeriodicoEstudiante codprestperiodicoest;

    public MultaPeriodicoEstudiante() {
    }

    public MultaPeriodicoEstudiante(Integer codmultaperiodicoest) {
        this.codmultaperiodicoest = codmultaperiodicoest;
    }

    public MultaPeriodicoEstudiante(Integer codmultaperiodicoest, int diasatrasados, int valortotalmulta, String estadocancelacion) {
        this.codmultaperiodicoest = codmultaperiodicoest;
        this.diasatrasados = diasatrasados;
        this.valortotalmulta = valortotalmulta;
        this.estadocancelacion = estadocancelacion;
    }

    public Integer getCodmultaperiodicoest() {
        return codmultaperiodicoest;
    }

    public void setCodmultaperiodicoest(Integer codmultaperiodicoest) {
        this.codmultaperiodicoest = codmultaperiodicoest;
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

    public PrestamoPeriodicoEstudiante getCodprestperiodicoest() {
        return codprestperiodicoest;
    }

    public void setCodprestperiodicoest(PrestamoPeriodicoEstudiante codprestperiodicoest) {
        this.codprestperiodicoest = codprestperiodicoest;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codmultaperiodicoest != null ? codmultaperiodicoest.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MultaPeriodicoEstudiante)) {
            return false;
        }
        MultaPeriodicoEstudiante other = (MultaPeriodicoEstudiante) object;
        if ((this.codmultaperiodicoest == null && other.codmultaperiodicoest != null) || (this.codmultaperiodicoest != null && !this.codmultaperiodicoest.equals(other.codmultaperiodicoest))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entitysRecursos.MultaPeriodicoEstudiante[ codmultaperiodicoest=" + codmultaperiodicoest + " ]";
    }
    
}

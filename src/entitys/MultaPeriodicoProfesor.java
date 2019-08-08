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
@Table(name = "multa_periodico_profesor")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MultaPeriodicoProfesor.findAll", query = "SELECT m FROM MultaPeriodicoProfesor m")
    , @NamedQuery(name = "MultaPeriodicoProfesor.findByCodmultaperiodicoprof", query = "SELECT m FROM MultaPeriodicoProfesor m WHERE m.codmultaperiodicoprof = :codmultaperiodicoprof")
    , @NamedQuery(name = "MultaPeriodicoProfesor.findByDiasatrasados", query = "SELECT m FROM MultaPeriodicoProfesor m WHERE m.diasatrasados = :diasatrasados")
    , @NamedQuery(name = "MultaPeriodicoProfesor.findByValortotalmulta", query = "SELECT m FROM MultaPeriodicoProfesor m WHERE m.valortotalmulta = :valortotalmulta")
    , @NamedQuery(name = "MultaPeriodicoProfesor.findByEstadocancelacion", query = "SELECT m FROM MultaPeriodicoProfesor m WHERE m.estadocancelacion = :estadocancelacion")
    , @NamedQuery(name = "MultaPeriodicoProfesor.findByDescripcioncancelacion", query = "SELECT m FROM MultaPeriodicoProfesor m WHERE m.descripcioncancelacion = :descripcioncancelacion")})
public class MultaPeriodicoProfesor implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codmultaperiodicoprof")
    private Integer codmultaperiodicoprof;
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
    @JoinColumn(name = "codprestperiodicoprof", referencedColumnName = "codprestperiodicoprof")
    @ManyToOne(optional = false)
    private PrestamoPeriodicoProfesor codprestperiodicoprof;

    public MultaPeriodicoProfesor() {
    }

    public MultaPeriodicoProfesor(Integer codmultaperiodicoprof) {
        this.codmultaperiodicoprof = codmultaperiodicoprof;
    }

    public MultaPeriodicoProfesor(Integer codmultaperiodicoprof, int diasatrasados, int valortotalmulta, String estadocancelacion) {
        this.codmultaperiodicoprof = codmultaperiodicoprof;
        this.diasatrasados = diasatrasados;
        this.valortotalmulta = valortotalmulta;
        this.estadocancelacion = estadocancelacion;
    }

    public Integer getCodmultaperiodicoprof() {
        return codmultaperiodicoprof;
    }

    public void setCodmultaperiodicoprof(Integer codmultaperiodicoprof) {
        this.codmultaperiodicoprof = codmultaperiodicoprof;
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

    public PrestamoPeriodicoProfesor getCodprestperiodicoprof() {
        return codprestperiodicoprof;
    }

    public void setCodprestperiodicoprof(PrestamoPeriodicoProfesor codprestperiodicoprof) {
        this.codprestperiodicoprof = codprestperiodicoprof;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codmultaperiodicoprof != null ? codmultaperiodicoprof.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MultaPeriodicoProfesor)) {
            return false;
        }
        MultaPeriodicoProfesor other = (MultaPeriodicoProfesor) object;
        if ((this.codmultaperiodicoprof == null && other.codmultaperiodicoprof != null) || (this.codmultaperiodicoprof != null && !this.codmultaperiodicoprof.equals(other.codmultaperiodicoprof))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entitysRecursos.MultaPeriodicoProfesor[ codmultaperiodicoprof=" + codmultaperiodicoprof + " ]";
    }
    
}

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
@Table(name = "multa_enciclopedia_profesor")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MultaEnciclopediaProfesor.findAll", query = "SELECT m FROM MultaEnciclopediaProfesor m")
    , @NamedQuery(name = "MultaEnciclopediaProfesor.findByCodmultaencprof", query = "SELECT m FROM MultaEnciclopediaProfesor m WHERE m.codmultaencprof = :codmultaencprof")
    , @NamedQuery(name = "MultaEnciclopediaProfesor.findByDiasatrasados", query = "SELECT m FROM MultaEnciclopediaProfesor m WHERE m.diasatrasados = :diasatrasados")
    , @NamedQuery(name = "MultaEnciclopediaProfesor.findByValortotalmulta", query = "SELECT m FROM MultaEnciclopediaProfesor m WHERE m.valortotalmulta = :valortotalmulta")
    , @NamedQuery(name = "MultaEnciclopediaProfesor.findByEstadocancelacion", query = "SELECT m FROM MultaEnciclopediaProfesor m WHERE m.estadocancelacion = :estadocancelacion")
    , @NamedQuery(name = "MultaEnciclopediaProfesor.findByDescripcioncancelacion", query = "SELECT m FROM MultaEnciclopediaProfesor m WHERE m.descripcioncancelacion = :descripcioncancelacion")})
public class MultaEnciclopediaProfesor implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codmultaencprof")
    private Integer codmultaencprof;
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
    @JoinColumn(name = "codprestencprof", referencedColumnName = "codprestencprof")
    @ManyToOne(optional = false)
    private PrestamoEnciclopediaProfesor codprestencprof;

    public MultaEnciclopediaProfesor() {
    }

    public MultaEnciclopediaProfesor(Integer codmultaencprof) {
        this.codmultaencprof = codmultaencprof;
    }

    public MultaEnciclopediaProfesor(Integer codmultaencprof, int diasatrasados, int valortotalmulta, String estadocancelacion) {
        this.codmultaencprof = codmultaencprof;
        this.diasatrasados = diasatrasados;
        this.valortotalmulta = valortotalmulta;
        this.estadocancelacion = estadocancelacion;
    }

    public Integer getCodmultaencprof() {
        return codmultaencprof;
    }

    public void setCodmultaencprof(Integer codmultaencprof) {
        this.codmultaencprof = codmultaencprof;
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

    public PrestamoEnciclopediaProfesor getCodprestencprof() {
        return codprestencprof;
    }

    public void setCodprestencprof(PrestamoEnciclopediaProfesor codprestencprof) {
        this.codprestencprof = codprestencprof;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codmultaencprof != null ? codmultaencprof.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MultaEnciclopediaProfesor)) {
            return false;
        }
        MultaEnciclopediaProfesor other = (MultaEnciclopediaProfesor) object;
        if ((this.codmultaencprof == null && other.codmultaencprof != null) || (this.codmultaencprof != null && !this.codmultaencprof.equals(other.codmultaencprof))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entitysRecursos.MultaEnciclopediaProfesor[ codmultaencprof=" + codmultaencprof + " ]";
    }
    
}

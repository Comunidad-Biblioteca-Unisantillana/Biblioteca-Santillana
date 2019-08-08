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
@Table(name = "multa_mapa_profesor")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MultaMapaProfesor.findAll", query = "SELECT m FROM MultaMapaProfesor m")
    , @NamedQuery(name = "MultaMapaProfesor.findByCodmultamapaprof", query = "SELECT m FROM MultaMapaProfesor m WHERE m.codmultamapaprof = :codmultamapaprof")
    , @NamedQuery(name = "MultaMapaProfesor.findByDiasatrasados", query = "SELECT m FROM MultaMapaProfesor m WHERE m.diasatrasados = :diasatrasados")
    , @NamedQuery(name = "MultaMapaProfesor.findByValortotalmulta", query = "SELECT m FROM MultaMapaProfesor m WHERE m.valortotalmulta = :valortotalmulta")
    , @NamedQuery(name = "MultaMapaProfesor.findByEstadocancelacion", query = "SELECT m FROM MultaMapaProfesor m WHERE m.estadocancelacion = :estadocancelacion")
    , @NamedQuery(name = "MultaMapaProfesor.findByDescripcioncancelacion", query = "SELECT m FROM MultaMapaProfesor m WHERE m.descripcioncancelacion = :descripcioncancelacion")})
public class MultaMapaProfesor implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codmultamapaprof")
    private Integer codmultamapaprof;
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
    @JoinColumn(name = "codprestmapaprof", referencedColumnName = "codprestmapaprof")
    @ManyToOne(optional = false)
    private PrestamoMapaProfesor codprestmapaprof;

    public MultaMapaProfesor() {
    }

    public MultaMapaProfesor(Integer codmultamapaprof) {
        this.codmultamapaprof = codmultamapaprof;
    }

    public MultaMapaProfesor(Integer codmultamapaprof, int diasatrasados, int valortotalmulta, String estadocancelacion) {
        this.codmultamapaprof = codmultamapaprof;
        this.diasatrasados = diasatrasados;
        this.valortotalmulta = valortotalmulta;
        this.estadocancelacion = estadocancelacion;
    }

    public Integer getCodmultamapaprof() {
        return codmultamapaprof;
    }

    public void setCodmultamapaprof(Integer codmultamapaprof) {
        this.codmultamapaprof = codmultamapaprof;
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

    public PrestamoMapaProfesor getCodprestmapaprof() {
        return codprestmapaprof;
    }

    public void setCodprestmapaprof(PrestamoMapaProfesor codprestmapaprof) {
        this.codprestmapaprof = codprestmapaprof;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codmultamapaprof != null ? codmultamapaprof.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MultaMapaProfesor)) {
            return false;
        }
        MultaMapaProfesor other = (MultaMapaProfesor) object;
        if ((this.codmultamapaprof == null && other.codmultamapaprof != null) || (this.codmultamapaprof != null && !this.codmultamapaprof.equals(other.codmultamapaprof))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entitysRecursos.MultaMapaProfesor[ codmultamapaprof=" + codmultamapaprof + " ]";
    }
    
}

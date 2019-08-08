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
@Table(name = "multa_diccionario_profesor")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MultaDiccionarioProfesor.findAll", query = "SELECT m FROM MultaDiccionarioProfesor m")
    , @NamedQuery(name = "MultaDiccionarioProfesor.findByCodmultadicprof", query = "SELECT m FROM MultaDiccionarioProfesor m WHERE m.codmultadicprof = :codmultadicprof")
    , @NamedQuery(name = "MultaDiccionarioProfesor.findByDiasatrasados", query = "SELECT m FROM MultaDiccionarioProfesor m WHERE m.diasatrasados = :diasatrasados")
    , @NamedQuery(name = "MultaDiccionarioProfesor.findByValortotalmulta", query = "SELECT m FROM MultaDiccionarioProfesor m WHERE m.valortotalmulta = :valortotalmulta")
    , @NamedQuery(name = "MultaDiccionarioProfesor.findByEstadocancelacion", query = "SELECT m FROM MultaDiccionarioProfesor m WHERE m.estadocancelacion = :estadocancelacion")
    , @NamedQuery(name = "MultaDiccionarioProfesor.findByDescripcioncancelacion", query = "SELECT m FROM MultaDiccionarioProfesor m WHERE m.descripcioncancelacion = :descripcioncancelacion")})
public class MultaDiccionarioProfesor implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codmultadicprof")
    private Integer codmultadicprof;
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
    @JoinColumn(name = "codprestdicprof", referencedColumnName = "codprestdicprof")
    @ManyToOne(optional = false)
    private PrestamoDiccionarioProfesor codprestdicprof;

    public MultaDiccionarioProfesor() {
    }

    public MultaDiccionarioProfesor(Integer codmultadicprof) {
        this.codmultadicprof = codmultadicprof;
    }

    public MultaDiccionarioProfesor(Integer codmultadicprof, int diasatrasados, int valortotalmulta, String estadocancelacion) {
        this.codmultadicprof = codmultadicprof;
        this.diasatrasados = diasatrasados;
        this.valortotalmulta = valortotalmulta;
        this.estadocancelacion = estadocancelacion;
    }

    public Integer getCodmultadicprof() {
        return codmultadicprof;
    }

    public void setCodmultadicprof(Integer codmultadicprof) {
        this.codmultadicprof = codmultadicprof;
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

    public PrestamoDiccionarioProfesor getCodprestdicprof() {
        return codprestdicprof;
    }

    public void setCodprestdicprof(PrestamoDiccionarioProfesor codprestdicprof) {
        this.codprestdicprof = codprestdicprof;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codmultadicprof != null ? codmultadicprof.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MultaDiccionarioProfesor)) {
            return false;
        }
        MultaDiccionarioProfesor other = (MultaDiccionarioProfesor) object;
        if ((this.codmultadicprof == null && other.codmultadicprof != null) || (this.codmultadicprof != null && !this.codmultadicprof.equals(other.codmultadicprof))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entitysRecursos.MultaDiccionarioProfesor[ codmultadicprof=" + codmultadicprof + " ]";
    }
    
}

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
@Table(name = "multa_diccionario_estudiante")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MultaDiccionarioEstudiante.findAll", query = "SELECT m FROM MultaDiccionarioEstudiante m")
    , @NamedQuery(name = "MultaDiccionarioEstudiante.findByCodmultadicest", query = "SELECT m FROM MultaDiccionarioEstudiante m WHERE m.codmultadicest = :codmultadicest")
    , @NamedQuery(name = "MultaDiccionarioEstudiante.findByDiasatrasados", query = "SELECT m FROM MultaDiccionarioEstudiante m WHERE m.diasatrasados = :diasatrasados")
    , @NamedQuery(name = "MultaDiccionarioEstudiante.findByValortotalmulta", query = "SELECT m FROM MultaDiccionarioEstudiante m WHERE m.valortotalmulta = :valortotalmulta")
    , @NamedQuery(name = "MultaDiccionarioEstudiante.findByEstadocancelacion", query = "SELECT m FROM MultaDiccionarioEstudiante m WHERE m.estadocancelacion = :estadocancelacion")
    , @NamedQuery(name = "MultaDiccionarioEstudiante.findByDescripcioncancelacion", query = "SELECT m FROM MultaDiccionarioEstudiante m WHERE m.descripcioncancelacion = :descripcioncancelacion")
    , @NamedQuery(name = "MultaDiccionarioEstudiante.findByFechamulta", query = "SELECT m FROM MultaDiccionarioEstudiante m WHERE m.fechamulta = :fechamulta")})
public class MultaDiccionarioEstudiante implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codmultadicest")
    private Integer codmultadicest;
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
    @JoinColumn(name = "codprestdicest", referencedColumnName = "codprestdicest")
    @ManyToOne(optional = false)
    private PrestamoDiccionarioEstudiante codprestdicest;

    public MultaDiccionarioEstudiante() {
    }

    public MultaDiccionarioEstudiante(Integer codmultadicest) {
        this.codmultadicest = codmultadicest;
    }

    public MultaDiccionarioEstudiante(Integer codmultadicest, int diasatrasados, int valortotalmulta, String estadocancelacion, String descripcioncancelacion, Date fechamulta) {
        this.codmultadicest = codmultadicest;
        this.diasatrasados = diasatrasados;
        this.valortotalmulta = valortotalmulta;
        this.estadocancelacion = estadocancelacion;
        this.descripcioncancelacion = descripcioncancelacion;
        this.fechamulta = fechamulta;
    }

    public Integer getCodmultadicest() {
        return codmultadicest;
    }

    public void setCodmultadicest(Integer codmultadicest) {
        this.codmultadicest = codmultadicest;
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

    public PrestamoDiccionarioEstudiante getCodprestdicest() {
        return codprestdicest;
    }

    public void setCodprestdicest(PrestamoDiccionarioEstudiante codprestdicest) {
        this.codprestdicest = codprestdicest;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codmultadicest != null ? codmultadicest.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MultaDiccionarioEstudiante)) {
            return false;
        }
        MultaDiccionarioEstudiante other = (MultaDiccionarioEstudiante) object;
        if ((this.codmultadicest == null && other.codmultadicest != null) || (this.codmultadicest != null && !this.codmultadicest.equals(other.codmultadicest))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entitys.MultaDiccionarioEstudiante[ codmultadicest=" + codmultadicest + " ]";
    }
    
}

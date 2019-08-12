/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entitys;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "multa_libro_profesor")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MultaLibroProfesor.findAll", query = "SELECT m FROM MultaLibroProfesor m")
    , @NamedQuery(name = "MultaLibroProfesor.findByCodmultalibroprof", query = "SELECT m FROM MultaLibroProfesor m WHERE m.codmultalibroprof = :codmultalibroprof")
    , @NamedQuery(name = "MultaLibroProfesor.findByDiasatrasados", query = "SELECT m FROM MultaLibroProfesor m WHERE m.diasatrasados = :diasatrasados")
    , @NamedQuery(name = "MultaLibroProfesor.findByValortotalmulta", query = "SELECT m FROM MultaLibroProfesor m WHERE m.valortotalmulta = :valortotalmulta")
    , @NamedQuery(name = "MultaLibroProfesor.findByEstadocancelacion", query = "SELECT m FROM MultaLibroProfesor m WHERE m.estadocancelacion = :estadocancelacion")
    , @NamedQuery(name = "MultaLibroProfesor.findByDescripcioncancelacion", query = "SELECT m FROM MultaLibroProfesor m WHERE m.descripcioncancelacion = :descripcioncancelacion")
    , @NamedQuery(name = "MultaLibroProfesor.findByFechamulta", query = "SELECT m FROM MultaLibroProfesor m WHERE m.fechamulta = :fechamulta")})
public class MultaLibroProfesor implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codmultalibroprof")
    private Integer codmultalibroprof;
    @Basic(optional = false)
    @Column(name = "diasatrasados")
    private int diasatrasados;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "valortotalmulta")
    private BigDecimal valortotalmulta;
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
    @JoinColumn(name = "codprestlibroprof", referencedColumnName = "codprestlibroprof")
    @ManyToOne(optional = false)
    private PrestamoLibroProfesor codprestlibroprof;

    public MultaLibroProfesor() {
    }

    public MultaLibroProfesor(Integer codmultalibroprof) {
        this.codmultalibroprof = codmultalibroprof;
    }

    public MultaLibroProfesor(Integer codmultalibroprof, int diasatrasados, BigDecimal valortotalmulta, String estadocancelacion, String descripcioncancelacion, Date fechamulta) {
        this.codmultalibroprof = codmultalibroprof;
        this.diasatrasados = diasatrasados;
        this.valortotalmulta = valortotalmulta;
        this.estadocancelacion = estadocancelacion;
        this.descripcioncancelacion = descripcioncancelacion;
        this.fechamulta = fechamulta;
    }

    public Integer getCodmultalibroprof() {
        return codmultalibroprof;
    }

    public void setCodmultalibroprof(Integer codmultalibroprof) {
        this.codmultalibroprof = codmultalibroprof;
    }

    public int getDiasatrasados() {
        return diasatrasados;
    }

    public void setDiasatrasados(int diasatrasados) {
        this.diasatrasados = diasatrasados;
    }

    public BigDecimal getValortotalmulta() {
        return valortotalmulta;
    }

    public void setValortotalmulta(BigDecimal valortotalmulta) {
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

    public PrestamoLibroProfesor getCodprestlibroprof() {
        return codprestlibroprof;
    }

    public void setCodprestlibroprof(PrestamoLibroProfesor codprestlibroprof) {
        this.codprestlibroprof = codprestlibroprof;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codmultalibroprof != null ? codmultalibroprof.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MultaLibroProfesor)) {
            return false;
        }
        MultaLibroProfesor other = (MultaLibroProfesor) object;
        if ((this.codmultalibroprof == null && other.codmultalibroprof != null) || (this.codmultalibroprof != null && !this.codmultalibroprof.equals(other.codmultalibroprof))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entitys.MultaLibroProfesor[ codmultalibroprof=" + codmultalibroprof + " ]";
    }
    
}

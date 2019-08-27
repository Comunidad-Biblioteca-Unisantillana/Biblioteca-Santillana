/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moduloMulta.entitys;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Storkolm
 */
@Entity
@Table(name = "control_precio_multa")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ControlPrecioMulta.findAll", query = "SELECT c FROM ControlPrecioMulta c")
    , @NamedQuery(name = "ControlPrecioMulta.findByCodpreciomulta", query = "SELECT c FROM ControlPrecioMulta c WHERE c.codpreciomulta = :codpreciomulta")
    , @NamedQuery(name = "ControlPrecioMulta.findByValorpordia", query = "SELECT c FROM ControlPrecioMulta c WHERE c.valorpordia = :valorpordia")
    , @NamedQuery(name = "ControlPrecioMulta.findByFechaactualizacion", query = "SELECT c FROM ControlPrecioMulta c WHERE c.fechaactualizacion = :fechaactualizacion")})
public class ControlPrecioMulta implements Serializable {

    

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codpreciomulta")
    private Integer codpreciomulta;
    @Basic(optional = false)
    @Column(name = "valorpordia")
    private int valorpordia;
    @Basic(optional = false)
    @Column(name = "fechaactualizacion")
    @Temporal(TemporalType.DATE)
    private Date fechaactualizacion;

    public ControlPrecioMulta() {
    }

    public ControlPrecioMulta(Integer codpreciomulta) {
        this.codpreciomulta = codpreciomulta;
    }

    public ControlPrecioMulta(Integer codpreciomulta, int valorpordia, Date fechaactualizacion) {
        this.codpreciomulta = codpreciomulta;
        this.valorpordia = valorpordia;
        this.fechaactualizacion = fechaactualizacion;
    }

    public Integer getCodpreciomulta() {
        return codpreciomulta;
    }

    public void setCodpreciomulta(Integer codpreciomulta) {
        this.codpreciomulta = codpreciomulta;
    }

    public int getValorpordia() {
        return valorpordia;
    }

    public void setValorpordia(int valorpordia) {
        this.valorpordia = valorpordia;
    }

    public Date getFechaactualizacion() {
        return fechaactualizacion;
    }

    public void setFechaactualizacion(Date fechaactualizacion) {
        this.fechaactualizacion = fechaactualizacion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codpreciomulta != null ? codpreciomulta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ControlPrecioMulta)) {
            return false;
        }
        ControlPrecioMulta other = (ControlPrecioMulta) object;
        if ((this.codpreciomulta == null && other.codpreciomulta != null) || (this.codpreciomulta != null && !this.codpreciomulta.equals(other.codpreciomulta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entitys.ControlPrecioMulta[ codpreciomulta=" + codpreciomulta + " ]";
    }
    
}

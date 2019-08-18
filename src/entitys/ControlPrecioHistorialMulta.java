/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entitys;

import java.io.Serializable;
import java.sql.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Camilo
 */
@Entity
@Table(name = "control_precio_historial_multa")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ControlPrecioHistorialMulta.findAll", query = "SELECT c FROM ControlPrecioHistorialMulta c")
    , @NamedQuery(name = "ControlPrecioHistorialMulta.findByCodpreciohistmulta", query = "SELECT c FROM ControlPrecioHistorialMulta c WHERE c.codpreciohistmulta = :codpreciohistmulta")
    , @NamedQuery(name = "ControlPrecioHistorialMulta.findByValorpordia", query = "SELECT c FROM ControlPrecioHistorialMulta c WHERE c.valorpordia = :valorpordia")
    , @NamedQuery(name = "ControlPrecioHistorialMulta.findByFechaactualizacion", query = "SELECT c FROM ControlPrecioHistorialMulta c WHERE c.fechaactualizacion = :fechaactualizacion")})
public class ControlPrecioHistorialMulta implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codpreciohistmulta")
    private Integer codpreciohistmulta;
    @Basic(optional = false)
    @Column(name = "valorpordia")
    private int valorpordia;
    @Basic(optional = false)
    @Column(name = "fechaactualizacion")
    @Temporal(TemporalType.DATE)
    private Date fechaactualizacion;

    public ControlPrecioHistorialMulta() {
    }

    public ControlPrecioHistorialMulta(Integer codpreciohistmulta) {
        this.codpreciohistmulta = codpreciohistmulta;
    }

    public ControlPrecioHistorialMulta(int valorpordia, Date fechaactualizacion) {
        this.valorpordia = valorpordia;
        this.fechaactualizacion = fechaactualizacion;
    }

    public Integer getCodpreciohistmulta() {
        return codpreciohistmulta;
    }

    public void setCodpreciohistmulta(Integer codpreciohistmulta) {
        this.codpreciohistmulta = codpreciohistmulta;
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
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ControlPrecioHistorialMulta)) {
            return false;
        }
        ControlPrecioHistorialMulta other = (ControlPrecioHistorialMulta) object;
        if ((this.codpreciohistmulta == null && other.codpreciohistmulta != null) || (this.codpreciohistmulta != null && !this.codpreciohistmulta.equals(other.codpreciohistmulta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entitys.ControlPrecioHistorialMulta[ codpreciohistmulta=" + codpreciohistmulta + " ]";
    }
    
}

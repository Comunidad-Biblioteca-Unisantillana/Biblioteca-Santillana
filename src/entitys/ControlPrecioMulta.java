/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entitys;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codpreciomulta")
    private Collection<MultaRevistaProfesor> multaRevistaProfesorCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codpreciomulta")
    private Collection<MultaLibroEstudiante> multaLibroEstudianteCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codpreciomulta")
    private Collection<MultaMapaEstudiante> multaMapaEstudianteCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codpreciomulta")
    private Collection<MultaDiccionarioProfesor> multaDiccionarioProfesorCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codpreciomulta")
    private Collection<MultaEnciclopediaEstudiante> multaEnciclopediaEstudianteCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codpreciomulta")
    private Collection<MultaRevistaEstudiante> multaRevistaEstudianteCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codpreciomulta")
    private Collection<MultaMapaProfesor> multaMapaProfesorCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codpreciomulta")
    private Collection<MultaEnciclopediaProfesor> multaEnciclopediaProfesorCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codpreciomulta")
    private Collection<MultaPeriodicoProfesor> multaPeriodicoProfesorCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codpreciomulta")
    private Collection<MultaPeriodicoEstudiante> multaPeriodicoEstudianteCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codpreciomulta")
    private Collection<MultaLibroProfesor> multaLibroProfesorCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codpreciomulta")
    private Collection<MultaDiccionarioEstudiante> multaDiccionarioEstudianteCollection;

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

    @XmlTransient
    public Collection<MultaRevistaProfesor> getMultaRevistaProfesorCollection() {
        return multaRevistaProfesorCollection;
    }

    public void setMultaRevistaProfesorCollection(Collection<MultaRevistaProfesor> multaRevistaProfesorCollection) {
        this.multaRevistaProfesorCollection = multaRevistaProfesorCollection;
    }

    @XmlTransient
    public Collection<MultaLibroEstudiante> getMultaLibroEstudianteCollection() {
        return multaLibroEstudianteCollection;
    }

    public void setMultaLibroEstudianteCollection(Collection<MultaLibroEstudiante> multaLibroEstudianteCollection) {
        this.multaLibroEstudianteCollection = multaLibroEstudianteCollection;
    }

    @XmlTransient
    public Collection<MultaMapaEstudiante> getMultaMapaEstudianteCollection() {
        return multaMapaEstudianteCollection;
    }

    public void setMultaMapaEstudianteCollection(Collection<MultaMapaEstudiante> multaMapaEstudianteCollection) {
        this.multaMapaEstudianteCollection = multaMapaEstudianteCollection;
    }

    @XmlTransient
    public Collection<MultaDiccionarioProfesor> getMultaDiccionarioProfesorCollection() {
        return multaDiccionarioProfesorCollection;
    }

    public void setMultaDiccionarioProfesorCollection(Collection<MultaDiccionarioProfesor> multaDiccionarioProfesorCollection) {
        this.multaDiccionarioProfesorCollection = multaDiccionarioProfesorCollection;
    }

    @XmlTransient
    public Collection<MultaEnciclopediaEstudiante> getMultaEnciclopediaEstudianteCollection() {
        return multaEnciclopediaEstudianteCollection;
    }

    public void setMultaEnciclopediaEstudianteCollection(Collection<MultaEnciclopediaEstudiante> multaEnciclopediaEstudianteCollection) {
        this.multaEnciclopediaEstudianteCollection = multaEnciclopediaEstudianteCollection;
    }

    @XmlTransient
    public Collection<MultaRevistaEstudiante> getMultaRevistaEstudianteCollection() {
        return multaRevistaEstudianteCollection;
    }

    public void setMultaRevistaEstudianteCollection(Collection<MultaRevistaEstudiante> multaRevistaEstudianteCollection) {
        this.multaRevistaEstudianteCollection = multaRevistaEstudianteCollection;
    }

    @XmlTransient
    public Collection<MultaMapaProfesor> getMultaMapaProfesorCollection() {
        return multaMapaProfesorCollection;
    }

    public void setMultaMapaProfesorCollection(Collection<MultaMapaProfesor> multaMapaProfesorCollection) {
        this.multaMapaProfesorCollection = multaMapaProfesorCollection;
    }

    @XmlTransient
    public Collection<MultaEnciclopediaProfesor> getMultaEnciclopediaProfesorCollection() {
        return multaEnciclopediaProfesorCollection;
    }

    public void setMultaEnciclopediaProfesorCollection(Collection<MultaEnciclopediaProfesor> multaEnciclopediaProfesorCollection) {
        this.multaEnciclopediaProfesorCollection = multaEnciclopediaProfesorCollection;
    }

    @XmlTransient
    public Collection<MultaPeriodicoProfesor> getMultaPeriodicoProfesorCollection() {
        return multaPeriodicoProfesorCollection;
    }

    public void setMultaPeriodicoProfesorCollection(Collection<MultaPeriodicoProfesor> multaPeriodicoProfesorCollection) {
        this.multaPeriodicoProfesorCollection = multaPeriodicoProfesorCollection;
    }

    @XmlTransient
    public Collection<MultaPeriodicoEstudiante> getMultaPeriodicoEstudianteCollection() {
        return multaPeriodicoEstudianteCollection;
    }

    public void setMultaPeriodicoEstudianteCollection(Collection<MultaPeriodicoEstudiante> multaPeriodicoEstudianteCollection) {
        this.multaPeriodicoEstudianteCollection = multaPeriodicoEstudianteCollection;
    }

    @XmlTransient
    public Collection<MultaLibroProfesor> getMultaLibroProfesorCollection() {
        return multaLibroProfesorCollection;
    }

    public void setMultaLibroProfesorCollection(Collection<MultaLibroProfesor> multaLibroProfesorCollection) {
        this.multaLibroProfesorCollection = multaLibroProfesorCollection;
    }

    @XmlTransient
    public Collection<MultaDiccionarioEstudiante> getMultaDiccionarioEstudianteCollection() {
        return multaDiccionarioEstudianteCollection;
    }

    public void setMultaDiccionarioEstudianteCollection(Collection<MultaDiccionarioEstudiante> multaDiccionarioEstudianteCollection) {
        this.multaDiccionarioEstudianteCollection = multaDiccionarioEstudianteCollection;
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
        return "entitysRecursos.ControlPrecioMulta[ codpreciomulta=" + codpreciomulta + " ]";
    }
    
}

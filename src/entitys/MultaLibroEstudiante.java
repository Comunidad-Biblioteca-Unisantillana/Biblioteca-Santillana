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
@Table(name = "multa_libro_estudiante")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MultaLibroEstudiante.findAll", query = "SELECT m FROM MultaLibroEstudiante m")
    , @NamedQuery(name = "MultaLibroEstudiante.findByCodmultalibroest", query = "SELECT m FROM MultaLibroEstudiante m WHERE m.codmultalibroest = :codmultalibroest")
    , @NamedQuery(name = "MultaLibroEstudiante.findByDiasatrasados", query = "SELECT m FROM MultaLibroEstudiante m WHERE m.diasatrasados = :diasatrasados")
    , @NamedQuery(name = "MultaLibroEstudiante.findByValortotalmulta", query = "SELECT m FROM MultaLibroEstudiante m WHERE m.valortotalmulta = :valortotalmulta")
    , @NamedQuery(name = "MultaLibroEstudiante.findByEstadocancelacion", query = "SELECT m FROM MultaLibroEstudiante m WHERE m.estadocancelacion = :estadocancelacion")
    , @NamedQuery(name = "MultaLibroEstudiante.findByDescripcioncancelacion", query = "SELECT m FROM MultaLibroEstudiante m WHERE m.descripcioncancelacion = :descripcioncancelacion")})
public class MultaLibroEstudiante implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codmultalibroest")
    private Integer codmultalibroest;
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
    @JoinColumn(name = "codprestlibroest", referencedColumnName = "codprestlibroest")
    @ManyToOne(optional = false)
    private PrestamoLibroEstudiante codprestlibroest;

    public MultaLibroEstudiante() {
    }

    public MultaLibroEstudiante(Integer codmultalibroest) {
        this.codmultalibroest = codmultalibroest;
    }

    public MultaLibroEstudiante(Integer codmultalibroest, int diasatrasados, int valortotalmulta, String estadocancelacion) {
        this.codmultalibroest = codmultalibroest;
        this.diasatrasados = diasatrasados;
        this.valortotalmulta = valortotalmulta;
        this.estadocancelacion = estadocancelacion;
    }

    public Integer getCodmultalibroest() {
        return codmultalibroest;
    }

    public void setCodmultalibroest(Integer codmultalibroest) {
        this.codmultalibroest = codmultalibroest;
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

    public PrestamoLibroEstudiante getCodprestlibroest() {
        return codprestlibroest;
    }

    public void setCodprestlibroest(PrestamoLibroEstudiante codprestlibroest) {
        this.codprestlibroest = codprestlibroest;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codmultalibroest != null ? codmultalibroest.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MultaLibroEstudiante)) {
            return false;
        }
        MultaLibroEstudiante other = (MultaLibroEstudiante) object;
        if ((this.codmultalibroest == null && other.codmultalibroest != null) || (this.codmultalibroest != null && !this.codmultalibroest.equals(other.codmultalibroest))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entitysRecursos.MultaLibroEstudiante[ codmultalibroest=" + codmultalibroest + " ]";
    }
    
}

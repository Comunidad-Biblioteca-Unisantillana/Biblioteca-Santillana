/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entitysRecursos;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Camilo
 */
@Entity
@Table(name = "materia_por_enciclopedia")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MateriaPorEnciclopedia.findAll", query = "SELECT m FROM MateriaPorEnciclopedia m")
    , @NamedQuery(name = "MateriaPorEnciclopedia.findByCodmateriaenciclopedia", query = "SELECT m FROM MateriaPorEnciclopedia m WHERE m.codmateriaenciclopedia = :codmateriaenciclopedia")
    , @NamedQuery(name = "MateriaPorEnciclopedia.findByNombremateria", query = "SELECT m FROM MateriaPorEnciclopedia m WHERE m.nombremateria = :nombremateria")})
public class MateriaPorEnciclopedia implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "codmateriaenciclopedia")
    private String codmateriaenciclopedia;
    @Basic(optional = false)
    @Column(name = "nombremateria")
    private String nombremateria;
    @JoinColumn(name = "codbarraenciclopedia", referencedColumnName = "codbarraenciclopedia")
    @ManyToOne(optional = false)
    private Enciclopedia codbarraenciclopedia;

    public MateriaPorEnciclopedia() {
    }

    public MateriaPorEnciclopedia(String codmateriaenciclopedia) {
        this.codmateriaenciclopedia = codmateriaenciclopedia;
    }

    public MateriaPorEnciclopedia(String codmateriaenciclopedia, String nombremateria) {
        this.codmateriaenciclopedia = codmateriaenciclopedia;
        this.nombremateria = nombremateria;
    }

    public String getCodmateriaenciclopedia() {
        return codmateriaenciclopedia;
    }

    public void setCodmateriaenciclopedia(String codmateriaenciclopedia) {
        this.codmateriaenciclopedia = codmateriaenciclopedia;
    }

    public String getNombremateria() {
        return nombremateria;
    }

    public void setNombremateria(String nombremateria) {
        this.nombremateria = nombremateria;
    }

    public Enciclopedia getCodbarraenciclopedia() {
        return codbarraenciclopedia;
    }

    public void setCodbarraenciclopedia(Enciclopedia codbarraenciclopedia) {
        this.codbarraenciclopedia = codbarraenciclopedia;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codmateriaenciclopedia != null ? codmateriaenciclopedia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MateriaPorEnciclopedia)) {
            return false;
        }
        MateriaPorEnciclopedia other = (MateriaPorEnciclopedia) object;
        if ((this.codmateriaenciclopedia == null && other.codmateriaenciclopedia != null) || (this.codmateriaenciclopedia != null && !this.codmateriaenciclopedia.equals(other.codmateriaenciclopedia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entitys.MateriaPorEnciclopedia[ codmateriaenciclopedia=" + codmateriaenciclopedia + " ]";
    }
    
}

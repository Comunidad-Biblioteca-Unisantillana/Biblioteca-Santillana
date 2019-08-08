/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entitys;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Storkolm
 */
@Entity
@Table(name = "materia")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Materia.findAll", query = "SELECT m FROM Materia m")
    , @NamedQuery(name = "Materia.findByCodmateria", query = "SELECT m FROM Materia m WHERE m.codmateria = :codmateria")
    , @NamedQuery(name = "Materia.findByNombremateria", query = "SELECT m FROM Materia m WHERE m.nombremateria = :nombremateria")})
public class Materia implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "codmateria")
    private String codmateria;
    @Basic(optional = false)
    @Column(name = "nombremateria")
    private String nombremateria;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codmateria")
    private Collection<MateriaPorEnciclopedia> materiaPorEnciclopediaCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codmateria")
    private Collection<MateriaPorDiccionario> materiaPorDiccionarioCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codmateria")
    private Collection<MateriaPorLibro> materiaPorLibroCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codmateria")
    private Collection<MateriaPorRevista> materiaPorRevistaCollection;

    public Materia() {
    }

    public Materia(String codmateria) {
        this.codmateria = codmateria;
    }

    public Materia(String codmateria, String nombremateria) {
        this.codmateria = codmateria;
        this.nombremateria = nombremateria;
    }

    public String getCodmateria() {
        return codmateria;
    }

    public void setCodmateria(String codmateria) {
        this.codmateria = codmateria;
    }

    public String getNombremateria() {
        return nombremateria;
    }

    public void setNombremateria(String nombremateria) {
        this.nombremateria = nombremateria;
    }

    @XmlTransient
    public Collection<MateriaPorEnciclopedia> getMateriaPorEnciclopediaCollection() {
        return materiaPorEnciclopediaCollection;
    }

    public void setMateriaPorEnciclopediaCollection(Collection<MateriaPorEnciclopedia> materiaPorEnciclopediaCollection) {
        this.materiaPorEnciclopediaCollection = materiaPorEnciclopediaCollection;
    }

    @XmlTransient
    public Collection<MateriaPorDiccionario> getMateriaPorDiccionarioCollection() {
        return materiaPorDiccionarioCollection;
    }

    public void setMateriaPorDiccionarioCollection(Collection<MateriaPorDiccionario> materiaPorDiccionarioCollection) {
        this.materiaPorDiccionarioCollection = materiaPorDiccionarioCollection;
    }

    @XmlTransient
    public Collection<MateriaPorLibro> getMateriaPorLibroCollection() {
        return materiaPorLibroCollection;
    }

    public void setMateriaPorLibroCollection(Collection<MateriaPorLibro> materiaPorLibroCollection) {
        this.materiaPorLibroCollection = materiaPorLibroCollection;
    }

    @XmlTransient
    public Collection<MateriaPorRevista> getMateriaPorRevistaCollection() {
        return materiaPorRevistaCollection;
    }

    public void setMateriaPorRevistaCollection(Collection<MateriaPorRevista> materiaPorRevistaCollection) {
        this.materiaPorRevistaCollection = materiaPorRevistaCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codmateria != null ? codmateria.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Materia)) {
            return false;
        }
        Materia other = (Materia) object;
        if ((this.codmateria == null && other.codmateria != null) || (this.codmateria != null && !this.codmateria.equals(other.codmateria))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entitysRecursos.Materia[ codmateria=" + codmateria + " ]";
    }
    
}

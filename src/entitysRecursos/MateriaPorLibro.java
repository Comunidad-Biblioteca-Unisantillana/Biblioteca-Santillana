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
@Table(name = "materia_por_libro")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MateriaPorLibro.findAll", query = "SELECT m FROM MateriaPorLibro m")
    , @NamedQuery(name = "MateriaPorLibro.findByCodmaterialibro", query = "SELECT m FROM MateriaPorLibro m WHERE m.codmaterialibro = :codmaterialibro")
    , @NamedQuery(name = "MateriaPorLibro.findByNombremateria", query = "SELECT m FROM MateriaPorLibro m WHERE m.nombremateria = :nombremateria")})
public class MateriaPorLibro implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "codmaterialibro")
    private String codmaterialibro;
    @Basic(optional = false)
    @Column(name = "nombremateria")
    private String nombremateria;
    @JoinColumn(name = "codbarralibro", referencedColumnName = "codbarralibro")
    @ManyToOne(optional = false)
    private Libro codbarralibro;

    public MateriaPorLibro() {
    }

    public MateriaPorLibro(String codmaterialibro) {
        this.codmaterialibro = codmaterialibro;
    }

    public MateriaPorLibro(String codmaterialibro, String nombremateria) {
        this.codmaterialibro = codmaterialibro;
        this.nombremateria = nombremateria;
    }

    public String getCodmaterialibro() {
        return codmaterialibro;
    }

    public void setCodmaterialibro(String codmaterialibro) {
        this.codmaterialibro = codmaterialibro;
    }

    public String getNombremateria() {
        return nombremateria;
    }

    public void setNombremateria(String nombremateria) {
        this.nombremateria = nombremateria;
    }

    public Libro getCodbarralibro() {
        return codbarralibro;
    }

    public void setCodbarralibro(Libro codbarralibro) {
        this.codbarralibro = codbarralibro;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codmaterialibro != null ? codmaterialibro.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MateriaPorLibro)) {
            return false;
        }
        MateriaPorLibro other = (MateriaPorLibro) object;
        if ((this.codmaterialibro == null && other.codmaterialibro != null) || (this.codmaterialibro != null && !this.codmaterialibro.equals(other.codmaterialibro))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entitys.MateriaPorLibro[ codmaterialibro=" + codmaterialibro + " ]";
    }
    
}

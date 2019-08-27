package recursos.entitys;

import java.io.Serializable;
import java.util.List;
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
 * @author
 * @creado 
 * @author 
 * @modificado 
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
    private List<MateriaPorEnciclopedia> materiaPorEnciclopediaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codmateria")
    private List<MateriaPorDiccionario> materiaPorDiccionarioList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codmateria")
    private List<MateriaPorLibro> materiaPorLibroList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codmateria")
    private List<MateriaPorRevista> materiaPorRevistaList;

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
    public List<MateriaPorEnciclopedia> getMateriaPorEnciclopediaList() {
        return materiaPorEnciclopediaList;
    }

    public void setMateriaPorEnciclopediaList(List<MateriaPorEnciclopedia> materiaPorEnciclopediaList) {
        this.materiaPorEnciclopediaList = materiaPorEnciclopediaList;
    }

    @XmlTransient
    public List<MateriaPorDiccionario> getMateriaPorDiccionarioList() {
        return materiaPorDiccionarioList;
    }

    public void setMateriaPorDiccionarioList(List<MateriaPorDiccionario> materiaPorDiccionarioList) {
        this.materiaPorDiccionarioList = materiaPorDiccionarioList;
    }

    @XmlTransient
    public List<MateriaPorLibro> getMateriaPorLibroList() {
        return materiaPorLibroList;
    }

    public void setMateriaPorLibroList(List<MateriaPorLibro> materiaPorLibroList) {
        this.materiaPorLibroList = materiaPorLibroList;
    }

    @XmlTransient
    public List<MateriaPorRevista> getMateriaPorRevistaList() {
        return materiaPorRevistaList;
    }

    public void setMateriaPorRevistaList(List<MateriaPorRevista> materiaPorRevistaList) {
        this.materiaPorRevistaList = materiaPorRevistaList;
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
        return "entitys.Materia[ codmateria=" + codmateria + " ]";
    }
    
}

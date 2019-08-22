package recursos1.entitys;

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
 * @author
 * @creado 
 * @author 
 * @modificado 
 */
@Entity
@Table(name = "materia_por_diccionario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MateriaPorDiccionario.findAll", query = "SELECT m FROM MateriaPorDiccionario m")
    , @NamedQuery(name = "MateriaPorDiccionario.findByCodmateriadiccionario", query = "SELECT m FROM MateriaPorDiccionario m WHERE m.codmateriadiccionario = :codmateriadiccionario")})
public class MateriaPorDiccionario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "codmateriadiccionario")
    private String codmateriadiccionario;
    @JoinColumn(name = "codbarradiccionario", referencedColumnName = "codbarradiccionario")
    @ManyToOne(optional = false)
    private Diccionario codbarradiccionario;
    @JoinColumn(name = "codmateria", referencedColumnName = "codmateria")
    @ManyToOne(optional = false)
    private Materia codmateria;

    public MateriaPorDiccionario() {
    }

    public MateriaPorDiccionario(String codmateriadiccionario) {
        this.codmateriadiccionario = codmateriadiccionario;
    }

    public String getCodmateriadiccionario() {
        return codmateriadiccionario;
    }

    public void setCodmateriadiccionario(String codmateriadiccionario) {
        this.codmateriadiccionario = codmateriadiccionario;
    }

    public Diccionario getCodbarradiccionario() {
        return codbarradiccionario;
    }

    public void setCodbarradiccionario(Diccionario codbarradiccionario) {
        this.codbarradiccionario = codbarradiccionario;
    }

    public Materia getCodmateria() {
        return codmateria;
    }

    public void setCodmateria(Materia codmateria) {
        this.codmateria = codmateria;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codmateriadiccionario != null ? codmateriadiccionario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MateriaPorDiccionario)) {
            return false;
        }
        MateriaPorDiccionario other = (MateriaPorDiccionario) object;
        if ((this.codmateriadiccionario == null && other.codmateriadiccionario != null) || (this.codmateriadiccionario != null && !this.codmateriadiccionario.equals(other.codmateriadiccionario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entitys.MateriaPorDiccionario[ codmateriadiccionario=" + codmateriadiccionario + " ]";
    }
    
}

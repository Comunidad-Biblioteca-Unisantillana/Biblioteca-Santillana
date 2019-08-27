package recursos.entitys;

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
@Table(name = "materia_por_revista")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MateriaPorRevista.findAll", query = "SELECT m FROM MateriaPorRevista m")
    , @NamedQuery(name = "MateriaPorRevista.findByCodmateriarevista", query = "SELECT m FROM MateriaPorRevista m WHERE m.codmateriarevista = :codmateriarevista")})
public class MateriaPorRevista implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "codmateriarevista")
    private String codmateriarevista;
    @JoinColumn(name = "codmateria", referencedColumnName = "codmateria")
    @ManyToOne(optional = false)
    private Materia codmateria;
    @JoinColumn(name = "codbarrarevista", referencedColumnName = "codbarrarevista")
    @ManyToOne(optional = false)
    private Revista codbarrarevista;

    public MateriaPorRevista() {
    }

    public MateriaPorRevista(String codmateriarevista) {
        this.codmateriarevista = codmateriarevista;
    }

    public String getCodmateriarevista() {
        return codmateriarevista;
    }

    public void setCodmateriarevista(String codmateriarevista) {
        this.codmateriarevista = codmateriarevista;
    }

    public Materia getCodmateria() {
        return codmateria;
    }

    public void setCodmateria(Materia codmateria) {
        this.codmateria = codmateria;
    }

    public Revista getCodbarrarevista() {
        return codbarrarevista;
    }

    public void setCodbarrarevista(Revista codbarrarevista) {
        this.codbarrarevista = codbarrarevista;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codmateriarevista != null ? codmateriarevista.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MateriaPorRevista)) {
            return false;
        }
        MateriaPorRevista other = (MateriaPorRevista) object;
        if ((this.codmateriarevista == null && other.codmateriarevista != null) || (this.codmateriarevista != null && !this.codmateriarevista.equals(other.codmateriarevista))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entitys.MateriaPorRevista[ codmateriarevista=" + codmateriarevista + " ]";
    }
    
}

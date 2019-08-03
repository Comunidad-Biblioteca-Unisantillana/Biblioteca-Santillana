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
@Table(name = "autor_por_diccionario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AutorPorDiccionario.findAll", query = "SELECT a FROM AutorPorDiccionario a")
    , @NamedQuery(name = "AutorPorDiccionario.findByCodautdic", query = "SELECT a FROM AutorPorDiccionario a WHERE a.codautdic = :codautdic")})
public class AutorPorDiccionario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "codautdic")
    private String codautdic;
    @JoinColumn(name = "codautordiccionario", referencedColumnName = "codautordiccionario")
    @ManyToOne(optional = false)
    private AutorDiccionario codautordiccionario;
    @JoinColumn(name = "codbarradiccionario", referencedColumnName = "codbarradiccionario")
    @ManyToOne(optional = false)
    private Diccionario codbarradiccionario;

    public AutorPorDiccionario() {
    }

    public AutorPorDiccionario(String codautdic) {
        this.codautdic = codautdic;
    }

    public String getCodautdic() {
        return codautdic;
    }

    public void setCodautdic(String codautdic) {
        this.codautdic = codautdic;
    }

    public AutorDiccionario getCodautordiccionario() {
        return codautordiccionario;
    }

    public void setCodautordiccionario(AutorDiccionario codautordiccionario) {
        this.codautordiccionario = codautordiccionario;
    }

    public Diccionario getCodbarradiccionario() {
        return codbarradiccionario;
    }

    public void setCodbarradiccionario(Diccionario codbarradiccionario) {
        this.codbarradiccionario = codbarradiccionario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codautdic != null ? codautdic.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AutorPorDiccionario)) {
            return false;
        }
        AutorPorDiccionario other = (AutorPorDiccionario) object;
        if ((this.codautdic == null && other.codautdic != null) || (this.codautdic != null && !this.codautdic.equals(other.codautdic))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entitys.AutorPorDiccionario[ codautdic=" + codautdic + " ]";
    }
    
}

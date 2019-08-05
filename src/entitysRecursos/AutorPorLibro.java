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
@Table(name = "autor_por_libro")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AutorPorLibro.findAll", query = "SELECT a FROM AutorPorLibro a")
    , @NamedQuery(name = "AutorPorLibro.findByCodautlib", query = "SELECT a FROM AutorPorLibro a WHERE a.codautlib = :codautlib")})
public class AutorPorLibro implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "codautlib")
    private String codautlib;
    @JoinColumn(name = "codautorlibro", referencedColumnName = "codautorlibro")
    @ManyToOne(optional = false)
    private AutorLibro codautorlibro;
    @JoinColumn(name = "codbarralibro", referencedColumnName = "codbarralibro")
    @ManyToOne(optional = false)
    private Libro codbarralibro;

    public AutorPorLibro() {
    }

    public AutorPorLibro(String codautlib) {
        this.codautlib = codautlib;
    }

    public String getCodautlib() {
        return codautlib;
    }

    public void setCodautlib(String codautlib) {
        this.codautlib = codautlib;
    }

    public AutorLibro getCodautorlibro() {
        return codautorlibro;
    }

    public void setCodautorlibro(AutorLibro codautorlibro) {
        this.codautorlibro = codautorlibro;
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
        hash += (codautlib != null ? codautlib.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AutorPorLibro)) {
            return false;
        }
        AutorPorLibro other = (AutorPorLibro) object;
        if ((this.codautlib == null && other.codautlib != null) || (this.codautlib != null && !this.codautlib.equals(other.codautlib))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entitys.AutorPorLibro[ codautlib=" + codautlib + " ]";
    }
    
}

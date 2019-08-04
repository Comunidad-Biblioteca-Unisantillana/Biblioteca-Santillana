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
@Table(name = "autor_por_enciclopedia")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AutorPorEnciclopedia.findAll", query = "SELECT a FROM AutorPorEnciclopedia a")
    , @NamedQuery(name = "AutorPorEnciclopedia.findByCodautenc", query = "SELECT a FROM AutorPorEnciclopedia a WHERE a.codautenc = :codautenc")})
public class AutorPorEnciclopedia implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "codautenc")
    private String codautenc;
    @JoinColumn(name = "codautorenciclopedia", referencedColumnName = "codautorenciclopedia")
    @ManyToOne(optional = false)
    private AutorEnciclopedia codautorenciclopedia;
    @JoinColumn(name = "codbarraenciclopedia", referencedColumnName = "codbarraenciclopedia")
    @ManyToOne(optional = false)
    private Enciclopedia codbarraenciclopedia;

    public AutorPorEnciclopedia() {
    }

    public AutorPorEnciclopedia(String codautenc) {
        this.codautenc = codautenc;
    }

    public String getCodautenc() {
        return codautenc;
    }

    public void setCodautenc(String codautenc) {
        this.codautenc = codautenc;
    }

    public AutorEnciclopedia getCodautorenciclopedia() {
        return codautorenciclopedia;
    }

    public void setCodautorenciclopedia(AutorEnciclopedia codautorenciclopedia) {
        this.codautorenciclopedia = codautorenciclopedia;
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
        hash += (codautenc != null ? codautenc.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AutorPorEnciclopedia)) {
            return false;
        }
        AutorPorEnciclopedia other = (AutorPorEnciclopedia) object;
        if ((this.codautenc == null && other.codautenc != null) || (this.codautenc != null && !this.codautenc.equals(other.codautenc))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entitys.AutorPorEnciclopedia[ codautenc=" + codautenc + " ]";
    }
    
}

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
@Table(name = "materia_por_revista")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MateriaPorRevista.findAll", query = "SELECT m FROM MateriaPorRevista m")
    , @NamedQuery(name = "MateriaPorRevista.findByCodmateriarevista", query = "SELECT m FROM MateriaPorRevista m WHERE m.codmateriarevista = :codmateriarevista")
    , @NamedQuery(name = "MateriaPorRevista.findByNombremateria", query = "SELECT m FROM MateriaPorRevista m WHERE m.nombremateria = :nombremateria")})
public class MateriaPorRevista implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "codmateriarevista")
    private String codmateriarevista;
    @Basic(optional = false)
    @Column(name = "nombremateria")
    private String nombremateria;
    @JoinColumn(name = "codbarrarevista", referencedColumnName = "codbarrarevista")
    @ManyToOne(optional = false)
    private Revista codbarrarevista;

    public MateriaPorRevista() {
    }

    public MateriaPorRevista(String codmateriarevista) {
        this.codmateriarevista = codmateriarevista;
    }

    public MateriaPorRevista(String codmateriarevista, String nombremateria) {
        this.codmateriarevista = codmateriarevista;
        this.nombremateria = nombremateria;
    }

    public String getCodmateriarevista() {
        return codmateriarevista;
    }

    public void setCodmateriarevista(String codmateriarevista) {
        this.codmateriarevista = codmateriarevista;
    }

    public String getNombremateria() {
        return nombremateria;
    }

    public void setNombremateria(String nombremateria) {
        this.nombremateria = nombremateria;
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entitys;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Camilo
 */
@Entity
@Table(name = "autor_enciclopedia")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AutorEnciclopedia.findAll", query = "SELECT a FROM AutorEnciclopedia a")
    , @NamedQuery(name = "AutorEnciclopedia.findByCodautorenciclopedia", query = "SELECT a FROM AutorEnciclopedia a WHERE a.codautorenciclopedia = :codautorenciclopedia")
    , @NamedQuery(name = "AutorEnciclopedia.findByNombre", query = "SELECT a FROM AutorEnciclopedia a WHERE a.nombre = :nombre")
    , @NamedQuery(name = "AutorEnciclopedia.findByApellido", query = "SELECT a FROM AutorEnciclopedia a WHERE a.apellido = :apellido")
    , @NamedQuery(name = "AutorEnciclopedia.findByFechanacimiento", query = "SELECT a FROM AutorEnciclopedia a WHERE a.fechanacimiento = :fechanacimiento")
    , @NamedQuery(name = "AutorEnciclopedia.findByEdad", query = "SELECT a FROM AutorEnciclopedia a WHERE a.edad = :edad")
    , @NamedQuery(name = "AutorEnciclopedia.findByNacionalidad", query = "SELECT a FROM AutorEnciclopedia a WHERE a.nacionalidad = :nacionalidad")})
public class AutorEnciclopedia implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "codautorenciclopedia")
    private String codautorenciclopedia;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @Column(name = "apellido")
    private String apellido;
    @Basic(optional = false)
    @Column(name = "fechanacimiento")
    @Temporal(TemporalType.DATE)
    private Date fechanacimiento;
    @Basic(optional = false)
    @Column(name = "edad")
    private int edad;
    @Basic(optional = false)
    @Column(name = "nacionalidad")
    private String nacionalidad;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codautorenciclopedia")
    private List<AutorPorEnciclopedia> autorPorEnciclopediaList;

    public AutorEnciclopedia() {
    }

    public AutorEnciclopedia(String codautorenciclopedia) {
        this.codautorenciclopedia = codautorenciclopedia;
    }

    public AutorEnciclopedia(String codautorenciclopedia, String nombre, String apellido, Date fechanacimiento, int edad, String nacionalidad) {
        this.codautorenciclopedia = codautorenciclopedia;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechanacimiento = fechanacimiento;
        this.edad = edad;
        this.nacionalidad = nacionalidad;
    }

    public String getCodautorenciclopedia() {
        return codautorenciclopedia;
    }

    public void setCodautorenciclopedia(String codautorenciclopedia) {
        this.codautorenciclopedia = codautorenciclopedia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Date getFechanacimiento() {
        return fechanacimiento;
    }

    public void setFechanacimiento(Date fechanacimiento) {
        this.fechanacimiento = fechanacimiento;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    @XmlTransient
    public List<AutorPorEnciclopedia> getAutorPorEnciclopediaList() {
        return autorPorEnciclopediaList;
    }

    public void setAutorPorEnciclopediaList(List<AutorPorEnciclopedia> autorPorEnciclopediaList) {
        this.autorPorEnciclopediaList = autorPorEnciclopediaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codautorenciclopedia != null ? codautorenciclopedia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AutorEnciclopedia)) {
            return false;
        }
        AutorEnciclopedia other = (AutorEnciclopedia) object;
        if ((this.codautorenciclopedia == null && other.codautorenciclopedia != null) || (this.codautorenciclopedia != null && !this.codautorenciclopedia.equals(other.codautorenciclopedia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entitys.AutorEnciclopedia[ codautorenciclopedia=" + codautorenciclopedia + " ]";
    }
    
}

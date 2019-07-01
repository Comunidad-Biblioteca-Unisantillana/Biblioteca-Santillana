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
@Table(name = "autor_diccionario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AutorDiccionario.findAll", query = "SELECT a FROM AutorDiccionario a")
    , @NamedQuery(name = "AutorDiccionario.findByCodautordiccionario", query = "SELECT a FROM AutorDiccionario a WHERE a.codautordiccionario = :codautordiccionario")
    , @NamedQuery(name = "AutorDiccionario.findByNombre", query = "SELECT a FROM AutorDiccionario a WHERE a.nombre = :nombre")
    , @NamedQuery(name = "AutorDiccionario.findByApellido", query = "SELECT a FROM AutorDiccionario a WHERE a.apellido = :apellido")
    , @NamedQuery(name = "AutorDiccionario.findByFechanacimiento", query = "SELECT a FROM AutorDiccionario a WHERE a.fechanacimiento = :fechanacimiento")
    , @NamedQuery(name = "AutorDiccionario.findByEdad", query = "SELECT a FROM AutorDiccionario a WHERE a.edad = :edad")
    , @NamedQuery(name = "AutorDiccionario.findByNacionalidad", query = "SELECT a FROM AutorDiccionario a WHERE a.nacionalidad = :nacionalidad")})
public class AutorDiccionario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "codautordiccionario")
    private String codautordiccionario;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codautordiccionario")
    private List<AutorPorDiccionario> autorPorDiccionarioList;

    public AutorDiccionario() {
    }

    public AutorDiccionario(String codautordiccionario) {
        this.codautordiccionario = codautordiccionario;
    }

    public AutorDiccionario(String codautordiccionario, String nombre, String apellido, Date fechanacimiento, int edad, String nacionalidad) {
        this.codautordiccionario = codautordiccionario;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechanacimiento = fechanacimiento;
        this.edad = edad;
        this.nacionalidad = nacionalidad;
    }

    public String getCodautordiccionario() {
        return codautordiccionario;
    }

    public void setCodautordiccionario(String codautordiccionario) {
        this.codautordiccionario = codautordiccionario;
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
    public List<AutorPorDiccionario> getAutorPorDiccionarioList() {
        return autorPorDiccionarioList;
    }

    public void setAutorPorDiccionarioList(List<AutorPorDiccionario> autorPorDiccionarioList) {
        this.autorPorDiccionarioList = autorPorDiccionarioList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codautordiccionario != null ? codautordiccionario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AutorDiccionario)) {
            return false;
        }
        AutorDiccionario other = (AutorDiccionario) object;
        if ((this.codautordiccionario == null && other.codautordiccionario != null) || (this.codautordiccionario != null && !this.codautordiccionario.equals(other.codautordiccionario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entitys.AutorDiccionario[ codautordiccionario=" + codautordiccionario + " ]";
    }
    
}

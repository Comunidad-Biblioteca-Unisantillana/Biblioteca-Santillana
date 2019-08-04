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
@Table(name = "autor_libro")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AutorLibro.findAll", query = "SELECT a FROM AutorLibro a")
    , @NamedQuery(name = "AutorLibro.findByCodautorlibro", query = "SELECT a FROM AutorLibro a WHERE a.codautorlibro = :codautorlibro")
    , @NamedQuery(name = "AutorLibro.findByNombre", query = "SELECT a FROM AutorLibro a WHERE a.nombre = :nombre")
    , @NamedQuery(name = "AutorLibro.findByApellido", query = "SELECT a FROM AutorLibro a WHERE a.apellido = :apellido")
    , @NamedQuery(name = "AutorLibro.findByFechanacimiento", query = "SELECT a FROM AutorLibro a WHERE a.fechanacimiento = :fechanacimiento")
    , @NamedQuery(name = "AutorLibro.findByEdad", query = "SELECT a FROM AutorLibro a WHERE a.edad = :edad")
    , @NamedQuery(name = "AutorLibro.findByNacionalidad", query = "SELECT a FROM AutorLibro a WHERE a.nacionalidad = :nacionalidad")})
public class AutorLibro implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "codautorlibro")
    private String codautorlibro;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codautorlibro")
    private List<AutorPorLibro> autorPorLibroList;

    public AutorLibro() {
    }

    public AutorLibro(String codautorlibro) {
        this.codautorlibro = codautorlibro;
    }

    public AutorLibro(String codautorlibro, String nombre, String apellido, Date fechanacimiento, int edad, String nacionalidad) {
        this.codautorlibro = codautorlibro;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechanacimiento = fechanacimiento;
        this.edad = edad;
        this.nacionalidad = nacionalidad;
    }

    public String getCodautorlibro() {
        return codautorlibro;
    }

    public void setCodautorlibro(String codautorlibro) {
        this.codautorlibro = codautorlibro;
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
    public List<AutorPorLibro> getAutorPorLibroList() {
        return autorPorLibroList;
    }

    public void setAutorPorLibroList(List<AutorPorLibro> autorPorLibroList) {
        this.autorPorLibroList = autorPorLibroList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codautorlibro != null ? codautorlibro.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AutorLibro)) {
            return false;
        }
        AutorLibro other = (AutorLibro) object;
        if ((this.codautorlibro == null && other.codautorlibro != null) || (this.codautorlibro != null && !this.codautorlibro.equals(other.codautorlibro))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entitys.AutorLibro[ codautorlibro=" + codautorlibro + " ]";
    }
    
}

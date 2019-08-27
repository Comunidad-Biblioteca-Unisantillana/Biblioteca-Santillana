/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package usuario.entitys;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Storkolm
 */
@Entity
@Table(name = "bibliotecario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Bibliotecario.findAll", query = "SELECT b FROM Bibliotecario b")
    , @NamedQuery(name = "Bibliotecario.findByIdbibliotecario", query = "SELECT b FROM Bibliotecario b WHERE b.idbibliotecario = :idbibliotecario")
    , @NamedQuery(name = "Bibliotecario.findByTipoid", query = "SELECT b FROM Bibliotecario b WHERE b.tipoid = :tipoid")
    , @NamedQuery(name = "Bibliotecario.findByNombres", query = "SELECT b FROM Bibliotecario b WHERE b.nombres = :nombres")
    , @NamedQuery(name = "Bibliotecario.findByApellidos", query = "SELECT b FROM Bibliotecario b WHERE b.apellidos = :apellidos")
    , @NamedQuery(name = "Bibliotecario.findByFechanacimiento", query = "SELECT b FROM Bibliotecario b WHERE b.fechanacimiento = :fechanacimiento")
    , @NamedQuery(name = "Bibliotecario.findByEdad", query = "SELECT b FROM Bibliotecario b WHERE b.edad = :edad")
    , @NamedQuery(name = "Bibliotecario.findByGenero", query = "SELECT b FROM Bibliotecario b WHERE b.genero = :genero")
    , @NamedQuery(name = "Bibliotecario.findByTelefono", query = "SELECT b FROM Bibliotecario b WHERE b.telefono = :telefono")
    , @NamedQuery(name = "Bibliotecario.findByCorreoelectronico", query = "SELECT b FROM Bibliotecario b WHERE b.correoelectronico = :correoelectronico")
    , @NamedQuery(name = "Bibliotecario.findByCiudadresidencia", query = "SELECT b FROM Bibliotecario b WHERE b.ciudadresidencia = :ciudadresidencia")
    , @NamedQuery(name = "Bibliotecario.findByDireccionresidencia", query = "SELECT b FROM Bibliotecario b WHERE b.direccionresidencia = :direccionresidencia")
    , @NamedQuery(name = "Bibliotecario.findByNacionalidad", query = "SELECT b FROM Bibliotecario b WHERE b.nacionalidad = :nacionalidad")
    , @NamedQuery(name = "Bibliotecario.findBySalario", query = "SELECT b FROM Bibliotecario b WHERE b.salario = :salario")})
public class Bibliotecario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idbibliotecario")
    private String idbibliotecario;
    @Basic(optional = false)
    @Column(name = "tipoid")
    private String tipoid;
    @Basic(optional = false)
    @Column(name = "nombres")
    private String nombres;
    @Basic(optional = false)
    @Column(name = "apellidos")
    private String apellidos;
    @Basic(optional = false)
    @Column(name = "fechanacimiento")
    @Temporal(TemporalType.DATE)
    private Date fechanacimiento;
    @Basic(optional = false)
    @Column(name = "edad")
    private int edad;
    @Basic(optional = false)
    @Column(name = "genero")
    private String genero;
    @Basic(optional = false)
    @Column(name = "telefono")
    private String telefono;
    @Basic(optional = false)
    @Column(name = "correoelectronico")
    private String correoelectronico;
    @Basic(optional = false)
    @Column(name = "ciudadresidencia")
    private String ciudadresidencia;
    @Basic(optional = false)
    @Column(name = "direccionresidencia")
    private String direccionresidencia;
    @Basic(optional = false)
    @Column(name = "nacionalidad")
    private String nacionalidad;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "salario")
    private Float salario;

    public Bibliotecario() {
    }

    public Bibliotecario(String idbibliotecario) {
        this.idbibliotecario = idbibliotecario;
    }

    public Bibliotecario(String idbibliotecario, String tipoid, String nombres, String apellidos, Date fechanacimiento, int edad, String genero, String telefono, String correoelectronico, String ciudadresidencia, String direccionresidencia, String nacionalidad) {
        this.idbibliotecario = idbibliotecario;
        this.tipoid = tipoid;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.fechanacimiento = fechanacimiento;
        this.edad = edad;
        this.genero = genero;
        this.telefono = telefono;
        this.correoelectronico = correoelectronico;
        this.ciudadresidencia = ciudadresidencia;
        this.direccionresidencia = direccionresidencia;
        this.nacionalidad = nacionalidad;
    }

    public String getIdbibliotecario() {
        return idbibliotecario;
    }

    public void setIdbibliotecario(String idbibliotecario) {
        this.idbibliotecario = idbibliotecario;
    }

    public String getTipoid() {
        return tipoid;
    }

    public void setTipoid(String tipoid) {
        this.tipoid = tipoid;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
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

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreoelectronico() {
        return correoelectronico;
    }

    public void setCorreoelectronico(String correoelectronico) {
        this.correoelectronico = correoelectronico;
    }

    public String getCiudadresidencia() {
        return ciudadresidencia;
    }

    public void setCiudadresidencia(String ciudadresidencia) {
        this.ciudadresidencia = ciudadresidencia;
    }

    public String getDireccionresidencia() {
        return direccionresidencia;
    }

    public void setDireccionresidencia(String direccionresidencia) {
        this.direccionresidencia = direccionresidencia;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public Float getSalario() {
        return salario;
    }

    public void setSalario(Float salario) {
        this.salario = salario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idbibliotecario != null ? idbibliotecario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Bibliotecario)) {
            return false;
        }
        Bibliotecario other = (Bibliotecario) object;
        if ((this.idbibliotecario == null && other.idbibliotecario != null) || (this.idbibliotecario != null && !this.idbibliotecario.equals(other.idbibliotecario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "usuario.entitys.Bibliotecario[ idbibliotecario=" + idbibliotecario + " ]";
    }
    
}

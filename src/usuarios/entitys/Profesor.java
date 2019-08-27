/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package usuarios.entitys;

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
@Table(name = "profesor")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Profesor.findAll", query = "SELECT p FROM Profesor p")
    , @NamedQuery(name = "Profesor.findByIdprofesor", query = "SELECT p FROM Profesor p WHERE p.idprofesor = :idprofesor")
    , @NamedQuery(name = "Profesor.findByTipoid", query = "SELECT p FROM Profesor p WHERE p.tipoid = :tipoid")
    , @NamedQuery(name = "Profesor.findByNombres", query = "SELECT p FROM Profesor p WHERE p.nombres = :nombres")
    , @NamedQuery(name = "Profesor.findByApellidos", query = "SELECT p FROM Profesor p WHERE p.apellidos = :apellidos")
    , @NamedQuery(name = "Profesor.findByFechanacimiento", query = "SELECT p FROM Profesor p WHERE p.fechanacimiento = :fechanacimiento")
    , @NamedQuery(name = "Profesor.findByEdad", query = "SELECT p FROM Profesor p WHERE p.edad = :edad")
    , @NamedQuery(name = "Profesor.findByGenero", query = "SELECT p FROM Profesor p WHERE p.genero = :genero")
    , @NamedQuery(name = "Profesor.findByTelefono", query = "SELECT p FROM Profesor p WHERE p.telefono = :telefono")
    , @NamedQuery(name = "Profesor.findByCorreoelectronico", query = "SELECT p FROM Profesor p WHERE p.correoelectronico = :correoelectronico")
    , @NamedQuery(name = "Profesor.findByCiudadresidencia", query = "SELECT p FROM Profesor p WHERE p.ciudadresidencia = :ciudadresidencia")
    , @NamedQuery(name = "Profesor.findByDireccionresidencia", query = "SELECT p FROM Profesor p WHERE p.direccionresidencia = :direccionresidencia")
    , @NamedQuery(name = "Profesor.findByNacionalidad", query = "SELECT p FROM Profesor p WHERE p.nacionalidad = :nacionalidad")})
public class Profesor implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idprofesor")
    private String idprofesor;
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

    public Profesor() {
    }

    public Profesor(String idprofesor) {
        this.idprofesor = idprofesor;
    }

    public Profesor(String idprofesor, String tipoid, String nombres, String apellidos, Date fechanacimiento, int edad, String genero, String telefono, String correoelectronico, String ciudadresidencia, String direccionresidencia, String nacionalidad) {
        this.idprofesor = idprofesor;
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

    public String getIdprofesor() {
        return idprofesor;
    }

    public void setIdprofesor(String idprofesor) {
        this.idprofesor = idprofesor;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idprofesor != null ? idprofesor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Profesor)) {
            return false;
        }
        Profesor other = (Profesor) object;
        if ((this.idprofesor == null && other.idprofesor != null) || (this.idprofesor != null && !this.idprofesor.equals(other.idprofesor))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "usuario.entitys.Profesor[ idprofesor=" + idprofesor + " ]";
    }
    
}

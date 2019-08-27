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
@Table(name = "estudiante")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Estudiante.findAll", query = "SELECT e FROM Estudiante e")
    , @NamedQuery(name = "Estudiante.findByCodestudiante", query = "SELECT e FROM Estudiante e WHERE e.codestudiante = :codestudiante")
    , @NamedQuery(name = "Estudiante.findByIdentificacion", query = "SELECT e FROM Estudiante e WHERE e.identificacion = :identificacion")
    , @NamedQuery(name = "Estudiante.findByTipoid", query = "SELECT e FROM Estudiante e WHERE e.tipoid = :tipoid")
    , @NamedQuery(name = "Estudiante.findByNombre", query = "SELECT e FROM Estudiante e WHERE e.nombre = :nombre")
    , @NamedQuery(name = "Estudiante.findByApellido", query = "SELECT e FROM Estudiante e WHERE e.apellido = :apellido")
    , @NamedQuery(name = "Estudiante.findByFechanacimiento", query = "SELECT e FROM Estudiante e WHERE e.fechanacimiento = :fechanacimiento")
    , @NamedQuery(name = "Estudiante.findByEdad", query = "SELECT e FROM Estudiante e WHERE e.edad = :edad")
    , @NamedQuery(name = "Estudiante.findByGenero", query = "SELECT e FROM Estudiante e WHERE e.genero = :genero")
    , @NamedQuery(name = "Estudiante.findByTelefono", query = "SELECT e FROM Estudiante e WHERE e.telefono = :telefono")
    , @NamedQuery(name = "Estudiante.findByCorreoelectronico", query = "SELECT e FROM Estudiante e WHERE e.correoelectronico = :correoelectronico")
    , @NamedQuery(name = "Estudiante.findByPlan", query = "SELECT e FROM Estudiante e WHERE e.plan = :plan")
    , @NamedQuery(name = "Estudiante.findByCiudadresidencia", query = "SELECT e FROM Estudiante e WHERE e.ciudadresidencia = :ciudadresidencia")
    , @NamedQuery(name = "Estudiante.findByDireccionresidencia", query = "SELECT e FROM Estudiante e WHERE e.direccionresidencia = :direccionresidencia")
    , @NamedQuery(name = "Estudiante.findByNacionalidad", query = "SELECT e FROM Estudiante e WHERE e.nacionalidad = :nacionalidad")})
public class Estudiante implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "codestudiante")
    private String codestudiante;
    @Basic(optional = false)
    @Column(name = "identificacion")
    private String identificacion;
    @Basic(optional = false)
    @Column(name = "tipoid")
    private String tipoid;
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
    @Column(name = "genero")
    private String genero;
    @Basic(optional = false)
    @Column(name = "telefono")
    private String telefono;
    @Basic(optional = false)
    @Column(name = "correoelectronico")
    private String correoelectronico;
    @Basic(optional = false)
    @Column(name = "plan")
    private String plan;
    @Basic(optional = false)
    @Column(name = "ciudadresidencia")
    private String ciudadresidencia;
    @Basic(optional = false)
    @Column(name = "direccionresidencia")
    private String direccionresidencia;
    @Basic(optional = false)
    @Column(name = "nacionalidad")
    private String nacionalidad;

    public Estudiante() {
    }

    public Estudiante(String codestudiante) {
        this.codestudiante = codestudiante;
    }

    public Estudiante(String codestudiante, String identificacion, String tipoid, String nombre, String apellido, Date fechanacimiento, int edad, String genero, String telefono, String correoelectronico, String plan, String ciudadresidencia, String direccionresidencia, String nacionalidad) {
        this.codestudiante = codestudiante;
        this.identificacion = identificacion;
        this.tipoid = tipoid;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechanacimiento = fechanacimiento;
        this.edad = edad;
        this.genero = genero;
        this.telefono = telefono;
        this.correoelectronico = correoelectronico;
        this.plan = plan;
        this.ciudadresidencia = ciudadresidencia;
        this.direccionresidencia = direccionresidencia;
        this.nacionalidad = nacionalidad;
    }

    public String getCodestudiante() {
        return codestudiante;
    }

    public void setCodestudiante(String codestudiante) {
        this.codestudiante = codestudiante;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getTipoid() {
        return tipoid;
    }

    public void setTipoid(String tipoid) {
        this.tipoid = tipoid;
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

    public String getPlan() {
        return plan;
    }

    public void setPlan(String plan) {
        this.plan = plan;
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
        hash += (codestudiante != null ? codestudiante.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Estudiante)) {
            return false;
        }
        Estudiante other = (Estudiante) object;
        if ((this.codestudiante == null && other.codestudiante != null) || (this.codestudiante != null && !this.codestudiante.equals(other.codestudiante))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "usuario.entitys.Estudiante[ codestudiante=" + codestudiante + " ]";
    }
    
}

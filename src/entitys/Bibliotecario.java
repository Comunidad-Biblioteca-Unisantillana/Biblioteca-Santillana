/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entitys;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idbibliotecario")
    private Collection<DevolucionLibroEstudiante> devolucionLibroEstudianteCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idbibliotecario")
    private Collection<DevolucionPeriodicoProfesor> devolucionPeriodicoProfesorCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idbibliotecario")
    private Collection<PrestamoDiccionarioEstudiante> prestamoDiccionarioEstudianteCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idbibliotecario")
    private Collection<PrestamoMapaProfesor> prestamoMapaProfesorCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idbibliotecario")
    private Collection<DevolucionRevistaProfesor> devolucionRevistaProfesorCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idbibliotecario")
    private Collection<PrestamoEnciclopediaEstudiante> prestamoEnciclopediaEstudianteCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idbibliotecario")
    private Collection<PrestamoRevistaEstudiante> prestamoRevistaEstudianteCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idbibliotecario")
    private Collection<DevolucionPeriodicoEstudiante> devolucionPeriodicoEstudianteCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idbibliotecario")
    private Collection<PrestamoMapaEstudiante> prestamoMapaEstudianteCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idbibliotecario")
    private Collection<ReservaColgenEstudiante> reservaColgenEstudianteCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idbibliotecario")
    private Collection<PrestamoLibroProfesor> prestamoLibroProfesorCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idbibliotecario")
    private Collection<DevolucionLibroProfesor> devolucionLibroProfesorCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idbibliotecario")
    private Collection<DevolucionMapaEstudiante> devolucionMapaEstudianteCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idbibliotecario")
    private Collection<DevolucionEnciclopediaProfesor> devolucionEnciclopediaProfesorCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idbibliotecario")
    private Collection<DevolucionRevistaEstudiante> devolucionRevistaEstudianteCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idbibliotecario")
    private Collection<PrestamoLibroEstudiante> prestamoLibroEstudianteCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idbibliotecario")
    private Collection<PrestamoPeriodicoEstudiante> prestamoPeriodicoEstudianteCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idbibliotecario")
    private Collection<PrestamoEnciclopediaProfesor> prestamoEnciclopediaProfesorCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idbibliotecario")
    private Collection<ReservaColgenProfesor> reservaColgenProfesorCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idbibliotecario")
    private Collection<LoginBibliotecario> loginBibliotecarioCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idbibliotecario")
    private Collection<PrestamoPeriodicoProfesor> prestamoPeriodicoProfesorCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idbibliotecario")
    private Collection<PrestamoRevistaProfesor> prestamoRevistaProfesorCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idbibliotecario")
    private Collection<DevolucionDiccionarioProfesor> devolucionDiccionarioProfesorCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idbibliotecario")
    private Collection<DevolucionDiccionarioEstudiante> devolucionDiccionarioEstudianteCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idbibliotecario")
    private Collection<PrestamoDiccionarioProfesor> prestamoDiccionarioProfesorCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idbibliotecario")
    private Collection<DevolucionEnciclopediaEstudiante> devolucionEnciclopediaEstudianteCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idbibliotecario")
    private Collection<DevolucionMapaProfesor> devolucionMapaProfesorCollection;

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

    @XmlTransient
    public Collection<DevolucionLibroEstudiante> getDevolucionLibroEstudianteCollection() {
        return devolucionLibroEstudianteCollection;
    }

    public void setDevolucionLibroEstudianteCollection(Collection<DevolucionLibroEstudiante> devolucionLibroEstudianteCollection) {
        this.devolucionLibroEstudianteCollection = devolucionLibroEstudianteCollection;
    }

    @XmlTransient
    public Collection<DevolucionPeriodicoProfesor> getDevolucionPeriodicoProfesorCollection() {
        return devolucionPeriodicoProfesorCollection;
    }

    public void setDevolucionPeriodicoProfesorCollection(Collection<DevolucionPeriodicoProfesor> devolucionPeriodicoProfesorCollection) {
        this.devolucionPeriodicoProfesorCollection = devolucionPeriodicoProfesorCollection;
    }

    @XmlTransient
    public Collection<PrestamoDiccionarioEstudiante> getPrestamoDiccionarioEstudianteCollection() {
        return prestamoDiccionarioEstudianteCollection;
    }

    public void setPrestamoDiccionarioEstudianteCollection(Collection<PrestamoDiccionarioEstudiante> prestamoDiccionarioEstudianteCollection) {
        this.prestamoDiccionarioEstudianteCollection = prestamoDiccionarioEstudianteCollection;
    }

    @XmlTransient
    public Collection<PrestamoMapaProfesor> getPrestamoMapaProfesorCollection() {
        return prestamoMapaProfesorCollection;
    }

    public void setPrestamoMapaProfesorCollection(Collection<PrestamoMapaProfesor> prestamoMapaProfesorCollection) {
        this.prestamoMapaProfesorCollection = prestamoMapaProfesorCollection;
    }

    @XmlTransient
    public Collection<DevolucionRevistaProfesor> getDevolucionRevistaProfesorCollection() {
        return devolucionRevistaProfesorCollection;
    }

    public void setDevolucionRevistaProfesorCollection(Collection<DevolucionRevistaProfesor> devolucionRevistaProfesorCollection) {
        this.devolucionRevistaProfesorCollection = devolucionRevistaProfesorCollection;
    }

    @XmlTransient
    public Collection<PrestamoEnciclopediaEstudiante> getPrestamoEnciclopediaEstudianteCollection() {
        return prestamoEnciclopediaEstudianteCollection;
    }

    public void setPrestamoEnciclopediaEstudianteCollection(Collection<PrestamoEnciclopediaEstudiante> prestamoEnciclopediaEstudianteCollection) {
        this.prestamoEnciclopediaEstudianteCollection = prestamoEnciclopediaEstudianteCollection;
    }

    @XmlTransient
    public Collection<PrestamoRevistaEstudiante> getPrestamoRevistaEstudianteCollection() {
        return prestamoRevistaEstudianteCollection;
    }

    public void setPrestamoRevistaEstudianteCollection(Collection<PrestamoRevistaEstudiante> prestamoRevistaEstudianteCollection) {
        this.prestamoRevistaEstudianteCollection = prestamoRevistaEstudianteCollection;
    }

    @XmlTransient
    public Collection<DevolucionPeriodicoEstudiante> getDevolucionPeriodicoEstudianteCollection() {
        return devolucionPeriodicoEstudianteCollection;
    }

    public void setDevolucionPeriodicoEstudianteCollection(Collection<DevolucionPeriodicoEstudiante> devolucionPeriodicoEstudianteCollection) {
        this.devolucionPeriodicoEstudianteCollection = devolucionPeriodicoEstudianteCollection;
    }

    @XmlTransient
    public Collection<PrestamoMapaEstudiante> getPrestamoMapaEstudianteCollection() {
        return prestamoMapaEstudianteCollection;
    }

    public void setPrestamoMapaEstudianteCollection(Collection<PrestamoMapaEstudiante> prestamoMapaEstudianteCollection) {
        this.prestamoMapaEstudianteCollection = prestamoMapaEstudianteCollection;
    }

    @XmlTransient
    public Collection<ReservaColgenEstudiante> getReservaColgenEstudianteCollection() {
        return reservaColgenEstudianteCollection;
    }

    public void setReservaColgenEstudianteCollection(Collection<ReservaColgenEstudiante> reservaColgenEstudianteCollection) {
        this.reservaColgenEstudianteCollection = reservaColgenEstudianteCollection;
    }

    @XmlTransient
    public Collection<PrestamoLibroProfesor> getPrestamoLibroProfesorCollection() {
        return prestamoLibroProfesorCollection;
    }

    public void setPrestamoLibroProfesorCollection(Collection<PrestamoLibroProfesor> prestamoLibroProfesorCollection) {
        this.prestamoLibroProfesorCollection = prestamoLibroProfesorCollection;
    }

    @XmlTransient
    public Collection<DevolucionLibroProfesor> getDevolucionLibroProfesorCollection() {
        return devolucionLibroProfesorCollection;
    }

    public void setDevolucionLibroProfesorCollection(Collection<DevolucionLibroProfesor> devolucionLibroProfesorCollection) {
        this.devolucionLibroProfesorCollection = devolucionLibroProfesorCollection;
    }

    @XmlTransient
    public Collection<DevolucionMapaEstudiante> getDevolucionMapaEstudianteCollection() {
        return devolucionMapaEstudianteCollection;
    }

    public void setDevolucionMapaEstudianteCollection(Collection<DevolucionMapaEstudiante> devolucionMapaEstudianteCollection) {
        this.devolucionMapaEstudianteCollection = devolucionMapaEstudianteCollection;
    }

    @XmlTransient
    public Collection<DevolucionEnciclopediaProfesor> getDevolucionEnciclopediaProfesorCollection() {
        return devolucionEnciclopediaProfesorCollection;
    }

    public void setDevolucionEnciclopediaProfesorCollection(Collection<DevolucionEnciclopediaProfesor> devolucionEnciclopediaProfesorCollection) {
        this.devolucionEnciclopediaProfesorCollection = devolucionEnciclopediaProfesorCollection;
    }

    @XmlTransient
    public Collection<DevolucionRevistaEstudiante> getDevolucionRevistaEstudianteCollection() {
        return devolucionRevistaEstudianteCollection;
    }

    public void setDevolucionRevistaEstudianteCollection(Collection<DevolucionRevistaEstudiante> devolucionRevistaEstudianteCollection) {
        this.devolucionRevistaEstudianteCollection = devolucionRevistaEstudianteCollection;
    }

    @XmlTransient
    public Collection<PrestamoLibroEstudiante> getPrestamoLibroEstudianteCollection() {
        return prestamoLibroEstudianteCollection;
    }

    public void setPrestamoLibroEstudianteCollection(Collection<PrestamoLibroEstudiante> prestamoLibroEstudianteCollection) {
        this.prestamoLibroEstudianteCollection = prestamoLibroEstudianteCollection;
    }

    @XmlTransient
    public Collection<PrestamoPeriodicoEstudiante> getPrestamoPeriodicoEstudianteCollection() {
        return prestamoPeriodicoEstudianteCollection;
    }

    public void setPrestamoPeriodicoEstudianteCollection(Collection<PrestamoPeriodicoEstudiante> prestamoPeriodicoEstudianteCollection) {
        this.prestamoPeriodicoEstudianteCollection = prestamoPeriodicoEstudianteCollection;
    }

    @XmlTransient
    public Collection<PrestamoEnciclopediaProfesor> getPrestamoEnciclopediaProfesorCollection() {
        return prestamoEnciclopediaProfesorCollection;
    }

    public void setPrestamoEnciclopediaProfesorCollection(Collection<PrestamoEnciclopediaProfesor> prestamoEnciclopediaProfesorCollection) {
        this.prestamoEnciclopediaProfesorCollection = prestamoEnciclopediaProfesorCollection;
    }

    @XmlTransient
    public Collection<ReservaColgenProfesor> getReservaColgenProfesorCollection() {
        return reservaColgenProfesorCollection;
    }

    public void setReservaColgenProfesorCollection(Collection<ReservaColgenProfesor> reservaColgenProfesorCollection) {
        this.reservaColgenProfesorCollection = reservaColgenProfesorCollection;
    }

    @XmlTransient
    public Collection<LoginBibliotecario> getLoginBibliotecarioCollection() {
        return loginBibliotecarioCollection;
    }

    public void setLoginBibliotecarioCollection(Collection<LoginBibliotecario> loginBibliotecarioCollection) {
        this.loginBibliotecarioCollection = loginBibliotecarioCollection;
    }

    @XmlTransient
    public Collection<PrestamoPeriodicoProfesor> getPrestamoPeriodicoProfesorCollection() {
        return prestamoPeriodicoProfesorCollection;
    }

    public void setPrestamoPeriodicoProfesorCollection(Collection<PrestamoPeriodicoProfesor> prestamoPeriodicoProfesorCollection) {
        this.prestamoPeriodicoProfesorCollection = prestamoPeriodicoProfesorCollection;
    }

    @XmlTransient
    public Collection<PrestamoRevistaProfesor> getPrestamoRevistaProfesorCollection() {
        return prestamoRevistaProfesorCollection;
    }

    public void setPrestamoRevistaProfesorCollection(Collection<PrestamoRevistaProfesor> prestamoRevistaProfesorCollection) {
        this.prestamoRevistaProfesorCollection = prestamoRevistaProfesorCollection;
    }

    @XmlTransient
    public Collection<DevolucionDiccionarioProfesor> getDevolucionDiccionarioProfesorCollection() {
        return devolucionDiccionarioProfesorCollection;
    }

    public void setDevolucionDiccionarioProfesorCollection(Collection<DevolucionDiccionarioProfesor> devolucionDiccionarioProfesorCollection) {
        this.devolucionDiccionarioProfesorCollection = devolucionDiccionarioProfesorCollection;
    }

    @XmlTransient
    public Collection<DevolucionDiccionarioEstudiante> getDevolucionDiccionarioEstudianteCollection() {
        return devolucionDiccionarioEstudianteCollection;
    }

    public void setDevolucionDiccionarioEstudianteCollection(Collection<DevolucionDiccionarioEstudiante> devolucionDiccionarioEstudianteCollection) {
        this.devolucionDiccionarioEstudianteCollection = devolucionDiccionarioEstudianteCollection;
    }

    @XmlTransient
    public Collection<PrestamoDiccionarioProfesor> getPrestamoDiccionarioProfesorCollection() {
        return prestamoDiccionarioProfesorCollection;
    }

    public void setPrestamoDiccionarioProfesorCollection(Collection<PrestamoDiccionarioProfesor> prestamoDiccionarioProfesorCollection) {
        this.prestamoDiccionarioProfesorCollection = prestamoDiccionarioProfesorCollection;
    }

    @XmlTransient
    public Collection<DevolucionEnciclopediaEstudiante> getDevolucionEnciclopediaEstudianteCollection() {
        return devolucionEnciclopediaEstudianteCollection;
    }

    public void setDevolucionEnciclopediaEstudianteCollection(Collection<DevolucionEnciclopediaEstudiante> devolucionEnciclopediaEstudianteCollection) {
        this.devolucionEnciclopediaEstudianteCollection = devolucionEnciclopediaEstudianteCollection;
    }

    @XmlTransient
    public Collection<DevolucionMapaProfesor> getDevolucionMapaProfesorCollection() {
        return devolucionMapaProfesorCollection;
    }

    public void setDevolucionMapaProfesorCollection(Collection<DevolucionMapaProfesor> devolucionMapaProfesorCollection) {
        this.devolucionMapaProfesorCollection = devolucionMapaProfesorCollection;
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
        return "entitys.Bibliotecario[ idbibliotecario=" + idbibliotecario + " ]";
    }
    
}

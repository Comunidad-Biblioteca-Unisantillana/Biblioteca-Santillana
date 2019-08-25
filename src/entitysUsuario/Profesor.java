
package entitysUsuario;

import java.util.Date;

/**
 *
 * @author Julian
 */
public class Profesor {
    
    private String idprofesor,tipoid,nombres,apellidos,genero,telefono,correoelectronico,ciudadresidencia,
            direccionresidencia,nacionalidad;
    private Date fechanacimiento;
    private int edad;

    public Profesor(String idprofesor, String tipoid, String nombres, String apellidos,Date fechanacimiento, int edad, String genero, String telefono, String correoelectronico, String ciudadresidencia, String direccionresidencia, String nacionalidad) {
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
    
    
    
}

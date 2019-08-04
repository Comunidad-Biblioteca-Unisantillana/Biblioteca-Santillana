package modeloDAO;

import entitysRecursos.Estudiante;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import modelo.ConnectionBD;

/**
 *
 * @author stive
 */
public class EstudianteDAO implements InterfacePersonaCRUD<Estudiante> {

    private ConnectionBD connection;

    public EstudianteDAO() {
        connection = ConnectionBD.getInstance();
    }
    /**
     * Método que realiza los INSERT en la BD.
     * @param estudiante
     * @return 
     */
    @Override
    public boolean createDAO(Estudiante estudiante) {

        String sqlSentence = "INSERT INTO Estudiante (codEstudiante, identificacion, tipoId, nombre, apellido, fechaNacimiento, edad, genero, telefono, correoElectronico, plan, ciudadResidencia, direccionResidencia, nacionalidad)"
                + " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        PreparedStatement pps;

        try {
            pps = connection.getConnection().prepareStatement(sqlSentence);
            pps.setString(1, estudiante.getCodEstudiante());
            pps.setString(2, estudiante.getIdentificacion());
            pps.setString(3, estudiante.getTipoId());
            pps.setString(4, estudiante.getNombre());
            pps.setString(5, estudiante.getApellido());
            pps.setDate(6, estudiante.getFechaNacimiento());
            pps.setInt(7, estudiante.getEdad());
            pps.setString(8, estudiante.getGenero());
            pps.setString(9, estudiante.getTelefono());
            pps.setString(10, estudiante.getCorreoElectronico());
            pps.setString(11, estudiante.getPlan());
            pps.setString(12, estudiante.getCiudadResidencia());
            pps.setString(13, estudiante.getDireccionResidencia());
            pps.setString(14, estudiante.getNacionalidad());

            if (pps.executeUpdate() > 0) {
                System.out.println("Estudiante creado");
                return true;
            }

        } catch (SQLException e) {
            System.out.println("El Estudiante no se pudo crear " + "\n" + e.getMessage());
        } finally {
            connection.closeConnection();
        }
        return false;

    }
    /**
     * Método que realiza las consultas en la BD por medio de un código.
     * @param codEstudiante
     * @return 
     */
    @Override
    public Estudiante readDAO(String codEstudiante) {
        Statement stmt;
        ResultSet rs;
        Estudiante estudiante = null;
        try {
            
            System.out.println("SELECT * FROM Estudiante WHERE codEstudiante = '" + codEstudiante + "'");
            stmt = connection.getConnection().createStatement();
            rs = stmt.executeQuery("SELECT * FROM Estudiante WHERE codEstudiante = '" + codEstudiante + "'" );
            
            while (rs.next()) {
                estudiante = new Estudiante(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
                        rs.getDate(6), rs.getInt(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(11),
                        rs.getString(12), rs.getString(13), rs.getString(14));
            }
            rs.close();
            return estudiante;
        } catch (SQLException e) {
            System.out.println("El estudiante con este código no existe");
        } catch (Exception e) {
            System.out.println("No se pudo realizar la consulta");
        } 
        return estudiante;

    }
    /**
     * Método que realiza las actualizaciones en la BD.
     * @param estudiante
     * @return 
     */
    @Override
    public boolean updateDAO(Estudiante estudiante) {
        String sqlSentence = "UPDATE Estudiante SET codEstudiante = ?, identificacion = ?, tipoId = ?, nombre = ?, apellido = ?, fechaNacimiento = ?, edad = ?, genero = ?, telefono = ?, correoElectronico = ?, plan = ?, ciudadResidencia = ?, direccionResidencia = ?, nacionalidad = ?"
                + " WHERE codEstudiante = ?";
        PreparedStatement pps;

        try {
            pps = connection.getConnection().prepareStatement(sqlSentence);
            pps.setString(1, estudiante.getCodEstudiante());
            pps.setString(2, estudiante.getIdentificacion());
            pps.setString(3, estudiante.getTipoId());
            pps.setString(4, estudiante.getNombre());
            pps.setString(5, estudiante.getApellido());
            pps.setDate(6, estudiante.getFechaNacimiento());
            pps.setInt(7, estudiante.getEdad());
            pps.setString(8, estudiante.getGenero());
            pps.setString(9, estudiante.getTelefono());
            pps.setString(10, estudiante.getCorreoElectronico());
            pps.setString(11, estudiante.getPlan());
            pps.setString(12, estudiante.getCiudadResidencia());
            pps.setString(13, estudiante.getDireccionResidencia());
            pps.setString(14, estudiante.getNacionalidad());
            pps.setString(15, estudiante.getCodEstudiante());

            if (pps.executeUpdate() > 0) {
                System.out.println("Realizo el update");
                return true;
            } else {
                System.out.println("No existe una estudiante con ese código");
            }
        } catch (SQLException e) {
            System.out.println("No se pudo realizar el update del estudiante");
        } 
        return false;

    }
    /**
     *  Método que realiza los delete de la BD por medio de un código.
     * @param codEstudiante
     * @return 
     */
    @Override
    public boolean deleteDAO(String codEstudiante) {
        String sqlSentence = "DELETE FROM Estudiante WHERE codEstudiante = ?";
        PreparedStatement pps;
       
        try{
           pps = connection.getConnection().prepareStatement(sqlSentence);        
           pps.setString(1, codEstudiante);
           
           if(pps.executeUpdate() > 0){
               System.out.println("Hizo el delete");
               return true;
           }
        }
        catch(SQLException e){
           System.err.println("No se pudo realizar el delete del estudiante");
        }

       return false;

    }
    /**
     * Método que consulta todas las filas de esa entidad en la base de datos.
     * @return
     * @throws Exception 
     */
    @Override
    public List<Estudiante> readAllDAO() throws Exception {
       
        PreparedStatement pps;
        ResultSet rs;
        ArrayList<Estudiante> estudiantes = new ArrayList();
        
        try{
            pps = connection.getConnection().prepareStatement("SELECT * FROM Estudiante");
            rs = pps.executeQuery();
            
            while(rs.next()){
                Estudiante estudianteTmp = new Estudiante(rs.getString("codEstudiante"), rs.getString("identificacion"), rs.getString("tipoId"), rs.getString("nombre"), rs.getString("apellido"),
                        rs.getDate("fechaNacimiento"), rs.getInt("edad"), rs.getString("genero"), rs.getString("telefono"), rs.getString("correoElectronico"), rs.getString("plan"),
                        rs.getString("ciudadResidencia"), rs.getString("direccionResidencia"), rs.getString("nacionalidad"));               
                estudiantes.add(estudianteTmp);
            }
            rs.close();
        }
        catch(SQLException e){
            System.out.println("No se realizo el readAll correctamente en estudiante");
        }
        catch(Exception e){
            System.out.println("Problema en el readAll de estudiante");
        }       
        return estudiantes;
    }
}

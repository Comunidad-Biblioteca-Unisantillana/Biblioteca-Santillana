package moduloDevolucion.DAO;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import general.modelo.ConnectionBD;
import moduloDevolucion.entitys.DevolucionDiccionarioEst;

/**
 * @author Camilo Jaramillo
 * @created 04/08/2019
 * @author Miguel Fernández
 * @modificado: 08/09/2019
 */
public class DevolucionDiccionarioDAOEst extends DevolucionRecursoDAOAbs<DevolucionDiccionarioEst> {

    public DevolucionDiccionarioDAOEst() {
        connection = ConnectionBD.getInstance();
    }

    @Override
    public boolean createDAO(DevolucionDiccionarioEst devolucion) {

        String sqlSentence = "INSERT INTO Devolucion_Diccionario_Estudiante (codPrestDicEst, idBibliotecario, fechaDevolucion, estadoDevolucion) VALUES (?,?,CURRENT_DATE,?)";
        PreparedStatement pps;

        try {
            pps = connection.getConnection().prepareStatement(sqlSentence);
            pps.setInt(1, devolucion.getCodPrestamoDiccionarioEst());
            pps.setString(2, devolucion.getIdBibliotecario());
            pps.setString(3, devolucion.getEstadoDevolucion());

            if (pps.executeUpdate() > 0) {
                System.out.println("Registro creado");
                return true;
            }
        } catch (SQLException e) {
            System.out.println("El registro no se pudo crear " + "\n" + e.getMessage());
        }
        return false;
    }

    @Override
    public DevolucionDiccionarioEst readDAO(int codigo) {
        Statement stmt;
        ResultSet rs;
        DevolucionDiccionarioEst devolucion = null;

        try {
            stmt = connection.getConnection().createStatement();
            rs = stmt.executeQuery("SELECT * FROM Devolucion_Diccionario_Estudiante WHERE codDevDicEst = " + codigo + ";");

            while (rs.next()) {
                devolucion = new DevolucionDiccionarioEst(rs.getInt("codPrestDicEst"), rs.getString("idBibliotecario"),
                        rs.getString("estadoDevolucion"));
                devolucion.setFechaDevolucion(rs.getDate("fechaDevolucion"));
                devolucion.setCodDevolucionDiccionarioEst(rs.getInt("codDevDicEst"));
            }
            rs.close();
            return devolucion;
        } catch (SQLException e) {
            System.out.println("La devolución de diccionario de estudiante con ese codigo no existe");
        } catch (Exception e) {
            System.out.println("No se pudo realizar la consulta");
        }
        return devolucion;
    }

    @Override
    public boolean updateDAO(DevolucionDiccionarioEst devolucion) {
        String sqlSentence = "UPDATE Devolucion_Diccionario_Estudiante SET codPrestDicEst = ?, idBibliotecario = ?, fechaDevolucion = ?, estadoDevolucion = ?"
                + " WHERE codDevDicEst = ?";
        PreparedStatement pps;

        try {
            pps = connection.getConnection().prepareStatement(sqlSentence);
            pps.setInt(1, devolucion.getCodPrestamoDiccionarioEst());
            pps.setString(2, devolucion.getIdBibliotecario());
            pps.setDate(3, (Date) devolucion.getFechaDevolucion());
            pps.setString(4, devolucion.getEstadoDevolucion());
            pps.setInt(5, devolucion.getCodDevolucionDiccionarioEst());

            if (pps.executeUpdate() > 0) {
                System.out.println("Realizo el update");
                return true;
            } else {
                System.out.println("No existe una devolucion con ese codigo");
            }
        } catch (SQLException e) {
            System.out.println("No se pudo realizar el update de devolucion diccionario del estudiante");
        }
        return false;
    }

    @Override
    public boolean deleteDAO(int codigo) {
        String sqlSentence = "DELETE FROM Devolucion_Diccionario_Estudiante WHERE codDevDicEst = ?";
        PreparedStatement pps;

        try {
            pps = connection.getConnection().prepareStatement(sqlSentence);
            pps.setInt(1, codigo);

            if (pps.executeUpdate() > 0) {
                System.out.println("Hizo el delete");
                return true;
            } else {
                System.out.println("No existe una devolucion diccionario de estudiante con ese codigo");
            }
        } catch (SQLException e) {
            System.err.println("No se pudo realizar el delete de devolucion de diccionario del estudiante ");
        }
        return false;
    }

    @Override
    public List<DevolucionDiccionarioEst> readAllDAO() {
        PreparedStatement pps;
        ResultSet rs;
        ArrayList<DevolucionDiccionarioEst> devoluciones = new ArrayList();

        try {
            pps = connection.getConnection().prepareStatement("SELECT * FROM Devolucion_Diccionario_Estudiante");
            rs = pps.executeQuery();

            while (rs.next()) {
                DevolucionDiccionarioEst devolucionTmp = new DevolucionDiccionarioEst(rs.getInt("codPrestDicEst"), rs.getString("idBibliotecario"), 
                        rs.getString("estadoDevolucion"));
                devolucionTmp.setFechaDevolucion(rs.getDate("fechaDevolucion"));
                devolucionTmp.setCodDevolucionDiccionarioEst(rs.getInt("codDevDicEst"));
                devoluciones.add(devolucionTmp);
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println("No se realizo el readAll correctamente sobre devolucion diccionario de estudiante");
        } catch (Exception e) {
            System.out.println("Problema en el readAll");
        }
        return devoluciones;
    }
    
    /**
     * el método realiza la consulta del código de la devolución de una
     * diccionario del profesor en la BD, por medio del código del préstamo.
     *
     * @param codPrestamo
     * @return codDevolucion
     */
    @Override
    public int readCodigoDAO(int codPrestamo) {
        Statement stmt;
        ResultSet rs;
        int codDevolucion = -1;

        try {
            stmt = connection.getConnection().createStatement();
            rs = stmt.executeQuery("SELECT codDevDicEst FROM Devolucion_Diccionario_Estudiante "
                    + "WHERE codPrestDicEst = '" + codPrestamo + "';");

            while (rs.next()) {
                codDevolucion = rs.getInt("codDevDicEst");
            }

            rs.close();
        } catch (SQLException e) {
            System.out.println("Error al realizar el readCodigoDAO, en devolución diccionario estudiante");
        }

        return codDevolucion;
    }
    
}

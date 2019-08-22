package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Clase que permite conectarse desde Java con la base de datos de postgresql.<br>
 * Implementa el patrón Singleton.
 * @author Camilo Jaramillo
 */
public class ConnectionBD {
    
    private static ConnectionBD instance;
    private Connection connection;
    
    /**
     * En el constructor se establece la conexión a la base de datos de postgresql
     */
    private ConnectionBD(){
        try{
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql:PruebaBiblioteca", "postgres", "root");
            System.out.println("Conecto a la BBDD");
        }
        catch(ClassNotFoundException e){
            System.err.println("No se pudo conectar a la BBDD");
        }
        catch(SQLException e){
            System.err.println(e.getMessage());
        }
    }
    

    
    /**
     * Retorna la instancia de esta clase, permitiendo conservar una unica instancia<br>
     * a lo largo del programa.
     * @return ConnectionBD
     */
    public static ConnectionBD getInstance(){
        if(instance == null){
            instance = new ConnectionBD();
        }
        return instance;
    }
    
    /**
     * Retorna la coneccion con la base de datos de postgresql. (Se utiza en algunas ocasiones).
     * @return Connection
     * @throws SQLException 
     */
    public Connection getConnection() throws SQLException{     
        DriverManager.getConnection("jdbc:postgresql:PruebaBiblioteca", "postgres", "root");
        return  connection;
    }
    
    /**
     * Método que cierra la conexión abierta con la base de datos.
     */
    public void closeConnection(){
        try{
            connection.close();
        }
        catch(SQLException e){
            System.err.println("No se pudo cerrar la conexion con la BBDD");
        }
    }
}
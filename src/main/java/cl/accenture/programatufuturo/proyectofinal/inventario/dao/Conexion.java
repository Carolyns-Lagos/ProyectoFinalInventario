package cl.accenture.programatufuturo.proyectofinal.inventario.dao;

//Es necesario importar mi clase sin conexion

import cl.accenture.programatufuturo.proyectofinal.inventario.exception.SinConexionException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    //Informacion de mysql necesaria para realizar la coneccion

    private static final String DRIVER = "com.mysql.jdbc.Driver";
    //Direccion de puerto de la base de datos
    private static final int PORT = 3306;
    //Direccion de puerto de la base de datos
    private static final String HOST = "localhost";
    //Nombre de la base de Datos
    private static final String DATABASE = "inventariopf";
    //nombre usuario de mysql
    private static final String USER = "root";
    //Contraseña mysql
    private static final String PASSWORD = "1234";
    private Connection connection;

    //Falta explicar esto
    public Connection obtenerConnection() throws SinConexionException{
        try{
            Class.forName(DRIVER);
            this.connection=
                    DriverManager.getConnection(
                            "jdbc:mysql://"+HOST+":"+PORT+"/"+DATABASE + "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
                            USER,PASSWORD);

            return connection;
        } catch (ClassNotFoundException e){
            throw new SinConexionException("Ocurrio un error al establecer la conexion a : " + e.getMessage(), e);

        }catch (SQLException e){
            throw new SinConexionException("Ocurrio un error al establecer la conexion b : " + e.getMessage(), e);

        }

    }

    public Connection getConnection() {
        return this.connection;
    }




}

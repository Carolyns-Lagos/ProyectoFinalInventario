package cl.accenture.programatufuturo.proyectofinal.inventario.dao;

import cl.accenture.programatufuturo.proyectofinal.inventario.exception.SinConexionException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SQLIdAutoincrementable {

    //Se crea atributo para la conexion, dado que sera necesaria para trabajar con los datos y tablas de SQL
    private Conexion conexion;

    //Inicializo esta clase con un constructor que permita la coneccion.
    public SQLIdAutoincrementable() {

        this.conexion = new Conexion();
    }

    //Constructores get y ser

    public Conexion getConexion() {

        return this.conexion;
    }

    public void setConexion(Conexion conexion) {

        this.conexion = conexion;
    }

    //Metodo que buscara el valor maximo de id en la base de datos para saber el id al momento de crear un nuevo dato en las clases, cuando el id en la base de datos sea automatico
    public int idIncrementableSucursal() throws SinConexionException, SQLException {
        //Inicializo las variables necesarias.
        int id = 1;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            final String SQL = "SELECT MAX(idSucursal) FROM inventarioPF.Sucursal";
            ps = conexion.obtenerConnection().prepareStatement(SQL);
            rs = ps.executeQuery();
            //Recorro la tabla, pisiendo el valor de la columna 1 y a ese valor sumarle 1
            while (rs.next()) {
                id = rs.getInt(1) + 1;
            }
            //En caso de haber un error en la consulta, se indicara
        } catch (SQLException e) {
            e.printStackTrace();

            //En caso de algun error se indicara
        } catch (Exception ex) {
            System.out.println("Error" + ex.getMessage());

            //Finally se ocupara para cuando se terminen de ocupar las conecciones mara evitar que consuma mas memoria de la necesaria en el equipo
        } finally {
            try {
                ps.close();
                rs.close();
            } catch (Exception ex) {

            }
        }
        return id;
    }

    //Metodo que buscara el valor maximo de id en la base de datos para saber el id al momento de crear un nuevo dato en las clases, cuando el id en la base de datos sea automatico
    public int idIncrementableProducto() throws SinConexionException, SQLException {
        //Inicializo las variables necesarias.
        int id = 1;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            final String SQL = "SELECT MAX(idProducto) FROM inventarioPF.Producto";
            ps = conexion.obtenerConnection().prepareStatement(SQL);
            rs = ps.executeQuery();
            //Recorro la tabla, pisiendo el valor de la columna 1 y a ese valor sumarle 1
            while (rs.next()) {
                id = rs.getInt(1) + 1;
            }
            //En caso de haber un error en la consulta, se indicara
        } catch (SQLException e) {
            e.printStackTrace();

            //En caso de algun error se indicara
        } catch (Exception ex) {
            System.out.println("Error" + ex.getMessage());

            //Finally se ocupara para cuando se terminen de ocupar las conecciones mara evitar que consuma mas memoria de la necesaria en el equipo
        } finally {
            try {
                ps.close();
                rs.close();
            } catch (Exception ex) {

            }
        }
        return id;
    }

    public int idIncrementableVenta() throws SinConexionException, SQLException {
        //Inicializo las variables necesarias.
        int id = 1;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            final String SQL = "SELECT MAX(idVenta) FROM inventarioPF.Venta";
            ps = conexion.obtenerConnection().prepareStatement(SQL);
            rs = ps.executeQuery();
            //Recorro la tabla, pisiendo el valor de la columna 1 y a ese valor sumarle 1
            while (rs.next()) {
                id = rs.getInt(1) + 1;
            }
            //En caso de haber un error en la consulta, se indicara
        } catch (SQLException e) {
            e.printStackTrace();

            //En caso de algun error se indicara
        } catch (Exception ex) {
            System.out.println("Error" + ex.getMessage());

            //Finally se ocupara para cuando se terminen de ocupar las conecciones mara evitar que consuma mas memoria de la necesaria en el equipo
        } finally {
            try {
                ps.close();
                rs.close();
            } catch (Exception ex) {

            }
        }
        return id;
    }


}

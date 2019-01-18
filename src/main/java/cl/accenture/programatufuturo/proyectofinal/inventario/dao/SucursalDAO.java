package cl.accenture.programatufuturo.proyectofinal.inventario.dao;

import cl.accenture.programatufuturo.proyectofinal.inventario.exception.SinConexionException;
import cl.accenture.programatufuturo.proyectofinal.inventario.model.Sucursal;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SucursalDAO {
    //Se crea atributo para la conexion, dado que sera necesaria para trabajar con los datos y tablas de SQL
    private Conexion conexion;

    //Inicializo esta clase con un constructor que permita la conexion.

    //CONSTRUCTOR
    public SucursalDAO() {

        this.conexion = new Conexion();
    }

    // GETTERS Y SETTERS

    public Conexion getConexion() {

        return this.conexion;
    }

    public void setConexion(Conexion conexion) {

        this.conexion = conexion;
    }

    //Metodo que me permitira agregar un Producto que no se encuentre en la base de datos
    public void agregarSucursal(Sucursal sucursal) throws SinConexionException, SQLException {

        try {
            final String SQL = "INSERT INTO Producto(idSucursal,ubicacion,comuna,direccion)" + "VALUES (?,?,?,?)";
            PreparedStatement ps = conexion.obtenerConnection().prepareStatement(SQL);
            ps.setInt(1, sucursal.getIdSucursal());
            ps.setString(2, sucursal.getUbicacion());
            ps.setString(3, sucursal.getComuna());
            ps.setString(4, sucursal.getDireccion());
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

}


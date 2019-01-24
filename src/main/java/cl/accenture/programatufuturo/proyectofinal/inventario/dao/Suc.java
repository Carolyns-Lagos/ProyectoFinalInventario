package cl.accenture.programatufuturo.proyectofinal.inventario.dao;

import cl.accenture.programatufuturo.proyectofinal.inventario.exception.SinConexionException;
import cl.accenture.programatufuturo.proyectofinal.inventario.model.Sucursal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Suc {

    //Se crea atributo para la conexion, dado que sera necesaria para trabajar con los datos y tablas de SQL
    private Conexion conexion;

    //Inicializo esta clase con un constructor que permita la conexion.

    //CONSTRUCTOR
    public Suc() {

        this.conexion = new Conexion();
    }

    // GETTERS Y SETTERS

    public Conexion getConexion() {

        return this.conexion;
    }

    public void setConexion(Conexion conexion) {

        this.conexion = conexion;
    }

    public boolean verificarSucursal(Sucursal sucursal) throws SinConexionException, SQLException {
        final String SQL = "SELECT * FROM inventariopf.sucursal WHERE Nombre =? ";
        PreparedStatement ps = conexion.obtenerConnection().prepareStatement(SQL);
        ps.setString(1, sucursal.getNombre());
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            System.out.println("Socursal existente");
            return true;

        }
        System.out.println("Sucursal no encontrado");
        return false;
    }


    public static int obtenerIDSucursall(Conexion conexion, Sucursal sucursal) throws SinConexionException{
        int idSuc=0;
        try{
            final String SQL = "SELECT idSucursal from inventariopf.sucursal WHERE Nombre=? and Ubicacion=? and Comuna=? and Direccion=? ";
            PreparedStatement ps = conexion.obtenerConnection().prepareStatement(SQL);
            ps.setString(1,sucursal.getNombre());
            ps.setString(2,sucursal.getUbicacion());
            ps.setString(3,sucursal.getComuna());
            ps.setString(4,sucursal.getDireccion());
            ResultSet rs = ps.executeQuery();
            rs.next();
            idSuc = rs.getInt("idSucursal");
        }catch (SQLException e){
            e.printStackTrace();
        }
        return idSuc;
    }

    public void agregarSucursall(Sucursal sucursal) throws SinConexionException, SQLException {

        if (verificarSucursal(sucursal)!=true){
            System.out.println("Agregar Sucursal");

            try{
                final String SQL = "INSERT INTO inventariopf.sucursal( Nombre, Ubicacion, Comuna, Direccion)"+ "VALUES (?,?,?,?)";
                PreparedStatement ps = conexion.obtenerConnection().prepareStatement(SQL);
                ps.setString(1, sucursal.getNombre());
                ps.setString(2,sucursal.getUbicacion());
                ps.setString(3,sucursal.getComuna());
                ps.setString(4,sucursal.getDireccion());
                ps.executeUpdate();
            } catch (SQLException ex){
                ex.printStackTrace();
            }
        }else {
            System.out.println("Sucursal existente");
        }
    }

    public void eliminarSucursalPorNombre(Sucursal sucursal) throws SinConexionException, SQLException {
        if (verificarSucursal(sucursal)==false){
            //En caso de ser verdadero, procedere a eliminar al usuario
            System.out.println("Sucursal no encontrada");

        } else if(verificarSucursal(sucursal)==true){
            try{
                final String SQL = "DELETE FROM inventariopf.sucursal WHERE Nombre = ?";
                PreparedStatement ps = conexion.obtenerConnection().prepareStatement(SQL);
                //Le asigno valor al '?'
                ps.setString(1, sucursal.getNombre());
                ps.executeUpdate();
                System.out.println("Sucursal Eliminado");
            } catch (SQLException ex){
                ex.printStackTrace();
            }
        }
    }

    public Sucursal buscarSucursalPorId(int id) throws SinConexionException, SQLException {
        Sucursal suc=new Sucursal();
        final String SQL = "SELECT * FROM inventariopf.sucursal WHERE idSucursal =? ";
        PreparedStatement ps = conexion.obtenerConnection().prepareStatement(SQL);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {

            suc.setIdSucursal(rs.getInt(1));
            suc.setNombre(rs.getString(2));
            suc.setUbicacion(rs.getString(3));
            suc.setComuna(rs.getString(4));
            suc.setDireccion(rs.getString(5));
            return suc;
        }
        System.out.println("Sucursal no encontradoa");
        return null;
    }
}

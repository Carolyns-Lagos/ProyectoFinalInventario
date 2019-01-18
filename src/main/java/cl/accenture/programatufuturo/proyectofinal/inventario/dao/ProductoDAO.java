package cl.accenture.programatufuturo.proyectofinal.inventario.dao;

import cl.accenture.programatufuturo.proyectofinal.inventario.exception.*;
import cl.accenture.programatufuturo.proyectofinal.inventario.model.Producto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



public class ProductoDAO {

    //Se crea atributo para la conexion, dado que sera necesaria para trabajar con los datos y tablas de SQL
    private Conexion conexion;

    //Inicializo esta clase con un constructor que permita la conexion.

    //CONSTRUCTOR
    public ProductoDAO(){

        this.conexion=new Conexion();
    }

    // GETTERS Y SETTERS

    public Conexion getConexion() {

        return this.conexion;
    }
    public void setConexion(Conexion conexion) {

        this.conexion = conexion;
    }

    //Metodo para agregar producto, en caso de que no exista o no se pueda conectar por algun problema, no realizara nada, solo me indicara el problema.

    public boolean VerificarProducto(Producto Producto) throws ProductoYaEnSistemaException,SinConexionException, SQLException {
        //Creo la consulta en SQL en la cual indico cual sera mi condicion
        final String SQL = "SELECT * FROM inventarioPF.Productos WHERE Nombre = ? ";

        //Creo una variable que al ingresar a la conexion, me permita agregar corroborar datos, con prepare Statement creo mayor seguridad
        PreparedStatement ps = conexion.obtenerConnection().prepareStatement(SQL);
        ps.setString(1, Producto.getNombre());
        //Para obtener los resultados de la consulta SQL creo una variable ResultSet
        ResultSet rs = ps.executeQuery(SQL);
        //Mientras existan coincidencias en la consulta, retornara False, si no encuentra ningun valor, sera true.
        while (rs.next()) {
            return false;
        }
        throw new ProductoYaEnSistemaException("El Producto ya ha sido creado");
    }

    //Metodo que me permitira agregar un Producto que no se encuentre en la base de datos
    public void agregarProducto(Producto producto) throws SinConexionException, SQLException, ProductoYaEnSistemaException {
        //Verifico que no exista algun producto con el mismo nombre en la base de datos
        if (VerificarProducto(producto)!=false){
            //En caso de ser diferente a false, procedere a agregar al producto
            try{
                final String SQL = "INSERT INTO Producto(Nombre,Caracteristicas,Cantidad min, Cantidad Max, Precio, Marca)"+ "VALUES (?,?,?,?,?,?)";
                PreparedStatement ps = conexion.obtenerConnection().prepareStatement(SQL);
                ps.setString(1, producto.getNombre());
                ps.setString(2,producto.getCaracteristica());
                ps.setInt(3,producto.getCantidadMin());
                ps.setInt(4,producto.getCantidadMax());
                ps.setInt(5, producto.getPrecio());
                ps.setString(6,producto.getMarca());
                ps.executeUpdate();
            } catch (SQLException ex){
                ex.printStackTrace();
            }
        }
        System.out.println("producto ya en sistema");
    }






}

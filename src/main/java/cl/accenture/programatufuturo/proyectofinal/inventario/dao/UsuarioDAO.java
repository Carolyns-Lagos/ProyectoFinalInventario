package cl.accenture.programatufuturo.proyectofinal.inventario.dao;

import cl.accenture.programatufuturo.proyectofinal.inventario.exception.DriverErrorException;
import cl.accenture.programatufuturo.proyectofinal.inventario.exception.SinConexionException;
import cl.accenture.programatufuturo.proyectofinal.inventario.exception.UsuarioNoExistenteException;
import cl.accenture.programatufuturo.proyectofinal.inventario.exception.UsuarioNoExistenteException;
import cl.accenture.programatufuturo.proyectofinal.inventario.exception.UsuarioOContraseñaIncorrectosException;
import cl.accenture.programatufuturo.proyectofinal.inventario.model.Sucursal;
import cl.accenture.programatufuturo.proyectofinal.inventario.model.Usuario;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAO {

    private Conexion conexion;

    public UsuarioDAO(){

        this.conexion=new Conexion();
    }

    //Constructores get y ser

    public Conexion getConexion() {

        return this.conexion;
    }

    public void setConexion(Conexion conexion) {

        this.conexion = conexion;
    }

    //Login para ingresar, retorna un boolean, recibe un String nombre y un String Usuario
    public boolean login(String usuario, String password) throws UsuarioOContraseñaIncorrectosException, SinConexionException {
        try {

            //Creo la consulta en SQL en la cual indico cual sera mi condicion
            final String SQL = "SELECT * FROM inventarioPF.usuario WHERE Nombre = ?  AND Password =?";

            //Creo una variable que al ingresar a la coneccion, me permita agregar corroborar datos
            PreparedStatement ps = conexion.obtenerConnection().prepareStatement(SQL);
            ps.setString(1, usuario);
            ps.setString(2, password);
            //Para obtener los resultados de la consulta SQL creo una variable ResultSet
            ResultSet rs = ps.executeQuery(SQL);
            //Mientras existan coincidencias en la consulta, retornara True, si no encuentra ningun valor, sera false.
            while (rs.next()) {
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();

        }
        throw new UsuarioOContraseñaIncorrectosException("Usuario o Contraseña incorrecto");
    }

    //Metodo para agregar usuario, en caso de que no exista o no se pueda conectar por algun problema, no realizara nada, solo me indicara el problema.
    //verificar usuario, retorna un boolean, recibe un usuario

    public boolean verificarUsuario(Usuario usuario) throws UsuarioNoExistenteException,SinConexionException, SQLException {
        //Creo la consulta en SQL en la cual indico cual sera mi condicion
        final String SQL = "SELECT * FROM inventarioPF.usuario WHERE Rut = ? ";

        //Creo una variable que al ingresar a la coneccion, me permita agregar corroborar datos, con prepate Statement creo mayor seguridad
        PreparedStatement ps = conexion.obtenerConnection().prepareStatement(SQL);
        ps.setString(1, usuario.getRut());
        //Para obtener los resultados de la consulta SQL creo una variable ResultSet
        ResultSet rs = ps.executeQuery(SQL);
        //Mientras existan coincidencias en la consulta, retornara True, si no encuentra ningun valor, sera false.
        while (rs.next()) {
            return true;
        }
        throw new UsuarioNoExistenteException("El usuario no ha sido creado");
    }

    //Metodo que me permitira agregar a un usuario que no se encuentre en la base de datos, retornara void, resivira un usuario
    public void agregarUsuario(Usuario usuario) throws SinConexionException, SQLException, UsuarioNoExistenteException {
        //Verifico que no exista algun usuario con el mismo rut en la base de datos
        if (verificarUsuario(usuario)!=true){
            //En caso de ser diferente a verdadero, procedere a agregar al usuario
            try{
                final String SQL = "INSERT INTO usuario(Rut,Nombre,Correo, Password, Rol, Sucursal_idSucursal)" + "VALUES (?,?,?,?,?,?)";
                PreparedStatement ps = conexion.obtenerConnection().prepareStatement(SQL);
                ps.setString(1, usuario.getNombre());
                ps.setString(2,usuario.getRut());
                ps.setString(3,usuario.getCorreo());
                ps.setString(4,usuario.getPassword());
                ps.setString(5, usuario.getRol());
                ps.setInt(6, usuario.getSucursal().getIdSucursal());
                ps.executeUpdate();
            } catch (SQLException ex){
                ex.printStackTrace();
            }
        }
        System.out.println("Usuario existente");
    }

    public void eliminarUsuarioPorRut(Usuario usuario) throws SinConexionException, UsuarioNoExistenteException, SQLException {
        if (verificarUsuario(usuario) != true) {
            //En caso de ser verdadero, procedere a eliminar al usuario
            System.out.println("Usuario no existente");

        } else {
            try {
                final String SQL = "UPDATE * FROM usuario WHERE Rut = ?";
                PreparedStatement ps = conexion.obtenerConnection().prepareStatement(SQL);
                //Le asigno valor al '?'
                ps.setString(1, usuario.getRut());
                ps.executeUpdate();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

        }

    }

}

package cl.accenture.programatufuturo.proyectofinal.inventario.dao;

import cl.accenture.programatufuturo.proyectofinal.inventario.exception.SinConexionException;
import cl.accenture.programatufuturo.proyectofinal.inventario.exception.UsuarioOContraseñaIncorrectosException;
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


    public boolean login(String usuario, String password) throws UsuarioOContraseñaIncorrectosException, SinConexionException {
        try {
            final String SQL = "SELECT * FROM inventarioPF.usuario WHERE Nombre = ?  AND Password =?";

            PreparedStatement ps = conexion.obtenerConnection().prepareStatement(SQL);
            ps.setString(1, usuario);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery(SQL);
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

    public boolean verificarUsuario(Usuario usuario) throws SinConexionException, SQLException {
        final String SQL = "SELECT * FROM inventariopf.usuario WHERE Rut =? ";
        PreparedStatement ps = conexion.obtenerConnection().prepareStatement(SQL);
        ps.setString(1, usuario.getRut());
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            return true;
        }
        System.out.println("Usuario no encontrado");
        return false;
    }

    public void agregarUsuario(Usuario usuario) throws SinConexionException, SQLException {

        if (verificarUsuario(usuario)!=true){
            System.out.println("Agregar Usuario");

            try{
                final String SQL = "INSERT INTO inventariopf.usuario(Rut,Nombre,Correo, Password, Rol, Sucursal_idSucursal)"+ "VALUES (?,?,?,?,?,?)";
                PreparedStatement ps = conexion.obtenerConnection().prepareStatement(SQL);
                ps.setString(1, usuario.getRut());
                ps.setString(2,usuario.getNombre());
                ps.setString(3,usuario.getCorreo());
                ps.setString(4,usuario.getPassword());
                ps.setString(5, usuario.getRol());
                ps.setInt(6, usuario.getSucursal().getIdSucursal());
                ps.executeUpdate();
            } catch (SQLException ex){
                ex.printStackTrace();
            }
        }else {
            System.out.println("Usuario existente");
        }

    }

    public void eliminarUsuarioPorRut(Usuario usuario) throws SinConexionException, SQLException {
        if (verificarUsuario(usuario)==false){
            //En caso de ser verdadero, procedere a eliminar al usuario
            System.out.println("Usuario no encontrado");

        } else if(verificarUsuario(usuario)==true){
            try{
                final String SQL = "DELETE FROM inventariopf.usuario WHERE Rut = ?";
                PreparedStatement ps = conexion.obtenerConnection().prepareStatement(SQL);
                //Le asigno valor al '?'
                ps.setString(1, usuario.getRut());
                ps.executeUpdate();
                System.out.println("Usuario Eliminado");
            } catch (SQLException ex){
                ex.printStackTrace();
            }


        }

    }

}

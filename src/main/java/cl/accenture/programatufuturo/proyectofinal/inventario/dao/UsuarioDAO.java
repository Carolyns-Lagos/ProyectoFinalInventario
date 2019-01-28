package cl.accenture.programatufuturo.proyectofinal.inventario.dao;

import cl.accenture.programatufuturo.proyectofinal.inventario.exception.SinConexionException;
import cl.accenture.programatufuturo.proyectofinal.inventario.exception.UsuarioOContraseñaIncorrectosException;
import cl.accenture.programatufuturo.proyectofinal.inventario.model.Usuario;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

//Librerias necesarias para el metodo de Encriptacion.


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


    public static String encriptar(String texto){

        String secretKey = "qualityinfosolutions"; //llave para encriptar datos
        String base64EncryptedString = "";

        try {

            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] digestOfPassword = md.digest(secretKey.getBytes("utf-8"));
            byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);

            SecretKey key = new SecretKeySpec(keyBytes, "DESede");
            Cipher cipher = Cipher.getInstance("DESede");
            cipher.init(Cipher.ENCRYPT_MODE, key);

            byte[] plainTextBytes = texto.getBytes("utf-8");
            byte[] buf = cipher.doFinal(plainTextBytes);
            byte[] base64Bytes = Base64.encodeBase64(buf);
            base64EncryptedString = new String(base64Bytes);

        } catch (Exception ex) {
        }
        return base64EncryptedString;
    }

    public static String desencriptar(String textoEncriptado) throws Exception {

        String secretKey = "qualityinfosolutions"; //llave para desenciptar datos
        String base64EncryptedString = "";

        try {
            byte[] message = Base64.decodeBase64(textoEncriptado.getBytes("utf-8"));
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] digestOfPassword = md.digest(secretKey.getBytes("utf-8"));
            byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);
            SecretKey key = new SecretKeySpec(keyBytes, "DESede");

            Cipher decipher = Cipher.getInstance("DESede");
            decipher.init(Cipher.DECRYPT_MODE, key);

            byte[] plainText = decipher.doFinal(message);

            base64EncryptedString = new String(plainText, "UTF-8");

        } catch (Exception ex) {
        }
        return base64EncryptedString;
    }

    public boolean loginVendedor(Usuario usuario) throws UsuarioOContraseñaIncorrectosException, SinConexionException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            final String SQL = "SELECT * FROM inventarioPF.usuario WHERE Rut = ?  AND Password =? ";

            String contraseñaEncriptada= UsuarioDAO.encriptar(usuario.getPassword());
            ps = conexion.obtenerConnection().prepareStatement(SQL);
            ps.setString(1, usuario.getRut());
            ps.setString(2, contraseñaEncriptada);
            rs = ps.executeQuery(SQL);
            UsuarioDAO dao= new UsuarioDAO();
            Usuario usuarioVendedor= dao.buscarUsuarioPorRut(usuario.getRut());
            if (rs.absolute(1) && usuarioVendedor.getRol()=="VENDEDOR") {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (getConexion()!=null) getConexion().getConnection().close();
                if (ps != null) ps.close();
                if (rs !=null) rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        throw new UsuarioOContraseñaIncorrectosException("Usuario o Contraseña Incorrecto");
    }

    public boolean loginNormal(Usuario usuario) throws UsuarioOContraseñaIncorrectosException, SinConexionException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            final String SQL = "SELECT * FROM inventarioPF.usuario WHERE Rut = ?  AND Password =? ";

            String contraseñaEncriptada= UsuarioDAO.encriptar(usuario.getPassword());
            ps = conexion.obtenerConnection().prepareStatement(SQL);
            ps.setString(1, usuario.getRut());
            ps.setString(2, contraseñaEncriptada);
            rs = ps.executeQuery(SQL);
            while(rs.next())
                return true;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (getConexion()!=null) getConexion().getConnection().close();
                if (ps != null) ps.close();
                if (rs !=null) rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        throw new UsuarioOContraseñaIncorrectosException("Usuario o Contraseña Incorrecto");
    }

    public boolean loginAdministrador(Usuario usuario) throws UsuarioOContraseñaIncorrectosException, SinConexionException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            final String SQL = "SELECT * FROM inventarioPF.usuario WHERE Rut = ?  AND Password =? ";

            String contraseñaEncriptada= UsuarioDAO.encriptar(usuario.getPassword());
            ps = conexion.obtenerConnection().prepareStatement(SQL);
            ps.setString(1, usuario.getRut());
            ps.setString(2, contraseñaEncriptada);
            rs = ps.executeQuery(SQL);
            UsuarioDAO dao= new UsuarioDAO();
            Usuario usuarioVendedor= dao.buscarUsuarioPorRut(usuario.getRut());
            if (rs.absolute(1) && usuarioVendedor.getRol()=="ADMINISTRADOR") {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (getConexion()!=null) getConexion().getConnection().close();
                if (ps != null) ps.close();
                if (rs !=null) rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        throw new UsuarioOContraseñaIncorrectosException("Usuario o Contraseña Incorrecto");
    }


    //Metodo para agregar usuario, en caso de que no exista o no se pueda conectar por algun problema, no realizara nada, solo me indicara el problema.
    //verificar usuario, retorna un boolean, recibe un usuario

    public boolean verificarUsuario(Usuario usuario) throws SinConexionException, SQLException {
        PreparedStatement ps= null;
        ResultSet rs= null;
        try {
            final String SQL = "SELECT * FROM inventariopf.usuario WHERE Rut =? AND Password= ?";
            ps = conexion.obtenerConnection().prepareStatement(SQL);
            ps.setString(1, usuario.getRut());
            ps.setString(2, UsuarioDAO.encriptar(usuario.getPassword()));
            rs = ps.executeQuery();
            if (rs.absolute(1)) {
                return true;
            }
        }catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (getConexion()!=null) getConexion().getConnection().close();
                if (ps != null) ps.close();
                if (rs !=null) rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public void agregarUsuario(Usuario usuario) throws SinConexionException, SQLException {
        PreparedStatement ps=null;
        if (verificarUsuario(usuario)==false){
            System.out.println("Agregar Usuario");
            try{
                final String SQL = "INSERT INTO inventariopf.usuario( Rut, Nombre, Correo,Telefono, Password, Rol, Sucursal_idSucursal)"+ "VALUES (?,?,?,?,?,?,?)";
                ps = conexion.obtenerConnection().prepareStatement(SQL);
                ps.setString(1,usuario.getRut());
                ps.setString(2,usuario.getNombre());
                ps.setString(3,usuario.getCorreo());
                ps.setInt(4, usuario.getTelefono());
                ps.setString(5,UsuarioDAO.encriptar(usuario.getPassword()));
                ps.setString(6,usuario.getRol());
                ps.setInt(7, SucursalDAO.obtenerIDSucursall(this.conexion, usuario.getSucursal()));
                ps.executeUpdate();
                getConexion().getConnection().close();
                ps.close();

            }catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public Usuario buscarUsuarioPorRut(String rut) throws SinConexionException, SQLException {
        Usuario user=new Usuario();
        final String SQL = "SELECT * FROM inventariopf.usuario WHERE Rut =? ";
        PreparedStatement ps = conexion.obtenerConnection().prepareStatement(SQL);
        ps.setString(1, rut);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {

            user.setRut(rs.getString(1));
            user.setNombre(rs.getString(2));
            user.setCorreo(rs.getString(3));
            user.setTelefono(rs.getInt(4));
            user.setPassword(rs.getString(5));
            user.setRol(rs.getString(6));
            user.getSucursal().setIdSucursal(rs.getInt(7));
            return user;
        }
        System.out.println("Usuario no encontrado");
        return null;
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

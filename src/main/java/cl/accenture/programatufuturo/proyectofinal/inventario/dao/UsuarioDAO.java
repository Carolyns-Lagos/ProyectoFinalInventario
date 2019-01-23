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

    public boolean login(Usuario usuario) throws UsuarioOContraseñaIncorrectosException, SinConexionException {
        try {
            final String SQL = "SELECT * FROM inventarioPF.usuario WHERE Nombre = ?  AND Password =?";

            String contraseñaEncriptada= UsuarioDAO.encriptar(usuario.getPassword());
            PreparedStatement ps = conexion.obtenerConnection().prepareStatement(SQL);
            ps.setString(1, usuario.getNombre());
            ps.setString(2, contraseñaEncriptada);
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

        if (verificarUsuario(usuario)==false){
            System.out.println("Agregar Usuario");
            try{
                final String SQL = "INSERT INTO inventariopf.usuario( Rut, Nombre, Correo,Telefono, Password, Rol, Sucursal_idSucursal)"+ "VALUES (?,?,?,?,?,?,?)";
                PreparedStatement ps = conexion.obtenerConnection().prepareStatement(SQL);
                ps.setString(1,usuario.getRut());
                ps.setString(2,usuario.getNombre());
                ps.setString(3,usuario.getCorreo());
                ps.setInt(4, usuario.getTelefono());
                ps.setString(5,UsuarioDAO.encriptar(usuario.getPassword()));
                ps.setString(6,usuario.getRol());
                ps.setInt(7, Suc.obtenerIDSucursall(this.conexion, usuario.getSucursal()));
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

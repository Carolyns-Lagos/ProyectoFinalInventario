package cl.accenture.programatufuturo.proyectofinal.inventario.controller;


import cl.accenture.programatufuturo.proyectofinal.inventario.dao.UsuarioDAO;
import cl.accenture.programatufuturo.proyectofinal.inventario.exception.SinConexionException;
import cl.accenture.programatufuturo.proyectofinal.inventario.exception.UsuarioOContraseñaIncorrectosException;
import cl.accenture.programatufuturo.proyectofinal.inventario.model.Sucursal;
import cl.accenture.programatufuturo.proyectofinal.inventario.model.Usuario;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@CrossOrigin(origins="*")
@RequestMapping("/usuarios")
@RestController
public class UsuarioControler {

    Sucursal sucursal = new Sucursal("LLuvia", "Los Leones", "Providencia", "Providencia 123");

    @CrossOrigin(origins="*")
    @RequestMapping(method = RequestMethod.POST ,
            value= "/loginAdministrador")  //localhost:8080/usuarios/login
    //Recibire el rut y la contraseña enviada desde la app
    public Usuario loginInicialAdministrador(@RequestBody Usuario usuario) throws SQLException {
        try {
            UsuarioDAO dao = new UsuarioDAO();
            //Verificare si existe, si es asi sera true, si no false
            Usuario newUsuario= dao.buscarUsuarioPorRut(usuario.getRut());
            if (dao.loginAdministrador(usuario)== true){
                //Si existe retornare el usuario con todos los atributos para que lo puedan ocupar luego en html
                return newUsuario;
            }
        } catch (SinConexionException e) {
            e.printStackTrace();
        } catch (UsuarioOContraseñaIncorrectosException e) {
            e.printStackTrace();
        }
        //Si no esta Les retornare nada
        return null;
    }

    @CrossOrigin(origins="*")
    @RequestMapping(method = RequestMethod.POST ,
            value= "/loginVendedor")
    //Recibire el rut y la contraseña enviada desde la app
    public Usuario loginInicialVendedor(@RequestBody Usuario usuario) throws SQLException {
        try {
            UsuarioDAO dao = new UsuarioDAO();
            //Verificare si existe, si es asi sera true, si no false
            Usuario newUsuario= dao.buscarUsuarioPorRut(usuario.getRut());
            if (dao.loginVendedor(usuario)== true){
                //Si existe retornare el usuario con todos los atributos para que lo puedan ocupar luego en html
                return newUsuario;
            }
        } catch (SinConexionException e) {
            e.printStackTrace();
        } catch (UsuarioOContraseñaIncorrectosException e) {
            e.printStackTrace();
        }
        //Si no esta Les retornare nada
        return null;
    }

    @CrossOrigin(origins="*")
    @PostMapping("/agregarUsuarioVendedor")
    public Usuario agregarUsuarioVendedor(@RequestBody Usuario usuario) {
        try{
            Usuario newUsuario= new Usuario(usuario.getRut(),usuario.getNombre(),usuario.getCorreo(), usuario.getTelefono(), usuario.getPassword(), "VENDEDOR", sucursal);
            UsuarioDAO dao = new UsuarioDAO();
            dao.agregarUsuario(newUsuario);
            System.out.println("Usuario agregado");
        } catch (SinConexionException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuario;


    }
}

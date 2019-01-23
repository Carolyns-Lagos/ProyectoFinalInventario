package cl.accenture.programatufuturo.proyectofinal.inventario.controller;


import cl.accenture.programatufuturo.proyectofinal.inventario.dao.Suc;
import cl.accenture.programatufuturo.proyectofinal.inventario.dao.UsuarioDAO;
import cl.accenture.programatufuturo.proyectofinal.inventario.exception.SinConexionException;
import cl.accenture.programatufuturo.proyectofinal.inventario.exception.UsuarioOContraseñaIncorrectosException;
import cl.accenture.programatufuturo.proyectofinal.inventario.model.Sucursal;
import cl.accenture.programatufuturo.proyectofinal.inventario.model.Usuario;
import org.springframework.web.bind.annotation.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@CrossOrigin(origins="*")
@RequestMapping("/usuarios")
@RestController
public class UsuarioControler {

    Sucursal sucursal = new Sucursal("LLuvia", "Los Leones", "Providencia", "Providencia 123");

    @CrossOrigin
    @RequestMapping(method = RequestMethod.POST ,
            value= "/login")  //localhost:8080/usuarios/login
    public void loginInicial(@RequestBody Usuario usuario) {
        try {
            UsuarioDAO dao = new UsuarioDAO();
            dao.login(usuario);
            System.out.println("usuario creado ");
        } catch (SinConexionException e) {
            e.printStackTrace();
        } catch (UsuarioOContraseñaIncorrectosException e) {
            e.printStackTrace();
        }
    }
    @CrossOrigin(origins="*")
    @PostMapping("/agregarUsuarioVendedor")
    public void agregarUsuarioVendedor(@RequestBody Usuario usuario) {

        try{
            Usuario newUsuario= new Usuario(usuario.getRut(),usuario.getNombre(),usuario.getCorreo(), usuario.getTelefono(), usuario.getPassword(), "VENDEDOR", sucursal);
            System.out.println("Usuario Actualizado");
            UsuarioDAO dao = new UsuarioDAO();
            System.out.println("Nombre: "+usuario.getNombre());
            dao.agregarUsuario(usuario);
            System.out.println("Usuario agregado");
        } catch (SinConexionException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}

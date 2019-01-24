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
    @PutMapping("/agregarUsuarioVendedor")
    public String agregarUsuarioVendedor(@RequestBody Usuario usuario) {

        try{
            Usuario newUsuario= new Usuario(usuario.getRut(),usuario.getNombre(),usuario.getCorreo(), usuario.getTelefono(), usuario.getPassword(), "VENDEDOR", sucursal);
            System.out.println("Usuario Actualizado");
            UsuarioDAO dao = new UsuarioDAO();
            System.out.println("Nombre: "+usuario.getNombre());
            System.out.println("Rut ="+ usuario.getRut());
            System.out.println("correo ="+ usuario.getCorreo());
            System.out.println("Telefono ="+ usuario.getTelefono());
            System.out.println("Pasword ="+ usuario.getPassword());
            dao.agregarUsuario(newUsuario);
            System.out.println("Usuario agregado");
        } catch (SinConexionException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "Usuario Agregado Con exito";


    }
}

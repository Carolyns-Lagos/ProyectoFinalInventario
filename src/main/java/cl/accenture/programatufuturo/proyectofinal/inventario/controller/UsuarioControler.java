package cl.accenture.programatufuturo.proyectofinal.inventario.controller;


import cl.accenture.programatufuturo.proyectofinal.inventario.dao.Suc;
import cl.accenture.programatufuturo.proyectofinal.inventario.dao.UsuarioDAO;
import cl.accenture.programatufuturo.proyectofinal.inventario.exception.SinConexionException;
import cl.accenture.programatufuturo.proyectofinal.inventario.exception.UsuarioOContraseñaIncorrectosException;
import cl.accenture.programatufuturo.proyectofinal.inventario.model.Usuario;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


@RestController
public class UsuarioControler {

    @RequestMapping(method = RequestMethod.POST ,
            value= "/login")
    public void loginInicial(@RequestBody Usuario usuario) {
        try {
            UsuarioDAO dao = new UsuarioDAO();
            dao.login(usuario);

        } catch (SinConexionException e) {
            e.printStackTrace();
        } catch (UsuarioOContraseñaIncorrectosException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(method = RequestMethod.PUT,
            value="/agregarUsuario")
    public void agregarUsuario(@RequestBody Usuario usuario) {

        try{
            UsuarioDAO dao = new UsuarioDAO();
            dao.agregarUsuario(usuario);
        } catch (SinConexionException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}

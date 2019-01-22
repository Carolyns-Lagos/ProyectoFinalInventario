package cl.accenture.programatufuturo.proyectofinal.inventario;

import cl.accenture.programatufuturo.proyectofinal.inventario.dao.*;
import cl.accenture.programatufuturo.proyectofinal.inventario.model.Producto;
import cl.accenture.programatufuturo.proyectofinal.inventario.model.Sucursal;
import cl.accenture.programatufuturo.proyectofinal.inventario.model.Usuario;

import java.util.ArrayList;

public class ProgramaPrueba {

    public static void main(String[] args) throws Exception {

        //Prueba Sucursal

        Sucursal sucursal = new Sucursal("LLuvia", "Los Leones", "Providencia", "Providencia 123");
//        Suc daoSuc= new Suc();
//        dao.agregarSucursall(sucursal);
//        daoSuc.eliminarSucursalPorNombre(sucursal);

        //Prueba Metodos Usuario



        UsuarioDAO daoUsers = new UsuarioDAO();
        Usuario usuario = new Usuario("1855588", "Jua Perez", "hola@gmail.com", "1234aa", "VENDEDOR", sucursal );

        daoUsers.agregarUsuario(usuario);

        daoUsers.eliminarUsuarioPorRut(usuario);

        //Prueba Sucursal



        //Prueba encriptar

        String contraseña= "61756550";
        String contraseñaEncriptada= UsuarioDAO.encriptar(contraseña);
        System.out.println("Contraseña Original="+contraseña);
        System.out.println("Contraseña Encriptada="+contraseñaEncriptada);
        String contraseñaDesencriptada=UsuarioDAO.desencriptar(contraseñaEncriptada);
        System.out.println("Contraseña Desencriptada="+contraseñaDesencriptada);

        String contraseña2= "61756550";
        String contraseñaEncriptada2= UsuarioDAO.encriptar(contraseña2);
        System.out.println("Contraseña Original="+contraseña2);
        System.out.println("Contraseña Encriptada="+contraseñaEncriptada2);
        String contraseñaDesencriptada2=UsuarioDAO.desencriptar(contraseñaEncriptada2);
        System.out.println("Contraseña Desencriptada="+contraseñaDesencriptada2);


    }
}

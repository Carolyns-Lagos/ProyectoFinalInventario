package cl.accenture.programatufuturo.proyectofinal.inventario;

import cl.accenture.programatufuturo.proyectofinal.inventario.dao.UsuarioDAO;
import cl.accenture.programatufuturo.proyectofinal.inventario.model.Producto;
import cl.accenture.programatufuturo.proyectofinal.inventario.model.Sucursal;
import cl.accenture.programatufuturo.proyectofinal.inventario.model.Usuario;

import java.util.ArrayList;

public class ProgramaPrueba {

    public static void main(String[] args) throws Exception {


        //Prueba Metodos Usuario

        ArrayList<Producto> p= new ArrayList<>(1);
        Sucursal sucursal = new Sucursal(1, "Los Leones", "Providencia", "Providencia", p);
        UsuarioDAO dao = new UsuarioDAO();
        Usuario usuario = new Usuario("1235568","Jua Perez","hola@gmail.com", "1234aa", "VENDEDOR", sucursal  );

        //dao.agregarUsuario(usuario);

        dao.eliminarUsuarioPorRut(usuario);

    }
}

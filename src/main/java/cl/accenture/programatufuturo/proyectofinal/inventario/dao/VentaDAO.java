package cl.accenture.programatufuturo.proyectofinal.inventario.dao;

import cl.accenture.programatufuturo.proyectofinal.inventario.exception.SinConexionException;
import cl.accenture.programatufuturo.proyectofinal.inventario.model.Boleta;
import cl.accenture.programatufuturo.proyectofinal.inventario.model.Producto;
import cl.accenture.programatufuturo.proyectofinal.inventario.model.Usuario;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class VentaDAO {

    private Conexion conexion;


    public VentaDAO(){

        this.conexion=new Conexion();
    }


    //Constructores get y ser

    public Conexion getConexion() {

        return this.conexion;
    }

    public void setConexion(Conexion conexion) {

        this.conexion = conexion;
    }

    public void ingresarVenta(Producto producto, int cantidad) throws SinConexionException {
        //Este seria el usuario que obtenemos en el login
        Usuario usuario = new Usuario("1888","Jua Perez","hola@gmail.com",5558684,"1234aa");
        //La boleta se supone que se crea al mismo tiempo que la venta
        Boleta boleta= new Boleta(usuario);
        BoletaDAO dao= new BoletaDAO();
        dao.agregarBoleta(boleta);
        int precioVenta= (int)(producto.getPrecio()*0.25);
        try{
            final String SQL = "INSERT INTO inventariopf.venta( Boleta_idBoleta, Producto_idProducto, Precio_Venta, Cantidad_Compra)"+ "VALUES (?,?,?,?)";
            PreparedStatement ps = conexion.obtenerConnection().prepareStatement(SQL);
            ps.setInt(1,boleta.getIdBoleta());
            ps.setInt(2,Prod.obtenerIDProducto(this.conexion,producto));
            ps.setInt(3,precioVenta);
            ps.setInt(4, cantidad);
            ps.executeUpdate();
        } catch (SQLException ex){
            ex.printStackTrace();
        } catch (SinConexionException e) {
            e.printStackTrace();
        }
    }
}

package cl.accenture.programatufuturo.proyectofinal.inventario.dao;

import cl.accenture.programatufuturo.proyectofinal.inventario.exception.SinConexionException;
import cl.accenture.programatufuturo.proyectofinal.inventario.model.Boleta;
import cl.accenture.programatufuturo.proyectofinal.inventario.model.Producto;
import cl.accenture.programatufuturo.proyectofinal.inventario.model.Venta;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

    public int calculatPrecioParaBoleta(List<Venta> ventas){
        int totalBoleta=0;
        ProductoDAO dao= new ProductoDAO();
        VentaDAO dao1 = new VentaDAO();
        for (int i = 0; i < ventas.size(); i++) {
            int totalVenta=ventas.get(i).getTotalVenta();
            totalBoleta+=totalVenta;
        }
        return totalBoleta;
    }

    public static boolean confirmarProductoEnVenta(int idProducto, int cantidadAVender) throws SinConexionException {
        ProductoDAO productoDao = new ProductoDAO();
        Producto productoIngresado=productoDao.buscarProductoPorId(idProducto);
        //Cantidad Max vendria siendo nuestro Stock actual
        if (cantidadAVender<=productoIngresado.getCantidadMax()){
            return true;
        } else if(cantidadAVender>productoIngresado.getCantidadMax()){
            System.out.println("No es posible Registrar el roducto con id " + idProducto+
                    "en la venta debido a que intenta vender un numero superior al stock disponible"  );
        }
        return false;
    }

    public List<Venta> buscarVentasPorBoleta(Boleta boleta) throws SinConexionException{
        List<Venta> ventasPorBoleta = new ArrayList<Venta>();
        try {
            final String SQL = "SELECT * FROM inventariopf.venta WHERE Boleta_idBoleta= ?";
            PreparedStatement ps = conexion.obtenerConnection().prepareStatement(SQL);
            ps.setInt(1, boleta.getIdBoleta());
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                Venta ventas= new Venta();
                ventas.getBoleta().setIdBoleta(rs.getInt(1));
                ventas.getProducto().setIdProducto( rs.getInt(2));
                ventas.setPrecioVenta( rs.getInt(3));
                ventas.setCantidadCompra( rs.getInt(4));
                ventas.setTotalVenta( rs.getInt(5));
                ventasPorBoleta.add(ventas);
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return ventasPorBoleta;
    }

    //Esto seria por 1 producto
    public void ingresarVenta(Venta ventaProducto, Boleta botetaVenta) throws SinConexionException {
        ventaProducto.setBoleta(botetaVenta);
        botetaVenta.setIdBoleta(BoletaDAO.obtenerIDBoleta(this.conexion,ventaProducto.getBoleta()));
        try{
            final String SQL = "INSERT INTO inventariopf.venta( Boleta_idBoleta, Producto_idProducto, Precio_Venta, Cantidad_Compra)"+ "VALUES (?,?,?,?)";
            PreparedStatement ps = conexion.obtenerConnection().prepareStatement(SQL);
            ps.setInt(1,ventaProducto.getBoleta().getIdBoleta());
            ps.setInt(2, ProductoDAO.obtenerIDProducto(this.conexion, ventaProducto.getProducto()));
            ps.setInt(3,ventaProducto.getPrecioVenta());
            ps.setInt(4, ventaProducto.getCantidadCompra());
            ps.executeUpdate();
        } catch (SQLException ex){
            ex.printStackTrace();
        }
    }
}

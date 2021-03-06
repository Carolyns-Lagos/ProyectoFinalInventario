package cl.accenture.programatufuturo.proyectofinal.inventario.dao;

import cl.accenture.programatufuturo.proyectofinal.inventario.exception.SinConexionException;
import cl.accenture.programatufuturo.proyectofinal.inventario.model.Producto;
import cl.accenture.programatufuturo.proyectofinal.inventario.model.Venta;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ProductoDAO {
    private Conexion conexion;

    public ProductoDAO(){

        this.conexion=new Conexion();
    }

    //Constructores get y ser

    public Conexion getConexion() {

        return this.conexion;
    }

    public void setConexion(Conexion conexion) {

        this.conexion = conexion;
    }


    //Metodos

    public static int obtenerIDProducto(Conexion conexion, Producto producto) throws SinConexionException {
        int idBol=0;
        try{
            final String SQL = "SELECT idProducto from inventariopf.producto WHERE Nombre=? and Caracteristica=? and Cantidad_min=? and Cantidad_Max=? and Precio=? and Marca=? and Categoria_Producto=?";
            PreparedStatement ps = conexion.obtenerConnection().prepareStatement(SQL);
            ps.setString(1,producto.getNombre());
            ps.setString(2,producto.getCaracteristica());
            ps.setInt(3,producto.getCantidadMin());
            ps.setInt(4,producto.getCantidadMax());
            ps.setInt(5,producto.getPrecio());
            ps.setString(6,producto.getMarca());
            ps.setString(7,producto.getCategoria());
            ResultSet rs = ps.executeQuery();
            rs.next();
            idBol = rs.getInt("idBoleta");
        }catch (SQLException e){
            e.printStackTrace();
        }
        return idBol;

    }

    public Producto buscarProductoPorId(int id) throws SinConexionException {
        Producto prod=new Producto();
        try {
            final String SQL = "SELECT * FROM inventariopf.producto WHERE idProducto =? ";
            PreparedStatement ps = conexion.obtenerConnection().prepareStatement(SQL);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                prod.setIdProducto(rs.getInt(1));
                prod.setNombre(rs.getString(2));
                prod.setCaracteristica(rs.getString(3));
                prod.setCantidadMin(rs.getInt(4));
                prod.setCantidadMax(rs.getInt(5));
                prod.setPrecio(rs.getInt(6));
                prod.setMarca(rs.getString(7));
                prod.setCategoria(rs.getString(8));
                return prod;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("Usuario no encontrado");
        return null;
    }



    public void actualizarStock(List<Venta> ventasporBoleta) throws SinConexionException {

        for (int i = 0; i <ventasporBoleta.size() ; i++) {
            try{
                final String SQL1 = "SELECT Cantidad_Max FROM inventariopf.producto WHERE idProducto= ? ";
                PreparedStatement ps1 = conexion.obtenerConnection().prepareStatement(SQL1);
                ps1.setInt(1, ventasporBoleta.get(i).getProducto().getIdProducto() );
                ResultSet rs1 = ps1.executeQuery();
                rs1.next();
                int stockActual = rs1.getInt("Cantidad_Max");
                int cantidadVenta= ventasporBoleta.get(i).getCantidadCompra();
                int cantidadFinal=stockActual-cantidadVenta;
                final String SQL2 = "UPDATE producto SET Cantidad_Max= ? WHERE idProducto =?";
                PreparedStatement ps = conexion.obtenerConnection().prepareStatement(SQL2);
                ps.setInt(1, cantidadFinal);
                ps.setInt(2,ventasporBoleta.get(i).getProducto().getIdProducto());
                ResultSet rs2 = ps.executeQuery();
                rs2.next();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }



//    public void descontarStock(Producto producto, Boleta boleta) throws SinConexionException, SQLException {
//        String SQLcodigoProducto= "SELECT Producto_idProducto from inventariopf.venta where Boleta_idBoleta = ?";
//        PreparedStatement ps = conexion.obtenerConnection().prepareStatement(SQLcodigoProducto);
//        ps.setInt(1, venta.getBoleta().getIdBoleta());
//    }
//
//    void descontarstockkk(String codi,String can)
//    {
//        int des = Integer.parseInt(can);
//        String cap="";
//        int desfinal;
//        String consul="SELECT * FROM producto WHERE  cod_pro='"+codi+"'";
//        try {
//            Statement st= cn.createStatement();
//            ResultSet rs= st.executeQuery(consul);
//            while(rs.next())
//            {
//                cap= rs.getString(4);
//            }
//        } catch (Exception e) {
//        }
//        desfinal=Integer.parseInt(cap)-des;
//        String modi="UPDATE producto SET Stock='"+desfinal+"' WHERE cod_pro = '"+codi+"'";
//        try {
//            PreparedStatement pst = cn.prepareStatement(modi);
//            pst.executeUpdate();
//        } catch (Exception e) {
//        }
//
//
//
//    }











}

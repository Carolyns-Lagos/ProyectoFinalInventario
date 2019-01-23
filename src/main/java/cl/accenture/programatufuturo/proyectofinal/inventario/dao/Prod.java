package cl.accenture.programatufuturo.proyectofinal.inventario.dao;

import cl.accenture.programatufuturo.proyectofinal.inventario.exception.SinConexionException;
import cl.accenture.programatufuturo.proyectofinal.inventario.model.Producto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Prod {
    private Conexion conexion;

    public Prod(){

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

//    public void descontarStock(Producto producto, Boleta boleta) throws SinConexionException, SQLException {
//        String SQLcodigoProducto= "SELECT Producto_idProducto from inventariopf.venta where Boleta_idBoleta = ?";
//        PreparedStatement ps = conexion.obtenerConnection().prepareStatement(SQLcodigoProducto);
//        ps.setInt(1, venta.getBoleta().getIdBoleta());
//
//
//
//
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
//
//
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

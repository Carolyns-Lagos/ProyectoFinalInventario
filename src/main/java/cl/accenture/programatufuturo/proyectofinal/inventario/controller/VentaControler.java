package cl.accenture.programatufuturo.proyectofinal.inventario.controller;

import cl.accenture.programatufuturo.proyectofinal.inventario.dao.BoletaDAO;
import cl.accenture.programatufuturo.proyectofinal.inventario.dao.ProductoDAO;
import cl.accenture.programatufuturo.proyectofinal.inventario.dao.VentaDAO;
import cl.accenture.programatufuturo.proyectofinal.inventario.exception.SinConexionException;
import cl.accenture.programatufuturo.proyectofinal.inventario.model.Boleta;
import cl.accenture.programatufuturo.proyectofinal.inventario.model.Producto;
import cl.accenture.programatufuturo.proyectofinal.inventario.model.Usuario;
import cl.accenture.programatufuturo.proyectofinal.inventario.model.Venta;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@CrossOrigin(origins="*")
@RequestMapping("/venta")
@RestController
public class VentaControler {


//    @GetMapping ("/obtenerProductosVenta")
//    public List<Venta> crearListaVentaAPartirDelIdProducto( @PathVariable("id") int idProducto, int cantidadAVender){
//        List<Venta> listaProductosAVender=new LinkedList<Venta>();
//        try {
//            ProductoDAO productoDao = new ProductoDAO();
//            Producto productoIngresado=productoDao.buscarProductoPorId(idProducto);
//            for (int i = 0; i < listaProductosAVender.size(); i++) {
//                if (VentaDAO.confirmarProductoEnVenta(idProducto,cantidadAVender)==true){
//                    Venta ventaAAgregar= new Venta(productoIngresado,cantidadAVender);
//                    listaProductosAVender.add(ventaAAgregar);
//                } else{
//                    System.out.println("No es posible Registrar el roducto numero " + i+1+
//                            "en la comprta debido a que intenta vender un numero superior al stock disponible"  );
//                }
//            }
//        }catch (Exception ex){
//            ex.printStackTrace();
//            return null;
//        }
//    }



    @CrossOrigin(origins="*")
    @PostMapping("/ingresarProducto")
    public Producto idProducto(@RequestBody Producto product) throws SinConexionException, SQLException {
        ProductoDAO producto=new ProductoDAO();
        Producto p=producto.buscarProductoPorId(product.getIdProducto());
        return p;
    }

    @CrossOrigin(origins="*")
    @PostMapping("/buscarProducto")
    public Producto buscarProducto(@RequestBody Producto product) throws SinConexionException, SQLException {
        ProductoDAO producto=new ProductoDAO();
        Producto p=producto.buscarProductoPorId(product.getIdProducto());
        return p;
    }


    @CrossOrigin(origins="*")
    @PostMapping("/ingresarVenta")
    //Recibire el usuario con el cual se ingreso al login y una lista de ventas de la pantalla ventas
    public String vender(@RequestBody Usuario userLogin, @RequestBody List<Venta> ventas ){
        try {
            //Creo los objetos dao para ocupar sus metodos
            VentaDAO ventaDao = new VentaDAO();
            /*Para este caso, sumo el total de los productos que se encuentran en la lista, por ejemplo vendere 2
            productos que valen 1000 y 1 que valga 3000, el total del primer prooducto seria 2000 y del segunto 3000
            asi que sumare los precios finales para entregarselos a la boleta como su total= 5000
            */
            int precioBoleta=ventaDao.calculatPrecioParaBoleta(ventas);
            //Creo el objeto boleta, para el cual solo necesito el usuario de momento
            Boleta boletaVenta= new Boleta(userLogin);
            BoletaDAO boletaDao= new BoletaDAO();
            /*Luego de ello ingreso la boleta a la base de datos, en este caso si o si necesito el precio final,
             por lo cual los 2 atributos que recibe son la boleta que cree y el precio que calculamos
            */
            boletaDao.agregarBoleta(boletaVenta,precioBoleta);
            /* Teniendo la boleta ingresada en la base de datos, puedo ingresar la venta debido a que necesito si o si
             el id de la venta, recorro la lista ingresada, ingresare un dato por cada producto de la lista, asociado solo
             a un numero de boleta
            * */
            for (int i = 0; i < ventas.size() ; i++) {
                Venta vent= new Venta(ventas.get(i).getProducto(), ventas.get(i).getCantidadCompra());
                ventaDao.ingresarVenta(vent, boletaVenta);
            }
            //Confirmada la Venta, se Procede a Actualizar el Stock
            ProductoDAO productoDao= new ProductoDAO();
            /*Creare una lista interna de ventas, con todos los atributos para tener libre acceso los id de Producto*/
            List<Venta> lista=ventaDao.buscarVentasPorBoleta(boletaVenta);
            /* Posterior a ello aplico el metodo para descontar Stock, el cual recibira la Lista Creada Anteriormente
            */
            productoDao.actualizarStock(lista);
            return "Venta Exitosa";
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return "La venta no se ha podido realizar";
    }
}

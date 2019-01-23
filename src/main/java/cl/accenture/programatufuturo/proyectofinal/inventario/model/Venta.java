package cl.accenture.programatufuturo.proyectofinal.inventario.model;

public class Venta {

    private Boleta boleta;
    private Producto producto;
    private int precioVenta, cantidadCompra, totalVenta;

    //Constructotes

    public Venta() {}

    public Venta(Producto producto, int precioVenta, int cantidadCompra) {
        this.producto = producto;
        this.precioVenta = precioVenta;
        this.cantidadCompra = cantidadCompra;
        this.totalVenta=precioVenta*cantidadCompra;
    }

    //Metodo get y set


    public Boleta getBoleta() {
        return this.boleta;
    }

    public void setBoleta(Boleta boleta) {
        this.boleta = boleta;
    }

    public Producto getProducto() {
        return this.producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public int getPrecioVenta() {
        return this.precioVenta;
    }

    public void setPrecioVenta(int precioVenta) {
        this.precioVenta = precioVenta;
    }

    public int getCantidadCompra() {
        return this.cantidadCompra;
    }

    public void setCantidadCompra(int cantidadCompra) {
        this.cantidadCompra = cantidadCompra;
    }
}

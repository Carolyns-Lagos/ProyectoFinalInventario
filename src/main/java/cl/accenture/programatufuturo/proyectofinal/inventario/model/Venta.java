package cl.accenture.programatufuturo.proyectofinal.inventario.model;

import java.util.ArrayList;
import java.util.Date;

public class Venta {

    //Atributos clase ventas
    private int idVenta;
    private Usuario usuario;
    private Sucursal sucursal;
    private Date date;
    private ArrayList<Producto> listaDeProductos;

    //Constructores basicos

    public Venta() {
    }

    public Venta(int idVenta, Usuario usuario, Sucursal sucursal, Date date, ArrayList<Producto> listaDeProductos) {
        this.idVenta = idVenta;
        this.usuario = usuario;
        this.sucursal = sucursal;
        this.date = date;
        this.listaDeProductos = listaDeProductos;
    }

    //set y get

    public int getIdVenta() {
        return this.idVenta;
    }

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }

    public Usuario getUsuario() {
        return this.usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Sucursal getSucursal() {
        return this.sucursal;
    }

    public void setSucursal(Sucursal sucursal) {
        this.sucursal = sucursal;
    }

    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public ArrayList<Producto> getListaDeProductos() {
        return this.listaDeProductos;
    }

    public void setListaDeProductos(ArrayList<Producto> listaDeProductos) {
        this.listaDeProductos = listaDeProductos;
    }
}

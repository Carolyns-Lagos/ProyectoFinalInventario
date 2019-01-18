package cl.accenture.programatufuturo.proyectofinal.inventario.model;

import java.util.ArrayList;

public class Sucursal {

    //Atributos de la clase Sucursal
    //el id tengo que generarlo con Springbood
    private int idSucursal;
    private String ubicacion, comuna, direccion;
    //Para representar la relacion de muchos a muchos con producto se crea una lista, una socursal posee muchos productos
    private ArrayList<Producto> listaProductos;

    //Constructores

    public Sucursal() {

    }

    public Sucursal(int idSucursal, String ubicacion, String comuna, String direccion, ArrayList<Producto> productos) {
        this.idSucursal = idSucursal;
        this.ubicacion = ubicacion;
        this.comuna = comuna;
        this.direccion = direccion;
        this.listaProductos=productos;
    }

    //Metodos Get y Set

    public int getIdSucursal() {
        return this.idSucursal;
    }

    public void setIdSucursal(int idSucursal) {
        this.idSucursal = idSucursal;
    }

    public String getUbicacion() {
        return this.ubicacion;
    }

    public void setUbicacion(String nuevaUbicacion) {
        this.ubicacion = nuevaUbicacion;
    }

    public String getComuna() {
        return this.comuna;
    }

    public void setComuna(String nuevaComuna) {
        this.comuna = nuevaComuna;
    }

    public String getDireccion() {
        return this.direccion;
    }

    public void setDireccion(String nuevaDireccion) {
        this.direccion = nuevaDireccion;
    }

    public ArrayList<Producto> getListaProductos() {
        return this.listaProductos;
    }

    public void setListaProductos(ArrayList<Producto> nuevaListaProductos) {
        this.listaProductos = nuevaListaProductos;
    }

}

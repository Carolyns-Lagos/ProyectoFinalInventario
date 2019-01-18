package cl.accenture.programatufuturo.proyectofinal.inventario.model;

import java.util.ArrayList;

public class Producto {

    //Atributos de la clase Usuario
    private int idProducto;
    private String nombre, caracteristica;
    private int cantidadMin, cantidadMax, precio;
    private String  marca, categoria;
    //Para representar la relacion de muchos a muchos con producto se crea una lista, una socursal posee muchos productos
    private ArrayList<Sucursal> listaSucursales;
    private ArrayList<Venta> listaDeVentas;

    //Constructores

    public Producto() {

    }


    public Producto(int idProducto, String nombre, String caracteristica, int cantidadMin, int cantidadMax, int precio, String marca, String categoria, ArrayList<Sucursal> sucursales, ArrayList<Venta> listaDeVentas) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.caracteristica = caracteristica;
        this.cantidadMin = cantidadMin;
        this.cantidadMax = cantidadMax;
        this.precio = precio;
        this.marca = marca;
        this.categoria = categoria;
        this.listaSucursales = sucursales;
        this.listaDeVentas = listaDeVentas;
    }

    public Producto(String nombre, String caracteristica, int cantidadMin, int cantidadMax, int precio, String marca, String categoria, ArrayList<Sucursal> listaSucursales, ArrayList<Venta> listaDeVentas) {
        this.nombre = nombre;
        this.caracteristica = caracteristica;
        this.cantidadMin = cantidadMin;
        this.cantidadMax = cantidadMax;
        this.precio = precio;
        this.marca = marca;
        this.categoria = categoria;
        this.listaSucursales = listaSucursales;
        this.listaDeVentas = listaDeVentas;
    }


    //Metodos Get y Set


    public int getCantidadMin() {
        return this.cantidadMin;
    }

    public void setCantidadMin(int nuevaCantidadMin) {
        this.cantidadMin = nuevaCantidadMin;
    }

    public int getCantidadMax() {
        return this.cantidadMax;
    }

    public void setCantidadMax(int nuevaCantidadMax) {
        this.cantidadMax = nuevaCantidadMax;
    }

    public int getPrecio() {
        return this.precio;
    }

    public void setPrecio(int nuevoPrecio) {
        this.precio = nuevoPrecio;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nuevoNombre) {
        this.nombre = nuevoNombre;
    }

    public String getCaracteristica() {
        return this.caracteristica;
    }

    public void setCaracteristica(String nuevaCaracteristica) {
        this.caracteristica = nuevaCaracteristica;
    }

    public String getMarca() {
        return this.marca;
    }

    public void setMarca(String nuevaMarca) {
        this.marca = nuevaMarca;
    }

    public String getCategoria() {
        return this.categoria;
    }

    public void setCategoria(String nuevaCategoria) {
        this.categoria = categoria;
    }

    public ArrayList<Sucursal> getListaSucursales() {
        return this.listaSucursales;
    }

    public void setListaSucursales(ArrayList<Sucursal> nuevaListaSucursales) {
        this.listaSucursales = nuevaListaSucursales;
    }

    public ArrayList<Venta> getListaDeVentas() {
        return this.listaDeVentas;
    }

    public void setListaDeVentas(ArrayList<Venta> listaDeVentas) {
        this.listaDeVentas = listaDeVentas;
    }

    public int getIdProducto() {
        return this.idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }
}


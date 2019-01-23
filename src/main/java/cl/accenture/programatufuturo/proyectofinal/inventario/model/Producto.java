package cl.accenture.programatufuturo.proyectofinal.inventario.model;

public class Producto {

    //Atributos de la clase Usuario
    private int idProducto;
    private String nombre, caracteristica;
    private int cantidadMin, cantidadMax, precio;
    private String  marca, categoria;

    //Constructores

    public Producto() {
        this.cantidadMin=5;
    }

    public Producto(String nombre, String caracteristica, int cantidadMax, int precio, String marca, String categoria) {
        this.nombre = nombre;
        this.caracteristica = caracteristica;
        this.cantidadMin = 5;
        this.cantidadMax = cantidadMax;
        this.precio = precio;
        this.marca = marca;
        this.categoria = categoria;
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

    public int getIdProducto() {
        return this.idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }
}


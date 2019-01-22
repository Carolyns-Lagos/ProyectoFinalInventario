package cl.accenture.programatufuturo.proyectofinal.inventario.model;

import cl.accenture.programatufuturo.proyectofinal.inventario.dao.SQLIdAutoincrementable;
import cl.accenture.programatufuturo.proyectofinal.inventario.exception.SinConexionException;

import java.sql.SQLException;
import java.util.ArrayList;

public class Sucursal {

    //Atributos de la clase Sucursal
    //el id tengo que generarlo con Springbood
    private int idSucursal;
    private String nombre, ubicacion, comuna, direccion;


    //Constructores

    public Sucursal() throws SinConexionException, SQLException {

    }

    public Sucursal(String nombre, String ubicacion, String comuna, String direccion) {
        this.nombre= nombre;
        this.ubicacion = ubicacion;
        this.comuna = comuna;
        this.direccion = direccion;
    }

    //Metodos Get y Set

    public int getIdSucursal() {
        return this.idSucursal;
    }

    public void setIdSucursal(int idSucursal) {
        this.idSucursal = idSucursal;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

}

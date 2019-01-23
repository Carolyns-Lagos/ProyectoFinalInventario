package cl.accenture.programatufuturo.proyectofinal.inventario.model;

import cl.accenture.programatufuturo.proyectofinal.inventario.Conversores.ConversorDataUtilASql;

public class Boleta {

    //Constructores
    private int idBoleta;
    private Usuario usuario;
    private java.sql.Date fecha;
    private int totalBoleta;

    //Constructores

    public Boleta() {
        java.util.Date uDate = new java.util.Date();
        this.fecha= ConversorDataUtilASql.convertUtilToSql(uDate);
    }

    public Boleta( Usuario usuario) {
        this.usuario = usuario;
        java.util.Date uDate = new java.util.Date();
        this.fecha= ConversorDataUtilASql.convertUtilToSql(uDate);
    }

    //Metodos Set y GeT
    public int getIdBoleta() {
        return this.idBoleta;
    }

    public void setIdBoleta(int idBoleta) {
        this.idBoleta = idBoleta;
    }

    public Usuario getUsuario() {
        return this.usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public java.sql.Date getFecha() {
        return this.fecha;
    }

    public void setFecha(java.sql.Date fecha) {
        this.fecha = fecha;
    }

    public int getTotalBoleta() {
        return this.totalBoleta;
    }

    public void setTotalBoleta(int totalBoleta) {
        this.totalBoleta = totalBoleta;
    }
}

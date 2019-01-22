package cl.accenture.programatufuturo.proyectofinal.inventario.model;

import java.sql.Date;

public class Boleta {

    //Constructores
    private int idBoleta;
    private Usuario usuario;
    private Date fecha;
    private int totalBoleta;

    //Constructores

    public Boleta() {

    }

    public Boleta(int idBoleta, Usuario usuario, int totalBoleta) {
        this.idBoleta = idBoleta;
        this.usuario = usuario;
        this.totalBoleta = totalBoleta;
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

    public Date getFecha() {
        return this.fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getTotalBoleta() {
        return this.totalBoleta;
    }

    public void setTotalBoleta(int totalBoleta) {
        this.totalBoleta = totalBoleta;
    }
}

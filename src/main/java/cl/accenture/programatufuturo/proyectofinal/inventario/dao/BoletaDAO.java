package cl.accenture.programatufuturo.proyectofinal.inventario.dao;

public class BoletaDAO {

    private Conexion conexion;

    public BoletaDAO(){

        this.conexion=new Conexion();
    }

    //Constructores get y ser

    public Conexion getConexion() {

        return this.conexion;
    }

    public void setConexion(Conexion conexion) {

        this.conexion = conexion;
    }


}

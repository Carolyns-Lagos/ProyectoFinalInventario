package cl.accenture.programatufuturo.proyectofinal.inventario.dao;

import cl.accenture.programatufuturo.proyectofinal.inventario.exception.SinConexionException;
import cl.accenture.programatufuturo.proyectofinal.inventario.model.Boleta;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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


    //Metodos

    public void agregarBoleta(Boleta boleta) throws SinConexionException {
        try{
            final String SQL = "INSERT INTO inventariopf.boleta(Usuario_Rut, Fecha)"+ "VALUES (?,?)";
            PreparedStatement ps = conexion.obtenerConnection().prepareStatement(SQL);
            ps.setString(1,boleta.getUsuario().getRut());
            ps.setDate(2,boleta.getFecha());
            ps.executeUpdate();
        } catch (SQLException ex){
            ex.printStackTrace();
        }
    }

    public static int obtenerIDBoleta(Conexion conexion, Boleta boleta) throws SinConexionException{
        int idBol=0;
        try{
            final String SQL = "SELECT idSucursal from inventariopf.boleta WHERE Usuario_Rut=? and Fecha=? and Total_Boleta=? ";
            PreparedStatement ps = conexion.obtenerConnection().prepareStatement(SQL);
            ps.setString(1,boleta.getUsuario().getRut());
            ps.setDate(2,boleta.getFecha());
            ps.setInt(3,boleta.getTotalBoleta());
            ResultSet rs = ps.executeQuery();
            rs.next();
            idBol = rs.getInt("idBoleta");
        }catch (SQLException e){
            e.printStackTrace();
        }
        return idBol;

    }

    public boolean buscarBoletaPorID(Boleta boleta) throws SinConexionException, SQLException {
        final String SQL = "SELECT * FROM inventariopf.boleta WHERE Nombre =? ";
        PreparedStatement ps = conexion.obtenerConnection().prepareStatement(SQL);
        ps.setInt(1,BoletaDAO.obtenerIDBoleta(this.conexion, boleta));
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            return true;
        }
        System.out.println("Boleta no encontrada");
        return false;
    }









}

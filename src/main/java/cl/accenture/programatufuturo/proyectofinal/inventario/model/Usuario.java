package cl.accenture.programatufuturo.proyectofinal.inventario.model;

public class Usuario {

    //Atributos de la clase Usuario
    private String nombre, correo;
    private String rut;
    private int telefono;
    private String password,rol;
    private Sucursal sucursal;

    //Constructores

    public Usuario() {
    }

    public Usuario(String rut, String nombre, String correo,int telefono, String password, String rol, Sucursal sucursal) {
        this.rut = rut;
        this.nombre = nombre;
        this.correo = correo;
        this.telefono=telefono;
        this.password = password;
        this.rol=rol;
        this.sucursal = sucursal;
    }
    public Usuario(String rut, String nombre, String correo,int telefono, String password) {
        this.rut = rut;
        this.nombre = nombre;
        this.correo = correo;
        this.telefono=telefono;
        this.password = password;

    }

    //Metodos Get y Set

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nuevoNombre) {
        this.nombre = nuevoNombre;
    }

    public String getRut() {
        return this.rut;
    }

    public void setRut(String nuevoRut) {
        this.rut = nuevoRut;
    }

    public String getCorreo() {
        return this.correo;
    }

    public void setCorreo(String nuevoCorreo) {
        this.correo = nuevoCorreo;
    }

    public int getTelefono() {
        return this.telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public String getPassword() {
         return this.password;
    }

    public void setPassword(String nuevaPassword) {
        this.password = nuevaPassword;
    }

    public String getRol() { return this.rol; }

    public void setRol(String nuevoRol) {
        this.rol = nuevoRol;
    }

    public Sucursal getSucursal() {
        return this.sucursal;
    }

    public void setSucursal(Sucursal nuevaSucursal) {
        this.sucursal = nuevaSucursal;
    }
}

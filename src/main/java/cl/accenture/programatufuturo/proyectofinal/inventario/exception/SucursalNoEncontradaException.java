package cl.accenture.programatufuturo.proyectofinal.inventario.exception;

public class SucursalNoEncontradaException extends Exception {
    //Esta excepcion, transmitira el mensaje indicado por el usuario, ademas de que mostrara en cpdigo (palabras rojas) cual es el problema)

    public SucursalNoEncontradaException(String message, Throwable e) {

        super(message, e);
    }
}

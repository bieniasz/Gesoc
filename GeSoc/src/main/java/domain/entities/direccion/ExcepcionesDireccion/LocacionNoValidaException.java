package domain.entities.direccion.ExcepcionesDireccion;

public class LocacionNoValidaException extends Exception{

    public LocacionNoValidaException(String locacion, String valor) {

        super(locacion + ": " + valor + " no valido");
    }
}

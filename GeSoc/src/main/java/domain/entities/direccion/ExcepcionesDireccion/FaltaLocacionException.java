package domain.entities.direccion.ExcepcionesDireccion;

public class FaltaLocacionException extends Exception{

    public FaltaLocacionException(String locacion) {
        super("Primero debe seleccionar el campo: " + locacion);
    }
}

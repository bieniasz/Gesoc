package domain.entities.operacionComercial.builder.Exception;

public class FaltaProveedorException extends Exception{

    public FaltaProveedorException(){
        super("Falta seleccionar un Proveedor");
    }
}

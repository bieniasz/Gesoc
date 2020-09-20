package operacionComercial.builder.Exception;

public class FaltaMedioDePagoException extends Exception{

    public FaltaMedioDePagoException() {
        super("Para crear una Operacion de Egreso debe seleccionarse un medio de pago");
    }
}

package operacionComercial.builder.Exception;

public class FaltaNumeroIdentificadorMedioPagoException extends Exception {

    public FaltaNumeroIdentificadorMedioPagoException(){
        super("Para crear una Operacion de Egreso debe seleccionarse un medio de pago");
    }
}

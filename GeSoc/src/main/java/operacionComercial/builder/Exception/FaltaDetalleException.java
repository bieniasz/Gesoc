package operacionComercial.builder.Exception;

public class FaltaDetalleException extends Exception {

    public FaltaDetalleException() {
        super("Para crear una Operacion de Egreso deben agregarse detalles");
    }
}

package operacionComercial.builder.Exception;

public class FaltaEgresoException extends Exception {

    public FaltaEgresoException() {
        super("Para crear un presupuesto debe ser asignado a un presupuesto");
    }
}

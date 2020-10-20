package domain.entities.operacionComercial.builder.Exception;

public class FaltaFechaException extends Exception{

    public FaltaFechaException() {
        super("Para crear una Operacion de Egreso debe seleccionar una Fecha");
    }
}

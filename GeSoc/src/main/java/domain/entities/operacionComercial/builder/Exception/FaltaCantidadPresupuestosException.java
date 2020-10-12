package domain.entities.operacionComercial.builder.Exception;

public class FaltaCantidadPresupuestosException extends Exception {
    public FaltaCantidadPresupuestosException () { super("Falta indicar la cantidad esperada de presupuestos"); }
}

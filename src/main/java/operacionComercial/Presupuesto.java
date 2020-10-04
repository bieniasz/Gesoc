package operacionComercial;

public class Presupuesto extends OperacionComercial{
    private OperacionEgreso egreso;
    private boolean elegido = false;

    public OperacionEgreso getEgreso() { return egreso; }
    public boolean isElegido() { return elegido; }

    public void setElegido(boolean elegido) { this.elegido = elegido; }
    public void setEgreso(OperacionEgreso egreso) {
        this.egreso = egreso;
        egreso.asociarPresupuesto(this);
    }
}

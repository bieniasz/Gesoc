package operacionComercial;

import ProveedorDocComer.DocumentoComercial;

import java.time.LocalDate;
import java.util.List;

public class Presupuesto extends OperacionComercial{
    private OperacionEgreso egreso;
    private boolean esElElegido;

    public OperacionEgreso getEgreso() { return egreso; }
    public boolean isEsElElegido() { return esElElegido; }

    public void setEsElElegido(boolean esElElegido) { this.esElElegido = esElElegido; }
    public void setEgreso(OperacionEgreso egreso) { this.egreso = egreso; }
}

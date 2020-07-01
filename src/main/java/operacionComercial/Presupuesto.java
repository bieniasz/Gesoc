package operacionComercial;

import ProveedorDocComer.DocumentoComercial;

import java.time.LocalDate;
import java.util.List;

public class Presupuesto extends OperacionComercial{
    private OperacionEgreso egreso;
    private boolean esElElegido;

    public Presupuesto(LocalDate fecha, float valorTotal, DocumentoComercial docComercial, List<DetalleEgreso> detalle, OperacionEgreso egreso, boolean esElElegido) {
        super(fecha, valorTotal, docComercial, detalle);
        this.egreso = egreso;
        this.esElElegido = esElElegido;
    }


    @Override
    public Double calcularValorTotal() {
    	return this.getDetalle().stream().mapToDouble(d->d.getValorTotal()).sum();
    }
    @Override
    public void registrarDetalle() { }
    @Override
    public void registrarDocumentoComercial() { }

    private void asociarAOperacionEgreso() {}
    private void copiarItemYCantidadDeEgreso() {}
    private void marcarComoElegido() {}

    /* GETTERS & SETTERS */
    public OperacionEgreso getEgreso() { return egreso; }
    public boolean isEsElElegido() { return esElElegido; }

    public void setEgreso(OperacionEgreso egreso) { this.egreso = egreso; }
    public void setEsElElegido(boolean esElElegido) { this.esElElegido = esElElegido; }
}

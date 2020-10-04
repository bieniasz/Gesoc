package operacionComercial;

import ProveedorDocComer.DocumentoComercial;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@DiscriminatorValue("Presupuesto")
public class Presupuesto extends OperacionComercial{

    @ManyToOne
    @JoinColumn(name="OperacionEgreso", referencedColumnName = "id")
    private OperacionEgreso egreso;

    @Column
    private boolean esElElegido;

    public Presupuesto(LocalDate fecha, float valorTotal, DocumentoComercial docComercial, List<DetalleEgreso> detalle, OperacionEgreso egreso, boolean esElElegido) {
        super(fecha, valorTotal, docComercial, detalle);
        this.egreso = egreso;
        this.egreso.asociarPresupuesto(this);
        this.esElElegido = esElElegido;
    }


    @Override
    public Double calcularValorTotal() {
    	return this.getDetalle().stream().mapToDouble(DetalleEgreso::getValorTotal).sum();
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
    public boolean isElegido() { return elegido; }

    public void setElegido(boolean elegido) { this.elegido = elegido; }
    public void setEgreso(OperacionEgreso egreso) {
        this.egreso = egreso;
        egreso.asociarPresupuesto(this);
    }
}

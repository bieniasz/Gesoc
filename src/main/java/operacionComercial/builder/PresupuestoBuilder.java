package operacionComercial.builder;

import operacionComercial.DetalleEgreso;
import operacionComercial.OperacionEgreso;
import operacionComercial.Presupuesto;
import operacionComercial.builder.Exception.FaltaEgresoException;

import java.util.List;

public class PresupuestoBuilder extends OperacionComercialBuilder{
    private OperacionEgreso egreso;
    private boolean esElElegido;

    public PresupuestoBuilder setDetalle(List<DetalleEgreso> detalleEgresos){
        super.setDetalle(detalleEgresos);
        return this;
    }

    public PresupuestoBuilder setEgreso(OperacionEgreso egreso) {
        this.egreso = egreso;
        return this;
    }
    public PresupuestoBuilder setEsElElegido(boolean esElElegido) {
        this.esElElegido = esElElegido;
        return this;
    }

    public Presupuesto build() throws Exception {
        super.setOperacion(new Presupuesto());
        if( this.egreso == null)
            throw new FaltaEgresoException();

        //this.setDetalle(this.egreso.getDetalle());
        Presupuesto presupuesto = (Presupuesto) super.build();
        presupuesto.setElegido(this.esElElegido);
        presupuesto.setEgreso(this.egreso);


        return presupuesto;
    }
}

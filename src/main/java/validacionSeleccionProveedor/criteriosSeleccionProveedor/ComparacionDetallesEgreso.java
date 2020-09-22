package validacionSeleccionProveedor.criteriosSeleccionProveedor;

import operacionComercial.DetalleEgreso;
import operacionComercial.OperacionEgreso;
import operacionComercial.Presupuesto;
import validacionSeleccionProveedor.CriterioValidacionEgresosPresupuesto;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class ComparacionDetallesEgreso extends CriterioValidacionEgresosPresupuesto {

    @Override
    public void validar(OperacionEgreso operacion) throws Exception {
        AtomicBoolean flag = new AtomicBoolean(false);

        operacion.getPresupuestos().forEach(presupuesto -> {
            try {
                compararDetalles(operacion, presupuesto);
            } catch (Exception e) {
                flag.set(true);
            }
        });

        if (flag.get())
            throw new Exception("El detalle de un presupuesto no coincide con el detalle del egreso");
    }

    private void compararDetalles(OperacionEgreso operacion, Presupuesto presupuesto) throws Exception {
        List<DetalleEgreso> detallePresupuesto = presupuesto.getDetalle();
        List<DetalleEgreso> detalleEgreso = operacion.getDetalle();

        if(!detalleEgreso.containsAll(detallePresupuesto))
            throw new Exception();
    }
}

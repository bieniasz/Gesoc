package validacionSeleccionProveedor.criteriosSeleccionProveedor;

import operacionComercial.DetalleEgreso;
import operacionComercial.OperacionEgreso;
import operacionComercial.Presupuesto;
import validacionSeleccionProveedor.CriterioValidacionEgresosPresupuesto;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

public class ComparacionDetallesEgreso extends CriterioValidacionEgresosPresupuesto {

    @Override
    public void validar(OperacionEgreso operacion) throws Exception {
        List<Presupuesto> presupuestos = operacion.getPresupuestos();
        List<DetalleEgreso> detalleEgreso = operacion.getDetalle();

        for (Presupuesto pres : presupuestos) {
            List<DetalleEgreso> detallePresupuesto = pres.getDetalle();
            for (DetalleEgreso itemPresupuesto : detallePresupuesto) {
                boolean coincideDetalle = detalleEgreso
                    .stream()
                    .anyMatch(itemEgreso -> itemsDetalleCoinciden(itemEgreso, itemPresupuesto));

                if (!coincideDetalle)
                    throw new Exception("El detalle de un presupuesto no coincide con el detalle del egreso");
            }
        }
    }

    private boolean itemsDetalleCoinciden(DetalleEgreso itemEgreso, DetalleEgreso itemPresupuesto) {
        return (itemEgreso.item.getDescripcion().equals(itemPresupuesto.item.getDescripcion()))
                && (itemEgreso.cantidad == itemPresupuesto.cantidad);
    }
}

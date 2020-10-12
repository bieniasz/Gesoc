package domain.entities.validadorTransparencia.precondComparacionDetalles;

import domain.entities.operacionComercial.DetalleEgreso;
import domain.entities.operacionComercial.Item;
import domain.entities.operacionComercial.OperacionEgreso;
import domain.entities.operacionComercial.Presupuesto;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import domain.entities.validacionSeleccionProveedor.criteriosSeleccionProveedor.ComparacionDetallesEgreso;

public class TestComparacionDetallesEgreso {
    private OperacionEgreso operacionEgreso;
    private final AuxComparacionDetallesEgreso precondDetallesEgreso = new AuxComparacionDetallesEgreso();
    String mensajeExito = "La Operacion de Egreso cumple con todas las politicas";

    @Before
    public void init(){
        this.operacionEgreso = new OperacionEgreso();
        DetalleEgreso detalleEgreso = generarDetalleTest("Lapiz", 5, 100.0);
        this.operacionEgreso.registrarDetalle(detalleEgreso);

        DetalleEgreso detalleEgreso2 = generarDetalleTest("Sacapuntas", 5, 150.0);
        this.operacionEgreso.registrarDetalle(detalleEgreso2);

        DetalleEgreso detalleEgreso3 = generarDetalleTest("Lapicera", 10, 500.0);
        this.operacionEgreso.registrarDetalle(detalleEgreso3);
    }

    @Test(expected = Exception.class)
    public void TestNoCoincidenDetalles() throws Exception {
        Presupuesto presupuesto = new Presupuesto();

        //otroDetalle es el que no va a coincidir
        DetalleEgreso otroDetalle = generarDetalleTest("Lapiz HB", 5, 300.0);
        DetalleEgreso detalleEgreso2 = generarDetalleTest("Sacapuntas", 5, 150.0);
        DetalleEgreso detalleEgreso3 = generarDetalleTest("Lapicera", 10, 500.0);
        presupuesto.registrarDetalle(otroDetalle);
        presupuesto.registrarDetalle(detalleEgreso2);
        presupuesto.registrarDetalle(detalleEgreso3);

        this.operacionEgreso.asociarPresupuesto(presupuesto);
        precondDetallesEgreso.validarMock(this.operacionEgreso);
    }

    @Test
    public void TestCoincidenDetalles() throws Exception {
        Presupuesto presupuesto = new Presupuesto();

        DetalleEgreso detalleEgreso = generarDetalleTest("Lapiz", 5, 100.0);
        DetalleEgreso detalleEgreso2 = generarDetalleTest("Sacapuntas", 5, 150.0);
        DetalleEgreso detalleEgreso3 = generarDetalleTest("Lapicera", 10, 500.0);
        presupuesto.registrarDetalle(detalleEgreso);
        presupuesto.registrarDetalle(detalleEgreso2);
        presupuesto.registrarDetalle(detalleEgreso3);

        this.operacionEgreso.asociarPresupuesto(presupuesto);
        String mensaje = precondDetallesEgreso.validarMock(this.operacionEgreso);
        Assert.assertEquals(mensajeExito, mensaje);
    }

    private DetalleEgreso generarDetalleTest(String descripcion, int cantidad, double valorTotal) {
        DetalleEgreso detalleEgreso = new DetalleEgreso();
        Item item = new Item();
        item.setDescripcion(descripcion);
        detalleEgreso.item = item;
        detalleEgreso.cantidad = cantidad;
        detalleEgreso.valorTotal = valorTotal;
        return detalleEgreso;
    }


    class AuxComparacionDetallesEgreso extends ComparacionDetallesEgreso {
        /*
        Esta clase existe para darle un valor de retorno ficticio a validar
        y poder testear los casos de exito unitariamente
        sin involucrar la suscripcion de validacion
        */

        public String validarMock(OperacionEgreso operacionEgreso) throws Exception {
            super.validar(operacionEgreso);
            return "La Operacion de Egreso cumple con todas las politicas";
        }
    }
}

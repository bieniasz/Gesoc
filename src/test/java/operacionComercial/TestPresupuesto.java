package operacionComercial;

import ProveedorDocComer.Proveedor;
import operacionComercial.builder.Exception.FaltaDetalleException;
import operacionComercial.builder.Exception.FaltaEgresoException;
import operacionComercial.builder.OperacionEgresoBuilder;
import operacionComercial.builder.PresupuestoBuilder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import organizacion.EntidadJuridica;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TestPresupuesto {
    private OperacionEgreso operacionEgreso;
    private DetalleEgreso unDetalle;

    @Before
    public void init() throws Exception {
        this.unDetalle = new DetalleEgreso();
        this.unDetalle.valorTotal = 5.0;
        DetalleEgreso otroDetalle = new DetalleEgreso();
        otroDetalle.valorTotal = 5.0;

        List<DetalleEgreso> detalles = new ArrayList<>();
        detalles.add(this.unDetalle);
        detalles.add(otroDetalle);

        OperacionEgresoBuilder builder = new OperacionEgresoBuilder();
        builder.setDetalle(detalles);
        builder.setMedioDePago(new MedioDePago());
        builder.setNumeroIdentificadorMedioPago("AAAAAAAA");
        builder.setProveedor(new Proveedor());
        builder.setOrganizacion(new EntidadJuridica());
        this.operacionEgreso = builder.build();
    }

    @Test
    public void PresupuestoBuilderConParametrosObligatorios() throws Exception {

        PresupuestoBuilder presupuestoBuilder = new PresupuestoBuilder();
        presupuestoBuilder.setEgreso(this.operacionEgreso);
        presupuestoBuilder.setEsElElegido(true);

        Presupuesto presupuesto = presupuestoBuilder.build();

        Assert.assertEquals("operacionComercial.Presupuesto", presupuesto.getClass().getName());
    }

    @Test
    public void PresupuestoValorTotalEsCalculadoViaBuilder() throws Exception {

        PresupuestoBuilder presupuestoBuilder = new PresupuestoBuilder();
        presupuestoBuilder.setEgreso(this.operacionEgreso);
        presupuestoBuilder.setEsElElegido(true);

        Presupuesto presupuesto = presupuestoBuilder.build();

        Double valorTotal = presupuesto.getValorTotal();
        Assert.assertEquals(valorTotal, 10.0, 0.0);
    }

    @Test
    public void PresupuestoFechaActualViaBuilder() throws Exception {

        PresupuestoBuilder presupuestoBuilder = new PresupuestoBuilder();
        presupuestoBuilder.setEgreso(this.operacionEgreso);
        presupuestoBuilder.setEsElElegido(true);

        Presupuesto presupuesto = presupuestoBuilder.build();

        LocalDate fechaOperacion = presupuesto.getFecha();
        Assert.assertEquals(fechaOperacion, LocalDate.now());
    }

    @Test(expected = FaltaEgresoException.class)
    public void PresupuestoSinEgresoBuilderFalla() throws Exception {
        PresupuestoBuilder presupuestoBuilder = new PresupuestoBuilder();
        presupuestoBuilder.setEsElElegido(true);

        presupuestoBuilder.build();
    }

    @Test
    public void PresupuestoAgregaDetallesYRecalculaValor() throws Exception {

        PresupuestoBuilder presupuestoBuilder = new PresupuestoBuilder();
        presupuestoBuilder.setEgreso(this.operacionEgreso);
        presupuestoBuilder.setEsElElegido(true);
        Presupuesto presupuesto = presupuestoBuilder.build();

        DetalleEgreso otroDetalleMas = new DetalleEgreso();
        otroDetalleMas.valorTotal = 4.0;
        presupuesto.registrarDetalle(otroDetalleMas);

        Double valorTotal = presupuesto.getValorTotal();
        Assert.assertEquals(14.0, valorTotal, 0.0);
    }

    @Test
    public void PresupuestoQuitaDetallesYRecalculaValor() throws Exception {

        PresupuestoBuilder presupuestoBuilder = new PresupuestoBuilder();
        presupuestoBuilder.setEgreso(this.operacionEgreso);
        presupuestoBuilder.setEsElElegido(true);
        Presupuesto presupuesto = presupuestoBuilder.build();

        presupuesto.quitarDetalle(this.unDetalle);

        Double valorTotal = presupuesto.getValorTotal();
        Assert.assertEquals(5.0, valorTotal, 0.0);
    }
}

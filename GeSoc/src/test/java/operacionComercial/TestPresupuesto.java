package operacionComercial;

import ProveedorDocComer.Proveedor;
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
    List<DetalleEgreso> listDetallesEgreso;

    DetalleEgreso detPresupuesto1;
    DetalleEgreso detPresupuesto2;
    List<DetalleEgreso> listDetallesPresupuesto;

    @Before
    public void init() throws Exception {
        DetalleEgreso unDetalle = new DetalleEgreso();
        unDetalle.valorTotal = 50.0;
        DetalleEgreso otroDetalle = new DetalleEgreso();
        otroDetalle.valorTotal = 60.0;

        this.listDetallesEgreso = new ArrayList<>();
        this.listDetallesEgreso.add(unDetalle);
        this.listDetallesEgreso.add(otroDetalle);

        OperacionEgresoBuilder builder = new OperacionEgresoBuilder();
        builder.setDetalle(this.listDetallesEgreso);
        builder.setMedioDePago(new MedioDePago());
        builder.setNumeroIdentificadorMedioPago("AAAAAAAA");
        builder.setProveedor(new Proveedor());
        builder.setOrganizacion(new EntidadJuridica());
        builder.setCantEsperadaPresupuestos(0);
        this.operacionEgreso = builder.build();

        /* Configuro el detalle del presupuesto */
        detPresupuesto1 = new DetalleEgreso();
        detPresupuesto1.valorTotal = 50.0;
        detPresupuesto2 = new DetalleEgreso();
        detPresupuesto2.valorTotal = 60.0;

        this.listDetallesPresupuesto = new ArrayList<DetalleEgreso>();
        this.listDetallesPresupuesto.add(detPresupuesto1);
        this.listDetallesPresupuesto.add(detPresupuesto2);

    }

    @Test
    public void PresupuestoBuilderConParametrosObligatorios() throws Exception {
        PresupuestoBuilder presupuestoBuilder = new PresupuestoBuilder();
        presupuestoBuilder.setEgreso(this.operacionEgreso);
        presupuestoBuilder.setDetalle(this.listDetallesPresupuesto);
        presupuestoBuilder.setEsElElegido(true);

        Presupuesto presupuesto = presupuestoBuilder.build();

        Assert.assertEquals("operacionComercial.Presupuesto", presupuesto.getClass().getName());
    }

    @Test
    public void PresupuestoValorTotalEsCalculadoViaBuilder() throws Exception {

        PresupuestoBuilder presupuestoBuilder = new PresupuestoBuilder();
        presupuestoBuilder.setEgreso(this.operacionEgreso);
        presupuestoBuilder.setDetalle(this.listDetallesPresupuesto);
        presupuestoBuilder.setEsElElegido(true);

        Presupuesto presupuesto = presupuestoBuilder.build();

        Double valorTotal = presupuesto.getValorTotal();
        Assert.assertEquals(valorTotal, 110.0, 0.0);
    }

    @Test
    public void PresupuestoFechaActualViaBuilder() throws Exception {

        PresupuestoBuilder presupuestoBuilder = new PresupuestoBuilder();
        presupuestoBuilder.setDetalle(this.listDetallesPresupuesto);
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
        presupuestoBuilder.setDetalle(this.listDetallesPresupuesto);
        presupuestoBuilder.setEgreso(this.operacionEgreso);
        presupuestoBuilder.setEsElElegido(true);
        Presupuesto presupuesto = presupuestoBuilder.build();

        DetalleEgreso otroDetalleMas = new DetalleEgreso();
        otroDetalleMas.valorTotal = 40.0;
        presupuesto.registrarDetalle(otroDetalleMas);

        Double valorTotal = presupuesto.getValorTotal();
        Assert.assertEquals(150.0, valorTotal, 0.0);
    }

    @Test
    public void PresupuestoQuitaDetallesYRecalculaValor() throws Exception {

        PresupuestoBuilder presupuestoBuilder = new PresupuestoBuilder();
        presupuestoBuilder.setDetalle(this.listDetallesPresupuesto);
        presupuestoBuilder.setEgreso(this.operacionEgreso);
        presupuestoBuilder.setEsElElegido(true);
        Presupuesto presupuesto = presupuestoBuilder.build();

        presupuesto.quitarDetalle(this.detPresupuesto1);

        Double valorTotal = presupuesto.getValorTotal();
        Assert.assertEquals(60.0, valorTotal, 0.0);
    }
}

package domain.entities.operacionComercial;

import domain.entities.ProveedorDocComer.Proveedor;
import domain.entities.operacionComercial.builder.Exception.*;
import domain.entities.operacionComercial.builder.OperacionEgresoBuilder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import domain.entities.organizacion.EntidadJuridica;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TestOperacionEgreso {
    private List<DetalleEgreso> detalles;
    private DetalleEgreso unDetalle;

    @Before
    public void init(){
        this.unDetalle = new DetalleEgreso();
        this.unDetalle.valorTotal = 5.0;
        DetalleEgreso otroDetalle = new DetalleEgreso();
        otroDetalle.valorTotal = 5.0;

        this.detalles = new ArrayList<>();
        this.detalles.add(this.unDetalle);
        this.detalles.add(otroDetalle);
    }

    @Test
    public void OperacionEgresoBuilderConParametrosObligatorios() throws Exception {
        OperacionEgresoBuilder builder = new OperacionEgresoBuilder();

        builder.setDetalle(this.detalles);
        builder.setMedioDePago(new MedioDePago());
        builder.setNumeroIdentificadorMedioPago("AAAAAAAA");
        builder.setProveedor(new Proveedor());
        builder.setOrganizacion(new EntidadJuridica());
        builder.setCantEsperadaPresupuestos(0);
        builder.setFecha(LocalDate.now());
        OperacionEgreso operacion = builder.build();

        Assert.assertEquals("domain.entities.operacionComercial.OperacionEgreso", operacion.getClass().getName());
    }

    @Test
    public void OperacionEgresoValorTotalEsCalculadoViaBuilder() throws Exception {
        OperacionEgresoBuilder builder = new OperacionEgresoBuilder();

        builder.setDetalle(this.detalles);
        builder.setMedioDePago(new MedioDePago());
        builder.setNumeroIdentificadorMedioPago("AAAAAAAA");
        builder.setProveedor(new Proveedor());
        builder.setOrganizacion(new EntidadJuridica());
        builder.setCantEsperadaPresupuestos(0);
        builder.setFecha(LocalDate.now());
        OperacionEgreso operacion = builder.build();

        Double valorTotal = operacion.getValorTotal();
        Assert.assertEquals(valorTotal, 10.0, 0.0);
    }

    @Test
    public void OperacionEgresoFechaActualViaBuilder() throws Exception {
        OperacionEgresoBuilder builder = new OperacionEgresoBuilder();

        builder.setDetalle(this.detalles);
        builder.setMedioDePago(new MedioDePago());
        builder.setNumeroIdentificadorMedioPago("AAAAAAAA");
        builder.setProveedor(new Proveedor());
        builder.setOrganizacion(new EntidadJuridica());
        builder.setCantEsperadaPresupuestos(0);
        builder.setFecha(LocalDate.now());
        OperacionEgreso operacion = builder.build();

        LocalDate fechaOperacion = operacion.getFecha();
        Assert.assertEquals(fechaOperacion, LocalDate.now());
    }

    @Test(expected = FaltaDetalleException.class)
    public void OperacionEgresoSinDetallesBuilderFalla() throws Exception {
        OperacionEgresoBuilder builder = new OperacionEgresoBuilder();

        builder.setMedioDePago(new MedioDePago());
        builder.setNumeroIdentificadorMedioPago("AAAAAAAA");
        builder.setProveedor(new Proveedor());
        builder.setOrganizacion(new EntidadJuridica());
        builder.setCantEsperadaPresupuestos(0);
        builder.setFecha(LocalDate.now());
        builder.build();
    }

    @Test(expected = FaltaMedioDePagoException.class)
    public void OperacionEgresoSinMedioDePagoBuilderFalla() throws Exception {
        OperacionEgresoBuilder builder = new OperacionEgresoBuilder();

        builder.setDetalle(this.detalles);
        builder.setNumeroIdentificadorMedioPago("AAAAAAAA");
        builder.setProveedor(new Proveedor());
        builder.setOrganizacion(new EntidadJuridica());
        builder.setCantEsperadaPresupuestos(0);
        builder.setFecha(LocalDate.now());
        builder.build();
    }

    @Test(expected = FaltaNumeroIdentificadorMedioPagoException.class)
    public void OperacionEgresoSinNumeroIdentificadorMedioPagoBuilderFalla() throws Exception {
        OperacionEgresoBuilder builder = new OperacionEgresoBuilder();

        builder.setDetalle(this.detalles);
        builder.setMedioDePago(new MedioDePago());
        builder.setProveedor(new Proveedor());
        builder.setOrganizacion(new EntidadJuridica());
        builder.setCantEsperadaPresupuestos(0);
        builder.setFecha(LocalDate.now());
        builder.build();
    }

    @Test(expected = FaltaProveedorException.class)
    public void OperacionEgresoSinProveedorBuilderFalla() throws Exception {
        OperacionEgresoBuilder builder = new OperacionEgresoBuilder();

        builder.setDetalle(this.detalles);
        builder.setMedioDePago(new MedioDePago());
        builder.setNumeroIdentificadorMedioPago("AAAAAAAA");
        builder.setOrganizacion(new EntidadJuridica());
        builder.setCantEsperadaPresupuestos(0);
        builder.setFecha(LocalDate.now());
        builder.build();
    }

    @Test(expected = FaltaOrganizacionException.class)
    public void OperacionEgresoSinOrganizacionBuilderFalla() throws Exception {
        OperacionEgresoBuilder builder = new OperacionEgresoBuilder();

        builder.setDetalle(this.detalles);
        builder.setMedioDePago(new MedioDePago());
        builder.setNumeroIdentificadorMedioPago("AAAAAAAA");
        builder.setProveedor(new Proveedor());
        builder.setCantEsperadaPresupuestos(0);
        builder.setFecha(LocalDate.now());
        builder.build();
    }

    @Test(expected = FaltaCantidadPresupuestosException.class)
    public void OperacionEgresoSinCantidadPresupuestosBuilderFalla() throws Exception {
        OperacionEgresoBuilder builder = new OperacionEgresoBuilder();

        builder.setDetalle(this.detalles);
        builder.setMedioDePago(new MedioDePago());
        builder.setNumeroIdentificadorMedioPago("AAAAAAAA");
        builder.setProveedor(new Proveedor());
        builder.setOrganizacion(new EntidadJuridica());
        builder.setFecha(LocalDate.now());
        builder.build();
    }


    @Test
    public void OperacionEgresoAgregaDetallesYRecalculaValor() throws Exception {
        OperacionEgresoBuilder builder = new OperacionEgresoBuilder();

        builder.setDetalle(this.detalles);
        builder.setMedioDePago(new MedioDePago());
        builder.setNumeroIdentificadorMedioPago("AAAAAAAA");
        builder.setProveedor(new Proveedor());
        builder.setOrganizacion(new EntidadJuridica());
        builder.setCantEsperadaPresupuestos(0);
        builder.setFecha(LocalDate.now());
        OperacionEgreso operacion = builder.build();

        DetalleEgreso otroDetalleMas = new DetalleEgreso();
        otroDetalleMas.valorTotal = 4.0;
        operacion.registrarDetalle(otroDetalleMas);

        Double valorTotal = operacion.getValorTotal();
        Assert.assertEquals(14.0, valorTotal, 0.0);
    }

    @Test
    public void PresupuestoQuitaDetallesYRecalculaValor() throws Exception {
        OperacionEgresoBuilder builder = new OperacionEgresoBuilder();

        builder.setDetalle(this.detalles);
        builder.setMedioDePago(new MedioDePago());
        builder.setNumeroIdentificadorMedioPago("AAAAAAAA");
        builder.setProveedor(new Proveedor());
        builder.setOrganizacion(new EntidadJuridica());
        builder.setCantEsperadaPresupuestos(0);
        builder.setFecha(LocalDate.now());
        OperacionEgreso operacion = builder.build();

        operacion.quitarDetalle(this.unDetalle);

        Double valorTotal = operacion.getValorTotal();
        Assert.assertEquals(5.0, valorTotal, 0.0);
    }
}

package domain.entities.vinculacionIngresoEgresos;

import domain.entities.ProveedorDocComer.DocumentoComercial;
import domain.entities.operacionComercial.OperacionEgreso;
import domain.entities.operacionComercial.OperacionIngreso;
import domain.entities.organizacion.Organizacion;
import domain.entities.vinculacionIngresoEgresos.adapters.adapterVinculator.AdapterVinculator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TestAdapterVinculator {
    private List<OperacionEgreso> operacionesEgreso;
    private List<OperacionIngreso> operacionesIngreso;
    private AdapterVinculator adapter = new AdapterVinculator();

    @Before
    public void init() {
        this.operacionesEgreso = new ArrayList<OperacionEgreso>();
        this.operacionesIngreso = new ArrayList<OperacionIngreso>();
        this.adapter = new AdapterVinculator();

        OperacionEgreso egreso1 = new OperacionEgreso();
        egreso1.setDetalle(new ArrayList<>());
        egreso1.valorTotal = 500.50;
        egreso1.registrarDocumentoComercial(new DocumentoComercial());
        egreso1.setFecha(LocalDate.of(2020, 10, 12));
        egreso1.setId(1);

        OperacionEgreso egreso2 = new OperacionEgreso();
        egreso2.setDetalle(new ArrayList<>());
        egreso2.valorTotal = 700.0;
        egreso2.setFecha(LocalDate.of(2020, 10, 13));
        egreso2.registrarDocumentoComercial(new DocumentoComercial());
        egreso2.setId(2);

        OperacionEgreso egreso3 = new OperacionEgreso();
        egreso3.setDetalle(new ArrayList<>());
        egreso3.valorTotal = 1000.0;
        egreso3.setFecha(LocalDate.of(2020, 10, 13));
        egreso3.registrarDocumentoComercial(new DocumentoComercial());
        egreso3.setId(3);

        this.operacionesEgreso.add(egreso1);
        this.operacionesEgreso.add(egreso2);
        this.operacionesEgreso.add(egreso3);

        OperacionIngreso ingreso1 = new OperacionIngreso();
        ingreso1.setId(1);
        ingreso1.setDescripcion("Un ingreso");
        ingreso1.setFecha(LocalDate.of(2020, 10, 13));
        ingreso1.setFechaHastaAceptable(LocalDate.now());
        ingreso1.setMonto(1200f);
        OperacionIngreso ingreso2 = new OperacionIngreso();
        ingreso2.setId(2);
        ingreso2.setDescripcion("Otro ingreso");
        ingreso2.setFecha(LocalDate.of(2020, 10, 12));
        ingreso2.setFechaHastaAceptable(LocalDate.now());
        ingreso2.setMonto(500f);

        this.operacionesIngreso.add(ingreso1);
        this.operacionesIngreso.add(ingreso2);
    }

    @Test
    public void TestAdapterVinculatorNoDevuelveNull() throws IOException {
        Map<OperacionEgreso, OperacionIngreso> mapVinculaciones = this.adapter.obtenerVinculaciones(this.operacionesIngreso, this.operacionesEgreso);
        Assert.assertNotNull(mapVinculaciones);
    }

    @Test
    public void TestAdapterVinculatorConVinculaciones() throws IOException {
        Map<OperacionEgreso, OperacionIngreso> mapVinculaciones = this.adapter.obtenerVinculaciones(this.operacionesIngreso, this.operacionesEgreso);
        Assert.assertTrue(mapVinculaciones.size() > 0);
    }
}

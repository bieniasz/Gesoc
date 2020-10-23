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
    private LocalDate fechaHastaAceptable;

    @Before
    public void init() {
        this.operacionesEgreso = new ArrayList<OperacionEgreso>();
        this.operacionesIngreso = new ArrayList<OperacionIngreso>();
        this.adapter = new AdapterVinculator();
        this.fechaHastaAceptable = LocalDate.of(2020, 12, 31);

        //OperacionEgresoTest y OperacionIngresoTest son clases auxiliares que me permiten setear
        //el id para operar, sin tener que conectarme a la BD
        OperacionEgresoTest egreso1 = new OperacionEgresoTest();
        egreso1.setDetalle(new ArrayList<>());
        egreso1.valorTotal = 500.50;
        egreso1.registrarDocumentoComercial(new DocumentoComercial());
        egreso1.setFecha(LocalDate.of(2020, 10, 12));
        egreso1.id_Egreso = 1;

        OperacionEgresoTest egreso2 = new OperacionEgresoTest();
        egreso2.setDetalle(new ArrayList<>());
        egreso2.valorTotal = 700.0;
        egreso2.setFecha(LocalDate.of(2020, 10, 13));
        egreso2.registrarDocumentoComercial(new DocumentoComercial());
        egreso2.id_Egreso = 2;

        OperacionEgresoTest egreso3 = new OperacionEgresoTest();
        egreso3.setDetalle(new ArrayList<>());
        egreso3.valorTotal = 1000.0;
        egreso3.setFecha(LocalDate.of(2020, 10, 13));
        egreso3.registrarDocumentoComercial(new DocumentoComercial());
        egreso3.id_Egreso = 3;

        this.operacionesEgreso.add(egreso1);
        this.operacionesEgreso.add(egreso2);
        this.operacionesEgreso.add(egreso3);

        OperacionIngresoTest ingreso1 = new OperacionIngresoTest(1, "Un ingreso", LocalDate.of(2020, 10, 13), 1200f, null);
        OperacionIngresoTest ingreso2 = new OperacionIngresoTest(2, "Otro ingreso", LocalDate.of(2020, 10, 12), 500f, null);

        this.operacionesIngreso.add(ingreso1);
        this.operacionesIngreso.add(ingreso2);
    }

    @Test
    public void TestAdapterVinculatorNoDevuelveNull() throws IOException {
        Map<OperacionEgreso, OperacionIngreso> mapVinculaciones = this.adapter.obtenerVinculaciones(this.operacionesIngreso, this.operacionesEgreso, this.fechaHastaAceptable);
        Assert.assertNotNull(mapVinculaciones);
    }

    @Test
    public void TestAdapterVinculatorConVinculaciones() throws IOException {
        Map<OperacionEgreso, OperacionIngreso> mapVinculaciones = this.adapter.obtenerVinculaciones(this.operacionesIngreso, this.operacionesEgreso, this.fechaHastaAceptable);
        Assert.assertTrue(mapVinculaciones.size() > 0);
    }



    class OperacionEgresoTest extends OperacionEgreso {
        public int id_Egreso;

        @Override
        public int getId() {
            return id_Egreso;
        }

        public void setId_Egreso(int id) {
            this.id_Egreso = id;
        }
    }
    class OperacionIngresoTest extends OperacionIngreso {
        public int id_Ingreso;

        public OperacionIngresoTest(int id_Ingreso, String descripcion, LocalDate fecha, float monto, Organizacion organizacion) {
            super.setDescripcion(descripcion);
            super.setFecha(fecha);
            super.setMonto(monto);
            super.setOrganizacion(organizacion);
            this.id_Ingreso = id_Ingreso;
        }

        @Override
        public int getId() {
            return id_Ingreso;
        }
    }
}

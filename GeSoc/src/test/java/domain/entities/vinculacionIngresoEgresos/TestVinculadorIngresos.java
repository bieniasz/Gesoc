package domain.entities.vinculacionIngresoEgresos;

import domain.entities.ProveedorDocComer.DocumentoComercial;
import domain.entities.operacionComercial.OperacionEgreso;
import domain.entities.operacionComercial.OperacionIngreso;
import domain.entities.vinculacionIngresoEgresos.adapters.adapterVinculator.AdapterVinculator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TestVinculadorIngresos {
    private VinculadorIngresoEgresos vinculadorIngresoEgresos;
    private List<OperacionEgreso> operacionesEgreso;
    private List<OperacionIngreso> operacionesIngreso;

    @Before
    public void init() {
        this.vinculadorIngresoEgresos = new VinculadorIngresoEgresos();
        this.vinculadorIngresoEgresos.setAdapterVinculador(new AdapterVinculator());

        this.operacionesEgreso = new ArrayList<OperacionEgreso>();
        this.operacionesIngreso = new ArrayList<OperacionIngreso>();
    }

    @Test
    public void Test1 () throws IOException {
        OperacionEgresoTest egreso1 = new OperacionEgresoTest();
        egreso1.setDetalle(new ArrayList<>());
        egreso1.valorTotal = 500.0;
        egreso1.registrarDocumentoComercial(new DocumentoComercial());
        egreso1.setFecha(LocalDate.of(2020, 9, 30));
        egreso1.id_Egreso = 1;

        OperacionEgresoTest egreso2 = new OperacionEgresoTest();
        egreso2.setDetalle(new ArrayList<>());
        egreso2.valorTotal = 700.0;
        egreso2.setFecha(LocalDate.of(2020, 3, 30));
        egreso2.registrarDocumentoComercial(new DocumentoComercial());
        egreso2.id_Egreso = 2;

        OperacionEgresoTest egreso3 = new OperacionEgresoTest();
        egreso3.setDetalle(new ArrayList<>());
        egreso3.valorTotal = 1000.0;
        egreso3.setFecha(LocalDate.of(2020, 5, 30));
        egreso3.registrarDocumentoComercial(new DocumentoComercial());
        egreso3.id_Egreso = 3;

        this.operacionesEgreso.add(egreso1);
        this.operacionesEgreso.add(egreso2);
        this.operacionesEgreso.add(egreso3);

        OperacionIngresoTest ingreso1 = new OperacionIngresoTest(1, "Un ingreso", LocalDate.of(2020, 10, 13), 1200f);
        OperacionIngresoTest ingreso2 = new OperacionIngresoTest(1, "Otro ingreso", LocalDate.of(2020, 10, 1), 500f);

        this.operacionesIngreso.add(ingreso1);
        this.operacionesIngreso.add(ingreso2);

        this.vinculadorIngresoEgresos.setOperacionEgresoList(this.operacionesEgreso);
        this.vinculadorIngresoEgresos.setOperacionIngresoList(this.operacionesIngreso);
        this.vinculadorIngresoEgresos.setFechaHastaAceptable(LocalDate.of(2020, 12, 31));

        this.vinculadorIngresoEgresos.vincularOperaciones();

        Assert.assertEquals(ingreso1, egreso1.getIngresoAsociado());
    }


    class OperacionEgresoTest extends OperacionEgreso {
        public int id_Egreso;

        @Override
        public int getid() {
            return id_Egreso;
        }

        public void setId_Egreso(int id) {
            this.id_Egreso = id;
        }
    }

    class OperacionIngresoTest extends OperacionIngreso {
        public int id_Ingreso;

        public OperacionIngresoTest(int id_Ingreso, String descripcion, LocalDate fecha, float monto) {
            super(descripcion, fecha, monto);
            this.id_Ingreso = id_Ingreso;
        }

        @Override
        public int getid() {
            return id_Ingreso;
        }
    }

}

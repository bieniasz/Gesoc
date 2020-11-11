package domain.entities.vinculacionIngresoEgresos;

import domain.entities.config.Config;
import domain.entities.config.Constantes;
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
        egreso1.valorTotal = 50.0;
        egreso1.setFecha(LocalDate.of(2020, 1, 10));
        egreso1.setId(1);

        OperacionEgreso egreso2 = new OperacionEgreso();
        egreso2.valorTotal = 150.0;
        egreso2.setFecha(LocalDate.of(2020, 2, 20));
        egreso2.setId(2);

        OperacionEgreso egreso3 = new OperacionEgreso();
        egreso3.valorTotal = 200.0;
        egreso3.setFecha(LocalDate.of(2020, 3, 8));
        egreso3.setId(3);

        OperacionEgreso egreso4 = new OperacionEgreso();
        egreso4.setId(4);
        egreso4.setFecha(LocalDate.of(2020, 3, 9));
        egreso4.valorTotal = 500.0;

        OperacionEgreso egreso5 = new OperacionEgreso();
        egreso5.setId(5);
        egreso5.setFecha(LocalDate.of(2020, 5, 1));
        egreso5.valorTotal = 800.0;

        OperacionEgreso egreso6 = new OperacionEgreso();
        egreso6.setId(6);
        egreso6.setFecha(LocalDate.of(2020, 7, 4));
        egreso6.valorTotal = 100.0;

        this.operacionesEgreso.add(egreso1);
        this.operacionesEgreso.add(egreso2);
        this.operacionesEgreso.add(egreso3);
        this.operacionesEgreso.add(egreso4);
        this.operacionesEgreso.add(egreso5);
        this.operacionesEgreso.add(egreso6);


        OperacionIngreso ingreso1 = new OperacionIngreso();
        ingreso1.setId(1);
        ingreso1.setDescripcion("Un ingreso");
        ingreso1.setFecha(LocalDate.of(2020, 2, 10));
        ingreso1.setFechaHastaAceptable(LocalDate.of(2020, 3, 10));
        ingreso1.setMonto(500f);

        OperacionIngreso ingreso2 = new OperacionIngreso();
        ingreso2.setId(2);
        ingreso2.setDescripcion("Otro ingreso");
        ingreso2.setFecha(LocalDate.of(2020, 4, 24));
        ingreso2.setFechaHastaAceptable(LocalDate.of(2020, 5, 15));
        ingreso2.setMonto(1000f);

        this.operacionesIngreso.add(ingreso1);
        this.operacionesIngreso.add(ingreso2);
    }

    @Test
    public void TestAdapterVinculatorDevuelveVinculaciones() throws IOException {
        /*  Vinculaciones esperadas
            Egreso  Ingreso
            2       1
            3       1
            5       2
        */
        Map<OperacionEgreso, OperacionIngreso> mapVinculaciones = this.adapter.obtenerVinculaciones(this.operacionesIngreso, this.operacionesEgreso, Constantes.vinculador_criterios_Fecha);
        Assert.assertNotNull(mapVinculaciones);
        Assert.assertEquals(3, mapVinculaciones.size());
    }
}

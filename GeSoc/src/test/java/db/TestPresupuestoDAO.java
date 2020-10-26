package db;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;
import db.DAOs.PresupuestoDAOMySQL;
import domain.entities.operacionComercial.OperacionComercial;
import domain.entities.operacionComercial.Presupuesto;
import domain.entities.operacionComercial.DetalleEgreso;
import domain.entities.operacionComercial.Item;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TestPresupuestoDAO {
    private PresupuestoDAOMySQL presupuestoDAO = new PresupuestoDAOMySQL();

    @Test
    public void TestPersistirPresupuesto() {
        Presupuesto presupuesto = new Presupuesto();
        DetalleEgreso detalle1 = new DetalleEgreso();
        DetalleEgreso detalle2 = new DetalleEgreso();
        List<DetalleEgreso> detallesDelPresupuesto = new ArrayList<>();
        Item item1 = new Item();
        Item item2 = new Item();
        presupuesto.setFecha(LocalDate.of(2020, 10, 23));
        item1.setDescripcion("Bebida");
        item2.setDescripcion("Transporte");
        detalle1.setItem(item1);
        detalle2.setItem(item2);
        detalle1.setCantidad(10);
        detalle2.setCantidad(1);
        detalle1.setValorTotal(400.00);
        detalle2.setValorTotal(300.00);
        detallesDelPresupuesto.add(detalle1);
        detallesDelPresupuesto.add(detalle2);
        presupuesto.setDetalle(detallesDelPresupuesto);


        presupuestoDAO.guardarPresupuesto(presupuesto);

    }

    @Test
    public void TestRecuperarIngreso() {
        /*OperacionIngreso ingresoRecuperado = ingresoDAO.buscarIngreso(3);

        Assert.assertNotNull(ingresoRecuperado);
        Assert.assertEquals("Donacion de ANSES", ingresoRecuperado.getDescripcion());
        Assert.assertEquals(Float.valueOf(400f), ingresoRecuperado.getMonto());*/
    }
}

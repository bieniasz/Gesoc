package db;

import db.DAOs.OperacionIngresoDAOMySQL;
import domain.entities.operacionComercial.OperacionIngreso;
import domain.entities.organizacion.EntidadJuridica;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;

public class TestIngresoDAO {
    private OperacionIngresoDAOMySQL ingresoDAO = new OperacionIngresoDAOMySQL();

    @Test
    public void TestPersistirIngreso() {
        OperacionIngreso ingreso = new OperacionIngreso();
        ingreso.setDescripcion("Donacion de ANSES");
        ingreso.setFecha(LocalDate.of(2020, 10, 23));
        ingreso.setMonto(400f);
        ingreso.setOrganizacion(new EntidadJuridica());

        ingresoDAO.guardarIngreso(ingreso);
    }

    @Test
    public void TestRecuperarIngreso() {
        OperacionIngreso ingresoRecuperado = ingresoDAO.buscarIngreso(3);

        Assert.assertNotNull(ingresoRecuperado);
        Assert.assertEquals("Donacion de ANSES", ingresoRecuperado.getDescripcion());
        Assert.assertEquals(Float.valueOf(400f), ingresoRecuperado.getMonto());
    }
}

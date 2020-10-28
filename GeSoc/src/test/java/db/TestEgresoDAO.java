package db;

import db.DAOs.OperacionEgresoDAOMemoria;
import domain.entities.ProveedorDocComer.Proveedor;
import domain.entities.operacionComercial.MedioDePago;
import domain.entities.operacionComercial.OperacionEgreso;
import domain.entities.operacionComercial.OperacionIngreso;
import domain.entities.organizacion.EntidadJuridica;
import org.junit.Assert;
import org.junit.Test;

public class TestEgresoDAO {
    private OperacionEgresoDAOMemoria egresoDAO = new OperacionEgresoDAOMemoria();

    @Test
    public void TestPersistirEgreso(){
        OperacionEgreso egreso = new OperacionEgreso();
        egreso.setIngresoAsociado(new OperacionIngreso());
        egreso.setMedioDePago(new MedioDePago());
        egreso.setOrganizacion(new EntidadJuridica());
        egreso.setNumeroIdentificadorMedioPago("Numero 3");
        egreso.setProveedor(new Proveedor());

        egreso.setCantidadEsperadaPresupuestos(3);
        egresoDAO.guardarOperacionEgreso(egreso);
    }

    @Test
    public void TestRecuperarEgreso() {
        OperacionEgreso egresoRecuperado = egresoDAO.buscarEgreso(5);

        Assert.assertNotNull(egresoRecuperado);
        Assert.assertEquals("Numero 3", egresoRecuperado.getNumeroIdentificadorMedioPago());

    }

}
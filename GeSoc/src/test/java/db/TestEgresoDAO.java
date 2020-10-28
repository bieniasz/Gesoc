package db;

import db.DAOs.OperacionEgresoDAO;
import db.DAOs.OperacionEgresoDAOMemoria;
import db.DAOs.OperacionEgresoDAOMySQL;
import domain.entities.ProveedorDocComer.Proveedor;
import domain.entities.operacionComercial.MedioDePago;
import domain.entities.operacionComercial.OperacionEgreso;
import domain.entities.organizacion.EntidadJuridica;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;

public class TestEgresoDAO {
    private OperacionEgresoDAO egresoDAO = new OperacionEgresoDAOMemoria();
    private int egresoId;

    @Test
    public void TestPersistirEgreso(){
        OperacionEgreso egreso = new OperacionEgreso();
        egreso.setMedioDePago(new MedioDePago());
        egreso.setOrganizacion(new EntidadJuridica());
        egreso.setNumeroIdentificadorMedioPago("Numero 3");
        egreso.setProveedor(new Proveedor());

        egreso.setCantidadEsperadaPresupuestos(3);
        egresoDAO.guardarOperacionEgreso(egreso);
    }

    @Test
    public void TestRecuperarEgreso() throws Exception {
        OperacionEgreso egresoRecuperado = egresoDAO.buscarOperacionEgresoPorId(1);

        Assert.assertNotNull(egresoRecuperado);
        Assert.assertEquals("Numero 3", egresoRecuperado.getNumeroIdentificadorMedioPago());

    }

    @Test
    public void TestPersistirLuegoBuscarEgresoMySQL() throws Exception {
        egresoDAO = new OperacionEgresoDAOMySQL();
        LocalDate fechaHoy = LocalDate.of(2020, 10, 28);

        OperacionEgreso egreso = new OperacionEgreso();
        Assert.assertEquals(0, egreso.getId());

        egreso.setMedioDePago(new MedioDePago());
        egreso.setFecha(fechaHoy);
        egreso.setOrganizacion(new EntidadJuridica());
        egreso.setNumeroIdentificadorMedioPago("Numero 3");
        egreso.setProveedor(new Proveedor());

        egreso.setCantidadEsperadaPresupuestos(3);
        egresoDAO.guardarOperacionEgreso(egreso);

        /*
        Estas lineas funcionan de dos maneras
        Primero testeando la transaccion de guardado, porque al hacer el commit,
            guarda el id generado en el objeto
        Segundo asegurando que ese id queda luego parametrizado por this.egresoId,
            para no tener que adivinar el id al recuperarlo desde la base
        */
        this.egresoId = egreso.getId();
        Assert.assertNotEquals(0, this.egresoId);

        //Luego hago la busqueda en el mismo scope, para no perder el dato del egresoId
        OperacionEgreso egresoRecuperado = egresoDAO.buscarOperacionEgresoPorId(this.egresoId);
        Assert.assertNotNull(egresoRecuperado);
        Assert.assertEquals("Numero 3", egresoRecuperado.getNumeroIdentificadorMedioPago());
        Assert.assertEquals(fechaHoy, egresoRecuperado.getFecha());
    }
}
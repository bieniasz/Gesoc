package db;

import com.google.common.annotations.VisibleForTesting;
import db.DAOs.DireccionDAOMySQL;
import db.DAOs.DireccionDao;
import domain.entities.direccion.AtributosPersistentes.Ciudad;
import domain.entities.direccion.AtributosPersistentes.Moneda;
import domain.entities.direccion.AtributosPersistentes.Pais;
import domain.entities.direccion.AtributosPersistentes.Provincia;
import domain.entities.direccion.Direccion;
import domain.entities.direccion.DireccionBuilder;
import domain.entities.direccion.ExcepcionesDireccion.LocacionNoValidaException;
import domain.entities.direccion.MLDireccionesService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.lang.reflect.Executable;

public class TestDireccionDAO {
    private DireccionDao dao;

    @Before
    public void init(){
        this.dao = new DireccionDAOMySQL();
    }

    @Test
    public void TestPersistirCiudad_RecuperarPorId(){
        Ciudad ciudad = new Ciudad();
        ciudad.setNombre("CABA");
        dao.guardarCiudad(ciudad);
        Assert.assertNotEquals(0, ciudad.getId());

        Integer ciudadID = ciudad.getId();
        Ciudad caba = EntityManagerHelper.getEntityManager().find(Ciudad.class, ciudadID);
        Assert.assertEquals(caba.getNombre(), "CABA");
    }

    @Test
    public void TestRecuperarCiudadConQuery(){
        Ciudad caba = (Ciudad) EntityManagerHelper.createQuery("from Ciudad where nombre = 'CABA'").getSingleResult();
        Assert.assertEquals(caba.getNombre(), "CABA");
    }

    @Test
    public void TestPersistirProvincia_RecuperarPorId(){
        Provincia chaco = new Provincia();
        chaco.setNombre("Chaco");
        dao.guardarProvincia(chaco);
        Assert.assertNotEquals(0, chaco.getId());

        Provincia provincia = EntityManagerHelper.getEntityManager().find(Provincia.class, chaco.getId());
        Assert.assertEquals(provincia.getNombre(), "Chaco");
    }

    @Test
    public void TestPersistirPais(){
        Pais argentina = new Pais();
        argentina.setNombre("Argentina");
        dao.guardarPais(argentina);
        Assert.assertNotEquals(0, argentina.getId());

        Pais pais = EntityManagerHelper.getEntityManager().find(Pais.class, argentina.getId());
        Assert.assertEquals(pais.getNombre(), "Argentina");
    }

    @Test
    public void TestPersistirMoneda_RecuperarPorId(){
        Moneda dolar = new Moneda();
        dolar.setNombre("USD");
        dao.guardarMoneda(dolar);
        Assert.assertNotEquals(0, dolar.getId());

        Moneda moneda = EntityManagerHelper.getEntityManager().find(Moneda.class, dolar.getId());
        Assert.assertEquals(moneda.getNombre(), "USD");
    }

    @Test
    public void TestPersistirDireccion_RecuperarPorID(){
        //TODO: usar direccion builder
        Direccion direccion = new Direccion();
        direccion.setCalle("Av. de Mayo");
        direccion.setAltura("1460");
        direccion.setCiudad(EntityManagerHelper.getEntityManager().find(Ciudad.class, 1));
        direccion.setProvincia(EntityManagerHelper.getEntityManager().find(Provincia.class, 1));
        direccion.setPais(EntityManagerHelper.getEntityManager().find(Pais.class, 1));
        direccion.setActivo(true);
        direccion.setMonedaLocal(EntityManagerHelper.getEntityManager().find(Moneda.class, 1));

        dao.guardarDireccion(direccion);
        Assert.assertNotEquals(0, direccion.getId());

        Direccion direccionRec = EntityManagerHelper.getEntityManager().find(Direccion.class, direccion.getId());

        Assert.assertEquals(direccionRec.getCalle(), "Av. de Mayo");
        Assert.assertEquals(direccionRec.getAltura(), "1460");
        Assert.assertEquals(direccionRec.getCiudad().getNombre(), "CABA");
        Assert.assertEquals(direccionRec.getProvincia().getNombre(), "Chaco");
        Assert.assertEquals(direccionRec.getPais().getNombre(), "Argentina");
        Assert.assertEquals(direccionRec.getMonedaLocal().getNombre(), "USD");

    }

    @Test
    public void TestGuardarDireccionContruidaPorBuilder() throws Exception {
        DireccionBuilder builder = new DireccionBuilder();
        builder.setProveedorDatosDirecciones(new MLDireccionesService());

        Direccion direccionConstruidaPorBuilder = builder
                .setPais("Argentina")
                .setProvincia("Corrientes")
                .setCiudad("Bella Vista")
                .setCalle("Maipu")
                .setAltura("768")
                .setMonedaLocal("ARS")
                .build();

        dao.guardarDireccion(direccionConstruidaPorBuilder);
    }



}

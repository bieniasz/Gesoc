package db;

import com.google.common.annotations.VisibleForTesting;
import db.DAOs.DireccionDAOMySQL;
import db.DAOs.DireccionDao;
import domain.entities.direccion.AtributosPersistentes.Ciudad;
import domain.entities.direccion.AtributosPersistentes.Moneda;
import domain.entities.direccion.AtributosPersistentes.Pais;
import domain.entities.direccion.AtributosPersistentes.Provincia;
import domain.entities.direccion.Direccion;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestDireccionDAO {
    private DireccionDao dao;

    @Before
    public void init(){
        this.dao = new DireccionDAOMySQL();
    }

    @Test
    public void TestPersistirCiudad(){
        Ciudad ciudad = new Ciudad();
        ciudad.setNombre("Cordoba");

        dao.guardarCiudad(ciudad);
    }

    @Test
    public void TestRecuperarCiudadConQuery(){
        Ciudad caba = (Ciudad) EntityManagerHelper.createQuery("from Ciudad where nombre = 'CABA'").getSingleResult();
        Assert.assertEquals(caba.getNombre(), "CABA");
    }

    @Test
    public void TestRecuperarCiudadPorId(){
        Ciudad caba = EntityManagerHelper.getEntityManager().find(Ciudad.class, 1);
        Assert.assertEquals(caba.getNombre(), "CABA");
    }

    @Test
    public void TestPersistirProvincia(){
        Provincia provincia = new Provincia();
        provincia.setNombre("Chaco");

        dao.guardarProvincia(provincia);
    }

    @Test
    public void TestRecuperarProvinciaPorId(){
        Provincia caba = EntityManagerHelper.getEntityManager().find(Provincia.class, 1);
        Assert.assertEquals(caba.getNombre(), "Chaco");
    }

    @Test
    public void TestPersistirPais(){
        Pais pais = new Pais();
        pais.setNombre("Argentina");

        dao.guardarPais(pais);
    }

    @Test
    public void TestRecuperarPaisPorId(){
        Pais pais = EntityManagerHelper.getEntityManager().find(Pais.class, 1);
        Assert.assertEquals(pais.getNombre(), "Argentina");
    }

    @Test
    public void TestPersistirMoneda(){
        Moneda moneda = new Moneda();
        moneda.setNombre("USD");

        dao.guardarMoneda(moneda);
    }

    @Test
    public void TestRecuperarMonedaPorId(){
        Moneda moneda = EntityManagerHelper.getEntityManager().find(Moneda.class, 1);
        Assert.assertEquals(moneda.getNombre(), "USD");
    }

    @Test
    public void TestPersistirDireccion(){
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
    }

    @Test
    public void TestRecuperarDireccion(){
        Direccion direccion = EntityManagerHelper.getEntityManager().find(Direccion.class, 1);

        Assert.assertEquals(direccion.getCalle(), "Av. de Mayo");
        Assert.assertEquals(direccion.getAltura(), "1460");
        Assert.assertEquals(direccion.getCiudad().getNombre(), "CABA");
        Assert.assertEquals(direccion.getProvincia().getNombre(), "Chaco");
        Assert.assertEquals(direccion.getPais().getNombre(), "Argentina");
        Assert.assertEquals(direccion.getMonedaLocal().getNombre(), "USD");
    }
}

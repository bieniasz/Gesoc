package db;

import db.DAOs.DireccionDAOMySQL;
import db.DAOs.DireccionDao;
import domain.entities.ProveedorDocComer.Proveedor;
import domain.entities.direccion.AtributosPersistentes.Ciudad;
import domain.entities.direccion.Direccion;
import org.junit.Assert;
import org.junit.Test;

public class TestProveedorDao {
    private DireccionDao dao = new DireccionDAOMySQL();



    @Test
    public void persistirProveedorTest(){
        Ciudad caba = EntityManagerHelper.getEntityManager().find(Ciudad.class, 1);
        Direccion direccion = new Direccion();
        direccion.setCiudad(caba);
        direccion.setCalle("Juncal");

        Proveedor proveedor = new Proveedor();
        proveedor.setNombreApellido_RazonSocial("Coca cola SRL");
        proveedor.setTipoIdentificacion("CUIT");
        proveedor.setNumeroIdentificacion((long) 11234511);
        proveedor.setDireccionPostal(direccion);
        proveedor.setEstado("Soltera");
        proveedor.setActivo(true);

        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.getEntityManager().persist(proveedor);
        EntityManagerHelper.commit();
    }
}

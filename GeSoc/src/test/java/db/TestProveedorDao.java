package db;

import db.DAOs.DireccionDAOMySQL;
import db.DAOs.DireccionDao;
import db.DAOs.ProveedorDAO;
import db.DAOs.ProveedorDAOMySQL;
import domain.entities.ProveedorDocComer.Proveedor;
import domain.entities.direccion.AtributosPersistentes.Ciudad;
import domain.entities.direccion.Direccion;
import domain.entities.direccion.DireccionBuilder;
import domain.entities.direccion.ExcepcionesDireccion.LocacionNoValidaException;
import domain.entities.direccion.MLDireccionesService;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class TestProveedorDao {
    private ProveedorDAO proveedorDAO = new ProveedorDAOMySQL();


    @Test
    public void TestPersistirProveedor() throws Exception {
        DireccionBuilder builder = new DireccionBuilder();
        builder.setProveedorDatosDirecciones(new MLDireccionesService());

        Direccion direccion = builder
                .setPais("Argentina")
                .setProvincia("Corrientes")
                .setCiudad("Bella Vista")
                .setCalle("Maipu")
                .setAltura("768")
                .setMonedaLocal("ARS")
                .build();

        Proveedor proveedor = new Proveedor();
        proveedor.setNombreApellido_RazonSocial("Coca cola SRL");
        proveedor.setTipoIdentificacion("CUIT");
        proveedor.setNumeroIdentificacion((long) 11234511);
        proveedor.setDireccionPostal(direccion);
        proveedor.setEstado("Soltera");
        proveedor.setActivo(true);

        proveedorDAO.guardarProveedor(proveedor);
    }

    @Test
    public void TestRecuperarProveedor() {
        Proveedor proveedor = proveedorDAO.getProveedor(1);

        Assert.assertEquals(proveedor.getNombreApellido_RazonSocial(), "Coca cola SRL");
        Assert.assertEquals(proveedor.getTipoIdentificacion(), "CUIT");
        Assert.assertEquals((long) proveedor.getNumeroIdentificacion(), (long) 11234511);
        Assert.assertEquals(proveedor.getEstado(), "Soltera");
    }
}

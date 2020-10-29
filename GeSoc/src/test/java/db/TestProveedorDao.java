package db;

import db.DAOs.ProveedorDAOMySQL;
import domain.entities.ProveedorDocComer.Proveedor;
import domain.entities.direccion.Direccion;
import domain.entities.direccion.DireccionBuilder;
import domain.entities.direccion.MLDireccionesService;
import org.junit.Assert;
import org.junit.Test;

public class TestProveedorDao {
    private ProveedorDAOMySQL proveedorDAO = new ProveedorDAOMySQL();


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

    @Test
    public void TestRecuperarProveedorPorQuery() {
        Proveedor proveedor = proveedorDAO.buscarProveedorPorNombre("Coca cola SRL");

        Assert.assertEquals(proveedor.getNombreApellido_RazonSocial(), "Coca cola SRL");
        Assert.assertEquals(proveedor.getTipoIdentificacion(), "CUIT");
        Assert.assertEquals((long) proveedor.getNumeroIdentificacion(), (long) 11234511);
        Assert.assertEquals(proveedor.getEstado(), "Soltera");
        Assert.assertEquals(proveedor.getId(), 1);
    }

    @Test
    public void TestModificarProveedor() {
        Proveedor proveedor = proveedorDAO.getProveedor(1);
        proveedor.setEstado("fffffff");
        proveedorDAO.actualizarProveedor(proveedor);

        proveedor = proveedorDAO.getProveedor(1);
        Assert.assertEquals(proveedor.getNombreApellido_RazonSocial(), "Coca cola SRL");
        Assert.assertEquals(proveedor.getTipoIdentificacion(), "CUIT");
        Assert.assertEquals((long) proveedor.getNumeroIdentificacion(), (long) 11234511);
        Assert.assertEquals(proveedor.getEstado(), "fffffff");
    }
}

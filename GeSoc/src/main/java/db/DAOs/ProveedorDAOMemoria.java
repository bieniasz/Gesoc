package db.DAOs;

import domain.entities.ProveedorDocComer.Proveedor;

import java.util.ArrayList;
import java.util.List;

public class ProveedorDAOMemoria implements ProveedorDAO{
    private List<Proveedor> proveedors = new ArrayList<>();

    @Override
    public void guardarProveedor(Proveedor proveedor) {

    }

    @Override
    public Proveedor getProveedor(Integer id) {
        Proveedor proveedor1 = new Proveedor();
        proveedor1.setNombreApellido_RazonSocial("Alfred SA");
        proveedor1.setId(1);

        return proveedor1;
    }

    @Override
    public List<Proveedor> getTodosLosProveedores() {
        Proveedor proveedor1 = new Proveedor();
        proveedor1.setNombreApellido_RazonSocial("Alfred SA");
        proveedor1.setId(1);
        Proveedor proveedor2 = new Proveedor();
        proveedor2.setNombreApellido_RazonSocial("Doritos SRL");
        proveedor2.setId(2);
        Proveedor proveedor3 = new Proveedor();
        proveedor3.setNombreApellido_RazonSocial("Almirante Brown SA");
        proveedor3.setId(3);


        proveedors.add(proveedor1);
        proveedors.add(proveedor2);
        proveedors.add(proveedor3);

        return proveedors;
    }
}

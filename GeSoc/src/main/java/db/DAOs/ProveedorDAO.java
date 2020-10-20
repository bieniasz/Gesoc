package db.DAOs;

import domain.entities.ProveedorDocComer.Proveedor;

public interface ProveedorDAO {

    public void guardarProveedor(Proveedor proveedor);
    public Proveedor getProveedor(Integer id);
}

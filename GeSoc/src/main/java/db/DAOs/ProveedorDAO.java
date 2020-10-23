package db.DAOs;

import domain.entities.ProveedorDocComer.Proveedor;

import java.util.List;

public interface ProveedorDAO {

    public void guardarProveedor(Proveedor proveedor);
    public Proveedor getProveedor(Integer id);
    public List<Proveedor> getTodosLosProveedores();
}

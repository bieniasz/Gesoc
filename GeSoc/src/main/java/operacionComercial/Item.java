package operacionComercial;

import ProveedorDocComer.Proveedor;

import java.util.List;

public class Item { //TODO evaluar nombre mas descriptivo como "ItemOperacionComercial"
    private String descripcion;
    private List<Proveedor> proveedores;

    public void altaItem() {}

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<Proveedor> getProveedores() {
        return proveedores;
    }

    public void setProveedores(List<Proveedor> proveedores) {
        this.proveedores = proveedores;
    }
}

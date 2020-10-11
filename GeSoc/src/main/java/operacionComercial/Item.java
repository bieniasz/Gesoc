package operacionComercial;

import ProveedorDocComer.Proveedor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.List;


@Entity
@Table(name="Item")
public class Item extends EntidadPersistente{ //TODO evaluar nombre mas descriptivo como "ItemOperacionComercial"

    @Column
    private String descripcion;
    @ManyToMany
    private List<Proveedor> proveedores;

    @Column
    private boolean activo;

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

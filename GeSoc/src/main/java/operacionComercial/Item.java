package operacionComercial;

import ProveedorDocComer.Proveedor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="Item")
public class Item extends EntidadPersistente { //TODO evaluar nombre mas descriptivo como "ItemOperacionComercial"

    @Column
    private String descripcion;

    @ManyToMany
    private List<Proveedor> proveedores;

    public void altaItem() {}
}

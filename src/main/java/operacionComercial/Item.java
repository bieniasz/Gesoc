package operacionComercial;

import ProveedorDocComer.Proveedor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.List;

@Entity
@Table(name="Item")
public class Item extends EntidadPersistente { //TODO evaluar nombre mas descriptivo como "ItemOperacionComercial"

    @Column
    private String descripcion;

    @Transient
    private List<Proveedor> proveedores;

    public void altaItem() {}
}

package domain.entities.operacionComercial;

import domain.entities.ProveedorDocComer.Proveedor;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name="Item")
public class Item extends EntidadPersistente { //TODO evaluar nombre mas descriptivo como "ItemOperacionComercial"

    public Item() {
    }

    @Column
    private String descripcion;


    @Column
    private boolean activo;

    public void altaItem() {
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

}


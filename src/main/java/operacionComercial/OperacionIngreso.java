package operacionComercial;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table
public class OperacionIngreso extends EntidadPersistente {

    @Column
    String descripcion;

    @Column
    Float monto;

    public OperacionIngreso(String descripcion, float monto){
        this.descripcion = descripcion;
        this.monto = monto;
    }

    /* GETTERS & SETTERS */
    public String getDescripcion() {
        return descripcion;
    }
    public Float getMonto() {
        return monto;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public void setMonto(Float monto) {
        this.monto = monto;
    }
}

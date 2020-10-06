package operacionComercial;

//import jdk.vm.ci.meta.Local;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table
public class OperacionIngreso extends EntidadPersistente {

    @Column
    String descripcion;

    @Column(columnDefinition = "DATE")
    private LocalDate fecha;

    @Column
    Float monto;

    public OperacionIngreso(String descripcion, LocalDate fecha, float monto){
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.monto = monto;
    }

    /* GETTERS & SETTERS */
    public String getDescripcion() {
        return descripcion;
    }
    public Float getMonto() {
        return monto;
    }
    public LocalDate getFecha() {
        return fecha;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public void setMonto(Float monto) {
        this.monto = monto;
    }
    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

}

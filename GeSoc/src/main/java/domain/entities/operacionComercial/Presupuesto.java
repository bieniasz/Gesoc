package domain.entities.operacionComercial;

import javax.persistence.*;

@Entity
@DiscriminatorValue("Presupuesto")
public class Presupuesto extends OperacionComercial{

    @ManyToOne
    @JoinColumn(name="OperacionEgreso", referencedColumnName = "id")
    private OperacionEgreso egreso;

    @Column
    private boolean esElElegido = false;


    public OperacionEgreso getEgreso() { return egreso; }
    public boolean isElegido() { return esElElegido; }

    public void setElegido(boolean elegido) { this.esElElegido = elegido; }
    public void setEgreso(OperacionEgreso egreso) {
        this.egreso = egreso;
        egreso.asociarPresupuesto(this);
    }
}

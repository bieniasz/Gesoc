package domain.entities.operacionComercial;

import javax.persistence.*;

@Entity
@DiscriminatorValue("Presupuesto")
public class Presupuesto extends OperacionComercial{

    public Presupuesto() {
    }

    @ManyToOne(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
    @JoinColumn(name="egreso", referencedColumnName = "id")
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

package domain.entities.operacionComercial;

import javax.persistence.*;

@Entity
@Table(name="DetalleEgreso")
public class DetalleEgreso extends EntidadPersistente {

    public DetalleEgreso() {
    }

    @OneToOne(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
    public Item item;

    @Column
    public int cantidad;

    @Column
    public Double valorTotal;

	public Double getValorTotal() {
		return valorTotal;
	}

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }
}

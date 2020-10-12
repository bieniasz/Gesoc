package domain.entities.operacionComercial;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="DetalleEgreso")
public class DetalleEgreso extends EntidadPersistente {

    @OneToOne
    public Item item;

    @Column
    public int cantidad;

    @Column
    public Double valorTotal;

	public Double getValorTotal() {
		return valorTotal;
	}

}
